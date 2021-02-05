package com.github.olorini.db;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "snippets")
@Table(name = "snippets")
public class Snippet {

	@Id
	@Column
	private String id;

	@Column
	private String code;

	@Column
	private Timestamp createDate;

	@Column
	private Timestamp timeOfDisable;

	@Column
	private Integer viewsCount;

	public Snippet() { }

	public Snippet(String code, int time, int views) {
		this.id = UUID.randomUUID().toString();
		this.code = code;
		this.createDate = Timestamp.valueOf(LocalDateTime.now());
		this.viewsCount = (views > 0) ? views : null;
		this.timeOfDisable = (time > 0) ? Timestamp.from(createDate.toInstant().plusSeconds(time)) : null;
	}

	public String getCode() {
		return code;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getTimeOfDisable() {
		return timeOfDisable;
	}

	public void setTimeOfDisable(Timestamp timeOfDisable) {
		this.timeOfDisable = timeOfDisable;
	}

	public Integer getViewsCount() {
		return viewsCount;
	}

	public void setViewsCount(Integer views) {
		this.viewsCount = views;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Snippet snippet = (Snippet) o;
		return Objects.equals(getCode(), snippet.getCode()) &&
				getCreateDate().equals(snippet.getCreateDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCode(), getCreateDate());
	}

	public void decrementViews() {
		this.viewsCount--;
	}
}
