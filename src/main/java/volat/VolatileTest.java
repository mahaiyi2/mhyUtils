package volat;

import java.util.HashMap;
import java.util.Map;

/**
 * volatile变量自增运算测试
 *
 * @author zzm
 */
public class VolatileTest {
	public static Map<String,Object> map = new HashMap<String,Object>();
	public static volatile int race = 0;
	
	public static void increase() {
		race++;
	}

	private static final int THREADS_COUNT = 200;

	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					map.put(Singleton.getInstance().toString(),"0");
				}
			});
			threads[i].start();
		}
		// 等待所有累加线程都结束
		while (Thread.activeCount() > 1)
			Thread.yield();
		//System.out.println(race);
		for(String key : map.keySet()){
			System.out.println(key);
		}
	}
}
