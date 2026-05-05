# 📝 To-Do List — Java 8

Sistema de gerenciamento de tarefas em Java puro (console), desenvolvido com foco em arquitetura em camadas, boas práticas de Programação Orientada a Objetos (POO) e persistência de dados em arquivo.

---

## 🎯 Objetivo

Este projeto foi desenvolvido com o objetivo de praticar e demonstrar conceitos fundamentais de desenvolvimento backend com Java, incluindo:

* Organização em camadas (model, service, repository)
* Separação de responsabilidades
* Tratamento de exceções personalizadas
* Persistência de dados sem uso de frameworks

---

## ✨ Funcionalidades

* ✅ Adicionar tarefas com validação de nome
* 📋 Listar todas as tarefas
* ✔️ Concluir tarefa
* 🔄 Reabrir tarefa concluída
* ✏️ Editar nome da tarefa
* 🗑️ Remover tarefa
* 🔍 Filtrar por status (pendentes / concluídas)
* 💾 Persistência automática em arquivo CSV

---

## 🖥️ Exemplo de uso

```
===== TO-DO LIST =====
1 - Adicionar tarefa
2 - Listar todas
3 - Concluir tarefa
4 - Reabrir tarefa
5 - Editar tarefa
6 - Remover tarefa
7 - Filtrar tarefas
0 - Sair

Escolha: 1
Nome da tarefa: Estudar Java

✔ Tarefa adicionada: [1] Estudar Java - ⏳ Pendente
```

---

## 🏗️ Arquitetura do Projeto

```
ToDoList/
├── model/
│   └── Tarefa.java                 # Entidade principal
├── service/
│   └── GerenciadorDeTarefas.java  # Regras de negócio
├── repository/
│   └── TarefaRepository.java      # Persistência em arquivo (CSV)
├── exception/
│   ├── TarefaNaoEncontradaException.java
│   └── TarefaInvalidaException.java
└── Main.java                      # Interface com o usuário (console)
```

---

## 🧠 Conceitos Aplicados

| Conceito                        | Aplicação                                     |
| ------------------------------- | --------------------------------------------- |
| Encapsulamento                  | Classe `Tarefa`                               |
| Single Responsibility Principle | Separação em camadas                          |
| Exceções personalizadas         | Pacote `exception`                            |
| Programar contra interfaces     | Uso de `List`                                 |
| Iterator seguro                 | Remoção sem `ConcurrentModificationException` |
| Persistência de dados           | Arquivo CSV com leitura/escrita               |
| Streams API                     | Filtros e buscas                              |

---

## 🛠 Tecnologias Utilizadas

* Java 8
* VS Code
* Programação Orientada a Objetos (POO)

---

## 🚀 Como Executar

### Pré-requisitos:

* Java 8 ou superior instalado

### Passos:

```bash
# Compilar o projeto ou baixar todos os arquivos
javac -d out model/*.java exception/*.java repository/*.java service/*.java Main.java

# Executar
java -cp out Main
```

---

## 📁 Armazenamento de Dados

As tarefas são salvas automaticamente no arquivo:

```
tarefas.csv
```

Formato utilizado:

```
id,nome,concluida
1,Estudar Java,false
2,Fazer exercícios,true
```

---

## 📈 Melhorias Futuras

* Persistência em JSON (mais robusta que CSV)
* Interface gráfica com JavaFX
* Integração com banco de dados (ex: SQLite)
* Implementação de testes automatizados (JUnit)
* Ordenação de tarefas (por nome, status, etc.)

---

## 👨‍💻 Autor

Rafa — Estudante de Análise e Desenvolvimento de Sistemas
Focado em desenvolvimento backend com Java e evolução contínua na área de tecnologia.

---

## 📌 Observação

Este projeto foi desenvolvido com fins educacionais e como parte do meu portfólio para entrada na área de desenvolvimento.
