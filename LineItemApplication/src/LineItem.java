/**
 * @author Neha
 *
 */
public class LineItem
{
	// Variables for the class
    private String itemCode;
    private double price;
    private int quantity;
	private double taxPercent;
	
	public LineItem(){
		this.price=0.0;
		this.quantity =1;
	}
	
	public LineItem(double taxPercent){
		this.taxPercent = taxPercent;
	}

	// getters and setters for the variables
    public double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * method to compute the total price based on the number of items requested
	 * @param quantity
	 * @return total price of the all items
	 */

	public double totalPrice(double price)
	{
		return this.getQuantity() * price;
		
	}

	/**
	 * method to compute the total tax based on
	 * @param totalPrice
	 * @return total tax
	 */

	public double totalTax(double totalPrice)
	{		
		return this.taxPercent * totalPrice;		
	}
	
	/**
	 *  method to validate the input price and quantity
	 * @return true or false
	 */
	public boolean validateInputs(){
		if(this.getQuantity()<=0 || this.getQuantity() >50
				|| this.getPrice()<0 || this.getPrice()>=1000){
			return false;
		}
		return true;
	}
}