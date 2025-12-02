# **API Autobots — Atividade**
## Tecnologias Utilizadas

- Java 17

- Spring Boot

- Spring Web

- Spring Data JPA

- Spring HATEOAS

- H2 Database

- Lombok

- Maven

## Como Executar o Projeto

### Pré-requisitos

- JDK 17 instalado

- Maven instalado

- MySQL instalado e rodando


### Configuração do Banco de Dados (MySQL)

Criar o banco manualmente:

```SQL
CREATE DATABASE base;
```

## Configurar as credenciais no arquivo
src/main/resources/application.properties:

```
#conexao
spring.datasource.url=jdbc:mysql://localhost:3306/base
spring.datasource.username=root
spring.datasource.password=fatec
#comandos
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql: true

#logging.level.org.hibernate.SQL=debug
#logging.level.org.hibernate.type.descriptor.sql=trace
```

Caso esteja usando outro nome de banco, usuário ou senha, ajuste na URL.

### Rodando o projeto

mvn spring-boot:run

### A API ficará disponível em:

http://localhost:8080


## Rotas relacionadas à **Usuario**:
```
GET /usuario/{id}
Obter um usuário específico pelo ID.
Retorna um objeto UsuarioDTO com links HATEOAS.

GET /usuario/usuarios
Obter todos os usuários.
Retorna uma lista de UsuarioDTO com links HATEOAS.

POST /usuario/cadastro
Cadastrar um novo usuário.
Recebe no corpo da requisição uma instância de Usuario.
Não é permitido enviar `id`.

PUT /usuario/atualizar
Atualizar os dados de um usuário existente.
Recebe no corpo da requisição uma instância de Usuario contendo o campo 'id'.

DELETE /usuario/excluir
Excluir um usuário existente.
Recebe no corpo da requisição uma instância de Usuario com o 'id' a ser removido.

```

## Rotas relacionadas à **Documento**:

```
GET /documento/{id}
Obter um documento específico pelo ID.
Retorna um objeto DocumentoDTO com links HATEOAS.

GET /documento/documentos
Obter todos os documentos.
Retorna uma lista de DocumentoDTO com links HATEOAS.

POST /documento/cadastro
Cadastrar um novo documento.
Recebe no corpo da requisição uma instância de Documento.
Não é permitido enviar `id`.

PUT /documento/atualizar
Atualizar os dados de um documento existente.
Recebe no corpo da requisição uma instância de Documento contendo o campo 'id'.

DELETE /documento/excluir
Excluir um documento existente.
Recebe no corpo da requisição uma instância de Documento com o 'id' a ser removido.
```

## Rotas relacionadas à **Endereço**:

```
GET /endereco/{id}
Obter um endereço específico pelo ID.
Retorna um objeto EnderecoDTO com links HATEOAS.

GET /endereco/enderecos
Obter todos os endereços.
Retorna uma lista de EnderecoDTO com links HATEOAS.

POST /endereco/cadastro
Cadastrar um novo endereço.
Recebe no corpo da requisição uma instância de Endereco.
Não é permitido enviar `id`.

PUT /endereco/atualizar
Atualizar os dados de um endereço existente.
Recebe no corpo da requisição uma instância de Endereco contendo o campo 'id'.

DELETE /endereco/excluir
Excluir um endereço existente.
Recebe no corpo da requisição uma instância de Endereco com o 'id' a ser removido.

```

## Rotas relacionadas à **Telefone**:

```
GET /telefone/telefone/{id}
Obter um telefone específico pelo ID.
Retorna um objeto TelefoneDTO com links HATEOAS.

GET /telefone/telefones
Obter todos os telefones.
Retorna uma lista de TelefoneDTO com links HATEOAS.

POST /telefone/cadastro
Cadastrar um novo telefone.
Recebe no corpo da requisição uma instância de Telefone.
Não deve ser enviado 'id' no corpo.

PUT /telefone/atualizar
Atualizar os dados de um telefone existente.
Recebe no corpo uma instância de Telefone contendo o campo 'id'.

DELETE /telefone/excluir
Excluir um telefone existente.
Recebe no corpo uma instância de Telefone com o 'id' a ser removido.
```

## Rotas relacionadas à **Mercadoria**:

