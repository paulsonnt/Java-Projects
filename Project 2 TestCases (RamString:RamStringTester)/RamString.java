
public class RamString extends MyIndexOutOfBoundsException implements WackyStringInterface{
	
	private String current;
	
	public RamString() {
		current = "";
	}
	
	public RamString(String string) {
		current = string;
	}

	@Override
	public void setWackyString(String string) {
		current = string;
	}

	@Override
	public String getWackyString() {
		return current;
	}

	@Override
	public String getEvenCharacters() {
		String evenChars = "";
			for(int i = 1; i < current.length(); i += 2) {
			evenChars += current.charAt(i);
			}
		return evenChars;
	}

	@Override
	public String getOddCharacters() {
		String oddChars = "";
			for(int i = 0; i < current.length(); i += 2) {
			oddChars += current.charAt(i);
			}
		return oddChars;
	}

	@Override
	public int countNonDigits() {
		int nonDigits = 0;
		for(int i = 0; i < current.length(); i++) {
			if(checkIsDigit(current.charAt(i)) == false) {
				nonDigits++;
			}
		}
		return nonDigits;
	}

	@Override
	public boolean isValidEmail() {
		int posOfATR = 0;
		int posOfPeriod = 0;
		for(int i = 0; i < current.length(); i++) {
			if(current.charAt(i) == '@') {
				posOfATR = i;
			}
			if(current.charAt(i) == '.') {
				posOfPeriod = i;
			}
		}
		
		if(posOfATR >= 1) {
			if(posOfPeriod >= posOfATR + 2) {
				if(current.length() > posOfPeriod) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void ramifyString() {
		String ramified = "";
		for(int i = 0; i < current.length(); i++) { 
			System.out.println (current.length() + " " + i + " " + current.charAt(i));
			if(current.charAt(i) == '0') {
				if(i + 1 < current.length()){
					if(current.charAt(i+1) == '0') {
						ramified += "VCU";
						i++;
					}	
				}
				else {
					ramified += "Rams";
				}
			}
		
			else {
				ramified += current.charAt(i);
			}
		}
		current = ramified;
	}

	@Override
	public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) 
			throws MyIndexOutOfBoundsException, IllegalArgumentException {
		if((endPosition-startPosition) > current.length()) {
			String message = "";
			//super.MyIndexOutOfBoundsException();
		}
		if(startPosition > endPosition) {
		//	throw IllegalArgumentException;
		}
		String newString = "";
		for(int i = 0; i < current.length(); i++) {
			if(i < startPosition || i > endPosition) {
				newString += current.charAt(i);
			}
			else {
			if(checkIsDigit(current.charAt(i)) == true) {
				newString += convertToRomanNumeral(current.charAt(i));
			}
			else {
				newString += current.charAt(i);
			}
		}
	}
	current = newString;	
}
	
	public boolean checkIsDigit(char aDigit) {
		char[] ints = new char[] {'0','1','2','3','4','5','6','7','8','9'};
		for(int i = 0; i < ints.length; i++) {
			if(aDigit == ints[i]) {
				return true;
			}
		}
		return false;
	}
	
	public String convertToRomanNumeral(char aChar) {
		String[] romanNums = new String[] {"0","I","II","III","IV","V","VI","VII","VIII","IX"};
		int aInt = (int) aChar;
		return romanNums[aInt];
	}

}
