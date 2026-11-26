package agenda.model;

//Classe base dos contatos cada contato é uma folha no padrão Composite.
public abstract class Contato implements ItemAgenda {
    private String nome;
    private String telefone;
    private String email;
    private String grupo;

    public Contato(String nome, String telefone, String email, String grupo) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.grupo = grupo;
    }

    public abstract String getTipo();

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getGrupo() { return grupo; }

    public void setNome(String nome) { this.nome = nome; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + nome + " | " + telefone + " | " + email);
    }

    @Override
    public String toString() {
        return nome + " | " + telefone + " | " + email + " | Grupo: " + grupo
                + " | Tipo: " + getTipo();
    }
}
