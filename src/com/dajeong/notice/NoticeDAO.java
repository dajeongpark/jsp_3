package com.dajeong.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dajeong.board.BoardDAO;
import com.dajeong.board.BoardDTO;
import com.dajeong.page.RowNumber;
import com.dajeong.util.DBConnector;
import com.oreilly.servlet.MultipartRequest;

public class NoticeDAO implements BoardDAO {

	@Override
	public List<BoardDTO> selectList(RowNumber rowNumber) throws Exception {
		Connection con = DBConnector.getConnect();
		//rownum을 하나의 컬럼으로 만들어서 컬럼 갯수 세기
		//from 뒤에 띄어쓰기 유의
		String sql = "select * from "
				+ "(select rownum R, N.* from "
				+ "(select num,title,writer,reg_date,hit from notice "
				+ "where "+rowNumber.getSearch().getKind()+" like ? "	//테이블명은 ? 안됨
				+ "order by num desc) N) "
				+ "where R between ? and ?";
			
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+rowNumber.getSearch().getSearch()+"%");
		st.setInt(2, rowNumber.getStartRow());
		st.setInt(3, rowNumber.getLastRow());
			
		ResultSet rs = st.executeQuery();
		List<BoardDTO> ar = new ArrayList<>();
		NoticeDTO ndto = null;
			
		while(rs.next()) {
			ndto = new NoticeDTO();
			ndto.setNum(rs.getInt("num"));
			ndto.setTitle(rs.getString("title"));
			ndto.setWriter(rs.getString("writer"));
			ndto.setReg_date(rs.getDate("reg_date"));
			ndto.setHit(rs.getInt("hit"));
			ar.add(ndto);
		}
			
		DBConnector.disConnect(rs, st, con);
		return ar;
			
	}


	@Override
	public BoardDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select * from notice where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		NoticeDTO ndto = new NoticeDTO();
		if(rs.next()) {
			ndto.setNum(rs.getInt("num"));
			ndto.setTitle(rs.getString("title"));
			ndto.setContents(rs.getString("contents"));
			ndto.setWriter(rs.getString("writer"));
			ndto.setReg_date(rs.getDate("reg_date"));
			ndto.setHit(rs.getInt("hit"));
		}
		
		DBConnector.disConnect(rs, st, con);
		
		return ndto;
	}
	
	//seq
	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select notice_seq.nextval from dual";
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		DBConnector.disConnect(rs, st, con);
		
		return num;
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into notice values(?,?,?,?,sysdate,0)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, boardDTO.getNum());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setString(4, boardDTO.getWriter());
	
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
	//MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		return 0;
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(String kind, String search) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from notice "
				+ "where "+kind+" like ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search+"%");
		
		ResultSet rs = st.executeQuery();
		rs.next();
		int result = rs.getInt(1);
		
		DBConnector.disConnect(rs, st, con);
		
		return result;
	}

}
