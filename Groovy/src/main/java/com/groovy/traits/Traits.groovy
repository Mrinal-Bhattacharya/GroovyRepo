package com.groovy.traits

trait Traits {
	long id
}
trait HasVersion {
	long version
}
trait Persistent {
	boolean save() {
		println "saving ${this.dump()}"
	}
}
trait Entity implements Persistent, Traits, HasVersion {
	boolean save() {
		version++
		Persistent.super.save()
	}
}
class Publication implements Entity {
	String title
}
class Book extends Publication {
	String isbn
}