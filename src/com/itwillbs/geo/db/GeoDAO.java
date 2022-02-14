package com.itwillbs.geo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class GeoDAO {
	// 위치정보를 처리하는 DAO

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비연결
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/model2");
		con = ds.getConnection();
		return con;
	}

	// 자원해제
	public void CloseDB() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
			// getMarkerList() : 위치정보에서 근방 100미터 내의 사람을 구해오는 쿼리문을 사용. (수정전 : 1키로 기준)
						public ArrayList getMarkerList(HashMap<String, Object> listOpt)
						{
							// 가변길이 배열
							ArrayList geomList = new ArrayList();
							String latitude = (String)listOpt.get("latitude");
							String longitude = (String)listOpt.get("longitude");
							
							
							try {
								// 1.2. 디비연결
								con = getCon();
								// 3. sql 작성 & pstmt 객체생성
								// 일단 1키로 범위로 잡고 조회(위경도만 뽑아온다)
								sql = "SELECT latitude, longitude FROM (SELECT latitude, longitude, ( 6371 * acos( cos( radians("+latitude+") ) * cos( radians( latitude) ) * cos( radians( longitude ) - radians("+ longitude +") ) + sin( radians("+ latitude +") ) * sin( radians(latitude) ) ) ) AS distance FROM itwill_geo)DATA WHERE DATA.distance < 1";					
								
								pstmt = con.prepareStatement(sql);					
								
								// 4. sql 실행
								rs = pstmt.executeQuery();
								// 5. 데이터 처리
								
								while(rs.next()){
									// 마커 하나의 정보 저장
									GeoDTO dto = new GeoDTO();
									dto.setLatitude(rs.getString("latitude"));
									dto.setLongitude(rs.getString("longitude"));
									
									// MemberBean의 정보를 => ArrayList 1칸에 저장
									geomList.add(dto);
								}
								System.out.println("디비 마커 조회 완료!");

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								CloseDB();
							}
							return geomList;
						}
						// getMarkerList()
			
			

	
		// getGeoList() : 위치정보에서 근방 100미터 내의 사람을 구해오는 쿼리문을 사용. (수정전 : 1키로 기준)
		public ArrayList getGeoList(HashMap<String, Object> listOpt2)
		{
			//가변길이 배열
			ArrayList geoList = new ArrayList();
			String latitude = (String)listOpt2.get("latitude");
			String longitude = (String)listOpt2.get("longitude");
			
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 작성 & pstmt 객체생성
				sql = "SELECT latitude, longitude FROM (SELECT latitude, longitude, ( 6371 * acos( cos( radians("+latitude+") ) * cos( radians( latitude) ) * cos( radians( longitude ) - radians("+ longitude +") ) + sin( radians("+ latitude +") ) * sin( radians(latitude) ) ) ) AS distance FROM itwill_geo)DATA WHERE DATA.distance < 1";					
				
				pstmt = con.prepareStatement(sql);
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				
				while(rs.next()){
					// 리스트 하나의 정보 저장
					GeoDTO dto = new GeoDTO();
					dto.setLatitude(rs.getString("latitude"));
					dto.setLongitude(rs.getString("longitude"));
					
					// MemberBean의 정보를 => ArrayList 1칸에 저장
					geoList.add(dto);
				}
				System.out.println("디비 리스트 조회 완료!");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return geoList;
		}
		// getGeoList()
		
		

}
