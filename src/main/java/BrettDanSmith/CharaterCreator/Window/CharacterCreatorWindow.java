package BrettDanSmith.CharaterCreator.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import BrettDanSmith.CharaterCreator.App;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class CharacterCreatorWindow extends JFrame {
	private static final long serialVersionUID = -3735779624246420526L;
	private JPanel contentPane;
	private JPanel imagePanel;
	private JTextField textField;

	public CharacterCreatorWindow() {
		setTitle("Character Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 800);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuBar_File = new JMenu("File");
		menuBar.add(menuBar_File);

		JMenuItem menuBar_File_Exit = new JMenuItem("Exit");
		menuBar_File_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App.getApp().exit();
			}
		});
		menuBar_File.add(menuBar_File_Exit);
		contentPane = new JPanel();
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JSplitPane splitPane = new JSplitPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, splitPane, 200, SpringLayout.NORTH, contentPane);
		splitPane.setResizeWeight(0.25);
		sl_contentPane.putConstraint(SpringLayout.WEST, splitPane, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, splitPane, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, splitPane, 0, SpringLayout.EAST, contentPane);
		contentPane.add(splitPane);

		imagePanel = new JPanel() {
			private static final long serialVersionUID = -1048390171807949833L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.black);
				g.fillRect(0, 0, imagePanel.getWidth(), imagePanel.getHeight());
			}

		};
		splitPane.setRightComponent(imagePanel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel layersPanel = new JPanel();

		splitPane.setLeftComponent(layersPanel);
		SpringLayout sl_layersPanel = new SpringLayout();
		sl_layersPanel.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, layersPanel);
		sl_layersPanel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, layersPanel);
		sl_layersPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -20, SpringLayout.SOUTH, layersPanel);
		sl_layersPanel.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, layersPanel);
		layersPanel.setLayout(sl_layersPanel);
		layersPanel.add(scrollPane);

		JLabel lblLayers = new JLabel("Layers");
		lblLayers.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblLayers);

		JTree tree = new JTree();
		tree.setVisibleRowCount(200);
		tree.setShowsRootHandles(true);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Character") {
			{
				DefaultMutableTreeNode node_1;
				add(new DefaultMutableTreeNode("Background"));
				node_1 = new DefaultMutableTreeNode("Head");
				node_1.add(new DefaultMutableTreeNode("Base"));
				node_1.add(new DefaultMutableTreeNode("Hat"));
				node_1.add(new DefaultMutableTreeNode("Eyes"));
				node_1.add(new DefaultMutableTreeNode("Mouth"));
				node_1.add(new DefaultMutableTreeNode("Ears"));
				node_1.add(new DefaultMutableTreeNode("Nose"));
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Body");
				node_1.add(new DefaultMutableTreeNode("Base"));
				node_1.add(new DefaultMutableTreeNode("Clothing"));
				node_1.add(new DefaultMutableTreeNode("Shoes"));
				node_1.add(new DefaultMutableTreeNode("Accessories"));
				add(node_1);
			}
		}));
		scrollPane.setViewportView(tree);

		JButton btnAddLayer = new JButton("+");
		btnAddLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddLayerDialog(tree).setVisible(true);
			}
		});
		sl_layersPanel.putConstraint(SpringLayout.NORTH, btnAddLayer, 0, SpringLayout.SOUTH, scrollPane);
		sl_layersPanel.putConstraint(SpringLayout.WEST, btnAddLayer, -20, SpringLayout.EAST, layersPanel);
		sl_layersPanel.putConstraint(SpringLayout.SOUTH, btnAddLayer, 0, SpringLayout.SOUTH, layersPanel);
		sl_layersPanel.putConstraint(SpringLayout.EAST, btnAddLayer, 0, SpringLayout.EAST, layersPanel);
		layersPanel.add(btnAddLayer);

		JButton btnRemoveLayer = new JButton("-");
		btnRemoveLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DefaultTreeModel) tree.getModel())
						.removeNodeFromParent((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
			}
		});
		sl_layersPanel.putConstraint(SpringLayout.NORTH, btnRemoveLayer, 0, SpringLayout.NORTH, btnAddLayer);
		sl_layersPanel.putConstraint(SpringLayout.WEST, btnRemoveLayer, -25, SpringLayout.WEST, btnAddLayer);
		sl_layersPanel.putConstraint(SpringLayout.SOUTH, btnRemoveLayer, 0, SpringLayout.SOUTH, btnAddLayer);
		sl_layersPanel.putConstraint(SpringLayout.EAST, btnRemoveLayer, -5, SpringLayout.WEST, btnAddLayer);
		layersPanel.add(btnRemoveLayer);

		JLabel lblIterations = new JLabel("Iterations: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblIterations, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIterations, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblIterations, 25, SpringLayout.NORTH, contentPane);
		lblIterations.setToolTipText("Amount of generated images.");
		contentPane.add(lblIterations);

		JSpinner spinner = new JSpinner();
		spinner.setToolTipText("Amount of generated images.");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, spinner, 0, SpringLayout.SOUTH, lblIterations);
		spinner.setModel(new SpinnerNumberModel(1, 1, 9999, 1));
		sl_contentPane.putConstraint(SpringLayout.NORTH, spinner, 0, SpringLayout.NORTH, lblIterations);
		sl_contentPane.putConstraint(SpringLayout.WEST, spinner, 5, SpringLayout.EAST, lblIterations);
		sl_contentPane.putConstraint(SpringLayout.EAST, spinner, 55, SpringLayout.EAST, lblIterations);
		contentPane.add(spinner);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		sl_contentPane.putConstraint(SpringLayout.WEST, progressBar, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, progressBar, -5, SpringLayout.NORTH, splitPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, progressBar, -5, SpringLayout.EAST, contentPane);
		contentPane.add(progressBar);
		
		JButton btnNewButton = new JButton("Start");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, -5, SpringLayout.NORTH, progressBar);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -5, SpringLayout.EAST, contentPane);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, -5, SpringLayout.NORTH, progressBar);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Browse");
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -5, SpringLayout.WEST, btnNewButton_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_1, -5, SpringLayout.NORTH, progressBar);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_1, -5, SpringLayout.WEST, btnNewButton);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Export Directory: ");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblIterations);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -5, SpringLayout.NORTH, textField);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}
}
