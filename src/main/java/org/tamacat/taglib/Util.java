/*
 * Copyright 2015 tamacat.org
 * All rights reserved.
 */
package org.tamacat.taglib;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

	static final char[] SPECIAL_CHARACTERS_HTML = new char[]{ '&', '<', '>', '"', '\'' };
	static final String[] SPECIAL_STRING_HTML = new String[]{ "&amp;", "&lt;", "&gt;", "&#034;", "&#039;" };
	static final Locale currentLocale = Locale.getDefault();

	private static final String EMPTY = "";

	public static boolean isNotEmpty(Object value) {
		return value != null && !EMPTY.equals(value);
	}

	public static boolean isEmpty(Object value) {
		return value == null || EMPTY.equals(value);
	}

	/**
	 * & -> &amp;
     * < -> &lt;
     * > -> &gt;
     * " -> &#034;
     * ' -> &#039;
	 */
	public static String escape(String x) {
		if (x == null || x.length() == 0) {
			return "";
		} else {
			StringBuilder result = new StringBuilder();
			LOOP: for (int i=0; i<x.length(); i++) {
				char c = x.charAt(i);
				for (int j=0; j<SPECIAL_CHARACTERS_HTML.length; j++) {
					if (c == SPECIAL_CHARACTERS_HTML[j]) {
						result.append(SPECIAL_STRING_HTML[j]);
						continue LOOP;
					}
				}
				result.append(c);
			}
			return result.toString();
//			return x.replace("&", "&amp;")
//					.replace("\"", "&#034;") //&quot;
//					.replace("<", "&lt;")
//					.replace(">", "&gt;")
//					.replace("'", "&#039;");
		}
	}
	
	public static String encode(Object x) {
		if (x != null) {
			if (x instanceof CharSequence) {
				try {
					return URLEncoder.encode(x.toString(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
				}
			}
			return x.toString();
		} else {
			return "";
		}
	}
	
	public static String timestamp(String pattern) {
		if (pattern == null) pattern = "yyyy-MM-dd HH:mm:ss";
		return date(new Date(), pattern);
	}
	
	public static String date(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
			return formatter.format(date);
		} else {
			return "";
		}
	}
	
	public static String format(String value, String format) {
		String convValue = null;
		try {
			DecimalFormat nf = new DecimalFormat(format); 
			convValue = nf.format(Double.parseDouble(value));
		} catch (IllegalArgumentException e) {
			return value;
		}
		return convValue;
	}

	public static String cut(String str, int length) {
		if (str != null && str.length() > length && length >= 0) {
			return str.substring(0, length) + "...";
		} else {
			return str;
		}
	}
}
