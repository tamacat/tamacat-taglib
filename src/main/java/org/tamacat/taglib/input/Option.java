/*
 * Copyright (c) 2015 tamacat.org
 * All rights reserved.
 */
package org.tamacat.taglib.input;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.tamacat.taglib.Util;

/**
 * 
 * This class implements the &lt;input:option&gt; tag, which presents an
 * &lt;option&gt; form element.
 */
public class Option extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private String value;
    private Map<String,String> attributes;
    private String title;

    @Override
	public void release() {
        super.release();
        value = null;
        attributes = null;
        title = null;
    }

    public int doStartTag() throws JspException {
    	if (getBodyContent() != null) {
            getBodyContent().clearBody();
        }
        return EVAL_BODY_BUFFERED;
    }

    public int doEndTag() throws JspException {
        try {
            String content = getBodyContent() != null ?
            		getBodyContent().getString() : null;

            JspWriter out = pageContext.getOut();

            String value = this.value;
            if (value == null && content != null) {
                value = content.trim();
            }

            out.print("<option");
            if (value != null) {
                out.print(" value=\"" + Util.escape(value) + "\"");
            }

            if (id != null) {
                out.print(" id=\"" + Util.escape(id) + "\"");
            }
            if (title != null) {
                out.print(" title=\"" + Util.escape(title) + "\"");
            }

            printAttributes(out, attributes);
            if (testAndRemoveChosen(value)) {
                out.print(" selected=\"selected\"");
            }

            out.print(">");
            if (content != null) {
                out.print(content);
            }
            out.print("</option>");
        } catch (Exception ex) {
            throw new JspTagException(ex.getMessage());
        } finally {
        	release();
        }
        return EVAL_PAGE;
    }

    protected boolean testAndRemoveChosen(String value) throws JspException {
        Select selectTag = (Select) findAncestorWithClass(this, Select.class);
        if (selectTag != null) {
            Map<String,String> chosen = selectTag.getChosen();
            if (value != null && chosen != null && chosen.containsKey(value)) {
                if (!selectTag.isMultiple()) {
                    chosen.remove(value);
                }
                return true;
            } else {
                return false;
            }
        } else {
            throw new JspTagException("option tag used outside a select tag");
        }
    }

	public static void printAttributes(JspWriter out, Map<String, String> attributes) throws JspTagException, IOException {
		if (attributes != null) {
			for (String key : attributes.keySet()) {
				String value = attributes.get(key);
				if (key.equals("name") || key.equals("value") || key.equals("type") || key.equals("checked")) {
					throw new JspTagException("Illegal key '" + key + "' found in attributes Map");
				}
				if (value == null) {
					value = key;
				}
				out.print(Util.escape(key) + "=\"" + Util.escape(value) + "\" ");
			}
		}
	}
	
	public void setClass(String className) {
		attributes.put("class", className);
	}
	
    public void setValue(String x) {
        value = x;
    }

    public void setAttributes(Map<String, String> x) {
        attributes = x;
    }

	public void setTitle(String title) {
		this.title = title;
	}
}