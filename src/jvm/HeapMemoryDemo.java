package jvm;

/**
 * @Description:堆内存大小测试（默认初始为物理内存1/64，最大为物理内存1/4）
 * 堆参数：-Xms600m -Xmx600m -XX:+PrintGCDetails
 * @author chenran
 * @date 2020年7月2日
 */
public class HeapMemoryDemo {
	
	public static void main(String[] args) throws Exception {
		
		long initial = Runtime.getRuntime().totalMemory();
		long max = Runtime.getRuntime().maxMemory();
		
		// 实际结果并不是600m，而是575m，原因是幸存1区的容量没有计算，因为本身只有一个幸存区会存储数据
		// 除了使用-XX:+PrintGCDetails查看参数外，还可以使用命令行参数：jps / jstat -gc 进程id
		System.out.println("-Xms：" + initial/1024/1024 + "MB");
		System.out.println("-Xmx：" + max/1024/1024 + "MB");
		
		Thread.sleep(100000000);
		
	}

}
