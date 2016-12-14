package com.groovy.mars

import groovy.transform.TupleConstructor

class Robot{
	void move(Map map,Direction dir){}
	def move1(Direction dir){
		this
	}
	def at(Speed speed){
		this
	}
}
enum Direction{
	left,right,up,down
}
enum DistanceUnit {
	centimeter('cm',0.01),
	meter('m',1),
	kilometer('km',1000)
	String abbrivation
	double multiplier

	DistanceUnit(String abbr,double mult){
		this.abbrivation=abbr
		this.multiplier=mult
	}

	String toString(){
		abbrivation
	}
}
@TupleConstructor
class Distance{
	double amount
	DistanceUnit unit
	String toString(){
		"$amount $unit"
	}
	Speed div(Duration t){
		new Speed(this,t)
	}
}
enum TimeUnit {
	hour('h',3600),
	minute('min',60),
	second('s',1)
	String abbrivation
	double multiplier

	TimeUnit(String abbr,double mult){
		this.abbrivation=abbr
		this.multiplier=mult
	}

	String toString(){
		abbrivation
	}
}
@TupleConstructor
class Duration{
	double amount
	TimeUnit unit
	String toString(){
		"$amount $unit"
	}
}
@TupleConstructor
class Speed{
	Distance distance
	Duration duration
	String toString(){
		"$distance/$duration"
	}
}