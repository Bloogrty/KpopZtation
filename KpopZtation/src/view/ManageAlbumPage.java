package view;


import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.mysql.jdbc.PreparedStatement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Albums;
import model.Artists;
import util.Connect;

public class ManageAlbumPage extends Application implements EventHandler<ActionEvent> {

	
	Scene scene;
	BorderPane borderpane, atas;
	GridPane gridpane;
	VBox vbox;
	
	//label
	Label albumlistlbl, albumidlbl, albumnamelbl, albumpricelbl, albumstocklbl, artistnamelbl;
	TextField albumid, albumname, albumprice;
	
	Button insert, update, delete, submit, cancel;
	
	// utk update, setiap select akan simpan id secara temporary
	private Integer tempId = null;
	
	ComboBox<String> combobox;
	Spinner<Integer> stockspinner;
	

	
	TableView<Albums> tablealbum;
	ArrayList<Albums> albums;
	ArrayList<Artists> artists;
	private Connect connect = Connect.getInstance();
	
	public void init() {
		borderpane = new BorderPane();
		gridpane = new GridPane();
		vbox = new VBox();
		//deklarasi label
		albumidlbl = new Label("AlbumID");
		albumnamelbl = new Label("Album Name");
		albumpricelbl = new Label("Album Price");
		albumstocklbl = new Label("Album Stock");
		artistnamelbl = new Label("Artist Name");
		albumlistlbl = new Label("Album List");
		albumlistlbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		
		//deklarasi field
		albumid = new TextField();
		albumname = new TextField();
		albumprice = new TextField();
		albumid.setDisable(true);
		
		//deklarasi spinenr
		stockspinner = new Spinner<>(0,9999,0);
		
		//deklarasi button
		insert = new Button ("Insert");
		update = new Button ("Update");
		delete = new Button ("Delete");
		submit = new Button ("Submit");
		cancel = new Button ("Cancel");
		
		artists = new ArrayList<Artists>();
		combobox = new ComboBox<>();

		atas = new BorderPane();
		albums = new ArrayList<Albums>();
		tablealbum = new TableView<>();
		
		
		//masukin ke scene
		scene = new Scene(borderpane, 425, 700);
		
		insert.setOnAction(this);
		update.setOnAction(this);
		delete.setOnAction(this);
		submit.setOnAction(this);
		cancel.setOnAction(this);
		
		insert.setDisable(false);
		update.setDisable(false);
		delete.setDisable(false);
		submit.setDisable(true);
		cancel.setDisable(true);

	}
	
	public void layouting() {
		// ubah ukuran button
		insert.setMinWidth(60);
		update.setMinWidth(60);
		delete.setMinWidth(60);
		submit.setMinWidth(60);
		cancel.setMinWidth(60);
		
		//masukin ke grid
		gridpane.add(albumidlbl, 0, 0);
		gridpane.add(albumid, 1, 0);
		gridpane.add(albumnamelbl, 0, 1);
		gridpane.add(albumname, 1, 1);
		gridpane.add(albumpricelbl, 0, 2);
		gridpane.add(albumprice, 1, 2);
		gridpane.add(albumstocklbl, 0, 3);
		gridpane.add(stockspinner, 1, 3);
		gridpane.add(artistnamelbl, 0, 4);
		gridpane.add(combobox, 1, 4);
				
		//masukin button
		gridpane.add(insert, 2, 0);
		gridpane.add(update, 3, 0);
		gridpane.add(delete, 4, 0);
		gridpane.add(submit, 2, 1);
		gridpane.add(cancel, 4, 1);
				
		//rapiin
		gridpane.setPadding(new Insets(5));
		gridpane.setHgap(5);
		gridpane.setVgap(5);		
				
		//masukin ke boderpane
		borderpane.setTop(atas);
		borderpane.setCenter(gridpane);
		
		
		atas.setTop(albumlistlbl);
		atas.setCenter(tablealbum);
		atas.setAlignment(albumlistlbl, Pos.CENTER);
		gridpane.setAlignment(Pos.CENTER);
		
	}
	
