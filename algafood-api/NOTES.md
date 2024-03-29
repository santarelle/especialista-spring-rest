# Maven é um gerenciador de depêndencias Java

mvnw package
mvnw clean

# Como o DevTools funciona ?

Ele usa dois tipos de loader o Class Loader Base e o Class Loader Restart
As dependencias que a gente adiciona no projeto ficam no Class Loader Base
O nosso codigo fonte fica no Class Loader Restart
Toda vez que a gente salva o código fonte ele faz o reload e restartar o servidor

# O que é injeção de depêndencias ?

É a ação de inserir uma dependencia em outra classe. 
Seja um arquivo, configuracao, um container um, controler externo, outra classe.
@Autowired é a forma mais comum fazendo com Spring

# Spring Context (Spring IoC Container)

Beans sao gerenciados pelo container do spring

@Component

# Injetando dependencias (beans Spring)

O Spring instancia os beans na ordem de necessidade das injecoes. 
Se o Notificador e passado no construtor de AtiviacaoClienteService 
entao o Notificador sera instanciado primeiro

# Usando @Configuration e @Bean para definir beans

@Configuration o objetivo e servidor como definicao de outros Beans
Vantagens a gente pode configurar o bean da forma que quizermos
Voce pode usar o @Configuration com @Bean ou o @Component para definir os seus Beans

# Conhecendo os pontos de injecao e a anotacao @Autowired
1. Usando o construtor para injetar beans. Se voce tiver mais que um construtor e precisar decidir qual deve ser usado, vc pode user o @Autowired
2. Usando o metodo de setter com @Autowired vc tambem pode injetar beans
3. Usando o @Autowired diretamente na declaracao do atributo da classe

# Dependencia opcional com @Autowired

Quando anotamos com @Autowired(required = false) tornamos a injecao opcional. Caso exista um bean o Spring faz a injecao caso contrario, o valor fica null.
Sem o required = false para deixar opcional o Spring da erro Not Found e nao inicializa a aplicacao

# Ambiguidade de beans e injecao de lista de beans

Quando temos dois beans repetidos o Spring por si so nao sabe qual deve ser injetado. Exemplo e usando uma interface com duas ou mais implementacoes.
Uma forma de resolver isso e usando uma lista List<Notificador> notificadores. O Spring vai pegar todas as implementacoes e injetar em uma lista

# Desambiguacao de beans com @Primary

Quando anotamos um Bean com @Primary o Spring vai usar este bean na hora de uma injecao ambigua

# Desambiguacao de beans com @Qualifier

Quando anotamos um Bean com @Qualifier estamos dando um apilido para este beans e podemos usar este @Qualifier("nome") para identificar a injecao de beans em outra classe.

# Desambiguacao de beans com anotacao customizada

E possivel resolver criando uma anotacao que e anotada com @Qualifier e um @Retention do tipo RUNTIME.

# Mudando o comportamento da aplicacao com Spring Profiles
Quando usamos o profile o Spring inicializa somente os beans references aquele profile.

No arquivo application.properties podemos usar multiplos profiles ao mesmo tempo. 

Ex: prod,mysql,smtp,s3

Ex: dev,h2,mailmock,filesystem

# Criando metodos de callback do ciclo de vida dos beans

O clico de vida do bean dentro do container tem as fases: inicializacao, usando o bean e destruicao do bean.

Podemos usar metodos de callback quando o bean mudar de estado com anotacoes: 

1. @PostConstruct - E executado apois o contrutor da classe e @PreDestroy - E executado quando o bean vai ser destruido

2. @Bean(initMethod = "init", destroyMethod = "destroy") - Usando essa anotacao e passando o nome dos metodos a serem executados como string.

3. Implements InitializingBean, DisposableBean - Implementando essas interfaces vc nao precisa usar o PostConstruct e PreDestroy (nao e muito recomendado essa forma)

# Publicando e consumindo eventos customizados

Ele utiliza o padrao Observer que emite e escuta eventos quando determinada acao acontece

