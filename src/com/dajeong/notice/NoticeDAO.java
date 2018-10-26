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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
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
