package view;


import java.util.Random;

import javafx.application.Application;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import util.Connect;

public class RegisterPage extends Application implements EventHandler<ActionEvent> {
	Scene scene;
	
	//layout
	BorderPane borderpane;
	GridPane gridpane, atas, bawah, radio;
	
	//label
	Label registerlabel,username,mail,password, gender,address;
	
	//field
	TextField usernamefield, mailfield;
	PasswordField passwordfield;
	
	//radio
	RadioButton male,female;
	ToggleGroup group;

	
	//text area
	TextArea addressfield;
	
	//button
	Button reset,register,login;
	
	String id = "";
	
	ComboBox<String> artistBox;
	Connect connect = Connect.getInstance();
	
	public void init() {
		//inisialisasi layout
				borderpane = new BorderPane();
				gridpane = new GridPane();
				atas = new GridPane();
				bawah = new GridPane();
				radio = new GridPane();
				atas.setAlignment(Pos.CENTER);
//				gridpane.setAlignment(Pos.CENTER);
				bawah.setAlignment(Pos.CENTER);	
				
				//inisialisasi semua
				registerlabel = new Label("Register");
				username = new Label("Username");
				mail = new Label("E-mail");
				password = new Label("Password");
				gender = new Label("Gender");
				address = new Label("Address");
				registerlabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
				
				usernamefield = new TextField();
				mailfield = new TextField();
				passwordfield = new PasswordField();
				
				//radio
				male = new RadioButton("Male");
				female = new RadioButton("Female");
				group = new ToggleGroup();
				male.setToggleGroup(group);
				female.setToggleGroup(group);
				
				addressfield = new TextArea();
				
				reset = new Button("Reset");
				register = new Button("Register");
				login = new Button("Login");
				
				//masukin ke scene
				scene = new Scene(borderpane, 600, 500);
				
				login.setOnAction(this);
				reset.setOnAction(this);
				register.setOnAction(this);
	}
	
	public void layouting() {
		radio.add(male, 0, 0);
		radio.add(female, 1, 0);
		
	
		
		//masukin ke layout
		//atas
		atas.add(registerlabel, 0, 0);
		
		//center
		gridpane.add(username, 0, 0);
		gridpane.add(mail, 0, 1);
		gridpane.add(password, 0, 2);
		gridpane.add(gender, 0, 3);
		gridpane.add(address, 0, 4);
		
		//masukin field
		gridpane.add(usernamefield, 1, 0);
		gridpane.add(mailfield, 1, 1);
		gridpane.add(passwordfield, 1, 2);
		gridpane.add(radio, 1, 3);
		gridpane.add(addressfield, 1, 4);
		
		//bawah
		bawah.add(reset, 0, 0);
		bawah.add(register, 1, 0);
		bawah.add(login, 2, 0);
		
		
		//rapiin layout
		atas.setPadding(new Insets(10));
		atas.setHgap(200);
		atas.setVgap(20);
		gridpane.setPadding(new Insets(10));
		gridpane.setHgap(10);
		gridpane.setVgap(20);
		bawah.setPadding(new Insets(10));
		bawah.setHgap(30);
		bawah.setVgap(20);
		radio.setHgap(30);
		
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
		


		
		stage.setScene(scene);
		stage.setTitle("Register");
		stage.setResizable(false);
		stage.show();
	}
	
	private String getRandomID() {
		Random rand = new Random();
		String randomID = "US" + ""
		+ rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10);;
		
		return randomID;
	}
	
	private void addData(String userID, String userName, String userPassword, String userEmail, String userAddress,
			String userGender, String userRole) {
		// Insert to DB
		userID = getRandomID();
		String query = "INSERT INTO users "
				+ "VALUES ('"+userID+"','"+userName+"','"+userPassword+"','"+userEmail+"','"+userAddress+"','"+userGender+"','"+userRole+"')";
		connect.execUpdate(query);		
	}
	
	private void clear() {
		usernamefield.clear();
		mailfield.clear();
		passwordfield.clear();
		male.setSelected(false);
		female.setSelected(false);
		addressfield.clear();
	}
	
	

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == login) {
//			System.out.println("Next page");
			
			Stage curr = (Stage)login.getScene().getWindow();
			curr.close();
			
			//pindah halaman
			Stage next = new Stage();
			try {
				new LoginPage().start(next);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == reset) {
			clear();
		}
		
		else if (e.getSource() == register ) {
			String name,email,password,gender = "", address;
			String role = "User";
			
			name = usernamefield.getText();
			email = mailfield.getText();
			password = passwordfield.getText();
			if (group.getSelectedToggle() == male) {
				gender = "Male";
			} else if (group.getSelectedToggle() == female) {
				gender = "Female";
			}
			address = addressfield.getText();
			
			//KONDISI ALERT
			
			if (name.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty() || address.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Field can't be empty!");
				alert.show();
			}
			else if(name.length() < 5 || name.length() > 20) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Name length must be 5 - 20!");
				alert.show();
			}
			else if (email.contains("@.")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Email character '@' must not be next to '.'");
				alert.show();
			}else if (email.length()<9) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Email is not valid ");
				alert.show();
			}else if (email.startsWith("@") || email.startsWith(".") || email.endsWith("@") || email.endsWith(".")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Email must not start and ends with '@' nor '.' ");
				alert.show();
			} else if (!email.endsWith(".com")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Email must end with '.com'");
				alert.show();
			}else if (password.length() < 5 || password.length() > 20) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Password must 5 - 20 length of character!");
				alert.show();
			} else if (gender.equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Gender must be choosed!");
				alert.show();
			} else if (!address.endsWith("Street")){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Address must end with 'Street'!");
				alert.show();
			} else {
				addData(id, name, password, email,address, gender, role );
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Register Succeed");
				alert.showAndWait();
				clear();
			}
				
			
		}
		
	}

}
