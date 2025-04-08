package com.entreprise.employee;
import com.entreprise.exception.InvalidEmployeeException;

public class Employee {
    int id;
    String name;
    String department;
    double salary;

    public Employee(int id, String name, String department, double salary) throws InvalidEmployeeException{
        this.id = id;

        if (name.isEmpty()) {
            throw new InvalidEmployeeException("Veuillez renseignez un nom");
        } else {
            this.name = name;
        }

        if (department.isEmpty()) {
            throw new InvalidEmployeeException("Veuillez renseigner un departement");
        } else {
            this.department = department;
        }

        if (salary < 0) { throw new InvalidEmployeeException("Veuillez renseignez un salaire");
        } else {
            this.salary = salary;
        }

    }
    public String getName() {
        return name;
    }
    public void setName(String name) throws InvalidEmployeeException{
        if (name.isEmpty()) {
            throw new InvalidEmployeeException("Veuillez renseignez un nom");
        } else {
            this.name = name;
        }
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) throws InvalidEmployeeException {
        if (salary == 0) {
            throw new InvalidEmployeeException("Veuillez renseignez un salaire");
        } else {
            this.salary = salary;
        }
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) throws InvalidEmployeeException {
        if (department.isEmpty()) {
            throw new InvalidEmployeeException("Veuillez renseigner un departement");
        } else {
            this.department = department;
        }
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + "]";
    }
}
