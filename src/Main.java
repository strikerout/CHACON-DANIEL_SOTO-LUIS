import org.apache.log4j.Logger;

import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        String create = "DROP TABLE IF EXISTS PRODUCTOS; CREATE TABLE PRODUCTOS(ID INT AUTO_INCREMENT PRIMARY KEY, NOMBRE VARCHAR(20) NOT NULL, TIPO VARCHAR(20) NOT NULL, PRECIO INT NOT NULL)";
        String insert = "INSERT INTO PRODUCTOS(NOMBRE, TIPO, PRECIO) VALUES ('JAIFON', 'APPLE', 2000), ('ETO NO E UN JAIFON', 'CHINCHUNWA', 800)";
        String select = "SELECT * FROM PRODUCTOS";

        Connection connection = null;
        try {
            connection = getConnection();

            Statement statement = connection.createStatement();
            statement.execute(create);
            statement.execute(insert);
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()){
                logger.info("Se obtuvo el producto: id:"+resultSet.getInt("id") + "-" + resultSet.getString("nombre") + "-" + resultSet.getString("tipo") + "-" + resultSet.getInt("precio"));
            }


        } catch (Exception exception) {
            logger.error("No chivio", exception);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
               logger.error(e.getMessage());
            }
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/testMeet", "sa", "sa");
    }
}