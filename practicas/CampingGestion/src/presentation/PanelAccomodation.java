package presentation;

import java.util.Locale;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.JPanel;

public class PanelAccomodation extends MainPanel {

	private static JButton[] tools;

	/**
	 * Create the panel.
	 */
	public PanelAccomodation() {
		tools = new JButton[4];
		tools[2] = new JButton("New reservation");
		tools[1] = new JButton("New object");
		tools[0] = new JButton("New category");
		tools[3] = new JButton("Delete");
		// TODO
		//tools[0].addActionListener(new LogoutActionListener());
		//tools[0].setIcon(new ImageIcon(PanelAccount.class.getResource("/presentation/flags/polish.png")));

		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);
		
		JTree tree = new JTree();
		splitPane.setLeftComponent(tree);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
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
