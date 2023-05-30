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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
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
import model.Artists;
import util.Connect;

//public class ManageArtistPage extends Application implements EventHandler<ActionEvent>{
public class ManageArtistPage implements EventHandler<ActionEvent>{
	
//	public static void main(String[] args) {
//		launch(args);
//
//	}
		Scene scene;
		GridPane gridpane;
		BorderPane borderpane, atas;
		VBox vbox;
		
		Label artistlistlbl,artistidlbl, artistnamelbl;
		TextField artistid, artistname;
		
		Button insert, update, delete, submit, cancel;
		
		TableView<Artists> artiststable;
		ArrayList<Artists> artists;
//		=======================
		// utk update, setiap select akan simpan id secara temporary
		private String tempId = null;
		
		private Connect connect = Connect.getInstance();
		
		public void init() {
			borderpane = new BorderPane();
			atas = new BorderPane();
			gridpane = new GridPane();
			vbox = new VBox();
			
			//deklarasi label
			artistidlbl = new Label("Artist ID");
			artistnamelbl = new Label("Artist Name");		
			
			//deklarasi field
			artistid = new TextField();
			artistname = new TextField();
			
			//deklarasi button
			insert = new Button ("Insert");
			update = new Button ("Update");
			delete = new Button ("Delete");
			submit = new Button ("Submit");
			cancel = new Button ("Cancel");
			
			insert.setOnAction(this);
			update.setOnAction(this);
			delete.setOnAction(this);
			submit.setOnAction(this);
			cancel.setOnAction(this);
			
			artistid.setDisable(true);
			
		}

		
		public void layouting() {
			insert.setMinWidth(60);
			update.setMinWidth(60);
			delete.setMinWidth(60);
			submit.setMinWidth(60);
			cancel.setMinWidth(60);
			
			//masukin ke grid
			gridpane.add(artistidlbl, 0, 0);
			gridpane.add(artistid, 1, 0);
			gridpane.add(artistnamelbl, 0, 1);
			gridpane.add(artistname, 1, 1);
			
			//masukin button
			gridpane.add(insert, 2, 0);
			gridpane.add(update, 3, 0);
			gridpane.add(delete, 4, 0);
			gridpane.add(submit, 2, 1);
			gridpane.add(cancel, 4, 1);
			
			gridpane.setPadding(new Insets(5));
			gridpane.setHgap(5);
			gridpane.setVgap(5);
			
			//masukin ke boderpane
			borderpane.setTop(atas);
			borderpane.setCenter(gridpane);
			
			
			artistTable();
			
			vbox.getChildren().add(artiststable);
			vbox.getChildren().add(borderpane);
			vbox.setPadding(new Insets(10));
		}


	

	
	public void artistTable() {
		atas = new BorderPane();
		artiststable = new TableView<>();
		artists = new ArrayList<Artists>();
		artistlistlbl = new Label("Album List");
		artistlistlbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		

		TableColumn<Artists, String> idcolumn = new TableColumn("ArtistID");

		idcolumn.setCellValueFactory(new PropertyValueFactory<>("ArtistID"));
		idcolumn.setMinWidth(200);
		idcolumn.setResizable(false);
		
//		//kolom album
		TableColumn<Artists, String> namecolumn = new TableColumn("ArtistName");
//		//ini supaya data nama di tabel bisa nambah
		namecolumn.setCellValueFactory(new PropertyValueFactory<>("ArtistName")); // name merupakan variabel dari kelas member
		namecolumn.setMinWidth(500);
		namecolumn.setResizable(false);
		
		//masukin kolom ke tabel
		artiststable.getColumns().addAll(idcolumn, namecolumn);
		artiststable.setOnMouseClicked(tableMouseEvent());
		
		//masukin table ke borderpane
		atas.setTop(artistlistlbl);
		atas.setTop(artistlistlbl);
		atas.setCenter(artiststable);
		atas.setAlignment(artistlistlbl, Pos.CENTER);
	}


