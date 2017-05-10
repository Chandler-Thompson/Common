package ProceduralGeneration.CellularAutomata;

import ProceduralGeneration.ProceduralMap;
import ProceduralGeneration.ProceduralMapGenerator;

/**
 * 
 * This is a cellular automata cave generator.
 * It generates caves by using rules similar to John Conway's Game of Life.
 * 
 * @author Chandler
 * 
 */

public class CellularAutomataGenerator extends ProceduralMapGenerator<Integer>{
	
	//0 = dead
	//1 = alive
	private ProceduralMap<Integer> map;
	private float lifeChance;
	private int birthThreshold;
	private int isolationThreshold;
	private int overpopulationThreshold;
	
	private int numCells;
	private int numDeadCells;
	private int numLivingCells;
	
	public CellularAutomataGenerator(){
		lifeChance = 0.5f;//initialize to 50%
		birthThreshold = 0;
		isolationThreshold = 0;
		overpopulationThreshold = 0;
	}
	
	public void setRules(int birthThreshold, int isolationThreshold, int overpopulationThreshold){
		this.birthThreshold = birthThreshold;
		this.isolationThreshold = isolationThreshold;
		this.overpopulationThreshold = overpopulationThreshold;
	}
	
	public void setLifeChance(float lifeChance){
		this.lifeChance = lifeChance;
	}
	
	@Override
	public void generateMap(int width, int height){
		generateMap(this.lifeChance, width, height);
	}
	
	@SuppressWarnings("unused")
	public void generateMap(float lifeChance, int width, int height){
		this.lifeChance = lifeChance;
		map = new ProceduralMap<Integer>(width, height);
		
		//populate map
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map.width; x++){
				map.set(x, y, (((Math.random()) >= this.lifeChance) ? 0:1));//if random number is greater than or equal to lifeChance we will say cell is dead (lower life chance improves chance random number will be above it)
				int var = (map.get(x, y) == 1) ? numLivingCells++:numDeadCells++;//probably a stupid way to do this... (var is never actually used as it has no real purpose other than to keep from a syntax error occurring)
				numLivingCells++;
			}
		}
	}
	
	public void create(ProceduralMap<Integer> map){
		this.map = map;
	}
	
	@Override
	public void step(){
		//create tempMap
		int[][] tempMap = new int[map.length][map.width];
		
		//apply rules to map (can't change map directly as newly placed cells would affect other newly placed cells
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map.width; x++){
				if(map.get(x, y) == 0){//if dead cell is surrounded by exactly [birthThreshold] living ones, it revives
					tempMap[y][x] = (checkCells(x, y) == birthThreshold) ? 1:0;
					numDeadCells--;
					numLivingCells++;
				}else if(map.get(x, y) == 1){//if alive cell is surrounded by less than [isolationThreshold], or more than [overpopulationThreshold] cells, it dies.
					tempMap[y][x] = (checkCells(x, y) < isolationThreshold || checkCells(x, y) > overpopulationThreshold) ? 0:1;
					numDeadCells++;
					numLivingCells--;
				}
			}
		}
		
		//update map with new placements of cells
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map.width; x++){
				map.set(x, y, tempMap[y][x]);
			}
		}
		
	}
	
	//checks map int[][] to see if cell is surrounded by exactly three living cells
	private int checkCells(int coordX, int coordY){
		int cellCount = 0;
		
		for(int y = coordY-1; y <= coordY+1; y++){
			for(int x = coordX-1; x <= coordX+1; x++)
				if(!(y == coordY && x == coordX)){
					try{
						cellCount += (map.get(x, y) == 1) ? 1:0;
					}catch(ArrayIndexOutOfBoundsException ae){}
				}
		}
			
		return cellCount;
		
	}
	
	//output the current state of the map
	public void output(){
		
		String output = "  ";
		
		for(int i = 0; i < map.length; i++){
			output += " " + i;
		}
		
		output += "\n";
		
		for(int y = 0; y < map.length; y++){
			output += (y < 10) ? ("0" + y + "|"):(y + "|");
			for(int x = 0; x < map.width; x++){
				output += ((map.get(x, y) == 1) ? "0": " ") + "|";
			}
			output += "\n";
		}
		
		System.out.println(output);
		
	}
	
	@Override
	public ProceduralMap<Integer> getMap(){
		return map;
	}
	
	public int getNumCells(){
		return numCells;
	}
	
	public int getNumDeadCells(){
		return numDeadCells;
	}
	
	public int getNumLivingCells(){
		return numLivingCells;
	}
	
}
