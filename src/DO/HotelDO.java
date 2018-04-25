package DO;

import javax.faces.bean.ManagedProperty;

public class HotelDO extends AbstractDO{
	private DestenationDO destenationDO;
	private HotelInfoDO hotelInfoDO;
	private HotelPricingInfoDO hotelPricingInfoDO;
	private HotelUrlsDO hotelUrlsDO;
	private OfferDateRangeDO offerDateRangeDO;
	
	
	public DestenationDO getDestenationDO() {
		return destenationDO;
	}
	public void setDestenationDO(DestenationDO destenationDO) {
		this.destenationDO = destenationDO;
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
	public OfferDateRangeDO getOfferDateRangeDO() {
		return offerDateRangeDO;
	}
	public void setOfferDateRangeDO(OfferDateRangeDO offerDateRangeDO) {
		this.offerDateRangeDO = offerDateRangeDO;
	}
	
	
}
