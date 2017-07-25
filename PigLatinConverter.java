import java.util.ArrayList;

public class PigLatinConverter {
	
	//Array of Digraphs and Trigraphs
	private static final String[] DIGRAPH = {"bl", "br", "ch", "cl", "cr", "dr", "fl", "fr", "gl", "gr", "pl", "pr", "qu", "sc", "sh", "sk", "sl", "sm", "sn", "sp", "st", "sw", "th", "tr", "tw", "wh", "wr",};
	private static final String[] TRIGRAPH = {"sch", "scr", "shr", "sph", "spl", "spr", "squ", "str", "thr"};
	
	
	//primary function for conversion
	public String convert(String s) {
		
		//Split sentence into array of strings
		String st[] = s.split(" ");
		//Variable to keep track whether or not a Digraph or Trigraph was used: 1 for digraph, 2 for trigraph, 0 for neither
		int type;
		
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
			
			//Retrieve the shifting character, digraph, or trigraph in the string if string is greater than or equal to three characters
			String shift;
			if(st[i].length() >= 3) {
				shift = this.getDigraphOrTrigraph(st[i]);
			}
			else shift = st[i].substring(0,1);
			
			//label type as 0, 1, or 2
			if(shift.length() == 3) type = 2;
			else if(shift.length() == 2) type = 1;
			else type = 0;
			
			//Vowel Start Conversion
			if(shift.equalsIgnoreCase("a") || shift.equalsIgnoreCase("e") || shift.equalsIgnoreCase("i") || shift.equalsIgnoreCase("o") || shift.equalsIgnoreCase("u")) {
				st[i] = st[i] + "way";
			}
			//Consonant Start Conversion
			else {
				StringBuilder sb = new StringBuilder(st[i]);
				sb.delete(0, type+1);
				if (Character.isUpperCase(st[i].charAt(0))) {
					sb.setCharAt(0, sb.toString().toUpperCase().charAt(0));
				}
				st[i] = sb.toString();
				st[i] = st[i] + shift.toLowerCase() + "ay";
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
	
	//Method that tests to see if a digraph stars the given String. If Digraph exists, return digraph. Otherwise, return first character
	//String parameter MUST be greater than two characters
	private String getDigraphOrTrigraph(String s) {
		
		//Test Trigraph, return Trigraph
		String firstThree = s.substring(0, 3);
		for (int i = 0; i < TRIGRAPH.length; i++) {
			if (firstThree.equalsIgnoreCase(TRIGRAPH[i])) {
				return firstThree;
			}
		}
		
		//Test Digraph, return Digraph
		String firstTwo = s.substring(0, 2);
		for(int i = 0; i < DIGRAPH.length; i++) {
			if (firstTwo.equalsIgnoreCase(DIGRAPH[i])) {
				return firstTwo;
			}
		}
		
		//Otherwise return First letter
		return s.substring(0, 1);
	}
	
}
