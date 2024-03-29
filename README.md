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


### Definições básicas.

* **Queues (Filas)** <br>
As queues são as filas responsáveis por armazenar as mensagens que serão distribuídas para o canal de saída.

* **Bindings (Vínculos)** <br>
Os bindings são caminhos de entrega das mensagens, o exchange utiliza-os para identificar em qual queue a mensagem será entregue.

* **Exchanges (Permutadores)** <br>
Os exchanges são os responsáveis por distribuir as mensagens para as queues, utilizando os bindings para identificar a fila de destino daquela mensagem específica.


### Composição básica das filas

* **Producing – O produtor da mensagem** <br>
Como diz o nome, é a aplicação que produz a mensagem e a insere na fila.

* **Queue – A fila** <br>
É a caixa postal – a fila – que fica dentro do RabbitMQ e aloca as mensagens enviadas pela sua aplicação. A única limitação para uma fila é a memória de um servidor ou o limite do seu disco. Uma fila pode ser abastecida e consumida por mais de uma aplicação.

* **Consuming – O recepetor da mensagem** <br>
Esse é o destinatário da nossa mensagem. Geralmente é uma aplicação em que a principal função é receber e processar a mensagem alocada na fila.


### Instalando no Windows manualmente RabbitMQ 

* 1 - Vá ate o site do oficial do [Rabbitmq](https://www.rabbitmq.com/)

* 2 - Faça o download do rabbitmq referente ao seu S.O.

* 3 - Configure conforme o seu S.O no proprio site oficial tem o manual para cada S.O.

* 4 - Não esqueça de instalar o erl.exe

* 5 - Use a versão rabbitmq-server-3.8.16.exe mais simples de instalar.

* 6 - Adicionar a variavel de sistema<br>
    6.1 - nome da variavel = **ERLANG_HOME** <br>
    6.2 - valor da variavel = **C:\Program Files\erl-24.0** Varia conforme a maquina <br>
* 7 - Vá ate a pasta `CD C:\Program Files\RabbitMQ Server\rabbitmq_server-3.8.9\sbin` usando este comando **prompt**. <br>
    7.1 - Execute este comando: `rabbitmq-plugins.bat enable rabbitmq_management`  <br>
    7.2 - Execute este comando: `rabbitmq-plugins enable rabbitmq_shovel rabbitmq_shovel_management` <br>
    
    **Importante:** os comando executados no `7.1 e 7.2` são para liberar a interface do RabbitMq.
    
* 8 - Vá ate o diretorio C:\Windows\System32\drivers\etc\hosts.
    8.1 - Neste diretorio procure pelo arquivo `hosts` 
    8.2 - Editar o arquivo adicionando esta linha `127.0.0.1 rabbitmq`
    8.3 - Pro fim abra o seu browser e coloque a seguinte url: `http://rabbitmq:15672/` se tudo deu certo será exibida a tela de login do rabbitmq.
    8.4 - usuario: **guest** e senha: **guest**

### Instalando com Maven
Para instalar a biblioteca com maven, basta adicionar a dependência no arquivo `pom.xml`.

````
<dependency>
    <groupId>com.rabbitmq</groupId>
    <artifactId>amqp-client</artifactId>
    <version>5.12.0</version>
</dependency>
````
Neste exemplo iremos utilizar a versão 5.12.0


### Vamos colocar as mãos na massa.

* **Enviando uma mensagem para o Server** <br>
Para conectar ao server, devemos instanciar a classe ConnectionFactory e publicar a mensagem utilizando um channel, conforme exemplo abaixo:
* Trecho do codigo:

````
Producing – O produtor da mensagem

ConnectionFactory factory = new ConnectionFactory();
factory.setUsername("username");
factory.setPassword("senha");
factory.setHost("ip-server");
try (Connection connection = factory.newConnection()) {
  Channel channel = connection.createChannel();
  channel.queueDeclare("nomeDaFila", false, false, false, null);
  String mensagem = "Olá Mundo!";
  channel.basicPublish("", "nomeDaFila", null, mensagem.getBytes("UTF-8"));
}
````

* **Recebendo uma mensagem do Server** <br>
Para receber uma mensagem do server, utilize o mesmo código do envio, porém, instanciando a classe Consumer com o método de recebimento da mensagem.

````
Consuming – O recepetor da mensagem

ConnectionFactory factory = new ConnectionFactory();
factory.setUsername("username");
factory.setPassword("senha");
factory.setHost("ip-server");
try (Connection connection = factory.newConnection()) {
  Channel channel = connection.createChannel();
  channel.queueDeclare("nomeDaFila", false, false, false, null);
  Consumer consumer = new DefaultConsumer(channel) {
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
      String mensagem = new String(body, "UTF-8");
      System.out.println("Mensagem recebida: " + mensagem);
    }
  };
  channel.basicConsume("nomeDaFila", true, consumer);
}
```` 

* ### Pontos importantes:
  * A fila so precisa ser criada uma unica vez?<br>
    R: sim se todas vez que vc for enviar algo para a fila tive que criar uma fila isso deixa seu sistema mais.
    
  * A connection devemos criar varias?<br>
    R: Não.<br>
    Ao criar uma connection no ambiente de produção, evite instanciar uma nova Connection, pois o construtor cria uma conexão TCP/IP com o server destino, podendo elevar o           processamento da aplicação e transporte na rede.
    
    

Rapidamente temos um software capaz de responder à mensagens enviadas de forma assíncrona.


Creditos:<br>
[Marcela Sisiliani de Sena SIlva](https://imasters.com.br/back-end/rabbitmq-introducao-ao-mundo-das-filas)<br>
[Evandro Silva](https://medium.com/tango-labs/usando-rabbitmq-para-turbinar-suas-aplica%C3%A7%C3%B5es-java-32020f03a24c)<br>
[ABrandaoL Microsoft MVP](https://www.youtube.com/watch?v=PESoVKv0Spo)<br>
[alex_ber](https://alex-ber.medium.com/installing-rabbitmq-on-windows-4411f5114a84)
