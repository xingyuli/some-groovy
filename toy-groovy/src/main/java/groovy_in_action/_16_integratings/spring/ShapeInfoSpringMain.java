package groovy_in_action._16_integratings.spring;

import groovy.lang.GroovyObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ShapeInfoSpringMain {

    public static void main(String[] args) {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/beans.xml");
            Shape s = new Square(7L);

            // Wiring GroovyBeans
            Shape c = (Shape) ctx.getBean("circle");
            ShapeInfo info = (ShapeInfo) ctx.getBean("maxareainfo");
            info.displayInfo(s, c);

            new MaxPerimeterInfo().displayInfo(s, c);

            GroovyObject info2 = (GroovyObject) ctx.getBean("maxareainfo2");
            System.out.println(info2.getProperty("prefix"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
