package com.ad.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ad.admin.action.*;


// Interface - 동일한 틀에서 니즈에 따른 동작을 할 수 있게 하기 위함.
// 			       한 컵에 물을 담으면 물컵이 되고, 꽃을 심으면 화분이 된다. 
// 		 	      쓰임은 달라지지지만, 틀-컵의 역할이 무언가를 담은 거에 있음은 변함이 없다. 이 컵이 바로 인터페이스의 역할!
// 동일한 형태의 추상메소드를 오버라이딩시키기 위해서. 
public interface Action {
	// -> Model(DB 처리동작)동작이 필요할 때 사용하는 객체
	// -> 처리하는 동작의 형태(틀)을 강제로 부여
	// 상수, 추상 메소드
	
	// => execute 메소드는 실행 시 request, response를 전달받아서 처리 후에 ActionForward를 리턴하는 메소드
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	// public int ABC(int a, int b); 와 같은 모습
	
}
