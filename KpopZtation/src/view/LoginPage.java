package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Users;
import util.Connect;

public class LoginPage extends Application implements EventHandler<ActionEvent> {
	Scene scene;
	
	//layout
	BorderPane borderpane;
	GridPane gridpane, atas, bawah;
	
	//komponen
	Label loginLabel, mailLabel, passwordLabel;
	TextField mailField;
	PasswordField passwordField;
	Button register, submit;
	
	ArrayList<Users> users;
	private Connect connect = Connect.getInstance();
	public static String role = "";
	public static String userCurrentID = "";
	
	public static String getRole() {
		return role;
	}
	public static String getCurrentID() {
		return userCurrentID;
	}

	public void init() {
		//array
		users = new ArrayList<>();
		//inisialisasi layout
		borderpane = new BorderPane();
		gridpane = new GridPane();
		atas = new GridPane();
		bawah = new GridPane();
		atas.setAlignment(Pos.CENTER);
		gridpane.setAlignment(Pos.CENTER);
		bawah.setAlignment(Pos.CENTER);
		
		// inisisalisasi komponen
		loginLabel = new Label("Login");
		loginLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
		
		mailLabel = new Label("E-mail");
		passwordLabel = new Label("Password");
		
		//field
		mailField = new TextField();
		passwordField = new PasswordField();
		
		//button
		register = new Button("Register");
		submit = new Button("Submit");
		
		//masukin ke scene
		scene = new Scene(borderpane, 300, 200);
	}
	
	public void layouting() {
				//TOP
				
				atas.add(loginLabel, 0, 0);
//				atas.add(loginLabel,Pos.BASELINE_CENTER, 0, 0);

				
				//tengah
				gridpane.add(mailLabel, 0,0);
				gridpane.add(mailField, 1, 0);
				gridpane.add(passwordLabel, 0, 1);
				gridpane.add(passwordField, 1, 1);
				
				//bawah
				bawah.add(register, 0, 0);
				bawah.add(submit, 1, 0);
				
				//rapiin layout
				atas.setPadding(new Insets(10));
				atas.setHgap(200);
				atas.setVgap(20);
				gridpane.setPadding(new Insets(10));
				gridpane.setHgap(10);
				gridpane.setVgap(20);
				bawah.setPadding(new Insets(10));
				bawah.setHgap(10);
				bawah.setVgap(20);
				
				borderpane.setTop(atas);
				borderpane.setCenter(gridpane);
				borderpane.setBottom(bawah);
				

	}
	
	public static void main(String[] args) {
		launch(args);

	}


	@Override
	public void start(Stage stage) throws Exception {
		init();
		layouting();
		setEventHandler();

		

		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Login Page");
		stage.show();
		
		
		
	}
	
	public void setEventHandler() {
		submit.setOnAction(this);
		register.setOnAction(this);
	}
	
	private void getData() {
		users.clear();
		
		// Select from DB
		String query = "SELECT * FROM users";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				String UserID = connect.rs.getString("UserID");
				String UserName = connect.rs.getString("UserName");
				String UserPassword = connect.rs.getString("UserPassword");
				String UserEmail = connect.rs.getString("UserEmail");
				String UserAddress = connect.rs.getString("UserAddress");
				String UserGender = connect.rs.getString("UserGender");
				String UserRole = connect.rs.getString("UserRole");
				users.add(new Users(UserID, UserName, UserPassword, UserEmail, UserAddress, UserGender, UserRole));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == submit) {

			getData();
			String email,password;
			boolean isNotExist = true;
			
			email = mailField.getText();
			password = passwordField.getText();
			
			for (Users user : users) {
				if (user.getUserEmail().equals(email) && user.getUserPassword().equals(password)) {
					// alert sukses
					isNotExist = false;
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setContentText("Success");
					alert.showAndWait();
					role = user.getUserRole();
					userCurrentID = user.getUserID();
//					System.out.println("Role  = "+role);
					// masuk ke main page
					Stage currentStage = (Stage) submit.getScene().getWindow();
					currentStage.close();
					
					Stage nextPage = new Stage();
					
					try {
						new MainPage().start(nextPage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (isNotExist == true) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("User Dose Not Exist!");
				alert.show();
			}
		}else if(event.getSource() == register) {
//			System.out.println("Next page");
			
			Stage curr = (Stage)submit.getScene().getWindow();
			curr.close();
			
			//pindah halaman
			Stage next = new Stage();
			try {
				new RegisterPage().start(next);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
