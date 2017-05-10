package PlanetMap;

public class Planet {

	private final String name;
	private final int xCoord;
	private final int yCoord;
	private final int equator_diameter;
	private final int meridian_diameter;
	
	public Planet(String name, int xCoord, int yCoord, int equator_diameter, int meridian_diameter){
		
		this.name = name;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.equator_diameter = equator_diameter;
		this.meridian_diameter = meridian_diameter;
		
	}
	
	public String getName(){
		return name;
	}
	
	public int getXCoord(){
		return xCoord;
	}
	
	public int getYCoord(){
		return yCoord;
	}
	
	public int getEquatorDiameter(){
		return equator_diameter;
	}
	
	public int getMeridianDiameter(){
		return meridian_diameter;
	}
	
}
