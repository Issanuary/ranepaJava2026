package ru.ranepa.repository; //хранилище

import ru.ranepa.model.Employee;

import java.util.*;

public class EmployeeRepository {
    private final Map<Long, Employee> storage = new HashMap<>();
    private long currentId = 0;

    //сохр сотрд
    public Employee save(Employee employee) {
        currentId++;
        employee.setId(currentId);
        storage.put(employee.getId(), employee);
        return employee;
    }
    //получить список сотрудников
    public List<Employee> findAll() {
        return new ArrayList<>(storage.values());
    }
    //найти сотр
    public Optional<Employee> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(storage.get(id));
    }
    //удалить
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        return storage.remove(id) != null;
    }
}