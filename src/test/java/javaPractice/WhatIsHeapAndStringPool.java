package javaPractice;

public class WhatIsHeapAndStringPool {
	
	/*
	 * Heap Memory (String text = new String("Hello world");
	 * 1. is a region of memory used for dynamic memory allocations
	 * 2. where java object is stored during runtime execution
	 * 3. this memory is shared amongst all the thread and is managed by Java virtual machine through a process called garbage collections
	 * 
	 * Note: Objects are created dynamically by using a "new" keyword and their references is stored in the stack memory
	 */
	
	/*
	 *Garbage Collection ==> 
	 *1. JVM automatically manages memory allocations and deallocations.
	 *2. When any object becomes unreachable (i.e. no references point to them), then they are eligible for garbage collection.
	 */
	
	/*
	 * What is the String Pool?
		Note ==> The String Pool (or String Intern Pool) is a special storage area in the heap memory 
		that stores string literals. When you create a string using a literal (e.g., "Hello"),
		Java checks if that string already exists in the pool.
		If it does, it reuses the reference instead of creating a new object.
		=================== Example ==================
		public static void main(String[] args) {
        String str1 = "Hello"; // Created in String Pool
        String str2 = "Hello"; // Reuses reference from String Pool

        System.out.println("str1 == str2: " + (str1 == str2)); // true

        // Using new keyword creates a new object
        String str3 = new String("Hello"); // Not in String Pool
        System.out.println("str1 == str3: " + (str1 == str3)); // false
    }
    
    In this example:
	str1 and str2 both point to the same string literal in the String Pool.
	str3, created with new, refers to a different object in heap memory, demonstrating that it does not share the same reference as str1.
	 */

}
