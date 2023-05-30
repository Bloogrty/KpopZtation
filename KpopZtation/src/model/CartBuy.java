package model;

public class CartBuy {
	private String AlbumID;
	private String AlbumName;
	private Integer AlbumPrice;
	private Integer Qty;
	public CartBuy(String albumID, String albumName, Integer albumPrice, Integer qty) {
		super();
		AlbumID = albumID;
		AlbumName = albumName;
		AlbumPrice = albumPrice;
		Qty = qty;
	}
	public String getAlbumID() {
		return AlbumID;
	}
	public void setAlbumID(String albumID) {
		AlbumID = albumID;
	}
	public String getAlbumName() {
		return AlbumName;
	}
	public void setAlbumName(String albumName) {
		AlbumName = albumName;
	}
	public Integer getAlbumPrice() {
		return AlbumPrice;
	}
	public void setAlbumPrice(Integer albumPrice) {
		AlbumPrice = albumPrice;
	}
	public Integer getQty() {
		return Qty;
	}
	public void setQty(Integer qty) {
		Qty = qty;
	}
	
	
	
}
