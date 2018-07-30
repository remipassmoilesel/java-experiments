package org.argparse;

/**
 * Created by remipassmoilesel on 17/11/16.
 */
public class StandardArgParse {

    public static void main(String[] args) {
        // arg1 -arg2 val2 -arg3 val3
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
