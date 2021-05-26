# java-spring-rabbit-mq

### O que é RabbitMQ:
RabbitMQ é um servidor de mensageria de código aberto (open source) desenvolvido em Erlang, implementado para suportar mensagens em um protocolo denominado Advanced Message Queuing Protocol (AMQP). Ele possibilita lidar com o tráfego de mensagens de forma rápida e confiável, além de ser compatível com diversas linguagens de programação, possuir interface de administração nativa e ser multiplataforma.

### O que messageria?
Em poucas palavras, a Mensageria ou Messaging consiste na utilização de mensagens para estabelecer a comunicação síncrona ou assíncrona entre aplicações.

Onde uma mensagem pode ser definida como uma estrutura de dados composta por meta-dados como host de origem/destino, fila de destino, etc, além de dados fornecidos pela aplicação, por exemplo os dados de um cliente a ser cadastrado.


### Onde usar messageria?

* **Cenario - 01.** <br>
onde o seu WebService esteja indisponível todas as requisições feitas para ele serão perdidas. Porém quando utilizamos um messageria para intermediar a comunicação nós ganhamos resiliência, pois mesmo que a outra ponta esteja indisponível, a requisição será recebida e armazenada e assim que o sistema voltar a funcionar ele irá ler todas as mensagens e processar todas as requisições que ficaram enfileiradas no MOM, ou seja, você diminui bastante o acoplamento da integração.


* **Cenario - 02.**<br>
Onde temos um sistema de uma loja que chama um outro sistema que gera nota fiscal, sem um messageria, caso o sistema de notas fique indisponível a loja ficará incapaz de vender pois ocorrerá erros, porém com o messageria a requisição será enfileirada e a nota fiscal será gerada posteriormente tornando o problema transparente para o usuário.
