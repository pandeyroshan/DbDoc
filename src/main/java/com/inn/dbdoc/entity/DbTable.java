package com.inn.dbdoc.entity;

import java.util.List;

import lombok.Data;

@Data
public class DbTable {
	private String tableName;
	private List<DbTableRow> tableRows;

	public DbTable(String tableName, List<DbTableRow> tableRows) {
		super();
		this.tableName = tableName;
		this.tableRows = tableRows;
	}

	public DbTable() {
		super();
	}

}
