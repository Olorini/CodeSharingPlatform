package com.github.olorini.db;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "snippets")
public class Snippet {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column
	String code;

	@Column
	Timestamp createDate;

	public Snippet() {
		this.code = "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}";
		this.createDate = Timestamp.valueOf(LocalDateTime.now());
	}

	public Snippet(String code) {
		this.code = code;
		this.createDate = Timestamp.valueOf(LocalDateTime.now());
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
