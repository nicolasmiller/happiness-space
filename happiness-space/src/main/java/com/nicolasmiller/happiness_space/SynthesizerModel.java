package com.nicolasmiller.happiness_space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SynthesizerModel {
	private List<Parameter> parameters;
	public transient Map<String, Parameter> name;

	public SynthesizerModel() {
		parameters = new ArrayList<Parameter>();
	}

	public void addParameter(Parameter p) {
		parameters.add(p);
	}

	public List<Parameter> getParameters() {
		return parameters;
	}
}
