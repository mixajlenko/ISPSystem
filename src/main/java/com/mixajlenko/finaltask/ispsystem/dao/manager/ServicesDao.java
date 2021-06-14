package com.mixajlenko.finaltask.ispsystem.dao.manager;

import com.mixajlenko.finaltask.ispsystem.dao.connection.ConnectionFactory;
import com.mixajlenko.finaltask.ispsystem.dao.IServiceDao;
import com.mixajlenko.finaltask.ispsystem.dao.queries.SqlQueries;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceIdException;
import com.mixajlenko.finaltask.ispsystem.exception.NotFoundServiceNameException;
import com.mixajlenko.finaltask.ispsystem.model.Service;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import static com.mixajlenko.finaltask.ispsystem.dao.manager.crudconstants.Constants.*;

public class ServicesDao implements IServiceDao {

    private static final Logger logger = Logger.getLogger(ServicesDao.class);



    @Override
    public Service getById(Integer id) throws SQLException, NamingException, NotFoundServiceIdException {
        logger.info("getById with " + id + " argument start");
        Service service;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SqlQueries.SERVICE_BY_ID.getConstant())) {

            preparedStatement.setInt(1, id);

            var rs = preparedStatement.executeQuery();

            service = initServiceList(rs).get(0);

        } catch (SQLException e){
            logger.info("getById " + id + " Fail");
            throw new NotFoundServiceIdException();
        }
        logger.info("getById with " + id + SUCCESS);
        return service;
    }

    @Override
    public List<Service> getAll() throws SQLException, NamingException {
        logger.info("getAll" + START);
        List<Service> services;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQueries.ALL_SERVICE.getConstant());
             ResultSet rs = ps.executeQuery()) {

            services = initServiceList(rs);

        } catch (SQLException e) {
            logger.info("getAll fail. Return empty list");
            return Collections.emptyList();

        }

        logger.info("getAll" + SUCCESS);
        return services;

    }

    @Override
    public boolean update(Service entity) throws SQLException, NamingException {
        logger.info(UPDATE + entity + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_SERVICE.getConstant())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.info(UPDATE + entity + ROLLBACK);
            connection.rollback();
            return false;

        } finally {
            logger.info(CLOSE_CONNECTION);
            connection.close();
        }
        return true;

    }

    @Override
    public boolean delete(Integer id) throws SQLException, NamingException {
        logger.info(DELETE + id + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_SERVICE.getConstant())) {

            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            logger.info(DELETE + id + ROLLBACK);
            connection.rollback();
            return false;

        } finally {
            logger.info(CLOSE_CONNECTION);
            connection.close();
        }
        logger.info(DELETE + id + SUCCESS);
        return true;

    }

    @Override
    public boolean add(Service entity) throws SQLException, NamingException {
        logger.info(ADD + START);
        var connection = ConnectionFactory.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_SERVICE.getConstant())) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.info(ADD + ROLLBACK);
            connection.rollback();
            return false;
        } finally {
            logger.info(CLOSE_CONNECTION);
            connection.close();
        }
        logger.info(ADD + SUCCESS);
        return true;

    }

    @Override
    public Service getByName(String name) throws NamingException, SQLException, NotFoundServiceNameException {
        logger.info(GET_BY_NAME + name + START);
        Service service;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SqlQueries.SERVICE_BY_NAME.getConstant())) {

            preparedStatement.setString(1, name);

            var rs = preparedStatement.executeQuery();

            service = initServiceList(rs).get(0);

        } catch (SQLException e) {
            logger.info(GET_BY_NAME + name + " Fail");
            throw new NotFoundServiceNameException();
        }
        logger.info(GET_BY_NAME + name + SUCCESS);
        return service;

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
