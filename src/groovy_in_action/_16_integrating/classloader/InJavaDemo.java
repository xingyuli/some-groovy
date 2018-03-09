package groovy_in_action._16_integrating.classloader;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

public class InJavaDemo {

    public static void main(String[] args) throws Exception {
        GroovyClassLoader gcl = new GroovyClassLoader();
        Class greetingClass = gcl.parseClass(new File("src/groovy_in_action/_16_integrating/classloader/Hello.groovy"));

        // From Java, to invoke a method, you have to either use reflection explicitly--which is usually pretty ugly--or
        // rely on the fact that all Groovy classes automatically implement the groovy.lang.GroovyObject interface,
        // exposing the invokeMethod, getProperty, and setProperty methods.

        // Where getProperty and setProperty are responsible for accessing properties of your Groovy class from Java,
        // invokeMethod allows you to call any method on Groovy classes easily from Java.

        GroovyObject hello = (GroovyObject) greetingClass.newInstance();
        System.out.println(hello.invokeMethod("greeting", null));
    }

}
