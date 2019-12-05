package db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HSQLConnect {
	
	private static final String jdbcURL = "jdbc:hsqldb:file:G:\\5. Gestione Reparto\\CO2\\db\\hsql\\db;ifexists=true;hsqldb.lock_file=false";
	private static HikariDataSource ds;
	
	public static Connection connection() {
		Connection conn = null;
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(jdbcURL);
			config.setUsername("pws");
			config.setPassword("pws1234");		
			
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", "250");
			config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			
			ds = new HikariDataSource(config);
			conn = ds.getConnection();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
		return conn;
	}

}
