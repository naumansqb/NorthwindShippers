package com.pluralsight.db;

import com.pluralsight.model.Shipper;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Shipper> getAllShippers() {
        List<Shipper> shippers = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM shippers");
            ResultSet resultSet = statement.executeQuery();
        ){
            while(resultSet.next()) {
                int shipperId = resultSet.getInt("ShipperID");
                String companyName = resultSet.getString("CompanyName");
                String phone = resultSet.getString("Phone");

                Shipper shipper = new Shipper(shipperId, companyName, phone);
                shippers.add(shipper);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shippers;
    }

    public int addShipper(String companyName, String phone) {
        int generatedId = -1;
        String sql = "INSERT INTO shippers (CompanyName, Phone) VALUES (?, ?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ){
            statement.setString(1, companyName);
            statement.setString(2, phone);
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next()) {
                generatedId = keys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedId;
    }

    public void updateShipperPhone(int shipperId, String newPhone) {
        String sql = "UPDATE shippers SET Phone = ? WHERE ShipperID = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, newPhone);
            statement.setInt(2, shipperId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteShipper(int shipperId) {
        String sql = "DELETE FROM shippers WHERE ShipperID = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setInt(1, shipperId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}