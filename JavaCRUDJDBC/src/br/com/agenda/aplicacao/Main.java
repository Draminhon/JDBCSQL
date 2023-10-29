package br.com.agenda.aplicacao;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void exibeContatos(Contato contatos, ContatoDAO contatoDao) throws SQLException {

		for (Contato c : contatoDao.getContatos()) {
			System.out.println("ID:" + c.getId() + " Contato: " + c.getNome() + " Idade: " + c.getIdade() + " Data: "
					+ c.getDataCadastro() + "\n");
		}
	}

	public static void main(String[] args) throws SQLException {

		ContatoDAO contatoDao = new ContatoDAO();

		Scanner scan = new Scanner(System.in);

		int choice = -1;

		while (choice != 2) {
			Contato contato = new Contato();
			System.out.println("digite 1 para inserir, 2 para consultar!");
			choice = scan.nextInt();

			if (choice == 1) {
				System.out.println("Digite o nome");
				contato.setNome(scan.next());
				System.out.println("digite a idade");
				contato.setIdade(scan.nextInt());
				contato.setDataCadastro(new Date());
				contatoDao.save(contato);

			}

			if (choice == 2) {
				exibeContatos(contato, contatoDao);
			}

		}

		
		scan.close();
	}

}
