package com.xresch.xrutils.annotation;

import java.util.ArrayList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xresch.xrutils.utils.XRCSV;

public class XRAnnotations {
	
	private static Logger logger = LoggerFactory.getLogger(XRCSV.class.getName());
	
	/*****************************************************************************
	 * Returns the registry if it exists, else null.
	 * 
	 * @return class of registry or null
	 * 
	 *****************************************************************************/
	private static Class<?>[] getXRDiscoverableClasses() {
		
		Class<?>[] clazzes = new Class<?>[] {};
		
		try {
			Class<?> registry = Class.forName(XRDiscoverable.class.getName()+"Registry");
			
			clazzes = (Class<?>[]) registry.getField("CLASSES").get(null);
		} catch (ClassNotFoundException e) {
			logger.warn("Couldn't find registry: XRDiscoverableRegistry");
		}  catch (Exception e) {
			logger.warn("Error while reading XRDiscoverableRegistry.CLASSES: "+e.getMessage());
		}
		
		return clazzes;
	}
	
	/*****************************************************************************
	 * Discovers classes annotated with @XRDiscoverable and are of the specific 
	 * type. Will only return classes that are of the specified type, meaning 
	 * either the type itself or a subclass of the type.
	 * 
	 * @param type to filter the list by
	 * 
	 * @return list of classes found
	 * 
	 *****************************************************************************/
	@SuppressWarnings("unchecked")
	public static <T> ArrayList<Class<T>> discover(Class<T> type) {
		
		ArrayList<Class<T>> foundClasses = new ArrayList<>();
		
		try {
			Class<?>[] clazzes = getXRDiscoverableClasses();
			
			for(Class<?> clazz : clazzes) {
				if(type.isAssignableFrom(clazz)) {
					foundClasses.add( (Class<T>)clazz );
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foundClasses;
			
	}
	
	/*****************************************************************************
	 * Discovers classes annotated with @XRDiscoverable and are of the specific 
	 * type. Will only return classes that are of the specified type, meaning 
	 * either the type itself or a subclass of the type.
	 * 
	 * @param type to filter the list by
	 * @params tags the tags to filter by (must match all tags, case-sensitive)
	 * 
	 * @return list of classes found
	 * 
	 *****************************************************************************/
	@SuppressWarnings("unchecked")
	public static <T> ArrayList<Class<T>> discover(Class<T> type, String... tags) {
		
		ArrayList<Class<T>> foundClasses = new ArrayList<>();
		
		try {

			Class<?>[] clazzes = getXRDiscoverableClasses();
			
			outer:
			for(Class<?> clazz : clazzes) {
				if(type.isAssignableFrom(clazz)) {
					
					XRDiscoverable annotation = clazz.getDeclaredAnnotation(XRDiscoverable.class);
					Set<String> annotationTags = Set.of(annotation.tags());
					
					//-----------------------
					// Check has all tags
					for(String tag : tags) {
						if( ! annotationTags.contains(tag) ) {
							continue outer;
						}
					}
					
					//-----------------------
					// Add to findings 
					foundClasses.add( (Class<T>)clazz );
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foundClasses;
			
	}
	
	/*****************************************************************************
	 * Discovers classes annotated with @XRDiscoverable and have the specified 
	 * tags.
	 * 
	 * @params tags the tags to filter by (must match all tags, case-sensitive)
	 * 
	 * @return list of classes found
	 * 
	 *****************************************************************************/
	public static  ArrayList<Class<?>> discover(String... tags) {
		
		ArrayList<Class<?>> foundClasses = new ArrayList<>();
		
		try {

			Class<?>[] clazzes = getXRDiscoverableClasses();
			
			outer:
			for(Class<?> clazz : clazzes) {

				XRDiscoverable annotation = clazz.getDeclaredAnnotation(XRDiscoverable.class);
				Set<String> annotationTags = Set.of(annotation.tags());
				
				//-----------------------
				// Check has all tags
				for(String tag : tags) {
					if( ! annotationTags.contains(tag) ) {
						continue outer;
					}
				}
				
				//-----------------------
				// Add to findings 
				foundClasses.add( (Class<?>)clazz );
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foundClasses;
			
	}
	
}
