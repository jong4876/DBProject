package newDBProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class newDB 
{
    public static Connection dbConn;
    
        public static Connection getConnection()
        {
            Connection conn = null;
            try {
                String user = "jong4876"; 
                String pw = "Bc1162711";
                String url = "jdbc:oracle:thin:@localhost:1521:oraclee";
                
                Class.forName("oracle.jdbc.driver.OracleDriver");        
                conn = DriverManager.getConnection(url, user, pw);
                
                System.out.println("Database에 연결되었습니다.\n");
                
            } catch (ClassNotFoundException cnfe) {
                System.out.println("DB 드라이버 로딩 실패 :"+cnfe.toString());
            } catch (SQLException sqle) {
                System.out.println("DB 접속실패 : "+sqle.toString());
            } catch (Exception e) {
                System.out.println("Unkonwn error");
                e.printStackTrace();
            }
            return conn;     
        }
}




class CampingModel 
{
		Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstmt = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        ArrayList<Customer> customer;
        String inform = null;
		String id = null;
		int i;
        
        
        
        public CampingModel() { /////////////////////////////////이거 메서드로 옮겨서 arrayList로 받기!!
        	conn = newDB.getConnection();
        }
        
        
        public ArrayList<Customer> getCustomerAll(){
        	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
        	
        	ArrayList<Customer> datas = new ArrayList<Customer>();
        	
    	 try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String sql = "SELECT * FROM Customer";
  
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            
          
            while(rs.next()){
            	
                String liscence_num = rs.getString(1);
            	Customer temp = new Customer(rs.getString(1),
						 rs.getString(2),
						 rs.getString(3),
						 rs.getString(4),
						 rs.getString(5),
						 rs.getInt(6),
						 rs.getString(7)
            			);
            	
				datas.add(temp);
                //arraylist에 담기~~
             
            }
            
        } catch (SQLException sqle) {
          
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            try{
               
                if ( pstmt != null ){pstmt.close();}   
          
            }catch(Exception e){
            	  System.out.println("getAll 예외 발생");
                throw new RuntimeException(e.getMessage());
            }
            
        }
    	
    	
    	return datas;
    }
        
    
        public ArrayList<CampingCar> getCampingCarAll(){
        	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
        	
        	ArrayList<CampingCar> datas = new ArrayList<CampingCar>();
        	
    	 try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String sql = "SELECT * FROM CampingCar";
  
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            
          
            while(rs.next()){
            	
                String Car_regist_id = rs.getString(1);
                CampingCar temp = new CampingCar(rs.getString(1),
						 rs.getString(2),
						 rs.getString(3),
						 rs.getInt(4),
						 rs.getString(5),
						 rs.getString(6),
						 rs.getInt(7),
						 rs.getString(8),
						 rs.getString(9)
            			);
            	
				datas.add(temp);
                //arraylist에 담기~~
             
            }
            
        } catch (SQLException sqle) {
          
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            try{
               
                if ( pstmt != null ){pstmt.close();}   
          
            }catch(Exception e){
            	  System.out.println("getCampingcar 예외 발생");
                throw new RuntimeException(e.getMessage());
            }
            
        }
    	
    	
    	return datas;
    }
        public ArrayList<Rental_Inform> getRental_InformAll(){// condition에 따른 수정필요
        	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
        	
        	ArrayList<Rental_Inform> datas = new ArrayList<Rental_Inform>();
        	
    	 try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String sql = "SELECT * FROM Rental_Inform";
  
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            
          
            while(rs.next()){
            	
                String Rental_num = rs.getString(1);
                Rental_Inform temp = new Rental_Inform(rs.getString(1),
						 rs.getString(2),
						 rs.getString(3),
						 rs.getString(4),
						 rs.getDate(5),
						 rs.getInt(6),
						 rs.getInt(7),
						 rs.getDate(8),
						 rs.getString(9),
						 rs.getInt(10)
            			);
            	
				datas.add(temp);
                //arraylist에 담기~~
             
            }
            
        } catch (SQLException sqle) {
          
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            try{
               
                if ( pstmt != null ){pstmt.close();}   
          
            }catch(Exception e){
            	  System.out.println("getCampingcar 예외 발생");
                throw new RuntimeException(e.getMessage());
            }
            
        }
    	
    	
    	return datas;
    }
        public ArrayList<Rental_Company> getRental_CompanyAll(){// condition에 따른 수정필요
        	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
        	
        	ArrayList<Rental_Company> datas = new ArrayList<Rental_Company>();
        	
    	 try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String sql = "SELECT * FROM Rental_Company";
  
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            
          
            while(rs.next()){
            	
                String Rental_num = rs.getString(1);
                Rental_Company temp = new Rental_Company(rs.getString(1),
						 rs.getString(2),
						 rs.getString(3),
						 rs.getString(4),
						 rs.getString(5),
						 rs.getString(6)
						
            			);
            	
				datas.add(temp);
                //arraylist에 담기~~
             
            }
            
        } catch (SQLException sqle) {
          
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            try{
               
                if ( pstmt != null ){pstmt.close();}   
          
            }catch(Exception e){
            	  System.out.println("getRental_CompanyAll 예외 발생");
                throw new RuntimeException(e.getMessage());
            }
            
        }
    	
    	
    	return datas;
    }
        
        
        public ArrayList<Repair_Company> getRepair_CompanyAll(){// condition에 따른 수정필요
        	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
        	
        	ArrayList<Repair_Company> datas = new ArrayList<Repair_Company>();
        	
    	 try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String sql = "SELECT * FROM Repair_Company";
  
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            
          
            while(rs.next()){
            	
                String Rental_num = rs.getString(1);
                Repair_Company temp = new Repair_Company(rs.getString(1),
						 rs.getString(2),
						 rs.getString(3),
						 rs.getString(4),
						 rs.getString(5),
						 rs.getString(6)
						
            			);
            	
				datas.add(temp);
                //arraylist에 담기~~
             
            }
            
        } catch (SQLException sqle) {
          
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            try{
               
                if ( pstmt != null ){pstmt.close();}   
          
            }catch(Exception e){
            	  System.out.println("getRepair_CompanyAll 예외 발생");
                throw new RuntimeException(e.getMessage());
            }
            
        }
    	
    	
    	return datas;
    }
        
        public ArrayList<Repair_Inform> getRepair_InformAll(){// condition에 따른 수정필요
        	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
        	
        	ArrayList<Repair_Inform> datas = new ArrayList<Repair_Inform>();
        	
    	 try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String sql = "SELECT * FROM Repair_Inform";
  
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            
          
            while(rs.next()){
            	
                String Rental_num = rs.getString(1);
                Repair_Inform temp = new Repair_Inform(rs.getString(1),
						 rs.getString(2),
						 rs.getString(3),
						 rs.getString(4),
						 rs.getString(5),
						 rs.getString(6),
						 rs.getString(7),
						 rs.getInt(8),
						 rs.getString(9),
						 rs.getString(10)
            			);
            	
				datas.add(temp);
                //arraylist에 담기~~
             
            }
            
        } catch (SQLException sqle) {
          
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            try{
               
                if ( pstmt != null ){pstmt.close();}   
          
            }catch(Exception e){
            	  System.out.println("getRepair_Inform 예외 발생");
                throw new RuntimeException(e.getMessage());
            }
            
        }
    	
    	
    	return datas;
    }


	public ArrayList<DescribeTableVO> getTableDescription(String tableName) {

		String sql = "select COLUMN_NAME from COLS where table_name=?";
		PreparedStatement pstmt = null;
		ArrayList<DescribeTableVO> arrDescribeTableVO = new ArrayList<DescribeTableVO>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tableName.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println("getTableDescription");
				System.out.println(rs.getString("COLUMN_NAME"));
				DescribeTableVO tempDescribeTableVO = new DescribeTableVO(rs.getString("COLUMN_NAME"));
				arrDescribeTableVO.add(tempDescribeTableVO);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
					pstmt.close();
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
	
		return arrDescribeTableVO;
	}
	// 테이블 전체를 arrayList로 넘기는 코드들
	
	
	
	public ArrayList<Customer> getSearch1All(){// Search1에 해당하는 결과값을 넘겨주는 코드
    	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
    	
    	ArrayList<Customer> datas = new ArrayList<Customer>();
    	
	 try {
		 // 시간있을때 수정
        String sql = "select liscence_num, name, tel from customer where liscence_num in (select r_li_num from Rental_Inform where ((r_Start+r_day) < (r_end)))";

        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        
      
        while(rs.next()){
        	
            String Li_num = rs.getString(1);
            Customer temp = new Customer(rs.getString(1),
					 rs.getString(2),
					 null,
					 rs.getString(3),
					 null,
					 0,
					 null
        			);
        	
			datas.add(temp);
            //arraylist에 담기~~
        
        }
        
    } catch (SQLException sqle) {
    	
        sqle.printStackTrace();
        
    }finally{
        // DB 연결을 종료한다.
        try{
           
            if ( pstmt != null ){pstmt.close();}   
      
        }catch(Exception e){
        	System.out.println("getSearch1All 오류");
            throw new RuntimeException(e.getMessage());
        }
        
    }
	
	
	return datas;
}
	
	public ArrayList<CampingCar> getSearch2All(){// Search3에 해당하는 결과값을 넘겨주는 코드
    	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
    	
    	ArrayList<CampingCar> datas = new ArrayList<CampingCar>();
    	
	 try {
		 // 시간있을때 수정
        String sql = "select id,name,NOP,image,inform,cost from Campingcar where  comp_id in ( select r_company_id from Rental_Inform group by r_company_id having count(*) >= 3)";
       
       
		
		 		
		pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        
	      
		  while(rs.next()){
		        	
			  String Li_num = rs.getString(1);
		      CampingCar temp = new CampingCar(rs.getString(1),
		     		 rs.getString(2),
						 null,
						 rs.getInt(3),
						 rs.getString(4),
						 rs.getString(5),
						 rs.getInt(6),
						 null,
						 null
		 			);
				datas.add(temp);
		      //arraylist에 담기~~
		  
		  }
		        
		    }  catch (SQLException sqle) {
		    	
		        sqle.printStackTrace();
		        
		    }finally{
		        // DB 연결을 종료한다.
		        try{
		           
		            if ( pstmt != null ){pstmt.close();}   
		      
		        }catch(Exception e){
		        	System.out.println("getSearch1All 오류");
		            throw new RuntimeException(e.getMessage());
		        }
		        
		    }
			
		
	return datas;
}
	
	
	
	
	
	public ArrayList<CampingCar> getSearch3All(){// Search3에 해당하는 결과값을 넘겨주는 코드
    	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
    	
    	ArrayList<CampingCar> datas = new ArrayList<CampingCar>();
    	
	 try {
		 // 시간있을때 수정
        String sql = "select comp_id,name, cost, NOP from Campingcar where comp_id in(select rc_id from RENTAL_COMPANY where cost < 500000 and NOP >= 4)";

        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        
      
        while(rs.next()){
        	
            String Li_num = rs.getString(1);
            CampingCar temp = new CampingCar(null,
            		 rs.getString(2),
					 null,
					 rs.getInt(4),
					 null,
					 null,
					 rs.getInt(3),
					 rs.getString(1),
					 null
        			);
        	
			datas.add(temp);
            //arraylist에 담기~~
        
        }
        
    } catch (SQLException sqle) {
    	
        sqle.printStackTrace();
        
    }finally{
        // DB 연결을 종료한다.
        try{
           
            if ( pstmt != null ){pstmt.close();}   
      
        }catch(Exception e){
        	System.out.println("getSearch1All 오류");
            throw new RuntimeException(e.getMessage());
        }
        
    }
	
	
	return datas;
}
	
	public ArrayList<Search4> getSearch4All(){// Search4 정비정보
    	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
    	
    	ArrayList<Search4> datas = new ArrayList<Search4>();
    	
	 try {
		 // 시간있을때 수정
        String sql = "select RI_company, count(RI_company), sum(RI_cost) from Repair_Inform group by RI_company having sum(RI_cost)>100000 order by sum(RI_COST) desc";

        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        
      
        while(rs.next()){
        	
            String Li_num = rs.getString(1);
            Search4 temp = new Search4(rs.getString(1),
            		rs.getInt(2),
            		rs.getInt(3)
        			);
            
        	
			datas.add(temp);
            //arraylist에 담기~~
        
        }
        
    } catch (SQLException sqle) {
    	
        sqle.printStackTrace();
        
    }finally{
        // DB 연결을 종료한다.
        try{
           
            if ( pstmt != null ){pstmt.close();}   
      
        }catch(Exception e){
        	System.out.println("getSearch1All 오류");
            throw new RuntimeException(e.getMessage());
        }
        
    }
	
	
	return datas;
}
	
	public ArrayList<Repair_Company> getSearch5All(){// Search3에 해당하는 결과값을 넘겨주는 코드
    	//전체 검색 데이터를 전달할 arraylist를 반환하는 함수
    	
    	ArrayList<Repair_Company> datas = new ArrayList<Repair_Company>();
    	
	 try {
	
        String sql = "select R_name,R_address ,R_call from Repair_Company where R_id in (select  RI_id from Repair_Inform where RI_details like  '%파손정비%')";

        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        
      
        while(rs.next()){
        	
            String Li_num = rs.getString(1);
            Repair_Company temp = new Repair_Company(null,
            		rs.getString(1),
            		rs.getString(2),
            		rs.getString(3),
            		null,
            		null);
        	
			datas.add(temp);
            //arraylist에 담기~~
        
        }
        
    } catch (SQLException sqle) {
    	
        sqle.printStackTrace();
        
    }finally{
        // DB 연결을 종료한다.
        try{
           
            if ( pstmt != null ){pstmt.close();}   
      
        }catch(Exception e){
        	System.out.println("getSearch5All 오류");
            throw new RuntimeException(e.getMessage());
        }
        
    }
	
	
	return datas;
}
	
	
/////삭제	
	
