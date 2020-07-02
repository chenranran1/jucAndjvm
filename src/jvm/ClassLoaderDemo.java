package jvm;

import com.trs.client.TRSConnection;

/**
 * @Description:测试类加载器
 * @author chenran
 * @date 2020年6月29日
 */
public class ClassLoaderDemo {

	public static void main(String[] args) {
		
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println(systemClassLoader);	// sun.misc.Launcher$AppClassLoader@4e25154f
		System.out.println(systemClassLoader.getParent());	// sun.misc.Launcher$ExtClassLoader@33909752
		System.out.println(systemClassLoader.getParent().getParent()); // null
		
		ClassLoader classLoader = TRSConnection.class.getClassLoader();
		System.out.println(classLoader);	// sun.misc.Launcher$ExtClassLoader@232204a1
	}
}
