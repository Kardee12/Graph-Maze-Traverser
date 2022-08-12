package Manishankar.cs146.project3;

import java.util.*;
/**
 * CellNode class for cell object to be used in 2D maze object
 */
public class CellNode {
    /**
     * private int for size of maze
     */
    private int size;
    /**
     * private boolean array of walls
     * Essentially, the boolean array shows which walls exist and which do not.
     * [North Wall, South Wall, West Wall, East Wall]
     * Default Values are: [True, True, True, True] as shown in figure 1 of the project report
     */
    private boolean[]walls;
    //[North, South, West, East]
    //true if wall in, else false
    /**
     * helps show location of Cellnode within the maze
     */
    private final int row;
    private final int column;
    /**
     * Meant for looking at value of node during algo run
     */
    public int value;
    /**
     * for color of Node for DFS and BFS
     */
    color colors;
    /**
     * Meant for DFS
     */
    private int distance;
    /**
     * Meant for DFS
     */
    private CellNode prev;
    /**
     * Meant for DFS
     */
    private int time;

    /** Constructor for CellNode that creates a new CellNode object and automatically sets all walls for the node to be true
     *
     * @param val value  of node
     * @param r x value of node
     * @param c y value of node
     * @param size total size of maze
     */
    public CellNode(int val, int r, int c, int size){
        //sets x, y position in array
        this.size = size;
        this.row = r;
        this.column = c;
        this.value = val;
        this.walls = new boolean[4];
        this.setWalls();
        colors = color.White;
        distance = 0;
        prev = null;
    }

    /**
     * Getter method for row
     * @return x coordinate of node
     */
    public int getRow() {
        return row;
    }
    /**
     * Getter method for row
     * @return y coordinate of node
     */
    public int getColumn() {
        return column;
    }

    /**
     * Method that checks if all walls for a node are intact
     * @return boolean if walls are intact or not
     */
    public boolean intactWalls() {
        int count = 0;
        for(int i = 0; i < walls.length; i ++){
            if(walls[i]){
                count++;
            }

        }

        return count == 4 || (this.getRow()==this.size-1 && this.getColumn() == this.size-1 && count == 3);
    }

    /**
     * Sets value of wall to true or false at location that we want (North, South, West, East)
     * @param value True or False
     * @param wallIndex Location we want
     */
    //sets value of walls attribute to all true to maintain step 1 of perfect maze
    public void setWall(boolean value, int wallIndex){
        walls[wallIndex] = value;
    }

    /**
     * Fills array with all true values to signify that all walls are intact for a node
     */
    public void setWalls(){
        for(int i = 0; i < walls.length; i++){
            walls[i] = true;
        }
    }

    /**
     * Prints nodes in coordinate mode
     * @return String of node coordinates
     */
    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }

    /**
     * Knocks down the wall between current node and another node by checking node location relative to otherNode.
     * It "knocks" the wall down by changing the respective index of the Walls array to false for both nodes
     * @param otherNode the Node we want to knock down wall with the current node
     */
    public void knockDownWall(CellNode otherNode){
        //check North Wall
        if(otherNode.column == this.column && otherNode.row == this.row-1){
            walls[0] = false;
            otherNode.walls[1] = false;
        }

        //check South Wall
        if(otherNode.column == this.column && otherNode.row == this.row+1){
            walls[1] = false;
            otherNode.walls[0] = false;
        }

        //check West Wall
        if(otherNode.column == this.column-1 && otherNode.row == this.row){
            walls[2] = false;
            otherNode.walls[3] = false;
        }
        //check East Wall
        if(otherNode.column == this.column+1 && otherNode.row == this.row){
            walls[3] = false;
            otherNode.walls[2] = false;

        }
    }
    /**
     * Similar to knockDownWall method, this method checks that the wall between the two nodes has been knocked down
     * @param otherNode the node we're comparing the current node to
     * @return boolean if nodes have wall or not
     */
    public boolean isConnected(CellNode otherNode){
        boolean checker = false;
        if(otherNode.column == this.column && otherNode.row == this.row-1){
            if(!this.walls[0] && !otherNode.walls[1]){
                checker = true;
            }
        }

        //check South Wall
        if(otherNode.column == this.column && otherNode.row == this.row+1){
            if(!this.walls[1] && !otherNode.walls[0]){
                checker = true;
            }
        }

        //check West Wall
        if(otherNode.column == this.column-1 && otherNode.row == this.row){
            if(!this.walls[2] && !otherNode.walls[3]){
                checker = true;
            }
        }
        //check East Wall
        if(otherNode.column == this.column+1 && otherNode.row == this.row){
            if(!this.walls[3] && !otherNode.walls[2]){
                checker = true;
            }
        }
        return checker;
    }

    /**
     * Returns North wall value
     * @return value of North wall
     */
    public boolean getNorth() {
        return walls[0];
    }
    /**
     * Returns South wall value
     * @return value of South wall
     */
    public boolean getSouth() {
        return walls[1];
    }
    /**
     * Returns West wall value
     * @return value of West wall
     */
    public boolean getWest() {
        return walls[2];
    }
    /**
     * Returns East wall value
     * @return value of East wall
     */
    public boolean getEast() {
        return walls[3];
    }
    /**
     * Returns value of node
     * @return value of node
     */
    public int getValue() {
        return value;
    }
    /**
     * Sets value of node
     * @param value of node
     */
    public void setValue(int value) {
        this.value = value;
    }
    /**
     * Sets color of node
     * @param colors of node
     */
    public void setColors(color colors) {
        this.colors = colors;
    }
    /**
     * Gets distance of node
     * @return distance of node
     */
    public int getDistance() {
        return distance;
    }
    /**
     * Sets distance of node
     * @param distance of node
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
    /**
     * Gets previous node
     * @return previous node of node
     */
    public CellNode getPrev() {
        return prev;
    }
    /**
     * sets previous node
     * @param prev node of node
     */
    public void setPrev(CellNode prev) {
        this.prev = prev;
    }
    /**
     * set time for DFS algo
     * @param time for DFS algo
     */
    public void setTime(int time) {
        this.time = time;
    }
}