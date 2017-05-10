package common;

import java.util.ArrayList;

public class ArrayManipulation<E> {

	
	
	public ArrayList<ArrayList<E>> convert1DTo2D(ArrayList<E> list, int width){
		ArrayList<ArrayList<E>> newList = new ArrayList<>();
		
		for(int i = 0; i < width; i++)
			newList.add(new ArrayList<E>());
		
		for(int i = 0; i < list.size(); i++)
			newList.get(i/width).add(list.get(i));
		
		return newList;
	}
	
	public ArrayList<E> convert2DTo1D(ArrayList<ArrayList<E>> list){
		ArrayList<E> newList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++){
			for (int x = 0; x < list.get(i).size(); x++)
				newList.add(list.get(i).get(x));
		}
		
		return newList;
		
	}
	
	public ArrayList<E> createDeepCopy(ArrayList<E> list){
		ArrayList<E> copy = new ArrayList<E>();
		
		for(int i = 0; i < list.size(); i++)
			copy.add(list.get(i));
		
		return copy;
		
	}
}
