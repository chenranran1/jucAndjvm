package jvm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @Description:直接内存测试，包括以下两方面：-XX:MaxDirectMemorySize=10m 
 * 1.OOM测试：java.lang.OutOfMemoryError:Direct buffer memory
 * 2.拷贝文件性能测试（IO与NIO），最终结果表明NIO性能要高出普通IO很多
 * 3.底层使用的是Unsafe类
 * public native long allocateMemory(long var1);
 * public native void freeMemory(long var1);
 * @author chenran
 * @date 2020年7月7日
 */
public class DirectMemoryDemo {

	public static final String URL = "D:\\迅雷下载\\何方神圣.mkv";
	public static final int _100MB = 1024 * 1024 * 100;
	public static final int _2G = 1024 * 1024 * 1024;

	public static void main(String[] args) throws Exception {

		// System.out.println("测试直接内存溢出");
		// oom();
		// System.out.println("测试文件复制");
		// copyTest();
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_2G);
		System.out.println("直接内存分配完毕");
		Scanner input = new Scanner(System.in);
		input.next();
		
		System.out.println("直接内存开始释放");
		byteBuffer = null;
		System.gc();
		input.next();
		
	}

	private static void copyTest() {
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 3; i++) {
			String dest = "D:\\迅雷下载\\何方神圣_" + i + ".mkv";
			// copyByIO(dest); // 29420ms
			// copyByNIO(dest);	// 13994ms
		}
		long end = System.currentTimeMillis();
		System.out.println("完成文件拷贝，总耗时：" + (end-start) + "ms");
	}
	
	private static void oom() {
		// 默认直接内存的大小也是1/4物理内存
		long maxDirectMemory = sun.misc.VM.maxDirectMemory();
		System.out.println("MaxDirectMemory：" + maxDirectMemory + "B、" + maxDirectMemory / 1024 / 1024 + "MB");
		// 直接在堆外内存上创建对象
		ByteBuffer buffer = ByteBuffer.allocateDirect(60 * 1024 * 1024);
	}

	private static void copyByIO(String dest) throws Exception {
		// 文本char[] 二进制byte[]
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(URL);
			fos = new FileOutputStream(dest);
			byte[] buffer = new byte[_100MB];
			while(true) {
				int len = fis.read(buffer);
				if (len == -1) {
					break;
				}
				fos.write(buffer, 0 , len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}

	private static void copyByNIO(String dest) throws Exception {
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(URL);
			fos = new FileOutputStream(dest);
			inChannel = fis.getChannel();
			outChannel = fos.getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocateDirect(_100MB);
			while(inChannel.read(buffer) != -1) {
				buffer.flip(); 
				outChannel.write(buffer);
				buffer.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
			if (outChannel != null) {
				outChannel.close();
			}
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
}
