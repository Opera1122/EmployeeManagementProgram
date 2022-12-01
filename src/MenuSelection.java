package src;

import src.jdbc.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class MenuSelection extends JFrame {
    JMenuBar topMenuBar = new JMenuBar();
    JMenu topMenufile = new JMenu("파일");
    JMenuItem fileLogOut = new JMenuItem("로그아웃");
    JMenuItem fileExit = new JMenuItem("종료");
    JMenu topMenuHelp = new JMenu("도움말");
    JMenuItem helpInfo = new JMenuItem("정보");
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image iconImage = toolkit.getImage("images/icon/em.png");

    JTabbedPane topTab = new JTabbedPane(JTabbedPane.TOP);

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
    JLabel infoTelLabel = new JLabel("휴대전화");
    JTextField infoTelTextField = new JTextField(10);
    JLabel infoPositionLabel = new JLabel("직위");
    JTextField infoPositionTextField = new JTextField(20);
    JPanel infoImagePanel = new JPanel();
    JButton infoImportImageButton = new JButton("불러오기");
    JFileChooser infofileChooser = new JFileChooser();
    JButton infoDeleteImageButton = new JButton("삭제");
    JButton editButton = new JButton("수정");
    JButton deleteButton = new JButton("삭제");

    String numberAndName[] = {"번호", "이름"};
    JComboBox numberAndNameComboBox = new JComboBox(numberAndName);
    JTextField searchTextField = new JTextField(10);
    JButton searchButton = new JButton("조회");
    ImageIcon unConvertedRefreshIcon = new ImageIcon("images/button/refresh.png");
    Image unConvertedRefreshImage = unConvertedRefreshIcon.getImage();
    Image ConvertedRefreshImage = unConvertedRefreshImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon refreshIcon = new ImageIcon(ConvertedRefreshImage);
    JButton refreshButton = new JButton(refreshIcon);
    DefaultTableModel model = new DefaultTableModel(numberAndName, 0);
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    JPanel signUpMenuPanel = new JPanel();
    JLabel signUpNameLabel = new JLabel("이름");
    JTextField signUpNameTextField = new JTextField(10);
    JLabel signUpBirthDateLabel = new JLabel("생년월일");
    JTextField signUpBirthDateTextField = new JTextField(10);
    JLabel signUpAddressLabel = new JLabel("주소");
    JTextField signUpAddressTextField = new JTextField(20);
    JLabel signUpEmailLabel = new JLabel("이메일");
    JTextField signUpEmailTextField = new JTextField(20);
    JLabel signUpTelLabel = new JLabel("휴대전화");
    JTextField signUpTelTextField = new JTextField(10);
    JLabel signUpPositionLabel = new JLabel("직위");
    JTextField signUpPositionTextField = new JTextField(20);
    JPanel signUpImagePanel = new JPanel();
    JButton signUpImportImageButton = new JButton("불러오기");
    JFileChooser signUpfileChooser = new JFileChooser();
    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("이미지 파일(*.jpg;*.jpeg;*.png)", "jpg", "jpeg", "png");
    JButton signUpDeleteImageButton = new JButton("삭제");

    JButton signUpButton = new JButton("등록");
    JButton resetButton = new JButton("다시 작성");

    void clearInfoContents() {
        infoNumberTextField.setText(null);
        infoNameTextField.setText(null);
        infoBirthDateTextField.setText(null);
        infoAddressTextField.setText(null);
        infoEmailTextField.setText(null);
        infoTelTextField.setText(null);
        infoPositionTextField.setText(null);
    }
    void clearSignUpContents() {
        signUpNameTextField.setText(null);
        signUpBirthDateTextField.setText(null);
        signUpAddressTextField.setText(null);
        signUpEmailTextField.setText(null);
        signUpTelTextField.setText(null);
        signUpPositionTextField.setText(null);
    }

    public MenuSelection() {
        setTitle("사원 관리 프로그램");
        setSize(700, 640);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setJMenuBar(topMenuBar);
        setIconImage(iconImage);

        topMenuBar.add(topMenufile);
        topMenufile.add(fileLogOut);
        fileLogOut.addActionListener(new MyListener());
        topMenufile.add(fileExit);
        fileExit.addActionListener(new MyListener());
        topMenuBar.add(topMenuHelp);
        topMenuHelp.add(helpInfo);
        helpInfo.addActionListener(new MyListener());

        add(topTab);
        topTab.setBounds(40, 20, 600, 520);
        topTab.addTab("사원 정보", infoMenuPanel);
        topTab.addTab("사원 등록", signUpMenuPanel);

        infoMenuPanel.setLayout(null);
        signUpMenuPanel.setLayout(null);

        int signUpX = 50;
        int signUpY = 30;
        int signUpTextFieldMarginLeft = 70;
        int signUpmarginBottom = 50;
        int labelWidth = 60;
        int labelHeight = 20;
        int textFieldWidth = 150;
        int textFieldHeight = 25;

        //사원 정보
        infoMenuPanel.add(infoImagePanel);
        infoImagePanel.setBounds(40, 10, 150, 150);
        infoImagePanel.setBorder(new LineBorder(Color.BLACK));

        infoMenuPanel.add(infoImportImageButton);
        infoImportImageButton.setBounds(20, 170, 90, 30);
        infoImportImageButton.addActionListener(new MyListener());

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

        infoMenuPanel.add(infoTelLabel);
        infoTelLabel.setBounds(20, 370, labelWidth, labelHeight);
        infoMenuPanel.add(infoTelTextField);
        infoTelTextField.setBounds(80, 370, textFieldWidth, textFieldHeight);

        infoMenuPanel.add(infoPositionLabel);
        infoPositionLabel.setBounds(20, 400, labelWidth, labelHeight);
        infoMenuPanel.add(infoPositionTextField);
        infoPositionTextField.setBounds(80, 400, textFieldWidth, textFieldHeight);

        infoMenuPanel.add(editButton);
        editButton.setBounds(20, 440, 100, 30);
        editButton.addActionListener(new MyListener());

        infoMenuPanel.add(deleteButton);
        deleteButton.setBounds(130, 440, 100, 30);
        deleteButton.addActionListener(new MyListener());

        //테이블 부분
        infoMenuPanel.add(numberAndNameComboBox);
        numberAndNameComboBox.addActionListener(new MyListener());
        numberAndNameComboBox.setBounds(250, 20, 70, 30);
        infoMenuPanel.add(searchTextField);
        searchTextField.setBounds(330, 20, 120, 30);
        infoMenuPanel.add(searchButton);
        searchButton.addActionListener(new MyListener());
        searchButton.setBounds(460, 20, 70, 30);
        infoMenuPanel.add(refreshButton);
        refreshButton.addActionListener(new MyListener());
        refreshButton.setBounds(540, 20, 30, 30);
        table.addMouseListener(new MyMouseListener());
        infoMenuPanel.add(scrollPane);
        scrollPane.setBounds(250, 60, 322, 410);
        try {
            EmployeeDAO.ShowNumberAndNameTable(model);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //사원 등록
        signUpMenuPanel.add(signUpNameLabel);
        signUpNameLabel.setBounds(signUpX, signUpY, labelWidth, labelHeight);
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

        signUpMenuPanel.add(signUpTelLabel);
        signUpTelLabel.setBounds(signUpX, signUpY += signUpmarginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpTelTextField);
        signUpTelTextField.setBounds(signUpX + signUpTextFieldMarginLeft, signUpY, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpPositionLabel);
        signUpPositionLabel.setBounds(signUpX, signUpY += signUpmarginBottom, labelWidth, labelHeight);
        signUpMenuPanel.add(signUpPositionTextField);
        signUpPositionTextField.setBounds(signUpX + signUpTextFieldMarginLeft, signUpY, textFieldWidth, textFieldHeight);

        signUpMenuPanel.add(signUpImagePanel);
        signUpImagePanel.setBounds(400, 28, 150, 150);
        signUpImagePanel.setBorder(new LineBorder(Color.BLACK));

        signUpMenuPanel.add(signUpImportImageButton);
        signUpImportImageButton.setBounds(380, 190, 90, 30);
        signUpImportImageButton.addActionListener(new MyListener());

        signUpMenuPanel.add(signUpDeleteImageButton);
        signUpDeleteImageButton.setBounds(480, 190, 90, 30);
        signUpDeleteImageButton.addActionListener(new MyListener());

        signUpMenuPanel.add(signUpButton);
        signUpButton.setBounds(200, 350, 100, 40);
        signUpButton.addActionListener(new MyListener());

        signUpMenuPanel.add(resetButton);
        resetButton.setBounds(315, 350, 100, 40);
        resetButton.addActionListener(new MyListener());
    }

    class MyListener implements ActionListener {
        int selectedRow = 0;
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

            if (e.getSource() == infoImportImageButton) {
                infofileChooser.addChoosableFileFilter(fileFilter);
                infofileChooser.showOpenDialog(null);
            }
            if (e.getSource() == editButton) {
                EmployeeDAO.EditEmployee(
                        table,
                        selectedRow,
                        model,
                        infoNameTextField.getText(),
                        Integer.parseInt(infoBirthDateTextField.getText()),
                        infoAddressTextField.getText(),
                        infoEmailTextField.getText(),
                        infoTelTextField.getText(),
                        infoPositionTextField.getText(),
                        Integer.parseInt(infoNumberTextField.getText())
                );
                model.setNumRows(0);
                try {
                    EmployeeDAO.ShowNumberAndNameTable(model);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                clearInfoContents();
                JOptionPane.showMessageDialog(null, "사원 정보가 수정되었습니다.", "수정 성공", JOptionPane.INFORMATION_MESSAGE);
            }

            if (e.getSource() == deleteButton) {
                EmployeeDAO.DeleteEmployee(table, selectedRow, model);
                model.setNumRows(0);
                try {
                    EmployeeDAO.ShowNumberAndNameTable(model);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                clearInfoContents();
                JOptionPane.showMessageDialog(null, "사원 정보가 삭제되었습니다.", "삭제 성공", JOptionPane.INFORMATION_MESSAGE);
            }

            if (e.getSource() == signUpImportImageButton) {
                signUpfileChooser.addChoosableFileFilter(fileFilter);
                signUpfileChooser.showOpenDialog(null);
            }

            if (e.getSource() == searchButton) {
                try {
                    if (numberAndNameComboBox.getSelectedItem().equals("번호")) {
                        model.setNumRows(0);
                        EmployeeDAO.SearchEmployeeNumber(model, Integer.parseInt(searchTextField.getText()));
                    } else if (numberAndNameComboBox.getSelectedItem().equals("이름")) {
                        model.setNumRows(0);
                        EmployeeDAO.SearchEmployeeName(model, searchTextField.getText());
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "잘못된 형식입니다.", "조회 실패", JOptionPane.ERROR_MESSAGE);
                    model.setNumRows(0);
                    try {
                        EmployeeDAO.ShowNumberAndNameTable(model);
                    } catch (SQLException exc) {
                        throw new RuntimeException(exc);
                    }
                }
            }

            if (e.getSource() == refreshButton) {
                model.setNumRows(0);
                clearInfoContents();
                try {
                    EmployeeDAO.ShowNumberAndNameTable(model);
                } catch (SQLException exc) {
                    throw new RuntimeException(exc);
                }
            }

            if (e.getSource() == signUpButton) {
                try {
                    EmployeeDAO.SignUpEmployee(
                            signUpNameTextField.getText(),
                            Integer.parseInt(signUpBirthDateTextField.getText()),
                            signUpAddressTextField.getText(),
                            signUpEmailTextField.getText(),
                            signUpTelTextField.getText(),
                            signUpPositionTextField.getText()
                    );
                    model.setNumRows(0);
                    EmployeeDAO.ShowNumberAndNameTable(model);
                    clearSignUpContents();
                    clearInfoContents();
                    JOptionPane.showMessageDialog(null, "사원 정보가 등록되었습니다.", "등록 성공", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "입력 정보를 다시 확인하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == resetButton) {
                clearSignUpContents();
            }

        }
    }

    class MyMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            table = (JTable)e.getSource();
            int selectedRow = table.getSelectedRow();

            try {
                EmployeeDAO.ShowListToTextFields(
                        table,
                        selectedRow,
                        model,
                        infoNumberTextField,
                        infoNameTextField,
                        infoBirthDateTextField,
                        infoAddressTextField,
                        infoEmailTextField,
                        infoTelTextField,
                        infoPositionTextField
                );
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
        public void mouseEntered(java.awt.event.MouseEvent e) {
        }
        public void mouseExited(java.awt.event.MouseEvent e) {
        }
        public void mousePressed(java.awt.event.MouseEvent e) {
        }
        public void mouseReleased(java.awt.event.MouseEvent e) {
        }
    }
}
