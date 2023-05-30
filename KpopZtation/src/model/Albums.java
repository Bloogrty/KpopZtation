package model;

public class Albums {
	private String AlbumID, AlbumName, ArtistName;
	private Integer AlbumPrice, AlbumStock;
	public Albums(String albumID, String albumName, String artistName, Integer albumPrice, Integer albumStock) {
		super();
		AlbumID = albumID;
		AlbumName = albumName;
		ArtistName = artistName;
		AlbumPrice = albumPrice;
		AlbumStock = albumStock;
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
	public String getArtistName() {
		return ArtistName;
	}
	public void setArtistName(String artistName) {
		ArtistName = artistName;
	}
	public Integer getAlbumPrice() {
		return AlbumPrice;
	}
	public void setAlbumPrice(Integer albumPrice) {
		AlbumPrice = albumPrice;
	}
	public Integer getAlbumStock() {
		return AlbumStock;
	}
	public void setAlbumStock(Integer albumStock) {
		AlbumStock = albumStock;
	}
	
}
