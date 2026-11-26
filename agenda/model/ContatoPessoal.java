package agenda.model;

// Tipo concreto de contato pessoal.
public class ContatoPessoal extends Contato {
    public ContatoPessoal(String nome, String telefone, String email, String grupo) {
        super(nome, telefone, email, grupo);
    }

    @Override
    public String getTipo() { return "PESSOAL"; }
}
