package com.me.admin.db;

public class AdminDTO {
	
	private String id;
	private String pw;
	private String nickname;
	private String phone;
	private String email;
	private String address;
	private int user_point;
	private int user_level;
	private int reported_count;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	public int getUser_point() {
		return user_point;
	}
	public void setUser_point(int user_point) {
		this.user_point = user_point;
	}
	public int getUser_level() {
		return user_level;
	}
	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}
	public int getReported_count() {
		return reported_count;
	}
	public void setReported_count(int reported_count) {
		this.reported_count = reported_count;
	}
	@Override
	public String toString() {
		return "AdminDTO [id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", user_point=" + user_point + ", user_level=" + user_level
				+ ", reported_count=" + reported_count + "]";
	}
	
	


}
