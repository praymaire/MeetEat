package com.mb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// => Model(DBμ²λ¦¬??)???΄ ??? ? ?¬?©?? κ°μ²΄
	// =>  μ²λ¦¬?? ??? ??(??)? κ°μ λ‘? λΆ??¬ 
	// ??,μΆμλ©μ?
	
	// => executeλ©μ?? ?€?? request,responseλ₯? ? ?¬λ°μ?
	//   μ²λ¦¬?? ActionForwardλ₯? λ¦¬ν΄?? λ©μ?
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	//public int ABC(int a, int b);

}
