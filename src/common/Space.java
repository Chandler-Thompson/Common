package common;

public class Space {

	private int x;
	private int y;
	
	private boolean isOccupied;
	private boolean isObstacle;
	
	public Space(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setOccupied(boolean isOccupied){
		this.isOccupied = isOccupied;
	}
	
	public void setObstacle(boolean isObstacle){
		this.isObstacle = isObstacle;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isOccupied(){
		return isOccupied;
	}
	
	public boolean isObstacle(){
		return isObstacle;
	}
	
}
