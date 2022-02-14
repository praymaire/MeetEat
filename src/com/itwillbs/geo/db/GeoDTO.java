package com.itwillbs.geo.db;


public class GeoDTO {
	// model2-itwill_board 테이블의 정보를 조회하는 객체
	private String latitude;
	private String longitude;
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
	
	
	@Override
	public String toString() {
		return "GeoDTO [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */


	
	
	// alt  shift s + r
	


	
	
}// DTO
