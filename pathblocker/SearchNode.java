
package aiProject;

import java.util.Stack;
// Node class representing a search state in BFS
public class SearchNode {
    int[][]map;
    Stack<Position> solutionPath = new Stack<Position>();
    Position current;
    //We use constructor to initialize the search node
    SearchNode(int[][]map,Stack<Position> solutionPath,Position current){
        this.map=map;
        this.solutionPath=solutionPath;
        this.current=current;
    }
}
