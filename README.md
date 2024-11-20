Conversor de Moedas
Este é um projeto em Java que permite converter valores entre diferentes moedas utilizando a API ExchangeRate-API. O usuário pode selecionar a moeda de origem, a moeda de destino e inserir o valor a ser convertido. O programa se comunica com a API para obter as taxas de câmbio em tempo real e realiza os cálculos.

Funcionalidades
Conversão entre as seguintes moedas:
Real (BRL) ↔ Dólar Americano (USD)
Real (BRL) ↔ Euro (EUR)
Dólar Americano (USD) ↔ Euro (EUR)
Menu interativo para selecionar a conversão desejada.
Validação de entrada para evitar erros com dados incorretos.
Exibição do valor convertido com duas casas decimais.
Tratamento de erros em caso de falhas na API ou na conexão.

Tecnologias Utilizadas
Java 11+
Uso da biblioteca HttpClient para requisições HTTP.
Parsing de JSON com a biblioteca org.json para processar as respostas da API.
ExchangeRate-API:
Obtenção das taxas de câmbio em tempo real.

Siga as instruções do menu interativo.
Exemplo de Uso
O programa exibe um menu com opções de conversão.
O usuário seleciona a conversão desejada, insere o valor e recebe o resultado convertido.
Exemplo:
=== Conversor de Moedas ===
1. Real (BRL) para Dólar Americano (USD)
2. Real (BRL) para Euro (EUR)
...
Escolha uma opção: 1
Digite a quantidade em BRL: 100
100.00 BRL equivalem a 19.23 USD

Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir um Pull Request ou relatar problemas na seção de Issues.

Licença
Este projeto é de uso livre. Não há restrições para modificações ou distribuição.

Autor
Leonardo Meyer
Contato: leomeyer78@gmail.com
