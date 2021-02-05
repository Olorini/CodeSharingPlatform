package com.github.olorini.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.olorini.db.Snippet;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodeResponse {

	private String code;
	private Timestamp date;
	private long time;
	private int views;

	public CodeResponse() { }

	public CodeResponse(Snippet snippet) {
		this.time = (snippet.getTimeOfDisable() != null)
				? (snippet.getTimeOfDisable().getTime()  - now()) / 1000
				: 0;
		this.views = (snippet.getViewsCount()!= null && snippet.getViewsCount() > 0)
				? snippet.getViewsCount()
				: 0;
		this.code = snippet.getCode();
		this.date = snippet.getCreateDate();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss", timezone="Europe/Moscow")
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	private long now() {
		return Timestamp.valueOf(LocalDateTime.now()).getTime();
	}
}
