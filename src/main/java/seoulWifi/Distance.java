package seoulWifi;

public class Distance {
	
	final static int EARTH_RADIUS = 6371;
	
	public static double getDistance(double lat1, double lnt1, double lat2, double lnt2) {
		  double dLat = Math.toRadians(lat2 - lat1);
		  double dLnt = Math.toRadians(lnt2 - lnt1);
		  
		  double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(lat1))* Math.cos(Math.toRadians(lat2))* Math.sin(dLnt/2)* Math.sin(dLnt/2);
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		  double d = EARTH_RADIUS * c; //km
		  return Math.round(d * 10000) / 10000.0;
		}
}
