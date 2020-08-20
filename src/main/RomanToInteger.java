package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RomanToInteger {

	private int result;  

	/*Constructor*/
	public RomanToInteger() {
		result = 0;
	}

	/*
	 * Roman to Integer Mappings
	 * M =1000, CM= 900, D=500, CD=400, C=100, XC= 90, L= 50, XL = 40, X = 10, IX = 9, V = 5, IV = 4, I = 1 
	 */
	private int r2imapper(char ch) {
		switch (ch) {
		case 'I':  
			return 1;
		case 'V':  
			return 5;
		case 'X':  
			return 10;
		case 'L':  
			return 50;
		case 'C':  
			return 100;
		case 'D':  
			return 500;
		case 'M':  
			return 1000;
		default:   
			return -1;
		}
	}
	
	/**
	 *  SUBTRACTIVE RULES
    SUBRULE-1. Only one I, X, and C can be used as the leading numeral in part of a subtractive pair.
    SUBRULE-2. I can only be placed before V and X.
    SUBRULE-3. X can only be placed before L and C.
    SUBRULE-4. C can only be placed before D and M. */
	private static void validateSubtractiveRules(String roman, int index) {
		if (roman.charAt(index-1) == 'I') { 
			if ((roman.charAt(index) != 'X') && (roman.charAt(index) != 'V')) 
				throw new NumberFormatException("Roman Numeral SUBRULE-2 validation failure");

		}
		else if (roman.charAt(index-1) == 'X') { 
			if ((roman.charAt(index) != 'L') && (roman.charAt(index) != 'C'))
				throw new NumberFormatException("Roman Numeral SUBRULE-3 validation failure");
		}
		else if (roman.charAt(index-1) == 'C') {  
			if ((roman.charAt(index) != 'D') && (roman.charAt(index) != 'M'))
				throw new NumberFormatException("Roman Numeral SUBRULE-4 validation failure");
		}
	}


	private static boolean validateRule1(ArrayList<Integer> list){
		ArrayList<Integer> temp = new ArrayList<Integer>(list);
		Collections.sort(temp, Collections.reverseOrder());
		return temp.equals(list);
	}

	
	public int getResult() {
		return this.result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * 
	ROMAN NUMERAL RULES:
	RULE-1. Numerals must be arranged in descending order of size.
	RULE-2. M, C, and X cannot be equaled or exceeded by smaller denominations.
       - If No. of I >= 10 input is invalid.
       - if No. of V >= 2 input is invalid. (Covered in RULE-3)
       - if No. of X >= 10 input is invalid.
       - If No. of L >= 2 input is invalid. (Covered in RULE-3)
       - if No. of C >=10 input is invalid. 
       - if No. of D >=2 input is invalid. (Covered in RULE-3) 
	RULE-3. D, L, and V can each only appear once.
	
	**/	
	public int r2i(String roman) {
		
		int strLen = roman.length();
		Set<Character> dlvSet = new HashSet<>();
		ArrayList<Integer> descOrder = new ArrayList<>();
		int countI = 0, countX = 0, countC = 0, i = 0,res = 0;
        
		
		if ( strLen == 0) {
			throw new NumberFormatException("Empty input string");
		}
		
		while (i < strLen) {
			//current ch in string.
			char ch = roman.charAt(i);        
			i++; //Next ch in string 
			
			/*RULE-3 update and validation*/	  
			if((ch == 'D') || (ch == 'L') || (ch == 'V')) {
				if(dlvSet.contains(ch)) {
					throw new NumberFormatException("Roman Numeral RULE-3 validation failure");
				}
				dlvSet.add(ch);
			}
			
			/* RULE-2 counter updates*/
			if(ch == 'I') {
				countI++;
			}else if(ch == 'X') {
				countX++;
			}else if(ch == 'C') {
				countC++;
			}

			int number = r2imapper(ch);  
			if (number < 0)
				throw new NumberFormatException("Illegal character : "+ch);
			
			
			if (i == roman.length()) {
				// Add last character value to result.
				res += number;
				descOrder.add(number);
			}
			else {
				int nextnum = r2imapper(roman.charAt(i));
                //If next number is greater than previous apply subtractive rules.
				if (number < nextnum) {
					//Validate the adjacent letters as valid pairs. 
					validateSubtractiveRules(roman, i);
					//Subtract lower number from higher number to find value
					res += (nextnum - number);
					descOrder.add(nextnum - number);
					i++;
				}
				else {
                    // Descending letters keep adding
					res += number;
					descOrder.add(number);
				}
			}

		}  

		/* RULE-2 counter validation*/
		if(countI >= 10 || countX >= 10 || countC >= 10) 
			throw new NumberFormatException("Roman Numeral RULE-2 validation failure");

		if (!validateRule1(descOrder))
			throw new NumberFormatException("Roman Numeral RULE-1 validation failure");
		
		if ((res < 0) || (res > 3999))
		    throw new IllegalArgumentException("Invalid argurment");

		setResult(res);
		
		return res;
	} 

} 





