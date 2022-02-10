package project2;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Filename: Main.java
 * 
 * @author William Weir 
 * Date: Jan 31, 2022 
 * Description: main class for project2
 */

public class Main {
	private static List<Polynomial> polyList = new ArrayList<>();

	// our main method
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
    public static boolean checkWeakOrder( List<Polynomial> polyList){
        boolean isWeakOrder = true;
        
        // Loop through and compare exponents
        for(int i = 0; i < polyList.size() - 1; i++){
        	Polynomial next = polyList.get(i + 1);
            if (next.compareExponents(polyList.get(i)) < 0){
                isWeakOrder = false;
                return isWeakOrder;
            }
        }
        return isWeakOrder;
    }

	// Method to process the list of polynomials we got from the file
	public static void processPolyList() {
		System.out.println("Parsed the following polynomials:");

		// Save return of createArrayFromFile into a new array and parse 
		try {
			ArrayList<String> a = createArrayFromFile();
			for (String element : a) {
				Polynomial p = new Polynomial(element);
				System.out.println(p);
				polyList.add(p);
			}
			
		// catch any invalid syntax
		} catch (InvalidPolynomialSyntax ex) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
			System.exit(-1);
		}
		
		System.out.println("\nChecking for strong order sort.");
		// Check for strong order
		System.out.println("Strong Ordered: " + OrderedList.checkSorted(polyList));
		
		System.out.println("\nChecking for weak order sort.");
		// Check for weak order
		System.out.println("Weak Ordered: " + checkWeakOrder(polyList));
	}
}