# SIGVS Produto - Backend API

![Status do Projeto](https://img.shields.io/badge/status-em_desenvolvimento-yellow)
![Java Version](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.8+-blue)
![License](https://img.shields.io/badge/license-MIT-green)

## 📋 Descrição

**SIGVS Produto** é uma API REST backend desenvolvida com **Spring Boot** considerando um Sistema Integrado para Gestão de Vendas e Supbrimentos (SIGVS). Este microserviço é responsável pelo gerenciamento de produtos, implementando boas práticas de arquitetura e qualidade de código.

O projeto segue o padrão **MVC com Service Layer** inclui testes automatizados para validação do comportamento das funções, análise de complexidade e acomplamento arquitetural.

## 🎯 Características Principais

- ✅ **API REST** completa para gestão de produtos
- ✅ **Arquitetura em Camadas** (Controller → Service → Repository → Model)
- ✅ **Spring Data JPA** para persistência de dados
- ✅ **H2 Database** para ambiente de desenvolvimento
- ✅ **Testes Automatizados** com JUnit 5
- ✅ **Validação Arquitetural** com ArchUnit
- ✅ **Cobertura de Código** com JaCoCo (mínimo 70%)
- ✅ **Java 21** - última versão LTS

## 🛠️ Stack Tecnológico

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| Java | 21 | Linguagem principal |
| Spring Boot | 3.5.14 | Framework Web |
| Spring Data JPA | - | ORM e persistência |
| H2 Database | - | Banco de dados em memória |
| JUnit 5 | - | Framework de testes |
| ArchUnit | 1.3.0 | Validação de arquitetura |
| JaCoCo | 0.8.11 | Cobertura de código |
| Maven | 3.8+ | Gerenciador de dependências |

## 📦 Estrutura do Projeto

```
sigvs_produto/
├── src/
│   ├── main/
│   │   └── java/com/fatec/
│   │       ├── controller/        # Endpoints REST
│   │       ├── service/           # Lógica de negócio
│   │       ├── repository/        # Acesso a dados
│   │       └── model/             # Entidades
│   └── test/
│       └── java/com/fatec/        # Testes automatizados
├── pom.xml                        # Configuração Maven
├── metricas-arquitetura.md       # Relatório de métricas
└── metricas-acoplamento.md       # Análise de acoplamento
```

## 🚀 Como Começar

### Pré-requisitos

- **Java 21** instalado
- **Maven 3.8+** instalado
- **Git** para controle de versão

### Instalação Local

1. **Clonar o repositório:**
```bash
git clone https://github.com/almeida2/sigvs_produto.git
cd sigvs_produto
```

2. **Compilar o projeto:**
```bash
./mvnw clean compile
```

3. **Executar os testes:**
```bash
./mvnw test
```

4. **Construir o artefato:**
```bash
./mvnw clean package
```

5. **Iniciar a aplicação:**
```bash
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 📊 Qualidade de Código

### Cobertura de Testes

O projeto utiliza **JaCoCo** para garantir qualidade:
- ✅ Mínimo de **70% de cobertura** de linhas de código
- ✅ Execução automática durante fase `verify` do Maven

Gerar relatório de cobertura:
```bash
./mvnw clean test jacoco:report
```

O relatório estará em: `target/site/jacoco/index.html`

### Validação Arquitetural

O projeto valida conformidade com padrão MVC + Service usando **ArchUnit**:

```bash
./mvnw test -Dtest=*ArchitectureTest
```

### Métricas Arquiteturais

Consulte o relatório completo em [`metricas-arquitetura.md`](./metricas-arquitetura.md):

| Camada | Instabilidade | Abstração | Distância |
|--------|:-------------:|:---------:|:---------:|
| **controller** | 1,00 | 0,00 | 0,00 |
| **service** | 0,67 | 0,33 | 0,00 |
| **repository** | 0,50 | 1,00 | 0,50 |
| **model** | 0,00 | 0,00 | 1,00 |

## 🔌 Endpoints Principais

A API segue padrões RESTful. Exemplo de endpoints esperados:

```
GET    /api/produtos           - Listar todos os produtos
GET    /api/produtos/{id}      - Obter produto específico
POST   /api/produtos           - Criar novo produto
PUT    /api/produtos/{id}      - Atualizar produto
DELETE /api/produtos/{id}      - Deletar produto
```

> 📝 Consulte a documentação específica de endpoints conforme o projeto evolui.

## 🧪 Testes

### Executar Todos os Testes
```bash
./mvnw test
```

### Executar Teste Específico
```bash
./mvnw test -Dtest=NomeTesteClass
```

### Executar com Verbosidade
```bash
./mvnw test -X
```

## 📚 Documentação Adicional

- [`metricas-arquitetura.md`](./metricas-arquitetura.md) - Análise detalhada de arquitetura
- [`metricas-acoplamento.md`](./metricas-acoplamento.md) - Relatório de acoplamento entre camadas

## 🤝 Contribuindo

Para contribuir com o projeto:

1. Faça um **fork** do repositório
2. Crie uma **branch** para sua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. **Push** para a branch (`git push origin feature/AmazingFeature`)
5. Abra um **Pull Request**

### Critérios de Contribuição

- ✅ Manter cobertura de testes ≥ 70%
- ✅ Seguir padrão de arquitetura MVC + Service
- ✅ Validar com testes de arquitetura (ArchUnit)
- ✅ Documentar mudanças significativas

## 📋 Checklist de Desenvolvimento

- [ ] Código segue padrão da arquitetura
- [ ] Testes unitários implementados
- [ ] Cobertura de testes ≥ 70%
- [ ] Validação ArchUnit passa
- [ ] Documentação atualizada
- [ ] Build Maven compila sem erros

## 📝 Licença

Este projeto está licenciado sob a **Licença MIT** - veja o arquivo [`LICENSE`](./LICENSE) para detalhes.

## 👤 Autor

**almeida2** - [GitHub Profile](https://github.com/almeida2)

## 📞 Suporte

Para reportar bugs ou sugerir melhorias, abra uma **Issue** no repositório.

---

<div align="center">

**⭐ Se este projeto foi útil para você, considere dar uma estrela! ⭐**

</div>
