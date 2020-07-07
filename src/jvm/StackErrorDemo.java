package jvm;

/**
 * @Description:栈内存分配错误测试，有两种情况：
 * 1.java.lang.StackOverflowError
 * 2.unable to create new native thread
 * @author chenran
 * @date 2020年7月7日
 */
public class StackErrorDemo {

	public static void main(String[] args) {
		
		// StackOverflowError
		overFlow();
		
		// unable to create new native thread
		createNativeThread();
	}

	private static void createNativeThread() {
		int i = 0;
		try {
			while(true) {
				i++;
				new Thread(()->{
					try {
						Thread.sleep(Integer.MAX_VALUE);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}).start();
			}
		} catch (Exception e) {
			System.out.println("最终创建的线程个数为：" + i);
			e.printStackTrace();
		}
		
	}

	private static void overFlow() {
		overFlow();
	}
}

