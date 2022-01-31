package com.epam.ds.hostel.dao.connectionpool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.ShardingKey;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class ConnectionPool {
	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;
	private final static ConnectionPool instance = new ConnectionPool();
	

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	private ConnectionPool() {

		DBResourceManager dbResourceManager = DBResourceManager.getInstance();

		this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
		this.url = dbResourceManager.getValue(DBParameter.DB_URL);
		this.user = dbResourceManager.getValue(DBParameter.DB_USER);
		this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
		try {
			this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
		} catch (NumberFormatException e) {
			this.poolSize = 5;
		}
	}

	public static ConnectionPool getInstance() {
		
		return instance;
	}

	public void initPoolData() throws ConnectionPoolException {
		try {
			Class.forName(driverName);
			givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);

			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				PooledConnection pooledConnection = new PooledConnection(connection);
				connectionQueue.add(pooledConnection);
			}
			

		} catch (SQLException e) {
			throw new ConnectionPoolException(e);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException(e);
		}

	}

	public void dispose() {
		clearConnectionQueue();
		
	}

	public void clearConnectionQueue() {
		try {
			closeConnectionsQueue(givenAwayConQueue);
			closeConnectionsQueue(connectionQueue);
		} catch (SQLException e) {

		}

	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}

	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException(e);
		}
		return connection;
	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException {
		boolean wasException = false;
		Exception exception = null;

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				wasException = true;
				exception = e;
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				wasException = true;
				exception = e;
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new ConnectionPoolException(e);
			}
		}

		if (wasException) {
			throw new ConnectionPoolException(exception);
		}
	}

	public void closeConnection(Connection con, Statement st) throws ConnectionPoolException {
		boolean wasException = false;
		Exception exception = null;

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				exception = e;
				wasException = true;
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new ConnectionPoolException(e);
			}
		}

		if (wasException) {
			throw new ConnectionPoolException(exception);
		}
	}

	private class PooledConnection implements Connection {
		private Connection connection;

		public PooledConnection(Connection c) throws SQLException {
			this.connection = c;
			this.connection.setAutoCommit(true);
		}

		public void reallyClose() throws SQLException {
			connection.clearWarnings();
			connection.close();
		}

		public void close() throws SQLException {
			if (connection.isClosed()) {
				throw new SQLException("Attempting to close closed connection.");
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
			if (!givenAwayConQueue.remove(this)) {
				throw new SQLException("Error deleting connection from the given away connections pool");
			}

			if (!connectionQueue.offer(this)) {
				throw new SQLException("Error allocating connection in the pool.");
			}

		}

		public <T> T unwrap(Class<T> iface) throws SQLException {
			return connection.unwrap(iface);
		}

		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return connection.isWrapperFor(iface);
		}

		public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return connection.prepareStatement(sql);
		}

		public CallableStatement prepareCall(String sql) throws SQLException {
			return connection.prepareCall(sql);
		}

		public String nativeSQL(String sql) throws SQLException {
			return connection.nativeSQL(sql);
		}

		public void setAutoCommit(boolean autoCommit) throws SQLException {
			connection.setAutoCommit(autoCommit);
		}

		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		public void commit() throws SQLException {
			connection.commit();
		}

		public void rollback() throws SQLException {
			connection.rollback();
		}

		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

		public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);
		}

		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		public void setCatalog(String catalog) throws SQLException {
			connection.setCatalog(catalog);
		}

		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);
		}

		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		public void clearWarnings() throws SQLException {
			connection.clearWarnings();
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			connection.setTypeMap(map);
		}

		public void setHoldability(int holdability) throws SQLException {
			connection.setHoldability(holdability);
		}

		public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

		public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

		public Savepoint setSavepoint(String name) throws SQLException {
			return connection.setSavepoint(name);
		}

		public void rollback(Savepoint savepoint) throws SQLException {
			connection.rollback(savepoint);
		}

		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			connection.releaseSavepoint(savepoint);
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			return connection.prepareStatement(sql, columnIndexes);
		}

		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			return connection.prepareStatement(sql, columnNames);
		}

		public Clob createClob() throws SQLException {
			return connection.createClob();
		}

		public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

		public NClob createNClob() throws SQLException {
			return connection.createNClob();
		}

		public SQLXML createSQLXML() throws SQLException {
			return connection.createSQLXML();
		}

		public boolean isValid(int timeout) throws SQLException {
			return connection.isValid(timeout);
		}

		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			connection.setClientInfo(name, value);
		}

		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			connection.setClientInfo(properties);
		}

		public String getClientInfo(String name) throws SQLException {
			return connection.getClientInfo(name);
		}

		public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return connection.createArrayOf(typeName, elements);
		}

		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			return connection.createStruct(typeName, attributes);
		}

		public void setSchema(String schema) throws SQLException {
			connection.setSchema(schema);
		}

		public String getSchema() throws SQLException {
			return connection.getSchema();
		}

		public void abort(Executor executor) throws SQLException {
			connection.abort(executor);
		}

		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			connection.setNetworkTimeout(executor, milliseconds);
		}

		public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}

		public void beginRequest() throws SQLException {
			connection.beginRequest();
		}

		public void endRequest() throws SQLException {
			connection.endRequest();
		}

		public boolean setShardingKeyIfValid(ShardingKey shardingKey, ShardingKey superShardingKey, int timeout)
				throws SQLException {
			return connection.setShardingKeyIfValid(shardingKey, superShardingKey, timeout);
		}

		public boolean setShardingKeyIfValid(ShardingKey shardingKey, int timeout) throws SQLException {
			return connection.setShardingKeyIfValid(shardingKey, timeout);
		}

		public void setShardingKey(ShardingKey shardingKey, ShardingKey superShardingKey) throws SQLException {
			connection.setShardingKey(shardingKey, superShardingKey);
		}

		public void setShardingKey(ShardingKey shardingKey) throws SQLException {
			connection.setShardingKey(shardingKey);
		}

	}

}
