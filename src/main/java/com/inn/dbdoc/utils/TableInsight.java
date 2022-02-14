package com.inn.dbdoc.utils;

import com.inn.dbdoc.entity.DbTable;
import com.inn.dbdoc.entity.DbTableRow;
import com.inn.dbdoc.enums.Size;

public class TableInsight {
	public static Size getTableColumnsMaxSize(DbTable dbTable) {

		Integer maxColumnSize = -1;

		for (DbTableRow dbTableRow : dbTable.getTableRows()) {
			if (dbTableRow.getFieldName().length() > maxColumnSize) {
				maxColumnSize = dbTableRow.getFieldName().length();
			}
		}

		if (Range.outOfRange.isValidValue(maxColumnSize)) {
			return Size.OUT_OF_RANGE;
		} else if (Range.extraLargeRange.isValidValue(maxColumnSize)) {
			return Size.EXTRA_LARGE;
		} else if (Range.largeRange.isValidValue(maxColumnSize)) {
			return Size.LARGE;
		} else if (Range.mediumRange.isValidValue(maxColumnSize)) {
			return Size.MEDIUM;
		} else if (Range.smallRange.isValidValue(maxColumnSize)) {
			return Size.SMALL;
		} else if (Range.extraSmallRange.isValidValue(maxColumnSize)) {
			return Size.EXTRA_SMALL;
		}

		return Size.OUT_OF_RANGE;
	}
}
