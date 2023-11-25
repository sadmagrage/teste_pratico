Realizar compras estava escrito "relizar compras".

Dos produtos eu troquei o nome de quantidade para qtdEstoque, para facilitar o entendimento a primeira vista.

Chá gelado estava escrito "ché".

Modifiquei o método executar separando o código entre o que a empresa faz e o cliente, passei para suas respectivas classes, então criei um escolherOpcao para cada um, na classe Cliente o método para realizarCompras, verCompras.

Após a separação do método executar, ficou mais fácil para poder fazer a reutilização do usuário para caso ele deseje efetuar mais operações sem ter que realizar o login a cada vez. É feita uma confirmação se o usuário deseja continuar logado, se digitar 1, o while continuará repetindo a função de escolha de opção, qualquer número digitado fora esse, retornará um false para a variável condicional, saindo do looping.

Relato: se passar o username e a senha separados por um espaço quando for pedido o usuário, ele entrará também, exemplo: "admin 1234"

Quando logado como admin após realizar uma compra, na tentativa de finalizá-la, o sistema retorna um erro se o usuário não é cliente, por tentar pegar o primeiro cliente de uma lista de clientes que está vazia. Uma mensagem será retornada para caso o usuário não seja um cliente, impossibilitando sua compra.

Assim como o erro anterior, o sistema gera outro erro quando é digitado um valor diferente de id das empresas registradas, para resolver, foi feita uma verificação primeiro, se o valor digitado é valido. Se não for, é retornado ao menu para escolher as empresas novamente.

Um id de produto digitado, mesmo que não for da empresa digitada inicialmente, ela será registrada mesmo assim. Melhor descrevendo, o sistema está permitindo que seja comprado um item que não é da respectiva empresa, não gera nenhum erro nem informações, quando visualizar as compras, ela aparecerá o item sendo ligado a empresa na qual foi comprada, não a qual ela é proveniente

O carrinho se não tiver nada registrado, quando a função verCompras é executada, o resultado mostra que foi comprado um objeto vazio, foi resolvido colocando uma condição para executar criarVenda quando tiver ao menos 1 item no carrinho.

Visualizando o que foi comprado, se nada foi comprado, fica vazio, corrigido colocando uma condição para quando não houverem itens comprados, mostrar uma mensagem de que nenhum compra foi realizada.

Para fazer com que o administrador tivesse acesso a todas as informações, consideramos ele como uma empresa, para seguir o caminho das mesmas, mas com um if para separá-la das demais, fazendo com que ele tenha acesso a informação de todas as empresas e todos os produtos.

Mensagem das vendas faltando o ": R$" no total líquido.