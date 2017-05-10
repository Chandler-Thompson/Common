package RecursivePathFinding;
import java.util.ArrayList;

import common.TextManipulation;

public class Main {
	
	int steps = 0;
	int numInterimNodes = 8;
	boolean finishFound = false;
	
	public static void main(String[] args){
		Main main = new Main();
		main.start();
	}
	
	public void start(){
		
		ArrayList<Node> nodeMap = new ArrayList<>();
		
		//create nodes
		Node start = new Node("Start");
		start.setStart(true);
		
		nodeMap.add(start);
		
		for(int i = 0; i < numInterimNodes; i++)
			nodeMap.add(new Node("" + TextManipulation.upperAlphabet[i]));
		
		Node finish = new Node("Finish");
		finish.setFinish(true);
		
		nodeMap.add(finish);
		
		//connect nodes (manually sadly...)
		nodeMap.get(0).connectNodes(nodeMap.get(1));//start to A
		nodeMap.get(0).connectNodes(nodeMap.get(6));//start to F
		nodeMap.get(1).connectNodes(nodeMap.get(2));//A to B
		nodeMap.get(1).connectNodes(nodeMap.get(5));//A to E
		nodeMap.get(2).connectNodes(nodeMap.get(3));//B to C
		nodeMap.get(3).connectNodes(nodeMap.get(4));//C to D
		nodeMap.get(3).connectNodes(nodeMap.get(5));//C to E
		nodeMap.get(4).connectNodes(nodeMap.get(5));//D to E
		nodeMap.get(4).connectNodes(nodeMap.get(8));//D to H
		nodeMap.get(4).connectNodes(nodeMap.get(9));//D to finish
		nodeMap.get(5).connectNodes(nodeMap.get(6));//E to F
		nodeMap.get(6).connectNodes(nodeMap.get(7));//F to G
		nodeMap.get(7).connectNodes(nodeMap.get(8));//G to H
		
		//begin path finding...
		ArrayList<Node> path = new ArrayList<>();
		path.add(nodeMap.get(0));//add start node
		
		try {
			startShallow(nodeMap, path);
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Final Path: " + path + "\nTook " + steps + " steps");
		
	}
	
	public ArrayList<Node> startShallow(ArrayList<Node> nodeMap, ArrayList<Node> path) throws PathNotFoundException{
		steps = 0;
		finishFound = false;
		
		return shallowRecurse(path, nodeMap.size());
	}
	
	private ArrayList<Node> shallowRecurse(ArrayList<Node> path, int maxSteps){

		steps++;
		ArrayList<Node> connectingNodes = path.get(path.size()-1).getConnectingNodes();
		
		if(maxSteps == 0){
			for(int i = 0; i < connectingNodes.size(); i++){
				if(connectingNodes.get(i).isFinish()){
					path.add(connectingNodes.get(i));
					finishFound = true;
					return path;
				}
			}
			return path;
		}
		
		for(int i = 0; i < maxSteps; i++){
			for(int a = 0; a < connectingNodes.size(); a++){
				if(!connectingNodes.get(a).isInPath(path)){
					path.add(connectingNodes.get(a));
					path = shallowRecurse(path, i);
					if(finishFound)
						return path;
					else
						path.remove(path.size()-1);
				}
			}
		}
		
		return path;
	}
	
}
