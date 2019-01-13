package org;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.function.Function;

/**
 * Java survival kit, to come back to Java after looooonngg month in web :)
 */
public class SurvivalKit {

    public static void main(String[] args) throws Exception {

        // Do not run this !
        System.exit(0);

        // delete recursively a directory
        FileUtils.deleteDirectory(Paths.get("directory").toFile());

        // simplest way to load xml file, with common ioutils
        // add '/' if resource is in top level of resource directory
        String config = IOUtils.toString(SurvivalKit.class.getResourceAsStream("/ddl-tool-mapping.xml.inc"),"UTF-8");

        // one line array
        int[] intArray = new int[]{1, 2, 3, 4};

        // time in ms
        System.out.println(System.currentTimeMillis());

        // create a local class
        class LocalClass extends Object {

        }
        new LocalClass();

        // lambda example: if we call a functionnal interface (interface with only one arguments)
        // this
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {

            }
        });

        // become this
        SwingUtilities.invokeLater(() -> {

        });

        // "Function" interface
        processOperation(8, (number) -> {
            return 8 == 8;
        });

    }

    private static Boolean processOperation(Integer number, Function<Integer, Boolean> function){
        return function.apply(number);
    }

}
