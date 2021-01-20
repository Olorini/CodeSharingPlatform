package com.github.olorini;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CodeModel {

	Long id;
	String code;
	Timestamp createDate;

	public CodeModel() {
		this.code = "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}";
		this.createDate = Timestamp.valueOf(LocalDateTime.now());
	}

	public CodeModel(String code) {
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
}
