package showroom.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ImageRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        JLabel label = new JLabel();

        if (value instanceof ImageIcon) {
            label.setIcon((ImageIcon) value);
        } else {
            label.setText("Không ảnh");
            label.setHorizontalAlignment(JLabel.CENTER);
        }

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        if (isSelected) {
            label.setBackground(table.getSelectionBackground());
            label.setOpaque(true);
        } else {
            label.setOpaque(false);
        }

        return label;
    }
}
