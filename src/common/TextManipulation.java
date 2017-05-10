package common;

public class TextManipulation {

	public static final char[] upperAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	public static final char[] lowerAlphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public static int alphabetToNum(String letter){
		
		switch(letter.toUpperCase()){
			case " ": return 0;
			case "A": return 1;
			case "B": return 2;
			case "C": return 3;
			case "D": return 4;
			case "E": return 5;
			case "F": return 6;
			case "G": return 7;
			case "H": return 8;
			case "I": return 9;
			case "J": return 10;
			case "K": return 11;
			case "L": return 12;
			case "M": return 13;
			case "N": return 14;
			case "O": return 15;
			case "P": return 16;
			case "Q": return 17;
			case "R": return 18;
			case "S": return 19;
			case "T": return 20;
			case "U": return 21;
			case "V": return 22;
			case "W": return 23;
			case "X": return 24;
			case "Y": return 25;
			case "Z": return 26;
		}
		
		return 99;
		
	}
	
	public static String numToAlphabet(int num){
		
		if(num > 26 || num < 0)
			System.out.println("The number " + num + " is either above 26 or below 0 and thus cannot be converted.");
		
		switch(num){
			case 0: return " ";
			case 1: return "A";
			case 2: return "B";
			case 3: return "C";
			case 4: return "D";
			case 5: return "E";
			case 6: return "F";
			case 7: return "G";
			case 8: return "H";
			case 9: return "I";
			case 10: return "J";
			case 11: return "K";
			case 12: return "L";
			case 13: return "M";
			case 14: return "N";
			case 15: return "O";
			case 16: return "P";
			case 17: return "Q";
			case 18: return "R";
			case 19: return "S";
			case 20: return "T";
			case 21: return "U";
			case 22: return "V";
			case 23: return "W";
			case 24: return "X";
			case 25: return "Y";
			case 26: return "Z";
		}
		
		return null;
		
	}

	public static String removeSpecialChar(String input, char character){
		
		String output = "";
		
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) != character)
				output += input.charAt(i);
		}
		
		return output;
		
	}
	
	public static String removeAllChar(String input){
		
		String output = "";
		
		for(int i = 0; i < input.length(); i++){
			
			if(input.charAt(i) == '?' || input.charAt(i) == '!' || input.charAt(i) == '.' ||
					input.charAt(i) == '/' || input.charAt(i) == '>' || input.charAt(i) == '\\' ||
					input.charAt(i) == '<' || input.charAt(i) == ',' || input.charAt(i) == '\'' ||
					input.charAt(i) == '#' || input.charAt(i) == '@' || input.charAt(i) == '\"' ||
					input.charAt(i) == '$' || input.charAt(i) == '%' || input.charAt(i) == '^' ||
					input.charAt(i) == '&' || input.charAt(i) == '&' || input.charAt(i) == '*' ||
					input.charAt(i) == '(' || input.charAt(i) == ')' || input.charAt(i) == '-' ||
					input.charAt(i) == '_' || input.charAt(i) == '=' || input.charAt(i) == '+' ||
					input.charAt(i) == '[' || input.charAt(i) == ']' || input.charAt(i) == '{' ||
					input.charAt(i) == '}' || input.charAt(i) == ':' || input.charAt(i) == ';' ||
					input.charAt(i) == '~' || input.charAt(i) == '`'){
				//then do nothing (don't add in to new output)
			}else{
				output += input.charAt(i);
			}	
		}
		return output;
	}
	
	public static int numOfSubstring(String input, String substring){
		
		int numSubstring = 0;
		boolean substringFound = true;
		
		for(int i = 0; i < input.length(); i++){//loop through input
			if(input.charAt(i) == substring.charAt(0)){//if beginning of substring found in input
				input = input.substring(i);//get rid of input before current position
				for(int a = 0; a < substring.length(); a++){//loop through substring
					if(input.charAt(i) != substring.charAt(a)){//compare substring char with input char
						substringFound = false;
						a = substring.length();//end loop
					}
					i++;//iterate through input at the same rate of substring
				}
				numSubstring += (substringFound) ? 1:0;
			}		
		}
		return numSubstring;
	}
	
}
