package seoulWifi;

import java.util.List;
import lombok.Data;

public class TbPublicWifiInfo {
    public WifiInfo TbPublicWifiInfo;
    
    @Data
    public class WifiInfo {
    	public int list_total_count;
    	public Result RESULT;
    	public List<Wifi> row;
        
        @Data
        public class Result {
        	public String CODE;
        	public String MESSAGE;
        }
    }
}