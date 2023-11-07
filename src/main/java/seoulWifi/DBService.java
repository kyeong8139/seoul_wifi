package seoulWifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class DBService {

	public static void insert(List<Wifi> list) {
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
			
			String sql = "INSERT INTO SEOUL_WIFI "
					+ "(MGR_NO, MAIN_NM, LNT, LAT, WRDOFC, ADRES1, ADRES2, "
					+ "INSTL_FLOOR, INSTL_TY, CMCWR, INOUT_DOOR, INSTL_MBY, "
					+ "SVC_SE, REMARS3, CNSTC_YEAR, WORK_DTTM) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?) "
					+ ";";
			
			for (Wifi wifi : list) {
				Double lnt = wifi.getLnt() != null ? Double.parseDouble(wifi.getLnt()) : 0.0; // 또는 다른 기본값
				Double lat = wifi.getLat() != null ? Double.parseDouble(wifi.getLat()) : 0.0; // 또는 다른 기본값
				Integer cnstcYear = wifi.getX_swifi_cnstc_year() != null ? Integer.parseInt(wifi.getX_swifi_cnstc_year()) : null;
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, wifi.getX_swifi_mgr_no());
				preparedStatement.setString(2, wifi.getX_swifi_main_nm());
				preparedStatement.setDouble(3, lnt);
				preparedStatement.setDouble(4, lat);
				preparedStatement.setString(5, wifi.getX_swifi_wrdofc());
				preparedStatement.setString(6, wifi.getX_swifi_adres1());
				preparedStatement.setString(7, wifi.getX_swifi_adres2());
				preparedStatement.setString(8, wifi.getX_swifi_instl_floor());
				preparedStatement.setString(9, wifi.getX_swifi_instl_ty());
				preparedStatement.setString(10, wifi.getX_swifi_cmcwr());
				preparedStatement.setString(11, wifi.getX_swifi_inout_door());
				preparedStatement.setString(12, wifi.getX_swifi_instl_mby());
				preparedStatement.setString(13, wifi.getX_swifi_svc_se());
				preparedStatement.setString(14, wifi.getX_swifi_remars3());
				preparedStatement.setInt(15, cnstcYear);
				preparedStatement.setString(16, wifi.getWork_dttm());
				
				int affected = preparedStatement.executeUpdate();
				if (affected == 0) {
					System.out.println("데이터 업데이트에 오류가 발생했습니다.");
				}
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
	
	public static ArrayList<Wifi> selectAll() {

		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<Wifi> result = new ArrayList<>();
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql = "SELECT "
					+ "MGR_NO, LNT, LAT "
					+ "FROM SEOUL_WIFI";
			
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Wifi wifi = new Wifi();
				wifi.setX_swifi_mgr_no(rs.getString("MGR_NO"));
				wifi.setLnt(String.valueOf(rs.getDouble("LNT")));
				wifi.setLat(String.valueOf(rs.getDouble("LAT")));
				
				result.add(wifi);
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
	
	public static ArrayList<Wifi> selectNearWifi(int count) {

		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<Wifi> result = new ArrayList<>();
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql ="SELECT * "
					+ "FROM SEOUL_WIFI "
					+ "ORDER BY Distance "
					+ "limit ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, count);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Wifi wifi = new Wifi();
				wifi.setX_swifi_mgr_no(rs.getString("MGR_NO"));
				wifi.setX_swifi_main_nm(rs.getString("MAIN_NM"));
				wifi.setLnt(String.valueOf(rs.getDouble("LNT")));
				wifi.setLat(String.valueOf(rs.getDouble("LAT")));
				wifi.setX_swifi_wrdofc(rs.getString("WRDOFC"));
				wifi.setX_swifi_adres1(rs.getString("ADRES1"));
				wifi.setX_swifi_adres2(rs.getString("ADRES2"));
				wifi.setX_swifi_instl_floor(rs.getString("INSTL_FLOOR"));
				wifi.setX_swifi_instl_ty(rs.getString("INSTL_TY"));
				wifi.setX_swifi_cmcwr(rs.getString("CMCWR"));
				wifi.setX_swifi_inout_door(rs.getString("INOUT_DOOR"));
				wifi.setX_swifi_instl_mby(rs.getString("INSTL_MBY"));
				wifi.setX_swifi_svc_se(rs.getString("SVC_SE"));
				wifi.setX_swifi_remars3(rs.getString("REMARS3"));
				wifi.setX_swifi_cnstc_year(String.valueOf(rs.getString("CNSTC_YEAR")));
				wifi.setWork_dttm(rs.getString("WORK_DTTM"));
				wifi.setDistance(rs.getString("Distance"));
				
				result.add(wifi);
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
	
	public static Wifi selectWifi(String mgr_no) {

		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Wifi result = new Wifi();
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql ="SELECT * "
					+ "FROM SEOUL_WIFI "
					+ "WHERE MGR_NO = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mgr_no);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				result.setX_swifi_mgr_no(rs.getString("MGR_NO"));
				result.setX_swifi_main_nm(rs.getString("MAIN_NM"));
				result.setLnt(String.valueOf(rs.getDouble("LNT")));
				result.setLat(String.valueOf(rs.getDouble("LAT")));
				result.setX_swifi_wrdofc(rs.getString("WRDOFC"));
				result.setX_swifi_adres1(rs.getString("ADRES1"));
				result.setX_swifi_adres2(rs.getString("ADRES2"));
				result.setX_swifi_instl_floor(rs.getString("INSTL_FLOOR"));
				result.setX_swifi_instl_ty(rs.getString("INSTL_TY"));
				result.setX_swifi_cmcwr(rs.getString("CMCWR"));
				result.setX_swifi_inout_door(rs.getString("INOUT_DOOR"));
				result.setX_swifi_instl_mby(rs.getString("INSTL_MBY"));
				result.setX_swifi_svc_se(rs.getString("SVC_SE"));
				result.setX_swifi_remars3(rs.getString("REMARS3"));
				result.setX_swifi_cnstc_year(String.valueOf(rs.getString("CNSTC_YEAR")));
				result.setWork_dttm(rs.getString("WORK_DTTM"));
				result.setDistance(rs.getString("Distance"));
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

	public static void updateDistance(double userX, double userY) {
		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Wifi> list = selectAll();
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(url);

			String sql = "UPDATE SEOUL_WIFI "
					+ "SET DISTANCE = ? "
					+ "WHERE MGR_NO = ? ";
			
			for (Wifi wifi : list) {
				
				double wifiX = Double.parseDouble(wifi.getLnt());
				double wifiY = Double.parseDouble(wifi.getLat());
				
				double distance = Distance.getDistance(userX, userY, wifiX, wifiY);
								
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setDouble(1, distance);
				preparedStatement.setString(2, wifi.getX_swifi_mgr_no());
				
				int affected = preparedStatement.executeUpdate();
				if (affected == 0) {
					System.out.println("데이터 업데이트에 오류가 발생했습니다.");
				}
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
	
	public static void load() {
		
		String key = "6e73486f436b79653130354d66626449";
		String type = "json";
		String service = "TbPublicWifiInfo";
		int startIdx = 1;
		int step = 999;
		int endIdx = startIdx + step;
		int max = 23416;
		TbPublicWifiInfo apiRequest = null;
		BufferedReader br = null;
		
		do {
			
			String urlString = "http://openapi.seoul.go.kr:8088/"
					+ key + "/" + type + "/" + service + "/" 
					+ startIdx + "/" + endIdx + "/";	
			
			try {
				URL url = new URL(urlString);
				
				URLConnection connection = url.openConnection();
				
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String json = "";
				while (true) {
					String temp = br.readLine();
					
					if (temp == null) {
						break;
					} else {
						json += temp;
					}
				}
					
				
				Gson gson = new Gson();
				apiRequest = gson.fromJson(json, TbPublicWifiInfo.class);
							
				if (apiRequest.TbPublicWifiInfo != null && apiRequest.TbPublicWifiInfo.RESULT.CODE.equals("INFO-000")) {
					List<Wifi> wifiList = apiRequest.TbPublicWifiInfo.row;
					insert(wifiList);
					
				} else {
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			startIdx = endIdx + 1;
			if (startIdx > max) {
				break;
			}
			
			endIdx = Math.min((startIdx + step), max);
					
		} while(true);
	
	}
	
	public static int countRow() {

		String url = "jdbc:sqlite:C:\\dev\\sqlite3\\WIFI_INFO.db";
		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int result = 0; 
		
		try {
			connection = DriverManager.getConnection(url);
			
			String sql = "SELECT COUNT(*) FROM SEOUL_WIFI";
			
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
}

