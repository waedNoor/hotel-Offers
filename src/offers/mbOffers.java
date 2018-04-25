package offers;

import handler.DestinationHandler;
import handler.HotelInfoHandler;
import handler.OfferDateRangeHandler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import DO.AbstractDO;
import DO.DestenationDO;
import DO.HotelDO;
import DO.HotelInfoDO;
import DO.HotelPricingInfoDO;
import DO.HotelUrlsDO;
import DO.OfferDateRangeDO;
import common.Constants;

import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;


@ManagedBean
@RequestScoped
public class mbOffers implements Serializable {
	private static final long serialVersionUID = 1L;

	private String test;
	private DestenationDO destenationDO;
	private OfferDateRangeDO offerDateRangeDO;
	private HotelInfoDO hotelInfoDO;
	private HotelPricingInfoDO hotelPricingInfoDO;
	private HotelUrlsDO hotelUrlsDO;
	
	@ManagedProperty(value = "#{city_cc}")
	private String city_cc;
	
	@ManagedProperty(value = "#{startDate_cc}")
	private Date startDate_cc;
	
	@ManagedProperty(value = "#{endDate_cc}")
	private Date endDate_cc;
	
	@ManagedProperty(value = "#{showSearchPage}")
	private boolean showSearchPage;
	
	@ManagedProperty(value = "#{minStarRate}")
	private String minStarRate;
	
	@ManagedProperty(value = "#{maxStarRate}")
	private String maxStarRate;
	
	@ManagedProperty(value = "#{lengthOfStay}")
	private String lengthOfStay;
	
	@ManagedProperty(value = "#{minTotalRate}")
	private String minTotalRate;
	
	@ManagedProperty(value = "#{maxTotalRate}")
	private String maxTotalRate;
	
	@ManagedProperty(value = "#{minGuestRating}")
	private String minGuestRating;
	
	@ManagedProperty(value = "#{maxGuestRating}")
	private String maxGuestRating;
	
	@ManagedProperty(value = "#{starRate}")
	private List<String> starRate;
	
	@ManagedProperty(value = "#{defaultHotelList}")
	private List<HotelDO> defaultHotelList;
	
	@ManagedProperty(value = "#{dateFormat}")
	 private String dateFormat;
	
	private boolean citySkiped;
	private boolean startDateSkiped;
	private boolean endDateSkiped;
	private boolean lengthOfStaySkiped;
	private boolean minTotalRateSkiped;
	private boolean maxTotalRateSkiped;
	private boolean minStarRateSkiped;
	private boolean maxStarRateSkiped;
	private boolean minGuestRatingSkiped;
	private boolean maxGuestRatingSkiped;
	
	@ManagedProperty(value = "#{hotelResults}")
	private List<HotelDO> hotelResults;
	@PostConstruct
	public void init(){
		starRate= new ArrayList<String>();
		starRate.add("1");
		starRate.add("2");
		starRate.add("3");
		starRate.add("4");
		starRate.add("5");
		
		citySkiped=true;
		startDateSkiped=true;
		 endDateSkiped=true;
		lengthOfStaySkiped=true;
		minTotalRateSkiped=true;
		maxTotalRateSkiped=true;
		 minStarRateSkiped=true;
		 maxStarRateSkiped=true;
		minGuestRatingSkiped=true;
		 maxGuestRatingSkiped=true;
		 defaultHotelList=Constants.getAllHotels();
		 dateFormat= Constants.DATE_FORMAT;
		 
	}
	
