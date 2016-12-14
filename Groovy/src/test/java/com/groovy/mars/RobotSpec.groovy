package com.groovy.mars

import static org.junit.Assert.*
import static com.groovy.mars.Direction.*
import static com.groovy.mars.TimeUnit.*
import static com.groovy.mars.DistanceUnit.*
import org.junit.Test

class RobotSpec {

	@Test
	public void move() {
		def robot=new Robot()
		def h=new Duration(1,hour)
		println h
		//robot.move left
		Number.metaClass.getKm ={-> new Distance(delegate,kilometer)}
		println 2.km/h
		robot.move left,at:3.km/h
		println robot.move1(left).at(new Speed())
	}
}
