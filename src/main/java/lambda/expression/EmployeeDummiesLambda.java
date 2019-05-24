package lambda.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class EmployeeDummiesLambda {
    private String name;
    private int perfEvalScore;

    public EmployeeDummiesLambda(String name, int perfEvalScore) {
        this.name = name;
        this.perfEvalScore = perfEvalScore;
    }

    public String getName() {
        return name;
    }

    public int getPerfEvalScore() {
        return perfEvalScore;
    }
}
class EmployeeUse {
    public static void main(String[] args) {
        List<EmployeeDummiesLambda> employees = new ArrayList<>();
        employees.add(new EmployeeDummiesLambda("wojtek", 5));
        employees.add(new EmployeeDummiesLambda("john", 1));
        employees.add(new EmployeeDummiesLambda("patryk", 3));
        employees.add(new EmployeeDummiesLambda("joachim", 2));
        employees.add(new EmployeeDummiesLambda("bożydar", 3));

        double totalPay = employees.stream().
                    filter(employeeWithScore3Plus -> employeeWithScore3Plus.getPerfEvalScore() >= 3).
                    map(employeeWithScore3Plus -> 100.0).
                    reduce(0.0, Double::sum);

        System.out.println(totalPay);
    }
}
