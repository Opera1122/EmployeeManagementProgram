package src;

import src.jdbc.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenuSelection extends JFrame {
    JMenuBar topMenuBar = new JMenuBar();
    JMenu topMenufile = new JMenu("파일");
    JMenuItem fileLogOut = new JMenuItem("로그아웃");
    JMenuItem fileExit = new JMenuItem("종료");
    JMenu topMenuHelp = new JMenu("도움말");
    JMenuItem helpInfo = new JMenuItem("정보");

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
    JLabel signUpPositionLabel = new JLabel("직위");
    JTextField signUpPositionTextField = new JTextField(20);
    JPanel signUpImagePanel = new JPanel();
    JButton signUpImportImageButton = new JButton("불러오기");
    JButton signUpDeleteImageButton = new JButton("삭제");

    JButton signUpButton = new JButton("등록");
    JButton resetButton = new JButton("다시 작성");

    JPanel infoMenuPanel = new JPanel();
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
    JLabel infoPositionLabel = new JLabel("직위");
    JTextField infoPositionTextField = new JTextField(20);
    JPanel infoImagePanel = new JPanel();
    JButton infoImportImageButton = new JButton("불러오기");
    JButton infoDeleteImageButton = new JButton("삭제");
    JButton editButton = new JButton("수정");
    JButton deleteButton = new JButton("삭제");

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
        setJMenuBar(topMenuBar);

        topMenuBar.add(topMenufile);
        topMenufile.add(fileLogOut);
        fileLogOut.addActionListener(new MyListener());
        topMenufile.add(fileExit);
        fileExit.addActionListener(new MyListener());
        topMenuBar.add(topMenuHelp);
        topMenuHelp.add(helpInfo);
        helpInfo.addActionListener(new MyListener());

        add(topButtonPanel);
        add(signUpMenuPanel);
        add(infoMenuPanel);

        infoMenuPanel.setVisible(true);
        signUpMenuPanel.setVisible(false);

        topButtonPanel.setBounds(30, 10, 200, 35);
        signUpMenuPanel.setBounds(40, 50, 600, 450);
        signUpMenuPanel.setBorder(new LineBorder(Color.BLACK));
        infoMenuPanel.setBounds(40, 50, 600, 450);
        infoMenuPanel.setBorder(new LineBorder(Color.BLACK));

        signUpMenuPanel.setLayout(null);
        infoMenuPanel.setLayout(null);

        topButtonPanel.add(signUpMenuButton);
        topButtonPanel.add(infoMenuButton);
        signUpMenuButton.addActionListener(new MyListener());
        infoMenuButton.addActionListener(new MyListener());

        int signUpX = 50;
        int signUpY = 30;
        int signUpTextFieldMarginLeft = 70;
        int signUpmarginBottom = 50;
        int labelWidth = 60;
        int labelHeight = 20;
        int textFieldWidth = 150;
        int textFieldHeight = 25;

        //사원 등록
        signUpMenuPanel.add(signUpNumberLabel);
        signUpNumberLabel.setBounds(signUpX, signUpY, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpNumberTextField);
        signUpNumberTextField.setBounds(signUpX + signUpTextFieldMarginLeft, signUpY, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpNameLabel);
        signUpNameLabel.setBounds(signUpX, signUpY += signUpmarginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpNameTextField);
        signUpNameTextField.setBounds(signUpX + signUpTextFieldMarginLeft, signUpY, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpBirthDateLabel);
        signUpBirthDateLabel.setBounds(signUpX, signUpY += signUpmarginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpBirthDateTextField);
        signUpBirthDateTextField.setBounds(signUpX + signUpTextFieldMarginLeft, signUpY, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpAddressLabel);
        signUpAddressLabel.setBounds(signUpX, signUpY += signUpmarginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpAddressTextField);
        signUpAddressTextField.setBounds(signUpX + signUpTextFieldMarginLeft, signUpY, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpEmailLabel);
        signUpEmailLabel.setBounds(signUpX, signUpY += signUpmarginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpEmailTextField);
        signUpEmailTextField.setBounds(signUpX + signUpTextFieldMarginLeft, signUpY, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpPositionLabel);
        signUpPositionLabel.setBounds(signUpX, signUpY += signUpmarginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpPositionTextField);
        signUpPositionTextField.setBounds(signUpX + signUpTextFieldMarginLeft, signUpY, textFieldWidth, textFieldHeight);

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

        //사원 정보
        infoMenuPanel.add(infoImagePanel);
        infoImagePanel.setBounds(40, 10, 150, 150);
        infoImagePanel.setBorder(new LineBorder(Color.BLACK));
        infoMenuPanel.add(infoImportImageButton);
        infoImportImageButton.setBounds(20, 170, 90, 30);
        infoMenuPanel.add(infoDeleteImageButton);
        infoDeleteImageButton.setBounds(120, 170, 90, 30);

        infoMenuPanel.add(infoNumberLabel);
        infoNumberLabel.setBounds(20, 220, labelWidth, labelHeight);
        infoMenuPanel.add(infoNumberTextField);
        infoNumberTextField.setBounds(80, 220, textFieldWidth, textFieldHeight);
        infoMenuPanel.add(infoNameLabel);
        infoNameLabel.setBounds(20, 250, labelWidth, labelHeight);
        infoMenuPanel.add(infoNameTextField);
        infoNameTextField.setBounds(80, 250, textFieldWidth, textFieldHeight);
        infoMenuPanel.add(infoBirthDateLabel);
        infoBirthDateLabel.setBounds(20, 280, labelWidth, labelHeight);
        infoMenuPanel.add(infoBirthDateTextField);
        infoBirthDateTextField.setBounds(80, 280, textFieldWidth, textFieldHeight);
        infoMenuPanel.add(infoAddressLabel);
        infoAddressLabel.setBounds(20, 310, labelWidth, labelHeight);
        infoMenuPanel.add(infoAddressTextField);
        infoAddressTextField.setBounds(80, 310, textFieldWidth, textFieldHeight);
        infoMenuPanel.add(infoEmailLabel);
        infoEmailLabel.setBounds(20, 340, labelWidth, labelHeight);
        infoMenuPanel.add(infoEmailTextField);
        infoEmailTextField.setBounds(80, 340, textFieldWidth, textFieldHeight);
        infoMenuPanel.add(infoPositionLabel);
        infoPositionLabel.setBounds(20, 370, labelWidth, labelHeight);
        infoMenuPanel.add(infoPositionTextField);
        infoPositionTextField.setBounds(80, 370, textFieldWidth, textFieldHeight);

        infoMenuPanel.add(editButton);
        editButton.setBounds(20, 405, 100, 30);
        editButton.addActionListener(new MyListener());
        infoMenuPanel.add(deleteButton);
        deleteButton.setBounds(130, 405, 100, 30);
        deleteButton.addActionListener(new MyListener());

    }

    class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == fileLogOut) {
                new Login();
                dispose();
            } else if (e.getSource() == fileExit) {
                dispose();
            }

            if (e.getSource() == helpInfo) {
                new Information();
            }

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
