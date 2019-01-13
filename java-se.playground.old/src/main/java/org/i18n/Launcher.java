package org.i18n;

import org.classpath.ClassPathInfo;

import javax.swing.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by remipassmoilesel on 02/11/16.
 */
public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            ClassPathInfo.showClassPath();

            // set the locale
            //              >> language / country
            Locale aLocale = new Locale("en", "EN");

            // get bundle
            ResourceBundle messages = ResourceBundle.getBundle("org.i18n.messages", aLocale);

            // show message
            JOptionPane.showMessageDialog(null, messages.getString("chips_are_down"));
        });
    }

}
