package formulas.physics;

public class SolidElasticity {

	//PE = Potential Energy
	
	public static double strain(double initial, double delta){
		return delta/initial;
	}
	
	public static double stress(double force, double area){
		return force/area;
	}
	
	//roughly accurate for only small values of phi
	public static double shearingStrain(double a, double b, double c){
		return (a*b)/(a*c);
	}
	
	public static double shearModulus(double force, double area, double phi){
		return force/(area*phi);
	}
	
	public static double bulkModulus(double force, double area, double initial, double delta){
		return modulusOfElasticity(force, area, initial, delta);
	}
	
	public static double modulusOfElasticity(double force, double area, double initial, double delta){
		return (force*initial)/(area*delta);
	}
	
	public static double stretchingForce(double elongation, double forceConstant){
		return elongation*forceConstant;
	}
	
	public static double springElasticPE(double elongation, double forceConstant){
		return 0.5*elongation*Math.pow(forceConstant, 2);
	}
	
}
