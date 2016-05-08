/*
 * Copyright (c) 2015 tamacat.org
 * All rights reserved.
 */
package org.tamacat.taglib.input;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Input extends TagSupport {

	private static final long serialVersionUID = 1L;
	protected Attributes attributes = new Attributes();
	
	@Override
	public void release() {
		super.release();
		attributes.release();;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			StringBuilder html = new StringBuilder();
			html.append("<input ");
			html.append(attributes.createAttributes());
			html.append(" />");
			out.print(html.toString());
		} catch (Exception ex) {
			throw new JspTagException(ex.getMessage());
		} finally {
			release();
		}
		return SKIP_BODY;
	}
	
	public void setType(String type) {
		attributes.setType(type);
	}
	
	public void setName(String name) {
		attributes.setName(name);
	}

	public void setValue(String value) {
		attributes.setValue(value);
	}
	
	public void setClass(String className) {
		attributes.setClass(className);
	}
	
	public void setStyle(String style) {
		attributes.setStyle(style);
	}

	public void setOnclick(String function) {
		attributes.setOnclick(function);
	}

	public void setOnchange(String function) {
		attributes.setOnchange(function);
	}
	
	public void setOnsubmit(String function) {
		attributes.setOnsubmit(function);
	}
	
	public void setOnfocus(String function) {
		attributes.setOnfocus(function);
	}
	
	public void setOnblur(String function) {
		attributes.setOnblur(function);
	}
	
	public void setOnkeyup(String function) {
		attributes.setOnkeyup(function);
	}
	
	public void setSelect(String function) {
		attributes.setSelect(function);
	}
	
	public void setId(String id) {
		attributes.setId(id);
	}

	public void setCssStyle(String style) {
		attributes.setCssStyle(style);
	}
	
	public void setWidth(String width) {
		attributes.setWidth(width);
	}
	
	public void setAccesskey(String accesskey) {
		attributes.setAccesskey(accesskey);
	}

	public void setTabindex(String tabindex) {
		attributes.setTabindex(tabindex);
	}

	public void setDisabled(String disabled) {
		attributes.setDisabled(disabled);
	}

	public void setTitle(String title) {
		attributes.setTitle(title);;
	}
	
	public void setPlaceholder(String placeholder) {
		attributes.setPlaceholder(placeholder);
	}

	public void setSize(String size) {
		attributes.setSize(size);
	}

	public void setMaxlength(String maxlength) {
		attributes.setMaxlength(maxlength);
	}

	public void setMin(String min) {
		attributes.setMin(min);
	}

	public void setMax(String max) {
		attributes.setMax(max);
	}

	public void setStep(String step) {
		attributes.setStep(step);
	}
	
	public void setPattern(String regexp) {
		attributes.setPattern(regexp);
	}
	
	public void setAutocomplete(String onoff) {
		attributes.setAutocomplete(onoff);
	}
	
	public void setReadonly(String readonly) {
		attributes.setReadonly(readonly);
	}
	
	public void setForm(String id) {
		attributes.setForm(id);
	}
	
	public void setRequired(String required) {
		attributes.setRequired(required);
	}
	
	public void setAutofocus(String autofocus) {
		attributes.setAutofocus(autofocus);
	}
	
	public void setList(String list) {
		attributes.setList(list);
	}
}