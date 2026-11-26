package agenda.factory;

import agenda.model.Contato;
import agenda.model.ContatoProfissional;

// Factory concreta responsável por criar contatos profissionais.
public class ContatoProfissionalFactory extends ContatoFactory {
    @Override
    public Contato criarContato(String nome, String telefone, String email, String grupo) {
        return new ContatoProfissional(nome, telefone, email, grupo);
    }
}