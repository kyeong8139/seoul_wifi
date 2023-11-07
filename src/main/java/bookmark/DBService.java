package bookmark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBService {
	
	public static void insert(Bookmark bookmark) {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		bookmark.setRegisterDate(formatedNow);
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);
			
			String sql = "INSERT INTO BOOKMARK "
					+ "(GROUP_ID, MGR_NO, REGISTER_DATE) "
					+ "VALUES "
					+ "(?, ?, ?);";
			
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bookmark.getGroupId());
			preparedStatement.setString(2, bookmark.getMgrNo());
			preparedStatement.setString(3, bookmark.getRegisterDate());
			
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
	
	public static ArrayList<Bookmark> selectAll() {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<Bookmark> result = new ArrayList<>();
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql = "SELECT "
					+ "MARK_ID, GROUP_ID, MGR_NO, REGISTER_DATE "
					+ "FROM BOOKMARK";
			
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Bookmark bookmark = new Bookmark(); 
				bookmark.setMarkId(rs.getInt("MARK_ID"));
				bookmark.setGroupId(rs.getInt("GROUP_ID"));
				bookmark.setMgrNo(rs.getString("MGR_NO"));
				bookmark.setRegisterDate(rs.getString("REGISTER_DATE"));
				
				result.add(bookmark);
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
	
	public static Bookmark select(int id) {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		Bookmark bookmark = new Bookmark(); 
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql = "SELECT "
					+ "MARK_ID, GROUP_ID, MGR_NO, REGISTER_DATE "
					+ "FROM BOOKMARK "
					+ "WHERE MARK_ID = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			
			bookmark.setMarkId(rs.getInt("MARK_ID"));
			bookmark.setGroupId(rs.getInt("GROUP_ID"));
			bookmark.setMgrNo(rs.getString("MGR_NO"));
			bookmark.setRegisterDate(rs.getString("REGISTER_DATE"));
			
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
		
		return bookmark;
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
			
			String sql = "DELETE FROM BOOKMARK "
					+ "WHERE MARK_ID = ? ";
					
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
