package migrations;

public class Place extends MigrationModel {
        public Place() {
                super();
                this.tableName = "Place";
                this.table = "create table if not exists " + this.tableName + " ("
                                + "id serial primary key,"
                                + "numero int,"
                                + "etat boolean,"
                                + "type int,"
                                + "id_section int);";
                this.constraints.add("alter table " + this.tableName + " add "
                                + "constraint fk_section foreign key (id_section)"
                                + "references Section(id) on delete cascade");
        }
}