public boolean deleteCustomer(String name) {

	
	
	String sql = "delete from Customer where name = ?";

	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		int op = pstmt.executeUpdate();
		pstmt.close();
		
		if(op > 0) {
			System.out.println("Customer삭제 완료!");
			return true;
		}
		else
			System.out.println("Customer삭제 실패.");
			return false;
	} catch(Exception e) { e.printStackTrace(); }
	return false;
}


	
	
	public boolean deleteCampingcar(String id) {

		
		
		String sql = "delete from Campingcar where id = ?";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
				System.out.println("Customer삭제 완료!");
				return true;
			}
			else
				System.out.println("Customer삭제 실패.");
				return false;
		} catch(Exception e) { e.printStackTrace(); }
		return false;
	}
	
	public boolean deleteRental_Company(String rc_id) {

		
		
		String sql = "delete from Rental_Company where rc_id = ?";

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rc_id);
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
				System.out.println("Rental_Company삭제 완료!");
				return true;
			}
			else
				System.out.println("Rental_Company삭제 실패.");
				return false;
		} catch(Exception e) { e.printStackTrace(); }
		return false;
	}
	
public boolean deleteRental_Inform(String R_num) {

	
	
	String sql = "delete from Rental_Inform where R_num = ?";

	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, R_num);
		int op = pstmt.executeUpdate();
		pstmt.close();
		
		if(op > 0) {
			System.out.println("Rental_Inform삭제 완료!");
			return true;
		}
		else
			System.out.println("Rental_Inform삭제 실패.");
			return false;
	} catch(Exception e) { e.printStackTrace(); }
	return false;
}

public boolean deleteRepair_Company(String R_id) {

	
	
	String sql = "delete from Repair_Company where R_id = ?";

	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, R_id);
		int op = pstmt.executeUpdate();
		pstmt.close();
		
		if(op > 0) {
			System.out.println("Repair_Company삭제 완료!");
			return true;
		}
		else
			System.out.println("Repair_Company삭제 실패.");
			return false;
	} catch(Exception e) { e.printStackTrace(); }
	return false;
}

public boolean deleteRepair_Inform(String RI_info) {

	
	
	String sql = "delete from Repair_Inform where RI_info = ?";

	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, RI_info);
		int op = pstmt.executeUpdate();
		pstmt.close();
		
		if(op > 0) {
			System.out.println("Repair_Inform삭제 완료!");
			return true;
		}
		else
			System.out.println("Repair_Inform삭제 실패.");
			return false;
	} catch(Exception e) { e.printStackTrace(); }
	return false;
}

	public boolean updateLast_Use(String name) {//Last_Use를 현재 날짜로 바꿔주는 메서드

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String Today = sdf.format(c1.getTime());

	

		
		
		String sql = "update Customer set Last_Use = "+Today+" where name = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
				System.out.println("수정 완료!");
				return true;
				
			}
			else
				System.out.println("수정 실패!");
				return false;
			
		} catch(Exception e) { e.printStackTrace(); }
		return false;
		
	}
	
	public boolean updateR_date(String name) {//R_date를 현재날짜로 바꿔주는 메서드

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String Today = sdf.format(c1.getTime());

	

		
		
		String sql = "update Campingcar set R_date = "+Today+" where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
				System.out.println("수정 완료!");
				return true;
				
			}
			else
				System.out.println("수정 실패!");
				return false;
			
		} catch(Exception e) { e.printStackTrace(); }
		return false;
		
	}
		

	
	public boolean updateRentalFare() {// 성수기에 가격 10% 증가시키는 메서드-Rental_Inform
		
		String sql = "update Rental_Inform set r_cost = r_cost * 1.1 where r_start >= '18/08/01' and r_start <= '18/09/01'";
		try {
			pstmt = conn.prepareStatement(sql);
			
			
		
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
				System.out.println("수정 완료!");
				return true;
				
			}
			else
				System.out.println("수정 실패!");
				return false;
			
		} catch(Exception e) { e.printStackTrace(); }
		return false;
		
	}
		
	public boolean updateBackFare() {// 원상복구
		
		String sql = "update Rental_Inform set r_cost = r_cost * (1.0/1.1)where r_start >= '18/08/01' and r_start <= '18/09/01'";
		try {
			pstmt = conn.prepareStatement(sql);
		
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
				System.out.println("수정 완료!");
				return true;
				
			}
			else
				System.out.println("수정 실패!");
				return false;
			
		} catch(Exception e) { e.printStackTrace(); }
		return false;
		
	}
	
	
	public boolean updateRC_empname(String inname, String outname) {// Rental_Company의 담당자 이름 변경
		
		String sql = "update Rental_Company set rc_empname = ? where rc_empname = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, outname);
			pstmt.setString(2, inname);
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
				System.out.println("수정 완료!");
				return true;
				
			}
			else
				System.out.println("수정 실패!");
				return false;
			
		} catch(Exception e) { e.printStackTrace(); }
		return false;
		
	}
	
	public boolean updateR_empname(String inname, String outname) {// Repair_Company의 담당자 이름 변경
		
		String sql = "update Repair_Company set R_empname = ? where R_empname = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, outname);
			pstmt.setString(2, inname);
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
				System.out.println("수정 완료!");
				return true;
				
			}
			else
				System.out.println("수정 실패!");
				return false;
			
		} catch(Exception e) { e.printStackTrace(); }
		return false;
		
	}
	
	
	public boolean updateRI_end(String name) {// 수리비를 납입한 자의 RI_end를 NULL으로 바꿈
		
		String sql = "update Repair_Inform set RI_end = '납입완료' where RI_info = ? and RI_end <> '납입완료      '";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
		
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
				System.out.println("수정 완료!");
				return true;
				
			}
			else
				System.out.println("수정 실패!");
				return false;
			
		} catch(Exception e) { e.printStackTrace(); }
		return false;
		
	}
	
	
	

//삽입
	public boolean insertCustomer(String liscence_num, String name, String address, String tel, String email, int last_date, String inform) {
  		String sql = "insert into Customer values (?,?,?,?,?,?,?)";
  		

  		try {
  			pstmt = conn.prepareStatement(sql);
  			
  			pstmt.setString(1,liscence_num);
  			pstmt.setString(2, name);
  			pstmt.setString(3, address);
  			pstmt.setString(4,tel);
  			pstmt.setString(5, email);
  			pstmt.setInt(6, last_date);
  			pstmt.setString(7, inform);
  			
  			int op = pstmt.executeUpdate();
  			pstmt.close();
  			
  			if(op > 0) {
				System.out.println("삽입 완료!");
				return true;
			}
			else {
				System.out.println("삽입 실패!");
				return false;
			}
				
  		}
  		catch (SQLException e) {
  			e.printStackTrace();
  		} 
  		return false;

  		
  		
      }
	






public boolean insertCampingcar(String id, String name, String car_num, int NOP, String image, String inform, int cost, String comp_id,String R_date) {
		String sql = "insert into Campingcar values (?,?,?,?,?,?,?,?,?)";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,id);
			pstmt.setString(2, name);
			pstmt.setString(3, car_num);
			pstmt.setInt(4,NOP);
			pstmt.setString(5, image);
			pstmt.setString(6, inform);
			pstmt.setInt(7,cost);
			pstmt.setString(8, comp_id);
			pstmt.setString(9, R_date);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삽입 완료!");
			return true;
		}
		else {
			System.out.println("삽입 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;

		
		
  }


public boolean insertRental_Company(String rc_id, String rc_name, String rc_address, String rc_call, String rc_empname,  String rc_empemail) {
		String sql = "insert into Rental_Company values (?,?,?,?,?,?)";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,rc_id);
			pstmt.setString(2, rc_name);
			pstmt.setString(3, rc_address);
			pstmt.setString(4,rc_call);
			pstmt.setString(5, rc_empname);
		
			pstmt.setString(6, rc_empemail);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삽입 완료!");
			return true;
		}
		else {
			System.out.println("삽입 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;		
  }


public boolean insertRental_Inform(String r_num, String r_id, String r_li_num, String r_company_id, Date r_start,  int  r_day,int r_cost,Date r_end,String r_add_category,int r_add_cost) {
	String sql = "insert into Rental_Inform values (?,?,?,?,?,?,?,?,?,?)";

	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,r_num);
		pstmt.setString(2, r_id);
		pstmt.setString(3, r_li_num);
		pstmt.setString(4,r_company_id);
		pstmt.setDate(5, new java.sql.Date(r_start.getTime()));
	
		pstmt.setInt(6, r_day);
		pstmt.setInt(7, r_cost);
		pstmt.setDate(8,  new java.sql.Date(r_end.getTime()));
		pstmt.setString(9, r_add_category);
		pstmt.setInt(10, r_add_cost);
		int op = pstmt.executeUpdate();
		pstmt.close();
		
		if(op > 0) {
		System.out.println("삽입 완료!");
		return true;
	}
	else {
		System.out.println("삽입 실패!");
		return false;
	}
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	} 
	return false;	
}
public boolean  insertRepair_Company(String R_id, String R_name, String R_address, String R_call, String R_empname, String R_empmail) {
	String sql = "insert into Repair_Company values (?,?,?,?,?,?)";
	

	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,R_id);
		pstmt.setString(2, R_name);
		pstmt.setString(3, R_address);
		pstmt.setString(4,R_call);
		pstmt.setString(5,  R_empname);
	
		pstmt.setString(6, R_empmail);
		
		int op = pstmt.executeUpdate();
		pstmt.close();
		
		if(op > 0) {
		System.out.println("삽입 완료!");
		return true;
	}
	else {
		System.out.println("삽입 실패!");
		return false;
	}
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	} 
	return false;
}
public boolean insertRepair_Inform(String RI_info, String  RI_campingcar_id, String RI_id, String RI_company, String RI_li_num, String RI_details,String RI_date, int RI_cost,String RI_end,String RI_etc) {
	String sql = "insert into Repair_Inform values (?,?,?,?,?,?,?,?,?,?)";
	

	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,RI_info);
		pstmt.setString(2, RI_campingcar_id);
		pstmt.setString(3, RI_id);
		pstmt.setString(4,RI_company);
		pstmt.setString(5, RI_li_num);
	
		pstmt.setString(6, RI_details);
		pstmt.setString(7, RI_date);
		pstmt.setInt(8, RI_cost);
		pstmt.setString(9, RI_end);
		pstmt.setString(10,  RI_etc);
		int op = pstmt.executeUpdate();
		pstmt.close();
		
		if(op > 0) {
		System.out.println("삽입 완료!");
		return true;
	}
	else {
		System.out.println("삽입 실패!");
		return false;
	}
		
	}
	catch (SQLException e) {
		e.printStackTrace();
	} 
	return false;

	
	
}



