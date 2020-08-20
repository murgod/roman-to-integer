package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class RomanToIntegerConverter {

public static void main(String[] args) {
   
   System.out.println("------------------ROMAN NUMERAL EXERCISE--------------------");
   
   while (true) {
	   
		 System.out.println("Enter the ROMAN numeral");
		 Scanner sc=new Scanner (System.in);
		 String roman=sc.next();                  
		 
		 roman = roman.toUpperCase();  
		 int len = roman.length();

         try {
        	 RomanToInteger r2i = new RomanToInteger();
        	 r2i.r2i(roman);
             System.out.println("Result : " +roman + " ==> " + r2i.getResult());
             System.out.println("---------------------------------------------------");
         }
         catch (NumberFormatException e ) {
             System.out.println(e.getMessage());
             System.out.println("Invalid Input : "+roman);
             System.out.println("---------------------------------------------------");
         }
         catch (IllegalArgumentException e) {
             System.out.println(e.getMessage());
             System.out.println("Invalid Input : "+roman);
             System.out.println("---------------------------------------------------");
         }
   }  
}  

} 
