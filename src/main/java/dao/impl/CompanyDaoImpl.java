package dao.impl;

import dao.CompanyDao;
import domain.Company;
import util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    private final String initTable = "CREATE TABLE IF NOT EXISTS COMPANY (" +
            "ID INTEGER PRIMARY KEY AUTO_INCREMENT, " +
            "NAME VARCHAR(255) UNIQUE NOT NULL" +
            ");";

    private final String addCompany = "INSERT INTO COMPANY (NAME) VALUES (?);";

    private final String selectAll = "SELECT * FROM COMPANY;";

    private final String deleteAll = "DROP TABLE COMPANY;";

    public CompanyDaoImpl() {
        createTable();
    }

    private void createTable() {
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(initTable)) {
            connection.setAutoCommit(true);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Company company) {
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addCompany)) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteAll)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAll() {
        try (Connection connection = DataBaseUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(selectAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getString("ID"));
                System.out.println("company name: " + resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        try (
                Connection connection = DataBaseUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(selectAll);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                companies.add(new Company(
                        resultSet.getInt("ID"),
                        resultSet.getString("NAME")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

}
