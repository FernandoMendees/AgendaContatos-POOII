package agenda.factory;

import agenda.model.Contato;
import agenda.model.ContatoPessoal;

/** Factory concreta responsável por criar contatos pessoais. */
public class ContatoPessoalFactory extends ContatoFactory {
    @Override
    public Contato criarContato(String nome, String telefone, String email, String grupo) {
        return new ContatoPessoal(nome, telefone, email, grupo);
    }
}
