/*
 * Copyright 2015 tamacat.org
 * All rights reserved.
 */
package org.tamacat.taglib.input;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import org.tamacat.taglib.Util;

/** 
 * This class implements the &lt;input:checkbox&gt; tag, which presents an
 * &lt;input type="checkbox" ... /&gt; form element.
 */
public class Checkbox extends Input {

	private static final long serialVersionUID = 1L;

	private String label; // name of the checkbox label name
	private String defaultValue;
	protected String[] dValArray; // our multiple default values

	public Checkbox() {
    	setType("checkbox");
    }
    
	@Override
	public void release() {
        super.release();
        setType("checkbox");
        label = null;
        defaultValue = null;
    }

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			StringBuilder html = new StringBuilder();
			html.append("<input ");
			html.append(attributes.createAttributes());
			
			if (defaultValue != null) {
				String value = attributes.get("value");
				if (defaultValue.equals(value)) {
					html.append(" checked=\"checked\"");
				}
			} else if (dValArray != null && dValArray.length >= 1) {
				String value = attributes.get("value");
				for (String dVal : dValArray) {
					if (dVal != null && dVal.length()>=1 && dVal.equals(value)) {
						html.append(" checked=\"checked\"");
						break;
					}
				}
			}
			html.append(" />");
			out.print(html.toString());
			
			//label
            if (label != null) {   
            	String id = attributes.get("id");
                if (id == null) {
                	out.print("<label class=\"control-label\">");
                } else if (label != null && id != null) {
                	out.print("<label for=\"" + Util.escape(id) + "\" class=\"control-label\">");
                }
            	out.print(Util.escape(label) + "</label>");
            }
		} catch (Exception ex) {
			throw new JspTagException(ex.getMessage());
		} finally {
			release();
		}
		return SKIP_BODY;
	}
	
    public void setLabel(String label) {
		this.label = label;
	}
	
	public void setDefault(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	/**
	 * @since 1.5
	 * @param defaultValues
	 */
	public void setDefaults(String... defaultValues) {
		this.dValArray = defaultValues;
	}
}