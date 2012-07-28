package com.nicolasmiller.happiness_space;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FooTest {
	@Test
	void testGetValue() {
		Foo x = new Foo(3);
		assertEquals(3, x.getValue());
	}
}
