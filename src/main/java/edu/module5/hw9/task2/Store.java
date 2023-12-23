package edu.module5.hw9.task2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private final List<DirectorySearcher> directories;
    private final List<Path> files;

    public Store() {
        this.directories = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public void addFile(Path path) {
        files.add(path);
    }

    public void addDir(DirectorySearcher path) {
        directories.add(path);
    }

    public List<DirectorySearcher> getDirectories() {
        return directories;
    }

    public List<Path> getFiles() {
        return files;
    }
}
