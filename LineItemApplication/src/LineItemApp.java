/**
 * Application class for Line Item Invoice System
 * @ author Neha
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.NumberFormat;

public class LineItemApp {

	/**
	 * Main function which creates an object of Line Item class and uses it to
	 * get values from LineItem Class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Variables for user input
		String choice = "y";
		String itemCode;
		String taxable="";

		// variable to save subtotal values
		double taxableSubtotal = 0.0, untaxableSubtotal = 0.0, tax = 0.0, grandTotal = 0.0;

		// array created to test exception
		String itemArray[]= new String[1];
		int i =0;
		// to print output in decimal format
		NumberFormat currency = NumberFormat.getCurrencyInstance();

		// constant to set the value for tax percent
		final double TAX_PERCENT = 0.0525;

		// create an object of LineItem Class with null as default to test exception
		LineItem myItem = null;
//				new LineItem(TAX_PERCENT);

		// display message to screen
		System.out.println("Welcome to the Line Item Invoice: ");
		System.out.println();

		// scanner instance to get the input from user
		Scanner keyboard = new Scanner(System.in);

		// user inputs data until user wants to continue
		while (choice.equalsIgnoreCase("y")) {
			
			// try block starts here to catch multiple exceptions.
			try {
				// prompt user for item code
				System.out.println("Enter the Item code:");
				itemCode = keyboard.next();
				// set the item code value of LineItem
				myItem.setItemCode(itemCode);
				
				// for exception test purpose
				itemArray[i] = itemCode;
				i++;
				// prompt user for number of items
				System.out
						.println("Enter the number of items:\n(Number of items cannot be less than 1 or more than 50");
				// set the quantity of LineItem
				myItem.setQuantity(keyboard.nextInt());

				// prompt user for price of item
				System.out
						.println("Enter the price of the item:\n(Price of item should be >= 0 and <1000");
				// set the price of LineItem
				myItem.setPrice(keyboard.nextDouble());
				
				if (!myItem.validateInputs()) {
					System.out
							.println("Incorrect values for number of Items or price of Item.Enter Item details again");
					continue;
				}

				// is item taxable or not
				System.out.println("Is the Item Taxable? (Enter Y/N): ");
				taxable = keyboard.next();
				
			} catch (InputMismatchException e) {
				System.out.println("Incorrect data type for inputs.");
				keyboard.nextLine();
				continue;
			} catch (NullPointerException e){
				System.out.println("Object reference not available");
				keyboard.nextLine();
				myItem = new LineItem(TAX_PERCENT);
				continue;
			}
			catch (ArrayIndexOutOfBoundsException e){
				System.out.println("Array index oout of Bounds exception");
				keyboard.nextLine();
				itemArray = new String[20];
				continue;
			}catch (Exception e){
				System.out.println("Exception Found");
			}
			finally {
				System.out.println("Input entered.");
			}

			// validate input given by user for isTaxable
			checkTaxable: if (taxable.equalsIgnoreCase("y")) {
				// if the item is taxable add the price to taxable subtotal
				taxableSubtotal += myItem.totalPrice(myItem.getPrice());

			} else if (taxable.equalsIgnoreCase("n")) {
				// if the item is taxable add the price to untaxable subtotal
				untaxableSubtotal += myItem.totalPrice(myItem.getPrice());
			} else {
				// if user enters incorrect response ask again
				System.out.println("Incorrect answer. Enter again: ");
				System.out.println("Is the Item Taxable? (Enter Y/N): ");
				taxable = keyboard.next();
				// to validate the input again
				break checkTaxable;
			}

			// if user wishes to continue
			System.out.println("Do you wish to continue? (Enter Y/N): ");
			choice = keyboard.next();
		}
		// calculate the tax on taxable amount
		tax = myItem.totalTax(taxableSubtotal);

		// calculate the grand total
		grandTotal = taxableSubtotal + untaxableSubtotal + tax;

		// display the output
		System.out.println("Taxable Subtotal is: "
				+ currency.format(taxableSubtotal));
		System.out.println("Untaxable Subtotal is: "
				+ currency.format(untaxableSubtotal));
		System.out.println("Total Tax is: " + currency.format(tax));
		System.out.println("Grand Total is: " + currency.format(grandTotal));

		keyboard.close();

	}
}
