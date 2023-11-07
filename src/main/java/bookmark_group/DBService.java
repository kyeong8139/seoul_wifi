package bookmark_group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBService {
	
	public static void insert(Group group) {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		group.setRegisterDate(formatedNow);
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);
			
			String sql = "INSERT INTO BOOKMARK_GROUP "
					+ "(NAME, ORDER_ROW, REGISTER_DATE) "
					+ "VALUES "
					+ "(?, ?, ?);";
			
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, group.getName());
			preparedStatement.setInt(2, group.getOrderRow());
			preparedStatement.setString(3, group.getRegisterDate());
			
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
	
	public static ArrayList<Group> selectAll() {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<Group> result = new ArrayList<>();
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql = "SELECT "
					+ "GROUP_ID, NAME, ORDER_ROW, REGISTER_DATE, EDIT_DATE "
					+ "FROM BOOKMARK_GROUP "
					+ "ORDER BY ORDER_ROW ";
			
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Group group = new Group(); 
				group.setId(rs.getInt("GROUP_ID"));
				group.setName(rs.getString("NAME"));
				group.setOrderRow(rs.getInt("ORDER_ROW"));
				group.setRegisterDate(rs.getString("REGISTER_DATE"));
				group.setEditDate(rs.getString("Edit_DATE"));
				
				result.add(group);
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
	
	public static Group select(int id) {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		Group group = new Group(); 
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql = "SELECT "
					+ "GROUP_ID, NAME, ORDER_ROW, REGISTER_DATE, EDIT_DATE "
					+ "FROM BOOKMARK_GROUP "
					+ "WHERE GROUP_ID = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			
			group.setId(rs.getInt("GROUP_ID"));
			group.setName(rs.getString("NAME"));
			group.setOrderRow(rs.getInt("ORDER_ROW"));
			group.setRegisterDate(rs.getString("REGISTER_DATE"));
			group.setEditDate(rs.getString("EDIT_DATE"));
			
			
			
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
		
		return group;
	}
	
	public static void update(int id, Group group) {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);

			String sql = "UPDATE BOOKMARK_GROUP "
					+ "SET NAME = ?, ORDER_ROW = ?, EDIT_DATE = ? "
					+ "WHERE GROUP_ID = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, group.getName());
			preparedStatement.setInt(2, group.getOrderRow());
			preparedStatement.setString(3, formatedNow);
			preparedStatement.setInt(4, id);
			
			
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
	
	public static void delete(int id) {
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
			
			String sql = "DELETE FROM BOOKMARK_GROUP "
					+ "WHERE GROUP_ID = ? ";
					
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
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
