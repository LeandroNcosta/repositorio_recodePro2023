package com.agencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.agencia.model.ClientePassagem;
import com.agencia.utils.Colors;

public class ClientePassagemDAO {

	private static Connection conn;
	// private static Connection conn = Database.createConnection();
	private static String sql;
	private static ResultSet rset = null;

	public ClientePassagemDAO(Connection conn) {
		this.conn = conn;
	}

	public static void create(ClientePassagem clientePassagem) {
		sql = "INSERT INTO clientePassagem(id, dataCompra, idCliente, idPassagem) VALUES(null, ?, ?, ?);";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setTimestamp(1, new java.sql.Timestamp(clientePassagem.getDataCompra().getTime()));
			stmt.setInt(2, clientePassagem.getCliente().getId());
			stmt.setInt(3, clientePassagem.getPassagem().getId());

			stmt.executeUpdate();

			System.out.println(Colors.GREEN + "--ClientePassagem criado com sucesso" + Colors.RESET);
		} catch (SQLException e) {
			System.out.println("--Erro ao criar clientePassagem, Mensagem: " + e.getMessage());
		} finally {

		}
	}

	public static List<ClientePassagem> read(String pesquisa) {
		sql = String.format("SELECT * FROM clientePassagem WHERE nome LIKE '%s%%' OR cpf LIKE '%s%%'", pesquisa,
				pesquisa);
		List<ClientePassagem> passagens = new ArrayList<ClientePassagem>();

		try (Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				ClientePassagem ClientePassagem = new ClientePassagem();

				ClientePassagem.setId(resultSet.getInt("id"));
				ClientePassagem.setDataCompra(resultSet.getDate("dataCompra"));
				ClientePassagem.setCliente(ClienteDAO.findBy(resultSet.getInt("idCliente")));
				ClientePassagem.setPassagem(PassagemDAO.findBy(resultSet.getInt("idPassagem")));

				passagens.add(ClientePassagem);

				// stmt.setTimestamp(3, new
				// java.sql.Timestamp(consulta.getDataConsulta().getTime()));
			}
			System.out.println(Colors.GREEN + "--Resultado retornado com sucesso" + Colors.RESET);
			System.out.println("");
			return passagens;
		} catch (SQLException e) {
			System.out.println("--Não foi possíevl ler os dados da tabela clientePassagem. Message: " + e.getMessage());
			return null;
		}
	}

	public static void update(ClientePassagem clientePassagem) {
		sql = "UPDATE clientePassagem SET dataCompra = ?, idCliente = ?. idPassagem = ? WHERE id = ? LIMIT 1";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setTimestamp(1, new java.sql.Timestamp(clientePassagem.getDataCompra().getTime()));
			stmt.setInt(2, clientePassagem.getCliente().getId());
			stmt.setInt(3, clientePassagem.getPassagem().getId());
			stmt.setInt(3, clientePassagem.getId());

			stmt.executeUpdate();

			System.out.printf(Colors.GREEN + "--ClientePassagem atualizada" + Colors.RESET);

		} catch (SQLException e) {
			System.out.printf("--Erro ao atualizar clientePassagem com o id : %d, Mensagem: %n",
					clientePassagem.getId(), e.getMessage());

		} finally {

		}
	}

	public static void delete(int id) {
		sql = "DELETE FROM clientePassagem WHERE id = ? LIMIT 1";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);

			stmt.executeUpdate();

			System.out.println(Colors.GREEN + "--ClientePassagem foi deletado com sucesso." + Colors.RESET);

		} catch (SQLException e) {
			System.out.printf(Colors.RED + "--Erro ao deletar clientePassagem com o id : %d, Mensagem: %n", id,
					e.getMessage() + Colors.RED);

		} finally {

		}
	}

	public static ClientePassagem findBy(int clientePassagemId) {
		sql = String.format("SELECT * FROM clientePassagem WHERE id = %d or cpf = %s", clientePassagemId,
				clientePassagemId);
		ClientePassagem clientePassagem = new ClientePassagem();

		try (Statement statement = conn.createStatement()) {
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				clientePassagem.setId(resultSet.getInt("id"));
				clientePassagem.setDataCompra(resultSet.getDate("dataCompra"));
				clientePassagem.setCliente(ClienteDAO.findBy(resultSet.getInt("idCliente")));
				clientePassagem.setPassagem(PassagemDAO.findBy(resultSet.getInt("idPassagem")));

			}

			System.out.println(Colors.GREEN + "--Encontrado ClientePassagem com sucesso" + Colors.RESET);

			return clientePassagem;
		} catch (SQLException e) {
			System.out.println("--Não foi possível encontrar o ClientePassagem informado. Message: " + e.getMessage());
			return null;
		}

	}

	public void fecharConexao() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}