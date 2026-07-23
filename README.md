# XRUtils-Advanced
Yet another library that was created for the sole reason that Java does not allow an AnnotationProcessor be registered as a Service in the same project as it is created(as it needs to be compiled first). Might add more stuff later, therefore the generic name "advanced".


# XRAnnotations

XRAnnotation was initially created for the reason to be able to make specific classes discoverable, without the need to search through the entire class loader or using third party libraries that add another few hundred kilobytes to the compilation.

## Setup
To use XRAnnotation you need to register the AnnotationProcessor as a service:

File: 

```text
/src/main/resources/META-INF/services/javax.annotation.processing.Processor
```

File Content - Classname of the Processor:

```text
com.xresch.xrutils.annotation.AnnotationProcessor
```

## @XRDiscoverable
The annotation `@XRDiscoverable` allows classes to be marked so they can be afterwards discovered.

In the class you want to make discoverable, add the annotation:

```java
@XRDiscoverable( tags = {"strawberry", "apple"} )
public class MyDiscoverableClass extends MySuperclass {}

```

Now you can use `XRAnnotations.discover()` to discover an array of specific classes:

```java
ArrayList<Class<MySuperclass>> result = XRAnnotations.discover(MySuperclass.class);
System.out.println("result:" + result);

ArrayList<Class<MySuperclass>> resultTags = XRAnnotations.discover(MySuperclass.class, "strawberry", "orange");		
System.out.println("resultTags:" + resultTags);
```
