package presentation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class FotoCellEditor extends AbstractCellEditor implements TableCellEditor {

	private JButton button = new JButton();
	ImageIcon photoCell;

	public FotoCellEditor() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent actionEvent) {
				JFileChooser fcOpen = new JFileChooser();

				int v = fcOpen.showDialog(button, "Load photo");

				if (v == JFileChooser.APPROVE_OPTION) {
					File file = fcOpen.getSelectedFile();
					changePhoto(new ImageIcon(file.getAbsolutePath()));
				}
			}
		});
	}

	public void changePhoto(ImageIcon imag) {
		if (imag != null) {
			photoCell = imag;
			button.setIcon(imag);
		}
	}

	@Override
	public Object getCellEditorValue() {
		return photoCell;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {
		changePhoto((ImageIcon)value);
		return button;
	}

}
