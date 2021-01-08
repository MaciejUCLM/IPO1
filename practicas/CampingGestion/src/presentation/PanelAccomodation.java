package presentation;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.JPopupMenu;
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
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.awt.event.ItemEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.border.TitledBorder;
import javax.swing.ScrollPaneConstants;

public class PanelAccomodation extends MainPanel {
	
	private static final int galleryImageSize = 92;
	
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
	private JPanel pnlGallery;

	/**
	 * Create the panel.
	 */
	public PanelAccomodation() {
		table = new ManagerTable(new ReservationTableModel());

		tools = new JButton[4];
		tools[0] = new JButton(Messages.getString("PanelAccomodation.0")); //$NON-NLS-1$
		tools[0].addActionListener(table.new AddRowActionListener());
		tools[0].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/add.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		tools[1] = new JButton(Messages.getString("PanelAccomodation.2")); //$NON-NLS-1$
		tools[1].addActionListener(new NewNodeActionListener());
		tools[1].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/add-tab.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		tools[2] = new JButton(Messages.getString("PanelAccomodation.4")); //$NON-NLS-1$
		tools[2].addActionListener(table.new DeleteRowActionListener());
		tools[2].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/delete-bin.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$
		
		tools[3] = new JButton(Messages.getString("PanelAccomodation.6")); //$NON-NLS-1$
		tools[3].addActionListener(new DeleteNodeActionListener());
		tools[3].setIcon(IAppWindow.resizeImage(new ImageIcon(MainWindow.class.getResource("/presentation/resources/close-tab.png")), toolBarImageSize, toolBarImageSize)); //$NON-NLS-1$

		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		tree = new JTree();
		tree.setMinimumSize(new Dimension(20, 0));
		tree.setPreferredSize(new Dimension(150, 64));
		tree.addTreeSelectionListener(new TreeTreeSelectionListener());
		tree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tree.setEditable(true);
		selectedNode = new AccomodationTreeNode("Camping") { //$NON-NLS-1$
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new AccomodationTreeNode(Messages.getString("PanelAccomodation.9")); //$NON-NLS-1$
						node_2 = new AccomodationTreeNode("Deluxe"); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("A")); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("B")); //$NON-NLS-1$
						node_1.add(node_2);
						node_2 = new AccomodationTreeNode("Standard"); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("A")); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("B")); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("C")); //$NON-NLS-1$
						node_1.add(node_2);
					add(node_1);
					node_1 = new AccomodationTreeNode(Messages.getString("PanelAccomodation.17")); //$NON-NLS-1$
						node_2 = new AccomodationTreeNode(Messages.getString("PanelAccomodation.18")); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("A")); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("B")); //$NON-NLS-1$
						node_1.add(node_2);
						node_2 = new AccomodationTreeNode(Messages.getString("PanelAccomodation.21")); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("A")); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("B")); //$NON-NLS-1$
						node_1.add(node_2);
						node_2 = new AccomodationTreeNode(Messages.getString("PanelAccomodation.24")); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("A")); //$NON-NLS-1$
						node_1.add(node_2);
						node_2 = new AccomodationTreeNode(Messages.getString("PanelAccomodation.26")); //$NON-NLS-1$
							node_2.add(new AccomodationTreeNode("A")); //$NON-NLS-1$
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
				if(!txtNode.getText().equals("")) //$NON-NLS-1$
					addObject(txtNode.getText());

				txtNode.setText(""); //$NON-NLS-1$
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
		
		lblTags = new JLabel("NAMETAGS"); //$NON-NLS-1$
		GridBagConstraints gbc_lblTags = new GridBagConstraints();
		gbc_lblTags.insets = new Insets(0, 0, 5, 5);
		gbc_lblTags.gridx = 0;
		gbc_lblTags.gridy = 0;
		pnlDetails.add(lblTags, gbc_lblTags);
		
		lblFeatures = new JLabel(Messages.getString("PanelAccomodation.lblFeatures.text")); //$NON-NLS-1$
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
		sGallery.setBorder(new TitledBorder(null, Messages.getString("PanelAccomodation.sGallery.borderTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null)); //$NON-NLS-1$
		detailSplit.setRightComponent(sGallery);
		
		sText = new JScrollPane();
		sText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		detailSplit.setLeftComponent(sText);
		
		txtFeatures = new JTextArea();
		txtFeatures.setWrapStyleWord(true);
		txtFeatures.addCaretListener(new TxtFeaturesCaretListener());
		txtFeatures.setTabSize(4);
		txtFeatures.setLineWrap(true);
		sText.setViewportView(txtFeatures);
		
		lblStatus = new JLabel(Messages.getString("PanelAccomodation.lblStatus.text")); //$NON-NLS-1$
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
		
		lblPrice = new JLabel(Messages.getString("PanelAccomodation.lblPrice.text")); //$NON-NLS-1$
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
		
		((ReservationTableModel)table.getModel()).setCurrentTags(new String[] {"Camping"}); //$NON-NLS-1$
		
		try {
			MaskFormatter formatTel;
			formatTel = new MaskFormatter("'(###')' ###' ###' ###"); //$NON-NLS-1$
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
		
		pnlGallery = new JPanel();
		sGallery.setViewportView(pnlGallery);

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem mntmAdd = new JMenuItem(Messages.getString("PanelAccomodation.mntmAdd.text_2")); //$NON-NLS-1$
		mntmAdd.addActionListener(table.new AddRowActionListener());
		popupMenu.add(mntmAdd);
		JMenuItem mntmRemove = new JMenuItem(Messages.getString("PanelAccomodation.mntmRemove.text_2")); //$NON-NLS-1$
		mntmRemove.addActionListener(table.new DeleteRowActionListener());
		popupMenu.add(mntmRemove);
		IAppWindow.addPopup(table, popupMenu);
		IAppWindow.addPopup(sReservations, popupMenu);

		popupMenu = new JPopupMenu();
		mntmAdd = new JMenuItem(Messages.getString("PanelAccomodation.mntmAdd.text")); //$NON-NLS-1$
		mntmAdd.addActionListener(new NewNodeActionListener());
		popupMenu.add(mntmAdd);
		mntmRemove = new JMenuItem(Messages.getString("PanelAccomodation.mntmRemove.text")); //$NON-NLS-1$
		mntmRemove.addActionListener(new DeleteNodeActionListener());
		popupMenu.add(mntmRemove);
		IAppWindow.addPopup(tree, popupMenu);
		
		popupMenu = new JPopupMenu();
		mntmAdd = new JMenuItem(Messages.getString("PanelAccomodation.mntmAdd.text_3")); //$NON-NLS-1$
		mntmAdd.addActionListener(new AddToGalleryActionListener());
		popupMenu.add(mntmAdd);
		IAppWindow.addPopup(sGallery, popupMenu);
		
		updateDetails();

		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				ManagerTable tab = table;
				int row = tab.getSelectedRow();
				if (tab.getLength() > 0 && row >= 0) {
					Date start = (Date) tab.getValueAt(row, 1);
					Date finish = (Date) tab.getValueAt(row, 2);
					if (start.after(finish))
						tab.setValueAt(start, row, 2);
				}
			}
		});
	}
	
	public void updateDetails() {
		AccomodationTreeNode sn = getSelectedNode();
		String[] ts = sn.getTags();
		if (ts.length > 0) {
			String nameTags = ""; //$NON-NLS-1$
			for (int i = ts.length - 1; i >= 0; i--)
				nameTags += ts[i] + ","; //$NON-NLS-1$
			lblTags.setText(nameTags.substring(0, nameTags.length() - 1));
		} else
			lblTags.setText(Messages.getString("PanelAccomodation.35")); //$NON-NLS-1$

		txtFeatures.setText(sn.getFeatures());
		cmbStatus.setSelectedItem(sn.getState());
		spPrice.setValue(sn.getPrice());

		pnlGallery.removeAll();
		for (ImageIcon ic : sn.getImages())
			pnlGallery.add(galleryFactory(ic));
		sGallery.repaint();
	}

	@Override
	public void onLocaleChange() {
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
			getMain().log(Messages.getString("PanelAccomodation.36") + Arrays.stream(tags).collect(Collectors.joining(", "))); //$NON-NLS-1$ //$NON-NLS-2$
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
	
	private JLabel galleryFactory(ImageIcon ic) {
		JLabel lbl = new JLabel(""); //$NON-NLS-1$
		lbl.setIcon(IAppWindow.resizeImage(ic, galleryImageSize, galleryImageSize));
		
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem mntmShow = new JMenuItem(Messages.getString("PanelAccomodation.39")); //$NON-NLS-1$
		mntmShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ImageWindow.openImagePreview(ic);
				getMain().log(Messages.getString("PanelAccomodation.40")); //$NON-NLS-1$
			}
		});
		popupMenu.add(mntmShow);
		JMenuItem mntmAdd = new JMenuItem(Messages.getString("PanelAccomodation.41")); //$NON-NLS-1$
		mntmAdd.addActionListener(new AddToGalleryActionListener());
		popupMenu.add(mntmAdd);
		JMenuItem mntmRemove = new JMenuItem(Messages.getString("PanelAccomodation.42")); //$NON-NLS-1$
		mntmRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getSelectedNode().removeImage(ic);
				updateDetails();
				getMain().log(Messages.getString("PanelAccomodation.43") + getSelectedNode().toString()); //$NON-NLS-1$
			}
		});
		popupMenu.add(mntmRemove);
		IAppWindow.addPopup(lbl, popupMenu);

		return lbl;
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
				getMain().log(Messages.getString("PanelAccomodation.44")); //$NON-NLS-1$
				return;
			}

			int v = JOptionPane.showConfirmDialog(getMain().getFrame(),
					Messages.getString("PanelAccomodation.45")+parentPath.getLastPathComponent().toString()+Messages.getString("PanelAccomodation.46"), //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("PanelAccomodation.47"), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$

			if (v == JOptionPane.YES_OPTION) {
				parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
				if (parentNode != (DefaultMutableTreeNode) (tree.getModel().getRoot()))
					((DefaultTreeModel) tree.getModel()).removeNodeFromParent(parentNode);
				else {
					getMain().log(Messages.getString("PanelAccomodation.48")); //$NON-NLS-1$
					JOptionPane.showMessageDialog(getMain().getFrame(), Messages.getString("PanelAccomodation.49"), Messages.getString("PanelAccomodation.50"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
	}
	
	private class AddToGalleryActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fc = new JFileChooser();
			int v = fc.showOpenDialog(getMain().getFrame());

			if (v == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				getSelectedNode().addImage(new ImageIcon(file.getAbsolutePath()));
				getMain().log(Messages.getString("PanelAccomodation.51")); //$NON-NLS-1$
				updateDetails();
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
