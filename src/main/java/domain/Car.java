package domain;

public class Car {
    private Integer id;
    private final String name;
    private final Company company;

    public Car(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    public Car(Integer id, String name, Company company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
