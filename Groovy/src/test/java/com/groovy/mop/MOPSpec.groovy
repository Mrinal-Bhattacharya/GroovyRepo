package com.groovy.mop

import static org.junit.Assert.*

import org.junit.Test

class MOPSpec {

	@Test
	public void methodMissing() {
		def bounce = new Pretender()
		assert bounce.hello('world') == 'called hello with [world]'
	}

	@Test
	public void gorm() {
		def people = new MiniGorm()
		def dierk = [first: 'Dierk', last:'Koenig']
		def paul = [first: 'Paul', last:'King']
		people.db << dierk << paul
		assert people.findByFirst('Dierk') == dierk
		assert people.findByLast('King') == paul
	}

	@Test
	void propertyMissing(){
		def bounce = new PropPretender()
		assert bounce.hello == 'accessed hello'
	}

	@Test
	void dynamicPropertyMissing(){
		def one = new DynamicPretender()
		assert one.hello == 'accessed hello'
		one.whatToDo = { name -> name.size() }
		assert one.hello == 5
	}
}
