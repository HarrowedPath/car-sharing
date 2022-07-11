import util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        CarSharingApp carSharingApp = new CarSharingApp(args);
        carSharingApp.run();
//        cleanup();
    }

    public static void cleanup() {
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement dropCar = connection.prepareStatement("DROP TABLE CAR;");
             PreparedStatement dropCompany = connection.prepareStatement("DROP TABLE COMPANY;")) {
            connection.setAutoCommit(true);
            dropCar.executeUpdate();
            dropCompany.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}