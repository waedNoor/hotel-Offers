package DO;

import java.time.LocalDate;
import java.util.Date;

public class OfferDateRangeDO extends AbstractDO{
	private Date travelEndDate;
	private Date travelStartDate;
	private int lengthOfStay;
	
	
	public Date getTravelEndDate() {
		return travelEndDate;
	}
	public void setTravelEndDate(Date travelEndDate) {
		this.travelEndDate = travelEndDate;
	}
	public Date getTravelStartDate() {
		return travelStartDate;
	}
	public void setTravelStartDate(Date travelStartDate) {
		this.travelStartDate = travelStartDate;
	}
	public int getLengthOfStay() {
		return lengthOfStay;
	}
	public void setLengthOfStay(int lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}
}
