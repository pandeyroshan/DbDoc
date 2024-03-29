package com.inn.dbdoc.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface ITableController {

	@GetMapping(value = "/get-database-design", produces = { "application/octet-stream" })
	public ResponseEntity<InputStreamResource> getDatabaseDesign();
}
