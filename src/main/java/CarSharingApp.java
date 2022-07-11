import dao.CarDao;
import dao.CompanyDao;
import dao.impl.CarDaoImpl;
import dao.impl.CompanyDaoImpl;
import domain.Car;
import domain.Company;
import util.DataBaseUtil;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CarSharingApp {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final Scanner SCANNER = new Scanner(System.in);

    private final CompanyDao companyDao;
    private final CarDao carDao;

    public CarSharingApp(String[] args) {
        companyDao = new CompanyDaoImpl();
        carDao = new CarDaoImpl();
        DataBaseUtil.setDbUrl(args);
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            System.out.println("1. Log in as a manager");
            System.out.println("0. Exit");
            try {
                switch (Integer.parseInt(SCANNER.nextLine())) {
                    case 1:
                        logInAsManager();
                        break;
                    case 0:
                        companyDao.deleteAll();
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter number 0-1!");
            }
        }
    }

    private void logInAsManager() {
        while (true) {
            System.out.println("\n1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");
            try {
                switch (Integer.parseInt(SCANNER.nextLine())) {
                    case 1:
                        companyList();
                        break;
                    case 2:
                        addCompany();
                        break;
                    case 0:
                        System.out.println();
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter number 0-2!");
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addCompany() {
        System.out.println("\nEnter the company name:");
        String companyName = SCANNER.nextLine();
        Company company = new Company(companyName);
        companyDao.save(company);
        System.out.println("The company was created!");
    }

    private void companyList() {
        List<Company> companies = companyDao.getAll();
        if (companies.isEmpty())
            throw new IllegalStateException("\nThe company list is empty!");
        System.out.println("\nChoose the company:");
        companies.forEach(company -> System.out.println(company.getId() + ". " + company.getName()));
        System.out.println("0. Back");
        int userInput = Integer.parseInt(SCANNER.nextLine());
        if (userInput == 0)
            return;
        companies.stream()
                .filter(c -> c.getId().equals(userInput))
                .findFirst()
                .ifPresentOrElse(
                        this::companyLogin,
                        () -> {
                            throw new IllegalArgumentException("Не найдена компания с id: " + userInput);
                        });
    }

    private void companyLogin(Company company) {
        while (true) {
            System.out.println("\n'" + company.getName() + "' company");
            System.out.println("1. Car list");
            System.out.println("2. Create a car");
            System.out.println("0. Back");
            try {
                switch (Integer.parseInt(SCANNER.nextLine())) {
                    case 1:
                        carList(company);
                        break;
                    case 2:
                        addCar(company);
                        break;
                    case 0:
                        System.out.println();
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease write number 0-2!\n");
            } catch (IllegalStateException e) {
                if (!e.getMessage().equals(""))
                    System.out.println(e.getMessage());
            }
        }
    }

    private void carList(Company company) {
        List<Car> cars = carDao.getAllByCompany(company);
        if (cars.isEmpty())
            throw new IllegalStateException("\nThe car list is empty!");
        cars.forEach(car -> System.out.println(car.getId() + ". " + car.getName()));
    }

    private void addCar(Company company) {
        System.out.println("\nEnter the car name:");
        Car car = new Car(SCANNER.nextLine(), company);
        carDao.save(car);
        System.out.println("The car was added!");
    }
}
