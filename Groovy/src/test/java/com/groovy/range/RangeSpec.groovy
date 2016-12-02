package com.groovy.range

import static org.junit.Assert.*

import org.junit.Test

class RangeSpec {


	@Test
	void forin(){
		UserRange r=new UserRange()
		assert r.forin() =='12345'
	}
	@Test
	void foreach(){
		UserRange r=new UserRange()
		assert r.foreach()=='12345'
	}
	@Test
	void loopReverse(){
		UserRange r=new UserRange()
		assert r.loopInReverse()=='98765'
	}
	@Test
	void loopReverseOnlyHalf(){
		UserRange r=new UserRange()
		assert r.halfExclusiveReverse()=='9876'
	}

	@Test
	void filtering(){
		def ages = [20, 36, 42, 56]
		def midage = 21..50
		assert ages.grep(midage) == [36, 42]
	}

	@Test
	void rangeWithSwitch(){
		UserRange userRange=new UserRange()
		assert userRange.insuranceRate(36)==0.06
	}

	@Test
	void customRange(){
		Weekday weekDay=new Weekday()
		assert weekDay.getWorkingDays() == 'Mon Tue Wed Thu Fri '
	}

	@Test
	void miscellany(){
		assert (0..10).contains(0)
		assert (0..10).contains(5)
		assert (0..10).contains(10)
		assert (0..10).contains(-1) == false
		assert (0..10).contains(11) == false
		assert (0..<10).contains(9)
		assert (0..<10).contains(10) == false
		def a = 0..10
		assert a instanceof Range
		assert a.contains(5)
		a = new IntRange(0,10)
		assert a.contains(5)
		assert (0.0..1.0).contains(1.0)
		assert (0.0..1.0).containsWithinBounds(0.5)
		def today = new Date()
		def yesterday = today - 1
		assert (yesterday..today).size() == 2
		assert ('a'..'c').contains('b')
	}
}
