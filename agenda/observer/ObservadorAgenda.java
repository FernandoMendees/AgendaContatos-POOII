package agenda.observer;

// Observer contrato de quem deseja receber avisos da agenda.
public interface ObservadorAgenda {
    void atualizar(String mensagem);
}
