/*
 * Copyright (c) 2015 tamacat.org
 * All rights reserved.
 */
package org.tamacat.taglib.input;

/**
 * This class implements the &lt;input:radio&gt; tag, which presents an
 * &lt;input type="radio" ... /&gt; form element.
 */
public class Radio extends Checkbox {

	private static final long serialVersionUID = 1L;
	
	public Radio() {
    	setType("radio");
    }
    
	public void release() {
        super.release();
        setType("radio");
    }
}