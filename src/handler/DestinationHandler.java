package handler;

import org.json.JSONObject;

import DO.AbstractDO;
import DO.DestenationDO;

public class DestinationHandler {
	
	public static AbstractDO fillDO(JSONObject destinationDetails){
		DestenationDO destenationDO = new DestenationDO();
		if(destinationDetails!=null){
			destenationDO.setAssociatedMultiCityRegionId(destinationDetails.getString("associatedMultiCityRegionId"));
			destenationDO.setCountry(destinationDetails.getString("country"));
			destenationDO.setLongName(destinationDetails.getString("longName"));
			destenationDO.setNonLocalizedCity(destinationDetails.getString("nonLocalizedCity"));
			destenationDO.setProvince(destinationDetails.getString("province"));
			destenationDO.setRegionID(destinationDetails.getString("regionID"));
			destenationDO.setShortName(destinationDetails.getString("shortName"));
			destenationDO.setTla(destinationDetails.getString("tla"));
			destenationDO.setCity(destinationDetails.getString("city"));
		}
		return 	destenationDO;
		}
	  public static boolean checkCity(DestenationDO destenationDO,String city_cc){
		  if(destenationDO.getCity().equalsIgnoreCase(city_cc) ||  destenationDO.getCountry().equalsIgnoreCase(city_cc) ||
				  destenationDO.getLongName().contains(city_cc)|| destenationDO.getNonLocalizedCity().contains(city_cc) ||
				  destenationDO.getProvince().equalsIgnoreCase(city_cc) || destenationDO.getShortName().equalsIgnoreCase(city_cc)
				  ||destenationDO.getTla().equalsIgnoreCase(city_cc)){
			  return true;
		  }
		  return false;
	  }

}
