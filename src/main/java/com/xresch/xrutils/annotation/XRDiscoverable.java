package com.xresch.xrutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface XRDiscoverable {

	 String name() default "";
	 String[] tags() default {};
	 
}
