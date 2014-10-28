package lhj.java.test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TimerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ScheduledExecutorService executorService = Executors
				.newScheduledThreadPool(10);
		for (int i = 0; i < 10; i++) {
			ScheduledFuture<?> future = executorService.schedule(new Task(i),
					0, TimeUnit.MILLISECONDS);
			Object o = future.get();
			System.out.println(o);
		}
		executorService.shutdown();
		Timer timer = new Timer("test");
		for (int i = 0; i < 10; i++) {
			final int j = i;
			timer.schedule(new TimerTask() {
				public void run() {
					new Thread(new Task(j)).start();
				}
			}, new Date());
		}
		Timer timer2 = new Timer("test");
		for (int i = 0; i < 10; i++) {
			timer2.schedule(new Task(i), new Date());
		}

		Thread.sleep(10000);
		timer.cancel();
		timer2.cancel();
		for (int i = 0; i < 10; i++) {
			new Thread(new MyRun(i)).start();
		}
	}

	public static class MyRun implements Runnable {
		final static Semaphore avaliable = new Semaphore(3);
		int i = 0;

		public MyRun(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			try {
				avaliable.acquire();
				System.out.println("start test" + i);
				Thread.sleep(1000);
				System.out.println("end test" + i);
				avaliable.release();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}

	};

	public static class Task extends TimerTask implements Runnable {
		int i = 0;

		public Task(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println(i);
			if (i % 2 == 0) {
				System.out.println("before sleep" + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("after sleep" + i);
			}
		}

	}

}
