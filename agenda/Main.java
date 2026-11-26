package agenda;

import agenda.controller.AgendaController;
import agenda.observer.AgendaObservavel;
import agenda.observer.NotificadorConsole;
import agenda.repository.ContatoRepository;
import agenda.view.Menu;

// Ponto de entrada do sistema.
public class Main {
    public static void main(String[] args) {
        // O arquivo contatos.txt será criado na pasta em que o programa for executado.
        ContatoRepository repositorio = new ContatoRepository("contatos.txt");

        // Configura o padrão Observer: a agenda avisa o notificador sobre alterações.
        AgendaObservavel agendaObservavel = new AgendaObservavel();
        agendaObservavel.adicionarObservador(new NotificadorConsole());

        // Conecta as camadas e inicia a interface.
        AgendaController controller = new AgendaController(repositorio, agendaObservavel);
        Menu menu = new Menu(controller);
        menu.iniciar();
    }
}