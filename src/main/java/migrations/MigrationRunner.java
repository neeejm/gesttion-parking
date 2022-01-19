package migrations;

import java.sql.SQLException;
import java.sql.Statement;

import connexion.Connexion;

public class MigrationRunner {
    public static void main(String[] args) {
        System.out.println("Run migrations...");

        System.out.println("*");

        try {
            Statement st = Connexion.getInstane().getConnection().createStatement();

            System.out.println("Creating migration user: start");
            User user = new User();
            user.run(st);
            user.setAdmin();
            System.out.println("Creating migration user: end");

            System.out.println("*");

            System.out.println("Creating migration section: start");
            Section section = new Section();
            section.run(st);
            System.out.println("Creating migration section: end");

            System.out.println("*");

            System.out.println("Creating migration place: start");
            Place place = new Place();
            place.run(st);
            System.out.println("Creating migration place: end");

            System.out.println("*");

            System.out.println("Creating migration reservation: start");
            Reservation res = new Reservation();
            res.run(st);
            System.out.println("Creating migration resrevation: end");

            Connexion.getInstane().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("*");

        System.out.println("Migrations ended...");
    }
}
