package service;

import exception.TarefaInvalidaException;
import exception.TarefaNaoEncontradaException;
import model.Tarefa;
import repository.TarefaRepository;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class GerenciadorDeTarefas {

    private List<Tarefa> tarefas;
    private int proximoId;
    private final TarefaRepository repository;

    public GerenciadorDeTarefas(TarefaRepository repository) {
        this.repository = repository;
        this.tarefas = repository.carregar();
        this.proximoId = repository.carregarProximoId(tarefas);
    }

    public Tarefa adicionarTarefa(String nome) {
        validarNome(nome);
        Tarefa nova = new Tarefa(proximoId, nome);
        tarefas.add(nova);
        proximoId++;
        repository.salvar(tarefas);
        return nova;
    }

    public List<Tarefa> listarTarefas() {
        return Collections.unmodifiableList(tarefas);
    }

    public List<Tarefa> listarPendentes() {
        return tarefas.stream()
                .filter(t -> !t.isConcluida())
                .collect(java.util.stream.Collectors.toList());
    }

    public List<Tarefa> listarConcluidas() {
        return tarefas.stream()
                .filter(Tarefa::isConcluida)
                .collect(java.util.stream.Collectors.toList());
    }

    public Tarefa concluirTarefa(int id) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa.isConcluida()) {
            throw new TarefaInvalidaException("Tarefa já está concluída.");
        }
        tarefa.concluir();
        repository.salvar(tarefas);
        return tarefa;
    }

    public Tarefa reabrirTarefa(int id) {
        Tarefa tarefa = buscarPorId(id);
        if (!tarefa.isConcluida()) {
            throw new TarefaInvalidaException("Tarefa já está pendente.");
        }
        tarefa.reabrir();
        repository.salvar(tarefas);
        return tarefa;
    }

    public Tarefa editarTarefa(int id, String novoNome) {
        validarNome(novoNome);
        Tarefa tarefa = buscarPorId(id);
        tarefa.setNome(novoNome);
        repository.salvar(tarefas);
        return tarefa;
    }

    public void removerTarefa(int id) {
        buscarPorId(id); // valida se existe antes
        Iterator<Tarefa> iterator = tarefas.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                repository.salvar(tarefas);
                return;
            }
        }
    }

    private Tarefa buscarPorId(int id) {
        return tarefas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new TarefaInvalidaException("O nome da tarefa não pode ser vazio.");
        }
        if (nome.trim().length() < 3) {
            throw new TarefaInvalidaException("O nome da tarefa deve ter pelo menos 3 caracteres.");
        }
    }
}
