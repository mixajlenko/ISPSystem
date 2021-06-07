package com.mixajlenko.finaltask.ispsystem.dao.manager;

import com.mixajlenko.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.finaltask.ispsystem.dao.ITariffDao;
import com.mixajlenko.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.finaltask.ispsystem.dao.queries.SqlQueries;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class TariffDao implements ITariffDao {
    /* TODO fix code duplicates */

    private static Logger logger = Logger.getLogger(TariffDao.class);

    @Override
    public Tariff getById(Integer id) throws SQLException, NamingException {
        Tariff tariff = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SqlQueries.ALL_TARIFFS.getConstant())) {
            while (rs.next()) {
                if (rs.getInt(1) == id) {
                    tariff = new Tariff();
                    tariff.setId(id);
                    tariff.setName(rs.getString(2));
                    tariff.setDescription(rs.getString(3));
                    tariff.setPrice(rs.getInt(4));
                }
            }
        }
        return tariff;
    }

    @Override
    public List<Tariff> getAll() throws SQLException, NamingException {
        List<Tariff> tariffs = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQueries.ALL_TARIFFS.getConstant());
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                tariffs.add(getById(rs.getInt(1)));
            }
        }
        return tariffs;
    }

    @Override
    public boolean update(Tariff entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_TARIFF.getConstant())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getPrice());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_TARIFF.getConstant())) {
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }
        return true;
    }

    @Override
    public boolean add(Tariff entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_TARIFF.getConstant())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDouble(3, entity.getPrice());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }
        return true;
    }

    @Override
    public boolean setServiceTariff(int serviceId, int tariffId) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_SERVICE_TARIFF.getConstant())) {
            statement.setInt(1, serviceId);
            statement.setInt(2, getById(tariffId).getId());
            statement.executeUpdate();
            connection.commit();
        } catch (NamingException e) {
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }
        return true;
    }

    @Override
    public List<Tariff> getServiceTariff(int serviceId) throws SQLException, NamingException {
        List<Tariff> tariffs = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQueries.ALL_SERVICE_TARIFF.getConstant());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getInt(2) == serviceId) {
                    tariffs.add(getById(resultSet.getInt(3)));
                }
            }
        }
        return tariffs;
    }

    @Override
    public Tariff getByName(String name) throws NamingException, SQLException {
        Tariff tariff = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SqlQueries.ALL_TARIFFS.getConstant())) {
            while (rs.next()) {
                if (rs.getString(2).equals(name)) {
                    tariff = new Tariff();
                    tariff.setId(rs.getInt(1));
                    tariff.setName(rs.getString(2));
                    tariff.setDescription(rs.getString(3));
                    tariff.setPrice(rs.getInt(4));
                }
            }
        }
        return tariff;
    }


}
