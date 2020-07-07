package jvm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadLocalDemo {

//	public static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
//		protected DateFormat initialValue() {
//			return new SimpleDateFormat("yyyy-MM-dd");
//		};
//	};
	public static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();
	
	static {
		threadLocal.set(new SimpleDateFormat("yyyy-MM-dd"));
	}
	
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	static class myCallable implements Callable<Date>{

		@Override
		public Date call() throws Exception {
			return threadLocal.get().parse("2020-07-06");
			// return format.parse("2020-07-06");
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		
		DateFormat dateFormat = threadLocal.get();
		
		System.out.println(dateFormat.format("1993-10-15"));
//		ExecutorService service = Executors.newFixedThreadPool(10);
//
//	    List<Future<Date>> result = new ArrayList<>();
//
//	    myCallable task = new myCallable();
//	    for (int i = 0;i < 100;i++){
//	        result.add(service.submit(task));
//	    }
//
//	    for (Future<Date> future: result) {
//	        System.out.println(future.get());
//	    }
//	    
//	    service.shutdown();
	}
	
}
