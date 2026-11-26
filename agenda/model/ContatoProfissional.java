package agenda.model;

// Tipo concreto de contato profissional.
public class ContatoProfissional extends Contato {
    public ContatoProfissional(String nome, String telefone, String email, String grupo) {
        super(nome, telefone, email, grupo);
    }

    @Override
    public String getTipo() { return "PROFISSIONAL"; }
}
