package handler;

import org.json.JSONObject;

import DO.AbstractDO;
import DO.HotelUrlsDO;

public class HotelUrlsHandler{
	
	
	public static AbstractDO fillDO(JSONObject hotelUrlsDetails){
		HotelUrlsDO hotelUrlsDO= new HotelUrlsDO();
		
		hotelUrlsDO.setHotelInfositeUrl(hotelUrlsDetails.getString("hotelInfositeUrl"));
    	hotelUrlsDO.setHotelSearchResultUrl(hotelUrlsDetails.getString("hotelSearchResultUrl"));
    	
		return hotelUrlsDO;
	}
}
