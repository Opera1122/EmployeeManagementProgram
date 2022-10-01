package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {
    JPanel testPanel = new JPanel();
    JButton testButton = new JButton("이동완료");

    public Test() {
        setTitle("테스트");
        setSize(750, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        add(testPanel);
        testPanel.setBounds(50, 60, 630, 35);
        testPanel.add(testButton);
        testButton.addActionListener(new MyListener());
    }

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == testButton) {
                dispose();
                new Login();
            }
        }
    }
}
