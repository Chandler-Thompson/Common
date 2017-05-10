package ProceduralGeneration;

public abstract class ProceduralMapGenerator<T> {
	
	public ProceduralMapGenerator(){}
	
	/**
	 * Create the initial state of the map.
	 */
	public void generateMap(){}
	
	/**
	 *	Create the initial state of the map.
	 * @param width - The width of the map.
	 * @param length - The length of the map.
	 */
	public void generateMap(int width, int length) {}
	
	/**
	 * Go through a single iteration of the algorithm.
	 */
	public void step(){}
	
	/**
	 * Go through 'steps' iterations of the algorithm.
	 * @param steps - The number of iterations to step through the algorithm.
	 */
	public void run(int steps){
		for(int i = 0; i < steps; i++)
			step();
	}
	
	/**
	 * Go through 'steps' iterations of the algorithm. Outputting the state of the map at each step.
	 * @param steps - The number of iterations to step through the algorithm.
	 */
	public void runWithOutput(int steps){
		output();
		for(int i = 0; i < steps; i++){
			step();
			output();
		}
	}
	
	/**
	 * Outputs the current map state to the console.
	 */
	public void output(){}
	
	/**
	 * Get the map that is being edited by the procedural algorithm.
	 * @return - The ProceduralMap object being modified by the algorithm.
	 */
	public ProceduralMap<T> getMap(){
		System.err.println("Warning! You are using the default getMap() in ProceduralMapGenerator. Returning null.");
		return null;
	}
	
}
