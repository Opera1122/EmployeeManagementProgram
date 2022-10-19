package src.unusedfile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginError extends JFrame {
    JLabel label = new JLabel("아이디 또는 비밀번호를 다시 확인하세요.");
    JButton checkButton = new JButton("확인");
    public LoginError() {
        setTitle("로그인 실패");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 140);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        add(label);
        label.setBounds(125, 10, 400, 30);
        add(checkButton);
        checkButton.setBounds(200, 50, 80, 30);
        checkButton.addActionListener(new LoginError.MyListener());
    }

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == checkButton) {
                dispose();
            }
        }
    }
}
