package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	// CRUD: Create/Read/Update/Delete

	public void save(Contato contato) {

		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySql();

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			pstm.execute();

			System.out.println("Contato salvo com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void update(Contato contato) throws SQLException {

		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// criando conexão com o banco

			conn = ConnectionFactory.createConnectionToMySql();

			pstm = conn.prepareStatement(sql);

			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			// Qal o id do registro que desja atualizar?
			pstm.setInt(4, contato.getId());

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void delete(int id) throws ClassNotFoundException, SQLException {

		String sql = "DELETE FROM contatos WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySql();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	public List<Contato> getContatos() throws SQLException {

		String sql = "SELECT * FROM contatos";

		List<Contato> contatos = new ArrayList<>();

		Connection conn = null;

		PreparedStatement pstm = null;

		// Interface que vai recuperar os dados do banco; **select**
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySql();

			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				// cria uma nova instancia de contato para cada contato no banco.
				Contato contato = new Contato();

				// recuperar informações
				contato.setId(rset.getInt("id"));

				contato.setNome(rset.getString("nome"));

				contato.setIdade(rset.getInt("idade"));

				contato.setDataCadastro(rset.getDate("dataCadastro"));

				contatos.add(contato);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}

				if (rset != null) {
					rset.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return contatos;
	}
}
