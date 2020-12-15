package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
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
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/add.png")), toolBarImageSize, toolBarImageSize));

		tools[1] = new JButton("Delete");
		tools[1].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/delete-bin.png")), toolBarImageSize, toolBarImageSize));
		// TODO
		//tools[0].addActionListener(new LogoutActionListener());

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
