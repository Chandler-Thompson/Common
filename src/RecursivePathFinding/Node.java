package RecursivePathFinding;
import java.util.ArrayList;


public class Node {

	private String name;
	private boolean isStart;
	private boolean isFinish;
	private ArrayList<Node> connectingPoints;
	
	public Node(String name){
		this.name = name;
		connectingPoints = new ArrayList<>();
	}
	
	public void setStart(boolean start){
		isStart = start;
	}
	
	public void setFinish(boolean finish){
		isFinish = finish;
	}
	
	public boolean isStart(){
		return isStart;
	}
	
	public boolean isFinish(){
		return isFinish;
	}
	
	public void connectNodes(Node node){
		connectingPoints.add(node);
		node.getConnectingNodes().add(this);
	}
	
	public boolean isInPath(ArrayList<Node> path){
		boolean inPath = false;
		
		for(Node point : path)
			inPath = (!inPath) ? point.getName().equals(name):inPath;
		
		return inPath;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Node> getConnectingNodes(){
		return connectingPoints;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
}
