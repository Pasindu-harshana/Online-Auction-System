package fetchdata;

public class MaxBids {
	private String userID;
	private double maxBidPrice=0;
	private String itemID;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public double getMaxBidPrice() {
		return maxBidPrice;
	}
	public void setMaxBidPrice(double maxBidPrice) {
		this.maxBidPrice = maxBidPrice;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}	
}

