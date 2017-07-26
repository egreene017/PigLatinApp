import java.util.ArrayList;

//Version 1.0
public class PigLatinConverter {
	
	//Array of Digraphs and Trigraphs
	private static final String[] VOWELS = {"a", "e", "i", "o", "u", "y"};
	
	//primary function for conversion
	public String convert(String s) {
		
		//Split sentence into array of strings
		String st[] = s.split(" |-");
		
		//For each word, convert it to Pig Latin
		for (int i = 0; i < st.length; i++) {
			ArrayList<String> list = new ArrayList<String>();
			
			//Punctuation handling - Take out punctuation if its at the beginning or end of the word and add it back later
			while(!Character.isLetter(st[i].charAt(st[i].length()-1))) {
				StringBuilder sb = new StringBuilder(st[i]);
				list.add("" + st[i].charAt(st[i].length()-1)); //add punctuation to list
				sb.deleteCharAt(st[i].length()-1);
				st[i] = sb.toString();
			};
			
			//Only convert if the word contains a vowel 
			if (this.containsVowel(st[i])) {
				//Find the first instance of a vowel and move set that equal to shift
				String shift;
				if(!isVowel(st[i], 0, false)) { //if the first letter is not a vowel...
					//handle "qu" first
					if (st[i].substring(0, 2).equalsIgnoreCase("qu")) {
						shift = st[i].substring(0, 2);
					}
					else {
						shift = st[i].substring(0, 1);
						int letter = 1;
						while (!isVowel(st[i], letter, true)){
							shift = shift + Character.toString(st[i].charAt(letter));
							letter++;
						}
					}
					
				}
				else shift = st[i].substring(0,1);
				
				//Test to see if the shift is a vowel
				boolean isVowel = false;
				for(int j = 0; j < VOWELS.length - 1/*to not include y in this case*/; j++) {
					if (shift.equalsIgnoreCase(VOWELS[j])) {
						isVowel = true;
					}
				}
				
				//Vowel Start Conversion
				if(isVowel) {
					st[i] = st[i] + "way";
				}
				//Consonant Start Conversion
				else {
					StringBuilder sb = new StringBuilder(st[i]);
					sb.delete(0, shift.length());
					if (Character.isUpperCase(st[i].charAt(0))) {
						sb.setCharAt(0, sb.toString().toUpperCase().charAt(0));
					}
					st[i] = sb.toString();
					st[i] = st[i] + shift.toLowerCase() + "ay";
				}
			}
			
			//Put punctuation back into the word
			for(int j = 0; j < list.size(); j++) {
				st[i] = st[i] + list.get(j);
			}
		}
		
		//Make first Letter upperCase
		StringBuilder sb = new StringBuilder(st[0]);
		sb.setCharAt(0, st[0].toUpperCase().charAt(0));
		st[0] = sb.toString();
		
		//Concatenate array of strings into one String
		String pigLatin = "";
		for (int i = 0; i < st.length; i++) {
			pigLatin = pigLatin + st[i] + " ";
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
	public boolean containsVowel(String s) {
		if (Character.toString(s.charAt(0)).equalsIgnoreCase("y")) {
			for (int i = 1; i < s.length(); i++) {
				if (isVowel(s, i, true)) {
					return true;
				}
			}
		}
		else {
			for (int i = 0; i < s.length(); i++) {
				if (isVowel(s, i, true)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
