package org.classpath;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by remipassmoilesel on 02/11/16.
 */
public class ClassPathInfo {
    public static void main(String[] args) {
        showClassPath();
    }

    public static void showClassPath() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }

    }
}
