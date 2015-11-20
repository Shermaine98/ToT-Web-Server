package Database;

import java.sql.Connection;

/**
 *
 * @author Atayan
 * @author Sy
 *
 */
public abstract class DBConnectionFactory {

    String url = "jdbc:mysql://127.0.0.1:3306/MOBIDEV";
    String username = "root";
    String password = "";

    /**
     *
     * @return
     */
    public static DBConnectionFactory getInstance() {
        return new DBConnectionFactoryImpl();
    }

    /**
     *
     * @return
     */
    public abstract Connection getConnection();
}
