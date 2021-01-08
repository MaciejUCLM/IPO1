package presentation;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class MainPanel extends JPanel implements IMainPanel {

	protected JButton[] tools;

	protected static final int toolBarImageSize = 24;
	
	private static IAppWindow main;
	protected static IAppWindow getMain() {
		if (main == null)
			main = IController.getController().getWindow(EnumWindows.MAIN);
		return main;
	}

	@Override
	public JButton[] getToolBarButtons() {
		return tools;
	}

}
