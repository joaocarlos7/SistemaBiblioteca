# 📚 Sistema de Biblioteca

Sistema desenvolvido em **Java** para gerenciamento de livros, clientes e empréstimos em uma biblioteca, com suporte a persistência de dados em JSON.

O projeto foi construído como **desafio de conclusão de módulo da Rocketseat**, com foco em aplicar conceitos fundamentais de programação orientada a objetos e manipulação de dados em memória, simulando um fluxo real de operação de biblioteca.

---

## 📌 Objetivo

Centralizar e automatizar o controle de empréstimos de livros, permitindo o cadastro de clientes, gerenciamento do acervo e acompanhamento dos livros disponíveis e emprestados.

---

## 🚀 Funcionalidades

- ✅ Cadastro de livros  
- ✅ Cadastro de clientes  
- ✅ Registro de empréstimos  
- ✅ Controle de disponibilidade de livros  
- ✅ Persistência de dados em JSON  

---

## 🛠 Tecnologias

- Java (JDK 8+)  
- Programação Orientada a Objetos (POO)  
- Collections (ArrayList)  
- Streams API  
- Java Time API  
- Persistência em JSON (Gson)  

---

## 🧠 Arquitetura

- Estrutura baseada em entidades (`Book`, `Author`, `Loan`, `Client`)  
- Camada de gerenciamento central (`Library`)  
- Interface via terminal (`Menu`)  
- Persistência desacoplada (`PersistenceManager`)  

---

## ▶️ Como executar

```bash
git clone https://github.com/joaocarlos7/SistemaBiblioteca.git
cd SistemaBiblioteca
javac Main.java
java Main
