package org.remipassmoilesel.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by remipassmoilesel on 11/02/17.
 */
public class Utils {

    /**
     * Print attributes of a HTTP request for debug purposes
     *
     * @param httpRequest
     */
    public static void printAttributes(HttpServletRequest httpRequest) {

        System.out.println();
        System.out.println("Attributes of: " + httpRequest);
        Enumeration<String> names = httpRequest.getAttributeNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Object value = httpRequest.getAttribute(name);
            System.out.println("\t # Name: " + name);
            System.out.println("\t   Value: " + value);
        }
    }

}
