package com.mixajlenko.epam.finaltask.ispsystem.model.dao.queries;

public enum SqlQueries {

    ALL_SERVICE("SELECT * FROM \"SERVICE\""),

    ALL_USERS("SELECT * FROM \"USER\""),

    ALL_TARIFFS("SELECT * FROM \"TARIFF\""),

    COUNT_SERVICE_ROWS("SELECT count(id) FROM \"SERVICE\""),

    COUNT_USER_ROWS("SELECT count(id) FROM \"USER\""),

    COUNT_TARIFF_ROWS("SELECT count(id) FROM \"TARIFF\""),

    DELETE_FROM_SERVICE("DELETE FROM \"SERVICE\" WHERE ID = ?"),

    DELETE_FROM_USER("DELETE FROM \"USER\" WHERE ID = ?"),

    DELETE_FROM_TARIFF("DELETE FROM \"TARIFF\" WHERE ID = ?"),

    INSERT_SERVICE("INSERT INTO \"SERVICE\" VALUES (?,?,?)"),

    INSERT_USER("INSERT INTO \"USER\" VALUES (?,?,?,?)"),

    INSERT_TARIFF("INSERT INTO \"TARIFF\" VALUES (?,?,?,?)"),

    MAX_ID_SERVICE("SELECT MAX(ID) FROM \"SERVICE\""),

    UPDATE_SERVICE("UPDATE \"SERVICE\" SET NAME = ?, DESCRIPTION = ? WHERE ID = ?"),

    UPDATE_USER("UPDATE \"USER\" SET NAME = ?, PHONE = ?, EMAIL = ?  WHERE ID = ?"),

    UPDATE_TARIFF("UPDATE \"TARIFF\" SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?");

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
