package org.tamacat.taglib.input;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.tamacat.taglib.Util;

public class Attributes implements Serializable {
	private static final long serialVersionUID = 1L;

	protected static final String NO_VALUE = "__NO_VALUE__";

	protected Map<String, String> attributes = new LinkedHashMap<>();
	protected String format;
	
	public void release() {
		attributes = new LinkedHashMap<>();
		format = null;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	public String get(String key) {
		return attributes.get(key);
	}
	
	protected String createAttributes() throws IOException {
		StringBuilder html = new StringBuilder();
		if (Util.isNotEmpty(format)) {
			String val = attributes.get("value");
    		if (Util.isNotEmpty(val)) {
    			val = Util.format(val, format);
    			attributes.put("value", val);
    		}
		}
		for (String key : attributes.keySet()) {
			String value = attributes.get(key);
			if (value == NO_VALUE) {
				html.append(" " + key);
			} else {
				html.append(" " + key + "=\"" + Util.escape(value) + "\"");
			}
		}
		return html.toString();
	}
	
	public void setName(String name) {
		attributes.put("name", name);
	}

	public void setValue(String value) {
		attributes.put("value", value);
	}
	
	public void setClass(String className) {
		attributes.put("class", className);
	}
	
	public void setStyle(String style) {
		attributes.put("class", style);
	}

	public void setOnclick(String function) {
		attributes.put("onclick", function);
	}

	public void setOnchange(String function) {
		attributes.put("onchange", function);
	}
	
	public void setOnsubmit(String function) {
		attributes.put("onsubmit", function);
	}
	
	public void setOnfocus(String function) {
		attributes.put("onfocus", function);
	}
	
	public void setOnblur(String function) {
		attributes.put("onblur", function);
	}
	
	public void setOnkeyup(String function) {
		attributes.put("onkeyup", function);
	}
	
	public void setSelect(String function) {
		attributes.put("onselect", function);
	}
	
	public void setId(String id) {
		attributes.put("id", id);
	}

	public void setCssStyle(String style) {
		attributes.put("style", style);
	}
	
	public void setWidth(String width) {
		attributes.put("style", "width:"+width);
	}
	
	public void setAccesskey(String accesskey) {
		attributes.put("accesskey", accesskey);
	}

	public void setTabindex(String tabindex) {
		attributes.put("tabindex", tabindex);
	}

	public void setDisabled(String disabled) {
		attributes.put("disabled", disabled);
	}

	public void setTitle(String title) {
		attributes.put("title", title);
	}

	public void setType(String type) {
		attributes.put("type", type);
	}

	public void setPlaceholder(String placeholder) {
		attributes.put("placeholder", placeholder);
	}

	public void setSize(String size) {
		attributes.put("size", size);
	}

	public void setMaxlength(String maxlength) {
		attributes.put("maxlength", maxlength);
	}

	public void setMin(String min) {
		attributes.put("min", min);
	}

	public void setMax(String max) {
		attributes.put("max", max);
	}

	public void setStep(String step) {
		attributes.put("step", step);
	}
	
	public void setPattern(String regexp) {
		attributes.put("pattern", regexp);
	}
	
	public void setAutocomplete(String onoff) {
		attributes.put("autocomplete", onoff);
	}
	
	public void setReadonly(String readonly) {
		attributes.put("readonly", NO_VALUE);
	}
	
	public void setForm(String id) {
		attributes.put("form", id);
	}
	
	public void setRequired(String required) {
		attributes.put("required", NO_VALUE);
	}
	
	public void setAutofocus(String autofocus) {
		attributes.put("autofocus", NO_VALUE);
	}
	
	public void setList(String list) {
		attributes.put("list", list);
	}
	
}
