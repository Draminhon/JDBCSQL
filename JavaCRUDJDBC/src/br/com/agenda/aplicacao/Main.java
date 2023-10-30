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

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		ContatoDAO contatoDao = new ContatoDAO();

		Scanner scan = new Scanner(System.in);

		int choice = -1;

		while (choice != 4) {
			Contato contato = new Contato();
			System.out.println("digite 1 para inserir, 2 para alterar, 3 para deletar, 4 para consultar todos!");
			choice = scan.nextInt();

			if (choice == 1) {

				System.out.println("Digite o nome");
				scan.nextLine();
				contato.setNome(scan.nextLine());
               
				System.out.println("digite a idade");
				contato.setIdade(scan.nextInt());

				contato.setDataCadastro(new Date());
				contatoDao.save(contato);

			}

			if (choice == 2) {

				System.out.println("Contatos da lista:");
				exibeContatos(contato, contatoDao);

				System.out.println("Digite o novo nome");
				scan.nextLine();
				contato.setNome(scan.nextLine());

				System.out.println("digite a nova idade");
				contato.setIdade(scan.nextInt());

				contato.setDataCadastro(new Date());

				System.out.println("Informe o id da pessoa que você irá alterar");
				contato.setId(scan.nextInt());

				contatoDao.update(contato);

			}

			if (choice == 3) {
				System.out.println("Contatos da lista:");
				exibeContatos(contato, contatoDao);

				System.out.println("Digite o id a ser removido!");

				contatoDao.delete(scan.nextInt());

				exibeContatos(contato, contatoDao);

			}

			if (choice == 4) {
				exibeContatos(contato, contatoDao);
			}

		}

		scan.close();
	}

}
