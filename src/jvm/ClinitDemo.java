package jvm;

/**
 * @Description:类加载过程中的初始化过程测试 
 * 	1.初始化顺序按照代码的顺序 	2.先初始化父类再初始化子类	3.多线程在类的初始化的过程中是同步执行的
 * @author chenran
 * @date 2020年6月29日
 */
public class ClinitDemo {

	private static int a = 1;
	
	static {
		a = 2;
		b = 3;
		// System.out.println(b); // Cannot reference a field before it is defined
	}
	
	private static int b = 4;

	static class father {
		static {
			System.out.println("父类静态块");
		}
		private void test() { 
			
		}
		public final void test1() {
			
		}
	}

	static class son extends father {
		static {
			System.out.println("子类静态块");
		}
		private void test() {
			
		}
		public void test2() {
			super.test1();
		}
		
	}
	
	static class SynInit{
		
		static {
			System.out.println(Thread.currentThread().getName() + "进入静态代码块");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "完成类的初始化过程");
		}
		
	}
	

	public static void main(String[] args) {
		// 1.初始化顺序按照代码的顺序 
		System.out.println(ClinitDemo.a);	// 2
		System.out.println(ClinitDemo.b);  	// 4
		
		// 2.先初始化父类再初始化子类
		new son();
		
		// 3.类只会初始化一次，并且多线程是同步执行的
		new Thread(()->{
			new SynInit();
		},"AAA").start();
		new Thread(()->{
			new SynInit();
		},"BBB").start();
	}
}
