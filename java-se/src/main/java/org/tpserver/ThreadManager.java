package org.tpserver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {

	/** Taille du pool */
	private static final int THREAD_POOL_LENGHT = 200;

	/** Pool de thread */
	private static final ExecutorService executor = Executors
			.newFixedThreadPool(THREAD_POOL_LENGHT);

	/**
	 * Executer une action dans un thread séparé.
	 * 
	 * @param run
	 */
	public static void run(Runnable run) {
		executor.execute(run);
	}
}
