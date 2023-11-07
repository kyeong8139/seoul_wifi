package locationHistory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBService {
	
	public static void insert(String lat, String lnt, String time) {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);
			
			String sql = "INSERT INTO HISTORY "
					+ "(USER_X, USER_Y, REGISTER_DATE) "
					+ "VALUES "
					+ "(?, ?, ?);";
			
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, Double.parseDouble(lat));
			preparedStatement.setDouble(2, Double.parseDouble(lnt));
			preparedStatement.setString(3, time);
			
			int affected = preparedStatement.executeUpdate();
			if (affected == 0) {
				System.out.println("데이터 업데이트에 오류가 발생했습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList<Location> select() {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<Location> result = new ArrayList<>();
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql = "SELECT "
					+ "USER_ID, USER_X, USER_Y, REGISTER_DATE "
					+ "FROM HISTORY";
			
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Location location = new Location(); 
				location.setUserId(rs.getString("USER_ID"));
				location.setUserX(rs.getDouble("USER_X"));
				location.setUserY(rs.getDouble("USER_Y"));
				location.setRegisterDate(rs.getString("REGISTER_DATE"));
				
				result.add(location);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static int getLastID() {

		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql = "SELECT count(*) "
					+ "FROM HISTORY;";
			
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static void delete(int userId) {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);
			
			String sql = "DELETE FROM HISTORY "
					+ "WHERE HISTORY.USER_ID = ? ";
			
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, userId);
			
			int affected = preparedStatement.executeUpdate();
			if (affected == 0) {
				System.out.println("데이터 삭제에 오류가 발생했습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			try {
				if(connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
