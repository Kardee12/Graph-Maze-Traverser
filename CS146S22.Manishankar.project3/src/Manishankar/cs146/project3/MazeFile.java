package Manishankar.cs146.project3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Maze Output File Creator that creates the File with the maze results
 */
public class MazeFile {

    private int dimension;

    public MazeFile(int dimension){
        this.dimension = dimension;
    }
    public File fileOutput() throws IOException {
        try{
            MazeGeneratorSolver x = new MazeGeneratorSolver(dimension);
            x.generateMaze();
            String result = x.toString();
            FileWriter fw = new FileWriter("MazeOutput.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Graph size: " + dimension);
            bw.write("\n");
            bw.write("Maze: ");
            bw.write(result);
            bw.write("\n");
            bw.write("\n");
            bw.write(x.printBFSFull());
            System.out.println("Going through BFS");
            bw.write("\n");
            System.out.println("DFS");
            bw.write(x.printDFSFull());
            bw.write("\n");
            bw.write("======================");
            bw.write("\n");
            bw.write("  Program Completed!  ");
            bw.write("\n");
            bw.write("======================");
            bw.close();
            fw.close();
            
            return new File("MazeOutput.txt");
        }
        catch (IOException e){
            System.out.println(e.getStackTrace());
        }
		return null;

    }
}