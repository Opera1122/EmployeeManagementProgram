package emp;

import emp.db.SchemaInitializer;
import emp.ui.LoginFrame;
import emp.util.UiUtil;

import javax.swing.SwingUtilities;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UiUtil.installLookAndFeel();
            try {
                SchemaInitializer.ensureSchema();
            } catch (SQLException e) {
                UiUtil.error(null, "데이터베이스에 연결할 수 없습니다.\nMySQL이 실행 중인지, config/db.properties 설정을 확인하세요.\n"
                        + e.getMessage());
                System.exit(1);
                return;
            }
            new LoginFrame().setVisible(true);
        });
    }
}
