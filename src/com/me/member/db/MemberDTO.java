package com.me.member.db;

import java.sql.Date;

public class MemberDTO {

	private String id;
	private String pw;
	private String nickname;
	private String phone;
	private String email;
	private String address;
	private String profile_image;
	private int reported_count;
	private int user_point;
	private int user_level;
	private Date ban_date;
	
	public int getReported_count() {
		return reported_count;
	}
	public void setReported_count(int reported_count) {
		this.reported_count = reported_count;
	}
	public Date getBan_date() {
		return ban_date;
	}
	public void setBan_date(Date ban_date) {
		this.ban_date = ban_date;
	}
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
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
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
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", profile_image=" + profile_image + ", reported_count=" + reported_count
				+ ", user_point=" + user_point + ", user_level=" + user_level + ", ban_date=" + ban_date + "]";
	}
	
	
}
