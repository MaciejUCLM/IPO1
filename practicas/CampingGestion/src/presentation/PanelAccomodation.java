package presentation;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

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
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class PanelAccomodation extends MainPanel {
	
	private AccomodationTreeNode selectedNode;
	
	private JTree tree;
	private JTextField txtNode = new JTextField();
	private ManagerTable table;
	private JLabel lblTags;
	private JLabel lblStatus;
	private JComboBox<EnumObjectStates> cmbStatus;
	private JLabel lblPrice;
	private JSpinner spPrice;
	private JLabel lblFeatures;
	private JSplitPane detailSplit;
	private JScrollPane sGallery;
	private JScrollPane sText;
	private JTextArea txtFeatures;

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

		tree = new JTree();
		tree.setMinimumSize(new Dimension(20, 0));
		tree.setPreferredSize(new Dimension(150, 64));
		tree.addTreeSelectionListener(new TreeTreeSelectionListener());
		tree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tree.setEditable(true);
		selectedNode = new AccomodationTreeNode("Camping") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new AccomodationTreeNode("Bungalows");
						node_2 = new AccomodationTreeNode("Deluxe");
							node_2.add(new AccomodationTreeNode("A"));
							node_2.add(new AccomodationTreeNode("B"));
						node_1.add(node_2);
						node_2 = new AccomodationTreeNode("Standard");
							node_2.add(new AccomodationTreeNode("A"));
							node_2.add(new AccomodationTreeNode("B"));
							node_2.add(new AccomodationTreeNode("C"));
						node_1.add(node_2);
					add(node_1);
					node_1 = new AccomodationTreeNode("Fields");
						node_2 = new AccomodationTreeNode("Small");
							node_2.add(new AccomodationTreeNode("A"));
							node_2.add(new AccomodationTreeNode("B"));
						node_1.add(node_2);
						node_2 = new AccomodationTreeNode("Medium");
							node_2.add(new AccomodationTreeNode("A"));
							node_2.add(new AccomodationTreeNode("B"));
						node_1.add(node_2);
						node_2 = new AccomodationTreeNode("Big");
							node_2.add(new AccomodationTreeNode("A"));
						node_1.add(node_2);
						node_2 = new AccomodationTreeNode("Caravan");
							node_2.add(new AccomodationTreeNode("A"));
						node_1.add(node_2);
					add(node_1);
				}
			};
		tree.setModel(new DefaultTreeModel(selectedNode));
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
		sReservations.setPreferredSize(new Dimension(2, 300));
		subSplit.setLeftComponent(sReservations);
		
		JPanel pnlDetails = new JPanel();
		subSplit.setRightComponent(pnlDetails);
		GridBagLayout gbl_pnlDetails = new GridBagLayout();
		gbl_pnlDetails.columnWidths = new int[]{54, 31, 51, 0};
		gbl_pnlDetails.rowHeights = new int[]{20, 0, 0, 0};
		gbl_pnlDetails.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlDetails.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlDetails.setLayout(gbl_pnlDetails);
		
		lblTags = new JLabel("NAMETAGS");
		GridBagConstraints gbc_lblTags = new GridBagConstraints();
		gbc_lblTags.insets = new Insets(0, 0, 5, 5);
		gbc_lblTags.gridx = 0;
		gbc_lblTags.gridy = 0;
		pnlDetails.add(lblTags, gbc_lblTags);
		
		lblFeatures = new JLabel("Features");
		GridBagConstraints gbc_lblFeatures = new GridBagConstraints();
		gbc_lblFeatures.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblFeatures.insets = new Insets(0, 0, 5, 5);
		gbc_lblFeatures.gridx = 1;
		gbc_lblFeatures.gridy = 0;
		pnlDetails.add(lblFeatures, gbc_lblFeatures);
		
		detailSplit = new JSplitPane();
		GridBagConstraints gbc_detailSplit = new GridBagConstraints();
		gbc_detailSplit.gridheight = 3;
		gbc_detailSplit.fill = GridBagConstraints.BOTH;
		gbc_detailSplit.gridx = 2;
		gbc_detailSplit.gridy = 0;
		pnlDetails.add(detailSplit, gbc_detailSplit);
		
		sGallery = new JScrollPane();
		detailSplit.setRightComponent(sGallery);
		
		sText = new JScrollPane();
		detailSplit.setLeftComponent(sText);
		
		txtFeatures = new JTextArea();
		txtFeatures.addCaretListener(new TxtFeaturesCaretListener());
		txtFeatures.setPreferredSize(new Dimension(200, 16));
		txtFeatures.setTabSize(4);
		txtFeatures.setLineWrap(true);
		sText.setViewportView(txtFeatures);
		
		lblStatus = new JLabel("Status");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.EAST;
		gbc_lblStatus.insets = new Insets(0, 0, 0, 5);
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 2;
		pnlDetails.add(lblStatus, gbc_lblStatus);
		
		cmbStatus = new JComboBox<>();
		cmbStatus.addItemListener(new CmbStatusItemListener());
		cmbStatus.setModel(new DefaultComboBoxModel<>(EnumObjectStates.values()));
		GridBagConstraints gbc_cmbStatus = new GridBagConstraints();
		gbc_cmbStatus.anchor = GridBagConstraints.NORTHWEST;
		gbc_cmbStatus.insets = new Insets(0, 0, 0, 5);
		gbc_cmbStatus.gridx = 1;
		gbc_cmbStatus.gridy = 2;
		pnlDetails.add(cmbStatus, gbc_cmbStatus);
		
		lblPrice = new JLabel("Price/night");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 1;
		pnlDetails.add(lblPrice, gbc_lblPrice);
		
		spPrice = new JSpinner();
		spPrice.addChangeListener(new SpPriceChangeListener());
		spPrice.setModel(new SpinnerNumberModel(0.0f, 0.0f, null, 0.1f));
		GridBagConstraints gbc_spPrice = new GridBagConstraints();
		gbc_spPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_spPrice.anchor = GridBagConstraints.NORTH;
		gbc_spPrice.insets = new Insets(0, 0, 5, 5);
		gbc_spPrice.gridx = 1;
		gbc_spPrice.gridy = 1;
		pnlDetails.add(spPrice, gbc_spPrice);
		
		((ReservationTableModel)table.getModel()).setCurrentTags(new String[] {"Camping"});
		
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
		
		updateDetails();
	}
	
	public void updateDetails() {
		AccomodationTreeNode sn = getSelectedNode();
		String[] ts = sn.getTags();
		if (ts.length > 0) {
			String nameTags = "";
			for (int i = ts.length - 1; i >= 0; i--)
				nameTags += ts[i] + ",";
			lblTags.setText(nameTags.substring(0, nameTags.length() - 1));
		} else
			lblTags.setText("no selection");

		txtFeatures.setText(sn.getFeatures());
		cmbStatus.setSelectedItem(sn.getState());
		spPrice.setValue(sn.getPrice());

		sGallery.removeAll();
		for (ImageIcon ic : sn.getImages()) {
			JLabel lb = new JLabel("");
			lb.setIcon(ic);
			sGallery.add(lb);
		}
	}

	@Override
	public void onLocaleChange(Locale rb) {
		// TODO Auto-generated method stub
	}

	private class TreeTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent t) {
			table.clearSelection();
			ReservationTableModel mdl = (ReservationTableModel) table.getModel();
			String[] tags = Arrays.stream(t.getPath().getPath()).map(x -> x.toString()).toArray(String[]::new);
			mdl.setCurrentTags(tags);
			mdl.filter();
			selectedNode = (AccomodationTreeNode) tree.getLastSelectedPathComponent();
			updateDetails();
			getMain().log("Selected node: " + Arrays.stream(tags).collect(Collectors.joining(", ")));
		}
	}

	private DefaultMutableTreeNode addObject(Object child) {
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
	
	private DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new AccomodationTreeNode(child);
		((DefaultTreeModel) tree.getModel()).insertNodeInto(childNode, parent, parent.getChildCount());

		// Make sure the user can see the lovely new node.
		if (shouldBeVisible)
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));

		return childNode;
	}
	
	private AccomodationTreeNode getSelectedNode() {
		if (selectedNode == null)
			selectedNode = (AccomodationTreeNode) tree.getModel().getRoot();
		return selectedNode;
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
					"Are you sure you want to remove "+parentPath.getLastPathComponent().toString()+" object? All reservations will be preserved.",
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

	private class SpPriceChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			getSelectedNode().setPrice((float) spPrice.getValue());
		}
	}
	private class CmbStatusItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			getSelectedNode().setState((EnumObjectStates) cmbStatus.getSelectedItem());
		}
	}
	private class TxtFeaturesCaretListener implements CaretListener {
		public void caretUpdate(CaretEvent arg0) {
			getSelectedNode().setFeatures(txtFeatures.getText());
		}
	}

}
