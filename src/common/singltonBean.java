package common;

import handler.DestinationHandler;
import handler.HotelInfoHandler;
import handler.HotelPricingInfoHandler;
import handler.HotelUrlsHandler;
import handler.OfferDateRangeHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import DO.AbstractDO;
import DO.DestenationDO;
import DO.HotelDO;
import DO.HotelInfoDO;
import DO.HotelPricingInfoDO;
import DO.HotelUrlsDO;
import DO.OfferDateRangeDO;
import common.Constants;


//
//@Singleton
//@Startup

@ManagedBean
@ApplicationScoped
public class singltonBean {
	
	private DestenationDO destenationDO;
	private OfferDateRangeDO offerDateRangeDO;
	private HotelInfoDO hotelInfoDO;
	private HotelPricingInfoDO hotelPricingInfoDO;
	private HotelUrlsDO hotelUrlsDO;
	private List<HotelDO> hotelDO;
	
	
	 HashMap<String,DestenationDO> destinationHashMap;
	 HashMap<String, OfferDateRangeDO> offerDateRangeHashMap ;
	 HashMap<String, HotelInfoDO> hotelInfoHashMap;
	 HashMap<String, HotelPricingInfoDO> hotelPricingInfoHashMap;
	 HashMap<String, HotelUrlsDO> hotelUrlsHashMap;
	 HashMap<String, Collection<AbstractDO>> hotelHashMap;
	 
	
	 private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	 public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	     
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  
//	  @PostConstruct
	  public void init() throws JSONException, IOException, ParseException{
		  initAll();
		  JSONObject json = readJsonFromUrl(Constants.JSON_API);
		  JSONArray hotels =  json.getJSONObject("offers").getJSONArray("Hotel");
		  
		  for (int i = 0; i < hotels.length(); i++)
		   {
		    	String destination = hotels.getJSONObject(i).get("destination").toString();
		    	String offerDateRange = hotels.getJSONObject(i).get("offerDateRange").toString();
		    	String hotelInfo = hotels.getJSONObject(i).get("hotelInfo").toString();
		    	String hotelPricingInfo = hotels.getJSONObject(i).get("hotelPricingInfo").toString();
		    	String hotelUrls = hotels.getJSONObject(i).get("hotelUrls").toString();
		    	
			    JSONObject destinationDetails = new JSONObject(destination); 
			    JSONObject offerDateRangeDetails = new JSONObject(offerDateRange); 
			    JSONObject hotelInfoDetails = new JSONObject(hotelInfo); 
			    JSONObject hotelPricingInfoDetails = new JSONObject(hotelPricingInfo); 
			    JSONObject hotelUrlsDetails = new JSONObject(hotelUrls); 

			    destenationDO= (DestenationDO) DestinationHandler.fillDO(destinationDetails);
			    offerDateRangeDO= (OfferDateRangeDO) OfferDateRangeHandler.fillDO(offerDateRangeDetails);
			    hotelInfoDO= (HotelInfoDO) HotelInfoHandler.fillDO(hotelInfoDetails);
			    hotelPricingInfoDO= (HotelPricingInfoDO) HotelPricingInfoHandler.fillDO(hotelPricingInfoDetails);
			    hotelUrlsDO= (HotelUrlsDO) HotelUrlsHandler.fillDO(hotelUrlsDetails);
			    
			    destinationHashMap.put(hotelInfoDO.getHotelId(),destenationDO);
			    offerDateRangeHashMap.put(hotelInfoDO.getHotelId(),offerDateRangeDO);
			    hotelInfoHashMap.put(hotelInfoDO.getHotelId(),hotelInfoDO);
			    hotelPricingInfoHashMap.put(hotelInfoDO.getHotelId(),hotelPricingInfoDO);
			    hotelUrlsHashMap.put(hotelInfoDO.getHotelId(),hotelUrlsDO);
			    Collection hotelDetailAll=new ArrayList<AbstractDO>();
			    hotelDetailAll.add(destenationDO);
			    hotelDetailAll.add(offerDateRangeDO);
			    hotelDetailAll.add(hotelInfoDO);
			    hotelDetailAll.add(hotelPricingInfoDO);
			    hotelDetailAll.add(hotelUrlsDO);
			    
				  hotelHashMap.put(hotelInfoDO.getHotelId(), hotelDetailAll);
				  HotelDO hotelDetails=new HotelDO();
				  hotelDetails.setDestenationDO(destenationDO);
				  hotelDetails.setHotelInfoDO(hotelInfoDO);
				  hotelDetails.setHotelPricingInfoDO(hotelPricingInfoDO);
				  hotelDetails.setHotelUrlsDO(hotelUrlsDO);
				  hotelDetails.setOfferDateRangeDO(offerDateRangeDO);
				  
				
					hotelHashMap.put(hotelInfoDO.getHotelId(), hotelDetailAll);
					 hotelDO.add(hotelDetails);
					 }	  
		  Constants.setDestinationHashMap(destinationHashMap);
		  Constants.setHotelInfoHashMap(hotelInfoHashMap);
		  Constants.setHotelPricingInfoHashMap(hotelPricingInfoHashMap);
		  Constants.setHotelUrlsHashMap(hotelUrlsHashMap);
		  Constants.setOfferDateRangeHashMap(offerDateRangeHashMap);
		  Constants.setHotelHashMap(hotelHashMap);
		  Constants.setAllHotels(hotelDO);
	  }
	  private void initAll(){
		    destenationDO= new DestenationDO();
		    offerDateRangeDO= new OfferDateRangeDO();
		    hotelInfoDO= new HotelInfoDO();
		    hotelPricingInfoDO= new HotelPricingInfoDO();
		    hotelUrlsDO= new HotelUrlsDO();
		    
		   destinationHashMap = new HashMap<String, DestenationDO>();
		     offerDateRangeHashMap = new HashMap<String, OfferDateRangeDO>();
		    hotelInfoHashMap = new HashMap<String, HotelInfoDO>();
		    hotelPricingInfoHashMap = new HashMap<String, HotelPricingInfoDO>();
		    hotelUrlsHashMap = new HashMap<String, HotelUrlsDO>();
		    hotelHashMap=new HashMap<String, Collection<AbstractDO>>();
		    
		    hotelDO= new ArrayList<HotelDO>();
		
		}
	public DestenationDO getDestenationDO() {
		return destenationDO;
	}

