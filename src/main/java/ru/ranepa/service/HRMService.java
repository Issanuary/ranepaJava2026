package ru.ranepa.service;

import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HRMService {
    private final EmployeeRepository repository;

    public HRMService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Добавление нового сотрудника
    public Employee addEmployee(String name, String position, double salary, LocalDate hireDate) {
        Employee employee = new Employee(name, position, salary, hireDate);
        return repository.save(employee);
    }

    // Получение всех сотрудников
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Поиск по ID
    public Optional<Employee> findEmployeeById(Long id) {
        return repository.findById(id);
    }

    // Удаление сотрудника
    public boolean deleteEmployee(Long id) {
        return repository.delete(id);
    }

    // Расчет средней зарплаты
    public BigDecimal calculateAverageSalary() {
        List<Employee> employees = repository.findAll();

        if (employees.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal sum = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(BigDecimal.valueOf(employees.size()), 2, RoundingMode.HALF_UP);
    }

    // Поиск самого высокооплачиваемого сотрудника
    public Optional<Employee> findTopPaidEmployee() {
        List<Employee> employees = repository.findAll();

        return employees.stream()
                .max((e1, e2) -> e1.getSalary().compareTo(e2.getSalary()));
    }

    // Фильтрация по должности
    public List<Employee> filterByPosition(String position) {
        return repository.findAll().stream()
                .filter(e -> e.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());
    }

    // Получение статистики
    public String getStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== Company Statistics ===\n");

        // Средняя зарплата
        stats.append("Average salary: ").append(calculateAverageSalary()).append("\n");

        // Самый высокооплачиваемый сотрудник
        Optional<Employee> topPaid = findTopPaidEmployee();
        if (topPaid.isPresent()) {
            Employee emp = topPaid.get();
            stats.append("Top paid employee: ").append(emp.getName())
                    .append(" (").append(emp.getPosition()).append(") - ")
                    .append(emp.getSalary()).append("\n");
        } else {
            stats.append("No employees found\n");
        }

        // Количество сотрудников
        stats.append("Total employees: ").append(repository.findAll().size()).append("\n");

        return stats.toString();
    }
}