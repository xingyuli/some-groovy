package groovy_in_action._16_integrating.shell;

import groovy.lang.GroovyShell;

public class InJavaDemo {

    public static void main(String[] args) {
        GroovyShell shell = new GroovyShell();
        Object result = shell.evaluate("12 + 23");
        assert result.equals(35);
    }

}
