package model;

public class DetailTransactions {
	private String TransactionID, AlbumID;
	private int Qty;
	public DetailTransactions(String transactionID, String albumID, int qty) {
		super();
		TransactionID = transactionID;
		AlbumID = albumID;
		Qty = qty;
	}
	public String getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	public String getAlbumID() {
		return AlbumID;
	}
	public void setAlbumID(String albumID) {
		AlbumID = albumID;
	}
	public int getQty() {
		return Qty;
	}
	public void setQty(int qty) {
		Qty = qty;
	}
	
}
