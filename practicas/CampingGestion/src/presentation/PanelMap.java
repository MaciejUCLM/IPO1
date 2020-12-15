package presentation;

import java.util.Locale;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class PanelMap extends MainPanel {

	private static JButton[] tools;

	/**
	 * Create the panel.
	 */
	public PanelMap() {
		tools = new JButton[5];
		tools[0] = new JButton("Load route");
		tools[1] = new JButton("Point");
		tools[2] = new JButton("Line");
		tools[3] = new JButton("Rectangle");
		tools[4] = new JButton("Caption");
		// TODO
		//tools[0].addActionListener(new LogoutActionListener());
		//tools[0].setIcon(new ImageIcon(PanelAccount.class.getResource("/presentation/flags/polish.png")));

		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JLabel lblMap = new JLabel("");
		scrollPane.setViewportView(lblMap);
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