public boolean dropTable1() {// 미완
	
	
	String sql = "drop table Repair_Inform";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;

	
	
}
public boolean dropTable2() {// 미완
	
	
	String sql = "drop table Rental_Inform";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;

	
	
}
public boolean dropTable3() {// 미완
	
	
	String sql = "drop table Customer";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;

	
	
}
public boolean dropTable4() {// 미완
	
	
	String sql = "drop table Campingcar";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;

	
	
}
public boolean dropTable5() {// 미완
	
	
	String sql = "drop table Rental_Company";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;

	
	
}
public boolean dropTable6() {// 미완
	
	
	String sql = "drop table Repair_Company";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;

	
	
}

public boolean reviveTable1() {// 미완
	
	
	String sql = "create table Customer as select * from tmpCustomer";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
		
			return true;
		}
		else {
		
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
}

public boolean reviveTable2() {// 미완
	
	
	String sql = "create table Campingcar as select * from tmpCampingcar";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
}
	
	

public boolean reviveTable3() {// 미완
	
	
	String sql = "create table Repair_Inform as select * from tmpRepair_Inform";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
}
	
	

public boolean reviveTable4() {// 미완
	
	
	String sql = "create table Repair_Company as select * from tmpRepair_Company";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
}
	
	

public boolean reviveTable5() {// 미완
	
	
	String sql = "create table Rental_Inform as select * from tmpRental_Inform";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
}

	
	

public boolean reviveTable6() {// 미완
	
	
	String sql = "create table Rental_Company as select * from tmpRental_Company";
		

		try {
			pstmt = conn.prepareStatement(sql);
			
			int op = pstmt.executeUpdate();
			pstmt.close();
			
			if(op > 0) {
			System.out.println("삭제 완료!");
			return true;
		}
		else {
			System.out.println("삭제 실패!");
			return false;
		}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;

	
}

	/*

	drop table Repair_Inform;
drop table Rental_Inform;
drop table Customer;
drop table CampingCar;
drop table Rental_Company;
drop table Repair_Company;
	*/
	
	public boolean initialize() {// 초기화
		
  		
		dropTable1();
		dropTable2();
		dropTable3();
		dropTable4();
		dropTable5();
		dropTable6();
		
		reviveTable1();
		reviveTable2();
		reviveTable3();
		reviveTable4();
		reviveTable5();
		reviveTable6();
		


		
		return true;
	}

		
	


   
}

 



class DescribeTableVO {
	private String column_name;

	public DescribeTableVO(String column_name) {
		super();
		this.column_name = column_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	@Override
	public String toString() {
		return "DescribeTableVO [column_name=" + column_name + "]";
	}

}



class newDBUI extends JPanel{
	//UI제작하자!
	CampingModel CM;// dbconnect
	private JPanel mainPanel;
	private JPanel tempPanel;
	private JComboBox cmbMenu;
	private String[] strMenu = { "CUSTOMER", "CAMPINGCAR", "RENTAL_COMPANY","RENTAL_INFORM","REPAIR_COMPANY","REPAIR_INFORM"};
	private JButton btnSearch;
	private JButton btnSearch1;
	private JButton btnSearch2;
	private JButton btnSearch3;
	private JButton btnSearch4;
	private JButton btnSearch5;
	private JButton initBtn;
	private JScrollPane jScollPane;
	private JTable jTable;
	private int cnt = 0;//성수기증가 원상복구를 위함
	
	JLabel msgLabel;
	
	JTextField txtDeleteCus1;
	JTextField txtDeleteCus2;
	JTextField txtDeleteCus3;
	JTextField txtDeleteCus4;
	JTextField txtDeleteCus5;
	JTextField txtDeleteCus6;
	
	JTextField txtUpdateCus1;
	JTextField txtUpdateCus2;
	JTextField txtUpdateCus3;
	JTextField txtUpdateCus4;
	JTextField txtUpdateCus5;
	JTextField txtUpdateCus6;
	JTextField txtUpdateCus7;
	
	//customer 삽입을 위한 txt필드
	JTextField txtCusWhere;
	JTextField txtCusID;
	JTextField txtCusName;
	JTextField txtAge;
	JTextField txtRank;
	JTextField txtJob;
	JTextField txtReserve;
	
	JTextField txtProdWhere;
	JTextField txtProdNo;
	JTextField txtProName;
	JTextField txtInvertory;
	JTextField txtPrice;
	JTextField txtManufacturer;
	JTextField txtcusTelno;
	JTextField txtcusEmail;
	JTextField txtcusUsedate;
	JTextField txtcusCarImform;

	JTextField txtCusOrderWhere;
	JTextField txtOrderNo;
	JTextField txtOrderCus;
	JTextField txtOrderPro;
	JTextField txtQuantity;
	JTextField txtAddress;
	JTextField txtOrderDate;
	
	//캠핑카 삽입을위한 txt필드
	JTextField txtcarID;
	JTextField txtcarname;
	JTextField txtcarnum;
	JTextField txtcarnop;
	JTextField txtcarimage;
	JTextField txtcarinform;
	JTextField txtcost;
	JTextField txtcompid;
	JTextField txtfinishdate;
	

	//대여회사삽입을 위한 txt필드

	JTextField txtrc_ID;
	JTextField txtrc_name;
	JTextField txtrc_address;
	JTextField txtrc_call;
	JTextField txtrc_empname;
	JTextField txtrc_empemail;
	
	
	//대여정보 삽입를 위한 txt필드
		JTextField txtr_num;
		JTextField txtr_id;
		JTextField txtr_linum;
		JTextField txtr_comid;
		JTextField txtr_startdate;
		JTextField txtr_day;
		JTextField txtr_cost;
		JTextField txtr_enddate;
		JTextField txtr_adcategory;
		JTextField txtr_addcost;
		
		//정비회사 삽입을 위한 txt필드
		

		JTextField txtRE_ID;
		JTextField txtRE_name;
		JTextField txtRE_address;
		JTextField txtRE_call;
		JTextField txtRE_empname;
		JTextField txtRE_empemail;
		
		// 정비정보 삽입을 위한 TXT필드
		
		JTextField txtRI_info;
		JTextField txtRI_carid;
		JTextField txtRI_id;
		JTextField txtRI_comid;
		JTextField txtRI_linum;
		JTextField txtRI_detail;
		JTextField txtRI_date;
		JTextField txtRI_cost;
		JTextField txtRI_end;
		JTextField txtRI_etc;
	
	
	
	
	private BtnListener btnL;
	
	public newDBUI() {
		// db connect
		CM = new CampingModel();
		
		//listener
		btnL = new BtnListener();
		
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(1000, 680));
		setLayout(null);
		
		setPage();
	
	}
	
	public void setPage() {

		mainPanel = new JPanel();
		mainPanel.setBounds(50, 150, 900, 450);
		mainPanel.setBackground(new Color(245, 245, 245));
		mainPanel.setLayout(null);

		// combo box for choosing table
		cmbMenu = new JComboBox();
		for (String str : strMenu)
		cmbMenu.addItem(str);
		cmbMenu.setBounds(50, 105, 170, 40);
		cmbMenu.setFont(new Font("", Font.PLAIN, 18));
		add(cmbMenu);
		
		btnSearch1 = new JButton("Search1");
		btnSearch1.setBounds(50, 620, 100, 40);
		btnSearch1.setFont(new Font("", Font.PLAIN, 13)); 
		//btnSearch1.setBackground(Color.WHITE);
		btnSearch1.addActionListener(btnL);
		
		btnSearch2 = new JButton("Search2");
		btnSearch2.setBounds(150, 620, 100, 40);
		btnSearch2.setFont(new Font("", Font.PLAIN, 13)); 
		btnSearch2.addActionListener(btnL);
		
		btnSearch3 = new JButton("Search3");
		btnSearch3.setBounds(250, 620, 100, 40);
		btnSearch3.setFont(new Font("", Font.PLAIN, 13)); 
		btnSearch3.addActionListener(btnL);
		
		btnSearch4 = new JButton("Search4");
		btnSearch4.setBounds(350, 620, 100, 40);
		btnSearch4.setFont(new Font("", Font.PLAIN, 13)); 
		btnSearch4.addActionListener(btnL);
		
		btnSearch5 = new JButton("Search5");
		btnSearch5.setBounds(450, 620, 100, 40);
		btnSearch5.setFont(new Font("", Font.PLAIN, 13)); 
		btnSearch5.addActionListener(btnL);
		
		
		////////초기화
		initBtn = new JButton("initialize");
		initBtn.setBounds(600, 620, 100, 40);
		initBtn.setFont(new Font("", Font.PLAIN, 13)); 
		initBtn.addActionListener(btnL);
		
	
		

		btnSearch = new JButton("Search");
		btnSearch.setBounds(225, 105, 80, 40);
		btnSearch.setFont(new Font("", Font.PLAIN, 13)); 
		btnSearch.addActionListener(btnL);
		
		msgLabel = new JLabel("##안녕하세요. 캠핑카 관리 시스템입니다.");
		msgLabel.setBounds(350, 105, 800, 40);
		msgLabel.setFont(new Font("", Font.PLAIN, 18));
		
		add(btnSearch1);
		add(btnSearch2);
		add(btnSearch3);
		add(btnSearch4);
		add(btnSearch5);
		add(initBtn);//////
		add(msgLabel);
		add(btnSearch);
		add(mainPanel);
		setVisible(true);	
		}
	
	
	private JPanel getCustomerTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		

		/*******SELECT********/
		
		JLabel lbWhere = new JLabel("Enter Name");
		lbWhere.setBounds(80,60,150,40);
		lbWhere.setFont(new Font("", Font.BOLD,17));
		jPanel.add(lbWhere);
		
		
		txtDeleteCus1 = new JTextField();
		txtDeleteCus1.setBounds(200,65,200,30);
		txtDeleteCus1.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtDeleteCus1);
		
