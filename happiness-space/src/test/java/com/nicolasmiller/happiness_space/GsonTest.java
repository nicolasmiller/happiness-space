package com.nicolasmiller.happiness_space;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class GsonTest {
	private Parameter p1;
	private Parameter p2;
	private Parameter p3;
	private SynthesizerModel model;
	private SynthesizerModel deserializedModel;
	private Gson gson;
	private static final String json = "{\"parameters\":[{\"name\":\"Parameter 1\",\"minimum\":0,\"maximum\":127,\"value\":42},{\"name\":\"Parameter 2\",\"minimum\":0,\"maximum\":127,\"value\":9},{\"name\":\"State\",\"minimum\":0,\"maximum\":2,\"value\":2,\"valueNames\":{\"0\":\"off\",\"1\":\"on\",\"2\":\"way on\"}}]}";

	@Before
	public void setUp() {
		// midi cc type integer range params
		p1 = new Parameter();
		p1.setMinimum(0);
		p1.setMaximum(127);
		p1.setName("Parameter 1");
		p1.setValue(42);

		p2 = new Parameter();
		p2.setMinimum(0);
		p2.setMaximum(127);
		p2.setName("Parameter 2");
		p2.setValue(9);

		// named option "switch" param
		p3 = new Parameter();
		p3.setMinimum(0);
		p3.setMaximum(2);
		p3.setName("State");
		p3.setValue(2);
		p3.addValueName(0, "off");
		p3.addValueName(1, "on");
		p3.addValueName(2, "way on");

		gson = new Gson();
		model = new SynthesizerModel();
		model.addParameter(p1);
		model.addParameter(p2);
		model.addParameter(p3);
		System.out.println(gson.toJson(model));
	}

	@Test
	public void testToJSON() {
		assertEquals(true, p3.hasValueNames());
		assertEquals("off", p3.getValueName(0));
		assertEquals("on", p3.getValueName(1));
		assertEquals("way on", p3.getValueName(2));
		assertEquals(null, p3.getValueName(47));
		assertEquals(json, gson.toJson(model));
	}

	@Test
	public void testFromJSON() {
		deserializedModel = gson.fromJson(json, SynthesizerModel.class);
		assertReflectionEquals(model, deserializedModel);
	}
}