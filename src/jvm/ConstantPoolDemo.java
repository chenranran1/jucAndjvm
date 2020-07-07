package jvm;

/**
 * @Description:常量池测试
 * @author chenran
 * @date 2020年6月29日
 */
public class ConstantPoolDemo {

	public static void main(String[] args) {
		
		String string = new StringBuilder("计算机").append("软件").toString();
		System.out.println(string.intern() == string); // true
		
		String string2 = new StringBuilder("ja1").append("va").toString();
		System.out.println(string2.intern() == string2); // false
		
		String string3 = new String("java");
		String string4 = "java";
		System.out.println(string3 == string4);	// false
		System.out.println(string3.intern() == string4);	// true
		
		
	}
}
