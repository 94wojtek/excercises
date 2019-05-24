package lambda.expression;

import java.util.function.BiFunction;

public class Human {

    private int age;
    private String name;

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

class HumanMain {
    public static void main(String[] args) {
        BiFunction<Integer, String, Human> create = Human::new;
        System.out.println(create.apply(24, "wojtek"));
    }
}
