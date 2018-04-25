package common;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import DO.AbstractDO;
import DO.DestenationDO;
import DO.HotelInfoDO;
import DO.HotelPricingInfoDO;
import DO.HotelUrlsDO;
import DO.OfferDateRangeDO;
import DO.HotelDO;

public class Constants {	
	
	public static final String JSON_API = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	private static 	 HashMap<String,DestenationDO> destinationHashMap;
	private static  HashMap<String, OfferDateRangeDO> offerDateRangeHashMap ;
	private static  HashMap<String, HotelInfoDO> hotelInfoHashMap;
	private static  HashMap<String, HotelPricingInfoDO> hotelPricingInfoHashMap;
	private static  HashMap<String, HotelUrlsDO> hotelUrlsHashMap;
	private static  HashMap<String, Collection<AbstractDO>> hotelHashMap;
	private static  List<HotelDO> allHotels;
	private static HashMap<String, DestenationDO> getDestinationHashMap() {
		return destinationHashMap;
	}
	public static void setDestinationHashMap(
			HashMap<String, DestenationDO> destinationHashMap) {
		Constants.destinationHashMap = destinationHashMap;
	}
	public static HashMap<String, OfferDateRangeDO> getOfferDateRangeHashMap() {
		return offerDateRangeHashMap;
	}
	public static void setOfferDateRangeHashMap(
			HashMap<String, OfferDateRangeDO> offerDateRangeHashMap) {
		Constants.offerDateRangeHashMap = offerDateRangeHashMap;
	}
	public static HashMap<String, HotelInfoDO> getHotelInfoHashMap() {
		return hotelInfoHashMap;
	}
	public static void setHotelInfoHashMap(
			HashMap<String, HotelInfoDO> hotelInfoHashMap) {
		Constants.hotelInfoHashMap = hotelInfoHashMap;
	}
	public static HashMap<String, HotelPricingInfoDO> getHotelPricingInfoHashMap() {
		return hotelPricingInfoHashMap;
	}
	public static void setHotelPricingInfoHashMap(
			HashMap<String, HotelPricingInfoDO> hotelPricingInfoHashMap) {
		Constants.hotelPricingInfoHashMap = hotelPricingInfoHashMap;
	}
	public static HashMap<String, HotelUrlsDO> getHotelUrlsHashMap() {
		return hotelUrlsHashMap;
	}
	public static void setHotelUrlsHashMap(
			HashMap<String, HotelUrlsDO> hotelUrlsHashMap) {
		Constants.hotelUrlsHashMap = hotelUrlsHashMap;
	}
	public static HashMap<String, Collection<AbstractDO>> getHotelHashMap() {
		return hotelHashMap;
	}
	public static void setHotelHashMap(
			HashMap<String, Collection<AbstractDO>> hotelHashMap) {
		Constants.hotelHashMap = hotelHashMap;
	}
	public static List getAllHotels() {
		return allHotels;
	}
	public static void setAllHotels(List allHotels) {
		Constants.allHotels = allHotels;
	}


	
}
