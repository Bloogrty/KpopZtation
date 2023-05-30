package model;

public class Artists {
	private String ArtistID;
	private String ArtistName;
	public Artists(String artistID, String artistName) {
		super();
		ArtistID = artistID;
		ArtistName = artistName;
	}
	public String getArtistID() {
		return ArtistID;
	}
	public void setArtistID(String artistID) {
		ArtistID = artistID;
	}
	public String getArtistName() {
		return ArtistName;
	}
	public void setArtistName(String artistName) {
		ArtistName = artistName;
	}
	
	
	
	
}
