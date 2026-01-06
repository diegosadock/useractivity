# GitHub User Activity CLI

Um aplicativo de linha de comando (CLI) em Java que consulta a atividade recente de um usuário do GitHub e exibe de forma organizada e legível. Ele mostra eventos como estrelas, commits, criação de branches, pull requests, issues e forks.

---

## 🛠 Tecnologias Utilizadas

- **Java 21**
- **Maven**
- **Gson**: para desserializar JSON da API do GitHub
- **Jackson (opcional)**: para suporte a datas ISO (`OffsetDateTime`)
- **Maven Shade Plugin**: para gerar um JAR executável contendo todas as dependências

---

## 📦 Pré-requisitos

- Java 21 instalado
- Maven 3.8+ instalado
- Conexão com a internet (para acessar a API do GitHub)

---

## 📥 Instalação e Build

1. Clone o repositório:

```bash
git clone <URL-do-repositório>
cd tasktracker
```

Compile o projeto e gere o JAR executável:

```bash
mvn clean package
```

O JAR final será gerado em:

```bash
target/tasktracker-0.0.1.jar
```

# 🚀 Como executar no terminal
Passando o username como argumento
```bash
java -jar target/tasktracker-0.0.1.jar <GitHub-username>
```
Exemplo:

```bash
java -jar target/tasktracker-0.0.1.jar diegosadock
```

Saída esperada:

```css
[05/01/2026 04:29] Pushed to diegosadock/task-cli (main)
[05/01/2026 04:25] Created branch main in diegosadock/task-cli
```

📌 Funcionalidades
Lista eventos recentes de qualquer usuário GitHub

Suporta eventos principais:

WatchEvent → estrela de repositórios

PushEvent → commits enviados

CreateEvent → criação de branch ou repositório

IssuesEvent → abertura ou fechamento de issues

PullRequestEvent → abertura de pull requests

ForkEvent → forks realizados

Ordena eventos do mais recente para o mais antigo

Formata a data do evento: dd/MM/yyyy HH:mm

Evita null e eventos desconhecidos usando Optional

🔧 Estrutura do projeto
```bash
Copy code
src/main/java/
└── useractivity/
    ├── Main.java                 # Classe principal do CLI
    ├── formatter/
    │   └── GithubActivityFormatter.java  # Formata eventos do GitHub
    ├── model/
    │   ├── UserActivity.java
    │   ├── Repo.java
    │   └── Payload.java
    ├── services/
    │   ├── IUserActivityService.java
    │   └── UserActivityServiceImpl.java  # Busca eventos via API
    └── config/
        └── OffsetDateTimeAdapter.java    # Desserializa datas do GitHub
```

⚡ Observações importantes
Limite da API do GitHub: sem autenticação, há limite de 60 requisições/hora.

Token GitHub: é possível adicionar autenticação para aumentar o limite (melhoria futura).

Formato de datas: todas as datas são convertidas de ISO para dd/MM/yyyy HH:mm.

🖥 Exemplo completo de execução

```bash
java -jar target/tasktracker-0.0.1.jar professorisidro
```

Saída no terminal:

```css
[22/12/2025 22:47] Starred eclipse-jnosql/jnosql
[18/12/2025 14:10] Starred vinceliuice/WhiteSur-gtk-theme
[18/12/2025 14:09] Starred vinceliuice/MacTahoe-gtk-theme
[18/12/2025 14:09] Starred vinceliuice/WhiteSur-icon-theme
```

📄 Licença
MIT License – Use livremente, modifique e compartilhe.

https://roadmap.sh/projects/github-user-activity
