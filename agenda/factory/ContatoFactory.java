package agenda.factory;

import agenda.model.Contato;

/**
 * Criador abstrato do padrão Factory Method.
 * A subclasse decide qual tipo concreto de contato será criado.
 */
public abstract class ContatoFactory {
    public abstract Contato criarContato(String nome, String telefone,
                                         String email, String grupo);
}
