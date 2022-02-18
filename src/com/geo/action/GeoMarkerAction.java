package com.geo.action;


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.geo.db.GeoDAO;
import com.geo.db.GeoDTO;



public class GeoMarkerAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println(" G :GeoMarkerAction_execute() 호출");

		// 위치 정보를 가져오기(세션)
		HttpSession session = request.getSession();

		//전달된 파라메터 저장1
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		session.setAttribute("latitude", latitude);
		session.setAttribute("longitude", longitude);
		
		/* 전달된 파라메터 저장
		String latitude = (String)session.getAttribute("latitude");
		String longitude = (String)session.getAttribute("longitude");
		*/
		

		// 위치정보를 체크(위치정보가 없을 시, Beforemain.do로 이동)
		//ActionForward forward = new ActionForward();
		if(latitude == null){
			System.out.println("안녕하세요 MarkerAction 입니다. 12345678913");
			//forward.setPath("./BeforeMain.do");
			//forward.setRedirect(true);
			//return forward;
		}		
		
		
		HashMap<String, Object> listOpt = new HashMap<String, Object>();
        listOpt.put("latitude", latitude);
        listOpt.put("longitude", longitude);

        GeoDAO dao = new GeoDAO();
        ArrayList list =  dao.getMarkerList(listOpt);

        request.setAttribute("geomList", list);

		

		
		//세션 출력확인
		System.out.println(latitude);
		System.out.println(longitude);
			
		
		
		
     // 페이지 이동- markerview 페이지로 
		    ActionForward forward = new ActionForward();
     		forward.setPath("./GeoView/markerview.jsp");
     		forward.setRedirect(false);		
     		return forward;
     		
     		
	} //execute

}