package presentation;

import java.util.Locale;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class PanelManager extends MainPanel {

	private static JButton[] tools;

	/**
	 * Create the panel.
	 */
	public PanelManager() {
		tools = new JButton[2];
		tools[0] = new JButton("Add");
		tools[1] = new JButton("Delete");
		// TODO
		//tools[0].addActionListener(new LogoutActionListener());
		//tools[0].setIcon(new ImageIcon(PanelAccount.class.getResource("/presentation/flags/polish.png")));

		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public JButton[] getToolBarButtons() {
		return tools;
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}

}
