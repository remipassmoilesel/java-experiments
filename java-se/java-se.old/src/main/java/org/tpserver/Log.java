package org.tpserver;

public class Log {

	public static void error(Exception e) {
		e.printStackTrace();
	}

	public static void info(String string) {
		System.out.println(string);
	}

}
