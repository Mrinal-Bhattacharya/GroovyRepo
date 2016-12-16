package com.groovy.mop

class Pretender {
	def methodMissing(String name, Object args) {
		"called $name with $args"
	}
}
class MiniGorm {
	def db = []
	def methodMissing(String name, Object args) {
		db.find { it[name.toLowerCase()-'findby'] == args[0] }
	}
}
class PropPretender {
	def propertyMissing(String name) {
		"accessed $name"
	}
}
class DynamicPretender {
	Closure whatToDo = { name -> "accessed $name"}
	def propertyMissing(String name) {
		whatToDo(name)
	}
}
class MyGroovy1 { }
class MyGroovy2 { }
class MySuperGroovy { }
class MySubGroovy extends MySuperGroovy { }