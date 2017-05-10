package common;

import java.util.Scanner;

public class VectorManipulation {

	public static class Vector {

		public final int X;
		public final int Y;
		
		public final double MAGNITUDE;
		
		public Vector(int x, int y, double magnitude){
			X = x;
			Y = y;
			MAGNITUDE = magnitude;
		}
		
		public String toString(){
			return "("+X+","+Y+","+MAGNITUDE+")";
		}
		
	}
	
	public static Vector createVector(int x, int y, double mag){
		return new Vector(x, y, mag);
	}
	
	public static Vector addVectors(Vector v1, Vector v2){
		return new Vector(v1.X + v2.X, v1.Y + v2.Y, v1.MAGNITUDE + v2.MAGNITUDE);
	}
	
	public static Vector addVectors(int x1, int y1, double mag1, int x2, int y2, double mag2){
		return new Vector(x1+x2, y1+y2, mag1+mag2);
	}
	
	public static Vector subVectors(Vector v1, Vector v2){
		return new Vector(v1.X - v2.X, v1.Y - v2.Y, v1.MAGNITUDE - v2.MAGNITUDE);
	}
	
	public static Vector subVectors(int x1, int y1, double mag1, int x2, int y2, double mag2){
		return new Vector(x1-x2, y1-y2, mag1-mag2);
	}
	
	public static Vector multVectors(Vector v1, Vector v2){
		return new Vector(v1.X * v2.X, v1.Y * v2.Y, v1.MAGNITUDE * v2.MAGNITUDE);
	}
	
	public static Vector multVectors(int x1, int y1, double mag1, int x2, int y2, double mag2){
		return new Vector(x1*x2, y1*y2, mag1*mag2);
	}
	
	public static Vector scaleVector(Vector v, double scalar){
		return new Vector((int)(v.X*scalar), (int)(v.Y*scalar), v.MAGNITUDE*scalar);
	}
	
	public static Vector scaleVector(int x, int y, double mag, double scalar){
		return new Vector((int)(x*scalar), (int)(y*scalar), mag*scalar);
	}
	
	public static void main(String[] args){
		
		while(true){
			Vector outputVector = null;
			
			switch(menu()){
				case 1:
					outputVector = addVectors(retrieveX(), retrieveY(), retrieveMagnitude(), retrieveX(), retrieveY(), retrieveMagnitude());
					break;
				case 2:
					outputVector = subVectors(retrieveX(), retrieveY(), retrieveMagnitude(), retrieveX(), retrieveY(), retrieveMagnitude());
					break;
				case 3:
					outputVector = multVectors(retrieveX(), retrieveY(), retrieveMagnitude(), retrieveX(), retrieveY(), retrieveMagnitude());
					break;
				case 4:
					outputVector = scaleVector(retrieveX(), retrieveY(), retrieveMagnitude(), retrieveScalar());
					break;
				case 5:
					System.exit(0);
					break;
				default:
					outputVector = createVector(0, 0, 0.0);
			}
			
			System.out.println(outputVector.toString());
			
		}
		
	}
	
	private static int menu(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("What would you like to do?");
		System.out.println("1 - Add");
		System.out.println("2 - Subtract");
		System.out.println("3 - Multiply");
		System.out.println("4 - Scale");
		System.out.println("5 - Exit");
		
		int choice = input.nextInt();
		input.close();
		
		return choice;
	}
	
	private static int retrieveX(){
		return retrieveInt("X");
	}
	
	private static int retrieveY(){
		return retrieveInt("Y");
	}
	
	private static double retrieveMagnitude(){
		return retrieveDouble("Magnitude");
	}
	
	private static double retrieveScalar(){
		return retrieveDouble("Scalar");
	}
	
	private static double retrieveDouble(String string){
		Scanner input = new Scanner(System.in);
		System.out.print(string+": ");
		double choice = input.nextDouble();
		input.close();
		return choice;
	}
	
	private static int retrieveInt(String string){
		Scanner input = new Scanner(System.in);
		System.out.print(string+": ");
		int choice = input.nextInt();
		input.close();
		return choice;
	}
	
}