//		btnSelect
		JButton btnSelect = new JButton("Delete Customer");// customer의 튜플을 입력받은 name 기준으로 삭제하는 버튼
		btnSelect.setBounds(420, 65, 200, 30);
		btnSelect.setFont(new Font("", Font.PLAIN, 15));
		btnSelect.addActionListener(btnL);
		jPanel.add(btnSelect);
		


		JLabel lbname = new JLabel("Enter Name");
		lbname.setBounds(80,100,150,40);
		lbname.setFont(new Font("", Font.BOLD,17));
		jPanel.add(lbname);
		
		txtUpdateCus1 = new JTextField();
		txtUpdateCus1.setBounds(200,100,200,30);
		txtUpdateCus1.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtUpdateCus1);
		
		JButton btnUpdate = new JButton("Last_Use 업데이트");
		btnUpdate.setBounds(420, 100, 200, 30);
		btnUpdate.setFont(new Font("", Font.PLAIN, 15));
		btnUpdate.addActionListener(btnL);
		jPanel.add(btnUpdate);
		
	
		
		
		
		//insert 구현~~
		JLabel lbInsert = new JLabel("INSERT");
		lbInsert.setBounds(30, 140, 100, 40);
		lbInsert.setFont(new Font("Verdana", Font.BOLD, 17));
		jPanel.add(lbInsert);
		
		
		JLabel lbCusID = new JLabel("Li_num");
		lbCusID.setBounds(30, 170, 100, 40);
		lbCusID.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCusID);

		txtCusID = new JTextField();
		txtCusID.setBounds(100, 180, 60, 30);
		jPanel.add(txtCusID);
		
		JLabel lbCusName = new JLabel("Name");
		lbCusName.setBounds(165, 170, 100, 40);
		lbCusName.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCusName);

		txtCusName = new JTextField();
		txtCusName.setBounds(215, 180, 60, 30);
		jPanel.add(txtCusName);
		
		JLabel lbAge = new JLabel("Address");
		lbAge.setBounds(280, 170, 100, 40);
		lbAge.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbAge);

		txtAddress = new JTextField();
		txtAddress.setBounds(345, 180, 60, 30);
		jPanel.add(txtAddress);
		
		JLabel lbTel = new JLabel("Tel");
		lbTel.setBounds(410, 170, 100, 40);
		lbTel.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbTel);
		
		txtcusTelno = new JTextField();
		txtcusTelno.setBounds(435, 180, 60, 30);
		jPanel.add(txtcusTelno);
		
		
		
		
		JLabel lbmail = new JLabel("Email");
		lbmail.setBounds(500, 170, 100, 40);
		lbmail.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbmail);
		
		txtcusEmail = new JTextField();
		txtcusEmail.setBounds(545, 180, 60, 30);
		jPanel.add(txtcusEmail);
		
		JLabel lbuse = new JLabel("Last_Use");
		lbuse.setBounds(610, 170, 100, 40);
		lbuse.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbuse);
		
		txtcusUsedate = new JTextField();
		txtcusUsedate.setBounds(685, 180, 60, 30);
		jPanel.add(txtcusUsedate);
		
		
		
		JLabel lbInform = new JLabel("Info");
		lbInform.setBounds(750, 170, 100, 40);
		lbInform.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbInform);
		
		txtcusCarImform = new JTextField();
		txtcusCarImform.setBounds(785, 180, 60, 30);
		jPanel.add(txtcusCarImform);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(350, 230, 80, 35);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInsert.addActionListener(btnL);
		jPanel.add(btnInsert);
		
		
		///////////////
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("Customer");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<Customer> arrCustomerVO = CM.getCustomerAll();

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getliscence_num());
			rowData[0] = arrCustomerVO.get(i).getliscence_num();
			rowData[1] = arrCustomerVO.get(i).getname();
			rowData[2] = arrCustomerVO.get(i).getaddress();
			rowData[3] = arrCustomerVO.get(i).gettel();
			rowData[4] = arrCustomerVO.get(i).getemail();
			rowData[5] = arrCustomerVO.get(i).getlast_date();
			rowData[6] = arrCustomerVO.get(i).getinform();

			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
//		jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	}
	
	private JPanel getCampingCarTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		

		//삭제버튼
		JLabel lbWhere = new JLabel("Enter ID");
		lbWhere.setBounds(80,60,150,40);
		lbWhere.setFont(new Font("", Font.BOLD,17));
		jPanel.add(lbWhere);
		
		
		txtDeleteCus2 = new JTextField();
		txtDeleteCus2.setBounds(200,65,200,30);
		txtDeleteCus2.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtDeleteCus2);
		
//		btnSelect
		JButton btnSelect = new JButton("Delete Campingcar");
		btnSelect.setBounds(420, 65, 200, 30);
		btnSelect.setFont(new Font("", Font.PLAIN, 15));
		btnSelect.addActionListener(btnL);
		jPanel.add(btnSelect);
		
		
		JLabel lbname = new JLabel("Enter ID");
		lbname.setBounds(80,100,150,40);
		lbname.setFont(new Font("", Font.BOLD,17));
		jPanel.add(lbname);
		
		txtUpdateCus2 = new JTextField();
		txtUpdateCus2.setBounds(200,100,200,30);
		txtUpdateCus2.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtUpdateCus2);
		
		JButton btnUpdate = new JButton("캠핑카 등록일 업데이트");
		btnUpdate.setBounds(420, 100, 200, 30);
		btnUpdate.setFont(new Font("", Font.PLAIN, 15));
		btnUpdate.addActionListener(btnL);
		jPanel.add(btnUpdate);
		
		//INSERT
		JLabel lbInsert = new JLabel("INSERT");
		lbInsert.setBounds(30, 140, 100, 40);
		lbInsert.setFont(new Font("Verdana", Font.BOLD, 17));
		jPanel.add(lbInsert);
		
		
		
		JLabel lbCarID = new JLabel("ID");
		lbCarID.setBounds(10, 170, 100, 40);
		lbCarID.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCarID);
		
		
		txtcarID = new JTextField();
		txtcarID.setBounds(35, 180, 60, 30);
		jPanel.add(txtcarID);
		
		JLabel lbCarname = new JLabel("NAME");
		lbCarname.setBounds(100, 170, 100, 40);
		lbCarname.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCarname);
		
		
		txtcarname = new JTextField();
		txtcarname.setBounds(145, 180, 60, 30);
		jPanel.add(txtcarname);
		
		JLabel lbCarnum = new JLabel("NUM");
		lbCarnum.setBounds(210, 170, 100, 40);
		lbCarnum.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCarnum);
		
		
		txtcarnum = new JTextField();
		txtcarnum.setBounds(250, 180, 40, 30);
		jPanel.add(txtcarnum);
		
		JLabel lbCarnop = new JLabel("NOP");
		lbCarnop.setBounds(295, 170, 100, 40);
		lbCarnop.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCarnop);
		
		
		txtcarnop = new JTextField();
		txtcarnop.setBounds(330, 180, 30, 30);
		jPanel.add(txtcarnop);
		
		JLabel lbCarimage = new JLabel("IMG");
		lbCarimage.setBounds(375, 170, 100, 40);
		lbCarimage.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCarimage);
		
		
		txtcarimage = new JTextField();
		txtcarimage.setBounds(415, 180, 60, 30);
		jPanel.add(txtcarimage);
		
		
		
		JLabel lbCarinform = new JLabel("INFORM");
		lbCarinform.setBounds(480, 170, 100, 40);
		lbCarinform.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCarinform);
		
		
		txtcarinform = new JTextField();
		txtcarinform.setBounds(540, 180, 60, 30);
		jPanel.add(txtcarinform);
		
		
		JLabel lbCarcost = new JLabel("COST");
		lbCarcost.setBounds(605, 170, 100, 40);
		lbCarcost.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCarcost);
		
		
		txtcost = new JTextField();
		txtcost.setBounds(650, 180, 30, 30);
		jPanel.add(txtcost);
		
		
		
		
		
		JLabel lbCarcompid = new JLabel("COMPID");
		lbCarcompid.setBounds(685, 170, 100, 40);
		lbCarcompid.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCarcompid);
		
		
		txtcompid = new JTextField();
		txtcompid.setBounds(750, 180, 60, 30);
		jPanel.add(txtcompid);
		
		
		JLabel lbCardate = new JLabel("DATE");
		lbCardate.setBounds(815, 170, 100, 40);
		lbCardate.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCardate);
		
		
		txtfinishdate = new JTextField();
		txtfinishdate.setBounds(860, 180, 30, 30);
		jPanel.add(txtfinishdate);
		
		
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(350, 230, 80, 35);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInsert.addActionListener(btnL);
		jPanel.add(btnInsert);
		
		
		
		
		
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("CampingCar");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<CampingCar> arrCustomerVO = CM.getCampingCarAll();///////////수정필요

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getCar_regist_id());
			rowData[0] = arrCustomerVO.get(i).getCar_regist_id();
			rowData[1] = arrCustomerVO.get(i).getname();
			rowData[2] = arrCustomerVO.get(i).getcar_num();
			rowData[3] = arrCustomerVO.get(i).getNOP();
			rowData[4] = arrCustomerVO.get(i).getimage();
			rowData[5] = arrCustomerVO.get(i).getinform();
			rowData[6] = arrCustomerVO.get(i).getcost();
			rowData[7] = arrCustomerVO.get(i).getlent_id();
			rowData[8] = arrCustomerVO.get(i).getregist_day();

			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
		//jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	}
	private JPanel getReantal_infromTable(String condition) {//캠핑카 대여정보 
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		
				
		JButton btnSelect = new JButton("성수기 가격인상");
		btnSelect.setBounds(680, 40, 200, 35);
		btnSelect.setFont(new Font("", Font.PLAIN, 15));
		btnSelect.addActionListener(btnL);
		jPanel.add(btnSelect);
		
		JButton btnBackSelect = new JButton("원상복구");
		btnBackSelect.setBounds(680, 85, 200, 35);
		btnBackSelect.setFont(new Font("", Font.PLAIN, 15));
		btnBackSelect.addActionListener(btnL);
		jPanel.add(btnBackSelect);
		
	
		JLabel lbWhere = new JLabel("Enter R_NUM");
		lbWhere.setBounds(80,60,150,40);
		lbWhere.setFont(new Font("", Font.BOLD,17));
		jPanel.add(lbWhere);
				
				
		txtDeleteCus3 = new JTextField();
		txtDeleteCus3.setBounds(200,65,200,30);
		txtDeleteCus3.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtDeleteCus3);
				
