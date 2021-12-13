package BrettDanSmith.CharaterCreator.Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

public class AddLayerDialog extends JDialog {
	private static final long serialVersionUID = -3687130270768491181L;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUntitledlayer;
	private JTextField textField;

	public AddLayerDialog(JTree tree) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Add Layer");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Name: ");
			lblNewLabel.setBounds(5, 5, 45, 20);
			contentPanel.add(lblNewLabel);
		}
		{
			txtUntitledlayer = new JTextField();
			txtUntitledlayer.setText("Untitled-Layer");
			txtUntitledlayer.setBounds(55, 5, 375, 20);
			contentPanel.add(txtUntitledlayer);
			txtUntitledlayer.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Type: ");
			lblNewLabel_1.setBounds(5, 30, 45, 20);
			contentPanel.add(lblNewLabel_1);
		}

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Image", "Text", "Action", "AI-Generated" }));
		comboBox.setBounds(55, 30, 375, 20);
		contentPanel.add(comboBox);
		{
			JPanel panelImage = new JPanel();
			panelImage.setBounds(5, 60, 425, 160);
			contentPanel.add(panelImage);
			panelImage.setLayout(null);
			{
				textField = new JTextField();
				textField.setBounds(0, 140, 340, 20);
				panelImage.add(textField);
				textField.setColumns(10);
			}
			{
				JButton btnNewButton = new JButton("Browse");
				btnNewButton.setBounds(345, 140, 80, 20);
				panelImage.add(btnNewButton);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						((DefaultMutableTreeNode) tree.getLastSelectedPathComponent())
								.add(new DefaultMutableTreeNode(txtUntitledlayer.getText().strip()));
						tree.revalidate();
						dispose();
					}
				});

				JLabel lblNewLabel_2 = new JLabel("ERROR_TEXT");
				lblNewLabel_2.setEnabled(false);
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNewLabel_2.setForeground(Color.RED);
				buttonPane.add(lblNewLabel_2);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
