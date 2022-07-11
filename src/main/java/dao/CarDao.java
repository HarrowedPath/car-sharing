package dao;

import domain.Car;
import domain.Company;

import java.util.List;

public interface CarDao {
    void save (Car car);

    void deleteAll();

    void printAll();

    List<Car> getAllByCompany(Company company);

}
