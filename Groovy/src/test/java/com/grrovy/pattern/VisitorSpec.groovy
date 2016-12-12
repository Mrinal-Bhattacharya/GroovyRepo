package com.grrovy.pattern

import static org.junit.Assert.*

import org.junit.Test

class VisitorSpec {

	@Test
	public void test() {
		def picture = new Drawing(shapes:
		[new Square(width:1), new Circle(radius:1)])
		def total = 0
		picture.accept { total += it.area() }
		assert 4.141592653589793 == total
		println "The shapes in this drawing cover an area of $total units."
		println 'The individual contributions are: '
		picture.accept { println it.class.name + ":" + it.area() }
	}
}
