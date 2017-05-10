package PlanetMap;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class NavigationPanel extends JPanel{
	private static final long serialVersionUID = -1666966742063656341L;

	public NavigationPanel(){}
	
	public void paintPlanets(Graphics g, ArrayList<Planet> planets){
		
		for(int i = 0; i < planets.size(); i++){
			
			Planet planet = planets.get(i);
			
			g.fillOval(planet.getXCoord(), planet.getYCoord(), planet.getEquatorDiameter(), planet.getMeridianDiameter());
			
		}
		
	}
	
}
