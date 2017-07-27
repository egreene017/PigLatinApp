import java.util.ArrayList;

//Version 1.0
public class PigLatinConverter {
	
	//Array of Vowels
	private static final String[] VOWELS = {"a", "e", "i", "o", "u", "y"};
	
	//primary function for conversion
	public String convert(String s) {
		
		//Split sentence into array of strings
		String[][] split = this.split(s);
		String st[] = new String[split[0].length];
		String sy[] = new String[split[0].length];
		for (int i = 0; i < split[0].length; i++) {
			st[i] = split[0][i];
			sy[i] = split[1][i];
		}
		
		//For each word, convert it to Pig Latin
		for (int i = 0; i < st.length; i++) {
			
			//Only convert if the word contains a vowel 
			if (this.containsVowel(st[i])) {
				
				//Vowel Start Conversion
				if(this.isVowel(st[i], 0, false)) {
					st[i] = st[i] + "way";
				}
				//Consonant Start Conversion
				else {
					//Find the first instance of a vowel and move set that equal to shift
					String shift;
						//handle "qu" first
						if (st[i].substring(0, 2).equalsIgnoreCase("qu")) {
							shift = st[i].substring(0, 2);
						}
						else {
							shift = st[i].substring(0, 1);
							int letter = 1;
							while (!this.isVowel(st[i], letter, true)){
								shift = shift + Character.toString(st[i].charAt(letter));
								letter++;
							}
						}
						
					//Conversion
					StringBuilder sb = new StringBuilder(st[i]);
					sb.delete(0, shift.length());
					if (Character.isUpperCase(st[i].charAt(0))) {
						sb.setCharAt(0, sb.toString().toUpperCase().charAt(0));
					}
					st[i] = sb.toString();
					st[i] = st[i] + shift.toLowerCase() + "ay";
				}
				
			}
			
		}
		
		//Make first Letter upperCase
		StringBuilder sb = new StringBuilder(st[0]);
		sb.setCharAt(0, st[0].toUpperCase().charAt(0));
		st[0] = sb.toString();
		
		//Concatenate array of strings into one String
		String pigLatin = "";
		for (int i = 0; i < st.length; i++) {
			pigLatin = pigLatin + st[i] + sy[i];
		}
		
		return pigLatin;
	}
	
	//Method that returns true if the given index in the given String is a vowel. The third parameter will say whether or not to include y
	private boolean isVowel(String s, int index, boolean y) {
		boolean isVowel = false;
		//System.out.println("s = " + s + "; index = " + index + "; y = " + y);
		String letter = Character.toString(s.charAt(index));
		int size = VOWELS.length-1;
		if(y) size = VOWELS.length;
		for(int i = 0; i < size; i++) {
			if (letter.equalsIgnoreCase(VOWELS[i])) {
				isVowel = true;
			}
		}
		return isVowel;
	}
	
	//Method that returns true if the given string contains a vowel, a "y" is only treated as a consonant when it is the first letter of the string
	private boolean containsVowel(String s) {
		int ind = 0;
		if (Character.toString(s.charAt(0)).equalsIgnoreCase("y")) {
			ind = 1;
		}
		for (int i = ind; i < s.length(); i++) {
			if (isVowel(s, i, true)) {
				return true;
			}
		}
		return false;
	}
	
	//Method that splits words and symbols into two separate arrays
	private String[][] split(String s){
		//Separate into two lists first;
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> symbols = new ArrayList<String>();
		
		//Divide words and Symbols into their lists
		String current = "";
		Boolean word = false;
		//First test first position
		if(Character.isLetter(s.charAt(0))) {
			word = true;
			current = "" + s.charAt(0);
		}
		for (int i = 0; i < s.length(); i++) {
			//If current position is a letter...
			if(Character.isLetter(s.charAt(i))) {
				if(word) current = current+s.charAt(i);
				else {
					symbols.add(current);
					word = true;
					current = "" + s.charAt(i);
				}
			}
			//If current position is a symbol...
			else {
				if(!word) current = current+s.charAt(i);
				else {
					words.add(current);
					word = false;
					current = "" + s.charAt(i);
				}
			}
		}
		
		//add the last word or set of symbols
		if(word) words.add(current);
		else symbols.add(current);
		
		//Find size of larger list to find size of array
		int max = words.size();
		if(max < symbols.size()) max = symbols.size();
		
		//Create two-dimensional array
		String[][] split = new String[2][max];
		
		//Insert words list into array
		for (int i = 0; i < words.size(); i++) {
			split[0][i] = words.get(i);
		}
		
		//Insert symbols list into array
		for (int i = 0; i < symbols.size(); i++) {
			split[1][i] = symbols.get(i);
		}
		
		return split;
			
	}
}
