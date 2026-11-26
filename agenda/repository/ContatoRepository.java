package agenda.repository;

import agenda.model.Contato;
import agenda.model.ContatoPessoal;
import agenda.model.ContatoProfissional;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

//repositório: concentra leitura e gravação dos contatos os campos são codificados em Base64 para que textos com ponto
// e vírgula não quebrem a separação do arquivo.

public class ContatoRepository {
    private final String arquivo;

    public ContatoRepository(String arquivo) {
        this.arquivo = arquivo;
    }

    public List<Contato> listarTodos() {
        List<Contato> contatos = new ArrayList<>();
        File f = new File(arquivo);
        if (!f.exists()) return contatos;

        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";", -1);
                if (partes.length != 5) continue;

                String tipo = decodificar(partes[0]);
                String nome = decodificar(partes[1]);
                String telefone = decodificar(partes[2]);
                String email = decodificar(partes[3]);
                String grupo = decodificar(partes[4]);

                if ("PROFISSIONAL".equals(tipo)) {
                    contatos.add(new ContatoProfissional(nome, telefone, email, grupo));
                } else {
                    contatos.add(new ContatoPessoal(nome, telefone, email, grupo));
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível ler os contatos: " + e.getMessage());
        }
        return contatos;
    }

    public void salvarTodos(List<Contato> contatos) {
        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(arquivo), StandardCharsets.UTF_8))) {
            for (Contato c : contatos) {
                escritor.write(codificar(c.getTipo()) + ";"
                        + codificar(c.getNome()) + ";"
                        + codificar(c.getTelefone()) + ";"
                        + codificar(c.getEmail()) + ";"
                        + codificar(c.getGrupo()));
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Não foi possível salvar os contatos: " + e.getMessage());
        }
    }

    private String codificar(String valor) {
        return Base64.getEncoder().encodeToString(valor.getBytes(StandardCharsets.UTF_8));
    }

    private String decodificar(String valor) {
        return new String(Base64.getDecoder().decode(valor), StandardCharsets.UTF_8);
    }
}
