package handler;

import org.json.JSONObject;

import DO.AbstractDO;
import DO.HotelInfoDO;

public class HotelInfoHandler {
	
	
	public  static AbstractDO fillDO(JSONObject hotelInfoDetails){
	HotelInfoDO hotelInfoDO = new HotelInfoDO();

	hotelInfoDO.setHotelCity(hotelInfoDetails.getString("hotelCity"));
	hotelInfoDO.setHotelCountryCode(hotelInfoDetails.getString("hotelCountryCode"));
	hotelInfoDO.setHotelDestination(hotelInfoDetails.getString("hotelDestination"));
	hotelInfoDO.setHotelDestinationRegionID(hotelInfoDetails.getString("hotelDestinationRegionID"));
	hotelInfoDO.setHotelGuestReviewRating(hotelInfoDetails.get("hotelGuestReviewRating").toString());
	hotelInfoDO.setHotelId(hotelInfoDetails.get("hotelId").toString());
	hotelInfoDO.setHotelImageUrl(hotelInfoDetails.get("hotelImageUrl").toString());
	hotelInfoDO.setHotelLatitude(hotelInfoDetails.get("hotelLatitude").toString());
	hotelInfoDO.setHotelLongDestination(hotelInfoDetails.get("hotelLongDestination").toString());
	hotelInfoDO.setHotelLongitude(hotelInfoDetails.get("hotelLongitude").toString());
	hotelInfoDO.setHotelName(hotelInfoDetails.get("hotelName").toString());
	hotelInfoDO.setHotelProvince(hotelInfoDetails.get("hotelProvince").toString());
	hotelInfoDO.setHotelReviewTotal(hotelInfoDetails.get("hotelReviewTotal").toString());
	hotelInfoDO.setHotelStarRating(hotelInfoDetails.get("hotelStarRating").toString());
	hotelInfoDO.setHotelStreetAddress(hotelInfoDetails.get("hotelStreetAddress").toString());
	hotelInfoDO.setIsOfficialRating(hotelInfoDetails.get("isOfficialRating").toString());
	hotelInfoDO.setLocalizedHotelName(hotelInfoDetails.get("localizedHotelName").toString());
	hotelInfoDO.setVipAccess(hotelInfoDetails.get("vipAccess").toString());
	
	return hotelInfoDO;
}
	 public static boolean checkStarRate(HotelInfoDO hotelInfoDO,String minStarRate,String maxStarRate){
		 Double starRating= Double.parseDouble(hotelInfoDO.getHotelStarRating());


		  if(minStarRate!=null){
			  if(maxStarRate!=null){
				  if(Integer.parseInt(minStarRate)<= starRating && Integer.parseInt(maxStarRate)>= starRating){
					  return true;
					  }
			  		}
				  else if(Integer.parseInt(minStarRate)<= starRating){
					  return true;
				  }
	  }else if(maxStarRate!=null){
		  if(Integer.parseInt(maxStarRate)>= starRating){
			  return true;
			  }
	  		}
		 return false;
	  }
	  public static boolean checkGuestRating(HotelInfoDO hotelInfoDO,String minGuestRating, String maxGuestRating){
		  Double guestRating= Double.parseDouble(hotelInfoDO.getHotelGuestReviewRating());
		  if(minGuestRating!=null){
			  if(maxGuestRating!=null){
				  if(Integer.parseInt(minGuestRating)<= guestRating && Integer.parseInt(maxGuestRating)>= guestRating){
					  return true;
				  }
			  }
			  else if(Integer.parseInt(minGuestRating)<= guestRating){
				  return true;
			  }
		  }else if(maxGuestRating!=null){
			  if(Integer.parseInt(maxGuestRating)>= guestRating){
				  return true;
			  }
		  }
		  return false;
		  
	  }
	  public static boolean checkTotalRating(HotelInfoDO hotelInfoDO,String minTotalRate, String maxTotalRate){
		  Double totalReview= Double.parseDouble(hotelInfoDO.getHotelReviewTotal());
		  if(minTotalRate!=null){
			  if(maxTotalRate!=null){
				  if(Integer.parseInt(minTotalRate)<= totalReview && Integer.parseInt(maxTotalRate)>= totalReview){
					  return true;
					  }
			  		}
				  else if(Integer.parseInt(minTotalRate)<= totalReview){
					  return true;
				  }
		  }  else if(maxTotalRate!=null){
			  		if(Integer.parseInt(maxTotalRate)>= totalReview){
			  			return true;
			 }
	  		}
		  return false;
	  }
 

}
