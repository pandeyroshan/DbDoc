package com.inn.dbdoc.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inn.dbdoc.entity.DbTable;
import com.inn.dbdoc.entity.DbTableRow;

@Service
public class TableService {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	public DbTable getDbTableDetail(String tableName) {
		List<Object[]> tableInfo = null;
		List<DbTableRow> dbTableRows = new ArrayList<>();

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query showFullColumnQuery = entityManager.createNativeQuery("SHOW FULL COLUMNS FROM " + tableName);
		tableInfo = showFullColumnQuery.getResultList();
		
		for (Object[] o : tableInfo) {
			DbTableRow dbTableRow = new DbTableRow();
			dbTableRow.setFieldName(String.valueOf(o[0]));
			dbTableRow.setFieldType(String.valueOf(o[1]));
			dbTableRow.setIsNull(String.valueOf(o[3]));
			dbTableRow.setKeyType(String.valueOf(o[4]));
			dbTableRow.setDefaultValue(String.valueOf(o[5]));
			dbTableRow.setExtra(String.valueOf(o[6]));
			dbTableRow.setComment(String.valueOf(o[8]));
			
			dbTableRows.add(dbTableRow);
		}
		
		return new DbTable(tableName, dbTableRows);
	}

	public List<String> getAllDbTable() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query showTablesQuery = entityManager.createNativeQuery("SHOW TABLES");

		List<String> listOfTables = showTablesQuery.getResultList();

		return listOfTables;
	}
}
