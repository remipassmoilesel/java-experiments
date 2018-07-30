package org.extractressources;

import java.io.IOException;
import java.nio.file.Paths;

public class Log {

	private static boolean DEBUG = false;

	private static FileOutput fileOutput;
	{
		try {
			fileOutput = new FileOutput(Paths.get("log.txt"));
		} catch (Exception e) {
			System.err.println("Unable to open log !");
			e.printStackTrace();
		}

	}

	public static void message(String message) {
		try {
			fileOutput.out("INFO: " + message);
		} catch (Exception e) {
			if (DEBUG) {
				System.err.println("Error while loggin ! " + e.getClass() + " "
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public static void error(Throwable error) {
		try {
			fileOutput.out("ERROR: " + error);
		} catch (Exception e) {
			if (DEBUG) {
				System.err.println("Error while loggin ! " + e.getClass() + " "
						+ e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
