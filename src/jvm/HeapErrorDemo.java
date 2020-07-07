package jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pojo.User;

/**
 * @Description:堆空间内存分配错误，有以下两种情况错误：
 * 1.java.lang.OutOfMemoryError: Java heap space
 * 2.java.lang.OutOfMemoryError: GC overhead limit exceeded
 * @author chenran
 * @date 2020年7月7日
 */
public class HeapErrorDemo {

	public static void main(String[] args) {
		
//		System.out.println("堆空间OOM测试");
//		HeapOOM();
		
		System.out.println("GC overhead测试");
		gcOverHead();
		
	}

	private static void gcOverHead() {
		List<String> list = new ArrayList<>();
		while(true) {
			String string = "" + new Random().nextInt(666666) + new Random().nextInt(88888888);
			list.add(string.intern());
		}
	}

	private static void HeapOOM() {
		
		List<User> users = new ArrayList<>();
		while(true) {
			users.add(new User());
		}
		
	}
	
	
}