```
GET /mercadoria/mercadoria/{id}
Obter uma mercadoria específica pelo ID.
Retorna um objeto MercadoriaDTO com links HATEOAS.

GET /mercadoria/mercadorias
Obter todas as mercadorias.
Retorna uma lista de MercadoriaDTO com links HATEOAS.

POST /mercadoria/cadastro
Cadastrar uma nova mercadoria.
Recebe no corpo da requisição uma instância de Mercadoria.
Não deve ser enviado 'id' no corpo.

PUT /mercadoria/atualizar
Atualizar os dados de uma mercadoria existente.
Recebe no corpo uma instância de Mercadoria contendo o campo 'id'.

DELETE /mercadoria/excluir
Excluir uma mercadoria existente.
Recebe no corpo uma instância de Mercadoria com o 'id' a ser removido.
```

## Rotas relacionadas à **Servico**:

```
GET /servico/servico/{id}
Obter um serviço específico pelo ID.
Retorna um objeto ServicoDTO com links HATEOAS.

GET /servico/servicos
Obter todos os serviços.
Retorna uma lista de ServicoDTO com links HATEOAS.

POST /servico/cadastro
Cadastrar um novo serviço.
Recebe no corpo da requisição uma instância de Servico.
Não deve ser enviado 'id' no corpo.

PUT /servico/atualizar
Atualizar os dados de um serviço existente.
Recebe no corpo uma instância de Servico contendo o campo 'id'.

DELETE /servico/excluir
Excluir um serviço existente.
Recebe no corpo uma instância de Servico com o 'id' a ser removido.
```

## Rotas relacionadas à **Veiculo**:

```
GET /veiculo/veiculo/{id}
Obter um veículo específico pelo ID.
Retorna um objeto VeiculoDTO com links HATEOAS.

GET /veiculo/veiculos
Obter todos os veículos.
Retorna uma lista de VeiculoDTO com links HATEOAS.

POST /veiculo/cadastro
Cadastrar um novo veículo.
Recebe no corpo da requisição uma instância de Veiculo.
Não deve ser enviado 'id' no corpo.

PUT /veiculo/atualizar
Atualizar os dados de um veículo existente.
Recebe no corpo uma instância de Veiculo contendo o campo 'id'.

DELETE /veiculo/excluir
Excluir um veículo existente.
Recebe no corpo uma instância de Veiculo com o 'id' a ser removido.
```

## Rotas relacionadas à **Empresa**:

```
GET /empresa/empresa/{id}
Obter uma empresa específica pelo ID.
Retorna um objeto EmpresaDTO com links HATEOAS.

GET /empresa/empresas
Obter todas as empresas.
Retorna uma lista de EmpresaDTO com links HATEOAS.

POST /empresa/cadastro
Cadastrar uma nova empresa.
Recebe no corpo da requisição uma instância de EmpresaDTO.
Não deve ser enviado 'id' no corpo.

PUT /empresa/atualizar
Atualizar os dados de uma empresa existente.
Recebe no corpo uma instância de Empresa contendo o campo 'id'.

DELETE /empresa/excluir
Excluir uma empresa existente.
Recebe no corpo uma instância de Empresa com o 'id' a ser removido.

POST /empresa/associar/{idEmpresa}/{idUsuario}
Associar um usuário já existente a uma empresa.
{idEmpresa} → ID da empresa.
{idUsuario} → ID do usuário.
Adiciona o usuário à lista de usuários da empresa.
```

## Rotas relacionadas à **Venda**:

```
GET /venda/{id}
Obter uma venda específica pelo ID.
Retorna um objeto VendaDTO com links HATEOAS.

GET /venda/vendas
Obter todas as vendas.
Retorna uma lista de VendaDTO com links HATEOAS.

POST /venda/cadastro
Cadastrar uma nova venda.
Recebe no corpo da requisição uma instância de Venda.
Não deve incluir o campo 'id'.

PUT /venda/atualizar
Atualizar os dados de uma venda existente.
Recebe no corpo uma instância de Venda contendo o campo 'id'.

DELETE /venda/excluir
Excluir uma venda existente.
Recebe no corpo uma instância de Venda com o 'id' da venda a ser removida.
```


📋 Modelos de Requisição (Exemplos de JSON)

POST /usuario/cadastro

Cadastra um novo usuario.

{
    "nome": "Cliente de Teste",
    "nomeSocial": "Cliente",
    "perfis": ["CLIENTE"],
    "telefones": [
        {
            "ddd": "12",
            "numero": "00000-0002"
        }
    ],
    "endereco": {
        "estado": "SP",
        "cidade": "Taubate",
        "bairro": "Interlagos",
        "rua": "Parara",
        "numero": "11",
        "codigoPostal": "12555-000"
    },
    "documentos": [
        {
            "tipo": "CPF",
            "dataEmissao": "2023-01-10T00:00:00Z",
            "numero": "111.222.333-44"
        }
    ],
    "emails": [
        {
            "endereco": "cliente@teste.com"
        }
    ]
}


