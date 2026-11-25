package emp.util;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Image;
import java.sql.SQLException;

public final class UiUtil {
    private UiUtil() {
    }

    public static void installLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
    }

    public static Image appImage() {
        return ImageUtil.appIcon().getImage();
    }

    public static void error(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "오류", JOptionPane.ERROR_MESSAGE);
    }

    public static void info(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component parent, String message, String title) {
        return JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public static void databaseError(Component parent, SQLException exception) {
        error(parent, "데이터베이스 작업 중 오류가 발생했습니다.\n" + exception.getMessage());
    }
}
