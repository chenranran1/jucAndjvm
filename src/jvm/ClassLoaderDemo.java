package jvm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sun.java.accessibility.util.Translator;

/**
 * @Description:测试类加载器及一般java工程资源路径的问题
 * @author chenran
 * @date 2020年6月29日
 */
public class ClassLoaderDemo {

	public static void main(String[] args) {
		
		// 根加载器加载${JAVA_HOME}/jre/lib目录下的jar包
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println(systemClassLoader);	// sun.misc.Launcher$AppClassLoader@4e25154f
		System.out.println(systemClassLoader.getParent());	// sun.misc.Launcher$ExtClassLoader@33909752
		System.out.println(systemClassLoader.getParent().getParent()); // null
		
		// ExtClassLoader加载${JAVA_HOME}/jre/lib/ext/*.jar
		ClassLoader classLoader = Translator.class.getClassLoader();
		System.out.println(classLoader);	// sun.misc.Launcher$ExtClassLoader@232204a1
		
		// AppClassLoader加载ClassPath下的jar和class文件
		
		// 资源路径测试
		ClassLoaderDemo demo = new ClassLoaderDemo();
		// ①.获取类的class文件所在的目录
		String path1 = demo.getClass().getResource("").getPath();
		System.out.println(path1);	// /D:/eclipse-workspace/test-jucjvm/bin/jvm/
		
		// ②.获取classpath的根目录
		String path2 = demo.getClass().getResource("/").getPath();
		System.out.println(path2); // /D:/eclipse-workspace/test-jucjvm/bin/
		
		// ③.获取classpath下指定文件的完整路径
		String path3=demo.getClass().getClassLoader().getResource("user.properties").getPath();
        System.out.println(path3);
        
        // ④.读取classpath下的指定文件
        InputStream inputStream = demo.getClass().getClassLoader().getResourceAsStream("user.properties");
        Properties properties = new Properties();
        try {
			properties.load(inputStream);
			String name = String.valueOf(properties.get("name"));
			System.out.println(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		// ⑤.获取classpath的根目录
        String path4=demo.getClass().getClassLoader().getResource("").getPath();
        System.out.println(path4); // /D:/eclipse-workspace/test-jucjvm/bin/
		
	}
}
