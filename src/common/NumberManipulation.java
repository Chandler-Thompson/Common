package common;

public class NumberManipulation {

	public static boolean isPowerOfTwo(double depth){
		if(depth <= 1)
			return (depth == 1) ? true:false;
		else
			return isPowerOfTwo(depth/2);
	}

	public static int convert2Dto1DIndex(int x, int y, int width, int height){
		return (x/width)*(y%height);
	}
	
}
