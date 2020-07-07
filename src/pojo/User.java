package pojo;

import java.util.Random;

public class User {

	private String name;
	private String sex;
	private String gender;
	private final String phone = "32";
	public static String teString;
	public static final String unit = "1";
	public static final int area = 2;
	public static final int age = new Random().nextInt();
	public static byte[] pic = new byte[1024*1024*200];

	static {
		System.out.println("User类的静态初始化块");
	}

	public void method() {

	}
 
	public static void method2() {
		System.out.println("静态方法");
	}

	public void method3() {

	}

	public static void method4() {

	}
}
