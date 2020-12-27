package es.uco.pw.business.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Sql query.
 */
public class SqlQuery {
    /**
     * Gets query.
     *
     * @param queryName the query name
     * @return the query
     * @throws IOException the io exception
     */
    public static String getQuery(String queryName) throws IOException {
        FileReader reader = new FileReader("sql.properties");
        Properties p = new Properties();
        p.load(reader);
        return p.getProperty(queryName);
    }
}
