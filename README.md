# 📌 ForumHub - Desafio Alura

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

O **ForumHub** é uma API REST desenvolvida como desafio final da trilha de Backend com Java da Alura. O objetivo é replicar o funcionamento de um fórum, onde usuários podem criar tópicos, listar dúvidas e interagir, tudo protegido por autenticação via tokens JWT.

---

## 🚀 Funcionalidades

*   **Cadastro de Usuários**: Registro de novos usuários com senhas criptografadas (BCrypt).
*   **Autenticação**: Login seguro utilizando Spring Security e JWT (Nimbus).
*   **CRUD de Tópicos**:
    *   Criar um novo tópico (vinculado ao autor logado e ao curso).
    *   Listar todos os tópicos (com paginação e ordenação por data).
    *   Detalhar um tópico específico por ID.
    *   Atualizar título e mensagem.
    *   Excluir um tópico.
*   **Documentação**: Interface interativa com Swagger/OpenAPI.

---

## 📂 Estrutura do Projeto

A organização do projeto segue o padrão de camadas e pacotes por domínio:

```text
src/main/java/br/com/rorschach/forumhub
├── 📁 config                 # Configurações do Spring (Security, Swagger)
│   ├── SecurityConfigurations.java
│   └── SpringDocConfigurations.java
├── 📁 controller             # Endpoints da API
│   ├── AutenticacaoController.java
│   ├── TopicoController.java
│   └── UsuarioController.java
├── 📁 domain                 # Entidades de Negócio e Regras
│   ├── 📁 curso              # Domínio de Cursos
│   │   ├── Curso.java
│   │   └── CursoRepository.java
│   ├── 📁 topico             # Domínio de Tópicos
│   │   ├── 📁 dto            # Records de entrada e saída (DTOs)
│   │   │   ├── DadosCadastroTopico.java
│   │   │   ├── DadosListagemTopico.java
│   │   │   └── ...
│   │   ├── Topico.java
│   │   ├── TopicoRepository.java
│   │   └── TopicoService.java
│   └── 📁 usuario            # Domínio de Usuários
│       ├── Usuario.java
│       ├── UsuarioRepository.java
│       └── DadosTokenJWT.java
└── 📁 infra                  # Infraestrutura e Segurança
    └── 📁 security           # Lógica do JWT e Autenticação
        ├── AutenticacaoService.java
        └── JwtService.java
```

## 🛠️ Tecnologias Utilizadas

- Java 24 (OpenJDK)
- Spring Boot 3.5.10
- Spring Data JPA (Hibernate)
- Spring Security 6 (OAuth2 Resource Server + Nimbus)
- PostgreSQL (Banco de dados)
- Maven (Gerenciador de dependências)
- SpringDoc OpenAPI (Swagger)

## 🔧 Configuração e Execução

1. Clone o repositório

    git clone https://github.com/seu-usuario/challenge-forumhub.git
    cd challenge-forumhub

2. Configure o Banco de Dados
    No arquivo src/main/resources/application.properties, ajuste as credenciais:

      spring.datasource.url=jdbc:postgresql://localhost:5432/forumhub
      spring.datasource.username=seu_usuario
      spring.datasource.password=sua_senha
      api.security.token.secret=sua_chave_secreta_de_32_caracteres

3. Execute a aplicação

    mvn spring-boot:run

## 📖 Documentação da API (Swagger)

Com a aplicação rodando, acesse a documentação interativa para testar os endpoints:

👉 http://localhost:8080/swagger-ui.html

🛣️ Endpoints Principais

- Método	Endpoint	Descrição	Acesso

POST	/usuarios	Cadastro de novo usuário	Público

POST	/login	Autenticação e geração de Token	Público

GET	/topicos	Listagem de tópicos (paginada)	Protegido

POST	/topicos	Criação de novo tópico	Protegido

GET	/topicos/{id}	Detalhamento de tópico	Protegido

PUT	/topicos/{id}	Atualização de tópico	Protegido

DELETE	/topicos/{id}	Exclusão de tópico	Protegido

🧑‍💻 Autor
---

<div align="center">
Desenvolvido por: Romulo Chaves

Estudante de Back-end – Oracle Next Education (ONE)

<a href="https://github.com/R6mulo" target="_blank"> <img src="https://img.shields.io/badge/GitHub-000?logo=github&style=for-the-badge" /> </a> <a href="https://www.linkedin.com/in/romulo-chaves" target="_blank"> <img src="https://img.shields.io/badge/LinkedIn-0A66C2?logo=linkedin&style=for-the-badge" /> </a> </div>

📄 Licença

Este projeto é de uso educacional e foi desenvolvido como parte do programa de formação Backend na Oracle Next Education (ONE).
Sinta-se à vontade para estudar, aprimorar e reutilizar o código para fins de aprendizado.
