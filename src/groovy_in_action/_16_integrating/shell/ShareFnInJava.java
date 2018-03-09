package groovy_in_action._16_integrating.shell;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.runtime.MethodClosure;

public class ShareFnInJava {

    static class Multiplicator {
        public long multiply(long a, long b) {
            return a * b;
        }
    }

    public static void main(String[] args) {
        MethodClosure mclos = new MethodClosure(new Multiplicator(), "multiply");
        Binding binding = new Binding();
        binding.setVariable("multiply", mclos);

        GroovyShell shell = new GroovyShell(binding);
        System.out.println(shell.evaluate("multiply(5, 6)"));
    }

}
