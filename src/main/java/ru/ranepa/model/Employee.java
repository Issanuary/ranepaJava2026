package ru.ranepa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
//поля
public class Employee {
    private long id;
    private  String name;
    private  String position;
    private BigDecimal salary;
    private final LocalDate hireDate;

    //construct(созд.сотруд)
    public Employee(String name, String position, double salary, LocalDate hireDate) {
        this.name = name;
        this.position = position;
        this.salary = BigDecimal.valueOf(salary);
        this.hireDate = hireDate;
    }

    //getter(чтение д)
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }
//setter(изменение д)
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
//toString
    @Override
    public String toString() {
        return "employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }
}



