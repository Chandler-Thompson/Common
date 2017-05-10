package PlanetMap;

import java.util.ArrayList;

public class PlanetManager {

	final static int numPlanets = 10;
	
	final static int mapWidth = 1000;
	final static int mapLength = 1000;
	
	final static int maxPlanetSize = 10;
	final static int minPlanetSize = 1;
	
	static ArrayList<Planet> planets = new ArrayList<>();
	
	public static void main(String[] args){
		PlanetManager pManager = new PlanetManager();
		pManager.createPlanets();
	}
	
	private void createPlanets(){
		
		for(int i = 0; i < numPlanets; i++){
			
			String name = "Planet " + i;
			
			int xCoord = (int) (Math.random()*mapWidth);
			int yCoord = (int) (Math.random()*mapLength);
			
			int equatorDiameter = (int) ((Math.random()*maxPlanetSize)+minPlanetSize);
			int meridianDiameter = (int) ((Math.random()*maxPlanetSize)+minPlanetSize);
			
			planets.add(new Planet(name, xCoord, yCoord, equatorDiameter, meridianDiameter));
			
		}
		
	}
	
}
