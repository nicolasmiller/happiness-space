package com.nicolasmiller.happiness_space;

import java.util.ArrayList;
import java.util.List;

public class Parameter {
	private static final String LSB_MSB = "LSB_MSB";
	private String name;
	private int minimum = 0;
	private int maximum;
	private int value;
	private List<String> valueNames = null;
	private List<Integer> valueIndices = null;
	private String valueMode;
	private byte[] sysexMessage;

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

	public void addValueName(String name) {
		if (valueNames == null) {
			valueNames = new ArrayList<String>();
		}
		valueNames.add(name);
	}

	public boolean hasValueNames() {
		return !(valueNames == null);
	}

	public String[] getNames() {
		String[] names = new String[valueNames.size()];
		int i = 0;
		for (String name : valueNames) {
			names[i] = name;
			i++;
		}
		return names;
	}

	public String getValueName(Integer value) {
		return valueNames.get(value);
	}

	public void setSysexMessage(byte[] message) {
		this.sysexMessage = message;
	}

	public void setValueIndices(List<Integer> indices) {
		this.valueIndices = indices;
	}

	public void setValueMode(String valueMode) {
		this.valueMode = valueMode;
	}

	private void insertMessageValue() {
		if (valueMode.equals(LSB_MSB)) {
			if (valueIndices.size() != 2) {
				throw new RuntimeException(
						"LSB_MSB mode expects two sysex message indices for inserting parameter value.");
			}
			sysexMessage[valueIndices.get(0)] = (byte) (0x0f & this.getValue());
			sysexMessage[valueIndices.get(1)] = (byte) ((0xf0 & this.getValue()) >> 4);
		} else {
			throw new RuntimeException("Invalid message value mode.");
		}
	}

	public byte[] getSysexMessage() {
		insertMessageValue();
		return sysexMessage;
	}
}
