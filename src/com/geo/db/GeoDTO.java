package com.geo.db;


import java.sql.Timestamp;

public class GeoDTO {
	// model2-itwill_board 테이블의 정보를 조회하는 객체
	private String latitude;
	private String longitude;
	private String where_name;
	private String when_name;
	private String food_category;
	private String id;
	private String upload_image;
	private String content;
	private int bno;
	private Timestamp write_time;

	
	
	
	
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

	public String getWhere_name() {
		return where_name;
	}

	public void setWhere_name(String where_name) {
		this.where_name = where_name;
	}

	public String getWhen_name() {
		return when_name;
	}

	public void setWhen_name(String when_name) {
		this.when_name = when_name;
	}

	public String getFood_category() {
		return food_category;
	}

	public void setFood_category(String food_category) {
		this.food_category = food_category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpload_image() {
		return upload_image;
	}

	public void setUpload_image(String upload_image) {
		this.upload_image = upload_image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public Timestamp getWrite_time() {
		return write_time;
	}

	public void setWrite_time(Timestamp write_time) {
		this.write_time = write_time;
	}


	@Override
	public String toString() {
		return "GeoDTO [latitude=" + latitude + ", longitude=" + longitude + ", where_name=" + where_name
				+ ", when_name=" + when_name + ", food_category=" + food_category + ", id=" + id + ", upload_image="
				+ upload_image + ", content=" + content + ", bno=" + bno + ", write_time=" + write_time + "]";
	}
	
	
}// DTO
