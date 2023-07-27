# VR Benefícios - Mini autorizador

Pequeno sistema para autorizar ou não uma transação no Vale Refeição ou Vale Alimentação.

## Criação de cartões

Serviço para criação de cartões:

- todo cartão deverá ser criado com um saldo inicial de R$500,00
- presume-se que o número do cartão seja composto apenas por números e que tenha o tamanho 16
- Caso o cartão seja criado deve develver o status 201 - Created - com os dados do cartão criado em um json
- Caso o cartão já exista, deve retornar o erro 422 - unprocessable entity - com os dados do cartão que foi enviado no json. Não vou retornar os dados do cartão existente para não vazar a senha.

### Contrato:

```
Method: POST
URL: http://localhost:8080/cartoes
Body (json):
{
    "numeroCartao": "6549873025634501",
    "senha": "1234"
}
```

#### Possíveis respostas

```
Criação com sucesso:
   Status Code: 201
   Body (json):
   {
      "senha": "1234",
      "numeroCartao": "6549873025634501"
   } 
-----------------------------------------
Caso o cartão já exista:
   Status Code: 422
   Body (json):
   {
      "senha": "1234",
      "numeroCartao": "6549873025634501"
   } 
```

## Obetenção de saldo

Serviço para obetenção de saldo do cartão.

- Caso o cartão não exista deve ser lançado o erro 404 - Not Found, sem body.
- Caso o cartão exista retorna o status 200 - OK - e o valor do saldo.

### Contrato

```
Method: GET
URL: http://localhost:8080/cartoes/{numeroCartao} , onde {numeroCartao} é o número do cartão que se deseja consultar
```

#### Possíveis respostas

```
Obtenção com sucesso:
   Status Code: 200
   Body: 495.15 
-----------------------------------------
Caso o cartão não exista:
   Status Code: 404 
   Sem Body
```

## Autorização de transações

Serviço para autorização de transações realizadas usando os cartões previamente criados como meio de pagamento.

Uma transação pode ser autorizada se:
   * o cartão existir
   * a senha do cartão for a correta
   * o cartão possuir saldo disponível
   * neste caso deve retornar o Status 201 - Created. E 'OK' no body.
   * Caso não seja autorizado lancar o status 422 - unprocessable entity - e colocar um destes motivos no body: SALDO_INSUFICIENTE|SENHA_INVALIDA|CARTAO_INEXISTENTE

### Contrato

```
Method: POST
URL: http://localhost:8080/transacoes
Body (json):
{
    "numeroCartao": "6549873025634501",
    "senhaCartao": "1234",
    "valor": 10.00
}
```

#### Possíveis respostas:
```
Transação realizada com sucesso:
   Status Code: 201
   Body: OK 
-----------------------------------------
Caso alguma regra de autorização tenha barrado a mesma:
   Status Code: 422 
   Body: SALDO_INSUFICIENTE|SENHA_INVALIDA|CARTAO_INEXISTENTE (dependendo da regra que impediu a autorização)
```

## Autenticação de usuários

Autenticação usando o usuário criado pelo MongoDB quando sobe no docker compose.
