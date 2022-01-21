package migrations;

public class Section extends MigrationModel {
    public Section() {
        super();
        this.tableName = "Section";
        this.table = "create table if not exists " + this.tableName + " ("
                + "id serial primary key,"
                + "code varchar(5) unique);";
    }
}
