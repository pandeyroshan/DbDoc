package com.inn.dbdoc.utils;

import java.time.temporal.ValueRange;

public class Range {
	public static ValueRange extraSmallRange = ValueRange.of(0, 11);
	public static ValueRange smallRange = ValueRange.of(12, 22);
	public static ValueRange mediumRange = ValueRange.of(23, 33);
	public static ValueRange largeRange = ValueRange.of(34, 44);
	public static ValueRange extraLargeRange = ValueRange.of(45, 55);
	public static ValueRange outOfRange = ValueRange.of(56, 100);
}