//				btnSelect
		JButton btnSelect1 = new JButton("Delete Rental_Inform");
		btnSelect1.setBounds(420, 65, 200, 30);	
		btnSelect1.setFont(new Font("", Font.PLAIN, 15));
		btnSelect1.addActionListener(btnL);
		jPanel.add(btnSelect1);
		
		
		JLabel lbInsert = new JLabel("INSERT");
		lbInsert.setBounds(30, 100, 100, 40);
		lbInsert.setFont(new Font("Verdana", Font.BOLD, 17));
		jPanel.add(lbInsert);
		
		
		
		
		
		
		
		JLabel lbr_num = new JLabel("num");
		lbr_num.setBounds(30, 130, 100, 40);
		lbr_num.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbr_num);

		txtr_num = new JTextField();
		txtr_num.setBounds(95, 140, 60, 30);
		jPanel.add(txtr_num);
		
		
		JLabel lbr_id = new JLabel("id");
		lbr_id.setBounds(175, 130, 100, 40);
		lbr_id.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbr_id);
		
		txtr_id = new JTextField();
		txtr_id.setBounds(255, 140, 60, 30);
		jPanel.add( txtr_id);
		
		
		JLabel lblinum = new JLabel("linum");
		lblinum.setBounds(345, 130, 100, 40);
		lblinum.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lblinum);

		txtr_linum = new JTextField();
		txtr_linum.setBounds(440, 140, 60, 30);
		jPanel.add(txtr_linum);
		
		JLabel lbr_comid = new JLabel("comp_id");
		 lbr_comid.setBounds(530, 130, 100, 40);
		 lbr_comid.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add( lbr_comid);

		 txtr_comid = new JTextField();
		 txtr_comid.setBounds(620, 140, 60, 30);
		jPanel.add( txtr_comid);
		
		JLabel lbstart_date = new JLabel("Start");
		lbstart_date.setBounds(700, 130, 100, 40);
		lbstart_date.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbstart_date);
		
		 txtr_startdate = new JTextField();
		 txtr_startdate.setBounds(790, 140, 60, 30);
		jPanel.add( txtr_startdate);
		
		
		
		
		JLabel lbr_day = new JLabel("day");
		lbr_day.setBounds(30, 170, 100, 40);
		lbr_day.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbr_day);
		
		txtr_day = new JTextField();
		txtr_day.setBounds(95, 180, 60, 30);
		jPanel.add(txtr_day);
		
		
		
		
		
		
		JLabel lbr_cost = new JLabel("cost");
		lbr_cost.setBounds(170, 170, 100, 40);
		lbr_cost.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbr_cost);

		txtr_cost = new JTextField();
		txtr_cost.setBounds(255, 180, 60, 30);
		jPanel.add(txtr_cost);
		
		JLabel lbend_date = new JLabel("End");
		lbend_date.setBounds(345, 170, 100, 40);
		lbend_date.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbend_date);

		txtr_enddate = new JTextField();
		txtr_enddate.setBounds(440, 180, 60, 30);
		jPanel.add(txtr_enddate);
		
		JLabel lbad_category = new JLabel("Category");
		lbad_category.setBounds(530, 170, 100, 40);
		lbad_category.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbad_category);

		txtr_adcategory = new JTextField();
		txtr_adcategory.setBounds(620, 180, 60, 30);
		jPanel.add(txtr_adcategory);
		
		JLabel lbr_add_cost = new JLabel("Add_cost");
		lbr_add_cost.setBounds(700, 170, 100, 40);
		lbr_add_cost.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbr_add_cost);
		
		txtr_addcost = new JTextField();
		txtr_addcost.setBounds(790, 180, 60, 30);
		jPanel.add(txtr_addcost);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(350, 230, 80, 35);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInsert.addActionListener(btnL);
		jPanel.add(btnInsert);
		
		
		
		
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("Rental_Inform");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<Rental_Inform> arrCustomerVO = CM.getRental_InformAll();///////////수정필요

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getRental_num());
			rowData[0] = arrCustomerVO.get(i).getRental_num();
			rowData[1] = arrCustomerVO.get(i).getid();
			rowData[2] = arrCustomerVO.get(i).getcar_num();
			rowData[3] = arrCustomerVO.get(i).getcom_id();
			rowData[4] = arrCustomerVO.get(i).getrent_start();
			rowData[5] = arrCustomerVO.get(i).getrent_day();
			rowData[6] = arrCustomerVO.get(i).getcost();
			rowData[7] = arrCustomerVO.get(i).getrent_end();
			rowData[8] = arrCustomerVO.get(i).getbill();
			rowData[9] = arrCustomerVO.get(i).getbill_money();

			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
		//jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	}
	
	private JPanel getRental_CompanyTable(String condition) {//캠핑카 대여정보 
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		

		JLabel lbInsert = new JLabel("INSERT");
		lbInsert.setBounds(30, 140, 100, 40);
		lbInsert.setFont(new Font("Verdana", Font.BOLD, 17));
		jPanel.add(lbInsert);
		

		//삭제버튼
		JLabel lbWhere = new JLabel("Enter RC_ID");
		lbWhere.setBounds(80,60,150,40);
		lbWhere.setFont(new Font("", Font.BOLD,17));
		jPanel.add(lbWhere);
		
		
		txtDeleteCus4 = new JTextField();
		txtDeleteCus4.setBounds(200,65,200,30);
		txtDeleteCus4.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtDeleteCus4);
		
//		btnSelect
		JButton btnSelect = new JButton("Delete Rental_Company");
		btnSelect.setBounds(420, 65, 200, 30);
		btnSelect.setFont(new Font("", Font.PLAIN, 15));
		btnSelect.addActionListener(btnL);
		jPanel.add(btnSelect);
		
		

		JLabel RCWhere = new JLabel("Enter RC_EMPNAME");
		RCWhere.setBounds(80,100,180,40);
		RCWhere.setFont(new Font("", Font.BOLD,17));
		jPanel.add(RCWhere);
				
				
		txtUpdateCus3 = new JTextField();// 변경을 위해 검색하는 텍스트 필드
		txtUpdateCus3.setBounds(260,110,80,30);
		txtUpdateCus3.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtUpdateCus3);
		

		JLabel RCto = new JLabel("To");
		RCto.setBounds(360,100,20,40);
		RCto.setFont(new Font("", Font.BOLD,17));
		jPanel.add(RCto);
		
		txtUpdateCus4 = new JTextField();// 검색한 텍스트를 바꿔줄 필드
		txtUpdateCus4.setBounds(420,110,80,30);
		txtUpdateCus4.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtUpdateCus4);
				
//				btnSelect
		JButton btnSelect2 = new JButton("대여담당자 이름 업데이트");
		btnSelect2.setBounds(520, 110, 200, 30);	
		btnSelect2.setFont(new Font("", Font.PLAIN, 15));
		btnSelect2.addActionListener(btnL);
		jPanel.add(btnSelect2);
		
		
		JLabel lbrcID = new JLabel("RC_ID");
		lbrcID.setBounds(30, 170, 100, 40);
		lbrcID.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbrcID);

		txtrc_ID = new JTextField();
		txtrc_ID.setBounds(100, 180, 60, 30);
		jPanel.add(txtrc_ID);
		
		JLabel lbrcName = new JLabel("Name");
		lbrcName.setBounds(165, 170, 100, 40);
		lbrcName.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbrcName);

		txtrc_name = new JTextField();
		txtrc_name.setBounds(215, 180, 60, 30);
		jPanel.add(txtrc_name);
		
		JLabel lbrcaddress = new JLabel("Address");
		lbrcaddress.setBounds(280, 170, 100, 40);
		lbrcaddress.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbrcaddress);

		txtrc_address = new JTextField();
		txtrc_address.setBounds(345, 180, 60, 30);
		jPanel.add(txtrc_address);
		
		JLabel lbrc_call = new JLabel("CALL");
		lbrc_call.setBounds(410, 170, 100, 40);
		lbrc_call.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbrc_call);
		
		txtrc_call = new JTextField();
		txtrc_call.setBounds(455, 180, 60, 30);
		jPanel.add(txtrc_call);
		
		
		
		
		JLabel lbrcempname = new JLabel("Empname");
		lbrcempname.setBounds(525, 170, 100, 40);
		lbrcempname.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbrcempname);
		
		txtrc_empname = new JTextField();
		txtrc_empname.setBounds(610, 180, 60, 30);
		jPanel.add(txtrc_empname);
		
		JLabel lbuse = new JLabel("Emp email");
		lbuse.setBounds(680, 170, 100, 40);
		lbuse.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbuse);
		
		txtrc_empemail = new JTextField();
		txtrc_empemail.setBounds(770, 180, 60, 30);
		jPanel.add(txtrc_empemail);
		
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(350, 230, 80, 35);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInsert.addActionListener(btnL);
		jPanel.add(btnInsert);
		
		
	
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("Rental_Company");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<Rental_Company> arrCustomerVO = CM.getRental_CompanyAll();

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getid());
			rowData[0] = arrCustomerVO.get(i).getid();
			rowData[1] = arrCustomerVO.get(i).getname();
			rowData[2] = arrCustomerVO.get(i).getaddress();
			rowData[3] = arrCustomerVO.get(i).getcall();
			rowData[4] = arrCustomerVO.get(i).getManager_name();
			rowData[5] = arrCustomerVO.get(i).getManager_mail();
		
			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
		//jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	}
	private JPanel getRepair_CompanyTable(String condition) {//캠핑카 대여정보 //수정필요
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		

		//삭제버튼
		JLabel lbWhere = new JLabel("Enter R_ID");
		lbWhere.setBounds(80,60,150,40);
		lbWhere.setFont(new Font("", Font.BOLD,17));
		jPanel.add(lbWhere);
		
		
		txtDeleteCus5 = new JTextField();
		txtDeleteCus5.setBounds(200,65,200,30);
		txtDeleteCus5.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtDeleteCus5);
		
