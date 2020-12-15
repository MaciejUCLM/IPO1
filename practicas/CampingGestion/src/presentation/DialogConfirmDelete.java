package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DialogConfirmDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public DialogConfirmDelete() {
		setTitle("Delete entry");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 170);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblPhoto = new JLabel("ICON");
			contentPanel.add(lblPhoto);
		}
		{
			JLabel lblMessage = new JLabel("Are you sure you want to delete this entry?");
			contentPanel.add(lblMessage);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnYes = new JButton("Yes");
				btnYes.setActionCommand("OK");
				buttonPane.add(btnYes);
				getRootPane().setDefaultButton(btnYes);
			}
			{
				JButton btnNo = new JButton("No");
				btnNo.setActionCommand("Cancel");
				buttonPane.add(btnNo);
			}
		}
	}

}
