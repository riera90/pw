package es.uco.pw.business.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SqlQuery {
    public static String getQuery(String queryName) throws IOException {
        FileReader reader = new FileReader("sql.properties");
        Properties p = new Properties();
        p.load(reader);
        return p.getProperty(queryName);
    }
}
