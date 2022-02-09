package com.me.member.db;

import java.sql.Date;

public class ReportDTO {
	
	private int num;
	private String reported_user;
	private int reported_count;
	private String report_content;
	private String report_user;
	private Date report_date;
	private Date ban_date;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getReported_user() {
		return reported_user;
	}
	public void setReported_user(String reported_user) {
		this.reported_user = reported_user;
	}
	public int getReported_count() {
		return reported_count;
	}
	public void setReported_count(int reported_count) {
		this.reported_count = reported_count;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	public String getReport_user() {
		return report_user;
	}
	public void setReport_user(String report_user) {
		this.report_user = report_user;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public Date getBan_date() {
		return ban_date;
	}
	public void setBan_date(Date ban_date) {
		this.ban_date = ban_date;
	}
	
	@Override
	public String toString() {
		return "ReportDTO [num=" + num + ", reported_user=" + reported_user + ", reported_count=" + reported_count
				+ ", report_content=" + report_content + ", report_user=" + report_user + ", report_date=" + report_date
				+ ", ban_date=" + ban_date + "]";
	}
	
	

}
