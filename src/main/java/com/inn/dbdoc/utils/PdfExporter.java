package com.inn.dbdoc.utils;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.inn.dbdoc.entity.DbTable;
import com.inn.dbdoc.entity.DbTableRow;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfExporter {
	static ByteArrayOutputStream out = new ByteArrayOutputStream();
	static ByteArrayInputStream byteArrayInputStream;
	static Document document = new Document();

	public static ByteArrayInputStream tableDescReport(List<DbTable> allDbTables) {
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			writeValuesInPdf(allDbTables);
			document.close();
		} catch (DocumentException ex) {
			ex.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	public static void writeValuesInPdf(List<DbTable> allDbTables) throws DocumentException {
		for (DbTable dbTable : allDbTables) {
			PdfPTable table = new PdfPTable(7);

			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			boldFont.setSize(7.3f);

			Font normalFont = FontFactory.getFont(FontFactory.HELVETICA);
			normalFont.setSize(7.3f);

			Paragraph lineBreak = new Paragraph();
			lineBreak.add("\n");

			Phrase tableName = new Phrase("Table Name: " + dbTable.getTableName() + "\n", boldFont);
			Phrase tableDesc = new Phrase(
					"Desc: This table is being stored to store the custom response codes that microservice can generate.",
					normalFont);

			document.add(tableName);
			document.add(tableDesc);
			document.add(lineBreak);

			writeHeader(table);

			for (DbTableRow dbTableRow : dbTable.getTableRows()) {
				writeTableData(dbTableRow, table);
			}
			document.add(table);
			document.add(lineBreak);
		}
	}

	public static void writeHeader(PdfPTable table) throws DocumentException {
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 18, 5, 3, 2, 3, 6, 9 });

		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		headFont.setSize(7.1f);

		PdfPCell hcell;
		hcell = new PdfPCell(new Phrase("Field", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Type", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Null", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Key", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Default", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Extra", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		hcell = new PdfPCell(new Phrase("Comments", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

	}

	public static void writeTableData(DbTableRow dbTableRow, PdfPTable table) throws DocumentException {
		PdfPCell cell;

		Font dataFont = FontFactory.getFont(FontFactory.HELVETICA);
		dataFont.setSize(7.0f);

		cell = new PdfPCell(new Phrase(dbTableRow.getFieldName(), dataFont));
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(dbTableRow.getFieldType(), dataFont));
		cell.setPaddingLeft(5);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(dbTableRow.getIsNull(), dataFont));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingRight(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(dbTableRow.getKeyType(), dataFont));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingRight(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(dbTableRow.getDefaultValue(), dataFont));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingRight(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(dbTableRow.getExtra(), dataFont));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingRight(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(dbTableRow.getComment(), dataFont));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingRight(5);
		table.addCell(cell);

	}
}