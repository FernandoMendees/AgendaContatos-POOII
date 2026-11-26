package agenda.controller;

import agenda.composite.GrupoContatos;
import agenda.factory.ContatoFactory;
import agenda.model.Contato;
import agenda.model.ItemAgenda;
import agenda.observer.AgendaObservavel;
import agenda.repository.ContatoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Controlador: aplica as regras e conecta a tela ao repositório.
public class AgendaController {
    private final ContatoRepository repositorio;
    private final AgendaObservavel agendaObservavel;
    private final List<Contato> contatos;

    public AgendaController(ContatoRepository repositorio, AgendaObservavel agendaObservavel) {
        this.repositorio = repositorio;
        this.agendaObservavel = agendaObservavel;
        this.contatos = new ArrayList<>(repositorio.listarTodos());
    }

    public boolean cadastrar(ContatoFactory fabrica, String nome, String telefone,
                             String email, String grupo) {
        if (textoVazio(nome) || textoVazio(telefone) || textoVazio(grupo)) return false;
        Contato contato = fabrica.criarContato(nome.trim(), telefone.trim(),
                email == null ? "" : email.trim(), grupo.trim());
        contatos.add(contato);
        salvar();
        agendaObservavel.notificar("Contato '" + contato.getNome() + "' cadastrado.");
        return true;
    }

    public boolean editar(int indice, String nome, String telefone, String email, String grupo) {
        if (!indiceValido(indice) || textoVazio(nome) || textoVazio(telefone)
                || textoVazio(grupo)) return false;
        Contato c = contatos.get(indice);
        c.setNome(nome.trim());
        c.setTelefone(telefone.trim());
        c.setEmail(email == null ? "" : email.trim());
        c.setGrupo(grupo.trim());
        salvar();
        agendaObservavel.notificar("Contato '" + c.getNome() + "' atualizado.");
        return true;
    }

    public boolean remover(int indice) {
        if (!indiceValido(indice)) return false;
        Contato removido = contatos.remove(indice);
        salvar();
        agendaObservavel.notificar("Contato '" + removido.getNome() + "' removido.");
        return true;
    }

    public List<Contato> listar() {
        return new ArrayList<>(contatos);
    }

    public List<Contato> buscar(String termo) {
        List<Contato> encontrados = new ArrayList<>();
        String busca = termo.toLowerCase(Locale.ROOT);
        for (Contato c : contatos) {
            if (c.getNome().toLowerCase(Locale.ROOT).contains(busca)
                    || c.getTelefone().toLowerCase(Locale.ROOT).contains(busca)
                    || c.getEmail().toLowerCase(Locale.ROOT).contains(busca)) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }

    // Monta a árvore Composite agrupando os contatos pelo campo grupo.
    public List<GrupoContatos> listarPorGrupo() {
        List<GrupoContatos> grupos = new ArrayList<>();
        for (Contato contato : contatos) {
            GrupoContatos grupoEncontrado = null;
            for (GrupoContatos grupo : grupos) {
                if (grupo.getNome().equalsIgnoreCase(contato.getGrupo())) {
                    grupoEncontrado = grupo;
                    break;
                }
            }
            if (grupoEncontrado == null) {
                grupoEncontrado = new GrupoContatos(contato.getGrupo());
                grupos.add(grupoEncontrado);
            }
            grupoEncontrado.adicionar((ItemAgenda) contato);
        }
        return grupos;
    }

    private boolean indiceValido(int indice) {
        return indice >= 0 && indice < contatos.size();
    }

    private boolean textoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private void salvar() {
        repositorio.salvarTodos(contatos);
    }
}