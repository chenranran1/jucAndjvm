package jvm;

/**
 * @Description:本地变量表复用测试
 * @author chenran
 * @date 2020年7月1日
 */
public class LocalVariableDemo {

	public static void main(String[] args) {
		{
			byte[] myByte = new byte[64 * 1024 * 1024];
		}
		int a = 1;
		System.gc();
	}
}
