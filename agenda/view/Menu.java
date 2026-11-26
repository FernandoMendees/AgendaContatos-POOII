package agenda.view;

import agenda.composite.GrupoContatos;
import agenda.controller.AgendaController;
import agenda.factory.ContatoFactory;
import agenda.factory.ContatoPessoalFactory;
import agenda.factory.ContatoProfissionalFactory;
import agenda.model.Contato;
import java.util.List;
import java.util.Scanner;

// apresenta o menu e recebe os dados do usuário.
public class Menu {
    private final AgendaController controller;
    private final Scanner entrada = new Scanner(System.in);

    public Menu(AgendaController controller) {
        this.controller = controller;
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n===== AGENDA DE CONTATOS - GRUPO 7 =====");
            System.out.println("1 - Cadastrar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Buscar contato");
            System.out.println("4 - Editar contato");
            System.out.println("5 - Remover contato");
            System.out.println("6 - Listar contatos por grupo");
            System.out.println("0 - Sair");
            opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> mostrarContatos(controller.listar());
                case 3 -> buscar();
                case 4 -> editar();
                case 5 -> remover();
                case 6 -> listarGrupos();
                case 0 -> System.out.println("Programa encerrado. Dados salvos.");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        System.out.println("Tipo: 1 - Pessoal | 2 - Profissional");
        int tipo = lerInteiro("Escolha o tipo: ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo inválido.");
            return;
        }
        ContatoFactory fabrica = tipo == 1
                ? new ContatoPessoalFactory() : new ContatoProfissionalFactory();

        String nome = lerTexto("Nome: ");
        String telefone = lerTexto("Telefone: ");
        String email = lerTexto("E-mail (pode deixar vazio): ");
        String grupo = lerTexto("Grupo (ex.: Família, Amigos, Trabalho): ");

        if (controller.cadastrar(fabrica, nome, telefone, email, grupo)) {
            System.out.println("Contato cadastrado com sucesso!");
        } else {
            System.out.println("Nome, telefone e grupo são obrigatórios.");
        }
    }

    private void buscar() {
        String termo = lerTexto("Digite nome, telefone ou e-mail: ");
        mostrarContatos(controller.buscar(termo));
    }

    private void editar() {
        List<Contato> lista = controller.listar();
        mostrarContatos(lista);
        if (lista.isEmpty()) return;
        int indice = lerInteiro("Número do contato para editar: ") - 1;
        if (indice < 0 || indice >= lista.size()) {
            System.out.println("Número inválido.");
            return;
        }
        String nome = lerTexto("Novo nome: ");
        String telefone = lerTexto("Novo telefone: ");
        String email = lerTexto("Novo e-mail: ");
        String grupo = lerTexto("Novo grupo: ");
        if (controller.editar(indice, nome, telefone, email, grupo)) {
            System.out.println("Contato atualizado!");
        } else {
            System.out.println("Dados inválidos.");
        }
    }

    private void remover() {
        List<Contato> lista = controller.listar();
        mostrarContatos(lista);
        if (lista.isEmpty()) return;
        int indice = lerInteiro("Número do contato para remover: ") - 1;
        if (controller.remover(indice)) {
            System.out.println("Contato removido!");
        } else {
            System.out.println("Número inválido.");
        }
    }

    private void listarGrupos() {
        List<GrupoContatos> grupos = controller.listarPorGrupo();
        if (grupos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
            return;
        }
        for (GrupoContatos grupo : grupos) {
            grupo.exibir("");
        }
    }

    private void mostrarContatos(List<Contato> contatos) {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
            return;
        }
        for (int i = 0; i < contatos.size(); i++) {
            System.out.println((i + 1) + " - " + contatos.get(i));
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine();
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
