package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information extends JFrame {
    JPanel border = new JPanel();
    JLabel maker = new JLabel("제작자 : Opera1122");
    JLabel site = new JLabel("https://github.com/Opera1122");
    JLabel copyright = new JLabel("Copyright (C) 2022 all light reserved.");
    JButton check = new JButton("확인");
    public Information() {
        setTitle("프로그램 정보");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        add(border);
        border.setLayout(null);
        border.setBounds(10, 10, 365, 140);
        border.setBorder(new LineBorder(Color.BLACK));

        border.add(maker);
        maker.setBounds(125, 20, 150, 30);
        border.add(site);
        site.setBounds(100, 45, 200, 30);
        border.add(check);
        copyright.setBounds(85, 60, 250, 30);
        border.add(copyright);
        check.setBounds(150, 100, 60, 30);
        check.addActionListener(new MyListener());
    }

    class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == check) {
                dispose();
            }

        }
    }
}
