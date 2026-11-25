package emp.ui;

import emp.dao.EmployeeDAO;
import emp.model.Employee;
import emp.util.ImageUtil;
import emp.util.UiUtil;
import emp.util.Validation;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
    private static final String[] TABLE_COLUMNS = {"번호", "이름"};

    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final PhotoPanel infoPhotoPanel = new PhotoPanel();
    private final PhotoPanel signUpPhotoPanel = new PhotoPanel();
    private final JTextField infoNumberField = new JTextField();
    private final JTextField infoNameField = new JTextField();
    private final JTextField infoBirthDateField = new JTextField();
    private final JTextField infoAddressField = new JTextField();
    private final JTextField infoEmailField = new JTextField();
    private final JTextField infoTelField = new JTextField();
    private final JTextField infoPositionField = new JTextField();
    private final JTextField signUpNameField = new JTextField();
    private final JTextField signUpBirthDateField = new JTextField();
    private final JTextField signUpAddressField = new JTextField();
    private final JTextField signUpEmailField = new JTextField();
    private final JTextField signUpTelField = new JTextField();
    private final JTextField signUpPositionField = new JTextField();
    private final JComboBox<String> searchTypeBox = new JComboBox<>(new DefaultComboBoxModel<>(TABLE_COLUMNS));
    private final JTextField searchField = new JTextField(10);
    private final DefaultTableModel tableModel = new DefaultTableModel(TABLE_COLUMNS, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(tableModel);
    private Integer selectedNumber;

    public MainFrame() {
        setTitle("사원 관리 프로그램");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setIconImage(UiUtil.appImage());
        setJMenuBar(createMenuBar());

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("사원 정보", createInfoTab());
        tabs.addTab("사원 등록", createSignUpTab());

        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        content.add(tabs, BorderLayout.CENTER);
        setContentPane(content);

        infoNumberField.setEditable(false);
        infoBirthDateField.setToolTipText("예: 19900211");
        signUpBirthDateField.setToolTipText("예: 19900211");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(22);
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadSelectedEmployee();
            }
        });

        reloadAllEmployees();
        pack();
        setSize(720, 640);
        setLocationRelativeTo(null);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("파일");
        JMenuItem logout = new JMenuItem("로그아웃");
        JMenuItem exit = new JMenuItem("종료");
        logout.addActionListener(event -> logout());
        exit.addActionListener(event -> System.exit(0));
        fileMenu.add(logout);
        fileMenu.add(exit);

        JMenu helpMenu = new JMenu("도움말");
        JMenuItem info = new JMenuItem("정보");
        info.addActionListener(event -> new InformationDialog(this).setVisible(true));
        helpMenu.add(info);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        return menuBar;
    }

    private JPanel createInfoTab() {
        JPanel left = new JPanel(new BorderLayout(0, 8));
        left.add(createPhotoButtons(infoPhotoPanel, true), BorderLayout.NORTH);
        left.add(createForm(
                new Field("번호", infoNumberField),
                new Field("이름", infoNameField),
                new Field("생년월일", infoBirthDateField),
                new Field("주소", infoAddressField),
                new Field("이메일", infoEmailField),
                new Field("휴대전화", infoTelField),
                new Field("직위", infoPositionField)
        ), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        JButton editButton = new JButton("수정");
        JButton deleteButton = new JButton("삭제");
        editButton.addActionListener(event -> editEmployee());
        deleteButton.addActionListener(event -> deleteEmployee());
        actions.add(editButton);
        actions.add(deleteButton);
        left.add(actions, BorderLayout.SOUTH);

        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        JButton searchButton = new JButton("조회");
        JButton refreshButton = new JButton(ImageUtil.refreshIcon(18));
        refreshButton.setToolTipText("새로고침");
        searchButton.addActionListener(event -> searchEmployees());
        refreshButton.addActionListener(event -> {
            searchField.setText("");
            clearInfoForm();
            reloadAllEmployees();
        });
        search.add(searchTypeBox);
        search.add(searchField);
        search.add(searchButton);
        search.add(refreshButton);

        JPanel right = new JPanel(new BorderLayout(0, 8));
        right.add(search, BorderLayout.NORTH);
        right.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel tab = new JPanel(new BorderLayout(16, 0));
        tab.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tab.add(left, BorderLayout.WEST);
        tab.add(right, BorderLayout.CENTER);
        return tab;
    }

    private JPanel createSignUpTab() {
        JPanel form = createForm(
                new Field("이름", signUpNameField),
                new Field("생년월일", signUpBirthDateField),
                new Field("주소", signUpAddressField),
                new Field("이메일", signUpEmailField),
                new Field("휴대전화", signUpTelField),
                new Field("직위", signUpPositionField)
        );

        JPanel photo = createPhotoButtons(signUpPhotoPanel, false);
        JPanel top = new JPanel(new BorderLayout(24, 0));
        top.add(form, BorderLayout.CENTER);
        top.add(photo, BorderLayout.EAST);

        JButton signUpButton = new JButton("등록");
        JButton resetButton = new JButton("다시 작성");
        signUpButton.addActionListener(event -> signUpEmployee());
        resetButton.addActionListener(event -> clearSignUpForm());
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        actions.add(signUpButton);
        actions.add(resetButton);

        JPanel tab = new JPanel(new BorderLayout(0, 24));
        tab.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        tab.add(top, BorderLayout.NORTH);
        tab.add(actions, BorderLayout.SOUTH);
        return tab;
    }

    private JPanel createPhotoButtons(PhotoPanel photoPanel, boolean infoTab) {
        JButton importButton = new JButton("불러오기");
        JButton deleteButton = new JButton("삭제");
        importButton.setToolTipText(infoTab ? "선택한 뒤 수정을 눌러 저장합니다." : "등록 시 함께 저장됩니다.");
        deleteButton.setToolTipText(infoTab ? "미리보기에서 제거한 뒤 수정을 눌러 저장합니다." : "선택한 이미지를 취소합니다.");
        importButton.addActionListener(event -> {
            if (infoTab && selectedNumber == null) {
                UiUtil.error(this, "이미지를 변경할 사원을 먼저 선택하세요.");
                return;
            }
            photoPanel.chooseFromFile(this);
        });
        deleteButton.addActionListener(event -> {
            if (infoTab && selectedNumber == null) {
                UiUtil.error(this, "이미지를 삭제할 사원을 먼저 선택하세요.");
                return;
            }
            photoPanel.clearImage();
        });

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        buttons.add(importButton);
        buttons.add(deleteButton);

        JPanel wrapper = new JPanel(new BorderLayout(0, 8));
        JPanel photoHolder = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        photoHolder.add(photoPanel);
        wrapper.add(photoHolder, BorderLayout.CENTER);
        wrapper.add(buttons, BorderLayout.SOUTH);
        wrapper.setPreferredSize(new Dimension(220, 210));
        return wrapper;
    }

    private JPanel createForm(Field... fields) {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.WEST;
        labelConstraints.insets = new Insets(6, 4, 6, 8);
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        fieldConstraints.weightx = 1;
        fieldConstraints.insets = new Insets(6, 0, 6, 4);
        for (int i = 0; i < fields.length; i++) {
            labelConstraints.gridx = 0;
            labelConstraints.gridy = i;
            fieldConstraints.gridx = 1;
            fieldConstraints.gridy = i;
            form.add(new JLabel(fields[i].label), labelConstraints);
            fields[i].field.setColumns(14);
            form.add(fields[i].field, fieldConstraints);
        }
        return form;
    }

    private void loadSelectedEmployee() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            return;
        }
        int number = (Integer) tableModel.getValueAt(viewRow, 0);
        try {
            employeeDAO.findByNumber(number).ifPresentOrElse(this::fillInfoForm, () -> {
                UiUtil.error(this, "선택한 사원 정보를 찾을 수 없습니다.");
                reloadAllEmployees();
            });
        } catch (SQLException e) {
            UiUtil.databaseError(this, e);
        }
    }

    private void fillInfoForm(Employee employee) {
        selectedNumber = employee.getNumber();
        infoNumberField.setText(String.valueOf(employee.getNumber()));
        infoNameField.setText(employee.getName());
        infoBirthDateField.setText(String.valueOf(employee.getBirthDate()));
        infoAddressField.setText(employee.getAddress());
        infoEmailField.setText(employee.getEmail());
        infoTelField.setText(employee.getTel());
        infoPositionField.setText(employee.getPosition());
        infoPhotoPanel.setImageBytes(employee.getImage());
    }

    private void editEmployee() {
        if (selectedNumber == null) {
            UiUtil.error(this, "수정할 사원을 선택하세요.");
            return;
        }
        Employee employee = readInfoForm();
        if (employee == null) {
            return;
        }
        try {
            if (!employeeDAO.update(employee)) {
                UiUtil.error(this, "사원 정보 수정에 실패했습니다.");
                return;
            }
            reloadAllEmployees();
            employeeDAO.findByNumber(selectedNumber).ifPresent(this::fillInfoForm);
            selectRowByNumber(selectedNumber);
            UiUtil.info(this, "사원 정보가 수정되었습니다.", "수정 성공");
        } catch (SQLException e) {
            UiUtil.databaseError(this, e);
        }
    }

    private void deleteEmployee() {
        if (selectedNumber == null) {
            UiUtil.error(this, "삭제할 사원을 선택하세요.");
            return;
        }
        if (!UiUtil.confirm(this, "선택한 사원 정보를 삭제할까요?", "삭제 확인")) {
            return;
        }
        try {
            if (!employeeDAO.delete(selectedNumber)) {
                UiUtil.error(this, "사원 정보 삭제에 실패했습니다.");
                return;
            }
            clearInfoForm();
            reloadAllEmployees();
            UiUtil.info(this, "사원 정보가 삭제되었습니다.", "삭제 성공");
        } catch (SQLException e) {
            UiUtil.databaseError(this, e);
        }
    }

    private void signUpEmployee() {
        String error = Validation.validateEmployee(
                signUpNameField.getText(),
                signUpBirthDateField.getText(),
                signUpAddressField.getText(),
                signUpEmailField.getText(),
                signUpTelField.getText(),
                signUpPositionField.getText()
        );
        if (error != null) {
            UiUtil.error(this, error);
            return;
        }
        Employee employee = new Employee();
        employee.setName(signUpNameField.getText().trim());
        employee.setBirthDate(Validation.parseBirthDate(signUpBirthDateField.getText()));
        employee.setAddress(signUpAddressField.getText().trim());
        employee.setEmail(signUpEmailField.getText().trim());
        employee.setTel(signUpTelField.getText().trim());
        employee.setPosition(signUpPositionField.getText().trim());
        employee.setImage(signUpPhotoPanel.getImageBytes());
        try {
            int number = employeeDAO.insert(employee);
            clearSignUpForm();
            reloadAllEmployees();
            selectRowByNumber(number);
            loadSelectedEmployee();
            UiUtil.info(this, "사원 정보가 등록되었습니다.", "등록 성공");
        } catch (SQLException e) {
            UiUtil.databaseError(this, e);
        }
    }

    private void searchEmployees() {
        String keyword = searchField.getText().trim();
        if (Validation.isBlank(keyword)) {
            UiUtil.error(this, "조회할 값을 입력하세요.");
            return;
        }
        try {
            List<Employee> result;
            if ("번호".equals(searchTypeBox.getSelectedItem())) {
                try {
                    result = employeeDAO.searchByNumber(Integer.parseInt(keyword));
                } catch (NumberFormatException e) {
                    UiUtil.error(this, "번호는 숫자로 입력하세요.");
                    return;
                }
            } else {
                result = employeeDAO.searchByName(keyword);
            }
            fillTable(result);
            clearInfoForm();
            if (result.isEmpty()) {
                UiUtil.info(this, "조회 결과가 없습니다.", "조회");
            }
        } catch (SQLException e) {
            UiUtil.databaseError(this, e);
        }
    }

    private Employee readInfoForm() {
        String error = Validation.validateEmployee(
                infoNameField.getText(),
                infoBirthDateField.getText(),
                infoAddressField.getText(),
                infoEmailField.getText(),
                infoTelField.getText(),
                infoPositionField.getText()
        );
        if (error != null) {
            UiUtil.error(this, error);
            return null;
        }
        Employee employee = new Employee();
        employee.setNumber(selectedNumber);
        employee.setName(infoNameField.getText().trim());
        employee.setBirthDate(Validation.parseBirthDate(infoBirthDateField.getText()));
        employee.setAddress(infoAddressField.getText().trim());
        employee.setEmail(infoEmailField.getText().trim());
        employee.setTel(infoTelField.getText().trim());
        employee.setPosition(infoPositionField.getText().trim());
        employee.setImage(infoPhotoPanel.getImageBytes());
        return employee;
    }

    private void reloadAllEmployees() {
        try {
            fillTable(employeeDAO.findAllSummaries());
        } catch (SQLException e) {
            UiUtil.databaseError(this, e);
        }
    }

    private void fillTable(List<Employee> employees) {
        tableModel.setRowCount(0);
        for (Employee employee : employees) {
            tableModel.addRow(new Object[]{employee.getNumber(), employee.getName()});
        }
    }

    private void selectRowByNumber(int number) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (number == (Integer) tableModel.getValueAt(i, 0)) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(table.getCellRect(i, 0, true));
                return;
            }
        }
    }

    private void clearInfoForm() {
        selectedNumber = null;
        table.clearSelection();
        infoNumberField.setText("");
        infoNameField.setText("");
        infoBirthDateField.setText("");
        infoAddressField.setText("");
        infoEmailField.setText("");
        infoTelField.setText("");
        infoPositionField.setText("");
        infoPhotoPanel.clearImage();
    }

    private void clearSignUpForm() {
        signUpNameField.setText("");
        signUpBirthDateField.setText("");
        signUpAddressField.setText("");
        signUpEmailField.setText("");
        signUpTelField.setText("");
        signUpPositionField.setText("");
        signUpPhotoPanel.clearImage();
    }

    private void logout() {
        dispose();
        new LoginFrame().setVisible(true);
    }

    private static final class Field {
        private final String label;
        private final JTextField field;

        private Field(String label, JTextField field) {
            this.label = label;
            this.field = field;
        }
    }
}
