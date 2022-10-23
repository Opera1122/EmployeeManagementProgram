package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.jdbc.*;
import java.sql.*;

public class Login extends JFrame {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image iconImage = toolkit.getImage("src/em.png");
    JPanel labelPanel = new JPanel();
    JPanel idPanel = new JPanel();
    JPanel passwordPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JLabel Label = new JLabel("<html><body style='text-align:center;'><h1>로그인</h1>이용하시려면 관리자 계정을 로그인 하세요.</body></html>");
    public JTextField idTextField = new JTextField(15);
    JLabel idLabel = new JLabel("ID");
    public JPasswordField passwordTextField = new JPasswordField(15);
    JLabel passwordLabel = new JLabel("PW");
    JButton loginButton = new JButton("로그인");
    JButton cancelButton = new JButton("종료");

    public Login() {
        setTitle("사원 관리 프로그램");
        setSize(500, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setIconImage(iconImage);

        passwordTextField.setEchoChar('*');

        add(labelPanel);
        add(idPanel);
        add(passwordPanel);
        add(buttonPanel);

        labelPanel.setBounds(40, 25, 400, 100);
        labelPanel.setBorder(new LineBorder(Color.BLACK));
        labelPanel.add(Label);

        idPanel.setBounds(155, 160, 190, 25);
        passwordPanel.setBounds(146, 190, 200, 25);
        idPanel.add(idLabel);
        idPanel.add(idTextField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);

        buttonPanel.setBounds(170, 240, 140, 35);
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        loginButton.addActionListener(new MyListener());
        cancelButton.addActionListener(new MyListener());
    }

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                try {
                    AdminDAO.LoginProcess(idTextField.getText(), String.valueOf(passwordTextField.getPassword()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (AdminDAO.inputMatchCheck == true) {
                    dispose();
                    new MenuSelection();
                    AdminDAO.inputMatchCheck = false;
                } else {
                    JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 다시 확인하세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }
    }
}
