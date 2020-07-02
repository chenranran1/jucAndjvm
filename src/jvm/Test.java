package jvm;

public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class<?> forName = Class.forName("java.lang.String");
		ClassLoader classLoader = forName.getClassLoader();
		System.out.println(classLoader);
	}

}
