package me.size.database;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;


public class Database {

    private final DatabaseConfig config;
    private HikariDataSource datasource;
    private final JavaPlugin plugin;


    public Database(JavaPlugin plugin) {
        this.plugin = plugin;
        config = new DatabaseConfig(plugin);
    }


    public void connect() throws SQLException {
        FileConfiguration config = this.config.getFileConfiguration();
        createConnection(config.getString(DatabaseConfig.MYSQL_URL), config.getString(DatabaseConfig.MYSQL_DATABASE),
                config.getString(DatabaseConfig.MYSQL_USERNAME), config.getString(DatabaseConfig.MYSQL_PASSWORD),
                config.getInt(DatabaseConfig.MYSQL_POOLSIZE));
    }


    private void createConnection(String databaseURL, String databaseName, String username, String password, int poolsize)
            throws SQLException {
        plugin.getLogger().info("Connecting to MySQL Database '" + databaseURL + "/" + databaseName + "'...");

        datasource = new HikariDataSource();
        datasource.setMaximumPoolSize(poolsize);
        datasource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        datasource.addDataSourceProperty("url", "jdbc:mysql://" + databaseURL + "/" + databaseName);
        datasource.addDataSourceProperty("user", username);
        datasource.addDataSourceProperty("password", password);

        try (Connection connection = getConnection()) {
            plugin.getLogger().info("Connected to MySQL database!");
        }
    }


    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }


    public final void disconnect() {
        plugin.getLogger().info("Closing MySQL database connection...");
        if (datasource == null) {
            return;
        }
        if (datasource.isClosed()) {
            return;
        }
        datasource.close();
        plugin.getLogger().info("Closed MySQL database connection");
    }
}
