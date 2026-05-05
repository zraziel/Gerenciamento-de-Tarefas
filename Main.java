import exception.TarefaInvalidaException;
import exception.TarefaNaoEncontradaException;
import model.Tarefa;
import repository.TarefaRepository;
import service.GerenciadorDeTarefas;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final GerenciadorDeTarefas gerenciador =
            new GerenciadorDeTarefas(new TarefaRepository());
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1: adicionarTarefa();   break;
                case 2: listarTarefas();     break;
                case 3: concluirTarefa();    break;
                case 4: reabrirTarefa();     break;
                case 5: editarTarefa();      break;
                case 6: removerTarefa();     break;
                case 7: listarFiltradas();   break;
                case 0: System.out.println("\nAté logo! 👋"); break;
                default: System.out.println("\n⚠ Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // ─── MENU ────────────────────────────────────────────────────────────────

    private static void exibirMenu() {
        System.out.println("\n╔═══════════════════════════╗");
        System.out.println("║       TO-DO LIST          ║");
        System.out.println("╠═══════════════════════════╣");
        System.out.println("║  1 - Adicionar tarefa     ║");
        System.out.println("║  2 - Listar todas         ║");
        System.out.println("║  3 - Concluir tarefa      ║");
        System.out.println("║  4 - Reabrir tarefa       ║");
        System.out.println("║  5 - Editar tarefa        ║");
        System.out.println("║  6 - Remover tarefa       ║");
        System.out.println("║  7 - Filtrar tarefas      ║");
        System.out.println("║  0 - Sair                 ║");
        System.out.println("╚═══════════════════════════╝");
        System.out.print("Escolha: ");
    }

    // ─── AÇÕES ───────────────────────────────────────────────────────────────

    private static void adicionarTarefa() {
        System.out.print("Nome da tarefa: ");
        String nome = scanner.nextLine().trim();
        try {
            Tarefa criada = gerenciador.adicionarTarefa(nome);
            System.out.println("✔ Tarefa adicionada: " + criada);
        } catch (TarefaInvalidaException e) {
            System.out.println("⚠ " + e.getMessage());
        }
    }

    private static void listarTarefas() {
        List<Tarefa> tarefas = gerenciador.listarTarefas();
        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa cadastrada.");
            return;
        }
        System.out.println("\n══════ TODAS AS TAREFAS ══════");
        tarefas.forEach(System.out::println);
    }

    private static void concluirTarefa() {
        System.out.print("ID da tarefa a concluir: ");
        int id = lerOpcao();
        try {
            Tarefa t = gerenciador.concluirTarefa(id);
            System.out.println("✔ " + t);
        } catch (TarefaNaoEncontradaException | TarefaInvalidaException e) {
            System.out.println("⚠ " + e.getMessage());
        }
    }

    private static void reabrirTarefa() {
        System.out.print("ID da tarefa a reabrir: ");
        int id = lerOpcao();
        try {
            Tarefa t = gerenciador.reabrirTarefa(id);
            System.out.println("✔ Tarefa reaberta: " + t);
        } catch (TarefaNaoEncontradaException | TarefaInvalidaException e) {
            System.out.println("⚠ " + e.getMessage());
        }
    }

    private static void editarTarefa() {
        System.out.print("ID da tarefa a editar: ");
        int id = lerOpcao();
        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine().trim();
        try {
            Tarefa t = gerenciador.editarTarefa(id, novoNome);
            System.out.println("✔ Tarefa atualizada: " + t);
        } catch (TarefaNaoEncontradaException | TarefaInvalidaException e) {
            System.out.println("⚠ " + e.getMessage());
        }
    }

    private static void removerTarefa() {
        System.out.print("ID da tarefa a remover: ");
        int id = lerOpcao();
        try {
            gerenciador.removerTarefa(id);
            System.out.println("✔ Tarefa removida com sucesso.");
        } catch (TarefaNaoEncontradaException e) {
            System.out.println("⚠ " + e.getMessage());
        }
    }

    private static void listarFiltradas() {
        System.out.println("\n1 - Pendentes  |  2 - Concluídas");
        System.out.print("Filtro: ");
        int filtro = lerOpcao();

        List<Tarefa> lista = filtro == 1
                ? gerenciador.listarPendentes()
                : gerenciador.listarConcluidas();

        String titulo = filtro == 1 ? "PENDENTES" : "CONCLUÍDAS";

        if (lista.isEmpty()) {
            System.out.println("\nNenhuma tarefa " + titulo.toLowerCase() + ".");
            return;
        }

        System.out.println("\n══════ " + titulo + " ══════");
        lista.forEach(System.out::println);
    }

    // ─── UTILITÁRIOS ─────────────────────────────────────────────────────────

    private static int lerOpcao() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }
}
