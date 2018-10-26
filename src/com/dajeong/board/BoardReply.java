package com.dajeong.board;

public interface BoardReply {
	
	//replyInsert
	public int reply(BoardReplyDTO boardReplyDTO) throws Exception;
	
	//replyUpdate
	public int replyUpdate(BoardReplyDTO boardReplyDTO) throws Exception;

}
