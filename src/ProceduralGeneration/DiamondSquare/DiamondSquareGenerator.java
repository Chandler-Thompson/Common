package ProceduralGeneration.DiamondSquare;

import java.awt.Color;

import ProceduralGeneration.ProceduralMap;
import ProceduralGeneration.ProceduralMapGenerator;

public class DiamondSquareGenerator extends ProceduralMapGenerator<Color>{

	/*
	 * 2^n + 1
	 * -------
	 * n = 1 -> 3x3 grid
	 * n = 2 -> 5x5 grid
	 * n = 3 -> 9x9 grid
	 * n = 4 -> 17x17 grid
	 * n = 5 -> 33x33 grid
	 */
	
	private int n;//determines size of square grid with formula (2^n)+1
	private int sideLength;//its a square, only need one variable for side length
	//private float colorDeviation;
	
	private ProceduralMap<Color> map;
	
	/**
	 * 
	 * Creates a generator for a diamond-square procedural algorithm in a map of width*length size.
	 * 
	 * @param width - The width of the map (must be an odd number).
	 * @param length - The length of the map (must be an odd number).
	 */
	public DiamondSquareGenerator(int n){
		this.n = n;
		sideLength = DiamondSquareGenerator.sideLengthFormula(n);
		//colorDeviation = 0.75f;
		
		map = new ProceduralMap<Color>(sideLength, sideLength);
	}
	
	/**
	 * Generates map with completely random colors.
	 */
	@Override
	public void generateMap(){
		
		//initialize everything to black
		for(int y = 0; y < map.length; y++){
			for(int x = 0; x < map.width; x++){
				map.set(x, y, new Color(0.0f, 0.0f, 0.0f));
			}
		}
		
		//set the top left corner
		float r = (float) (Math.random()*1.0f);
		float g = (float) (Math.random()*1.0f);
		float b = (float) (Math.random()*1.0f);
		map.set(0, 0, new Color(r, g, b));
		
		//set the top right corner
		r = (float) (Math.random()*1.0f);
		g = (float) (Math.random()*1.0f);
		b = (float) (Math.random()*1.0f);
		map.set(sideLength-1, 0, new Color(r, g, b));
		
		//set the bottom left corner
		r = (float) (Math.random()*1.0f);
		g = (float) (Math.random()*1.0f);
		b = (float) (Math.random()*1.0f);
		map.set(0, sideLength-1, new Color(r, g, b));
		
		//set the bottom right corner
		r = (float) (Math.random()*1.0f);
		g = (float) (Math.random()*1.0f);
		b = (float) (Math.random()*1.0f);
		map.set(sideLength-1, sideLength-1, new Color(r, g, b));
	}
	
	/**
	 * 
	 * Generates map starting with a base color, and deviating out from that color.
	 * 
	 * @param baseColor - The base color to start from.
	 */
	public void generateMap(Color baseColor){
		
	}
	
	/**
	 * 
	 * Generates map starting with a base color, and only deviating from that color by a specified amount.
	 * 
	 * @param baseColor - The base color to start from.
	 * @param colorDeviation - The amount that the map is allowed to deviate from the initial color.
	 */
	public void generateMap(Color baseColor, float colorDeviation){
		
	}
	
	/**
	 * Iterate the generated map once through the algorithm.
	 */
	public void step(){
		stepHelper(n, 0);
	}
	
	private void stepHelper(int n, int depth){
		
		for(int a = 0; a < Math.pow(4, depth); a++){
			int sideLength = sideLengthFormula(n)/2;
			int midpointDist = midPointFormula(n);
			
			int topLeftX = a * ((depth==0)?0:(int)(sideLength/depth));
			int topLeftY = (sideLength-1) * ((depth==0)?0:(int)(a/(depth*2)));
			
			int centerX = topLeftX + midpointDist;
			int centerY = topLeftY + midpointDist;
			
			//diamond step
			map.set(centerX, centerY, getDiamondColor(n, centerX, centerY));
			
			//square step (sides of diamond)
			map.set(centerX, centerY - sideLength, getSquareColor(n, centerX, centerY - sideLength));//top
			map.set(centerX + sideLength, centerY, getSquareColor(n, centerX + sideLength, centerY));//right
			map.set(centerX - sideLength, centerY, getSquareColor(n, centerX - sideLength, centerY));//left
			map.set(centerX, centerY + sideLength, getSquareColor(n, centerX, centerY + sideLength));//down
			
		}
		stepHelper(n-1, depth+1);
	}
	
