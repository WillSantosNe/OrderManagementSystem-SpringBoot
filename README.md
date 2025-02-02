# Order Management System

Este projeto é um **sistema de gerenciamento de pedidos** criado em **Java** com **Spring Boot**.  
Ele permite o **cadastro de usuários**, **produtos** e **pedidos**, incluindo operações para adicionar itens ao pedido, **calcular o preço total** e retornar dados em formato **JSON** via **endpoints REST**.

---

## Objetivo

Demonstrar habilidades em **Java**, **Spring Boot**, **arquitetura em camadas**, **banco de dados H2** e o uso de **DTOs** (Data Transfer Objects) para transferir dados sem expor diretamente as entidades.

---

## Funcionalidades Principais

### 1. Gerenciamento de Usuários (Users)
- **CRUD completo** (criação, listagem, busca por ID, atualização e remoção).  
- Cada usuário tem `id`, `nome`, `email`, `password`.

### 2. Gerenciamento de Produtos (Products)
- **CRUD completo** para produtos, com `id`, `nome`, `descricao` e `preco`.

### 3. Gerenciamento de Pedidos (Orders)
- Cadastro, edição e exclusão de pedidos.  
- Associados a um **usuário** em relação 1:N.  
- Contêm vários produtos via entidade intermediária `OrderItem` (armazena `quantity`, `subtotal` etc.).

### 4. Cálculo de Preço Total
- A cada criação ou edição de pedido, o sistema **recalcula** o valor total com base nos itens associados, podendo aplicar eventuais **regras de negócio** (frete, descontos etc.).

### 5. Endpoints RESTful
- **Endpoints** organizados em `/users`, `/products`, `/orders` e subcaminhos para operações específicas.  
- Comunicação em **JSON**, seguindo as boas práticas **REST**.

### 6. Documentação
- **README** (este arquivo), explicando o projeto e seu funcionamento.  

---

## Por que utilizar DTOs?

- **DTOs (Data Transfer Objects)** facilitam a transferência de dados sem expor toda a estrutura das entidades.  
- Permitem **customizar** quais campos são recebidos ou enviados em cada endpoint (por exemplo, `CreateOrderDTO` inclui `userId` e uma lista de `{ productId, quantity }`, sem detalhes que não sejam relevantes para a operação).  
- Melhoram a **segurança** (evitando expor atributos internos), a **manutenção** (mudanças na entidade não quebram a interface de API) e a **clareza** de cada requisição/resposta.

---

## Arquitetura em Camadas

1. **Persistência (repositories)**
   - **Interfaces** que estendem `JpaRepository` para cada entidade (`UserRepository`, `ProductRepository`, `OrderRepository`, `OrderItemRepository`).  
   - Responsáveis pelo acesso ao banco de dados **H2**.

2. **Negócio (services)**
   - Contêm a **lógica de negócios** e validações (`OrderService`, `ProductService`, `UserService`).  
   - Calculam subtotais, totais e outras regras específicas.

3. **Apresentação (controllers)**
   - **Classes REST** (`UserController`, `ProductController`, `OrderController`) anotadas com `@RestController`.  
   - Mapeiam endpoints, recebem **DTOs**, chamam os serviços e retornam **JSON**.

---

## Banco de Dados H2

O projeto utiliza o **H2** como banco de dados em memória, facilitando testes sem necessidade de SGBD externo.

**Exemplo de configurações** no `application-test.properties`:

```properties
# H2 Connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 Client
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Show SQL
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Para acessar o console do H2, abra `http://localhost:8080/h2-console` e use as credenciais acima.

---

## Endpoints Principais

A seguir, alguns exemplos (cada *controller* terá rotas semelhantes):

### Users
- **POST /users**: cria um novo usuário (recebe JSON com `nome`, `email`, `password`).  
- **GET /users**: lista todos os usuários cadastrados.  
- **GET /users/{id}**: busca um usuário específico.  
- **PUT /users/{id}**: atualiza dados de um usuário.  
- **DELETE /users/{id}**: remove um usuário.

### Products
- **POST /products**: cria um produto (`nome`, `descricao`, `preco`).  
- **GET /products**: lista todos.  
- **GET /products/{id}**: mostra detalhes de um produto.  
- **PUT /products/{id}**: atualiza informações.  
- **DELETE /products/{id}**: remove um produto.

### Orders
- **POST /orders**: cria um pedido (via `CreateOrderDTO`) com lista de itens (`productId`, `quantity`).  
- **GET /orders**: lista todos os pedidos.  
- **GET /orders/{id}**: detalhes de um pedido (itens, total etc.).  
- **PATCH /orders/{id}/status**: altera o status (por exemplo, de `CREATED` para `CONFIRMED`).  
- **DELETE /orders/{id}**: remove um pedido se seu status permitir.

---

## Como Rodar o Projeto

1. **Clonar o repositório**:
```bash
git clone https://github.com/seu-usuario/order-management-system.git
```

2. **Importar** no IDE (Eclipse, IntelliJ, VS Code etc.) como projeto **Maven** ou **Gradle**.

3. **Executar** a classe principal (por exemplo, `OrderManagementApplication`).

4. O projeto subirá em `http://localhost:8080` (porta padrão do Spring Boot).

5. **Testar** via **Postman**, **Insomnia** ou diretamente no **Swagger UI** (se configurado) em `http://localhost:8080/swagger-ui.html`.

---

## Exemplo de Requisição

### Criar Pedido (POST /orders)

**Request JSON**:
```json
{
  "userId": 1,
  "items": [
    { "productId": 10, "quantity": 2 },
    { "productId": 12, "quantity": 1 }
  ]
}
```

**Resposta (exemplo)**:
```json
{
  "id": 100,
  "userId": 1,
  "items": [
    { "productId": 10, "quantity": 2, "subtotal": 100.0 },
    { "productId": 12, "quantity": 1, "subtotal": 50.0 }
  ],
  "total": 150.0,
  "status": "CREATED",
  "orderDate": "2025-01-15T12:34:56"
}
```

---

## Possíveis Extensões Futuras

- **Autenticação e Autorização** (Spring Security)  
- **Integração com Pagamentos** (simular status “PAID”)  
- **Front-End** (Angular, React etc.) para consumir os endpoints.

---

## Contribuições 
Este projeto foi criado como **demonstração para currículo**, mostrando o uso de **DTOs**, **relacionamentos de entidades**, **lógica de negócios** e **API REST** em **Spring Boot**.

**Obrigado por conferir o projeto!**
