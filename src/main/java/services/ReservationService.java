package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connexion.Connexion;
import dao.IDao;
import entities.Reservation;

public class ReservationService implements IDao<Reservation> {
    private final String tableName = "reservation";

    @Override
    public boolean create(Reservation o) {
        String sql = "insert into " + this.tableName
                + " (id_user, id_place, date_in, matricule) values (?, ?, now(), ?)";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            PlaceService pls = new PlaceService();
            pls.updateEtat(o.getPlace(), true);
            ps.setInt(1, o.getUser().getId());
            ps.setInt(2, o.getPlace().getId());
            // ps.setDate(3, (java.sql.Date) new Date());
            ps.setString(3, o.getMatricule());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean update(Reservation o) {
        String sql = "update " + this.tableName + " set matricule=? where id_user=?, id_place=?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1, o.getMatricule());
            ps.setInt(2, o.getUser().getId());
            ps.setInt(3, o.getPlace().getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean delete(Reservation o) {
        String sql = "delete from " + this.tableName + " where id_user=? and id_place=?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, o.getUser().getId());
            ps.setInt(2, o.getUser().getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("delete : erreur sql : " + e.getMessage());

        }
        return false;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> res = new ArrayList<>();

        String sql = "select * from " + this.tableName;
        try {

            UserService us = new UserService();
            PlaceService pls = new PlaceService();
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Reservation(rs.getInt("id"), us.findById(rs.getInt("id_user")),
                        pls.findById(rs.getInt("id_place")),
                        rs.getDate("date_in"), rs.getDate("date_out"), rs.getString("matricule")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return res;
    }

    public List<Reservation> findAllByUserId(int id) {
        List<Reservation> res = new ArrayList<>();

        String sql = "select * from " + this.tableName + " where id_user = ?";
        try {

            UserService us = new UserService();
            PlaceService pls = new PlaceService();
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new Reservation(rs.getInt("id"), us.findById(rs.getInt("id_user")),
                        pls.findById(rs.getInt("id_place")),
                        rs.getDate("date_in"), rs.getDate("date_out"), rs.getString("matricule")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return res;
    }

    @Override
    public Reservation findById(int id) {
        String sql = "select * from " + this.tableName + " where id_place=?";
        try {
            UserService us = new UserService();
            PlaceService pls = new PlaceService();
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Reservation(rs.getInt("id"), us.findById(rs.getInt("id_user")),
                        pls.findById(rs.getInt("id_place")),
                        rs.getDate("date_in"), rs.getDate("date_out"), rs.getString("matricule"));
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
    }

    public Reservation findById(int id, int pid) {
        String sql = "select * from " + this.tableName + " where id_user=? and id_place=?";
        try {
            UserService us = new UserService();
            PlaceService pls = new PlaceService();
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, pid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Reservation(rs.getInt("id"), us.findById(rs.getInt("id_user")),
                        pls.findById(rs.getInt("id_place")),
                        rs.getDate("date_in"), rs.getDate("date_out"), rs.getString("matricule"));
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
    }

    public boolean cancel(Reservation o) {
        String sql = "update " + this.tableName + " set date_out=now() where id_place=?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            PlaceService pls = new PlaceService();
            pls.updateEtat(o.getPlace(), false);
            ps.setInt(1, o.getPlace().getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public Map<? extends String, ? extends Integer> reservationBySection() {
        HashMap<String, Integer> stats = new HashMap<>();
        String sql = "select count(*), s.code from public.reservation r, public.place p, public.section s where r.id_place = p.id and p.id_section = s.id group by s.code";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stats.put(rs.getString("code"), rs.getInt("count"));
            }

        } catch (SQLException e) {
            System.out.println("stats " + e.getMessage());
        }
        return stats;
    }

    public int count() {
        String sql = "select count(*) from " + this.tableName;
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }

        } catch (SQLException e) {
            System.out.println("stats " + e.getMessage());
        }
        return 0;
    }
}
