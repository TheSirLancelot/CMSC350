package project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Filename: Main.java
 * 
 * @author William Weir 
 * Date: Feb 21, 2022 
 * Description: GUI and main method of the project
 */

@SuppressWarnings("serial")
public class Main extends JFrame {

	// class variables
	private JTextField input = new JTextField(20), output = new JTextField(30);
	private static BinaryTree inputTree;

	//main method
	public static void main(String[] args) {
		Main frame = new Main();
		frame.setVisible(true);
	}
	
	// GUI constructor
	public Main() {
		// title
		super("Binary Tree Categorizer");
		
		// Setting some characteristics of the GUI up
		setSize(715, 175);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1));
		
		// creating components
		JComponent[] inputComponents = { new JLabel("Enter Expression"), input };
		JComponent[] outputComponents = { new JLabel("Output"), output };
		JButton[] buttonComponents = { new JButton("Make Tree"), new JButton("Is Balanced?"), new JButton("Is Full?"),
				new JButton("Is Proper?"), new JButton("Height"), new JButton("Nodes"), new JButton("Inorder") };
		makeFlowPanel(inputComponents);
		makeFlowPanel(buttonComponents);
		makeFlowPanel(outputComponents);
		addActionListeners(buttonComponents);
		output.setEditable(false);
		setResizable(false);
	}


	// method to take an array of components and make a flow panel
	private void makeFlowPanel(JComponent[] components) {
		JPanel panel = new JPanel(new FlowLayout());
		for (Component component : components) {
			panel.add(component);
		}
		add(panel);
	}


	// action listener
	private void addActionListeners(JButton[] buttons) {
		for (JButton button : buttons) {
			button.addActionListener(treeListener);
		}
	}


	// logic for handling which action was selected
	private final ActionListener treeListener = event -> {
		try {
			switch ((event.getActionCommand())) {
			case "Make Tree":
				inputTree = new BinaryTree(input.getText());
				output.setText(inputTree.toString());
				break;
			case "Is Balanced?":
				output.setText(String.valueOf(inputTree.isBalanced()));
				break;
			case "Is Full?":
				output.setText(String.valueOf(inputTree.isFull()));
				break;
			case "Is Proper?":
				output.setText(String.valueOf(inputTree.isProper()));
				break;
			case "Height":
				output.setText(String.valueOf(inputTree.height()));
				break;
			case "Nodes":
				output.setText(String.valueOf(inputTree.nodes()));
				break;
			case "Inorder":
				output.setText(inputTree.inOrder());
				break;
			}
		} catch (InvalidTreeSyntax except) {
			JOptionPane.showMessageDialog(getParent(), except.getMessage());
		} catch (IndexOutOfBoundsException indexExcept) {
			JOptionPane.showMessageDialog(getParent(), "No input given!");
		}
	};
}