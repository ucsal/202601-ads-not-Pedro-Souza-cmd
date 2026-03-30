# 🧠 Olimpíada de Xadrez — Refatoração com SOLID

## 📌 Descrição

Este projeto consiste na refatoração de um sistema de aplicação de provas de xadrez, originalmente implementado de forma procedural e com alto acoplamento.

O objetivo foi aplicar os princípios **SOLID**, promovendo:

* separação de responsabilidades
* baixo acoplamento
* alta coesão
* melhor organização em camadas

---

## 🏗️ Arquitetura

O sistema foi reorganizado em camadas:

```
UI → Service → Repository → Model
```

### 📂 Estrutura de pacotes

```
model/        → entidades de domínio
repository/   → acesso a dados
service/      → regras de negócio
ui/           → interação com usuário
```

---

# 🔧 Aplicação dos Princípios SOLID

---

## ✅ S — Single Responsibility Principle (SRP)

Cada classe possui **uma única responsabilidade**.

### Exemplos:

* `ParticipanteService` → responsável apenas por operações de participante
* `AvaliacaoService` → responsável apenas pelo cálculo de nota
* `AplicacaoProvaService` → responsável pelo fluxo de aplicação da prova
* `MenuConsole` → responsável apenas pela interface com o usuário
* `TabuleiroView` → responsável apenas por renderizar o tabuleiro (FEN)

🔍 Antes:

* A classe `App` concentrava múltiplas responsabilidades (UI, regra, persistência)

✔ Depois:

* Cada responsabilidade foi isolada em sua própria classe

---

## ✅ O — Open/Closed Principle (OCP)

O sistema está aberto para extensão, mas fechado para modificação.

### Exemplos:

* Implementação de repositórios:

  * `InMemoryParticipanteRepository`
  * Possibilidade futura de `MySQLParticipanteRepository`

✔ É possível trocar a implementação sem alterar os services.

---

## ✅ L — Liskov Substitution Principle (LSP)

As implementações de repositório podem ser substituídas sem impactar o comportamento do sistema.

### Exemplo:

```java
ParticipanteRepository repo = new InMemoryParticipanteRepository();
```

➡ Pode ser substituído por outra implementação sem quebrar o sistema.

---

## ✅ I — Interface Segregation Principle (ISP)

As interfaces foram separadas por responsabilidade.

### Exemplos:

* `ParticipanteRepository`
* `ProvaRepository`
* `QuestaoRepository`
* `TentativaRepository`

✔ Cada interface expõe apenas os métodos necessários.

---

## ✅ D — Dependency Inversion Principle (DIP)

Os módulos de alto nível não dependem de implementações concretas.

### Exemplos:

* `ParticipanteService` depende de `ParticipanteRepository` (interface)
* `AplicacaoProvaService` depende de:

  * `QuestaoService`
  * `TentativaRepository`
  * `AvaliacaoService`

✔ Dependências são injetadas via construtor

---

# 🔄 Inversão de Controle (IoC)

A criação das dependências ocorre na classe `App`:

```java
new ParticipanteService(participanteRepository);
```

Isso caracteriza **injeção de dependência manual**, sem uso de frameworks.

---


# 📌 Conclusão

A refatoração permitiu transformar um código monolítico e acoplado em uma aplicação organizada em camadas, aderente aos princípios SOLID e preparada para evolução. Assim dessa forma a aplicação ficou mais "legivel" e com
maior manutenabilidade, sendo evidente onde cada coisa está e o que ela faz

---
