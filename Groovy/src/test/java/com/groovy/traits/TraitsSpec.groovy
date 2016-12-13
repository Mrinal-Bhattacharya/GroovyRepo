package com.groovy.traits

import static org.junit.Assert.*

import org.junit.Test

class TraitsSpec {

	@Test
	public void save() {
		Entity gina = new Book(id:1, version:1, title:"gina", isbn:"111111")
		gina.save()
		assert gina.version == 2
	}
}
