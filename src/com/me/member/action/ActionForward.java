package com.me.member.action;

// 페이지를 이동할 때 필요한 정보를 저장하는 객체
// 1) 페이지 이동정보(주소) 
// 2) 페이지 이동방식
public class ActionForward {

	private String path;		// 1)
	private boolean isRedirect; // 2)
	// true - sendRedirect방식
	// false - forward방식
	
	// alt shift s + r
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {	// == get() boolean타입은 get()이 이렇게 나온다
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
}