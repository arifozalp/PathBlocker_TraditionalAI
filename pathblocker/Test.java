package aiProject;

/*
1) Why you prefer the search algorithm you choose?
The search algorithm we use is BFS. The purpose of using this is that the BFS algorithm finds the path with the least cost.
2) Can you achieve the optimal result? Why? Why not?
We may not always get the best result. The BFS algorithm aims to find the path with the least cost. While trying to find the solution, it checks all the branches and stores the branches it checks in memory, so it performs this process in a longer time. However, if the best result means finding the path with the least cost, BFS always finds the best result.
3) How you achieved efficiency for keeping the states?
In the BFS algorithm, we kept the path to the result with the queue. Since the agent's location and the end location cannot overlap in the maps sent to the algorithm, the first sent location is removed from the queue, and then all the movement combinations it can make are added to the queue. In this way, we increase the program efficiency of the queue by performing addition and removal operations.
4) If you prefer to use DFS (tree version) then do you need to avoid cycles?
The tree version of DFS visits each node only once, meaning there is no possibility of returning to the same node via different paths, so there is no looping. DFS scans one branch at a time, eliminating nodes if the node it is searching for is the wrong solution, and continues searching for other nodes without looping.
5) What will be the path-cost for this problem?
In this problem, we calculated the path cost as the number of steps. We increased the number of steps by one for each turn. Thus, we made the path cost suitable for the game by counting the turns rather than calculating it step by step.
*/
public class Test {
     public static void main(String[] args) {
        long startTime = System.nanoTime();
        for (int level = 1; level <= 10; level++) {
            // Sends the txt file for each level.
            Environment environment = new Environment("level" + String.format("%02d", level));
            if (environment.agent.search(environment)) {
                // Save the map image if the level is solved
                environment.saveImage("level" + String.format("%02d", level)); 
                System.out.println("Level "+ level+ " solved!");
            } else {
                System.out.println("Level " + level + " not solved!");
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Execution time : " + (endTime - startTime) / 1e9 + " seconds");
    }

}