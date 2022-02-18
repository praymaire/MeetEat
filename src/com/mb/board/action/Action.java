package com.mb.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// => Model(DBì²˜ë¦¬?™?‘)?™?‘?´ ?•„?š”?• ?•Œ ?‚¬?š©?•˜?Š” ê°ì²´
	// =>  ì²˜ë¦¬?•˜?Š” ?™?‘?˜ ?˜•?ƒœ(??)?„ ê°•ì œë¡? ë¶??—¬ 
	// ?ƒ?ˆ˜,ì¶”ìƒë©”ì„œ?“œ
	
	// => executeë©”ì„œ?“œ?Š” ?‹¤?–‰?‹œ request,responseë¥? ? „?‹¬ë°›ì•„?„œ
	//   ì²˜ë¦¬?›„?— ActionForwardë¥? ë¦¬í„´?•˜?Š” ë©”ì„œ?“œ
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	//public int ABC(int a, int b);

}