	public void windowShow() {
		vbox.getChildren().add(albumlistlbl);
		vbox.getChildren().add(borderpane);
		vbox.setAlignment(Pos.CENTER);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void albumTable() {

		
		//kolom album
		TableColumn<Albums, String> idcolumn = new TableColumn("AlbumID");
		//ini supaya data nama di tabel bisa nambah
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("AlbumID")); // name merupakan variabel dari kelas member
		idcolumn.setMinWidth(140);
		
//		//kolom album
		TableColumn<Albums, String> namecolumn = new TableColumn("AlbumName");
//		//ini supaya data nama di tabel bisa nambah
		namecolumn.setCellValueFactory(new PropertyValueFactory<>("AlbumName")); // name merupakan variabel dari kelas member
		namecolumn.setMinWidth(140);
		
		//kolom album
		TableColumn<Albums, String> artistcolumn = new TableColumn("ArtistName");
		//ini supaya data nama di tabel bisa nambah
		artistcolumn.setCellValueFactory(new PropertyValueFactory<>("ArtistName")); // name merupakan variabel dari kelas member
		artistcolumn.setMinWidth(140);
		
		TableColumn<Albums, Integer> pricecolumn = new TableColumn("albumprice");
		pricecolumn.setCellValueFactory(new PropertyValueFactory<>("AlbumPrice"));
		pricecolumn.setMinWidth(140);

		TableColumn<Albums, Integer> stockcolumn = new TableColumn("AlbumStock");
		stockcolumn.setCellValueFactory(new PropertyValueFactory<>("AlbumStock"));
		stockcolumn.setMinWidth(140);
		
		
		
		
		//masukin kolom ke tabel
		tablealbum.getColumns().addAll(idcolumn, namecolumn, artistcolumn, pricecolumn, stockcolumn);
		
		//masukin table ke borderpane
//		atas.setTop(albumlistlbl);
		
	}
	
