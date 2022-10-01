package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginError extends JFrame {
    JLabel label = new JLabel("아이디 또는 비밀번호를 다시 확인하세요.");
    JButton checkButton = new JButton("확인");
    JPanel panel = new JPanel();
    public LoginError() {
        setTitle("로그인 실패");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 200);
        setResizable(false);
        setVisible(true);

        add(panel);
        panel.add(label);
        panel.add(checkButton);
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
