package ru.ranepa.model;

import java.time.LocalDate;

public class HRM_App {
    public static void main(String[] args) {
        var e= new Employee("Иванов Виталий Сергеевич",
                "Руководитель", 25_000.0, LocalDate.of(2026,  2,26));
        //SOUT
        System.out.println(e);
    }
}