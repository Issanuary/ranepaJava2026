package ru.ranepa.presentation;

import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepository;
import ru.ranepa.service.HRMService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HRMApplication {
    private final HRMService service;
    private final Scanner scanner;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //констр
    public HRMApplication() {
        EmployeeRepository repository = new EmployeeRepository();
        this.service = new HRMService(repository);
        this.scanner = new Scanner(System.in);
    }
    //запуск меню
    public void start() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showAllEmployees();
                    break;
                case "2":
                    addEmployee();
                    break;
                case "3":
                    deleteEmployee();
                    break;
                case "4":
                    findEmployeeById();
                    break;
                case "5":
                    showStatistics();
                    break;
                case "6":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void printMenu() {
        System.out.println("\n=== HRM System Menu ===");
        System.out.println("1. Show all employees");
        System.out.println("2. Add employee");
        System.out.println("3. Delete employee");
        System.out.println("4. Find employee by ID");
        System.out.println("5. Show statistics");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private void showAllEmployees() {
        List<Employee> employees = service.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n=== Employee List ===");
        employees.forEach(System.out::println);
    }

    private void addEmployee() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter position: ");
            String position = scanner.nextLine();

            System.out.print("Enter salary: ");
            double salary = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter hire date (yyyy-MM-dd): ");
            LocalDate hireDate = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);

            Employee employee = service.addEmployee(name, position, salary, hireDate);
            System.out.println("Employee added successfully with ID: " + employee.getId());

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid salary format. Please enter a number.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private void deleteEmployee() {
        try {
            System.out.print("Enter employee ID to delete: ");
            Long id = Long.parseLong(scanner.nextLine());

            boolean deleted = service.deleteEmployee(id);
            if (deleted) {
                System.out.println("Employee with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid ID format. Please enter a number.");
        }
    }

    private void findEmployeeById() {
        try {
            System.out.print("Enter employee ID: ");
            Long id = Long.parseLong(scanner.nextLine());

            Optional<Employee> employee = service.findEmployeeById(id);
            if (employee.isPresent()) {
                System.out.println("\n=== Employee Found ===");
                System.out.println(employee.get());
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid ID format. Please enter a number.");
        }
    }

    private void showStatistics() {
        System.out.println(service.getStatistics());
    }

    public static void main(String[] args) {
        new HRMApplication().start();
    }
} 