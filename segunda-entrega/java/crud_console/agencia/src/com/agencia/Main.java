package com.agencia;

import java.text.ParseException;

import java.util.Scanner;

import com.agencia.dao.ClienteDAO;
import com.agencia.dao.ClientePacoteDAO;
import com.agencia.dao.ClientePassagemDAO;
import com.agencia.dao.DestinoDAO;
import com.agencia.dao.HospedagemDAO;
import com.agencia.dao.PacoteDAO;
import com.agencia.dao.PassagemDAO;
import com.agencia.database.Database;
import com.agencia.menus.MenuCliente;
import com.agencia.menus.MenuPacote;
import com.agencia.menus.MenuPagamento;
import com.agencia.menus.MenuPassagem;
import com.agencia.utils.Colors;

// CRUD APENAS PARA 4 CLASSES / TABELAS

public class Main {

	public static void main(String[] args) throws ParseException {

		Scanner input = new Scanner(System.in);
		var connection = Database.createConnection();
		ClienteDAO clienteDAO = new ClienteDAO(connection);
		HospedagemDAO hospedagemDAO = new HospedagemDAO(connection);
		PassagemDAO passagemDAO = new PassagemDAO(connection);
		PacoteDAO pacoteDAO = new PacoteDAO(connection);
		DestinoDAO destinoDAO = new DestinoDAO(connection);
		ClientePacoteDAO cp = new ClientePacoteDAO(connection);
		ClientePassagemDAO cpassagem = new ClientePassagemDAO(connection);

		int opcao = 0;
		int subOpcao = 0;

		System.out.println(
				Colors.BLUE_BOLD_BRIGHT + "                   __      ___                  __  __       _     \r\n"
						+ "                   \\ \\    / (_)                |  \\/  |     (_)    \r\n"
						+ "                    \\ \\  / / _  __ _  __ _  ___| \\  / | __ _ _ ___ \r\n"
						+ "                     \\ \\/ / | |/ _` |/ _` |/ _ \\ |\\/| |/ _` | / __|\r\n"
						+ "                      \\  /  | | (_| | (_| |  __/ |  | | (_| | \\__ \\\r\n"
						+ "                       \\/   |_|\\__,_|\\__, |\\___|_|  |_|\\__,_|_|___/\r\n"
						+ "                                      __/ |                        \r\n"
						+ "                                     |___/                         \r\n" + "         ");

		do {
			System.out.println(Colors.BLUE_BOLD_BRIGHT + "\n 1 - Clientes");
			System.out.println(" 2 - Pacotes de viagens");
			System.out.println(" 3 - Passagens");
			System.out.println(" 4 - Pagamentos");
			System.out.println(" 5 - Sair");
			System.out.printf(" Escolha uma opção: " + Colors.RESET);

			opcao = input.nextInt();

			switch (opcao) {
			case 1:
				showOptions("cliente");
				subOpcao = input.nextInt();
				MenuCliente.menuCliente(subOpcao);
				break;
			/// ================ PACOTE ============================ //
			case 2:
				showOptions("pacotes");
				subOpcao = input.nextInt();
				MenuPacote.menuPacote(subOpcao);
				break;
			/// ================ PASSAGEM ============================ //
			case 3:
				showOptions("passagem");
				subOpcao = input.nextInt();
				MenuPassagem.menuPassagem(subOpcao);
				break;
			/// ================ PAGAMENTOS ============================ //
			case 4:
				showOptions("pagamento");
				subOpcao = input.nextInt();
				MenuPagamento.menuPagamento(subOpcao);
				break;
			case 5:
				input.close();
				clienteDAO.fecharConexao();
				hospedagemDAO.fecharConexao();
				pacoteDAO.fecharConexao();
				passagemDAO.fecharConexao();
				destinoDAO.fecharConexao();
				cp.fecharConexao();
				cpassagem.fecharConexao();
				break;
			default:
				System.out.println("\n Tente novamente...");
			}

		} while (opcao != 5);

	}

	public static void showOptions(String service) {
		System.out.println("");
		System.out.println(Colors.WHITE_BOLD_BRIGHT + " [Menu " + service + "]" + Colors.RESET);
		System.out.println("\n 1 - Adicionar \n 2 - Visualizar \n 3 - Atualizar \n 4 - Deletar \n 5 - Sair ");
		System.out.printf(" Escolha uma opção: ");
	}

}
