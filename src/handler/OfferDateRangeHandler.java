package handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.Constants;
import DO.AbstractDO;
import DO.OfferDateRangeDO;

public class OfferDateRangeHandler{

	static int year;
	static int month;
	static int day;
	public static AbstractDO fillDO(JSONObject offerDateRangeDetails) throws JSONException, ParseException{
		OfferDateRangeDO offerDateRangeDO = new OfferDateRangeDO();

		offerDateRangeDO.setLengthOfStay(offerDateRangeDetails.getInt("lengthOfStay"));
		JSONArray travelEndDateArray = offerDateRangeDetails.getJSONArray("travelEndDate");
		JSONArray travelStartDateArray = offerDateRangeDetails.getJSONArray("travelStartDate");
		
		dateFormat(travelEndDateArray);
	  SimpleDateFormat simpleFormat=new SimpleDateFormat(Constants.DATE_FORMAT);
		offerDateRangeDO.setTravelEndDate(simpleFormat.parse(dateFormat(travelEndDateArray)));
		offerDateRangeDO.setTravelStartDate(simpleFormat.parse(dateFormat(travelStartDateArray)));
//		offerDateRangeDO.setTravelEndDate(dateFormat(travelEndDateArray));
//		offerDateRangeDO.setTravelStartDate(dateFormat(travelStartDateArray));
    		
		return offerDateRangeDO;
	}
	 public static boolean checkDate(OfferDateRangeDO offerDateRangeDO,Date startDate_cc, Date endDate_cc ,String lengthOfStay ){
		  if(offerDateRangeDO.getTravelStartDate().equals(startDate_cc) && offerDateRangeDO.getTravelEndDate().equals(endDate_cc)
				  && offerDateRangeDO.getLengthOfStay() == Integer.parseInt(lengthOfStay)){
			  return true;
		  }
		  return false;
	  }
//	 public static LocalDate dateFormat(JSONArray jsonArray){
//		 year=Integer.parseInt(jsonArray.get(0).toString());
//		 month=Integer.parseInt(jsonArray.get(1).toString());
//		 day=Integer.parseInt(jsonArray.get(2).toString());
//		 LocalDate ld = LocalDate.of(year, month, day);
//		 format(ld, Constants.DATE_FORMAT);
//		 return ld;
//	 }
	public static String dateFormat(JSONArray jsonArray){
		 year=Integer.parseInt(jsonArray.get(0).toString());
		 month=Integer.parseInt(jsonArray.get(1).toString());
		 day=Integer.parseInt(jsonArray.get(2).toString());
		 return day+"/"+month+"/"+year;
	}
	
	  public static void format(Temporal co, String pattern) {
		    DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
		    String str = fmt.format(co);
		    System.out.println(pattern + ": " + str);
		  }
	
	public static boolean equality(Date date1, Date date2){
		
		return true;
	}
	public static int getYear() {
		return year;
	}

	public static void setYear(int year) {
		OfferDateRangeHandler.year = year;
	}

	public static int getMonth() {
		return month;
	}

	public static void setMonth(int month) {
		OfferDateRangeHandler.month = month;
	}

	public static int getDay() {
		return day;
	}

	public static void setDay(int day) {
		OfferDateRangeHandler.day = day;
	}

	
}
