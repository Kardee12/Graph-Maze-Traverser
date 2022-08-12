package Manishankar.cs146.project3;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MazeTester {

    MazeGeneratorSolver Maze4;
    MazeGeneratorSolver Maze5;
    MazeGeneratorSolver Maze6;
    MazeGeneratorSolver Maze7;
    MazeGeneratorSolver Maze8;
    MazeGeneratorSolver Maze9;
    MazeGeneratorSolver Maze10;
    MazeGeneratorSolver Maze20;
    MazeGeneratorSolver Maze100;
    MazeGeneratorSolver Maze1;
    MazeGeneratorSolver Maze0;
    long start;
    long end;

    
    MazeFile testFile;

    String BFS;
    String DFS;
    @Before
    public void setUp(){
    	Maze1 = new MazeGeneratorSolver(1);
        Maze4 = new MazeGeneratorSolver(4);
        Maze5 = new MazeGeneratorSolver(5);
        Maze6 = new MazeGeneratorSolver(6);
        Maze7 = new MazeGeneratorSolver(7);
        Maze8 = new MazeGeneratorSolver(8);
        Maze9 = new MazeGeneratorSolver(9);
        Maze10 = new MazeGeneratorSolver(10);
        Maze20 = new MazeGeneratorSolver(20);
        Maze100 = new MazeGeneratorSolver(100);
        testFile = new MazeFile(4);
        
        BFS = "";
        DFS = "";
    }
    
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void InvalidSizeGenerator() {
    	exceptionRule.expect(IllegalArgumentException.class);
    	exceptionRule.expectMessage("Size cannot be zero");
    	Maze0 = new MazeGeneratorSolver(0);
    }
    @Test
    public void timingSmallMazeGenerationSolving() {
    	start = System.currentTimeMillis();
    	Maze4.generateMaze();
    	end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to generate a 4 x 4 maze");
    	
    	start = System.currentTimeMillis();
    	Maze4.bfs(false);
    	end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to solve a 4 x 4 maze BFS with Shortest Path");
    	
    	start = System.currentTimeMillis();
    	Maze4.bfs(true);
    	end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to solve a 4 x 4 maze BFS with all visited");
    	
    	start = System.currentTimeMillis();
    	Maze4.dfs(false);
    	end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to solve a 4 x 4 maze DFS with Shortest Path");
    	
    	start = System.currentTimeMillis();
    	Maze4.dfs(true);
    	end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to solve a 4 x 4 maze DFS with all visited");
    	
    }
    @Test
    public void timingLargeMazeGenerationSolving() {
    	long start = System.currentTimeMillis();
    	Maze100.generateMaze();
    	long end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to generate a 100 x 100 maze");
    	
    	start = System.currentTimeMillis();
    	Maze100.bfs(false);
    	end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to solve a 100 x 100 maze BFS with all visited");
    	
    	start = System.currentTimeMillis();
    	Maze100.bfs(true);
    	end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to solve a 100 x 100 maze BFS with shortest path");
    	
    	start = System.currentTimeMillis();
    	Maze100.dfs(false);
    	end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to solve a 100 x 100 maze DFS with all visited");
    	
    	start = System.currentTimeMillis();
    	Maze100.dfs(true);
    	end = System.currentTimeMillis() - start;
    	System.out.println("Took " + end + " milliseconds to solve a 100 x 100 maze DFS with shortest path");
    	
    }
    @Test
    public void fourByFourHashes(){
        Maze4.generateMaze();
        DFS = Maze4.dfs(true);
        BFS = Maze4.bfs(true);
 
        //converts BFS maze solution into ASCII value
        StringBuilder sb = new StringBuilder(); 
        char[] letters = BFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);
        String BFSAscii = sb.toString();
        
      //converts DFS maze solution into ASCII value
        sb = new StringBuilder(); 
        letters = DFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);

        String DFSAscii = sb.toString();
        assertTrue(DFSAscii.equals(BFSAscii));
    }
    
    @Test
    public void FiveByFiveHashes(){
        Maze5.generateMaze();
        DFS = Maze5.dfs(true);
        BFS = Maze5.bfs(true);
 
        //converts BFS maze solution into ASCII value
        StringBuilder sb = new StringBuilder(); 
        char[] letters = BFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);
        String BFSAscii = sb.toString();
        
      //converts DFS maze solution into ASCII value
        sb = new StringBuilder(); 
        letters = DFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);

        String DFSAscii = sb.toString();
        assertTrue(DFSAscii.equals(BFSAscii));
    }
    
    @Test
    public void SixBySixHashes(){
        Maze6.generateMaze();
        DFS = Maze6.dfs(true);
        BFS = Maze6.bfs(true);
 
        //converts BFS maze solution into ASCII value
        StringBuilder sb = new StringBuilder(); 
        char[] letters = BFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);
        String BFSAscii = sb.toString();
        
      //converts DFS maze solution into ASCII value
        sb = new StringBuilder(); 
        letters = DFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);

        String DFSAscii = sb.toString();
        assertTrue(DFSAscii.equals(BFSAscii));
    }
    
    @Test
    public void SevenBySevenHashes(){
        Maze7.generateMaze();
        DFS = Maze7.dfs(true);
        BFS = Maze7.bfs(true);
 
        //converts BFS maze solution into ASCII value
        StringBuilder sb = new StringBuilder(); 
        char[] letters = BFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);
        String BFSAscii = sb.toString();
        
      //converts DFS maze solution into ASCII value
        sb = new StringBuilder(); 
        letters = DFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);

        String DFSAscii = sb.toString();
        assertTrue(DFSAscii.equals(BFSAscii));
    }
    
    @Test
    public void EightByEightHashes(){
        Maze8.generateMaze();
        DFS = Maze8.dfs(true);
        BFS = Maze8.bfs(true);
 
        //converts BFS maze solution into ASCII value
        StringBuilder sb = new StringBuilder(); 
        char[] letters = BFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);
        String BFSAscii = sb.toString();
        
      //converts DFS maze solution into ASCII value
        sb = new StringBuilder(); 
        letters = DFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);

        String DFSAscii = sb.toString();
        assertTrue(DFSAscii.equals(BFSAscii));
    }
    
    @Test
    public void NineByNineeHashes(){
        Maze9.generateMaze();
        DFS = Maze9.dfs(true);
        BFS = Maze9.bfs(true);
 
        //converts BFS maze solution into ASCII value
        StringBuilder sb = new StringBuilder(); 
        char[] letters = BFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);
        String BFSAscii = sb.toString();
        
      //converts DFS maze solution into ASCII value
        sb = new StringBuilder(); 
        letters = DFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);

        String DFSAscii = sb.toString();
        assertTrue(DFSAscii.equals(BFSAscii));
    }
    
    @Test
    public void TenByTenHashes(){
        Maze10.generateMaze();
        DFS = Maze10.dfs(true);
        BFS = Maze10.bfs(true);
 
        //converts BFS maze solution into ASCII value
        StringBuilder sb = new StringBuilder(); 
        char[] letters = BFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);
        String BFSAscii = sb.toString();
        
      //converts DFS maze solution into ASCII value
        sb = new StringBuilder(); 
        letters = DFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);

        String DFSAscii = sb.toString();
        assertTrue(DFSAscii.equals(BFSAscii));
    }
    
    @Test
    public void TwentyByTwentyHashes(){
        Maze20.generateMaze();
        DFS = Maze20.dfs(true);
        BFS = Maze20.bfs(true);
 
        //converts BFS maze solution into ASCII value
        StringBuilder sb = new StringBuilder(); 
        char[] letters = BFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);
        String BFSAscii = sb.toString();
        
      //converts DFS maze solution into ASCII value
        sb = new StringBuilder(); 
        letters = DFS.toCharArray(); 
        for (char ch : letters) 
        	sb.append((byte) ch);

        String DFSAscii = sb.toString();
        assertTrue(DFSAscii.equals(BFSAscii));
    }
    
    @Test
    public void testMazeFile() throws IOException{
    	File test = testFile.fileOutput();
    	assertNotNull(test);
    	
    	FileReader fr = new FileReader(test);
    	BufferedReader testReader = new BufferedReader(fr);
    	
    	File compare = new File("MazeOutput.txt");
    	
    	FileReader fr2 = new FileReader(compare);
    	BufferedReader compareReader = new BufferedReader(fr2);
    	
    	while(testReader.readLine() != null && compareReader.readLine() != null)
    	{
    		assertTrue(testReader.readLine().equals(compareReader.readLine()));
    		
    		testReader.readLine();
    		compareReader.readLine();
    	}   
    }
}