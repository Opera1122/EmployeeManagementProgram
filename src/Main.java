package src;

import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        setTitle("사원 관리 프로그램");
        setSize(750, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}