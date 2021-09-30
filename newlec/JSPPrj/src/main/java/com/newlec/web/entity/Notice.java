package com.newlec.web.entity;

import java.util.Date;

public class Notice {
	int id;
	String title;
    String writerId;
    Date regDate;
    String content;
    String files;
    int hit;
    boolean pub;
    
    public Notice() {
		// TODO Auto-generated constructor stub
	}
    
    

	public Notice(int id, String title, String writerId, Date regDate, String content, String files, int hit,
			boolean pub) {
		this.id = id;
		this.title = title;
		this.writerId = writerId;
		this.regDate = regDate;
		this.content = content;
		this.files = files;
		this.hit = hit;
		this.pub = pub;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public boolean getPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writerId=" + writerId + ", regDate=" + regDate
				+ ", content=" + content + ", files=" + files + ", hit=" + hit + ", pub=" + pub + "]";
	}



	
}
