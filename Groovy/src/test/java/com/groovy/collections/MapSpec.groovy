package com.groovy.collections

import org.junit.Test

class MapSpec {
	@Test
	void miscellany(){
		def myMap = [a:1, b:2, c:3]
		assert myMap instanceof LinkedHashMap
		assert myMap.size() == 3
		assert myMap['a'] == 1
		def emptyMap = [:]
		assert emptyMap.size() == 0
		def explicitMap = new TreeMap()
		explicitMap.putAll(myMap)
		assert explicitMap['a'] == 1
		def composed = [x:'y', *:myMap]
		assert composed == [x:'y', a:1, b:2, c:3]
		def x = 'a'
		assert ['x':1] == [x:1]
		assert ['a':1] == [(x):1]
	}

	@Test
	void accessMap(){
		def myMap = [a:1, b:2, c:3]
		assert myMap['a'] == 1
		assert myMap.a == 1
		assert myMap.get('a') == 1
		assert myMap.get('a',0) == 1
		assert myMap['d'] == null
		assert myMap.d == null
		assert myMap.get('d') == null
		assert myMap.get('d',0) == 0
		assert myMap.d == 0
		myMap['d'] = 1
		assert myMap.d == 1
		myMap.d = 2
		assert myMap.d == 2
	}

	@Test
	void query(){
		def myMap = [a:1, b:2, c:3]
		def other = [b:2, c:3, a:1]
		assert myMap == other
		assert !myMap.isEmpty()
		assert myMap.size() == 3
		assert myMap.containsKey('a')
		assert myMap.containsValue(1)
		assert myMap.entrySet() instanceof Collection
		assert myMap.any {entry -> entry.value > 2 }
		assert myMap.every {entry -> entry.key < 'd'}
		assert myMap.keySet() == ['a', 'b', 'c'] as Set
		assert myMap.values().toList() == [1, 2, 3]
	}

	@Test
	void iterating(){
		def myMap = [a:1, b:2, c:3]
		def store = ''
		myMap.each { entry ->
			store += entry.key
			store += entry.value
		}
		assert store == 'a1b2c3'
		store = ''
		myMap.each { key, value ->
			store += key
			store += value
		}
		assert store == 'a1b2c3'
		store = ''
		for (key in myMap.keySet()) {
			store += key
		}
		assert store == 'abc'
		store = ''
		for (value in myMap.values()) {
			store += value
		}
		assert store == '123'
	}

	@Test
	void buildingNewObjectFromMap(){
		def myMap = [a:1, b:2, c:3]
		myMap.clear()
		assert myMap.isEmpty()
		myMap = [a:1, b:2, c:3]
		myMap.remove('a')
		assert myMap.size() == 2
		assert [a:1] + [b:2] == [a:1, b:2]
		myMap = [a:1, b:2, c:3]
		def abMap = myMap.subMap(['a', 'b'])
		assert abMap.size() == 2
		abMap = myMap.findAll { entry -> entry.value < 3 }
		assert abMap.size() == 2
		assert abMap.a == 1
		def found = myMap.find { entry -> entry.value < 2 }
		assert found.key == 'a'
		assert found.value == 1
		def doubled = myMap.collect { entry -> entry.value *= 2 }
		assert doubled instanceof List
		assert doubled.every { item -> item % 2 == 0 }
		def addTo = []
		myMap.collect(addTo) { entry -> entry.value *= 2 }
		assert addTo instanceof List
		assert addTo.every { item -> item % 2 == 0 }
	}

	@Test
	void countFrequency(){
		def textCorpus =
				"""
the bare necessities
your your bare necessities 
the bare necessities
the bare necessities life
"""
		def grrovyMap=new GroovyMap()
		def wordFrequency=grrovyMap.frequency(textCorpus)
		def expected =[necessities:4,bare:4,the:3,your:2,life:1]
		assert wordFrequency==expected
	}
}
