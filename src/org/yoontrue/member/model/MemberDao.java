package org.yoontrue.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.yoontrue.util.JdbcUtil;

public class MemberDao {
   // Mybatis ORM 프레임워크로 바뀐다.
   static final String SELECT = "SELECT * FROM MEMBER";
   static final String INSERT = "INSERT INTO MEMBER(CODE, NAME, ID, PWD, AGE) VALUES(?,?,?,?,?)";
   
   PreparedStatement pstmt = null;
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;

   static MemberDao dao = new MemberDao();
   
   public static void main(String[] args) {
	      System.out.println("Delete 테스트");
	      dao.delete(new MemberDto("1004", null, null, null, 0));
   }

   public List<MemberDto> selectAll() {
      List<MemberDto> list = new ArrayList<MemberDto>();
      conn = JdbcUtil.getConnection();
      // Statement 객체를 이용해서 DB에 SQL문을 전달한다.
      try {
         stmt = conn.createStatement();
         // SQL 쿼리를 실행하고 그 결과를 ResultSet 객체로 돌려 받는다.
         rs = stmt.executeQuery(SELECT);
         while(rs.next()) {
            String code = rs.getString(1);
            String name = rs.getString(2);
            String id = rs.getString(3);
            String pwd = rs.getString(4);
            int age = rs.getInt(5);
            
            MemberDto member = new MemberDto(code, name, id, pwd, age);
            list.add(member);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JdbcUtil.close(rs);
         JdbcUtil.close(stmt);
         JdbcUtil.close(conn);
      }
      
      return list;
   } // end of selectAll()
   
   private void insert(MemberDto dto) {
      conn = JdbcUtil.getConnection();
      
      try {
         pstmt =conn.prepareStatement(INSERT);
         pstmt.setString(1, dto.getCode());
         pstmt.setString(2, dto.getName());
         pstmt.setString(3, dto.getId());
         pstmt.setString(4, dto.getPwd());
         pstmt.setInt(5, dto.getAge());
         int cnt = pstmt.executeUpdate(); // 처리 한 갯수가 리턴된다.
         if(cnt>0) {
            System.out.println("입력 성공!");
            conn.commit();
         } else {
            System.out.println("입력 실패!");
            conn.rollback();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JdbcUtil.close(rs, pstmt, conn);
      }
   }
   
   private void update(MemberDto member) {
      String UPDATE = "UPDATE MEMBER SET NAME=?, ID=?, PWD=?, AGE=? WHERE CODE=?";
      conn = JdbcUtil.getConnection();
      try {
         pstmt = conn.prepareStatement(UPDATE);
         pstmt.setString(1, member.getName());
         pstmt.setString(2, member.getId());
         pstmt.setString(3, member.getPwd());
         pstmt.setInt(4, member.getAge());
         pstmt.setString(5, member.getCode());
         int cnt = pstmt.executeUpdate();
         if(cnt>0) {
            System.out.println("수정 완료!");
            conn.commit();
         } else {
            System.out.println("수정 실패!");
            conn.rollback();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JdbcUtil.close(rs, pstmt, conn);
      }
   }
   
   private void delete(MemberDto member) {
      String DELETE = "DELETE FROM MEMBER WHERE CODE = ?";
      
      conn = JdbcUtil.getConnection();
      try {
         pstmt = conn.prepareStatement(DELETE);
         pstmt.setString(1, member.getCode());
         int cnt = pstmt.executeUpdate();
         if(cnt>0) {
            System.out.println("삭제완료!");
            conn.commit();
         } else {
            System.out.println("삭제실패!");
            conn.rollback();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JdbcUtil.close(rs, pstmt, conn);
      }
   }
    
   public static void UpdateEx(String[] args) {
      System.out.println("Update 테스트");
      
      //dao.insert(new MemberDto("1004","홍길동","GILDONG","4444",10));
      dao.update(new MemberDto("1004","홍길동2","GILDONG2","4442",12));
   }

   public static void SelectAndInsertEx(String[] args) {
      dao.insert(new MemberDto("1004","홍길동","GILDONG","4444",10));
      
      List<MemberDto> list = dao.selectAll();
      for(MemberDto member : list) {
         System.out.println(member);
      }
   }
}