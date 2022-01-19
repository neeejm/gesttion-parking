package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.Connexion;
import dao.IDao;
import entities.Place;

public class PlaceService implements IDao<Place> {
    private final String tableName = "place";

    @Override
    public boolean create(Place o) {
        String sql = "insert into " + this.tableName + " (numero, etat, type, id_section) values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, o.getNumero());
            ps.setBoolean(2, o.getEtat());
            ps.setInt(3, o.getType());
            ps.setInt(4, o.getSection().getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean update(Place o) {
        String sql = "update " + this.tableName + " set numero=?, etat=?, type=?, id_section=? where id=?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, o.getNumero());
            ps.setBoolean(2, o.getEtat());
            ps.setInt(3, o.getType());
            ps.setInt(4, o.getSection().getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean delete(Place o) {
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
    public List<Place> findAll() {
        List<Place> users = new ArrayList<Place>();

        String sql = "select * from " + this.tableName;
        try {
            SectionService ss = new SectionService();
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new Place(rs.getInt("id"), rs.getInt("numero"), rs.getBoolean("etat"), rs.getInt("type"),
                        ss.findById(rs.getInt("id_section"))));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return users;
    }

    @Override
    public Place findById(int id) {
        String sql = "select * from " + this.tableName + " where id=?";
        try {
            SectionService ss = new SectionService();
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Place(rs.getInt("id"), rs.getInt("numero"), rs.getBoolean("etat"), rs.getInt("type"),
                        ss.findById(rs.getInt("id_section")));
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
    }

    public boolean updateEtat(Place o, boolean etat) {
        String sql = "update " + this.tableName + " set etat=? where id=?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setBoolean(1, etat);
            ps.setInt(2, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
}
