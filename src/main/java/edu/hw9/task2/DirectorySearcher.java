package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class DirectorySearcher extends RecursiveTask<Long> {
    private final Path currentDir;

    private final DiskCollector<Long> dirCollector;
    private final DiskCollector<BasicFileAttributes> fileCollector;

    public DirectorySearcher(
        Path currentDir, DiskCollector<Long> dirCollector, DiskCollector<BasicFileAttributes> fileCollector
    ) {
        this.currentDir = currentDir;
        this.dirCollector = dirCollector;
        this.fileCollector = fileCollector;
    }

    private static BasicFileAttributes getAttributes(Path path) {
        try {
            return Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long compute() {
        if (!Files.isDirectory(currentDir)) {
            throw new IllegalArgumentException("currentDir must be directory");
        }
        long countFiles = processFilesAndDirectories();
        dirCollector.collect(currentDir, countFiles);
        return countFiles;
    }

    private long processFilesAndDirectories() {
        var store = processDirectory();
        List<DirectorySearcher> dirs = store.getDirectories();
        List<Path> files = store.getFiles();

        long fileCount = files.size();

        if (!dirs.isEmpty()) {
            fileCount += ForkJoinTask.invokeAll(dirs)
                .stream().mapToLong(ForkJoinTask::join).sum();
        }

        return fileCount;
    }

    private Store processDirectory() {
        Store store = new Store();
        try (var stream = Files.newDirectoryStream(currentDir)) {
            for (Path path : stream) {
                var attributes = getAttributes(path);
                if (attributes.isDirectory()) {
                    var task = new DirectorySearcher(path, dirCollector, fileCollector);
                    store.addDir(task);
                } else if (attributes.isRegularFile()) {
                    store.addFile(path);
                    fileCollector.collect(path, attributes);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return store;
    }
}
