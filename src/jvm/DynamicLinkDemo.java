package jvm;

/**
 * @Description:动态链接测试demo
 * @author chenran
 * @date 2020年6月30日
 */
public class DynamicLinkDemo { 

	public void methodA() {
		int j = 1;
		int k = 2;
		int l = j + k;
		int a = methodB();
	}

	public int methodB() {
		System.out.println("B");
		return 3;
	}
	
	
	
}
