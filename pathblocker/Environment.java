package aiProject;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

//We use it to store x-y values ​​together on the map within the position class.
class Position {
    int x, y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Environment {
    ImageCreator imageCreator = new ImageCreator();
    Position agentPos = new Position(0, 0);
    // Map size
    final int N = 16;
    //Search algorithm for solving the level
    BFSearch agent;
    int[][] map = new int[N][N];
    //We use stack to hold the solution path
    Stack<Position> solutionPath = new Stack<Position>();
    //We use Queue for BreathFirstSearch
    Queue<SearchNode> queue = new LinkedList<SearchNode>();
    
    
    //Here we are converting the ready map we received via txt into a 2D array.
    Environment(String level) {
        try {
            File file = new File(level + ".txt");
            Scanner scanner = new Scanner(file);
            int counter = 0;
            //We read each line
            while (scanner.hasNextLine()) {
                String newLine = scanner.nextLine();
                if (newLine.trim().isEmpty()) {
                    continue;
                }
                String[] line = newLine.trim().split("");
                for (int i = 0; i < line.length; i++) {
                    map[counter][i] = Integer.parseInt(line[i]);
                    if (map[counter][i] == 2) {
                        agentPos = new Position(counter, i);
                    } else if (map[counter][i] == 3) {
                        this.agent = new BFSearch(new Position(counter, i));
                        
                    }
                }
                counter++;
            }
        } catch (Exception e) {
            System.out.println("Your file not found");
        }
    }

    public int[][] copyMap(int[][] map) {
        int[][] newMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, map[i].length);
        }
        return newMap;
    }
    // Method to save the solution path
    public void saveImage(String filename) {
        imageCreator.saveMapAsImage(filename, map, solutionPath,agent.endPos);
    }
}