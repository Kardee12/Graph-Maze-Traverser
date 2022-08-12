package Manishankar.cs146.project3;

import java.util.*;
/**
 * Class that generates maze and solves the maze through DFS algo and BFS algo while also printing it
 */
public class MazeGeneratorSolver {
    /**
     * 2D array of CellNode objects called maze
     */
    private CellNode[][] maze;
    /**
     * size of array
     */
    private int size;
    /**
     * random object for use in generating maze
     */
    private Random random;
    /**
     * Cellnodes at starting location and ending location
     */
    private CellNode startingNode;
    private CellNode endingNode;
    /**
     * Variables required for DFS and BFS algorithms
     */
    private int beginTime;
    private ArrayList<CellNode> path;
    private int visitedCount = 0;
    private int visitedCountDFS = 0;
    private ArrayList<CellNode> visitedCellsPathBFS;
    private ArrayList<CellNode> dfsList;
    private ArrayList<CellNode> visitedCellsPathDFS;
    private int pathCount = 0;
    /**
     * To help us calculate North, South, West, and East neighbors of node
     */
    private static final List<int[]> DIRECTIONS = Arrays.asList(new int[] {0,1}, new int[] {1,0},new int[] {0,-1}, new int[] {-1,0});
    private MazeSolverPrinted printer;

    /**
     * Constructor for MazeGeneratorSolver that makes a new MazeGeneratorSolver object in addition to
     * filling the 2D CellNode maze with CellNode objects and setting walls in locations where we know there's not a
     * specific wall.
     * @param x the length and width of the maze that we want
     */
    public MazeGeneratorSolver(int x){
    	if(x == 0) {
    		throw new IllegalArgumentException("Size cannot be zero");
    	}
        this.size = x;
        int total = x*x;
        random = new Random(1734);
        maze = new CellNode[x][x];
        int count = 0;
        for(int r = 0; r < maze.length;r++){
            for(int c = 0; c < maze[r].length; c++){
                maze[r][c] = new CellNode(0,r, c, size);
                count+=1;
                if(r == 0 && c == 0){
                    maze[r][c].setWall(false,0);
                }
                //check if at [row-1][column-1] and remove South wall
                if(r == maze.length-1 && c == maze.length-1){
                    maze[r][c].setWall(false,1);
                }
            }
        }
        this.startingNode = maze[0][0];
        this.endingNode = maze[maze.length-1][maze.length-1];
        this.beginTime = 0;
        visitedCount = 0;
        int pathCount = 0;
        path = new ArrayList<CellNode>();
        visitedCellsPathBFS = new ArrayList<CellNode>();
        dfsList = new ArrayList<CellNode>();
        visitedCellsPathDFS = new ArrayList<CellNode>();
        printer = new MazeSolverPrinted(maze);
    }
    public CellNode findNode(int row, int column) {return maze[row][column];}

    /**
     * Finds the neighbors of the current node while also using the DIRECTIONS variable.
     * @param node the current node
     * @return Arraylist of neighbors
     */
    public ArrayList<CellNode> getDirectionsToNodeList(CellNode node) {
        ArrayList<CellNode> neighbors = new ArrayList<>();
        for (int[] direction: DIRECTIONS) {
            int x = direction[0] + node.getRow();
            int y = direction[1] + node.getColumn();
            if(x >= 0 && x < size && y >= 0 && y < size){
                neighbors.add(findNode(x,y));
            }
        }
        return neighbors;
    }
    /**
     * Algorithm given by Professor Potika that generates the maze
     */
    public void generateMaze(){
        Stack<CellNode> cellStack = new Stack<>();
        int totalCells = size*size;
        CellNode currentCell = maze[0][0];
        int visitedCells = 1;
        while (visitedCells < totalCells){
            ArrayList<CellNode> neighborsIntactWalls = validNeighborsWithIntactWalls(currentCell);
            if(neighborsIntactWalls.size()>=1){
                int randomNodeIndex = random.nextInt(neighborsIntactWalls.size());
                CellNode randomNode = neighborsIntactWalls.get(randomNodeIndex);
                currentCell.knockDownWall(randomNode);
                cellStack.push(currentCell);
                currentCell = randomNode;
                visitedCells+=1;
            }
            else {
                if(!cellStack.isEmpty()){
                    currentCell = cellStack.pop();
                }
            }
        }
        System.out.println("Done Generating maze");
    }

