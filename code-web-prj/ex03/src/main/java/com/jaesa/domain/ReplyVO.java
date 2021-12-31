package com.jaesa.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private Long rno; // 댓글의 고유한 번호
	private Long bno; // (FK) TBL_BOARD.BNO
	
	private String reply;
	private String replyer;
	
	private Date replyDate;
	private Date updateDate;
}
