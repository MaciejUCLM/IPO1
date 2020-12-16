package presentation;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class PanelAccomodation extends MainPanel {

	private static IAppWindow main;
	private static JButton[] tools;
	private JTree tree;

	/**
	 * Create the panel.
	 */
	public PanelAccomodation() {
		tools = new JButton[4];
		tools[0] = new JButton("New reservation");
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/add.png")), toolBarImageSize, toolBarImageSize));

		tools[1] = new JButton("New category");
		tools[1].addActionListener(new NewNodeActionListener());
		tools[1].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/add-tab.png")), toolBarImageSize, toolBarImageSize));

		tools[2] = new JButton("Delete reservation");
		tools[2].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/delete-bin.png")), toolBarImageSize, toolBarImageSize));
		
		tools[3] = new JButton("Delete category");
		tools[3].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/close-tab.png")), toolBarImageSize, toolBarImageSize));

		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);
		
		DefaultTreeModel treeModel = new DefaultTreeModel(
			new DefaultMutableTreeNode("Camping") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Bungalows");
						node_1.add(new DefaultMutableTreeNode("Deluxe"));
						node_1.add(new DefaultMutableTreeNode("Standard"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Fields");
						node_1.add(new DefaultMutableTreeNode("Small"));
						node_1.add(new DefaultMutableTreeNode("Medium"));
						node_1.add(new DefaultMutableTreeNode("Big"));
						node_1.add(new DefaultMutableTreeNode("Caravan"));
					add(node_1);
				}
			}
		);
		
		tree = new JTree();
		tree.addTreeSelectionListener(new TreeTreeSelectionListener());
		tree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tree.setEditable(true);
		tree.setModel(treeModel);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);;
		tree.setCellRenderer(new CampingTreeCellRenderer());
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

	private class TreeTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent arg0) {
			if (main == null)
				main = IAppWindow.getController().getWindow(EnumWindows.MAIN);
			main.log("Selected node: " + arg0.getPath());
		}
	}

	public DefaultMutableTreeNode addObject(Object child) {
	    DefaultMutableTreeNode parentNode = null;
	    TreePath parentPath = tree.getSelectionPath();

	    if (parentPath == null) {
	        //There is no selection. Default to the root node.
	        parentNode = (DefaultMutableTreeNode) (tree.getModel().getRoot());
	    } else {
	        parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
	    }

	    return addObject(parentNode, child, true);
	}
	
	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
		((DefaultTreeModel) tree.getModel()).insertNodeInto(childNode, parent, parent.getChildCount());

		//Make sure the user can see the lovely new node.
		if (shouldBeVisible)
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));

		return childNode;
	}

	private class NewNodeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addObject("Test Object");
		}
	}
}