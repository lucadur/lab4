package com.entreprise.employee;

import com.entreprise.exception.InvalidEmployeeException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) throws InvalidEmployeeException {
        if(emp.getName() == null || emp.getName().trim().isEmpty()) {
            throw new InvalidEmployeeException("Le nom de l'employé ne peut pas être vide.");
        }
        if(emp.getSalary() < 0) {
            throw new InvalidEmployeeException("Le salaire ne peut pas être négatif.");
        }
        employees.add(emp);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employees.stream()
                .filter(e -> department.equalsIgnoreCase(e.getDepartment()))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesSortedBySalaryDesc() {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
    }


    public double getTotalSalaries() {
        return employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
    }

    public double getAverageSalary() {
        if(employees.isEmpty()) return 0.0;
        return getTotalSalaries() / employees.size();
    }

    public Optional<Employee> getHighestPaidEmployee() {
        return employees.stream()
                .reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2);
    }
}
