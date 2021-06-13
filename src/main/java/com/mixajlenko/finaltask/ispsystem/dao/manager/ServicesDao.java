package com.mixajlenko.finaltask.ispsystem.dao.manager;

import com.mixajlenko.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.finaltask.ispsystem.dao.IServiceDao;
import com.mixajlenko.finaltask.ispsystem.dao.queries.SqlQueries;
import com.mixajlenko.finaltask.ispsystem.model.Service;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ServicesDao implements IServiceDao {

    private static final Logger logger = Logger.getLogger(ServicesDao.class);

    @Override
    public Service getById(Integer id) throws SQLException, NamingException {
        List<Service> services;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SqlQueries.SERVICE_BY_ID.getConstant())) {

            preparedStatement.setInt(1, id);

            var rs = preparedStatement.executeQuery();

            services = initServiceList(rs);

        }
        return services.get(0);
    }

    @Override
    public List<Service> getAll() throws SQLException, NamingException {
        List<Service> services;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQueries.ALL_SERVICE.getConstant());
             ResultSet rs = ps.executeQuery()) {
            services = initServiceList(rs);
        }
        return services;
    }

    @Override
    public boolean update(Service entity) throws SQLException, NamingException {
        var connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_SERVICE.getConstant())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getId());
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
        var connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_SERVICE.getConstant())) {
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
    public boolean add(Service entity) throws SQLException, NamingException {
        var connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_SERVICE.getConstant())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
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
    public Service getByName(String name) throws NamingException, SQLException {
        List<Service> services;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SqlQueries.SERVICE_BY_NAME.getConstant())) {

            preparedStatement.setString(1, name);

            var rs = preparedStatement.executeQuery();

            services = initServiceList(rs);

        }
        return services.get(0);
    }

    private List<Service> initServiceList(ResultSet rs) throws SQLException {
        List<Service> services = new ArrayList<>();
        while (rs.next()) {
            var service = new Service();
            service.setId(rs.getInt(1));
            service.setName(rs.getString(2));
            service.setDescription(rs.getString(3));
            services.add(service);
        }
        return services;
    }

}