	/**
	 * 
	 * Gets the colors of the four corners (in relation to the given center)
	 * and averages them together with a minor random factor.
	 * 
	 * @return the averages of the colors of the four corners of the square.
	 */
	private Color getDiamondColor(int n, int centerX, int centerY){
		
		int sideLength = midPointFormula(n);
		
		System.out.println("|DiamondSquareGenerator| (getDiamondColor):: centerX: "+centerX);
		System.out.println("|DiamondSquareGenerator| (getDiamondColor):: centerY: "+centerY);

		
		Color topLeft = map.get(centerX - sideLength, centerY - sideLength);
		Color topRight = map.get(centerX + sideLength, centerY - sideLength);
		Color botLeft = map.get(centerX - sideLength, centerY + sideLength);
		Color botRight = map.get(centerX + sideLength, centerY + sideLength);
		
		int r = (topLeft.getRed()+topRight.getRed()+botLeft.getRed()+botRight.getRed())/4;
		int g = (topLeft.getGreen()+topRight.getGreen()+botLeft.getGreen()+botRight.getGreen())/4;
		int b = (topLeft.getBlue()+topRight.getBlue()+botLeft.getBlue()+botRight.getBlue())/4;
		
		return new Color(r, g, b);
	}
	
	/**
	 * 
	 * Gets the colors of the four sides (in relation to the given center)
	 * and averages them together with a minor random factor.
	 * 
	 * @return the averages of the colors of the four sides of the square.
	 */
	private Color getSquareColor(int n, int x, int y){
		
		Color top;
		Color right;
		Color left;
		Color down;
		
		int sideLength = midPointFormula(n);
		
		try{
			top = map.get(x, y - sideLength);
		}catch(IndexOutOfBoundsException iobe){//in the case that the diamond center point is on the top row
			top = map.get(x, map.length-1);
		}
		try{
			right = map.get(x + sideLength, y);
		}catch(IndexOutOfBoundsException e){//in the case that the diamond center point is on the right col
			right = map.get(0, y);
		}
		try{
			left = map.get(x - sideLength, y);
		}catch(IndexOutOfBoundsException e){//in the case that the diamond center point is on the left col
			left = map.get(map.width-1, y);
		}
		try{
			down = map.get(x, y + sideLength);
		}catch(IndexOutOfBoundsException e){//in the case that the diamond center point is on the bottom row
			down = map.get(x, 0);
		}
		
		int r = (top.getRed()+right.getRed()+left.getRed()+down.getRed())/4;
		int g = (top.getGreen()+right.getGreen()+left.getGreen()+down.getGreen())/4;
		int b = (top.getBlue()+right.getBlue()+left.getBlue()+down.getBlue())/4;
		
		return new Color (r, g, b);
		
	}
	
	@Override
	public ProceduralMap<Color> getMap(){
		return map;
	}
	
	/**
	 * 
	 * Calculates the distance to the mid point of the grid at the
	 * given level n.
	 * 
	 * @param n - The determining factor for grid size.
	 * @return The distance to the side mid point of the grid.
	 */
	public static int midPointFormula(int n){
		return (int) (Math.pow(2, n)+1-n-((n%2==0)?1:2));
	}
	
	/**
	 * 
	 * Calculates the sideLength of the grid given n.
	 * 
	 * @param n - The determining factor for grid size.
	 * @return The sideLength of the grid, given n.
	 */
	public static int sideLengthFormula(int n){
		return (int) (Math.pow(2, n)+1);
	}
	
}
