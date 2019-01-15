package org.lambda;

import javax.swing.*;
import java.util.function.Function;

/**
 * Created by remipassmoilesel on 20/07/16.
 */
public class LambdaTries {


    public static void main(String args[]) {

        // lambda example: if we call a functionnal interface (interface with only one arguments)
        // this
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });

        // become this
        SwingUtilities.invokeLater(() -> {

        });

        System.out.println(applyTreatment(argument -> String.valueOf(argument), new Integer(8)));

    }

    /**
     * This method accept a lambda expression and a parameter
     *
     * @param lambda
     * @param toTreat
     * @return
     */
    public static String applyTreatment(Function<Integer, String> lambda, Integer toTreat) {

        // apply lambda to parameter
        return lambda.apply(toTreat);

    }


}
