package dao;

import domain.Company;

import java.util.List;

public interface CompanyDao {
    void save (Company company);

    void deleteAll();

    void printAll();

    List<Company> getAll();
}
