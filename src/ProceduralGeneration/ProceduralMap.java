package ProceduralGeneration;

public class ProceduralMap<T> {

	private Object[][] map;
	
	public final int width;
	public final int length;
	
	public ProceduralMap(int width, int length){
		map = new Object[length][width];
		
		this.width = width;
		this.length = length;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int x, int y){
		return (T) map[y][x];
	}
	
	public void set(int x, int y, T value){
		map[y][x] = value;
	}
	
	
}
