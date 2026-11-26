package agenda.model;

/**
 * Componente do padrão Composite.
 * Contatos e grupos podem ser tratados da mesma forma pela agenda.
 */
public interface ItemAgenda {
    String getNome();
    void exibir(String indentacao);
}
