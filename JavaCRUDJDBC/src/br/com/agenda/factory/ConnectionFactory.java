package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	// Nome do usuário do mysql

	private static final String USERNAME = "root";

	// Senha do banco
	private static final String PASSWORD = "159753";

	// Caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

	// conexão com o banco de dados

	public static Connection createConnectionToMySql() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

		return connection;

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Connection con = createConnectionToMySql();

		if (con != null) {
			System.out.println("Conexão obtida com sucesso");
			con.close();
		}

	}
}
