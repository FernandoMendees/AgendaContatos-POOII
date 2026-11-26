package agenda.observer;

import java.util.ArrayList;
import java.util.List;

//Subject do padrão Observer quando ocorre uma alteração, todos os observadores são avisados.
public class AgendaObservavel {
    private final List<ObservadorAgenda> observadores = new ArrayList<>();

    public void adicionarObservador(ObservadorAgenda observador) {
        observadores.add(observador);
    }

    public void removerObservador(ObservadorAgenda observador) {
        observadores.remove(observador);
    }

    public void notificar(String mensagem) {
        for (ObservadorAgenda observador : observadores) {
            observador.atualizar(mensagem);
        }
    }
}
