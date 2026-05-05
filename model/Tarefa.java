package model;

public class Tarefa {

    private int id;
    private String nome;
    private boolean concluida;

    public Tarefa(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.concluida = false;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public boolean isConcluida() { return concluida; }

    public void concluir() {
        this.concluida = true;
    }

    public void reabrir() {
        this.concluida = false;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        String status = concluida ? "✔ Concluída" : "⏳ Pendente";
        return "[" + id + "] " + nome + " - " + status;
    }
}
