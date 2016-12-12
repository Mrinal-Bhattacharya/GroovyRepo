package com.java.pattern;

import java.util.ArrayList;
import java.util.List;

public class VisitorPatternDemo {

	public static void main(String[] args) {
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Square());
		shapes.add(new Circle());
		Drawing draw = new Drawing(shapes);
		draw.accept(new PainterVisitor());
	}

}

interface Shape {
	void accept(PainterVisitor visitor);
}

class Square implements Shape {

	@Override
	public void accept(PainterVisitor visitor) {
		visitor.visit(this);
	}

	public int area() {
		return 1;
	}

}

class Circle implements Shape {
	@Override
	public void accept(PainterVisitor visitor) {
		visitor.visit(this);
	}

	public int area() {
		return 2;
	}
}

class Drawing implements Shape {
	List<Shape> shapes;

	public Drawing(List<Shape> shapes) {
		this.shapes = shapes;
	}

	@Override
	public void accept(PainterVisitor visitor) {
		for (Shape shape : shapes) {
			shape.accept(visitor);
		}
	}
}

interface IVisitor {

	void visit(Square shape);

	void visit(Circle shape);

}

class PainterVisitor implements IVisitor {

	@Override
	public void visit(Circle shape) {
		System.out.println(shape.area());
	}

	@Override
	public void visit(Square shape) {
		System.out.println(shape.area());
	}

}