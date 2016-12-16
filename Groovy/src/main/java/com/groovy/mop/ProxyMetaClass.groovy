package com.groovy.mop

class InspectMe {
	int outer(){
		return inner()
	}
	private int inner(){
		return 1
	}
}
