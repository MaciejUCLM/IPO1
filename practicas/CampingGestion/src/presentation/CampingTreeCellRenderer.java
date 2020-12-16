package presentation;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class CampingTreeCellRenderer extends DefaultTreeCellRenderer {

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
		String c = (String)(nodo.getUserObject());

		// TODO
		switch (c)
		{
		case "Camping":
			//setIcon(new ImageIcon(CampingTreeCellRenderer.class.getResource("")));
			break;
		default:
			//setIcon(new ImageIcon(CampingTreeCellRenderer.class.getResource("")));
			break;
		}

		return this;
	}

}
