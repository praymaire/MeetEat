package com.me.member.db;


public class MemberDTO {

	private String id;
	private String pw;
	private String nickname;
	private String phone;
	private String email;
	private String address;
	private String profile_image;
	
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
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", profile_image=" + profile_image + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
