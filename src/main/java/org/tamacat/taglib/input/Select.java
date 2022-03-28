/*
 * Copyright 2015 tamacat.org
 * All rights reserved.
 */
package org.tamacat.taglib.input;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.tamacat.taglib.Util;

public class Select extends TagSupport {

	private static final long serialVersionUID = 1L;

	protected String dVal; // default value if none is found
	protected String[] dValArray; // our multiple default values
	protected Map<?, ?> options;
	protected boolean multiple;
	protected List<String> optionLabels; // a list of option labels
	protected List<String> optionValues; // a list of option values
	protected HashMap<String, String> chosen;
	protected Attributes attributes = new Attributes();

	public void release() {
		super.release();
		dVal = null;
		dValArray = null;
		attributes.release();;
		options = null;
		multiple = false;
		optionLabels = null;
		optionValues = null;
		chosen = null;
	}

	public int doStartTag() throws JspException {
		try {
			ServletRequest req = pageContext.getRequest();
			JspWriter out = pageContext.getOut();

			out.print("<select");

			out.print(attributes.createAttributes());
			
			if (multiple) {
				out.print(" multiple=\"multiple\"");
			}

			out.println(">");

			String[] selected;
			if (dValArray != null && dVal != null) {
				selected = new String[dValArray.length + 1];
				selected[0] = dVal;
				System.arraycopy(dValArray, 0, selected, 1, dValArray.length);
			} else if (dValArray != null) {
				selected = dValArray;
			} else if (dVal != null) {
				selected = new String[] { dVal };
			} else {
				selected = req.getParameterValues(attributes.get("name"));
			}

			if (selected != null && selected.length > 1 && !multiple) {
				selected = null;
			}
			chosen = new HashMap<String, String>();
			if (selected != null) {
				for (int i = 0; i < selected.length; i++) {
					if (selected[i] != null) {
						chosen.put(selected[i], null);
					}
				}
			}

			if (optionLabels != null) {
				int n = optionLabels.size();
				for (int i = 0; i < n; i++) {
					Object oLabel = optionLabels.get(i);
					Object oVal = (options != null ? options.get(oLabel) : (optionValues != null ? optionValues.get(i)
							: oLabel));
					outputOption(out, oLabel, oVal);
				}
			} else if (options != null) {
				Iterator<?> i = options.keySet().iterator();
				while (i.hasNext()) {
					Object oLabel = i.next();
					Object oVal = options.get(oLabel);
					outputOption(out, oLabel, oVal);
				}
			}

		} catch (Exception ex) {
			throw new JspTagException(ex.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}
	
	protected void outputOption(JspWriter out, Object oLabel, Object oVal) throws java.io.IOException {
		String label = oLabel.toString();
		String value = (oVal != null ? oVal.toString() : null);

		out.print("<option");

		if (value != null) {
			out.print(" value=\"" + Util.escape(value) + "\"");
		}

		if (value == null) {
			value = label;
		}
		if (chosen.containsKey(value)) {
			if (!multiple) {
				chosen.remove(value);
			}
			out.print(" selected=\"selected\"");
		}
		out.print(">");
		out.print(Util.escape(label));
		out.println("</option>");
	}

	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.print("</select>");
		} catch (Exception ex) {
			throw new JspTagException(ex.getMessage());
		} finally {
			release();
		}
		return EVAL_PAGE;
	}

	public void setMultiple(boolean x) {
		multiple = x;
	}

	public void setDefault(String x) {
		dVal = x;
	}

	public void setDefaults(String... x) {
		dValArray = x;
	}

	public void setDefaultsMap(Map<String, String> x) {
		dValArray = new String[x.size()];
		Iterator<?> it = x.keySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			dValArray[i++] = it.next().toString();
		}
	}

	public void setDefaultsList(Collection<String> c) {
		dValArray = new String[c.size()];
		Iterator<String> it = c.iterator();
		int i = 0;
		while (it.hasNext()) {
			dValArray[i++] = it.next().toString();
		}
	}

	public void setOptions(Map<?, ?> x) {
		options = x;
	}

	public void setOptionLabels(List<String> x) {
		optionLabels = x;
	}

	public void setOptionValues(List<String> x) {
		optionValues = x;
	}
	
	protected HashMap<String, String> getChosen() {
		return chosen;
	}

	protected boolean isMultiple() {
		return multiple;
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