package agenda.composite;

import agenda.model.ItemAgenda;
import java.util.ArrayList;
import java.util.List;

//Composite: representa um grupo que pode conter contatos
// assim a agenda exibe um grupo e seus contatos de maneira uniforme.
public class GrupoContatos implements ItemAgenda {
    private final String nome;
    private final List<ItemAgenda> itens = new ArrayList<>();

    public GrupoContatos(String nome) {
        this.nome = nome;
    }

    public void adicionar(ItemAgenda item) {
        itens.add(item);
    }

    public void remover(ItemAgenda item) {
        itens.remove(item);
    }

    public List<ItemAgenda> getItens() {
        return itens;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + "[Grupo: " + nome + "]");
        for (ItemAgenda item : itens) {
            item.exibir(indentacao + "  ");
        }
    }
}
