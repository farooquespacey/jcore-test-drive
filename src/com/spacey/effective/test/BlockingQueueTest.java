package com.spacey.effective.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {

	private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);;

	public static void main(String[] args) {
		BlockingQueueTest bqt = new BlockingQueueTest();
		List<String> partialList = new ArrayList<String>();
		bqt.queue.drainTo(partialList, 1000000);
		System.out.println(partialList.size());
		if (partialList.size() > 0) {
			System.out.println("AAA");
		}
	}

}
