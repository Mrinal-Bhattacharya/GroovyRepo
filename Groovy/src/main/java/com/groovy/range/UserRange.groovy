package com.groovy.range

class UserRange {
	def forin(){
		String log=''
		for(element in 1..5){
			log+=element
		}
		return log
	}
	def foreach(){
		def log=''
		(1..5).each { element-> log+=element }
		return log
	}
	def loopInReverse(){
		def log=''
		for(element in 9..5){
			log+=element
		}
		return log
	}

	def halfExclusiveReverse(){
		def log=''
		(9..<5).each { element-> log+=element }
		return log
	}

	def insuranceRate(def age){
		def insuranceRate=0.0
		switch(age){
			case 16..20 : insuranceRate = 0.05 ; break
			case 21..50 : insuranceRate = 0.06 ; break
			case 51..65 : insuranceRate = 0.07 ; break
			default: throw new IllegalArgumentException()
		}
		return insuranceRate
	}
}
class Weekday implements Comparable {
	static final DAYS = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
	private int index = 0

	Weekday(String day) {
		index = DAYS.indexOf(day)
	}
	Weekday next() {
		return new Weekday(DAYS[(index + 1) % DAYS.size()])
	}
	Weekday previous() {
		return new Weekday(DAYS[index - 1])
	}
	int compareTo(Object other) {
		return this.index <=> other.index
	}
	String toString() {
		return DAYS[index]
	}
	def getWorkingDays(){
		def mon = new Weekday('Mon')
		def fri = new Weekday('Fri')
		def worklog = ''
		for (day in mon..fri) {
			worklog += day.toString() + ' '
		}
		return worklog
	}
}