	public void setDestenationDO(DestenationDO destenationDO) {
		this.destenationDO = destenationDO;
	}

	public OfferDateRangeDO getOfferDateRangeDO() {
		return offerDateRangeDO;
	}

	public void setOfferDateRangeDO(OfferDateRangeDO offerDateRangeDO) {
		this.offerDateRangeDO = offerDateRangeDO;
	}

	public HotelInfoDO getHotelInfoDO() {
		return hotelInfoDO;
	}

	public void setHotelInfoDO(HotelInfoDO hotelInfoDO) {
		this.hotelInfoDO = hotelInfoDO;
	}

	public HotelPricingInfoDO getHotelPricingInfoDO() {
		return hotelPricingInfoDO;
	}

	public void setHotelPricingInfoDO(HotelPricingInfoDO hotelPricingInfoDO) {
		this.hotelPricingInfoDO = hotelPricingInfoDO;
	}

	public HotelUrlsDO getHotelUrlsDO() {
		return hotelUrlsDO;
	}

	public void setHotelUrlsDO(HotelUrlsDO hotelUrlsDO) {
		this.hotelUrlsDO = hotelUrlsDO;
	}

	public HashMap<String, DestenationDO> getDestinationHashMap() {
		return destinationHashMap;
	}

	public void setDestinationHashMap(
			HashMap<String, DestenationDO> destinationHashMap) {
		this.destinationHashMap = destinationHashMap;
	}

	public HashMap<String, OfferDateRangeDO> getOfferDateRangeHashMap() {
		return offerDateRangeHashMap;
	}

	public void setOfferDateRangeHashMap(
			HashMap<String, OfferDateRangeDO> offerDateRangeHashMap) {
		this.offerDateRangeHashMap = offerDateRangeHashMap;
	}

	public HashMap<String, HotelInfoDO> getHotelInfoHashMap() {
		return hotelInfoHashMap;
	}

	public void setHotelInfoHashMap(HashMap<String, HotelInfoDO> hotelInfoHashMap) {
		this.hotelInfoHashMap = hotelInfoHashMap;
	}

	public HashMap<String, HotelPricingInfoDO> getHotelPricingInfoHashMap() {
		return hotelPricingInfoHashMap;
	}

	public void setHotelPricingInfoHashMap(
			HashMap<String, HotelPricingInfoDO> hotelPricingInfoHashMap) {
		this.hotelPricingInfoHashMap = hotelPricingInfoHashMap;
	}

	public HashMap<String, HotelUrlsDO> getHotelUrlsHashMap() {
		return hotelUrlsHashMap;
	}

	public void setHotelUrlsHashMap(HashMap<String, HotelUrlsDO> hotelUrlsHashMap) {
		this.hotelUrlsHashMap = hotelUrlsHashMap;
	}

	

	public List<HotelDO> getHotelDO() {
		return hotelDO;
	}

	public void setHotelDO(List<HotelDO> hotelDO) {
		this.hotelDO = hotelDO;
	}

	public HashMap<String, Collection<AbstractDO>> getHotelHashMap() {
		return hotelHashMap;
	}

	public void setHotelHashMap(HashMap<String, Collection<AbstractDO>> hotelHashMap) {
		this.hotelHashMap = hotelHashMap;
	}
	  
}
