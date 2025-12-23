package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class DBUtils {

	private static final String URL = "jdbc:mysql://unitest.cluster-cyjhdece3srq.eu-west-1.rds.amazonaws.com:3306/DFLUniTest";
	private static final String USER = "satnam";
	private static final String PASSWORD = "gJzV-zf77@9Pt4kh";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("✅ Connected to Database");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void fetchData(String query) {
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fetchAllData(String query) {
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			int columnCount = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				StringBuilder row = new StringBuilder();
				for (int i = 1; i <= columnCount; i++) {
					row.append(rs.getMetaData().getColumnName(i)).append(": ").append(rs.getString(i)).append(" | ");
				}
				System.out.println(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getColumnValueFromDB(String query, String columnName) {
		List<String> values = new ArrayList<>();

		try (Connection con = getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				values.add(rs.getString(columnName));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return values;
	}

	public static JSONObject getBreakdownResponse(String query) {
		JSONObject responseJson = null;

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			if (rs.next()) {
				String jsonString = rs.getString("prodprice_ask_total_price");
				responseJson = new JSONObject(jsonString);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseJson;
	}
}
