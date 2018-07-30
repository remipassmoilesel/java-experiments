package org.memory;

/**
 * Created by remipassmoilesel on 26/08/16.
 */
public class Launcher {

  public static void main(String[] args){

    // get max allowed memory of JVM, en Mo
    System.out.print(Runtime.getRuntime().maxMemory()/1024/1024);


  }


}
