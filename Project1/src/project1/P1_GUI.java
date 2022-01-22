package project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

import javax.swing.*;

/**
 * Filename: P1_GUI.java
 * @author William Weir
 * Date: Jan 19, 2022
 * Description: GUI for Project 1, CMSC350
 */

/** 
 * I looked into why I need (or don't need) a serialVersionUID, if I understand
 * it correctly, it is mainly for serializing exceptions, which I believe is 
 * outside the scope of this project.
 */
@SuppressWarnings("serial")
public class P1_GUI extends JFrame implements ActionListener {

	private JPanel mainWindow;
	private JPanel expressionPanel;
	private JPanel buttonPanel;
	private JPanel resultPanel;
	private JLabel userInputLabel;
	private JTextField userInputTextField;
	private JButton btn_PreToPost;
	private JButton btn_PostToPre;
	private JLabel resultLabel;
	private JTextField resultTextField;

	public void setResultTextField(String result) {
		this.resultTextField.setText(result);
	}
	
	public String getUserInputTextField() {
		return this.userInputTextField.getText();
	}
	/**
	 * @param title
	 * @throws SyntaxError
	 */
	public P1_GUI(String title) {
		
		//set JFrame title
		super(title);
		
		//create the panels
		this.mainWindow = new JPanel();
		this.expressionPanel = new JPanel();
		this.buttonPanel = new JPanel();
		this.resultPanel = new JPanel();
		
		//set layout to BoxLayout using the top-to-bottom (PAGE_AXIS) layout
		this.mainWindow.setLayout(
				new BoxLayout(mainWindow, BoxLayout.PAGE_AXIS));
		
		//create the different components for the window
		this.userInputLabel = new JLabel("Enter Expression");
		this.userInputTextField = new JTextField(null, 20);
		this.btn_PreToPost = new JButton("Prefix to Postfix");
		this.btn_PostToPre = new JButton("Postfix to Prefix");
		this.resultLabel = new JLabel("Result");
		this.resultTextField = new JTextField(null, 20);
		
		//add all of the components to their respective panels
		this.expressionPanel.add(userInputLabel);
		this.expressionPanel.add(userInputTextField);
		this.buttonPanel.add(btn_PreToPost);
		this.buttonPanel.add(btn_PostToPre);
		this.resultPanel.add(resultLabel);
		this.resultPanel.add(resultTextField);
		
		//set the result field to be not editable
		this.resultTextField.setEditable(false);
		
		//add the panels to the main window
		this.mainWindow.add(expressionPanel);
		this.mainWindow.add(buttonPanel);
		this.mainWindow.add(resultPanel);
		
		//add the mainWindow JPanel to the JFrame and set the JFrame settings
		this.add(mainWindow);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);

		/*
		 * add an event handler for each of the buttons
		 * Thanks again for the clarification on how to handle this
		 */

		this.btn_PreToPost.addActionListener(this);
		this.btn_PostToPre.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		final String input = getUserInputTextField();
		
		try {
			//if Prefix to Postfix
			if(e.getSource() == btn_PreToPost) {
				setResultTextField(
						ExpressionParser.evaluate(input, "PreToPost")
						);
				
			//if Postfix to Prefix	
			} else if(e.getSource() == btn_PostToPre) {
				setResultTextField(
						ExpressionParser.evaluate(input, "PostToPre")
						);
			}
		} catch (SyntaxError se) {
			JOptionPane.showMessageDialog(null, 
					"Syntax Error: " + se.getMessage(),
					"Expression Converter",
					JOptionPane.ERROR_MESSAGE);
		} catch (EmptyStackException ese) {
			JOptionPane.showMessageDialog(null, 
					"Syntax Error: Empty Stack Exception",
					"Expression Converter",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new P1_GUI("Expression Converter");
	}

}
