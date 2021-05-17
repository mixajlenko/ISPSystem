package com.mixajlenko.epam.finaltask.ispsystem.dao.manager;

import com.mixajlenko.epam.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.epam.finaltask.ispsystem.dao.IServiceDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.queries.SqlQueries;
import com.mixajlenko.epam.finaltask.ispsystem.model.Service;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServicesDao implements IServiceDao {

    @Override
    public Service getById(Integer id) throws SQLException, NamingException {
        Service service = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(SqlQueries.ALL_SERVICE.getConstant())) {
                while (rs.next()) {
                    if (rs.getInt(1) == id) {
                        service = new Service();
                        service.setId(id);
                        service.setName(rs.getString(2));
                        service.setDescription(rs.getString(3));
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
        }
        return service;
    }

    @Override
    public List<Service> getAll() throws SQLException, NamingException {
        List<Service> images = new ArrayList<>();
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(SqlQueries.ALL_SERVICE.getConstant())) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Service service = new Service();
                    service.setId(rs.getInt(1));
                    service.setName(rs.getString(2));
                    service.setDescription(rs.getString(3));
                    images.add(service);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return images;
    }

    @Override
    public boolean update(Service entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_SERVICE.getConstant())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getId());
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
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_SERVICE.getConstant())) {
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
    public boolean add(Service entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_SERVICE.getConstant());
             ResultSet ids = connection.createStatement().executeQuery(SqlQueries.COUNT_SERVICE_ROWS.getConstant())) {
            int maxId = 1;
            int ddd = 0;
            while (ids.next()) {
                maxId = ids.getInt(1);
            }
            for (int i = 1; i <= maxId; i++) {
                if (getById(i) == null) {
                    entity.setId(i);
                    break;
                } else {
                    ddd = i;
                }
            }
            if (ddd == maxId) {
                entity.setId(maxId + 1);
            }
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getDescription());
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
}
