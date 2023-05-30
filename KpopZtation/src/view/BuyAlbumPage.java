package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Albums;
import model.CartBuy;
import model.Users;
import util.Connect;

public class BuyAlbumPage extends Application implements EventHandler<ActionEvent> {
	Scene scene;
	
	//layout
	BorderPane borderpane, atas, bawah;
	GridPane gridpane;
	VBox vbox;
	
	ArrayList<Users> users;
	

	
	//label
	Label albumlistlbl, albumidlbl, albumnamelbl, artistnamelbl, qtylbl, cartlbl;
	
	//textfield
	TextField albumid, albumname, artistname;
	
	//spinner
	Spinner<Integer> qtyspinner;
	
	//button
	Button add, checkout;
	//deklarasi tabel
//	TableView<AlbumList> tableatas;
	TableView tablealbum;
	TableView tablecart;
	String usercurrentid = LoginPage.getCurrentID();
	
	ArrayList<Albums> album;
	ArrayList<CartBuy> cartbuy;
	
	private int currquantity = 0;
	
	private Connect connect = Connect.getInstance();
	
	public static String userCurrentID = "";
	public static String getCurrentID() {
		return userCurrentID;
	}

	
	public static void main(String[] args) {
		launch(args);

	}
	
	public void albumTable() {
		atas = new BorderPane();
		album = new ArrayList<Albums>();
		tablealbum = new TableView<>();
		
		//kolom album
		TableColumn<Albums, String> idcolumn = new TableColumn("AlbumID");
		//ini supaya data nama di tabel bisa nambah
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("AlbumID")); // name merupakan variabel dari kelas member
		idcolumn.setMinWidth(120);
		
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
		atas.setTop(albumlistlbl);
		atas.setCenter(tablealbum);
		atas.setAlignment(albumlistlbl, Pos.CENTER);
	}
	
	private void getDataAlbum() {
//		album.removeAllElements();
		album.clear();
		
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
				album.add(new Albums(albumid, albumname,artistname, albumprice, albumstock));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void getDataCart() {
		cartbuy.clear();
		String query = "SELECT * \r\n"
				+ "FROM carts c JOIN albums a ON a.AlbumID = c.AlbumID";
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String albumid = connect.rs.getString("AlbumID");
				String albumname = connect.rs.getString("AlbumName");
				Integer albumprice = connect.rs.getInt("AlbumPrice");
				Integer qty = connect.rs.getInt("Qty");
//				cartbuy.add(new CartBuy(albumid, albumname, albumprice, qty));
				cartbuy.add(new CartBuy(albumid, albumname, albumprice, qty));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void cartTable() {
		bawah = new BorderPane();
		cartbuy = new ArrayList<CartBuy>();
		tablecart = new TableView<>();
		
		//kolom album
		TableColumn<CartBuy, String> idcolumn = new TableColumn("AlbumID");
		//ini supaya data nama di tabel bisa nambah
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("AlbumID")); // name merupakan variabel dari kelas member
		idcolumn.setMinWidth(175);
		
//		//kolom album
		TableColumn<CartBuy, String> namecolumn = new TableColumn("AlbumName");
//		//ini supaya data nama di tabel bisa nambah
		namecolumn.setCellValueFactory(new PropertyValueFactory<>("AlbumName")); // name merupakan variabel dari kelas member
		namecolumn.setMinWidth(175);
		
		
		TableColumn<CartBuy, Integer> pricecolumn = new TableColumn("AlbumPrice");
		pricecolumn.setCellValueFactory(new PropertyValueFactory<>("AlbumPrice"));
		pricecolumn.setMinWidth(175);

		TableColumn<CartBuy, Integer> qtycolumn = new TableColumn("Qty");
		qtycolumn.setCellValueFactory(new PropertyValueFactory<>("Qty"));
		qtycolumn.setMinWidth(175);
		
		
		
		
		//masukin kolom ke tabel
		tablecart.getColumns().addAll(idcolumn, namecolumn, pricecolumn, qtycolumn);
		
		//masukin table ke borderpane
//		atas.setTop(albumlistlbl);
		bawah.setTop(cartlbl);
		bawah.setCenter(tablecart);
		bawah.setAlignment(cartlbl, Pos.CENTER);
	}
	public void init() {
		users = new ArrayList<>();
		//masukin ke scene

		borderpane = new BorderPane();
		gridpane = new GridPane();
		
		gridpane.setAlignment(Pos.CENTER);
		
		//inisialisasi
		albumlistlbl = new Label("Album List");
		albumidlbl = new Label("AlbumID");
		albumnamelbl = new Label("AlbumName");
		artistnamelbl = new Label("ArtistName");
		qtylbl = new Label("Quantity");
		cartlbl = new Label("Cart");
		albumlistlbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		cartlbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		
		//inisialisai textfield
		albumid = new TextField();
		albumname = new TextField();
		artistname = new TextField();
		albumid.setDisable(true);
		albumname.setDisable(true);
		artistname.setDisable(true);
		
		//inisialisasi button
		add = new Button("Add to Cart");
		checkout = new Button("Checkout");
		//inisialisasi spinner
		qtyspinner = new Spinner<>(0,9999,0);
		
		add.setOnAction(this);
		checkout.setOnAction(this);
		vbox = new VBox();
	}
	public void layouting() {
		//masukin ke gridpane center
				gridpane.add(albumidlbl, 0, 0);
				gridpane.add(albumid, 1, 0);
				gridpane.add(artistnamelbl, 2, 0);
				gridpane.add(artistname, 3, 0);
				gridpane.add(albumnamelbl, 0, 1);
				gridpane.add(albumname, 1, 1);
				gridpane.add(qtylbl, 2, 1);
				gridpane.add(qtyspinner, 3, 1);
				gridpane.add(add, 1, 2);
				gridpane.add(checkout, 3, 2);
				
				//RAPIIN
				gridpane.setPadding(new Insets(5));
				gridpane.setHgap(5);
				gridpane.setVgap(5);

				
				//masukin ke boderpane
				borderpane.setTop(atas);
				borderpane.setCenter(gridpane);
				borderpane.setBottom(bawah);
				scene = new Scene(borderpane, 700, 700);
	}
	public void toBox() {
		vbox.getChildren().add(atas);
		vbox.getChildren().add(gridpane);
		vbox.getChildren().add(bawah);
	}

	@Override
	public void start(Stage stage) throws Exception {
		init();
		albumTable();
		tablealbum.setOnMouseClicked(tableMouseEvent());
		cartTable();
		layouting();
		refreshTable();
		
		stage.setScene(scene);
		stage.setTitle("KpopZtation");
		stage.show();
	}
	private void refreshTable() {
		getDataAlbum();
		getDataCart();
		ObservableList<Albums> regObs = FXCollections.observableArrayList(album);
		ObservableList<CartBuy> regObs2 = FXCollections.observableArrayList(cartbuy);
		tablealbum.setItems(regObs);
		tablecart.setItems(regObs2);
	}
	
	private void addToCart(String UserID, String AlbumID, Integer Qty) {
		String query = "INSERT INTO carts\r\n"
				+ "VALUES ('"+UserID+"','"+AlbumID+"','"+Qty+"')";
		connect.execUpdate(query);
	}
	private void checkOut() {
		LocalDate date = LocalDate.now();
		String datenow = date.toString();
		String transactionid = getRandomID();
//		usercurrentid
		
		//header
		String query = "INSERT INTO headertransactions(TransactionID, UserID, TransactionDate)\r\n"
				+ "VALUES ('"+transactionid+"','"+usercurrentid+"','"+datenow+"')";
		connect.execUpdate(query);
		
		//detail
		for (CartBuy cart : cartbuy) {
			String queryDetail = "INSERT INTO `detailtransactions`(`TransactionID`, `AlbumID`, `Qty`) \r\n"
					+ "VALUES ('"+transactionid+"','"+cart.getAlbumID()+"','"+cart.getQty() +"')";
			connect.execUpdate(queryDetail);
		}
	}
	private void clearCartData() {
		cartbuy.clear();
		
		String query = "TRUNCATE TABLE carts";
		connect.execUpdate(query);
	}
	private String getRandomID() {
		Random rand = new Random();
		String randomID = "TR" + ""
		+ rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10);;
		
		
		return randomID;
	}


	private EventHandler<MouseEvent> tableMouseEvent() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				TableSelectionModel<Albums> tableSelectionModel = tablealbum.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE); // ketika mouse click tabel, yg kepilih 1 aja
				Albums album = tableSelectionModel.getSelectedItem();
				
				if (album !=  null) {
					albumid.setText(album.getAlbumID());
					albumname.setText(album.getAlbumName());
					artistname.setText(album.getArtistName());
					
					currquantity = album.getAlbumStock();
				}
				

			}
		};
	}
	@Override
	public void handle(ActionEvent e) {
		
		if(e.getSource() == add) {
			if (!albumid.getText().equals("")) {
				if (qtyspinner.getValue() <= 0 || qtyspinner.getValue() > currquantity ) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Quantity must between 1 and album stock!");
					alert.show();
				}else {
					String id = albumid.getText();
					addToCart(usercurrentid,id, qtyspinner.getValue());
					refreshTable();
				}
			}else {
				Alert errorMsg = new Alert(AlertType.ERROR);
				errorMsg.setContentText("Please select an album!");
				errorMsg.show();
			}
			///////////////////////////////

			
			

		}
		else if(e.getSource() == checkout) {
			if (cartbuy.size() != 0) {
				checkOut();
				clearCartData();
				refreshTable();
				
				Alert errorMsg = new Alert(AlertType.INFORMATION);
				errorMsg.setContentText("Check Out Success!");
				errorMsg.show();
			}else {
				Alert errorMsg = new Alert(AlertType.ERROR);
				errorMsg.setContentText("Please select 1 album");
				errorMsg.show();
			}

			
		}
	}

	public BuyAlbumPage() {
		init();
		albumTable();
		tablealbum.setOnMouseClicked(tableMouseEvent());
		cartTable();
		layouting();
		refreshTable();
		toBox();
	}

}