    /**
     * Finds all neighbors that have an intact wall
     * @param node the current node
     * @return ArrayList of nodes that are neighbors to the current node that have intact walls
     */
    public ArrayList<CellNode> validNeighborsWithIntactWalls(CellNode node){
        ArrayList<CellNode> intactNeighbors = new ArrayList<>();
        ArrayList<CellNode> neighbors = new ArrayList<CellNode>();
        neighbors = getDirectionsToNodeList(node);
        for(int i = 0; i < neighbors.size(); i++){
            if(neighbors.get(i).intactWalls()){
                intactNeighbors.add(neighbors.get(i));
            }
        }
        return intactNeighbors;
    }
    /**
     * Works similar to the validNeighborsWithIntactWalls, but returns arrayList that has neighbors that don't have a wall
     * between the 2 nodes
     * @param node current Node
     * @return ArrayList of nodes that are neighbors to the current node that are connected
     */
    public ArrayList<CellNode> reachableNeighbors(CellNode node){
        ArrayList<CellNode> reachableNeighbors = new ArrayList<>();
        ArrayList<CellNode> neighbors = new ArrayList<CellNode>();
        neighbors = getDirectionsToNodeList(node);

        for(int i = 0; i < neighbors.size(); i ++){
            if(node.isConnected(neighbors.get(i))){
                reachableNeighbors.add(neighbors.get(i));
            }
        }
        return reachableNeighbors;
    }

    /**
     * toString method that prints out the empty maze
     * @return String representation of the maze
     */
    public String toString() {
        //empty String
        String representation = "";
        //goes through each row
        for (int i = 0; i < maze.length; i++) {
            List<Boolean> northWalls = new ArrayList<Boolean>();
            List<Boolean> westWalls = new ArrayList<Boolean>();
            List<Boolean> southWalls = new ArrayList<Boolean>();
            //Adds North walls and West walls first, then checks if we're at last row before adding south value to array list
            for (int j = 0; j < maze[i].length; j++) {
                northWalls.add(maze[i][j].getNorth());
                westWalls.add(maze[i][j].getWest());
                if (i == maze.length - 1) {
                    southWalls.add(maze[i][j].getSouth());
                }
            }
            //finds out if node has an east wall
            boolean eastWall = maze[i][maze.length - 1].getEast();
            //string adds new wall and then a +
            representation += "\n";
            representation += "+";
            //goes through for loop to determine whether it should add an "-" or an " " based on value of northWall and
            // then adds + to signify that north wall is done being added
            for (boolean wall : northWalls) {
                representation += (wall ? "-" : " ") + "+";
            }
            //goes through for loop to determine whether it should add an "|" or an " " based on value of westWall and
            // then adds + to signify that west wall is done being added
            //new line on representation since, west wall and east wall should have | in equal fixed sized output with north walls
            representation += "\n";
            for (boolean wall : westWalls) {
                representation += (wall ? "|" : " ") + " ";
            }
            
            representation += (eastWall ? "|" : " ");
            // if on last row, south wall should be "-" or " " based on value of southWalls arrayList index
            if (i == maze.length - 1) {
                representation += "\n+";
                for (boolean wall : southWalls) {
                    representation += (wall ? "-" : " ") + "+";
                }
            }
        }
        return representation;
    }

    /**
     * method that resets every node to its default values so that we can run a new graph algorithm on it
     */
    public void resetMaze() {
        for (CellNode[] cellNodes : maze) {
            for (int j = 0; j < cellNodes.length; j++) {
                cellNodes[j].colors = color.White;
                cellNodes[j].setDistance(Integer.MAX_VALUE);
                cellNodes[j].setPrev(null);
                cellNodes[j].setTime(0);
                cellNodes[j].setValue(0);
            }
        }
    }
    /**
     * Searching Algorithms
     * BFS: Algorithm that iteratively calculates the shortest path distance from beginning of maze to end of the maze
     * DFS: Algorithm that recursively calculates the shortest path distance from beginning of maze to end of the maze
     */
    //BFS Algorithm
    /**
     * BFS algorithm that returns maze with BFS results, both hashtags and numbers
     * Uses algorithm that we learned about during lecture
     * @param pathChecker to determine if we want hashtags or numbers
     * @return BFS solved maze
     */
    public String bfs(boolean pathChecker){
        resetMaze();
        startingNode.setColors(color.Gray);
        startingNode.setDistance(0);
        startingNode.setPrev(null);
        visitedCount = 1;
        Queue<CellNode> bfsQueue = new LinkedList<>();
        boolean end = false;
        bfsQueue.add(startingNode);
        visitedCellsPathBFS.add(startingNode);
        startingNode.setValue(visitedCount-1);
        while(!bfsQueue.isEmpty() && !end){
            CellNode otherNode = bfsQueue.remove();
            ArrayList<CellNode> neighbors = reachableNeighbors(otherNode);
            for(CellNode node: neighbors ){
                if(node.getRow() == maze.length-1 && node.getColumn() == maze.length-1){
                    end = true;
                }
                if(node.colors == color.White){
                    node.colors = color.Gray;
                    node.setDistance(otherNode.getDistance()+1);
                    visitedCount ++;
                    node.setPrev(otherNode);
                    node.setValue((visitedCount-1)%10);
                    visitedCellsPathBFS.add(node);
                    bfsQueue.add(node);
                }
            }
            otherNode.colors = color.Black;
        }
        if(pathChecker){
            path = returnPath(startingNode, endingNode,new ArrayList<CellNode>());
            return printer.solvedMaze(path, false);

        }
        else{
            return printer.solvedMaze(visitedCellsPathBFS, true);
        }
    }

