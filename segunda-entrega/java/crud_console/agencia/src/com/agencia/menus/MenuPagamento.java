package com.agencia.menus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.agencia.dao.ClienteDAO;
import com.agencia.dao.ClientePacoteDAO;
import com.agencia.dao.PacoteDAO;
import com.agencia.model.ClientePacote;
import com.agencia.utils.Colors;

public class MenuPagamento {

	public static void menuPagamento(int subOpcao) throws ParseException {
		Scanner input = new Scanner(System.in);

		switch (subOpcao) {
		case 1:
			ClientePacote pagPacote = new ClientePacote();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			System.out.println("");
			System.out.println(Colors.YELLOW + " Informe os dados necessários: " + Colors.RESET);

			System.out.print(" Informe a data de compra:  (dd/MM/yyyy) ");
			Date dataFormatada = formato.parse(input.next());
			pagPacote.setDataCompra(dataFormatada);
			System.out.print(" Informe o Id do cliente: ");
			pagPacote.setCliente(ClienteDAO.findBy(input.nextInt()));
			System.out.print(" Informe o Id do pacote: ");
			pagPacote.setPacote(PacoteDAO.findBy(input.nextInt()));

			ClientePacoteDAO.create(pagPacote);

			break;
		case 2:
			List<ClientePacote> pagamentos = ClientePacoteDAO.read("");

			System.out.println(
					Colors.YELLOW + "-----------------------------------------------------------------------------");
			System.out.printf("%5s %20s %20s %20s", "ID", "DATA COMPRA", "IDCLIENTE", "IDPACOTE");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			for (ClientePacote pagamento2 : pagamentos) {
				System.out.format("%5s %20s %20s %20s", pagamento2.getId(), pagamento2.getDataCompra(),
						pagamento2.getCliente().getId(), pagamento2.getPacote().getId());
				System.out.println();

			}
			System.out.println(
					"-----------------------------------------------------------------------------" + Colors.RESET);

			break;
		case 3:
			// att
		case 4:
			System.out.printf(" Informe o ID a ser excluído: ");
			int deleteId = input.nextInt();

			ClientePacote cliPacote = ClientePacoteDAO.findBy(deleteId);

			System.out.printf(" Id: %d Data compra: %s %n", cliPacote.getId(), cliPacote.getDataCompra());

			System.out.printf(" Deseja excluir? (y/n) ");
			String yesOrNo = input.next();

			if (yesOrNo.equalsIgnoreCase("y")) {
				ClientePacoteDAO.delete(deleteId);
			} else if (yesOrNo.equalsIgnoreCase("n")) {
				System.out.printf(" Cliente não foi excluído");
			} else {
				System.out.printf(" Tente novamente..");
			}

			break;
		case 5:
			break;
		default:
			System.out.printf("\n Tente novamente... \n");
		}

	}

}