//		btnSelect
		JButton btnSelect = new JButton("Delete Repair_Company");
		btnSelect.setBounds(420, 65, 200, 30);
		btnSelect.setFont(new Font("", Font.PLAIN, 15));
		btnSelect.addActionListener(btnL);
		jPanel.add(btnSelect);

		JLabel RCWhere = new JLabel("Enter R_EMPNAME");
		RCWhere.setBounds(80,100,180,40);
		RCWhere.setFont(new Font("", Font.BOLD,17));
		jPanel.add(RCWhere);
				
				
		txtUpdateCus5 = new JTextField();// 변경을 위해 검색하는 텍스트 필드
		txtUpdateCus5.setBounds(260,110,80,30);
		txtUpdateCus5.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtUpdateCus5);
		

		JLabel RCto = new JLabel("To");
		RCto.setBounds(360,100,20,40);
		RCto.setFont(new Font("", Font.BOLD,17));
		jPanel.add(RCto);
		
		txtUpdateCus6 = new JTextField();// 검색한 텍스트를 바꿔줄 필드
		txtUpdateCus6.setBounds(420,110,80,30);
		txtUpdateCus6.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtUpdateCus6);
		
		JButton btnSelect2 = new JButton("정비담당자 이름 업데이트");
		btnSelect2.setBounds(520, 110, 200, 30);	
		btnSelect2.setFont(new Font("", Font.PLAIN, 15));
		btnSelect2.addActionListener(btnL);
		jPanel.add(btnSelect2);
				
		JLabel lbInsert = new JLabel("INSERT");
		lbInsert.setBounds(30, 140, 100, 40);
		lbInsert.setFont(new Font("Verdana", Font.BOLD, 17));
		jPanel.add(lbInsert);
		
		
		
		
		
		JLabel lbreID = new JLabel("RE_ID");
		lbreID.setBounds(30, 170, 100, 40);
		lbreID.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbreID);

		txtRE_ID = new JTextField();
		txtRE_ID.setBounds(100, 180, 60, 30);
		jPanel.add(txtRE_ID);
		
		JLabel lbreName = new JLabel("Name");
		lbreName.setBounds(165, 170, 100, 40);
		lbreName.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbreName);

		txtRE_name = new JTextField();
		txtRE_name.setBounds(215, 180, 60, 30);
		jPanel.add(txtRE_name);
		
		JLabel lbreaddress = new JLabel("Address");
		lbreaddress.setBounds(280, 170, 100, 40);
		lbreaddress.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbreaddress);

		txtRE_address = new JTextField();
		txtRE_address.setBounds(345, 180, 60, 30);
		jPanel.add(txtRE_address);
		
		JLabel lbre_call = new JLabel("CALL");
		lbre_call.setBounds(410, 170, 100, 40);
		lbre_call.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbre_call);
		
		txtRE_call = new JTextField();
		txtRE_call.setBounds(455, 180, 60, 30);
		jPanel.add(txtRE_call);
		
		
		
		
		JLabel lbreempname = new JLabel("Empname");
		lbreempname.setBounds(525, 170, 100, 40);
		lbreempname.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbreempname);
		
		txtRE_empname = new JTextField();
		txtRE_empname.setBounds(610, 180, 60, 30);
		jPanel.add(txtRE_empname);
		
		JLabel lbremail = new JLabel("Emp email");
		lbremail.setBounds(680, 170, 100, 40);
		lbremail.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbremail);
		
		txtRE_empemail = new JTextField();
		txtRE_empemail.setBounds(770, 180, 60, 30);
		jPanel.add(txtRE_empemail);
		

		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(350, 230, 80, 35);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInsert.addActionListener(btnL);
		jPanel.add(btnInsert);
		
		
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("Repair_Company");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<Repair_Company> arrCustomerVO = CM.getRepair_CompanyAll();

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getid());
			rowData[0] = arrCustomerVO.get(i).getid();
			rowData[1] = arrCustomerVO.get(i).getname();
			rowData[2] = arrCustomerVO.get(i).getaddress();
			rowData[3] = arrCustomerVO.get(i).getcall();
			rowData[4] = arrCustomerVO.get(i).getManager_name();
			rowData[5] = arrCustomerVO.get(i).getManager_mail();
		
			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
		//jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	}
	
	private JPanel getRepair_InformTable(String condition) {//캠핑카 대여정보 //수정필요
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		
		
		//삭제버튼
		JLabel lbWhere = new JLabel("Enter RI_INFO");
		lbWhere.setBounds(80,40,150,40);
		lbWhere.setFont(new Font("", Font.BOLD,17));
		jPanel.add(lbWhere);
				

		txtDeleteCus6 = new JTextField();
		txtDeleteCus6.setBounds(200,45,200,30);
		txtDeleteCus6.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtDeleteCus6);
		
		//삽입
		JLabel lbInsert = new JLabel("INSERT");
		lbInsert.setBounds(30, 100, 100, 40);
		lbInsert.setFont(new Font("Verdana", Font.BOLD, 17));
		jPanel.add(lbInsert);
		
		
		JLabel lbri_info = new JLabel("RI_info");
		lbri_info.setBounds(30, 130, 100, 40);
		lbri_info.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbri_info);

		txtRI_info = new JTextField();
		txtRI_info.setBounds(95, 140, 60, 30);
		jPanel.add(txtRI_info);
		
		
		JLabel lbri_cid = new JLabel("Car_id");
		lbri_cid.setBounds(175, 130, 100, 40);
		lbri_cid.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbri_cid);
		
		txtRI_carid = new JTextField();
		txtRI_carid.setBounds(255, 140, 60, 30);
		jPanel.add( txtRI_carid);
		
		
		JLabel lbri_id = new JLabel("RI_id");
		lbri_id.setBounds(345, 130, 100, 40);
		lbri_id.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbri_id);

		txtRI_id = new JTextField();
		txtRI_id.setBounds(440, 140, 60, 30);
		jPanel.add(txtRI_id);
		
		JLabel lbri_comid = new JLabel("RI_comp_id");
		 lbri_comid.setBounds(530, 130, 100, 40);
		 lbri_comid.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add( lbri_comid);

		txtRI_comid = new JTextField();
		txtRI_comid.setBounds(620, 140, 60, 30);
		jPanel.add( txtRI_comid);
		
		JLabel lbri_linum = new JLabel("RI_li_num");
		lbri_linum.setBounds(700, 130, 100, 40);
		lbri_linum.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbri_linum);
		
		txtRI_linum = new JTextField();
		txtRI_linum.setBounds(790, 140, 60, 30);
		jPanel.add( txtRI_linum);
		
		
		
		
		JLabel lbri_details = new JLabel("RI_details");
		lbri_details.setBounds(30, 170, 100, 40);
		lbri_details.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbri_details);
		
		txtRI_detail = new JTextField();
		txtRI_detail.setBounds(95, 180, 60, 30);
		jPanel.add(txtRI_detail);
		
		
		
		
		
		
		JLabel lbri_date = new JLabel("RI_date");
		lbri_date.setBounds(170, 170, 100, 40);
		lbri_date.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbri_date);

		txtRI_date = new JTextField();
		txtRI_date.setBounds(255, 180, 60, 30);
		jPanel.add(txtRI_date);
		
		JLabel lbri_cost = new JLabel("RI_cost");
		lbri_cost.setBounds(345, 170, 100, 40);
		lbri_cost.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbri_cost);

		txtRI_cost = new JTextField();
		txtRI_cost.setBounds(440, 180, 60, 30);
		jPanel.add(txtRI_cost);
		
		JLabel lbRI_end = new JLabel("RI_end");
		lbRI_end.setBounds(530, 170, 100, 40);
		lbRI_end.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbRI_end);

		txtRI_end = new JTextField();
		txtRI_end.setBounds(620, 180, 60, 30);
		jPanel.add(txtRI_end);
		
		JLabel lbri_etc = new JLabel("RI_etc");
		lbri_etc.setBounds(700, 170, 100, 40);
		lbri_etc.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbri_etc);
		
		txtRI_etc = new JTextField();
		txtRI_etc.setBounds(790, 180, 60, 30);
		jPanel.add(txtRI_etc);
	
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(350, 230, 80, 35);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInsert.addActionListener(btnL);
		jPanel.add(btnInsert);

		
//		btnSelect
		JButton btnSelect = new JButton("Delete Repair_Inform");
		btnSelect.setBounds(420, 45, 200, 30);
		btnSelect.setFont(new Font("", Font.PLAIN, 15));
		btnSelect.addActionListener(btnL);
		jPanel.add(btnSelect);
		
		JLabel lbname = new JLabel("Enter RI_INFO");
		lbname.setBounds(80,80,150,40);
		lbname.setFont(new Font("", Font.BOLD,17));
		jPanel.add(lbname);
		
		txtUpdateCus7 = new JTextField();
		txtUpdateCus7.setBounds(200,80,200,30);
		txtUpdateCus7.setFont(new Font("", Font.PLAIN,15));
		jPanel.add(txtUpdateCus7);
		
		JButton btnUpdate = new JButton("정비비용을 납입");
		btnUpdate.setBounds(420, 80, 200, 30);
		btnUpdate.setFont(new Font("", Font.PLAIN, 15));
		btnUpdate.addActionListener(btnL);
		jPanel.add(btnUpdate);

		//insert 구현~~
		
		
		
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("Repair_Inform");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<Repair_Inform> arrCustomerVO = CM.getRepair_InformAll();

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getrepair());
			rowData[0] = arrCustomerVO.get(i).getrepair();
			rowData[1] = arrCustomerVO.get(i).getid();
			rowData[2] = arrCustomerVO.get(i).getrepair_id();
			rowData[3] = arrCustomerVO.get(i).getcompany();
			rowData[4] = arrCustomerVO.get(i).getliscence_num();
			rowData[5] = arrCustomerVO.get(i).getdetails();
			rowData[6] = arrCustomerVO.get(i).getdate();
			rowData[7] = arrCustomerVO.get(i).getcost();
			rowData[8] = arrCustomerVO.get(i).getend();
			rowData[9] = arrCustomerVO.get(i).getetc();
		
			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
		//jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	}
	private JPanel getSearch1AllTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		

		///////////////
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("Customer");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<Customer> arrCustomerVO = CM.getSearch1All();

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getliscence_num());
			rowData[0] = arrCustomerVO.get(i).getliscence_num();
			rowData[1] = arrCustomerVO.get(i).getname();
			rowData[2] = arrCustomerVO.get(i).getaddress();
			rowData[3] = arrCustomerVO.get(i).gettel();
			rowData[4] = arrCustomerVO.get(i).getemail();
			rowData[5] = arrCustomerVO.get(i).getlast_date();
			rowData[6] = arrCustomerVO.get(i).getinform();

			model.addRow(rowData);
		}
	


		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
