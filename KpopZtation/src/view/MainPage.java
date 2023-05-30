package view;


import java.awt.Color;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;

public class MainPage extends Application implements EventHandler<ActionEvent> {
	Scene scene;
	
	//layout
	BorderPane borderpane;
	GridPane gridpane;
	VBox vbox;
	
	//windwow
	Window window;
	Window artistwindow, transactionwindow, albumwindow, buywindow;
	
	MenuBar menubar;
	Menu managemenu, transactionmenu, logoutmenu;
	MenuItem artistitem, albumitem, buyitem, viewitem, logoutitem;
	String role = LoginPage.getRole();
	ListView<String> listview;
	public static void main(String[] args) {
		launch(args);

	}
	public void init() {
		menubar = new MenuBar();
		vbox = new VBox();
		
		managemenu = new Menu("Manage");
		transactionmenu = new Menu("Transaction");
		logoutmenu = new Menu("Logout");
		
		artistitem = new MenuItem("Artist");
		albumitem = new MenuItem("Album");
		viewitem = new MenuItem("View Transaction");
		logoutitem = new MenuItem("Logout");
		buyitem = new MenuItem("Buy Album");

		
		if (role.equals("Admin")) {
			
			menubar.getMenus().add(managemenu);
			menubar.getMenus().add(transactionmenu);
			menubar.getMenus().add(logoutmenu);
			
			managemenu.getItems().add(artistitem);
			managemenu.getItems().add(albumitem);

			transactionmenu.getItems().add(viewitem);
			logoutmenu.getItems().add(logoutitem);

		} else if (role.equals("User")) {
			logoutitem = new MenuItem("Logout");
			
			viewitem = new MenuItem("View Transaction");
			logoutitem = new MenuItem("Logout");
			
//			menubar.getMenus().add(managemenu);
			
			menubar.getMenus().add(transactionmenu);
			menubar.getMenus().add(logoutmenu);
			
			transactionmenu.getItems().add(buyitem);
			transactionmenu.getItems().add(viewitem);
			logoutmenu.getItems().add(logoutitem);
		}
		
//		menuitem = new MenuItem("Register");

		artistitem.setOnAction(this);
		albumitem.setOnAction(this);
		viewitem.setOnAction(this);
		buyitem.setOnAction(this);
		logoutitem.setOnAction(this);
	}
	public void initializeMenu() {
		//layout 
		borderpane = new BorderPane();
		gridpane = new GridPane();
		




	}
	
//	public void viewManageArtist(){
//		window = new Window("Manage Artist");
//		window.setMaxHeight(300);
//		window.setMaxWidth(300);
//		borderpane.setCenter(window);
//		
//	}

	@Override
	public void start(Stage stage) throws Exception {

		initializeMenu();
		init();

//		viewManageArtist();
//		initializeListView();

		
		
		borderpane.setTop(menubar);
		
		//masukin ke scene
		scene = new Scene(borderpane, 700, 700);
		
//		/menuitem, manageartist, managealbum, viewtransaction, logoutmenuitem

		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("KpopZtation");
		stage.show();
		
	}


	@Override
	public void handle(ActionEvent event) {
		//LOGOUT
		if (event.getSource() == logoutitem) {
			Stage currentStage = (Stage) menubar.getScene().getWindow();
			currentStage.close();
			
			Stage nextPage = new Stage();
			
			try {
				new LoginPage().start(nextPage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//ARTIST
		else if (event.getSource() == artistitem) {
			artistwindow = new Window("Manage Artist");
			artistwindow.getRightIcons().add(new CloseIcon(artistwindow));
			artistwindow.getLeftIcons().add(new MinimizeIcon(artistwindow));
			artistwindow.getContentPane().getChildren().add(new ManageArtistPage().vbox);
			borderpane.setCenter(artistwindow);

		}
		else if (event.getSource() == viewitem) {
			transactionwindow = new Window("View Transaction");
			transactionwindow.getRightIcons().add(new CloseIcon(transactionwindow));
			transactionwindow.getLeftIcons().add(new MinimizeIcon(transactionwindow));
			transactionwindow.getContentPane().getChildren().add(new TransactionPage().vbox);
			borderpane.setCenter(transactionwindow);

		}
		else if (event.getSource() == albumitem) {
			albumwindow = new Window("View Transaction");
			albumwindow.getRightIcons().add(new CloseIcon(albumwindow));
			albumwindow.getLeftIcons().add(new MinimizeIcon(albumwindow));
			albumwindow.getContentPane().getChildren().add(new ManageAlbumPage().vbox);
			borderpane.setCenter(albumwindow);

		}
		else if (event.getSource() == buyitem) {
			buywindow = new Window("Buy Album Form");
			buywindow.getRightIcons().add(new CloseIcon(buywindow));
			buywindow.getLeftIcons().add(new MinimizeIcon(buywindow));
			buywindow.getContentPane().getChildren().add(new BuyAlbumPage().vbox);
			borderpane.setCenter(buywindow);

		}
	}


}
