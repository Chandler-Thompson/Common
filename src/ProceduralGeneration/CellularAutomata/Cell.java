package ProceduralGeneration.CellularAutomata;
import java.util.ArrayList;


public class Cell {

	private int x;
	private int y;
	private boolean isStart;
	private boolean isFinish;
	private ArrayList<Cell> connectingPoints;
	
	public Cell(int x, int y){
		this.x = x;
		this.y = y;
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
	
	public void connectNodes(Cell cell){
		connectingPoints.add(cell);
		cell.getConnectingNodes().add(this);
	}
	
	public boolean isInPath(ArrayList<Cell> path){
		boolean inPath = false;
		
		for(Cell cell : path)
			inPath = (!inPath) ? (cell.getX() == x && cell.getY() == y):inPath;
		
		return inPath;
	}
	
	public ArrayList<Cell> getConnectingNodes(){
		return connectingPoints;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	@Override
	public String toString(){
		return x+","+y;
	}
	
}
