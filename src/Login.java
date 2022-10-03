package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    static Admin admin = new Admin(null, null, 0, null, null, null);

    JPanel labelPanel = new JPanel();
    JPanel idPanel = new JPanel();
    JPanel passwordPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JLabel Label = new JLabel("<html><body style='text-align:center;'><h1>로그인</h1>이용하시려면 관리자 계정을 로그인 하세요.</body></html>");
    JTextField idTextField = new JTextField(15);
    JLabel idLabel = new JLabel("ID");
    JTextField passwordTextField = new JTextField(15);
    JLabel passwordLabel = new JLabel("PW");
    JButton loginButton = new JButton("로그인");
    JButton cancelButton = new JButton("종료");

    public static Connection makeConnection() {
        Connection con = null;

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");

            String url="jdbc:mysql://localhost:3306/employeedb?serverTimezone=Asia/Seoul";
			String userId="root";
			String password="1234";

            con = DriverManager.getConnection(url, userId, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void loginProcess() throws SQLException {
        boolean inputMatchCheck = false;
        makeConnection();

        String sql = "select id, pw from employeedb.admin";
        Statement stmt = makeConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            String adminId = rs.getString(1);
            String adminPassword = rs.getString(2);

            if (idTextField.getText().equals(adminId) && passwordTextField.getText().equals(adminPassword)) {
                inputMatchCheck = true;
            }

            if (inputMatchCheck == true) {
                dispose();
                new Test();
            } else {
                new LoginError();
            }
        }
    }

    public Login() {
        setTitle("사원 관리 프로그램");
        setSize(500, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

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

        add(labelPanel);
        add(idPanel);
        add(passwordPanel);
        add(buttonPanel);
    }

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                try {
                    loginProcess();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (e.getSource() == cancelButton) {
                dispose();
            }
        }
    }
}
