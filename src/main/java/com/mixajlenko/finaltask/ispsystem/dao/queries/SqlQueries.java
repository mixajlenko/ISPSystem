package com.mixajlenko.finaltask.ispsystem.dao.queries;

public enum SqlQueries {

    ALL_SERVICE("SELECT * FROM \"SERVICE\""),

    SERVICE_BY_ID("SELECT * FROM \"SERVICE\" WHERE ID = ?"),

    SERVICE_BY_NAME("SELECT * FROM \"SERVICE\" WHERE NAME = ?"),

    ALL_USERS("SELECT * FROM \"USER\""),

    USERS_BY_ID("SELECT * FROM \"USER\" WHERE ID = ?"),

    ALL_PAYMENTS("SELECT * FROM \"PAYMENT\""),

    PAYMENTS_BY_ID("SELECT * FROM \"PAYMENT\" WHERE ID = ?"),

    ALL_TARIFFS("SELECT * FROM \"TARIFF\""),

    TARIFFS_BY_NAME("SELECT * FROM \"TARIFF\" WHERE NAME = ?"),

    TARIFFS_BY_ID("SELECT * FROM \"TARIFF\" WHERE ID = ?"),

    ALL_SERVICE_TARIFF("SELECT * FROM \"SERVICE_TARIFF\""),

    ALL_USER_TARIFF("SELECT * FROM \"USERS_PLAN\""),

    USER_TARIFF_BY_USER_ID("SELECT * FROM \"USERS_PLAN\" WHERE USER_ID = ?"),

    ALL_USER_TARIFF_BY_ID("SELECT * FROM \"USERS_PLAN\" WHERE ID = ?"),

    ALL_USER_TARIFF_BY_USER_ID("SELECT * FROM \"USERS_PLAN\" WHERE USER_ID = ?"),

    ALL_USER_TARIFF_BY_TARIFF_ID_USER_ID("SELECT * FROM \"USERS_PLAN\" WHERE TARIFF_ID = ? AND USER_ID = ?"),

    SELECT_USER_BY_NAME("SELECT * FROM \"USER\" WHERE FIRSTNAME = ?"),

    SELECT_USER_BY_EMAIL("SELECT * FROM \"USER\" WHERE EMAIL = ?"),

    SELECT_USER_BY_STATUS("SELECT * FROM \"USER\" WHERE STATUS = ?"),

    DELETE_FROM_SERVICE("DELETE FROM \"SERVICE\" WHERE ID = ?"),

    DELETE_FROM_USER("DELETE FROM \"USER\" WHERE ID = ?"),

    DELETE_FROM_USER_PLAN("DELETE FROM \"USERS_PLAN\" WHERE ID = ?"),

    DELETE_FROM_PAYMENT("DELETE FROM \"PAYMENT\" WHERE ID = ?"),

    DELETE_FROM_TARIFF("DELETE FROM \"TARIFF\" WHERE ID = ?"),

    DELETE_FROM_USER_PLAN_BY_USER_ID_TARIFF_ID("DELETE FROM \"USERS_PLAN\" WHERE USER_ID = ? AND TARIFF_ID = ?"),

    INSERT_SERVICE("INSERT INTO \"SERVICE\" VALUES (?,?)"),

    INSERT_USER("INSERT INTO \"USER\"(\"firstName\", phone, email, \"secondName\", wallet, status, password, role)  VALUES (?,?,?,?,?,?,?,?)"),

    INSERT_TARIFF("INSERT INTO \"TARIFF\"(name, description, price) VALUES (?,?,?)"),

    INSERT_PAYMENT("INSERT INTO \"PAYMENT\"(user_id, bill, status, balance, date, type) VALUES (?,?,?,?,?,?)"),

    INSERT_SERVICE_TARIFF("INSERT INTO \"SERVICE_TARIFF\"(service_id, tariff_id) VALUES(?,?)"),

    INSERT_USERS_PLAN("INSERT INTO \"USERS_PLAN\"(user_id, tariff_id, status, \"nextBill\", \"subDate\") VALUES(?,?,?,?,?)"),

    MAX_ID_SERVICE("SELECT MAX(ID) FROM \"SERVICE\""),

    UPDATE_SERVICE("UPDATE \"SERVICE\" SET NAME = ?, DESCRIPTION = ? WHERE ID = ?"),

    UPDATE_USER("UPDATE \"USER\" SET \"firstName\" = ?, \"secondName\" = ?, PHONE = ?, EMAIL = ?, WALLET = ?, ROLE = ?, STATUS = ?, PASSWORD = ?  WHERE ID = ?"),

    UPDATE_PAYMENT("update \"PAYMENT\" set bill = ?, status = ?, balance = ?, type = ?, date = ? where id = ?"),

    UPDATE_PAYMENT_BY_USER_ID("update \"PAYMENT\" set bill = ?, status = ?, balance = ?, date = ? where user_id = ?"),

    UPDATE_TARIFF("UPDATE \"TARIFF\" SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?"),

    UPDATE_USER_TARIFF("UPDATE \"USERS_PLAN\" SET USER_ID = ?, TARIFF_ID = ?, \"subDate\" = ?, STATUS = ?, \"nextBill\" = ? WHERE ID = ?"),

    UPDATE_USER_TARIFF_BY_TARIFF_ID("UPDATE \"USERS_PLAN\" SET USER_ID = ?, SET TARIFF_ID = ?, SET SUB_DATE = ?, SET STATUS = ?, \"nextBill\" = ? WHERE TARIFF_ID = ?"),

    GET_PAYMENTS_BY_USER_ID("SELECT * FROM \"PAYMENT\" WHERE user_id = ?");

    private final String constant;

    public String getConstant() {
        return constant;
    }

    SqlQueries(String constant) {
        this.constant = constant;
    }
}
