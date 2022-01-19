package migrations;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MigrationModel {
    protected String table;
    protected String tableName;
    protected List<String> constraints;

    public MigrationModel() {
        this.constraints = new ArrayList<>();
    }

    public void run(Statement st) {
        try {
            st.executeUpdate("drop table if exists " + this.tableName);
            st.executeUpdate(this.table);
            for (String constraint : this.constraints) {
                // System.out.println(this.constraints.size());
                st.execute(constraint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
