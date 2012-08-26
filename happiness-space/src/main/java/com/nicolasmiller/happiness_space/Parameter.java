package com.nicolasmiller.happiness_space;

import java.util.HashMap;
import java.util.Map;

public class Parameter {
	private String name;
	private int minimum = 0;
	private int maximum;
	private int value;
	private Map<Integer, String> valueNames = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void addValueName(Integer value, String name) {
		if (valueNames == null) {
			valueNames = new HashMap<Integer, String>();
		}
		valueNames.put(value, name);
	}

	public boolean hasValueNames() {
		return !(valueNames == null);
	}

	public String getValueName(Integer value) {
		return valueNames.get(value);
	}
}
