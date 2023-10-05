import java.sql.*;
public class DBConnection {
	private final static String URL = "jdbc:postgresql://qatrain1.prosp.devexperts.com/qatrain";
	private final static String USER = "qatrain_core";
	private final static String PASSWORD = "qatrain_core";
	public static final String QUERY = "select\n"
									   + "\te.first_name,\n"
									   + "\te.last_name,\n"
									   + "\td.department_name,\n"
									   + "\tl.city,\n"
									   + "\tl.state_province\n"
									   + "from\n"
									   + "\temployees e\n"
									   + "join departments d on\n"
									   + "\te.department_id = d.department_id\n"
									   + "join locations l on\n"
									   + "\tl.location_id = d.location_id;";

	public static void connectAndFetch() {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			 Statement stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(QUERY))
		{
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("|" + rs.getString("first_name"));
				System.out.print("|" + rs.getString("last_name"));
				System.out.print("|" + rs.getString("department_name"));
				System.out.print("|" + rs.getString("city"));
				System.out.println("|" + rs.getString("state_province") + "\t|");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		connectAndFetch();
	}
}