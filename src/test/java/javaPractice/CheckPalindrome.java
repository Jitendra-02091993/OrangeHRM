package javaPractice;

import java.lang.reflect.Field;

import UIAutomation.handleAlert;

public class CheckPalindrome {
	public static void main(String[] args) {
//		checkPalindrome("nitin");
		reverseString("ksefce");
	}
	
	static String reverseString(String text) {
		String rev="";
		int le= text.length(); //6
		for(int i=le-1;i>=0;i--) {
			rev = rev + text.charAt(i);
		}
		return rev;
	}
	
	static void checkPalindrome(String text) {
		String reverse = reverseString(text);
		System.out.println(reverse);
		if(!reverse.equals(null)) {
		if(reverse.equals(text)) {
			System.out.println("String is Palindrome");
		}else {
			System.out.println("String is not a Palindrome");
		}
		}
	}
	
	
	

}