	private void getData() {
//		album.removeAllElements();
		albums.clear();
		
		String query = "select *\r\n"
				+ "FROM albums a join artists ar on a.ArtistID = ar.ArtistID\r\n"
				+ "";
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String albumid = connect.rs.getString("AlbumID");
				String albumname = connect.rs.getString("AlbumName");
				String artistname = connect.rs.getString("ArtistName");
				Integer albumprice = connect.rs.getInt("AlbumPrice");
				Integer albumstock = connect.rs.getInt("AlbumStock");
				albums.add(new Albums(albumid, albumname,artistname, albumprice, albumstock));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void getDataArtist() {
		artists.clear();
		
		String query = "SELECT * FROM artists";
		connect.rs = connect.execQuery(query);
		try {
			while (connect.rs.next()) {
				String ArtistID = connect.rs.getString("ArtistID");
				String ArtistName = connect.rs.getString("ArtistName");
				artists.add(new Artists(ArtistID, ArtistName));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setBox() {
		getDataArtist();
		
		for (Artists name : artists) {
			combobox.getItems().add(name.getArtistName());
		}
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		init();
		layouting();
		albumTable();
		tablealbum.setOnMouseClicked(tableMouseEvent());
		refreshTable();
		setBox();
		
		stage.setScene(scene);
		stage.setTitle("View Transaction");
		stage.show();
	}
	private EventHandler<MouseEvent> tableMouseEvent() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				TableSelectionModel<Albums> tableSelectionModel = tablealbum.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE); // ketika mouse click tabel, yg kepilih 1 aja
				Albums album = tableSelectionModel.getSelectedItem();
				
				albumid.setText(album.getAlbumID());
				albumname.setText(album.getAlbumName());
				albumprice.setText(album.getAlbumPrice().toString());
				
				combobox.getSelectionModel().select(album.getArtistName());
				stockspinner.getValueFactory().setValue(album.getAlbumStock());
//				stockspinner.setText(album.getAlbumPrice().toString());

//				artistname.setText(album.getArtistName());				

//				stockspinner.setText(album.getAlbumPrice().toString());
//				ipkField.setText(regis.getIpk().toString());
//			
//				tempId = album.getId();
				// simpan id sementaranya
			}
			
		};
	}
	
	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == submit) { 
			String albumid = getRandomID();
			String name = albumname.getText();
			int price = 0;
			boolean number = true;
			try {
				price = Integer.parseInt(albumprice.getText());
			} catch (Exception event) {
				event.printStackTrace();
				number = false;
			}
			int albumstock = 0;
			String artistid = "";

			for (Artists artist : artists) {
				if (artist.getArtistName().equals(combobox.getValue())) {
					artistid = artist.getArtistID();
				}
			}
			///validasi
			if (albumname.getText().length() <5 || albumname.getText().length() > 30) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Album Name must consist of 5 - 30 characters!");
				alert.show();
			}else if (price <= 0 || !number) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Album Price must be numeric and more than 0!");
				alert.show();
			} else if (stockspinner.getValue() <= 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Album Stock must more than 0!");
				alert.show();
			}else if (combobox.getValue() == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Artist Name must be Chosen!");
				alert.show();
			} else {
				addData(albumid, name, price, stockspinner.getValue(), artistid);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Insert Success");
				alert.show();
			}
			insert.setDisable(false);
			update.setDisable(false);
			delete.setDisable(false);
			submit.setDisable(true);
			cancel.setDisable(true);
			refreshTable();
			
		}
		else if(e.getSource() == update) {
			String id = albumid.getText();
			String name = albumname.getText();
			int price = 0;
			boolean number = true;
			try {
				price = Integer.parseInt(albumprice.getText());
			} catch (Exception event) {
				event.printStackTrace();
				number = false;
			}
			int albumstock = 0;
			String artistid = "";

			for (Artists artist : artists) {
				if (artist.getArtistName().equals(combobox.getValue())) {
					artistid = artist.getArtistID();
				}
			}
			///validasi
			if (albumname.getText().length() <5 || albumname.getText().length() > 30) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Album Name must consist of 5 - 30 characters!");
				alert.show();
			}else if (price <= 0 || !number) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Album Price must be numeric and more than 0!");
				alert.show();
			} else if (stockspinner.getValue() <= 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Album Stock must more than 0!");
				alert.show();
			}else if (combobox.getValue() == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Artist Name must be Chosen!");
				alert.show();
			} else {
				updateData(id, name, price, stockspinner.getValue(), artistid);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Update Success");
				alert.show();
			}
			refreshTable();
		}
		else if(e.getSource() == delete) {
			if (albumid.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("You must select the data on the table!");
				alert.show();
			} else {
			deleteData(albumid.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Delete Success");
			alert.show();
			}
			refreshTable();
		}
		
		else if(e.getSource() == insert) {
			insert.setDisable(true);
			update.setDisable(true);
			delete.setDisable(true);
			submit.setDisable(false);
			cancel.setDisable(false);
		} else if(e.getSource() == cancel) {
			insert.setDisable(false);
			update.setDisable(false);
			delete.setDisable(false);
			submit.setDisable(true);
			cancel.setDisable(true);
		}
		
		// tiap setelah execute sesuatu, formnya kosong lg
		refreshAllValue();
	}
	
	private void refreshAllValue() {
		albumid.setText("");
		albumname.setText("");
		albumprice.setText("");
//		albumstock.setText("");
		
		tempId = null;
	}
	
	private String getRandomID() {
		Random rand = new Random();
		String randomID = "AL" + ""
		+ rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10);;
		
		return randomID;
	}
	
	// insert
		private void addData(String AlbumID, String AlbumName, Integer AlbumPrice, Integer AlbumStock, String ArtistID) {

			String query = "INSERT INTO albums " + 
							"VALUES ('"+ AlbumID +"', '"+ AlbumName +"', '"+ AlbumPrice +"', '"+ AlbumStock +"', '"+ ArtistID + "')";
			connect.execUpdate(query);
		}
		
		private void updateData(String AlbumID, String AlbumName, Integer AlbumPrice, Integer AlbumStock, String ArtistID) {
			String query = "UPDATE albums\r\n"
					+ "SET AlbumID= '"+AlbumID+"',\r\n"
					+ "AlbumName= '"+AlbumName+"',\r\n"
					+ "AlbumPrice= '"+AlbumPrice+"',\r\n"
					+ "AlbumStock= '"+AlbumStock+"',\r\n"
					+ "ArtistID= '"+ArtistID+"' \r\n"
					+ "WHERE albumid = '"+AlbumID+ "'";
			connect.execUpdate(query);
		}
		
		private void deleteData(String albumID) {
			String query = "DELETE FROM albums WHERE albumid = '" + albumID + "'";
			connect.execUpdate(query);
		}
		
		private void refreshTable() {
			getData();
			ObservableList<Albums> regObs = FXCollections.observableArrayList(albums);
			tablealbum.setItems(regObs);
		}
		public ManageAlbumPage() {
			init();
			layouting();
			windowShow();
			albumTable();
			tablealbum.setOnMouseClicked(tableMouseEvent());
			refreshTable();
			setBox();
			
		}
}
