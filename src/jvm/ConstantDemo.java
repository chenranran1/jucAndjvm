package jvm;

import java.util.Random;

/**
 * @Description:静态常量对类的加载情况分析
 * @author chenran
 * @date 2020年7月1日
 */
public class ConstantDemo {

	public static int num = 1;	// 不会加载类
	public static int num2 = new Random().nextInt(); // 会加载类
	
	static {
		System.out.println("静态初始化块");
	}
	
	public static void main(String[] args) {
		new Thread(()->{System.out.println(num);}).start();
		new Thread(()->{System.out.println(num);}).start();
		new Thread(()->{System.out.println(num);}).start();
		new Thread(()->{System.out.println(num);}).start();
	}
}
