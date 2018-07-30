package org.toasts;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Toast extends JFrame {

  private JPanel contentPane;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Toast frame = new Toast();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public Toast() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);

    JButton btnTestToast = new JButton("Test Toast");
    btnTestToast.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ToastMessage toastMessage = new ToastMessage("Sample text to toast ", 3000);
        toastMessage.setVisible(true);
      }
    });
    contentPane.add(btnTestToast, BorderLayout.SOUTH);
  }


  public class ToastMessage extends JDialog {

    int miliseconds;

    public ToastMessage(String toastString, int time) {

      this.miliseconds = time;
      setUndecorated(true);
      getContentPane().setLayout(new BorderLayout(0, 0));

      JPanel panel = new JPanel();
      panel.setBackground(Color.GRAY);
      panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
      getContentPane().add(panel, BorderLayout.CENTER);

      JLabel toastLabel = new JLabel("");
      toastLabel.setText(toastString);
      toastLabel.setFont(new Font("Dialog", Font.BOLD, 12));
      toastLabel.setForeground(Color.WHITE);

      setBounds(100, 100, toastLabel.getPreferredSize().width + 20, 31);

      setAlwaysOnTop(true);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      int y = dim.height / 2 - getSize().height / 2;
      int half = y / 2;
      setLocation(dim.width / 2 - getSize().width / 2, y + half);
      panel.add(toastLabel);
      setVisible(false);

      new Thread() {
        public void run() {
          try {
            Thread.sleep(miliseconds);
            dispose();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }.start();
    }
  }


}


