package com.groovy.closure

class GroovyClosure {
	String logWithCounter(){
		def log = ''
		(1..10).each{ counter -> log += counter }
		return log
	}
	String logWithMagicalIT(){
		def log = ''
		(1..10).each{ log += it }
		return log
	}

	def printer={ line-> "Hello "+line }

	def doubleMethod (entry){
		entry.value = entry.value * 2
	}
	def numParams (Closure closure){
		closure.getMaximumNumberOfParameters()
	}
	def paramTypes (Closure closure){
		closure.getParameterTypes()
	}
}
class Book {
	String name
	String author
	BigDecimal price
	String category
}
abstract class Functor {
	//  arithmetic (binary, left commute and right commute)
	public static Closure bMultiply     = { x, y -> return x * y }
	public static Closure rMultiply     = { y, x -> return x * y }
	public static Closure lMultiply     = { x, y -> return x * y }

	// ...
	// composition
	public static Closure composition   = { f, g, x -> return f(g(x)) }
}