import com.entreprise.employee.Employee;
import com.entreprise.employee.EmployeeService;
import com.entreprise.exception.InvalidEmployeeException;
import com.entreprise.util.EmployeeDataLoader;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        Scanner scanner = new Scanner(System.in);

        try {
            List<Employee> loadedEmployees = EmployeeDataLoader.loadEmployees("employees.csv");
            for (Employee emp : loadedEmployees) {
                service.addEmployee(emp);
            }
            System.out.println("Données chargées avec succès.");
        } catch (IOException | InvalidEmployeeException e) {
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
        }

        int choix;
        do {
            afficherMenu();
            choix = Integer.parseInt(scanner.nextLine());
            switch (choix) {
                case 1:
                    try {
                        System.out.println("Entrer l'ID : ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Entrer le Nom : ");
                        String name = scanner.nextLine();
                        System.out.println("Entrer le Département : ");
                        String dept = scanner.nextLine();
                        System.out.println("Entrer le Salaire : ");
                        double salary = Double.parseDouble(scanner.nextLine());
                        service.addEmployee(new Employee(id, name, dept, salary));
                        System.out.println("Employé ajouté avec succès.");
                    } catch (InvalidEmployeeException ex) {
                        System.out.println("Erreur : " + ex.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Liste des employés : ");
                    service.getAllEmployees().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Entrer le département à filtrer : ");
                    String dept = scanner.nextLine();
                    List<Employee> filtres = service.getEmployeesByDepartment(dept);
                    if (filtres.isEmpty()) {
                        System.out.println("Aucun employé trouvé pour le département " + dept);
                    } else {
                        filtres.forEach(System.out::println);
                    }
                    break;
                case 4:
                    System.out.println("Employés triés par salaire décroissant : ");
                    service.getEmployeesSortedBySalaryDesc().forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Le total des salaires est : " + service.getTotalSalaries());
                    break;
                case 6:
                    Optional<Employee> highestPaid = service.getHighestPaidEmployee();
                    if (highestPaid.isPresent()) {
                        System.out.println("L'employé le mieux payé : " + highestPaid.get());
                    } else {
                        System.out.println("Aucun employé trouvé.");
                    }
                    break;
                case 7:
                    System.out.println("Quitter l'application.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        } while (choix != 7);

        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\nMenu :");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Afficher tous les employés");
        System.out.println("3. Filtrer par département");
        System.out.println("4. Trier par salaire décroissant");
        System.out.println("5. Calculer le total des salaires");
        System.out.println("6. Afficher l'employé le mieux payé");
        System.out.println("7. Quitter");
        System.out.print("Choix : ");
    }
}
