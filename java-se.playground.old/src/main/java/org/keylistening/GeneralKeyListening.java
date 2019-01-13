package org.keylistening;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by remipassmoilesel on 06/01/17.
 */
public class GeneralKeyListening {

    public static void main(String[] args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {

                        System.out.println();
                        System.out.println("Event received");
                        System.out.println(e.getKeyChar());
                        System.out.println(e.getKeyCode());
                        System.out.println(e.getModifiers());
                        System.out.println(e.getModifiersEx());
                        System.out.println(e.getSource());
                        System.out.println(e.getID() == KeyEvent.KEY_PRESSED);
                        System.out.println(e.getID() == KeyEvent.KEY_RELEASED);

                        // always return false to continue propagation of event
                        return false;
                    }
                });

    }

}
