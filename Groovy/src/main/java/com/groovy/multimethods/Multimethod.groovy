package com.groovy.multimethods

class Multimethod {
	def oracle(Object o) {
		return 'object'
	}
	def oracle(String o) {
		return 'string'
	}
}
class Equalizer {
	boolean equals(Equalizer e){
		return true
	}
}

// Java Version of equals
class JavaEqualizer { // Java
	public boolean equals(Object obj)
	{
		if (obj == null) return false
		if (!(obj instanceof JavaEqualizer)) return false
		JavaEqualizer w = (JavaEqualizer) obj
		return true // custom logic here
	}
}