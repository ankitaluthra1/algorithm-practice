package leetcode.random;

import java.util.Optional;

public class OptionalExample {

    static class Employee{
        String name;
        Employee(String name){
            this.name = name;
        }
    }

    private String nullTest(Employee e){
        Optional<Employee> optionalEmployee = Optional.ofNullable(e);
        return optionalEmployee.flatMap(o -> Optional.ofNullable(o.name)).orElse("some default value");
    }

    public static void main(String[] args) {
        Employee e1 = null;
        Employee e2 = new Employee(null);

        OptionalExample oe = new OptionalExample();
        System.out.println(oe.nullTest(e1));
        System.out.println(oe.nullTest(e2));
    }
}
