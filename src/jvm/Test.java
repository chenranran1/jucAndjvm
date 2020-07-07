package jvm;

import pojo.User;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
//		Class<String> forName = (Class<String>) Class.forName("java.lang.String");
//		System.out.println(forName);
//		Class<?> forName2 = Class.forName("java.lang.String");
//		System.out.println(forName == forName2);
//		String string = forName.newInstance();
//		System.out.println(string);
//		ClassLoader classLoader = forName.getClassLoader();
//		System.out.println(classLoader);
		// Thread.sleep(1000000);
//		while(true) {
//			Student student = new Student();
//			
//		}
		
		// 测试Class对象的字段和方法
//		Class<User> clazz = (Class<User>) Class.forName("pojo.User");
//		Field[] fields = clazz.getFields();
//		for (Field field : fields) {
//			System.out.println(field.getName());
//		}
//		
//		Method[] methods = clazz.getMethods();
//		for (Method method : methods) {
//			System.out.println(method.getName());
//		}
		
//		User user = new User();
//		User user2 = new User();
//		User user3 = new User();
		// User.method2();
		// System.out.println("-----------------------");
		System.out.println(User.area);
		System.err.println(User.age);
		// System.out.println(User.pic);
		// Thread.sleep(1000000);
		// System.gc();
		// Thread.sleep(2000);
		// User user = new User();
		Thread.sleep(1000);
	}
	

}
