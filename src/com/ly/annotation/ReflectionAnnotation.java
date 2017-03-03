package com.ly.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface ReflectAnnotation{
	String[] value() default "unknow";
}

class Person{
	@ReflectAnnotation
	@Deprecated
	public void empty(){
		System.out.println("\nempty");
	}
	
	@ReflectAnnotation(value={"girl,boy"})
	public void someBody(String name,int age){
		System.out.println("name = " + name + "; age = " + age);
	}
}
public class ReflectionAnnotation {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Person person = new Person();
		Class<Person> personClass = Person.class;
		Method methodSomeBodyMethod = personClass.getMethod("someBody", new Class[]{String.class,int.class});
		methodSomeBodyMethod.invoke(person, new Object[]{"Lily",18});
		iteratorAnnotations(methodSomeBodyMethod);
		
		Method emptyMethod = personClass.getMethod("empty", new Class[]{});
		emptyMethod.invoke(person, new Object[]{});
		iteratorAnnotations(emptyMethod);
	}
	
	public static void iteratorAnnotations(Method method){
		if(method.isAnnotationPresent(ReflectAnnotation.class)){
			ReflectAnnotation reflectAnnotation = method.getAnnotation(ReflectAnnotation.class);
			String []valueStrings = reflectAnnotation.value();
			for(String str:valueStrings) {
				System.out.println("string: " + str);
			}
		}
		
		Annotation []annotations = method.getAnnotations();
		for(Annotation annotation:annotations){
			System.out.println("annotation: " + annotation);
		}
	}

}
