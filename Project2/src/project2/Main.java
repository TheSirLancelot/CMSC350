package project2;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
	private static List<Polynomial> polyList = new ArrayList<>();

//main here
	public static void main(String[] args) {
		processPolyList();
	}

	// Method that returns an ArrayList of the expressions in the chosen file
	public static ArrayList<String> createArrayFromFile() {
		
		//Create ArrayList and JFileChooser
		ArrayList<String> expressionList = new ArrayList<>();
		JFileChooser chooser = new JFileChooser();
		
		//Show both directories and files
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		//use current directory
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int response = chooser.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				Scanner fileIn = new Scanner(file);
				if (file.isFile()) {
					while (fileIn.hasNextLine()) {
						String expression = fileIn.nextLine();
						expressionList.add(expression);
					}
				}
				fileIn.close();
			} catch (NoSuchElementException nse) {
				// File is empty
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "File is empty!");
			} catch (FileNotFoundException fne) {
				// File not found
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "File is not found!");
			}
		}
		return expressionList;
	}

	// Method that checks whether the provided list of Polynomials are in strong order
	public static boolean checkStrongOrder(List<Polynomial> polyList) {
		
		// Set return to true by default
		boolean isStrongOrder = true;
		
		// Getting previous poly to compare
		Polynomial previous = polyList.get(polyList.size() - 1);
		
		// Looping through the list and calling the compareTo method
		for (int i = polyList.size() - 2; i > 0; i--) {
			if (previous.compareTo(polyList.get(i)) < 0) {
				// List is not in Strong Order
				isStrongOrder = false;
			}
		}
		return isStrongOrder;
	}

//===============================================================================================
// method: processPolyList / returns: void / catches: InvalidPolynomialSyntax
// description: calls fromFile to fill a list with Polynomial objects and checks list order
//===============================================================================================
	public static void processPolyList() {
		System.out.println("Parsed the following polynomials:");
		try {
			ArrayList<String> a = createArrayFromFile();
			for (String element : a) {
				Polynomial p = new Polynomial(element);
				System.out.println(p);
				polyList.add(p);
			}
		} catch (InvalidPolynomialSyntax ex) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
		}
		
		System.out.println("\nChecking for strong order sort.");
		// Check for strong order
		System.out.println("Strong Ordered: " + checkStrongOrder(polyList));
		
		System.out.println("\nChecking for weak order sort.");
		// Check for weak order
		System.out.println("Weak Ordered: " + OrderedList.checkSorted(polyList));
	}
}