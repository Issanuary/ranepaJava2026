package ru.ranepa.repository;

import ru.ranepa.model.Employee;

import java.util.*;

public class EmployeeRepository {
    private final Map<Long, Employee> storage = new HashMap<>();
    private long currentId = 0;

    /**
     * Сохраняет сотрудника в репозиторий
     * @param employee сотрудник для сохранения
     * @return сохраненный сотрудник
     */
    public Employee save(Employee employee) {
        // У сотрудника всегда есть ID, созданный в конструкторе
        // Просто сохраняем его в мапу
        storage.put(employee.getId(), employee);
        return employee;
    }

    /**
     * Возвращает всех сотрудников
     * @return список всех сотрудников
     */
    public List<Employee> findAll() {
        return new ArrayList<>(storage.values());
    }

    /**
     * Поиск сотрудника по ID
     * @param id идентификатор сотрудника
     * @return Optional с сотрудником или пустой Optional
     */
    public Optional<Employee> findById(Long id) {
        // Исправляем проблему с сравнением long и null
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(storage.get(id));
    }

    /**
     * Удаление сотрудника по ID
     * @param id идентификатор сотрудника
     * @return true если удаление успешно, false если сотрудник не найден
     */
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        return storage.remove(id) != null;
    }

    /**
     * Генерация следующего ID (теперь не нужен, так как ID генерируется в Employee)
     * @deprecated ID генерируется в классе Employee
     */
    @Deprecated
    public long getNextId() {
        return ++currentId;
    }
}