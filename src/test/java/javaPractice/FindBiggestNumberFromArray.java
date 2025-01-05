package javaPractice;

public class FindBiggestNumberFromArray {
	
	public static void main(String[] args) {
		int [] num = {3,4,6,23,14,5,6,67};
		int le = num.length;
		
		int max = num[0]; // initially considering num at zero index is greatest
		
		for(int i= 1;i<le;i++) {
			if(num[i]> max) {
				max = num[i];
			}
		}
		
		System.out.println(max);
	}

}
