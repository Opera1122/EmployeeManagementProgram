package emp.ui;

import emp.util.ImageUtil;
import emp.util.UiUtil;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

public class PhotoPanel extends JPanel {
    private final JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
    private final JFileChooser fileChooser = new JFileChooser();
    private byte[] imageBytes;

    public PhotoPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(ImageUtil.PHOTO_SIZE, ImageUtil.PHOTO_SIZE));
        setMinimumSize(getPreferredSize());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("이미지 파일(*.jpg;*.jpeg;*.png)", "jpg", "jpeg", "png"));
        clearImage();
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
        if (imageBytes == null || imageBytes.length == 0) {
            showPlaceholder();
            return;
        }
        imageLabel.setText(null);
        imageLabel.setIcon(ImageUtil.toFittedIcon(imageBytes, ImageUtil.PHOTO_SIZE - 8, ImageUtil.PHOTO_SIZE - 8));
    }

    public void clearImage() {
        this.imageBytes = null;
        showPlaceholder();
    }

    public boolean chooseFromFile(Component parent) {
        if (fileChooser.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION) {
            return false;
        }
        try {
            setImageBytes(ImageUtil.readImageFile(fileChooser.getSelectedFile()));
            return true;
        } catch (IOException e) {
            UiUtil.error(parent, e.getMessage());
            return false;
        }
    }

    private void showPlaceholder() {
        imageLabel.setIcon(ImageUtil.placeholderIcon(ImageUtil.PHOTO_SIZE - 8));
        imageLabel.setText(null);
    }
}
