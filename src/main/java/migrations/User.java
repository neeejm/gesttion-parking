package migrations;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexion.Connexion;

public class User extends MigrationModel {
    public User() {
        super();
        this.tableName = "_User";
        this.table = "create table " + this.tableName + " ("
                + "id serial primary key,"
                + "username varchar(20) unique,"
                + "email varchar(50),"
                + "is_admin boolean,"
                + "password varchar(32));";
    }

    public boolean setAdmin() {
        try {
            String req = "insert into " + this.tableName + " (username, email, is_admin, password) values (?, ?, ?, ?)";
            PreparedStatement pr = Connexion.getInstane().getConnection().prepareStatement(req);
            pr.setString(1, "admin");
            pr.setString(2, "admin@parking.no");
            pr.setBoolean(3, true);
            pr.setString(4, tools.Hash.hashPwd("admin"));
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
