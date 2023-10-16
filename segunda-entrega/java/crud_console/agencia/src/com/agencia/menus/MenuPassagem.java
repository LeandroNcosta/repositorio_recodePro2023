package com.agencia.menus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.agencia.dao.DestinoDAO;
import com.agencia.dao.PassagemDAO;
import com.agencia.model.Passagem;
import com.agencia.utils.Colors;

public class MenuPassagem {

	public static void menuPassagem(int subOpcao) {
		Scanner input = new Scanner(System.in);

		switch (subOpcao) {

		case 1:
			Passagem passagem = new Passagem();

			System.out.println("");
			System.out.println(Colors.YELLOW + " Informe os dados necessários: \n" + Colors.RESET);
			System.out.print(" Deseja de qual compania ? (default 1 - Azul 2 - GOL) ");
			passagem.setCompania(input.next());

			String yesOrNo = "";

			while (!(yesOrNo.equalsIgnoreCase("y") || yesOrNo.equalsIgnoreCase("n"))) {
				System.out.print(" Deseja passagem de volta?: (y/n) ");
				yesOrNo = input.next();
				 
				if (yesOrNo.equalsIgnoreCase("y")) {
					passagem.setIdaEvolta(true);
				} else if (yesOrNo.equalsIgnoreCase("n")) {
					passagem.setIdaEvolta(false);
				} else {
					System.out.println(" Tente novamente...");
				}
			}

			System.out.print(" Deseja qual tipo de voo? 1 - Direto 2 - Escala 3 - Conexão ");
			int tipoVoo = input.nextInt();

			if (tipoVoo == 1) {
				passagem.setTipoVoo("direto");
			} else if (tipoVoo == 2) {
				passagem.setTipoVoo("escala");
			} else {
				passagem.setTipoVoo("conexao");
			}

			System.out.print(" Informe a quantidade: ");
			passagem.setQuantidade(input.nextInt());
			System.out.print(" Qual destino? (Default 1 ou 2) ");
			int destinoId = input.nextInt();
			passagem.setDestino(DestinoDAO.findBy(destinoId));

			PassagemDAO.create(passagem);
			break;
		case 2:

			List<Passagem> passagens = PassagemDAO.read("");

			System.out.println(
					Colors.YELLOW + "-----------------------------------------------------------------------------");
			System.out.printf("%5s %20s %20s %20s", "ID", "DATA", "DESTINO", "VALOR");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			for (Passagem passagem2 : passagens) {
				System.out.format("%5s %20s %20s %20s", passagem2.getId(), passagem2.getData(),
						passagem2.getDestino().getCidade() + "-" + passagem2.getDestino().getPais(),
						passagem2.getValor());
				System.out.println("");

			}
			System.out.println(
					"-----------------------------------------------------------------------------" + Colors.RESET);
			break;
		case 3:
			System.out.printf(Colors.YELLOW + "\n Informe o ID da passagem: ");
			int id = input.nextInt();

			Passagem passagem3 = PassagemDAO.findBy(id);

			System.out.printf(Colors.GREEN + "%n [dados] %n Id: %s Valor total: %s %n", passagem3.getId(),
					passagem3.getValor() + Colors.RESET);
			System.out.println("");
			System.out.println(Colors.YELLOW + " Atualize os dados abaixo: " + Colors.RESET);
			System.out.print(" Valor: ");
			passagem3.setValor((BigDecimal) input.nextBigDecimal());
			System.out.print(" Quantidade: ");
			passagem3.setQuantidade(input.nextInt());
			System.out.print(" Taxa servico: ");
			passagem3.setTaxaServico(input.nextInt());
			System.out.print(" Taxa embarque ");
			passagem3.setTaxaEmbarque(input.nextInt());

			PassagemDAO.update(passagem3);
			break;
		case 4:
			System.out.printf( Colors.YELLOW +"\n Informe o ID a ser excluído: ");
			int deleteId = input.nextInt();

			Passagem passagem4 = PassagemDAO.findBy(deleteId);

			System.out.printf(Colors.GREEN + "%n [dados] %n Id: %d Compania: %s %n", passagem4.getId(),
					passagem4.getCompania() + Colors.RESET);

			System.out.printf(" Deseja excluir? (y/n) ");
			String yesOrNo2 = input.next();

			if (yesOrNo2.equalsIgnoreCase("y")) {
				PassagemDAO.delete(deleteId);
			} else if (yesOrNo2.equalsIgnoreCase("n")) {
				System.out.printf(" Cliente não foi excluído");
			} else {
				System.out.printf(" Tente novamente...");
			}

			break;
		case 5:

			break;
		default:
			System.out.println("\n Tente novamente... \n");
		}

	}
}
