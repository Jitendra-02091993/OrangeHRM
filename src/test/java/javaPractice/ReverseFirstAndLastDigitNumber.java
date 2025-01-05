package javaPractice;
import java.util.ArrayList;
import java.util.List;

public class ReverseFirstAndLastDigitNumber {
	
	//23423532
	
	public static void main(String[] args) {
		int number = 6781234;
		
		List<Integer> list = new ArrayList<>();
		
		
		while(number>0) {
			int digit = number%10;
			list.add(0, digit);
			number = number/10;
		}
		System.out.println(list);
		System.out.println(list.size());
		
		int a = list.remove(0);
		int b = list.remove(list.size()-1);
		System.out.println(list);
		list.add(0,b);
		list.add(a);
//		
		System.out.println(list);
	}

}