//		jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	}
	
	private JPanel getSearch2AllTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		


		
		//insert 구현~~
		
		
		
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("CampingCar");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<CampingCar> arrCustomerVO = CM.getSearch2All();///////////수정필요

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getCar_regist_id());
			rowData[0] = arrCustomerVO.get(i).getCar_regist_id();
			rowData[1] = arrCustomerVO.get(i).getname();
			rowData[2] = arrCustomerVO.get(i).getcar_num();
			rowData[3] = arrCustomerVO.get(i).getNOP();
			rowData[4] = arrCustomerVO.get(i).getimage();
			rowData[5] = arrCustomerVO.get(i).getinform();
			rowData[6] = arrCustomerVO.get(i).getcost();
			rowData[7] = arrCustomerVO.get(i).getlent_id();
			rowData[8] = arrCustomerVO.get(i).getregist_day();

			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
		//jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	
	}
	
	
	private JPanel getSearch3AllTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		
		
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("CampingCar");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<CampingCar> arrCustomerVO = CM.getSearch3All();///////////수정필요

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getCar_regist_id());
			rowData[0] = arrCustomerVO.get(i).getCar_regist_id();
			rowData[1] = arrCustomerVO.get(i).getname();
			rowData[2] = arrCustomerVO.get(i).getcar_num();
			rowData[3] = arrCustomerVO.get(i).getNOP();
			rowData[4] = arrCustomerVO.get(i).getimage();
			rowData[5] = arrCustomerVO.get(i).getinform();
			rowData[6] = arrCustomerVO.get(i).getcost();
			rowData[7] = arrCustomerVO.get(i).getlent_id();
			rowData[8] = arrCustomerVO.get(i).getregist_day();

			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
		//jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	
	}
	
	private JPanel getSearch4AllTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		


		
		//insert 구현~~
		
		
		
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("Search4");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<Search4> arrCustomerVO = CM.getSearch4All();///////////수정필요

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getcompany());
			rowData[0] = arrCustomerVO.get(i).getcompany();
			rowData[1] = arrCustomerVO.get(i).getcount();
			rowData[2] = arrCustomerVO.get(i).getsum();
			
		

			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
		//jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	
	}
	private JPanel getSearch5AllTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 900, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);
		


		
		//insert 구현~~
		
		
		
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = CM.getTableDescription("Repair_Company");
		
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);
		
		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
	
		ArrayList<Repair_Company> arrCustomerVO = CM.getSearch5All();///////////수정필요

		
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getid());
			rowData[0] = arrCustomerVO.get(i).getid();
			rowData[1] = arrCustomerVO.get(i).getname();
			rowData[2] = arrCustomerVO.get(i).getaddress();
			rowData[3] = arrCustomerVO.get(i).getcall();
			rowData[4] = arrCustomerVO.get(i).getManager_name();
			rowData[5] = arrCustomerVO.get(i).getManager_mail();
			

			model.addRow(rowData);
		}
	
		// 테이블의 내용 전부 가져오기

		
		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("", Font.PLAIN, 17));
		jTable.setFont(new Font("", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(30, 280, 850, 150);
		//jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());
		
		jPanel.add(jScollPane);
		
		
		
		return jPanel;
	
	}
	
	
	
	
	
	
	
	
	
	// 모든 버튼 이벤트 처리
	private class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
	

			JButton b = (JButton) event.getSource();
			String tableName;
			String condition = "";
			
			String btnName=null;
			btnName=b.getText();
			switch(btnName) {
				case "Search":
					mainPanel.removeAll();
					mainPanel.setLayout(null);
	
					tableName = strMenu[cmbMenu.getSelectedIndex()];
					condition = "";
					jTable = null;
					tempPanel = null;
					msgLabel.setText("##안녕하세요. 캠핑카 관리 시스템입니다.");
	
					switch (tableName) {
					case "CUSTOMER":
						//condition 추가?
						tempPanel = getCustomerTable(condition);
						break;
						// 이벤트 추가
					case "CAMPINGCAR":
						
						tempPanel = getCampingCarTable(condition);
						break;
					
					case "RENTAL_INFORM":
						
						tempPanel = getReantal_infromTable(condition);
						break;
					
					case "RENTAL_COMPANY":
						
						tempPanel = getRental_CompanyTable(condition);
						break;
						
					case "REPAIR_COMPANY":
						
						tempPanel = getRepair_CompanyTable(condition);
						break;
						
					case "REPAIR_INFORM":
						
						tempPanel = getRepair_InformTable(condition);
						break;
					}
					
	
					mainPanel.add(tempPanel);
					add(mainPanel);
	
					setVisible(true);
					validate();
					repaint();
					break;
					
		
					
				case "Insert":
					tableName = strMenu[cmbMenu.getSelectedIndex()];
					System.out.println("삽입 실행");
					
					switch (tableName) {
					case "CUSTOMER":
					
					if(CM.insertCustomer(txtCusID.getText(), txtCusName.getText(),
								txtAddress.getText(), txtcusTelno.getText(), txtcusEmail.getText(),
								Integer.parseInt(txtcusUsedate.getText()),txtcusCarImform.getText())) {
						msgLabel.setText("##삽입에 성공하였습니다. 다시검색하세요");
					}
					
					else {
						msgLabel.setText("##삽입에 실패하였습니다. info가 ASDFG형식인지 확인하세요");
					}
						
					
					break;
					
					case "CAMPINGCAR":
						if(CM.insertCampingcar(txtcarID.getText(),txtcarname.getText(),
								txtcarnum.getText(), Integer.parseInt(txtcarnop.getText()), txtcarimage.getText(),
								txtcarinform.getText(),  Integer.parseInt(txtcost.getText()), txtcompid.getText(),
								txtfinishdate.getText())) {
						msgLabel.setText("##삽입에 성공하였습니다. 다시검색하세요");
					}
					
					else {
						msgLabel.setText("##삽입에 실패하였습니다. comp_id가 QWER형식인지 확인하세요");
					}
						
					
					break;

					case "RENTAL_COMPANY":
						if(CM.insertRental_Company(txtrc_ID.getText(), txtrc_name.getText(),
								txtrc_address.getText(),txtrc_call.getText(),
								txtrc_empname.getText(),
								txtrc_empemail.getText())) {
						msgLabel.setText("##삽입에 성공하였습니다. 다시검색하세요");
					}
					
					else {
						msgLabel.setText("##삽입에 실패하였습니다.");
					}
						
					
					break;
					
					
					
					
					
					case "RENTAL_INFORM":
						
						
						SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

						
						
						
						try {
							if(CM.insertRental_Inform(txtr_num.getText(), txtr_id.getText(), txtr_linum.getText(),
									txtr_comid.getText(),transFormat.parse(txtr_startdate.getText()),Integer.parseInt(txtr_day.getText()),
									Integer.parseInt(txtr_cost.getText()), transFormat.parse(txtr_enddate.getText()), 
									txtr_adcategory.getText(), Integer.parseInt(txtr_addcost.getText()))){
								msgLabel.setText("##삽입에 성공하였습니다. 다시검색하세요.");
							}
							
							else {
								msgLabel.setText("##삽입에 실패하였습니다. comp_id, id, li_num이 형식에 맞는지 확인하세요.");
							}
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						break;
						
					case "REPAIR_COMPANY":
						if(CM.insertRepair_Company(txtRE_ID.getText(), txtRE_name.getText(), 
								txtRE_address.getText(),txtRE_call.getText(),
								txtRE_empname.getText(), txtRE_empemail.getText())) {
						msgLabel.setText("##삽입에 성공하였습니다. 다시검색하세요");
					}
					
					else {
						msgLabel.setText("##삽입에 실패하였습니다.");
					}
						
					
					break;
					case "REPAIR_INFORM":
						if(CM.insertRepair_Inform(txtRI_info.getText(), txtRI_carid.getText(), txtRI_id.getText(),
								txtRI_comid.getText(), txtRI_linum.getText(), txtRI_detail.getText(),
								txtRI_date.getText(), Integer.parseInt(txtRI_cost.getText()), txtRI_end.getText(), txtRI_etc.getText()))  {
							msgLabel.setText("##삽입에 성공하였습니다. 다시검색하세요");
						}
						
						else {
							msgLabel.setText("##삽입에 실패하였습니다. info, campingcar_id, id, company가 형식에 맞는지 확인하세요.");
						}
							
						
						break;
					}
					break;
						
					
					
					
					
					
					
					
				case "Delete Customer":// Customer 튜플 삭제
					System.out.println("Delete!!");
					String name = txtDeleteCus1.getText();
					
					
					while(name.length()<5)
						name = name+" ";// 최대사이즈에 맞게 검색
					System.out.println(name.length()+"삭제");
					System.out.println(name+"삭제");
					
					if(CM.deleteCustomer(name) == true) {
						msgLabel.setText("##"+name+"이 삭제되었습니다. 다시 검색하세요");
					
						
					}
					else
						msgLabel.setText("##해당 이름이 존재하지 않습니다.");
					
					break;
					
				case "Delete Campingcar":// Customer 튜플 삭제
					System.out.println("Delete!!");
					name = txtDeleteCus2.getText();
					String tmp = name;
					
					while(name.length()<30)
						name = name+" ";// 최대사이즈에 맞게 검색
					System.out.println(name+"삭제");
					System.out.println(name.length()+"삭제");
					if(CM.deleteCampingcar(name) == true) {
						msgLabel.setText("##"+tmp+"이 삭제되었습니다. 다시 검색하세요");
						                      
					}
					else
						msgLabel.setText("##해당 이름이 존재하지 않습니다.");
					
					break;
					
					//Delete Rental_Inform
				case "Delete Rental_Inform":// Customer 튜플 삭제
					System.out.println("Delete!!");
					name = txtDeleteCus3.getText();// 1 2 3 4 5
					tmp = name;
					
					while(name.length()<10)
						name = name+" ";// 최대사이즈에 맞게 검색
					System.out.println(name+"삭제");
					System.out.println(name.length()+"삭제");
					if(CM.deleteRental_Inform(name) == true) {
						msgLabel.setText("##"+tmp+"이 삭제되었습니다. 다시 검색하세요");
						                      
					}
					else
						msgLabel.setText("##해당 이름이 존재하지 않습니다.");
					
					break;
					
					//Delete Rental_Company
				case "Delete Rental_Company":// Customer 튜플 삭제
					System.out.println("Delete!!");
					name = txtDeleteCus4.getText();// 1 2 3 4 5
					tmp = name;
					
					while(name.length()<30)
						name = name+" ";// 최대사이즈에 맞게 검색
					System.out.println(name+"삭제");
				
					if(CM.deleteRental_Company(name) == true) {
						msgLabel.setText("##"+tmp+"이 삭제되었습니다. 다시 검색하세요");
						                      
					}
					else
						msgLabel.setText("##해당 이름이 존재하지 않습니다.");
					
					break;

				case "Delete Repair_Company":// Customer 튜플 삭제
					System.out.println("Delete!!");
					name = txtDeleteCus5.getText();// 1 2 3 4 5
					tmp = name;
					
					while(name.length()<10)
						name = name+" ";// 최대사이즈에 맞게 검색
					System.out.println(name+"삭제");
				
					if(CM.deleteRepair_Company(name) == true) {
						msgLabel.setText("##"+tmp+"이 삭제되었습니다. 다시 검색하세요");
						                      
					}
					else
						msgLabel.setText("##해당 이름이 존재하지 않습니다.");
					
					break;

				case "Delete Repair_Inform":// Customer 튜플 삭제
					System.out.println("Delete!!");
					name = txtDeleteCus6.getText();// 1 2 3 4 5
					tmp = name;
					
					while(name.length()<30)
						name = name+" ";// 최대사이즈에 맞게 검색
					System.out.println(name+"삭제");
				
					if(CM.deleteRepair_Inform(name) == true) {
						msgLabel.setText("##"+tmp+"이 삭제되었습니다. 다시 검색하세요");
						                      
					}
					else
						msgLabel.setText("##해당 이름이 존재하지 않습니다.");
					
					break;
				
					
				case "Search1":
					System.out.println("search1 !!");
					mainPanel.removeAll();
					mainPanel.setLayout(null);
					jTable = null;
					tempPanel = null;
					msgLabel.setText("##렌탈비용 체납자를 검색합니다.");
					
					tempPanel = getSearch1AllTable(condition);
					
					mainPanel.add(tempPanel);
					add(mainPanel);
	
					setVisible(true);
					validate();
					repaint();
					break;
					
				case "Search2":
					System.out.println("search2 !!");
					mainPanel.removeAll();
					mainPanel.setLayout(null);
					jTable = null;
					tempPanel = null;
					msgLabel.setText("##실적이 좋은 회사의 대여중인 캠핑카 정보를 검색합니다.");
					tempPanel = getSearch2AllTable(condition);
					
					mainPanel.add(tempPanel);
					add(mainPanel);
	
					setVisible(true);
					validate();
					repaint();
					break;
				
				case "Search3":
					System.out.println("search3 !!");
					mainPanel.removeAll();
					mainPanel.setLayout(null);
					jTable = null;
					tempPanel = null;
					msgLabel.setText("##다인승(4인승 이상)이면서 저렴한 차량(500000이하)을 검색합니다.");
					
					tempPanel = getSearch3AllTable(condition);
					
					mainPanel.add(tempPanel);
					add(mainPanel);
	
					setVisible(true);
					validate();
					repaint();
					break;
					
				case "Search4":
					System.out.println("search4 !!");
					mainPanel.removeAll();
					mainPanel.setLayout(null);
					jTable = null;
					tempPanel = null;
					msgLabel.setText("##실적이 좋은 정비회사의 정보를 검색합니다.");
					
					tempPanel = getSearch4AllTable(condition);
					
					mainPanel.add(tempPanel);
					add(mainPanel);
	
					setVisible(true);
					validate();
					repaint();
					break;
	
				case "Search5": 
					System.out.println("search5 !!");
					mainPanel.removeAll();
					mainPanel.setLayout(null);
					jTable = null;
					tempPanel = null;
					msgLabel.setText("##파손정비가 진행중인 정비소 정보를 검색합니다.");
					
					tempPanel = getSearch5AllTable(condition);
					
					mainPanel.add(tempPanel);
					add(mainPanel);
	
					setVisible(true);
					validate();
					repaint();
					break;
				//초기화	
				case "initialize": 
					System.out.println("initialize !!");
				
					CM.initialize();
					msgLabel.setText("##초기화 완료. 다시 검색하세요.");
					
					
					break;
					
					
					
	
					
					
					
				//update
				//"Last_Use 업데이트"	
					
				case "Last_Use 업데이트":
					System.out.println("Last_Use 업데이트");
					name = txtUpdateCus1.getText();
					
					while(name.length()<5)
						name = name+" ";// 최대사이즈에 맞게 검색
					
					if(CM.updateLast_Use(name)==true) {
					msgLabel.setText("##Last_Use를 현재 날짜로 업데이트하였습니다. 다시 검색하세요");
					
					}
					else
						msgLabel.setText("##해당하는 이름이 없습니다.");
					
					
					break;
					
				case "캠핑카 등록일 업데이트":
					System.out.println("Last_Date 업데이트");
					name = txtUpdateCus2.getText();
					
					while(name.length()<30)
						name = name+" ";// 최대사이즈에 맞게 검색
					
					if(CM.updateR_date(name)==true) {
					msgLabel.setText("##Last_Date를 현재 날짜로 업데이트하였습니다. 다시 검색하세요");
					
					}
					else
						msgLabel.setText("##해당하는 ID가 없습니다.");
					
					
					break;
					
					
				case "대여담당자 이름 업데이트":
					System.out.println("Last_Date 업데이트");
					String inname = txtUpdateCus3.getText();
					String outname = txtUpdateCus4.getText();
					
					System.out.println("??");
					while(inname.length()<10)
						inname = inname+" ";// 최대사이즈에 맞게 검색
					
					
					
					if(CM.updateRC_empname(inname, outname)==true) {
					msgLabel.setText("##담당자 이름을 변경하였습니다. 다시 검색해주세요");
					
					}
					else
						msgLabel.setText("##해당하는 담당자가 없거나 바꿀이름을 바르게 입력하세요.");
					
					
					break;
					
					
				case "정비담당자 이름 업데이트":
					System.out.println("정비정보 업데이트");
					inname = txtUpdateCus5.getText();
					outname = txtUpdateCus6.getText();
					
					System.out.println("??");
					while(inname.length()<20)
						inname = inname+" ";// 최대사이즈에 맞게 검색
					
					
					
					if(CM.updateR_empname(inname, outname)==true) {
					msgLabel.setText("##담당자 이름을 변경하였습니다. 다시 검색해주세요");
					
					}
					else
						msgLabel.setText("##해당하는 담당자가 없거나 바꿀이름을 바르게 입력하세요.");
					
					
					break;
					
					
					
					
				case "성수기 가격인상":
					System.out.println("가격인상!!");
					
					if(CM.updateRentalFare()==true) {
					msgLabel.setText("##2018/08에 대여한 캠핑카의 청구요금을 10% 증가하였습니다. 다시 검색하세요");
					cnt++;
					
					}
					else
						msgLabel.setText("##요금증가에 해당하는 캠핑카가 없습니다.");
					
					
					break;
					
				case "원상복구":// 성수기 가격인상에 대한 복구
					
				if(cnt == 0)
					msgLabel.setText("##원상복구할 데이터가 없습니다.");
					
				while(cnt > 0) {
					CM.updateBackFare();
					msgLabel.setText("##원상복구하였습니다. 다시 검색하세요");
					cnt--;
				}
				
				
				break;
				
				
				case "정비비용을 납입":
					System.out.println("정비비용 업데이트");
					name = txtUpdateCus7.getText();
					tmp = name;
					
					while(name.length()<30)
						name = name+" ";// 최대사이즈에 맞게 검색
					
					if(CM.updateRI_end(name)==true) {
					msgLabel.setText("##"+tmp+"의 정비비용을 납입하였습니다. 다시 검색하세요");
					
					}
					else
						msgLabel.setText("##해당하는 info가 없거나 이미 납입 완료입니다.");
					
					
					break;
				
			}
		}
	}
	
	
	
}
class Customer{
	 private String liscence_num;
    private String name;

