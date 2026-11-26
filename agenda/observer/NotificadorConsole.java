package agenda.observer;

// Observador concreto que mostra alterações no terminal.
public class NotificadorConsole implements ObservadorAgenda {
    @Override
    public void atualizar(String mensagem) {
        System.out.println("[AVISO DA AGENDA] " + mensagem);
    }
}
