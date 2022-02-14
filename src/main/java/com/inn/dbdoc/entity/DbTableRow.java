package com.inn.dbdoc.entity;

import lombok.Data;

@Data
public class DbTableRow {
	private String fieldName;
	private String fieldType;
	private String isNull;
	private String keyType;
	private String defaultValue;
	private String extra;
	private String comment;

	public DbTableRow(String fieldName, String fieldType, String isNull, String keyType, String defaultValue,
			String extra, String comment) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.isNull = isNull;
		this.keyType = keyType;
		this.defaultValue = defaultValue;
		this.extra = extra;
		this.comment = comment;
	}

	public DbTableRow() {
		super();
	}

}