	public ManageArtistPage() {

		init();
		layouting();

		activeButton();

		refreshTable();
		
		//rapiin

		
		
		
		

//
//		
//
//		
//		//masukin ke scene
//		scene = new Scene(borderpane, 425, 700);
//		
//		stage.setScene(scene);
//		stage.setTitle("View Transaction");
//		stage.show();
	}
	private EventHandler<MouseEvent> tableMouseEvent() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				TableSelectionModel<Artists> tableSelectionModel = artiststable.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE); // ketika mouse click tabel, yg kepilih 1 aja
				Artists regis = tableSelectionModel.getSelectedItem();
				
				artistid.setText(regis.getArtistID());
				artistname.setText(regis.getArtistName());
			
				tempId = regis.getArtistID();
				// simpan id sementaranya
			}
			
		};
	}
	
	
	
	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == submit) {
			String id = getRandomID();
			String name = artistname.getText();
			
			if (name.length() < 5 || name.length() > 20) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Artist Name must consist of 5 - 20 characters");
				alert.show();
			} else {
				addData(id, name);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Insert Success");
				alert.show();
			}
			refreshTable();
			insert.setDisable(false);
			update.setDisable(false);
			delete.setDisable(false);
			submit.setDisable(true);
			cancel.setDisable(true);
		}
		else if(e.getSource() == update) {
			String idupdate = artistid.getText();
			String nameupdate = artistname.getText();

			if (idupdate.equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("You must select the data on the table!");
				alert.show();
			} else if (nameupdate.length() < 5 || nameupdate.length() > 20) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Artist Name must consist of 5 - 20 characters");
				alert.show();
			} else {
				String query = String.format("UPDATE artists\r\n"
						+ "SET artistid = ?, artistname = ?\r\n"
						+ "WHERE artistid = ?", idupdate, nameupdate, tempId);
			
				PreparedStatement ps = (PreparedStatement) connect.preparedStatement(query);
				try {
					ps.setString(1,  idupdate);
					ps.setString(2, nameupdate);
					ps.setString(3, tempId);
					ps.execute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
				} 
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Update Success");
				alert.show();
			}
			refreshTable();
		}
		else if(e.getSource() == delete) {
//			String query = String.format("DELETE FROM Registrant\n" + "WHERE id = %s", tempId);
//			connect.execUpdate(query);
			
			String query = String.format("DELETE FROM Artists\n" + "WHERE artistid = ?", tempId);
			PreparedStatement ps = (PreparedStatement) connect.preparedStatement(query);
			try {
				ps.setString(1, tempId);;
				ps.execute();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Delete Success");
			alert.show();
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
		artistid.setText("");
		artistname.setText("");
//		ageField.setText("");
//		ipkField.setText("");
		
		tempId = null;
	}
	
	// insert
	private void addData(String albumid, String albumname) {
		String query = "INSERT INTO artists " + 
//						"VALUES ('0', '"+ albumid +"', '"+ albumname + "')";
		"VALUES ('"+ albumid +"', '"+ albumname + "')";
		connect.execUpdate(query);
	}
	
	// select
	private void getData() {
		artists.clear();
		// select dari db
		String query = "SELECT * FROM artists";
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				// sesuai nama kolom di db
				String id = connect.rs.getString("ArtistID");
				String name = connect.rs.getString("ArtistName");
				artists.add(new Artists(id, name));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

	private void refreshTable() {
		getData();
		ObservableList<Artists> regObs = FXCollections.observableArrayList(artists);
		artiststable.setItems(regObs);
	}
	
	private void activeButton() {
		insert.setDisable(false);
		update.setDisable(false);
		delete.setDisable(false);
		submit.setDisable(true);
		cancel.setDisable(true);
	}
	private String getRandomID() {
		Random rand = new Random();
		String randomID = "AR" + ""
		+ rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10);;
		
		return randomID;
	}

}
