package domain;

public class Company {
    private Integer id = 0;
    private final String name;

    public Company(String name) {
        id++;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