    /**
     *Returns array lists of path of the algorithms; algorithm learned about in class
     * @param startingNode first node
     * @param endingNode last node
     * @param path DFS path or BFS path
     * @return path of algorithms
     */
    public ArrayList<CellNode> returnPath(CellNode startingNode, CellNode endingNode, ArrayList <CellNode> path){
        if(startingNode == endingNode){
            path.add(startingNode);
            //System.out.println(startingNode);
        }
        else if(endingNode.getPrev() == null){
            //System.out.println("No Path");
        }
        else{
            returnPath(startingNode, endingNode.getPrev(), path);
            path.add(endingNode);
            //System.out.println(endingNode);
        }
        return path;
    }
    /**
     * Makes string with details that go in output file
     * @return string with output file details for BFS
     */
    public String printBFSFull(){
        return "BFS: " + bfs(true) + "\n"
                + bfs(false) + "\n"
                + "Path: " + printer.cellsOfPath(path) + "\n"
                + "Length of Path: " + printer.getPathSize(path) + "\n"
                + "Visited Cells: " + printer.getVisitedCells(visitedCount,true, null) + "\n";
    }
    /**
     * Makes string with details that go in output file
     * @return string with output file details for DFS
     */
    public String printDFSFull(){
        return "DFS: " + dfs(true) + "\n"
                + dfs(false) + "\n"
                + "Path: " + printer.cellsOfPath(path) + "\n"
                + "Length of Path: " + printer.getPathSize(path) + "\n"
                + "Visited Cells: " + printer.getVisitedCells(0,false, visitedCellsPathDFS);

    }
    //DFS Algorithm
    /**
     * DFS algorithm that returns maze with DFS results, both hashtags and numbers
     * Uses algorithm that we learned about during lecture
     * @param pathChecker to determine if we want hashtags or numbers
     * @return DFS solved maze
     */
    public String dfs(boolean pathChecker) {
        resetMaze();
        beginTime = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j].colors == color.White) {
                    if(dfsVisit(maze[i][j])) {
                        break;
                    }
                }
            }
        }
        for(int i = 0; i < dfsList.size(); i++) {
            visitedCellsPathDFS.add(dfsList.get(i));
            //System.out.println(dfsList.get(i));
            if(dfsList.get(i).getRow() == maze.length-1 && dfsList.get(i).getColumn() == maze.length-1) {
                break;
            }
        }
        if(pathChecker){
            //shortest one
            //done on empty arraylist because it starts out empty and then builds onto it
            path = returnPath(startingNode, endingNode, new ArrayList<>());
            return printer.solvedMaze(path, false);
        }
        else{
            return printer.solvedMaze(visitedCellsPathDFS, true);
        }

    }
    /**
     * DFS Recursive algorithm; learned about in class
     * @param node node we're running recursive algo on
     * @return boolean to help us determine when to break dfs algo on
     */
    public boolean dfsVisit(CellNode node) {
        boolean found = false;
        if((node.getRow() == maze.length - 1 && node.getColumn() == maze.length - 1)) {
            node.colors = color.Black;
            beginTime = beginTime + 1;
//            node.setTime(beginTime);
            node.setValue((beginTime-1)%10);
            dfsList.add(node);
            return true;
        }

        node.colors = color.Gray;
        beginTime = beginTime + 1;
//        node.setDistance(beginTime);
        node.setValue((beginTime-1)%10);
        dfsList.add(node);

        ArrayList<CellNode> adjacentNodes = reachableNeighbors(node);
        for (CellNode vertice : adjacentNodes) {
            if (vertice.colors == color.White) {
                vertice.setPrev(node);
                found = dfsVisit(vertice);
            }
        }
        node.colors = color.Black;
        return found;
    }
}