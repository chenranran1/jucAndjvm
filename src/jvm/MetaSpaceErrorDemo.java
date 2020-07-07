package jvm;

import java.lang.reflect.Method;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @Description:元数据内存分配错误测试，OOM，-XX:MaxMetaSpaceSize=10m
 * java.lang.OutOfMemoryError: Metaspace
 * @author chenran
 * @date 2020年7月7日
 */
public class MetaSpaceErrorDemo extends ClassLoader{

	public static void main(String[] args) {
		
		// 通过cglib动态创建代理类
		// cglibTest();
		// 通过ClassWriter对象来创建二进制字节码来创建类
		classWriterTest();
		
	}
	
	private static void classWriterTest() {
		int j = 0;
		try {
			MetaSpaceErrorDemo demo = new MetaSpaceErrorDemo();
			for (int i = 0; i < 10000; i++) {
				// 创建对象，用于生成类的二进制字节码
				ClassWriter classWriter = new ClassWriter(0);
				// 指明版本号、修饰符、类名、包名、父类、接口
				classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
				// 返回byte[]
				byte[] code = classWriter.toByteArray();
				// 类的加载
				demo.defineClass("Class" + i, code,0, code.length);
				j++; 
			}
		} finally {
			System.out.println(j); // 8531
		}
	}
	
	private static void cglibTest() {
		
		while(true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invoke(obj, args);
				}
			});
			enhancer.create();
		}
	}
	
	static class OOMObject{
		
	}
}