	public void redirect() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().redirect("http://www.jboss.org");

	}
	  public void search(){
		  prepareSearchAction();
		  List hotelsIds=new ArrayList<String>();
		   hotelResults= new ArrayList<HotelDO>();


		  for(Map.Entry hotels :Constants.getHotelHashMap().entrySet()){ 
			  List<AbstractDO> hotel=(List) hotels.getValue();
			  boolean complete=false;
			  for(int i=0;i<hotel.size();i++){
				  switch(i){//false=skiped ==there is no value
				  case 0://destination
					  if(!citySkiped){//not null
						  boolean destinationMatch= DestinationHandler.checkCity((DestenationDO)hotel.get(0),city_cc);
						  if(!destinationMatch){
							  	i=	hotel.size();
							  	break;

						  }
					  }
				  case 1: 
					  if(!startDateSkiped && !endDateSkiped && !lengthOfStaySkiped){//not null
					  boolean dateMatch=  OfferDateRangeHandler.checkDate((OfferDateRangeDO)hotel.get(1),startDate_cc,endDate_cc,lengthOfStay);
					  if(!dateMatch){
						  i=hotel.size();
						  break;

					  }
					  }	
				  case 2:
					  if(!minStarRateSkiped ||  !maxStarRateSkiped && !minGuestRatingSkiped || !maxGuestRatingSkiped ||
							  !minTotalRateSkiped || !maxGuestRatingSkiped){//not null
						  boolean rateMatch=  checkHotelInfo((HotelInfoDO)hotel.get(2));
						  if(!rateMatch){
							  i=hotel.size();
							  break;

						  }
					  }
						default:
							  hotelsIds.add(((HotelInfoDO)hotel.get(2)).getHotelId());
							  HotelDO hotelMatch=new HotelDO();
							  hotelMatch.setDestenationDO((DestenationDO)hotel.get(0));
							  hotelMatch.setOfferDateRangeDO((OfferDateRangeDO)hotel.get(1));
							  hotelMatch.setHotelInfoDO((HotelInfoDO)hotel.get(2));
							  hotelMatch.setHotelPricingInfoDO((HotelPricingInfoDO)hotel.get(3));
							  hotelMatch.setHotelUrlsDO((HotelUrlsDO)hotel.get(4));
							  hotelResults.add(hotelMatch);
							  i=hotel.size();
						  
					  }	 
				  
				  }
				  
			  }
		  


		  
	 }
	public DestenationDO getDestenation(){
		
		return null;
	}
	  public boolean checkHotelInfo(HotelInfoDO hotelInfoDO){
		  
		  boolean guestRateCheck=true;
		  boolean totalRateCheck=true;
		  boolean starRateCheck=true;//to skip it if equal null

		  if(minStarRate!="" || maxStarRate!="")
			  starRateCheck= HotelInfoHandler.checkStarRate(hotelInfoDO,minStarRate,maxStarRate);
		  if(starRateCheck)
			 if(minGuestRating!="" || maxGuestRating!="")
				guestRateCheck=HotelInfoHandler.checkGuestRating(hotelInfoDO,minGuestRating,maxGuestRating);
			 if(guestRateCheck && starRateCheck)
				 if(minTotalRate!="" || maxTotalRate!="")
				  	totalRateCheck=HotelInfoHandler.checkTotalRating(hotelInfoDO,minTotalRate,maxTotalRate);
				  	 
		return starRateCheck&& guestRateCheck && totalRateCheck;
				 
				
	  }
	 

	  public void prepareSearchAction(){
		  if(city_cc!="")
			  citySkiped=false;
		  if(startDate_cc!=null)
			  startDateSkiped=false;
		  if(endDate_cc!=null)
			  endDateSkiped=false;
		  if(lengthOfStay!="")
			  lengthOfStaySkiped=false; 
		  if(minStarRate!="")
			 minStarRateSkiped=false;
		  if(maxStarRate!="")
			 maxStarRateSkiped=false; 
		 if(minGuestRating!="")
			minGuestRatingSkiped=false;
		 if(maxGuestRating!="")
			 maxGuestRatingSkiped=false;
		 if(minTotalRate!="")
				minTotalRateSkiped=false;
		  if(maxTotalRate!="")
				 maxTotalRateSkiped=false;
	  }
	    
	  public void loadMaxStarRate(){
    		starRate.clear();

	    	int min=Integer.parseInt(minStarRate);
	    	for(int i=min;i<=5;i++){
	    		String index=Integer.toString(i);
	    		starRate.add(index);
	    	}
	    	
	    }
	  public String calculateLengthOfStay(){
			if(endDate_cc !=null  && startDate_cc !=null){
				lengthOfStay= String.valueOf(endDate_cc.compareTo(startDate_cc));
			}
				return lengthOfStay;
	  }
	  public String getTest() {
			return test;
		}

		public void setTest(String test) {
			this.test = test;
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
		public String getCity_cc() {
			return city_cc;
		}
		public void setCity_cc(String city_cc) {
			this.city_cc = city_cc;
		}
		public Date getStartDate_cc() {
			return startDate_cc;
		}
		public void setStartDate_cc(Date startDate_cc) {
			this.startDate_cc = startDate_cc;
		}
		public Date getEndDate_cc() {
			return endDate_cc;
		}
		public void setEndDate_cc(Date endDate_cc) {
			this.endDate_cc = endDate_cc;
		}

		public boolean isShowSearchPage() {
			return showSearchPage;
		}

		public void setShowSearchPage(boolean showSearchPage) {
			this.showSearchPage = showSearchPage;
		}

		public String getMinStarRate() {
			return minStarRate;
		}
		public void setMinStarRate(String minStarRate) {
			this.minStarRate = minStarRate;
		}
		public String getMaxStarRate() {
			return maxStarRate;
		}
		public void setMaxStarRate(String maxStarRate) {
			this.maxStarRate = maxStarRate;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		public String getLengthOfStay() {
			if(endDate_cc !=null  && startDate_cc !=null){
				lengthOfStay= String.valueOf(endDate_cc.compareTo(startDate_cc));
			}
				return lengthOfStay;
		}
		public void setLengthOfStay(String lengthOfStay) {
			this.lengthOfStay = lengthOfStay;
		}
		public List<String> getStarRate() {
			return starRate;
		}
		public void setStarRate(List<String> starRate) {
			this.starRate = starRate;
		}
		public String getMinTotalRate() {
			return minTotalRate;
		}
		public void setMinTotalRate(String minTotalRate) {
			this.minTotalRate = minTotalRate;
		}
		public String getMaxTotalRate() {
			return maxTotalRate;
		}
		public void setMaxTotalRate(String maxTotalRate) {
			this.maxTotalRate = maxTotalRate;
		}
		public String getMinGuestRating() {
			return minGuestRating;
		}
		public void setMinGuestRating(String minGuestRating) {
			this.minGuestRating = minGuestRating;
		}
		public String getMaxGuestRating() {
			return maxGuestRating;
		}
		public void setMaxGuestRating(String maxGuestRating) {
			this.maxGuestRating = maxGuestRating;
		}
		public boolean isCitySkiped() {
			return citySkiped;
		}
		public void setCitySkiped(boolean citySkiped) {
			this.citySkiped = citySkiped;
		}
		public boolean isStartDateSkiped() {
			return startDateSkiped;
		}
		public void setStartDateSkiped(boolean startDateSkiped) {
			this.startDateSkiped = startDateSkiped;
		}
		public boolean isEndDateSkiped() {
			return endDateSkiped;
		}
		public void setEndDateSkiped(boolean endDateSkiped) {
			this.endDateSkiped = endDateSkiped;
		}
		public boolean isLengthOfStaySkiped() {
			return lengthOfStaySkiped;
		}
		public void setLengthOfStaySkiped(boolean lengthOfStaySkiped) {
			this.lengthOfStaySkiped = lengthOfStaySkiped;
		}
		public boolean isMinTotalRateSkiped() {
			return minTotalRateSkiped;
		}
		public void setMinTotalRateSkiped(boolean minTotalRateSkiped) {
			this.minTotalRateSkiped = minTotalRateSkiped;
		}
		public boolean isMaxTotalRateSkiped() {
			return maxTotalRateSkiped;
		}
		public void setMaxTotalRateSkiped(boolean maxTotalRateSkiped) {
			this.maxTotalRateSkiped = maxTotalRateSkiped;
		}
		public boolean isMinStarRateSkiped() {
			return minStarRateSkiped;
		}
		public void setMinStarRateSkiped(boolean minStarRateSkiped) {
			this.minStarRateSkiped = minStarRateSkiped;
		}
		public boolean isMaxStarRateSkiped() {
			return maxStarRateSkiped;
		}
		public void setMaxStarRateSkiped(boolean maxStarRateSkiped) {
			this.maxStarRateSkiped = maxStarRateSkiped;
		}
		public boolean isMinGuestRatingSkiped() {
			return minGuestRatingSkiped;
		}
		public void setMinGuestRatingSkiped(boolean minGuestRatingSkiped) {
			this.minGuestRatingSkiped = minGuestRatingSkiped;
		}
		public boolean isMaxGuestRatingSkiped() {
			return maxGuestRatingSkiped;
		}
		public void setMaxGuestRatingSkiped(boolean maxGuestRatingSkiped) {
			this.maxGuestRatingSkiped = maxGuestRatingSkiped;
		}
		public List<HotelDO> getHotelResults() {
			return hotelResults;
		}
		public void setHotelResults(List<HotelDO> hotelResults) {
			this.hotelResults = hotelResults;
		}
		public List<HotelDO> getDefaultHotelList() {
			return defaultHotelList;
		}
		public void setDefaultHotelList(List<HotelDO> defaultHotelList) {
			this.defaultHotelList = defaultHotelList;
		}

		public String getDateFormat() {
			return dateFormat;
		}

		public void setDateFormat(String dateFormat) {
			this.dateFormat = dateFormat;
		}

		
		
		
	
		
	}


