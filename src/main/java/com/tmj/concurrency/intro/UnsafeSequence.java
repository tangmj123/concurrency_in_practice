package com.tmj.concurrency.intro;

import java.util.HashMap;

import org.junit.Test;

public class UnsafeSequence {

	private int value;
	
	public int getNext() {
		int i = value;
		try {
			Thread.currentThread().sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		value+=1;
		i = value;
		return i;
//		return value++;//取出value的值，给value加1，把值赋给value
	}
	
	
	@Test
	public void testUnSafe() {
		HashMap map = new HashMap(10000);
		while(true){
			Thread thread = new Thread(()->{
				int sequence = getNext();
				System.out.println(Thread.currentThread().getName()+"===>"+sequence);
				if(map.containsKey(sequence)){
					System.exit(0);
				}
				else
					map.put(sequence, sequence);
			});
			thread.start();
			
//			Thread-467===>468
//			Thread-474===>471
//			Thread-471===>473
//			Thread-470===>470
//			Thread-469===>468
		}
	}
}
