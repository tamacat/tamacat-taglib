/*
 * Copyright (c) 2014 tamacat.org
 * All rights reserved.
 */
package org.tamacat.taglib;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Pagination extends TagSupport {

	private static final long serialVersionUID = 1L;

	protected Map<String, Object> params;
	protected int start = 1;
	protected int max = 10;
	protected int maxPage = 10;
	protected int hit = 0;
	protected Collection<?> collection;
	protected String handler;
	protected String href;
	protected String target;
	protected String type;

	@Override
	public void release() {
		super.release();
		params = null;
		start = 1;
		max = 10;
		maxPage = 10;
		hit = 0;
		collection = null;
		handler = null;
		href = null;
		target = null;
		type = null;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();

			if (hit > 0) {
				out.print(getPagination());
			}
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	protected String getPagination() {
		StringBuilder html = new StringBuilder();
		int size = 0;
		if (collection != null) {
			size = collection.size();
		}
		if (hit > 0 && hit > size) {
			size = hit;
		}

		int pages = (int) Math.ceil((double) size / (double) max);

		StringBuilder param = new StringBuilder();
		if (params != null) {
			for (String p : params.keySet()) {
				if (param.length() > 0) {
					param.append("&");
				}
				param.append(p + "=" + Util.encode(params.get(p)));
			}
		}
		// html.append("<li><a href=\"#1\">&laquo;</a></li>");
		String active = " class=\"active\"";
		for (int i = 1; i <= pages; i++) {
			int current = i;
			if (current > maxPage)
				break;

			int next = (current - 1) * max + 1; // next start param

			String style = "";
			if (start == next) {
				style = active;
			}
			// System.out.println("start="+start+", current="+current+", next="+next+", pages="+pages+", maxPage="+maxPage);
			html.append("<li" + style);
			if (id != null) {
				html.append(" id=\"" + Util.escape(id) + next + "\"");
			}

			html.append("><a");

			if (href != null) {
				String url = Util.escape(href) + (next);
				if (param.length() > 0) {
					url = url + "&" + param.toString();
				}
				html.append(" href=\"" + url + "\"");
			} else {
				html.append(" href=\"#" + next + "\"");
			}

			if (type != null) {
				html.append(" class=\"" + Util.escape(type) + "\"");
			}

			if (target != null) {
				html.append(" target=\"" + Util.escape(target) + "\"");
			}

			if (handler != null) {
				html.append(" onclick=\"" + Util.escape(handler) + "(" + (next) + ");\"");
			}
			html.append("><span>" + current + "</span></a></li>\n");
		}
		// html.append("<li class=\"disabled\"><a href=\"javascript:void(0)\">&raquo;</a></li>\n");
		return html.toString();
	}

	public void setCollection(Collection<?> collection) {
		this.collection = collection;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setType(String type) {
		this.type = type;
	}
}
