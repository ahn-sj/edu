package com.newlec.web.entity;

import java.util.Date;

public class NoticeView extends Notice {
	
	private int cmtCount;
	
	
	
	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	
	public NoticeView(int id, String title, String writerId, Date regDate, String files, int hit, boolean pub, int cmtCount) {
		super(id, title, writerId, regDate, writerId, files, hit, pub);
		this.cmtCount = cmtCount;
	}

}