    private String address; 
    private String tel;
    private String email; 
    private int last_date;
    private String inform;
    
	
    public  Customer(String liscence_num,String name, String address, String tel,String email
   		 ,int last_date, String inform) {
   	 
   	 this.liscence_num = liscence_num;
   	 this.name = name;
   	 this.address = address;
   	 this.tel = tel;
   	 this.email = email;
   	 this.last_date = last_date;
   	 this.inform = inform;
	
	}
	
	
	
	public String getliscence_num() {
		return liscence_num;
	}

	public void setliscence_num(String liscence_num) {
		this.liscence_num= liscence_num;
	}

	public String getname() {
		return name;
	}

	public void name(String name) {
		this.name = name;
	}

	public String getaddress() {
		return address;
	}

	public void settel(String tel) {
		this.tel = tel;
	}
	
	public String gettel() {
		return tel;
	}

	
	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	
	public int getlast_date() {
		return last_date;
	}

	public void setJob(int last_date) {
		this.last_date = last_date;
	}

	public String getinform() {
		return inform;
	}

	public void setinform(String inform) {
		this.inform = inform;
	}
	public String toString() {
		return "Cucstomer [=liscence_num"  +  liscence_num +  ", name="  + name +  ", address="  + address + ", tel=" + tel + ", email="
				+ email + ", last_date=" + last_date + ", inform=" + inform + "]";
	}
	
}


class CampingCar{// 캠핑카 클래스
	 private String Car_regist_id;
     private String name;
     private String car_num; 
     private int NOP;//num of passenger
     private String image; 
     private String inform;
     private int cost;
     private String lent_id;
     private String regist_day;
     
	
     public  CampingCar(String Car_regist_id,String name, String car_num, int NOP,String image
    		 ,String inform,int cost, String lent_id, String regist_day) {
    	 
    	 this.Car_regist_id= Car_regist_id;
    	 this.name = name;
    	 this.car_num = car_num;
    	 this.NOP = NOP;//num_of_passenger
    
    	 this.image = image;
    	 this.inform = inform;
    	 this.cost = cost;
    	 this.lent_id= lent_id;
    	 this.regist_day =regist_day;
 	
 	}
 	
 	
 	
 	public String getCar_regist_id() {
 		return Car_regist_id;
 	}


 	public String getname() {
 		return name;
 	}
 	


 	public String getcar_num() {
 		return car_num;
 	}

 	public int getNOP() {
 		return NOP;
 	}

 	public String getimage() {
 		return image;
 	}

 	public String getinform() {
 		return inform;
 	}
 	
 	public int getcost() {
 		return cost;
 	}

 	public String getlent_id() {
 		return lent_id;
 	}

 	public String getregist_day() {
 		return regist_day;
 	}

 	
	
	
}

class Rental_Inform{// 캠핑카 대여정보 클래스
	private String Rental_num;
    private String id;
    private String car_num; 
    private String com_id;//num of passenger
    private Date rent_start;//대여 시작일 
    private int rent_day;// 대여기간
    private int cost;
    private Date rent_end;
    private String bill;
    private int bill_money;
    
	
    public  Rental_Inform(String Rental_num,String id, String car_num, String com_id,Date rent_start
   		 ,int rent_day,int cost, Date rent_end, String bill, int bill_money) {
   	 
   	 this.Rental_num= Rental_num;
   	 this.id = id;
   	 this.car_num = car_num;
   	 this.com_id = com_id;//num_of_passenger
   	 this.rent_start = rent_start;//대여 시작일
   	 this.rent_day = rent_day;//대여기간
   	 this.cost = cost;	//청구요금
   	 this.rent_end = rent_end;
   	 this.bill= bill;
   	 this.bill_money =bill_money;
	
    }
	//필요할때만 set메서드 추가하자
	
	
	public String getRental_num() {
		return Rental_num;
	}


	public String getid() {
		return id;
	}
	


	public String getcar_num() {
		return car_num;
	}

	public String getcom_id() {
		return com_id;
	}

	public Date getrent_start() {
		return rent_start;
	}

	public int getrent_day() {
		return rent_day;
	}
	
	public int getcost() {
		return cost;
	}
	
	public Date getrent_end() {
		return rent_end;
	}

	public String getbill() {
		return bill;
	}

	public int getbill_money() {
		return bill_money;
	}

	
	
	
}

class Rental_Company{// 캠핑카 회사 클래스
	private String id;
    private String name;
    private String address; 
    private String call;//전번
    private String Manager_name;
    private String Manager_mail;
    
	
    public  Rental_Company(String id,String name, String address, String call,String Manager_name
   		 ,String Manager_mail) {
   	 
   	 this.id= id;
   	 this.name = name;
   	 this.address = address;
   	 this.call = call;//num_of_passenger
   	 this.Manager_name = Manager_name;//대여 시작일
   	 this.Manager_mail = Manager_mail;//대여기간
   	
    }
	//필요할때만 set메서드 추가하자
	
	
	public String getid() {
		return id;
	}


	public String getname() {
		return name;
	}
	


	public String getaddress() {
		return address;
	}

	public String getcall() {
		return call;
	}

	public String getManager_name() {
		return Manager_name;
	}

	public String getManager_mail() {
		return Manager_mail;
	}
	
	
	
	
	
}

class Repair_Company{// 캠핑카 정비소 클래스 // 수정필요
	private String id;
    private String name;
    private String address; 
    private String call;//전번
    private String Manager_name;
    private String Manager_mail;
    
	
    public  Repair_Company(String id,String name, String address, String call,String Manager_name
   		 ,String Manager_mail) {
   	 
   	 this.id= id;
   	 this.name = name;
   	 this.address = address;
   	 this.call = call;//num_of_passenger
   	 this.Manager_name = Manager_name;//담당자이름
   	 this.Manager_mail = Manager_mail;
   	
    }
	//필요할때만 set메서드 추가하자
	
	
	public String getid() {
		return id;
	}


	public String getname() {
		return name;
	}
	


	public String getaddress() {
		return address;
	}

	public String getcall() {
		return call;
	}

	public String getManager_name() {
		return Manager_name;
	}

	public String getManager_mail() {
		return Manager_mail;
	}
	
}
class Repair_Inform{// 캠핑카 대여정보 클래스
	private String repair;//고유 정비정보
    private String id;
    private String repair_id; 
    private String company;//num of passenger
    private String liscence_num;
    private String details;// 정비내역
    private String date;
    private int cost;
    private String end; // 납입기한
    private String etc;
    
	
    public  Repair_Inform(String repair,String id, String repair_id, String company,String liscence_num
   		 ,String details,String date, int cost, String end, String etc) {
   	 
   	 this.repair= repair;
   	 this.id = id;
   	 this.repair_id = repair_id;
   	 this.company = company;//num_of_passenger
   	 this.liscence_num = liscence_num;//고객 운전면허증 번호
   	 this.details = details;//정비내역
   	 //수리날짜추가
   	 this.date = date;
   	 this.cost = cost;	//청구요금
   	 this.end= end;
   	 this.etc =etc;
	
    }
	//필요할때만 set메서드 추가하자
	
	
	public String getrepair() {
		return repair;
	}


	public String getid() {
		return id;
	}
	


	public String getrepair_id() {
		return repair_id;
	}

	public String getcompany() {
		return company;
	}

	public String getliscence_num() {
		return liscence_num;
	}

	public String getdetails() {
		return details;
	}
	
	public String getdate() {
		return date;
	}
	
	public int getcost() {
		return cost;
	}
	


	public String getend() {
		return end;
	}

	public String getetc() {
		return etc;
	}

	
	
	
}
class Search4{// search4 arraylist 를 받기 위한 클래스
	private String RI_COMPANY;
	private int count;
	private int sum;
	
	public Search4(String RI_COMPANY, int count, int sum){
		this.RI_COMPANY = RI_COMPANY;
		this.count = count;
		this.sum =sum;
		
	}
	
	public String getcompany() {
		return RI_COMPANY;
	}
	
	public int getcount() {
		return count;
	}
	
	public int getsum() {
		return sum;
	}
	
	
	
	
	
}


public class prog1 {

	 public static void main( String[] args )
	    {
	        JFrame frame = new JFrame("A&P");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //to close normal
	        frame.setResizable(false); // can't control frame size
	        
	        newDBUI primary = new newDBUI();
			frame.getContentPane().add(primary);
			
			frame.pack();
			frame.setVisible(true);
			
	    }//main()
}

