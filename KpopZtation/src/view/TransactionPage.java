package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.DetailTransactions;
import model.HeaderTransactions;
import util.Connect;

public class TransactionPage extends Application {
	Scene scene;
	BorderPane borderpane, atas, bawah;
	VBox vbox = new VBox();
	
	//label
	Label list, detail;
	
	TableView tableheader;
	ArrayList <HeaderTransactions> headertransactions;
	
	
	TableView tabledetail;
	ArrayList<DetailTransactions> detailtransactions;
	
	
	private Connect connect = Connect.getInstance();

	
	public static void main(String[] args) {
		launch(args);
	}

	public void transactionList() {
		atas = new BorderPane();
		tableheader = new TableView<>();
		headertransactions = new ArrayList<HeaderTransactions>();
		list = new Label("Transaction List");
		list.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		
		//kolom album
		TableColumn<HeaderTransactions, String> idcolumn = new TableColumn("TransactionID");
		//ini supaya data nama di tabel bisa nambah
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("TransactionID")); // name merupakan variabel dari kelas member
		idcolumn.setMinWidth(200);
		
//		//kolom album
		TableColumn<HeaderTransactions, String> usercolumn = new TableColumn("userID");
//		//ini supaya data nama di tabel bisa nambah
		usercolumn.setCellValueFactory(new PropertyValueFactory<>("UserID")); // name merupakan variabel dari kelas member
		usercolumn.setMinWidth(250);
		
		//kolom album
		TableColumn<HeaderTransactions, String> datecolumn = new TableColumn("TransactionDate");
		//ini supaya data nama di tabel bisa nambah
		datecolumn.setCellValueFactory(new PropertyValueFactory<>("TransactionDate")); // name merupakan variabel dari kelas member
		datecolumn.setMinWidth(250);
		
		//masukin kolom ke tabel
		tableheader.getColumns().addAll(idcolumn, usercolumn, datecolumn);
		
		tableheader.setOnMouseClicked(tableTransactionMouseEvent());
		

		atas.setTop(list);
		atas.setCenter(tableheader);
		atas.setAlignment(list, Pos.CENTER);
	}
	////////////////////////////
	public void transactionDetail() {
		bawah = new BorderPane();
		tabledetail = new TableView<>();
		detailtransactions = new ArrayList<DetailTransactions>();
		detail = new Label("Transaction Detail");
		detail.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		
		//kolom album
		TableColumn<DetailTransactions, String> idcolumn = new TableColumn("TransactionID");
		//ini supaya data nama di tabel bisa nambah
		idcolumn.setCellValueFactory(new PropertyValueFactory<>("TransactionID")); // name merupakan variabel dari kelas member
		idcolumn.setMinWidth(200);
		
//		//kolom album
		TableColumn<DetailTransactions, String> albumcolumn = new TableColumn("AlbumID");
//		//ini supaya data nama di tabel bisa nambah
		albumcolumn.setCellValueFactory(new PropertyValueFactory<>("AlbumID")); // name merupakan variabel dari kelas member
		albumcolumn.setMinWidth(250);
		
		//kolom album
		TableColumn<DetailTransactions, Integer> qtycolumn = new TableColumn("Qty");
		//ini supaya data nama di tabel bisa nambah
		qtycolumn.setCellValueFactory(new PropertyValueFactory<>("Qty")); // name merupakan variabel dari kelas member
		qtycolumn.setMinWidth(250);
		
		//masukin kolom ke tabel
		tabledetail.getColumns().addAll(idcolumn, albumcolumn, qtycolumn);
		
		//masukin table ke borderpane
//		atas.setTop(albumlistlbl);
		bawah.setTop(detail);
		bawah.setCenter(tabledetail);
		bawah.setAlignment(detail, Pos.CENTER);
	}
	
	private EventHandler<MouseEvent> tableTransactionMouseEvent() {
		return new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				TableSelectionModel<HeaderTransactions> tableSelectionModel = tableheader.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				HeaderTransactions currTransaction = tableSelectionModel.getSelectedItem();
				refreshTableDetail(currTransaction.getTransactionID());
			}
		};
	}
	
	private void toBox() {
		vbox.getChildren().add(list);
		vbox.getChildren().add(tableheader);
		vbox.getChildren().add(detail);
		vbox.getChildren().add(tabledetail);
		vbox.setAlignment(Pos.CENTER);
//		list.setAlignment(Pos.CENTER);
//		detail.setAlignment(Pos.CENTER);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		
		transactionList();
		transactionDetail();
		refreshTable();

		borderpane = new BorderPane();
		//masukin ke boderpane
		borderpane.setTop(atas);
		borderpane.setBottom(bawah);
		

		
		//masukin ke scene
		scene = new Scene(borderpane, 700, 700);
		
		stage.setScene(scene);
		stage.setTitle("View Transaction");
		stage.show();
		
		
	}
	private void getDataHeader() {
//		album.removeAllElements();
		headertransactions.clear();
		
		String query = "SELECT * FROM headertransactions";
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String transactionid = connect.rs.getString("TransactionID");
				String userid = connect.rs.getString("UserID");
				String transactiondate = connect.rs.getString("TransactionDate");
				headertransactions.add(new HeaderTransactions(transactionid, userid,transactiondate));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void getDataDetail(String TransactionID) {
//		album.removeAllElements();
		detailtransactions.clear();
		
		String query = "SELECT * FROM detailtransactions WHERE transactionid = '" + TransactionID + "'";
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String transactionid = connect.rs.getString("TransactionID");
				String albumid = connect.rs.getString("AlbumID");
				Integer qty = connect.rs.getInt("Qty");
				detailtransactions.add(new DetailTransactions(transactionid, albumid,qty));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void refreshTable() {
		getDataHeader();
		
		ObservableList<HeaderTransactions> regObs = FXCollections.observableArrayList(headertransactions);
		tableheader.setItems(regObs);
		
	}
	private void refreshTableDetail(String TransactionID) {
		getDataDetail(TransactionID);
		ObservableList<DetailTransactions> regOb = FXCollections.observableArrayList(detailtransactions);
		tabledetail.setItems(regOb);
	}
	
	public TransactionPage() {
		transactionList();
		transactionDetail();
		refreshTable();
		toBox();
	}

}

