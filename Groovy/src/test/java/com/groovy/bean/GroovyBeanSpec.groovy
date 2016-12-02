package com.groovy.bean

import static org.junit.Assert.*

import org.junit.Test

class GroovyBeanSpec {

	@Test
	public void accessFieldsWithAccessMethod() {
		def groovyBook = new GroovyBean()
		groovyBook.setTitle('Groovy in Action')
		assert groovyBook.getTitle() == 'Groovy in Action'
		groovyBook.title = 'Groovy conquers the world'
		assert groovyBook.title == 'Groovy conquers the world'
	}
}
