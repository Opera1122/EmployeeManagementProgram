package emp.ui;

import emp.dao.AdminDAO;
import emp.util.UiUtil;
import emp.util.Validation;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private final AdminDAO adminDAO = new AdminDAO();
    private final JTextField idField = new JTextField(16);
    private final JPasswordField passwordField = new JPasswordField(16);

    public LoginFrame() {
        setTitle("사원 관리 프로그램");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(UiUtil.appImage());

        JLabel title = new JLabel("<html><div style='text-align:center'><h1>로그인</h1>이용하시려면 관리자 계정을 로그인 하세요.</div></html>", SwingConstants.CENTER);
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        titlePanel.add(title, BorderLayout.CENTER);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(6, 6, 6, 6);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        form.add(new JLabel("ID"), constraints);
        constraints.gridx = 1;
        form.add(idField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        form.add(new JLabel("PW"), constraints);
        constraints.gridx = 1;
        passwordField.setEchoChar('*');
        form.add(passwordField, constraints);

        JButton loginButton = new JButton("로그인");
        JButton exitButton = new JButton("종료");
        loginButton.addActionListener(event -> login());
        exitButton.addActionListener(event -> System.exit(0));
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        buttons.add(loginButton);
        buttons.add(exitButton);

        JPanel content = new JPanel(new BorderLayout(0, 16));
        content.setBorder(BorderFactory.createEmptyBorder(24, 40, 24, 40));
        content.add(titlePanel, BorderLayout.NORTH);
        content.add(form, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);
        setContentPane(content);
        getRootPane().setDefaultButton(loginButton);
        pack();
        setSize(Math.max(getWidth(), 480), Math.max(getHeight(), 320));
        setLocationRelativeTo(null);
    }

    private void login() {
        String id = idField.getText().trim();
        String password = String.valueOf(passwordField.getPassword());
        if (Validation.isBlank(id) || Validation.isBlank(password)) {
            UiUtil.error(this, "아이디와 비밀번호를 입력하세요.");
            return;
        }
        try {
            if (adminDAO.findByCredentials(id, password).isEmpty()) {
                UiUtil.error(this, "아이디 또는 비밀번호를 다시 확인하세요.");
                passwordField.setText("");
                passwordField.requestFocusInWindow();
                return;
            }
            dispose();
            new MainFrame().setVisible(true);
        } catch (SQLException e) {
            UiUtil.databaseError(this, e);
        }
    }
}
