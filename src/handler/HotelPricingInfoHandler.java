package handler;

import org.json.JSONObject;

import DO.AbstractDO;
import DO.HotelPricingInfoDO;

public class HotelPricingInfoHandler{
	
	
	public static AbstractDO fillDO(JSONObject hotelPricingInfoDetails){
		HotelPricingInfoDO hotelPricingInfoDO = new HotelPricingInfoDO();

		hotelPricingInfoDO.setAveragePriceValue(hotelPricingInfoDetails.get("averagePriceValue").toString());
    	hotelPricingInfoDO.setCrossOutPriceValue(hotelPricingInfoDetails.get("crossOutPriceValue").toString());
    	hotelPricingInfoDO.setCurrency(hotelPricingInfoDetails.get("currency").toString());
    	hotelPricingInfoDO.setDrr(hotelPricingInfoDetails.get("drr").toString());
    	hotelPricingInfoDO.setOriginalPricePerNight(hotelPricingInfoDetails.get("originalPricePerNight").toString());
    	hotelPricingInfoDO.setPercentSavings(hotelPricingInfoDetails.get("percentSavings").toString());
		hotelPricingInfoDO.setTotalPriceValue(hotelPricingInfoDetails.get("totalPriceValue").toString());
		
		return hotelPricingInfoDO;
	}
}
