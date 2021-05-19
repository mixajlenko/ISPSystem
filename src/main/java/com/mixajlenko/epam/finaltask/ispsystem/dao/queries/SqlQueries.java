package com.mixajlenko.epam.finaltask.ispsystem.dao.queries;

public enum SqlQueries {

    ALL_SERVICE("SELECT * FROM \"SERVICE\""),

    ALL_USERS("SELECT * FROM \"USER\""),

    ALL_TARIFFS("SELECT * FROM \"TARIFF\""),

    ALL_ACCOUNTS("SELECT * FROM \"ACCOUNT\""),

    ALL_SERVICE_TARIFF("SELECT * FROM \"SERVICE_TARIFF\""),

    SELECT_USER_BY_NAME("SELECT * FROM \"USER\" WHERE FIRSTNAME = ?"),

    SELECT_USER_BY_EMAIL("SELECT * FROM \"USER\" WHERE EMAIL = ?"),

    SELECT_USER_BY_STATUS("SELECT * FROM \"USER\" WHERE STATUS = ?"),

    COUNT_SERVICE_ROWS("SELECT count(id) FROM \"SERVICE\""),

    COUNT_USER_ROWS("SELECT count(id) FROM \"USER\""),

    COUNT_TARIFF_ROWS("SELECT count(id) FROM \"TARIFF\""),

    COUNT_ACCOUNT_ROWS("SELECT count(id) FROM \"ACCOUNT\""),

    COUNT_SERVICE_TARIFF_ROWS("SELECT count(id) FROM \"SERVICE_TARIFF\""),

    COUNT_USERS_PLAN_ROWS("SELECT count(id) FROM \"USERS_PLAN\""),

    DELETE_FROM_SERVICE("DELETE FROM \"SERVICE\" WHERE ID = ?"),

    DELETE_FROM_USER("DELETE FROM \"USER\" WHERE ID = ?"),

    DELETE_FROM_TARIFF("DELETE FROM \"TARIFF\" WHERE ID = ?"),

    DELETE_FROM_ACCOUNT("DELETE FROM \"ACCOUNT\" WHERE ID = ?"),

    INSERT_SERVICE("INSERT INTO \"SERVICE\" VALUES (?,?,?)"),

    INSERT_ACCOUNT("INSERT INTO \"ACCOUNT\"(user_id, status, wallet, password, role) VALUES (?,?,?,?,?)"),

    INSERT_USER("INSERT INTO \"USER\"(\"firstName\",\"secondName\", phone, email, role_id) VALUES (?,?,?,?,?)"),

    INSERT_TARIFF("INSERT INTO \"TARIFF\" VALUES (?,?,?,?)"),

    INSERT_SERVICE_TARIFF("INSERT INTO \"SERVICE_TARIFF\" VALUES(?,?,?)"),

    INSERT_USERS_PLAN("INSERT INTO \"USERS_PLAN\" VALUES(?,?,?,?)"),

    MAX_ID_SERVICE("SELECT MAX(ID) FROM \"SERVICE\""),

    UPDATE_SERVICE("UPDATE \"SERVICE\" SET NAME = ?, DESCRIPTION = ? WHERE ID = ?"),

    UPDATE_USER("UPDATE \"USER\" SET FIRSTNAME = ?, SECONDNAME = ?, PHONE = ?, EMAIL = ?, role_id = ?  WHERE ID = ?"),

    UPDATE_TARIFF("UPDATE \"TARIFF\" SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?"),

    UPDATE_ACCOUNT("UPDATE \"ACCOUNT\" SET ID_USER = ?, STATUS = ?, WALLET = ?, ROLE = ? WHERE ID = ?");


//    ALL_USERS("SELECT * FROM USERS"),
//
//    ALL_TEAMS("SELECT * FROM TEAMS"),
//
//
//    INSERT_TEAM("INSERT INTO TEAMS VALUES (?,?)"),
//
//    INSERT_USER_TEAMS("INSERT INTO USERS_TEAMS VALUES (?,?)"),
//
//
//    MAX_ID_TEAM("SELECT MAX(ID) FROM TEAMS"),
//
//    FIND("SELECT ID, NAME FROM TEAMS WHERE ID IN (SELECT TEAM_ID FROM USERS_TEAMS WHERE USER_ID = ?)");

    private final String constant;

    public String getConstant() {
        return constant;
    }

    SqlQueries(String constant) {
        this.constant = constant;
    }
}
