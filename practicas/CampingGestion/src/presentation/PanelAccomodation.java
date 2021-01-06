package presentation;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpinnerDateModel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.event.TreeSelectionListener;
import javax.swing.text.MaskFormatter;
import javax.swing.event.TreeSelectionEvent;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;

public class PanelAccomodation extends MainPanel {
	
	private JTree tree;

	private JTextField txtNode = new JTextField();
	private ManagerTable table;

	/**
	 * Create the panel.
	 */
	public PanelAccomodation() {
		table = new ManagerTable(new ReservationTableModel());

		tools = new JButton[4];
		tools[0] = new JButton("New reservation");
		tools[0].addActionListener(table.new AddRowActionListener());
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/add.png")), toolBarImageSize, toolBarImageSize));

		tools[1] = new JButton("New object");
		tools[1].addActionListener(new NewNodeActionListener());
		tools[1].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/add-tab.png")), toolBarImageSize, toolBarImageSize));

		tools[2] = new JButton("Delete reservation");
		tools[2].addActionListener(table.new DeleteRowActionListener());
		tools[2].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/delete-bin.png")), toolBarImageSize, toolBarImageSize));
		
		tools[3] = new JButton("Delete object");
		tools[3].addActionListener(new DeleteNodeActionListener());
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
		tree.setMinimumSize(new Dimension(20, 0));
		tree.setPreferredSize(new Dimension(150, 64));
		tree.addTreeSelectionListener(new TreeTreeSelectionListener());
		tree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tree.setEditable(true);
		tree.setModel(treeModel);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);;
		tree.setCellRenderer(new CampingTreeCellRenderer());
		splitPane.setLeftComponent(tree);

		txtNode.setBounds(0, 0, 200,30);
		txtNode.setVisible(false);
		txtNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if(!txtNode.getText().equals(""))
					addObject(txtNode.getText());

				txtNode.setText("");
				txtNode.setVisible(false);
			}
		});
		tree.add(txtNode);
		
		JSplitPane subSplit = new JSplitPane();
		subSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(subSplit);
		
		JScrollPane sReservations = new JScrollPane();
		sReservations.setPreferredSize(new Dimension(2, 230));
		subSplit.setLeftComponent(sReservations);
		
		JPanel pnlDetails = new JPanel();
		subSplit.setRightComponent(pnlDetails);
		pnlDetails.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTags = new JLabel("NAMETAGS");
		pnlDetails.add(lblTags);
		
		JLabel lblStatus = new JLabel("Status");
		pnlDetails.add(lblStatus);
		
		JComboBox<EnumObjectStates> cmbStatus = new JComboBox<>();
		cmbStatus.setModel(new DefaultComboBoxModel<>(EnumObjectStates.values()));
		lblStatus.setLabelFor(cmbStatus);
		pnlDetails.add(cmbStatus);
		
		JLabel lblPrice = new JLabel("Price/night");
		pnlDetails.add(lblPrice);
		
		JSpinner spPrice = new JSpinner();
		spPrice.setModel(new SpinnerNumberModel(0.0f, 0.0f, null, 0.1f));
		lblPrice.setLabelFor(spPrice);
		pnlDetails.add(spPrice);
		
		JTextArea txtFeatures = new JTextArea();
		pnlDetails.add(txtFeatures);
		
		JScrollPane sGallery = new JScrollPane();
		pnlDetails.add(sGallery);
		
		try {
			MaskFormatter formatTel;
			formatTel = new MaskFormatter("'(###')' ###' ###' ###");
			formatTel.setPlaceholderCharacter('*');
			table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JFormattedTextField(formatTel)));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JSpinner _spDate = new JSpinner();
		_spDate.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
		JSpinner _spCapacity = new JSpinner();
		_spCapacity.setModel(new SpinnerNumberModel(10, 0, null, 1));
		JSpinner _spPrice = new JSpinner();
		_spPrice.setModel(new SpinnerNumberModel(0.0f, 0.0f, null, 0.1f));

		table.getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor(_spDate));
		table.getColumnModel().getColumn(1).setCellRenderer(new DateCellRenderer());
		table.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor(_spDate));
		table.getColumnModel().getColumn(2).setCellRenderer(new DateCellRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(new SpinnerEditor(_spCapacity));
		table.getColumnModel().getColumn(6).setCellEditor(new SpinnerEditor(_spPrice));

		sReservations.setViewportView(table);
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}

	private class TreeTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent arg0) {
			getMain().log("Selected node: " + arg0.getPath());
		}
	}

	public DefaultMutableTreeNode addObject(Object child) {
	    DefaultMutableTreeNode parentNode = null;
	    TreePath parentPath = tree.getSelectionPath();

	    if (parentPath == null) {
	    	// Default to root node
	        parentNode = (DefaultMutableTreeNode) (tree.getModel().getRoot());
	    } else {
	        parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
	    }

	    return addObject(parentNode, child, true);
	}
	
	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
		((DefaultTreeModel) tree.getModel()).insertNodeInto(childNode, parent, parent.getChildCount());

		// Make sure the user can see the lovely new node.
		if (shouldBeVisible)
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));

		return childNode;
	}

	private class NewNodeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			txtNode.setBounds(0, 0, 200,30);
			txtNode.setVisible(true);
			txtNode.requestFocus();
		}
	}

	private class DeleteNodeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			DefaultMutableTreeNode parentNode = null;
			TreePath parentPath = tree.getSelectionPath();
			if (parentPath == null) {
				getMain().log("Cannot delete object. Please select a node!");
				return;
			}

			int v = JOptionPane.showConfirmDialog(getMain().getFrame(),
					"Are you sure you want to remove "+parentPath.getLastPathComponent().toString()+" object and all its reservations?",
					"Delete object", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (v == JOptionPane.YES_OPTION) {
					parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
					if (parentNode != (DefaultMutableTreeNode) (tree.getModel().getRoot()))
						((DefaultTreeModel) tree.getModel()).removeNodeFromParent(parentNode);
					else
						getMain().log("ERROR: trying to remove root node");
			}
		}
	}
}
