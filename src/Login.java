package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    JPanel labelPanel = new JPanel();
    JPanel textFieldPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JLabel Label = new JLabel("<html><body style='text-align:center;'><h1>로그인</h1>이용하시려면 관리자 계정을 로그인 하세요.</body></html>");
    JTextField idTextField = new JTextField(10);
    JTextField passwordTextField = new JTextField(10);
    JButton loginButton = new JButton("로그인");
    JButton cancelButton = new JButton("종료");

    public Login() {
        setTitle("사원 관리 프로그램");
        setSize(750, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        add(labelPanel);
        labelPanel.setBounds(200, 20, 500, 80);
        labelPanel.add(Label);

        add(textFieldPanel);
        textFieldPanel.setBounds(100, 200, 500, 100);
        textFieldPanel.add(idTextField);
        textFieldPanel.add(passwordTextField);

        add(buttonPanel);
        buttonPanel.setBounds(50, 400, 500, 50);
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        loginButton.addActionListener(new MyListener());
        cancelButton.addActionListener(new MyListener());
    }

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                dispose();
                new Test();
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }
    }
}
