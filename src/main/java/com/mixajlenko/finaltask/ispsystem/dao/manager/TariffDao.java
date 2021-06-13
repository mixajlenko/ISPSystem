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

    private static final Logger logger = Logger.getLogger(TariffDao.class);

    @Override
    public Tariff getById(Integer id) throws SQLException, NamingException {
        List<Tariff> tariffs;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SqlQueries.TARIFFS_BY_ID.getConstant())) {

            preparedStatement.setInt(1, id);

            var rs = preparedStatement.executeQuery();

            tariffs = initTariffList(rs);

        }
        return tariffs.get(0);
    }

    @Override
    public List<Tariff> getAll() throws SQLException, NamingException {
        List<Tariff> tariffs;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(SqlQueries.ALL_TARIFFS.getConstant())) {
            tariffs = initTariffList(rs);
        }
        return tariffs;
    }

    @Override
    public boolean update(Tariff entity) throws SQLException, NamingException {
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.UPDATE_TARIFF.getConstant())) {
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
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.DELETE_FROM_TARIFF.getConstant())) {
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
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.INSERT_TARIFF.getConstant())) {
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
        var connection = ConnectionFactory.getInstance().getConnection();
        try (var statement = connection.prepareStatement(SqlQueries.INSERT_SERVICE_TARIFF.getConstant())) {
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
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var statement = connection.prepareStatement(SqlQueries.ALL_SERVICE_TARIFF.getConstant());
             var resultSet = statement.executeQuery()) {
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
        List<Tariff> tariffs;
        try (var connection = ConnectionFactory.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SqlQueries.TARIFFS_BY_NAME.getConstant())) {
            preparedStatement.setString(1, name);
            var rs = preparedStatement.executeQuery();
            tariffs = initTariffList(rs);
        }
        return tariffs.get(0);
    }

    private List<Tariff> initTariffList(ResultSet rs) throws SQLException {
        List<Tariff> tariffs = new ArrayList<>();
        while (rs.next()) {
            var tariff = new Tariff();
            tariff.setId(rs.getInt(1));
            tariff.setName(rs.getString(2));
            tariff.setDescription(rs.getString(3));
            tariff.setPrice(rs.getInt(4));
            tariffs.add(tariff);
        }
        return tariffs;
    }

}
