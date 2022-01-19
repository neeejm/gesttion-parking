package migrations;

public class Reservation extends MigrationModel {
        public Reservation() {
                super();
                this.tableName = "Reservation";
                this.table = "create table if not exists " + this.tableName + " ("
                                + "id serial primary key,"
                                + "id_user int,"
                                + "id_place int,"
                                + "date_in timestamp,"
                                + "date_out timestamp,"
                                + "matricule varchar(10));";
                this.constraints.add("alter table " + this.tableName + " add "
                                + "constraint fk_res_user foreign key (id_user)"
                                + "references _User(id) on delete cascade");
                this.constraints.add("alter table " + this.tableName + " add "
                                + "constraint fk_res_place foreign key (id_place)"
                                + "references Place(id) on delete cascade");
        }
}
