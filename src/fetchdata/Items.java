package fetchdata;

public class Items {
	private String itemNo;
	private String itemName;
	private String itemDiscription;
	private double itemPrice;
	private String base64Image;
	private String sellerID;
	
	//item constructor
	public Items(String itemNo, String itemName, String itemDiscription, double itemPrice,String base64Image) {
		super();
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.itemDiscription = itemDiscription;
		this.itemPrice = itemPrice;
		this.base64Image = base64Image;
	}

	//overloading item constructor
	public Items( String itemName, String itemDiscription, String sellerID) {
		super();
		this.itemName = itemName;
		this.itemDiscription = itemDiscription;
		this.sellerID = sellerID;
	}

	//creating setters and getters
	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDiscription() {
		return itemDiscription;
	}

	public void setItemDiscription(String itemDiscription) {
		this.itemDiscription = itemDiscription;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public String getBase64Image(){
		
		return base64Image;
	}
	
	public void setImage(String base64Image){
		this.base64Image = base64Image;
	}


	public String getSellerID() {
		return sellerID;
	}


	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}
	
	
	
	
	
	
	
}
