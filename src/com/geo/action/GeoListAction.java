package com.geo.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.geo.db.GeoDAO;


public class GeoListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println(" G :GeoListAction_execute() 호출");
		
	
		// * 위치 정보를 가져오기(세션)
		HttpSession session = request.getSession();

		// 전달된 파라메터 저장1
		//String latitude = request.getParameter("latitude");
		//String longitude = request.getParameter("longitude");
		
		// 전달된 파라메터 저장2 : 마커뷰 가기전에 들러야 하는데, 둘다 받는 id 값이 다르다
		String latitude = request.getParameter("x");
		String longitude = request.getParameter("y");
		
		//차후를 위한 세션처리
		session.setAttribute("latitude", latitude);
		session.setAttribute("longitude", longitude);
		

		// 위치정보를 체크(위치정보가 없을 시, Beforemain.do로 이동(X) -> 완성 전까지 console 출력으로 유지)
		ActionForward forward = new ActionForward();
		if(latitude == null){
			System.out.println("안녕하세요 ListAction 입니다. 12345678913");
			//forward.setPath("./BeforeMain.do");
			//forward.setRedirect(true);
			//return forward;
		   }		
	
		
		HashMap<String, Object> listOpt2 = new HashMap<String, Object>();
        listOpt2.put("latitude", latitude);
        listOpt2.put("longitude", longitude);

        GeoDAO dao = new GeoDAO();
        ArrayList list =  dao.getGeoList(listOpt2);

        request.setAttribute("geoList", list);
		
		
		
		
		
		
		
		
				
				// DAO 객체를 생성 
				//GeoDAO dao = new GeoDAO();
				//GeoDTO dto = dao.getMarkerList();
				//ArrayList geolist =  dao.getGeoList();
				//request.setAttribute("geoList", dao.getGeoList());
							
				//세션 확인체크
				System.out.println(latitude);
				System.out.println(longitude);


     // 페이지 이동- markerview 페이지로 
     		forward.setPath("./GeoView/AfterMain.jsp");
     		forward.setRedirect(false);		
     		return forward;
	} //execute

}