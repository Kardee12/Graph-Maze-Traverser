package Manishankar.cs146.project3;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for printing solved algorithms
 */
public class MazeSolverPrinted {

    private CellNode[][] maze;

    public MazeSolverPrinted(CellNode[][] maze) {
        this.maze = maze;
    }
    /**
     * Prints out solved maze with shortest path or with visited Cells
     * @param Path Path to follow based on whether we want path with hashtags or path with visitedCells
     * @param wNumbers boolean that is used to determine whether we want hashes or numbers
     * @return SolvedMaze string
     */
    public String solvedMaze(ArrayList<CellNode> Path, boolean wNumbers){
        //use string builder
        String representation = "";
        //hashes
        if(!wNumbers){
            for(int i = 0; i < maze.length; i ++){
                boolean eastWall = maze[i][maze.length-1].getEast();
                representation += "\n";
                representation += "+";
                //north walls printing by going through each node and checking if it was in there
                boolean onPath = false;
                for(int j = 0; j < maze[i].length; j++){
                    onPath = Path.contains(maze[i][j]);
                    String wall = "";
                    if (maze[i][j].getNorth()){
                        wall = "-";
                    }
                    //prints segue between walls by checking if the path contains the node and the node in the row before it
                    else if(onPath && (i-1 >= 0 && Path.contains(maze[i-1][j]))){
                        wall = "#";
                    }
                    else{
                        wall = " ";
                    }
                    representation += wall + "+";
                }
                representation += "\n";

                //west walls
                //west walls printing by going through each node and checking if it was in there
                //goes through for loop to determine whether it should add an "|" or an " " or an "#" based on value of northWall and
                // then adds + to signify that wall is done being added
                for(int j = 0; j < maze[i].length; j++){
                    onPath = Path.contains(maze[i][j]);
                    String wall = "";
                    if (maze[i][j].getWest()){
                        wall = "|";
                    }
                    //
                    else if(onPath && (j-1 >= 0 && Path.contains(maze[i][j-1]))){
                        wall = "#";
                    }
                    else{
                        wall = " ";
                    }
                    representation += wall + (onPath? "#" : " ");
                }
                representation += (eastWall?"|":" ");

                //south wall
                //prints wall based on if south wall is true in last row nodes and then if there puts "-", else " "
                if(i == maze.length-1){
                    representation += "\n+";
                    String wall = "";
                    for(int j = 0; j < maze[i].length;j++){
                        if(maze[i][j].getSouth()){
                            wall = "-";
                        }
                        else{
                            wall = " ";
                        }
                        representation += wall + "+";
                    }
                }
            }
        }
        //does same as above but instead of printing hashtags, it prints the value of the node
        else{
            for(int i = 0; i < maze.length; i ++){
                List<Boolean> northWalls = new ArrayList<Boolean>() ;
                List<Boolean> westWalls = new ArrayList<Boolean>();
                List<Boolean> southWalls = new ArrayList<Boolean>();

                boolean eastWall = maze[i][maze.length-1].getEast();
                representation += "\n";
                representation += "+";

                //north walls printing
                boolean onPath = false;
                for(int j = 0; j < maze[i].length; j++){
                    onPath = Path.contains(maze[i][j]);
                    String wall = "";
                    if (maze[i][j].getNorth()){
                        wall = "-";
                    }
                    else{
                        wall = " ";
                    }
                    representation += wall + "+";
                }

                representation += "\n";

                //west walls
                for(int j = 0; j < maze[i].length; j++){
                    onPath = Path.contains(maze[i][j]);
                    String wall = "";
                    if (maze[i][j].getWest()){
                        wall = "|";
                    }
                    else{
                        wall = " ";
                    }
                    representation += wall + (onPath? (String.valueOf(maze[i][j].getValue())) : " ");
                }
                representation += (eastWall?"|":" ");
                //south wall
                if(i == maze.length-1){
                    representation += "\n+";
                    String wall = "";
                    for(int j = 0; j < maze[i].length;j++){
                        if(maze[i][j].getSouth()){
                            wall = "-";
                        }
                        else{
                            wall = " ";
                        }
                        representation += wall + "+";
                    }
                }
            }
        }
        return representation;
    }

    /**
     *If algo is true, it goes through BFS visited count
     * If algo is false, it goes through DFS visited count
     * @param algo to signify if we're traversing through the BFS or DFS
     * @return visitedCellsCount
     */
    public int getVisitedCells(int visitedCount, boolean algo, ArrayList<CellNode> path){
        if(algo){
            return visitedCount;
        }
        else{
            visitedCount = 0;
            for(int i = 0; i < maze.length; i++){
                for(int j = 0; j < maze[i].length; j++){
                    if(path.contains(maze[i][j])){
                        visitedCount +=1;
                    }
                }
            }
        }
        return visitedCount;
    }
    /**
     * Method that returns a string with the path of node order visited
     * @return String with path
     */
    public String cellsOfPath(ArrayList<CellNode> path){
        String lengthPath = "";
        for(int i = 0; i < path.size();i++){
            lengthPath += path.get(i) + " ";
        }
        return lengthPath;
    }
    /**
     * Returns length of path
     * @return length of path
     */
    public int getPathSize(ArrayList<CellNode> path){
        return path.size();
    }
}