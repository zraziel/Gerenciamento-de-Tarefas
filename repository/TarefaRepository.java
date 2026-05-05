package repository;

import model.Tarefa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaRepository {

    private static final String ARQUIVO = "tarefas.csv";

    public void salvar(List<Tarefa> tarefas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Tarefa t : tarefas) {
                writer.write(t.getId() + "," + t.getNome() + "," + t.isConcluida());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠ Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    public List<Tarefa> carregar() {
        List<Tarefa> tarefas = new ArrayList<>();
        File arquivo = new File(ARQUIVO);

        if (!arquivo.exists()) return tarefas;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",", 3);
                if (partes.length == 3) {
                    int id = Integer.parseInt(partes[0].trim());
                    String nome = partes[1].trim();
                    boolean concluida = Boolean.parseBoolean(partes[2].trim());

                    Tarefa t = new Tarefa(id, nome);
                    if (concluida) t.concluir();
                    tarefas.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠ Erro ao carregar tarefas: " + e.getMessage());
        }

        return tarefas;
    }

    public int carregarProximoId(List<Tarefa> tarefas) {
        return tarefas.stream()
                .mapToInt(Tarefa::getId)
                .max()
                .orElse(0) + 1;
    }
}
