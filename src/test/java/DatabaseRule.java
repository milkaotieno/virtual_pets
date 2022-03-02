
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.sql2o.*;

public class DatabaseRule implements AfterEachCallback, BeforeEachCallback {


    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/virtual_pets_test", null, null);  //Those with linux or windows use two strings for username and password
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        try(Connection con = DB.sql2o.open()) {
            String deletePersonsQuery = "DELETE FROM persons *;";
            con.createQuery(deletePersonsQuery).executeUpdate();
        }


    }
}
