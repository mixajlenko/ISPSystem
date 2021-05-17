package com.mixajlenko.epam.finaltask.ispsystem.dao.manager;

import com.mixajlenko.epam.finaltask.ispsystem.dao.IAccountDao;
import com.mixajlenko.epam.finaltask.ispsystem.dao.queries.SqlQueries;
import com.mixajlenko.epam.finaltask.ispsystem.model.Account;
import com.mixajlenko.epam.finaltask.ispsystem.dao.connection.ConnectionFactory;

import javax.naming.NamingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDao implements IAccountDao {

    @Override
    public Account getById(Integer id) throws SQLException, NamingException {
        Account account = null;
        Connection connection = ConnectionFactory.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(SqlQueries.ALL_ACCOUNTS.getConstant())) {
                while (rs.next()) {
                    if (rs.getInt(1) == id) {
                        account = new Account();
                        account.setId(id);
                        account.setUserId(rs.getInt(2));
                        account.setStatus(rs.getInt(3));
                        account.setStatus(rs.getInt(4));
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
        }
        return account;
    }

    @Override
    public List<Account> getAll() throws SQLException, NamingException {
        List<Account> accounts = new ArrayList<>();
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement ps = connection.prepareStatement(SqlQueries.ALL_ACCOUNTS.getConstant())) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Account account = new Account();
                    account.setId(rs.getInt(1));
                    account.setUserId(rs.getInt(2));
                    account.setStatus(rs.getInt(3));
                    account.setWallet(rs.getInt(4));
                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.WARNING, e.getMessage(), e);
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return accounts;
    }

    @Override
    public boolean update(Account entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_ACCOUNT.getConstant())) {
            statement.setInt(1, entity.getId());
            statement.setInt(2, entity.getUserId());
            statement.setInt(3, entity.getStatus());
            statement.setInt(4, entity.getWallet());
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
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.DELETE_FROM_ACCOUNT.getConstant())) {
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
    public boolean add(Account entity) throws SQLException, NamingException {
        Connection connection = ConnectionFactory.getInstance().getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_SERVICE.getConstant());
             ResultSet ids = connection.createStatement().executeQuery(SqlQueries.COUNT_ACCOUNT_ROWS.getConstant())) {
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
            statement.setInt(2, entity.getUserId());
            statement.setInt(3, entity.getStatus());
            statement.setInt(4, entity.getWallet());
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
    public boolean encryptPass(Account account) throws NoSuchAlgorithmException, SQLException, NamingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(account.getPassword().getBytes());
        byte[] hash = digest.digest();
        StringBuilder result = new StringBuilder();
        for (byte aByte : hash) {
            result.append(String.format("%02X", aByte));
        }
        account.setPassword(String.valueOf(result));
        update(account);
        return true;
    }
}
