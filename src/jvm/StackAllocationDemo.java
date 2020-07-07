package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:栈空间分配测试（本质是标量替换）
 * JVM参数：-Xms1G -Xmx1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:-EliminateAllocations
 * @author chenran
 * @date 2020年7月4日
 */
public class StackAllocationDemo {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<User> list = new ArrayList<>();
		for (int i = 0; i < 10000000; i++) {
			// alloc();
			User user = new User();
			user.id = 1;
			user.name = "cr";
			 // list.add(user);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("花费时间毫秒数：" + (end - start));
		
		try {
			Thread.sleep(1000000);
		} catch (Exception e) {
		}
	}

	private static void alloc() {
		User user = new User();
		user.id = 1;
		user.name = "cr";
	}
	
	static class User{
		public int id;
		public String name;
//		public byte[] pic = new byte[1024 * 1024 * 20];
	}
	
	
}
