package aiProject;

import java.util.Stack;

public class BFSearch {

    Position endPos;

    public BFSearch(Position endPos) {
        this.endPos = endPos;
    }

    public boolean search(Environment env) {
        return searchAlgorithm(env);
    }

    private boolean searchAlgorithm(Environment env) {

        Stack<Position> initialSolutionPath = new Stack<>();
        //Add initial state to the queue
        //Because when we start the while, we get a node from the queue.
        env.queue.add(new SearchNode(env.copyMap(env.map), initialSolutionPath, env.agentPos));

        //Here we created a 2D direction array to determine the directions.
        int [][] directions={{-1,0},{1,0},{0,1},{0,-1}};

        while (!env.queue.isEmpty()) {
            //Here we get the search node from the queue. Then we save the objects in the node.
            SearchNode searchState = env.queue.poll(); 
            int[][] queueMap = searchState.map;
            Stack<Position> queueSolutionPath = searchState.solutionPath;
            Position current = searchState.current;
            //we search all direction in for loop.
            for (int i = 0; i < 4; i++) {
                Position newPos = new Position(current.x, current.y);
                //First, it questions its movement in a certain direction. If it can move, it moves until it comes across a wall within the while.
                if (canMove(newPos, env, queueMap, directions[i][0], directions[i][1])) {
                    Stack<Position> newSolutionPath = (Stack<Position>) queueSolutionPath.clone(); 
                    int[][] newMap = env.copyMap(queueMap); 
                    while (canMove(newPos, env, newMap, directions[i][0], directions[i][1])) {
                        newPos.x += directions[i][0];
                        newPos.y += directions[i][1];
                        newMap[newPos.x][newPos.y] = 1; 
                        newSolutionPath.push(new Position(newPos.x, newPos.y));
                        //Here this method questioned whether it has been reached exit during the moving.
                        if (isFinished(newPos, env, newMap, newSolutionPath)) {
                            return true;
                        }
                    }
                    //At the end of the loop, we perform a push operation into the stack to show that the step has come to an end and add the final state to the queue.
                    newSolutionPath.push(new Position(-1, -1)); 
                    env.queue.add(new SearchNode(newMap, newSolutionPath, new Position(newPos.x, newPos.y)));
                }
            }
        }
        
        return false;
    }
    //Method to check if the agent reached the exit
    private boolean isFinished(Position pos, Environment env, int[][] map, Stack<Position> newVisitedMap) {
        if (pos.x == endPos.x && pos.y == endPos.y) {
            newVisitedMap.push(new Position(-1, -1));
            env.solutionPath = newVisitedMap;
            env.queue.add(new SearchNode(map, newVisitedMap, new Position(pos.x, pos.y)));
            return true;
        }
        return false;

    }

    //Method to check if the agent can move in a certain direction
    private boolean canMove(Position pos, Environment env, int[][] map, int dx, int dy) {
        int newX = pos.x + dx;
        int newY = pos.y + dy;
        return (newX >= 0 && newX < env.N - 1 && newY >= 0 && newY < env.N - 1 && map[newX][newY] != 1
                && map[newX][newY] != 2);
    }
}
