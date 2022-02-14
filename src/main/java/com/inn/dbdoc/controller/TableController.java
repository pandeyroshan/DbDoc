package com.inn.dbdoc.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.dbdoc.entity.DbTable;
import com.inn.dbdoc.service.TableService;
import com.inn.dbdoc.utils.PdfExporter;

@RestController
public class TableController implements ITableController {

	@Autowired
	private TableService tableService;

	@Override
	public ResponseEntity<InputStreamResource> getDatabaseDesign() {
		List<String> allDbTableNames = tableService.getAllDbTable();

		List<DbTable> allDbTables = new ArrayList<>();

		for (String dbTableName : allDbTableNames) {
			DbTable dbTableDetail = tableService.getDbTableDetail(dbTableName);
			allDbTables.add(dbTableDetail);
		}

		ByteArrayInputStream bis = PdfExporter.tableDescReport(allDbTables);
		var headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=dbreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
