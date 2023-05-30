package util;
import java.sql.*;

//JDBC -> java database connectivity
	// API utk connect program kita n execute query dr database
	// bisa return row
	
	// pakai XAMPP

public class Connect {

	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "kpopztation";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST,DATABASE);
	
	public ResultSet rs;
	public ResultSetMetaData rsm;
	
	private Connection con;
	private Statement st;
	private static Connect connect;
	
	// membuat kls connect jd singleton
	public static Connect getInstance() {
		if (connect == null) {
			return new Connect();
		} else {
			return connect;
		}
	}
	
	private Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION,USERNAME,PASSWORD);
			st = con.createStatement();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return obj dari parameternya
		}
	}
	
	// utk select
	// masukan data yg direturn dari execQuery berupa row
	public ResultSet execQuery(String query) {
		try {
			rs = st.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	// utk insert
	public void execUpdate(String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// prepared statement
	public PreparedStatement preparedStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}

}
