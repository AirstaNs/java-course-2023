package edu.project2;

import edu.project2.generator.PrimMazeGenerator;
import edu.project2.model.MazeDimensions;
import edu.project2.renderer.ConsolePrinter;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.renderer.RenderingConfig;
import edu.project2.solver.AStarSolver;
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
        MazeManager mazeManager = new MazeManager(primMazeGenerator, new AStarSolver(), renderer);
        mazeManager.generate();
        mazeManager.render(false);
        System.out.println("===============================================================");
        mazeManager.solve(start, end);
        mazeManager.render(true);
    }
}