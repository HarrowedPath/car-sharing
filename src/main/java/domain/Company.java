package domain;

import java.util.List;

public class Company {
    private Integer id;
    private final String name;
    private List<Car> cars; // "SELECT * FROM CAR WHERE COMPANY_ID = (?);"

    public Company(String name) {
        this.name = name;
    }

    public Company(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
