package com.ly.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface Inheritable{
	
}

@Inheritable
class InheritableFather{
	public InheritableFather() {
		System.out.println("InheritableFather:" + InheritableFather.class.isAnnotationPresent(Inheritable.class));
	}
}
public class InheritTableSon extends InheritableFather{

	public InheritTableSon() {
		System.out.println("InheritableSon:" + InheritTableSon.class.isAnnotationPresent(Inheritable.class));
	}
	public static void main(String[] args) {

		InheritTableSon son = new InheritTableSon();
	}

}
