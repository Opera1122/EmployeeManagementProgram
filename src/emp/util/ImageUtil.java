package emp.util;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class ImageUtil {
    public static final int PHOTO_SIZE = 150;
    public static final long MAX_IMAGE_BYTES = 5L * 1024 * 1024;

    private ImageUtil() {
    }

    public static byte[] readImageFile(File file) throws IOException {
        if (file.length() > MAX_IMAGE_BYTES) {
            throw new IOException("이미지 파일은 5MB 이하여야 합니다.");
        }
        byte[] bytes = Files.readAllBytes(file.toPath());
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
        if (image == null) {
            throw new IOException("지원하지 않는 이미지 형식입니다.");
        }
        return bytes;
    }

    public static ImageIcon toFittedIcon(byte[] bytes, int maxWidth, int maxHeight) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            BufferedImage source = ImageIO.read(new ByteArrayInputStream(bytes));
            if (source == null) {
                return null;
            }
            return new ImageIcon(scaleToFit(source, maxWidth, maxHeight));
        } catch (IOException e) {
            return null;
        }
    }

    public static ImageIcon placeholderIcon(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        enableQuality(g);
        g.setColor(new Color(236, 239, 241));
        g.fillRect(0, 0, size, size);
        g.setColor(new Color(176, 190, 197));
        int head = size / 4;
        int bodyW = size / 2;
        g.fillOval((size - head) / 2, size / 6, head, head);
        g.fillRoundRect((size - bodyW) / 2, size / 2, bodyW, size / 2, 24, 24);
        g.setColor(new Color(120, 144, 156));
        g.setFont(new Font("Dialog", Font.PLAIN, Math.max(11, size / 12)));
        String caption = "사진 없음";
        int textWidth = g.getFontMetrics().stringWidth(caption);
        g.drawString(caption, (size - textWidth) / 2, size - 10);
        g.dispose();
        return new ImageIcon(image);
    }

    public static ImageIcon appIcon() {
        int size = 64;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        enableQuality(g);
        g.setColor(new Color(25, 118, 210));
        g.fillRoundRect(2, 2, size - 4, size - 4, 16, 16);
        g.setColor(Color.WHITE);
        g.fillOval(22, 12, 20, 20);
        g.fillRoundRect(16, 34, 32, 22, 12, 12);
        g.dispose();
        return new ImageIcon(image);
    }

    public static ImageIcon refreshIcon(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        enableQuality(g);
        g.setColor(new Color(25, 118, 210));
        g.setStroke(new BasicStroke(Math.max(2f, size / 10f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.draw(new Ellipse2D.Float(4, 4, size - 12, size - 12));
        int[] x = {size - 10, size - 2, size - 14};
        int[] y = {6, 10, 16};
        g.fillPolygon(x, y, 3);
        g.dispose();
        return new ImageIcon(image);
    }

    private static BufferedImage scaleToFit(BufferedImage source, int maxWidth, int maxHeight) {
        double scale = Math.min(maxWidth / (double) source.getWidth(), maxHeight / (double) source.getHeight());
        int width = Math.max(1, (int) Math.round(source.getWidth() * scale));
        int height = Math.max(1, (int) Math.round(source.getHeight() * scale));
        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaled.createGraphics();
        enableQuality(g);
        g.drawImage(source, 0, 0, width, height, null);
        g.dispose();
        return scaled;
    }

    private static void enableQuality(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }
}
