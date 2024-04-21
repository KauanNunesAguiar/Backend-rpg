# Caracterização de Personagens RPG

Este projeto consiste em um sistema de criação e gerenciamento de personagens para jogos de RPG (Role-Playing Game) desenvolvido em Java com o framework Spring Boot.

## Funcionalidades Principais

- **Criação de Personagem**: Permite a criação de um novo personagem especificando o nome e, opcionalmente, pontos de vida (hp) (default 10 hp).
- **Busca de Personagem por ID ou Nome**: Permite buscar um personagem existente com base no seu ID ou nome.
- **Rolar Dados**: Possibilita rolar dados usando uma notação específica, como "1d20+2" para rolar um dado de 20 lados e somar 2 ao seu resultado.
- **Realizar Ações em Personagem**: Permite realizar ações em um personagem, como curar ou receber dano.

## Endpoints Disponíveis

- **/personagem/criar**: era para ser POST, mas só funciona em GET (não sei o porquê) para criar um novo personagem.
- **/personagem/procurar/id:{id}**: GET para buscar um personagem por ID.
- **/personagem/procurar/nome:{nome}**: GET para buscar um personagem por nome.
- **/personagem/rolar/{notacao}**: GET para rolar dados usando uma notação específica.
- **/personagem/ação/**: GET para realizar ações em um personagem, como curar ou causar dano.
- **/personagem/listar**: GET para listar todos os personagens criados.
- **/personagem/ajuda**: GET para adiquir nome do programador e do projeto.

## Estrutura do Projeto

O projeto está estruturado em três pacotes principais:

- **Controllers**: Contém o controlador responsável por gerenciar as requisições HTTP e seus respectivos endpoints.
- **Models**: Contém as classes que representam os objetos do domínio, como Personagem e PersonagemRequest.
- **Services**: Contém a lógica de negócio do projeto, como a criação e manipulação dos personagens.

## Dependências

Este projeto utiliza a API [rpg-dice-roller](https://github.com/djpeacher/rpg-dice-roller-api) para rolagem de dados.

Para executar o projeto, é necessário ter o Maven e o Java instalados.

## Autor
Este projeto foi desenvolvido por Kauan Nunes Aguiar.
