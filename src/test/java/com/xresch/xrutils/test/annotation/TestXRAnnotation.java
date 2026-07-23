package com.xresch.xrutils.test.annotation;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.xresch.xrutils.annotation.XRAnnotations;
import com.xresch.xrutils.utils.XRMath;
import com.xresch.xrutils.utils.XRRandom;


public class TestXRAnnotation {
		
	/*****************************************************
	 * 
	 *****************************************************/
	@Test
	public void testSearch() {
		
		//----------------------------
		// Discover Single
		ArrayList<Class<XRRandom>> result = XRAnnotations.discover(XRRandom.class);
		
		System.out.println("result:" + result);
		Assertions.assertEquals(1, result.size(), "Found one class");
		
		//----------------------------
		// Discover Multiple
		ArrayList<Class<XRMath>> resultMath = XRAnnotations.discover(XRMath.class);
		
		System.out.println("resultMath:" + resultMath);
		Assertions.assertEquals(2, resultMath.size(), "Found 2 classes");
		
		//----------------------------
		// Discover Type and 1 Tag
		ArrayList<Class<XRMath>> resultOneTag = XRAnnotations.discover(XRMath.class, "strawberry");
		
		System.out.println("resultOneTag:" + resultOneTag);
		Assertions.assertEquals(2, resultOneTag.size(), "Found 2 classes");
		
		//----------------------------
		// Discover Type and 2 Tags
		ArrayList<Class<XRMath>> resultTwoTags = XRAnnotations.discover(XRMath.class, "strawberry", "orange");
		
		System.out.println("resultTwoTags:" + resultTwoTags);
		Assertions.assertEquals(1, resultTwoTags.size(), "Found 1 classes");
		
		//----------------------------
		// Discover by Tags
		ArrayList<Class<?>> resultTagOnly = XRAnnotations.discover("orange");
		
		System.out.println("resultTagOnly:" + resultTagOnly);
		Assertions.assertEquals(2, resultTagOnly.size(), "Found 2 classes");
	}
	
	
	
}
