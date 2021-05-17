package com.mixajlenko.epam.finaltask.ispsystem.dao.manager;

import com.mixajlenko.epam.finaltask.ispsystem.connection.ConnectionFactory;
import com.mixajlenko.epam.finaltask.ispsystem.dao.ITariffDao;
import com.mixajlenko.epam.finaltask.ispsystem.model.Tariff;
import com.mixajlenko.epam.finaltask.ispsystem.dao.queries.SqlQueries;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TariffDao implements ITariffDao {

    @Override
    public Tariff getById(Integer id) throws SQLException, NamingException {
        Tariff tariff = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(SqlQueries.ALL_TARIFFS.getConstant())) {
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
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
        }
        return tariff;
    }

    @Override
    public List<Tariff> getAll() throws SQLException, NamingException {
        List<Tariff> tariffs = new ArrayList<>();
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(SqlQueries.ALL_TARIFFS.getConstant())) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tariffs.add(getById(rs.getInt(1)));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return tariffs;
    }

    @Override
    public boolean update(Tariff entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_TARIFF.getConstant())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDouble(3, entity.getPrice());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_TARIFF.getConstant())) {
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public boolean add(Tariff entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_TARIFF.getConstant());
             ResultSet ids = connection.createStatement().executeQuery(SqlQueries.COUNT_TARIFF_ROWS.getConstant())) {
            int maxId = 1;
            while (ids.next()) {
                maxId = ids.getInt(1);
            }
            for (int i = 1; i < maxId; i++) {
                if (getById(i) == null) {
                    entity.setId(i);
                    break;
                }
            }
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getDescription());
            statement.setDouble(4, entity.getPrice());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public boolean setServiceTariff(int serviceId, int tariffId) {
        return false;
    }

    @Override
    public boolean getServiceTariff(int serviceId) {
        return false;
    }
}
