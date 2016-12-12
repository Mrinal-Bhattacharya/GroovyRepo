package com.groovy.closure

class Person {
	String name
	int age
	def fetchAge = { age }
	def pretty = { "Person name is $name" }
	String toString() {
		pretty()
	}
}
class Thing {
	String name
	def pretty = { "Thing name is $name" }
	String toString() {
		pretty()
	}
}

