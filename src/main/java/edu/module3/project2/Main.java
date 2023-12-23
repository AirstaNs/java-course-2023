package edu.module3.project2;

import edu.module5.hw9.task3.ConcurrentDFSSolver;
import edu.module3.project2.generator.PrimMazeGenerator;
import edu.module3.project2.renderer.ConsolePrinter;
import edu.module3.project2.renderer.MazeRenderer;
import edu.module3.project2.renderer.Renderer;
import edu.module3.project2.renderer.RenderingConfig;
import edu.module3.project2.model.MazeDimensions;
import java.awt.Point;
import java.util.Random;

@SuppressWarnings({"RegexpSinglelineJava", "magicnumber"})
public final class Main {

    private static final int HEIGHT = 21;
    private static final int WIDTH = 21;

    private Main() {
    }

    public static void main(String[] args) {
        Random random = new Random(931239L);
        var mazeDimensions = new MazeDimensions(WIDTH, HEIGHT);
        PrimMazeGenerator primMazeGenerator = new PrimMazeGenerator(random, mazeDimensions);
        Point start = new Point(1, 1);
        Point end = new Point(HEIGHT - 2, WIDTH - 2);

        Renderer renderer = new MazeRenderer(new ConsolePrinter(new RenderingConfig()));
        MazeManager mazeManager = new MazeManager(primMazeGenerator, new ConcurrentDFSSolver(), renderer);
        mazeManager.generate();
        mazeManager.render(false);
        System.out.println("===============================================================");
        mazeManager.solve(start, end);
        mazeManager.render(true);
    }
}