PUT /usuario/atualizar

Atualiza o usuario com id: 2.

{
    "id": 2,
    "nome": "Cliente de Teste ATUALIZADO",
    "nomeSocial": "Cliente VIP",
    "perfis": ["CLIENTE"],
    "telefones": [
        {
            "ddd": "12",
            "numero": "98888-0002"
        },
        {
            "ddd": "11",
            "numero": "55555-5555"
        }
    ],
    "endereco": {
        "estado": "SP",
        "cidade": "Aaaaaa",
        "bairro": "Bbbbbb",
        "rua": "Rua atualizada",
        "numero": "20",
        "codigoPostal": "22222-222"
    },
    "documentos": [
        {
            "tipo": "CPF",
            "dataEmissao": "2023-01-10T00:00:00Z",
            "numero": "111.222.333-44"
        }
    ],
    "emails": [
        { 
            "endereco": "cliente-novo@teste.com"
        }
    ]
}

POST /veiculo/cadastro

Cadastra um veículo associando a um usuário existente.

{
    "tipo": "HATCH",
    "modelo": "Toyota Yaris",
    "placa": "ABC-1234",
    "proprietario": {
        "id": 4
    }
}

POST /servico/cadastro

Cadastra um serviço.

{
    "nome": "Troca de Óleo Sintético",
    "valor": 150.00,
    "descricao": "Troca de óleo do motor 5W30"
}

POST /mercadoria/cadastro

Cadastra uma mercadoria/peça.

{
    "nome": "Filtro de Óleo",
    "validade": "2030-01-01T00:00:00Z",
    "fabricacao": "2024-01-01T00:00:00Z",
    "quantidade": 50,
    "valor": 45.00,
    "descricao": "Filtro de óleo para motor 1.0"
}

POST /venda/cadastro

Cadastra uma venda associando IDs já existentes.

{
    "cadastro": "2025-12-02T02:31:34.200+00:00",
    "identificacao": "1234698445",
    "clienteId": 1,
    "funcionarioId": 2,
    "veiculoId": 1,
    "mercadoriasIds": [
      1
    ],
    "servicosIds": [
      1
    ]
}

POST /documento/cadastro

Cadastra um documento de forma isolada.

{
  "tipo": "CPF",
  "dataEmissao": "2023-01-10T00:00:00Z",
  "numero": "111.222.333-55"
          
}


DELETE /documento/excluir

Exclui o documento com id: 2.

{
  "id": 2
}

POST /endereco/cadastro

Cadastra um endereço.

{
  "estado": "Minas Gerais",
  "cidade": "Belo Horizonte",
  "bairro": "Savassi",
  "rua": "Rua Pernambuco",
  "numero": "450",
  "codigoPostal": "30130151",
  "informacoesAdicionais": "Apartamento 1203"
}

PUT /endereco/atualizar

Atualiza número e complemento do endereço com id: 2.

{
  "id": 2,
  "numero": "500",
  "informacoesAdicionais": "Próximo ao shopping"
}


PUT /telefone/atualizar

Atualiza o número do telefone com id: 1.

{
  "id": 1,
  "numero": "999999999"
}

DELETE /telefone/excluir

Exclui o telefone com id: 1.

{
  "id": 1
}

GET /empresa/cadastro

Cadastra uma empresa

{
  "razaoSocial": "AutoPrime Solutions Ltda",
  "nomeFantasia": "AutoPrime Centro Automotivo",
  "cadastro": "2025-11-15T10:22:18.500+00:00",
  "endereco": {
    "estado": "Rio de Janeiro",
    "cidade": "Rio de Janeiro",
    "bairro": "Botafogo",
    "rua": "Rua Voluntários da Pátria",
    "numero": "325",
    "codigoPostal": "22270-010",
    "informacoesAdicionais": "Próximo ao metrô"
  },
  "telefones": [
    {
      "ddd": "021",
      "numero": "997854123"
    }
  ],
  "usuariosIds": [
    2,
    1,
    3
  ],
  "mercadoriasIds": [
    1
  ],
  "servicosIds": [
    2,
    1
  ],
  "vendasIds": [
    1,
    2
  ]
}







