package javaPractice;

import java.util.ArrayList;
import java.util.List;

public class ReverseInteger {
	
	public static void main(String[] args) {
		int value = 65221763;
		
		List<Integer> list = new ArrayList<>();
		
		while(value > 0) {
			int digit = value % 10;
			list.add(digit);
			value = value / 10;
		}
		System.out.println(list);
		int reversedValue =0;  //36
		
		for(int i=0;i<list.size();i++) {
			reversedValue = reversedValue * 10 + list.get(i);
		}
		
		System.out.println(reversedValue);
		
		
		
		
	}

}
