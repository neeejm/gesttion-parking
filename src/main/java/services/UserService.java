package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDao;
import entities.User;

public class UserService implements IDao<User> {
    private final String tableName = "_user";

    @Override
    public boolean create(User o) {
        String sql = "insert into " + this.tableName + " (username, email, is_admin, password) values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1, o.getUsername());
            ps.setString(2, o.getEmail());
            ps.setBoolean(3, o.isAdmin());
            ps.setString(4, tools.Hash.hashPwd(o.getPassword()));
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean update(User o) {
        String sql = "update " + this.tableName + " set username=?, email=?, password=? where id=?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1, o.getUsername());
            ps.setString(2, o.getEmail());
            ps.setString(3, o.getPassword());
            ps.setInt(4, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean delete(User o) {
        String sql = "delete from " + this.tableName + " where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("delete : erreur sql : " + e.getMessage());

        }
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();

        String sql = "select * from " + this.tableName + " where is_admin=0";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                        rs.getString("password")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return users;
    }

    @Override
    public User findById(int id) {
        String sql = "select * from " + this.tableName + " where id=?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
                        rs.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
    }

    public boolean login(String username, String password) {
        String sql = "select * from " + this.tableName + " where username=? and password=?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, tools.Hash.hashPwd(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return false;
    }

}
