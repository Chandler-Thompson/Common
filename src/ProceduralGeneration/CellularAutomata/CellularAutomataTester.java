package ProceduralGeneration.CellularAutomata;
import java.awt.Color;
import java.util.ArrayList;

import ProceduralGeneration.ProceduralMap;
import ProceduralGeneration.ProceduralTester;


public class CellularAutomataTester extends ProceduralTester<Integer>{
	
	/*
	 * 
	 * Generation Notes:
	 *
	 * Having a birthThreshold higher than an isolationThreshold will cause the map to slowly degrade, and sections to "shrink" away from each other
	 * 
	 * Having birthThreshold == isolationThreshold keeps the same number of cells (roughly) but makes them pack together
	 * 
	 * Having a birthThreshold lower than an isolationThreshold will cause erratic behavior (suggested not to be used...)
	 * 
	 * Having lower numbers across all three variables causes little to no change
	 * 
	 * Having higher numbers across all three variables causes the map to quickly deteriorate
	 * 
	 * Having lower birthThreshold and isolationThreshold values but higher overpopulationThreshold values will cause large "rooms" to be created and slowly connect over time
	 * 
	 * Overall the overpopulationThreshold variable determines how much cells clump together to make rooms
	 * 
	 * Overall the isolationThreshold variable determines how much out-lying cells are killed/cleaned up. Also restricts size of passageways between rooms.
	 * 
	 * Overall the birthThreshold variable determines how much the rooms expand (higher birthThreshold) and are connected by passages (lower birthThreshold)
	 * 
	 * The right combination of birthThreshold and isolationThreshold will make various sized passage-ways between rooms. (having birthThreshold and isolationThreshold with a absolute difference of 1 does the trick generally)
	 * 
	 */

	ArrayList<Cell> cellMap;
	
	public CellularAutomataTester(ProceduralMap<Integer> map){
		super(map);
		cellMap = new ArrayList<>();
		
		createCellConnections();
		
	}
	
	//SETUP CELL CONNECTIONS
	//Loop through the cave looking for living cells
	//if a cell is living add it to the cell map and then
	//connect it to the cell to the left of it and the cell above it, assuming they are alive
	//bottom and right don't need to be done as the next cell will take care of adding this current cell
	private void createCellConnections(){
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map.width; x++){
				cellMap.add(new Cell(x, y));//add cell to the cell map
				
				if(map.get(x, y) == 1){//if the current cell is living
					try{
						if(map.get(x-1, y) == 1)//If cell to the left of this one is also living
							cellMap.get(cellMap.size()-1).connectNodes(cellMap.get(cellMap.size()-2));//connect this node with the previous cell
					}catch(IndexOutOfBoundsException e){/*IndexOutOfBoundsException will occur on entire left-most row*/}
					try{
						if(map.get(x, y-1) == 1){//If cell above this one is also living
							//										get cell		at end of list - width of map (cell above current one in 1D array)
							cellMap.get(cellMap.size()-1).connectNodes(cellMap.get(cellMap.size()-1-map.width));//connect this node with the one above it
						}
					}catch(IndexOutOfBoundsException e){/*IndexOutOfBoundsException will occur on entire top row*/}
				}
			}
		}
	}
	
	//need CaveViewer to determine which cells are already visited and to highlight new ones
	//x and y are the coordinates of the cell that the flood fill test should start from.
	public void floodFillTest(CellularAutomataViewer viewer, int x, int y){
		
		Cell startingCell = new Cell(0, 0);
		
		//find specified starting cell
		for(Cell cell : cellMap){
			if(cell.getX() == x && cell.getY() == y){
				startingCell = cell;
				break;
			}
		}
		
		explore(viewer, startingCell);
		
	}
	
	//recurse out from the starting cell until all connecting cells are highlighted
	private void explore(CellularAutomataViewer viewer, Cell startingCell){
		
		//return from this cell if it is red (has already been visited)
		if(viewer.getCellColor(startingCell.getX(), startingCell.getY()) == Color.RED)
			return;
		
		//highlight the cell
		viewer.highlightCell(startingCell.getX(), startingCell.getY());
		
		//loop through connecting nodes and highlight them as well
		for(int i = 0; i < startingCell.getConnectingNodes().size(); i++)
			explore(viewer, startingCell.getConnectingNodes().get(i));
		
	}
	
}
