package emp.ui;

import emp.util.UiUtil;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class InformationDialog extends JDialog {
    public InformationDialog(JFrame owner) {
        super(owner, "프로그램 정보", true);
        setResizable(false);
        setIconImage(UiUtil.appImage());

        JPanel body = new JPanel(new BorderLayout(0, 8));
        body.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));
        body.add(new JLabel("제작자 : Opera1122", SwingConstants.CENTER), BorderLayout.NORTH);
        body.add(new JLabel("https://github.com/Opera1122", SwingConstants.CENTER), BorderLayout.CENTER);
        body.add(new JLabel("(C) 2022. Opera1122 all rights reserved.", SwingConstants.CENTER), BorderLayout.SOUTH);

        JButton close = new JButton("확인");
        close.addActionListener(event -> dispose());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttons.add(close);

        JPanel content = new JPanel(new BorderLayout(0, 10));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        content.add(body, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);
        setContentPane(content);
        getRootPane().setDefaultButton(close);
        pack();
        setSize(Math.max(getWidth(), 380), Math.max(getHeight(), 200));
        setLocationRelativeTo(owner);
    }
}
