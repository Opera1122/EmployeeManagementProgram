package src;

import src.jdbc.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenuSelection extends JFrame {
    JPanel topButtonPanel = new JPanel();
    JButton signUpMenuButton = new JButton("사원 등록");
    JButton infoMenuButton = new JButton("사원 정보");

    JPanel signUpMenuPanel = new JPanel();
    JLabel signUpNumberLabel = new JLabel("번호");
    JTextField signUpNumberTextField = new JTextField(10);
    JLabel signUpNameLabel = new JLabel("이름");
    JTextField signUpNameTextField = new JTextField(10);
    JLabel signUpBirthDateLabel = new JLabel("생년월일");
    JTextField signUpBirthDateTextField = new JTextField(10);
    JLabel signUpAddressLabel = new JLabel("주소");
    JTextField signUpAddressTextField = new JTextField(20);
    JLabel signUpEmailLabel = new JLabel("이메일");
    JTextField signUpEmailTextField = new JTextField(20);
    JPanel signUpImagePanel = new JPanel();
    JButton signUpImportImageButton = new JButton("불러오기");
    JButton signUpDeleteImageButton = new JButton("삭제");

    JButton signUpButton = new JButton("등록");
    JButton resetButton = new JButton("다시 작성");

    JPanel infoMenuPanel = new JPanel();
    JPanel infoPanel = new JPanel();
    JLabel infoNumberLabel = new JLabel("번호");
    JTextField infoNumberTextField = new JTextField(10);
    JLabel infoNameLabel = new JLabel("이름");
    JTextField infoNameTextField = new JTextField(10);
    JLabel infoBirthDateLabel = new JLabel("생년월일");
    JTextField infoBirthDateTextField = new JTextField(10);
    JLabel infoAddressLabel = new JLabel("주소");
    JTextField infoAddressTextField = new JTextField(20);
    JLabel infoEmailLabel = new JLabel("이메일");
    JTextField infoEmailTextField = new JTextField(20);
    JPanel infoImagePanel = new JPanel();
    JButton infoImportImageButton = new JButton("불러오기");
    JButton infoDeleteImageButton = new JButton("삭제");

    JButton editButton = new JButton("수정");
    JButton deleteButton = new JButton("삭제");

    JPanel searchPanel = new JPanel();
    String numberAndName[] = {"번호", "이름"};
    String numberAndNames[][] = {{"1", "ㅎㅇ"}, {"2", "ㅂㅇ"}};
    JComboBox numberAndNameComboBox = new JComboBox(numberAndName);
    JTextField searchTextField = new JTextField(10);
    JButton searchButton = new JButton("조회");
    JTable table = new JTable(numberAndNames, numberAndName);

    public MenuSelection() {
        setTitle("사원 관리 프로그램");
        setSize(700, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        add(topButtonPanel);
        add(signUpMenuPanel);
        add(infoMenuPanel);

        topButtonPanel.setBounds(30, 20, 200, 35);
        signUpMenuPanel.setBounds(40, 60, 600, 450);
        signUpMenuPanel.setBorder(new LineBorder(Color.BLACK));
        infoMenuPanel.setBounds(40, 60, 600, 450);
        infoMenuPanel.setBorder(new LineBorder(Color.BLACK));

        signUpMenuPanel.setLayout(null);
        infoMenuPanel.setLayout(null);

        topButtonPanel.add(signUpMenuButton);
        topButtonPanel.add(infoMenuButton);
        signUpMenuButton.addActionListener(new MyListener());
        infoMenuButton.addActionListener(new MyListener());

        int x = 50;
        int y = 30;
        int textFieldMarginLeft = 70;
        int marginBottom = 50;
        int labelWidth = 60;
        int labelHeight = 20;
        int textFieldWidth = 200;
        int textFieldHeight = 25;

        signUpMenuPanel.add(signUpNumberLabel);
        signUpNumberLabel.setBounds(x, y, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpNumberTextField);
        signUpNumberTextField.setBounds(x + textFieldMarginLeft, y, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpNameLabel);
        signUpNameLabel.setBounds(x, y += marginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpNameTextField);
        signUpNameTextField.setBounds(x + textFieldMarginLeft, y, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpBirthDateLabel);
        signUpBirthDateLabel.setBounds(x, y += marginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpBirthDateTextField);
        signUpBirthDateTextField.setBounds(x + textFieldMarginLeft, y, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpAddressLabel);
        signUpAddressLabel.setBounds(x, y += marginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpAddressTextField);
        signUpAddressTextField.setBounds(x + textFieldMarginLeft, y, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpEmailLabel);
        signUpEmailLabel.setBounds(x, y += marginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpEmailTextField);
        signUpEmailTextField.setBounds(x + textFieldMarginLeft, y, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpImagePanel);
        signUpImagePanel.setBounds(400, 28, 150, 150);
        signUpImagePanel.setBorder(new LineBorder(Color.BLACK));
        signUpMenuPanel.add(signUpImportImageButton);
        signUpImportImageButton.setBounds(380, 190, 90, 30);
        signUpMenuPanel.add(signUpDeleteImageButton);
        signUpDeleteImageButton.setBounds(480, 190, 90, 30);

        signUpMenuPanel.add(signUpButton);
        signUpButton.setBounds(200, 350, 100, 40);
        signUpButton.addActionListener(new MyListener());
        signUpMenuPanel.add(resetButton);
        resetButton.setBounds(315, 350, 100, 40);
        resetButton.addActionListener(new MyListener());

        infoMenuPanel.add(infoPanel);
    }

    class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == signUpMenuButton) {
                signUpMenuPanel.setVisible(true);
                infoMenuPanel.setVisible(false);
            } else if (e.getSource() == infoMenuButton) {
                signUpMenuPanel.setVisible(false);
                infoMenuPanel.setVisible(true);
            }

        }
    }
}
