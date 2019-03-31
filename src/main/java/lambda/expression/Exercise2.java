package lambda.expression;

import java.util.function.UnaryOperator;

public class Exercise2 {
    public static void main(String[] args) {
        Object object = new Object();
        UnaryOperator<Object> unary = object::equals;
        System.out.println(unary.equals(new Object()));
    }
}