Injetando o bean `ApplicationEventPublisher` em uma classe e possivel enviar eventos usando o metodo `publishEvent`

Usando a anotacao `@EventListener` em um metodo e possivel escutar um evento quando ele e disparado e fazer algo com ele 

# Configurando projetos Spring Boot com o application.properties

E definido como chave e valor. Podemos configurar novas propriedades como tambem substituir as propriedades que o spring boot ja fornece.

Lista de propriedades comuns do proprio spring boot podem ser visualizadas pelo link abaixo:

https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

# Substituindo propriedades via linha de comando e variáveis de ambiente

Com o .jar da aplicacao gerado e possivel usar o --propriedade=valor via linha de comando

```
java -jar algafood-api.jar --server.port=8082
```

Outra forma e usando variavel de ambiente:

```
export SERVER_PORT=8083
java -jar algafood-api.jar
```

# Criando e acessando propriedades customizadas com @Value

```
@Value("${nome.da.propriedade}")
private String nomeDaPropriedade;
```

# Acessando propriedades com @ConfigurationProperties

Usado para agrupar propriedades do servidor em uma classe de configuracao.

Voce define os atributos e gera os getters e setters juntamente com o anotacao de `@ConfigurationProperties`.

Para usar voce injeta o beans em outra classe e usa as propriedades

# Alterando a configuração do projeto dependendo do ambiente (com Spring Profiles)

Usado para definir ambientes como Producao, Desenvolvimento, Testes entre outros.

Criando arquivos `application-*.properties` voce pode definir varios profiles.

Uma forma de usar e setando spring.profiles.active=prod no arquivo application.properites por exemplo.

# Ativando o Spring Profile por linha de comando e variável de ambiente

```
java -jar target/algafood-api-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

ou:

```
export SPRING_PROFILES_ACTIVE=prod
java -jar target/algafood-api-0.0.1-SNAPSHOT.jar
```

# O que é JPA e Hibernate?

## O que e persistencia de dados ?

E um termo utilizando para dizer que queremos preservar os dados da aplicacao. Normalmente usado um banco de dados.

## Persitencia com banco de dados usando Java

```
Codigo Java -> Driver JDBC -> MySQL
```

Cada banco de dados tem pelo menos uma implementacao de JDBC que e o responsavel com comunicar a aplicacao com o banco de dados.

Um link para um artigo sobre JPA: https://blog.algaworks.com/tutorial-jpa/

Em Java, sem usar JPA vc usa o PreparedStatement, Statement, ResultSet isso leva muito tempo e tem grandes possibilidades de erros, pq vc tem que associar o resultado com os atributos de classe manualmente.

## O que e ORM (Object Relational Mapping) ?

E uma tecnica para mapeamento orientadas a objetos para uma tabela de banco de dados, de forma que o programador trabalha mais com orientacao a objetos e menos no banco de dados.

## Mapeamento Objeto-Relacional
| Modelo Relacional | Modelo OO
| Tabela | Classe
| Linha | Objeto
| Coluna | Atributo
| N/A | Metodo
| Chave estrangeira (FK) | Associacao

## Persistencia com ORM

```
Codigo Java -> ORM -> Driver JDBC -> MySQL
```

## JPA (Java Persistente API) ?

JPA define a forma de ORM (Anotacoes)

JPA e uma especificacao JEE

## Hibernete ?

E uma implementacao da especificacao JPA.

Hibernete e a mais usada mas tem o EclipseLink.

# Conhecendo o padrão Aggregate do DDD

https://martinfowler.com/bliki/DDD_Aggregate.html

# URL para authorization code

http://auth.algafood.local:8081/oauth/authorize?response_type=code&client_id=algafood-analytics&state=abc&redirect_uri=http://aplicacao-algafood


http://auth.algafood.local:8081/oauth/authorize?response_type=token&client_id=algafood-webadmin&state=abc&redirect_uri=http://aplicacao-cliente