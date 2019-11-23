- Maven é um gerenciador de depêndencias Java
mvnw package
mvnw clean

- Como o DevTools funciona ?
Ele usa dois tipos de loader o Class Loader Base e o Class Loader Restart
As dependencias que a gente adiciona no projeto ficam no Class Loader Base
O nosso codigo fonte fica no Class Loader Restart
Toda vez que a gente salva o código fonte ele faz o reload e restartar o servidor

- O que é injeção de depêndencias ?
É a ação de inserir uma dependencia em outra classe. 
Seja um arquivo, configuracao, um container um, controler externo, outra classe.
@Autowired é a forma mais comum fazendo com Spring

Spring Context (Spring IoC Container)
Beans sao gerenciados pelo container do spring

@Component

[Injetando dependencias (beans Spring)]
O Spring instancia os beans na ordem de necessidade das injecoes. 
Se o Notificador e passado no construtor de AtiviacaoClienteService 
entao o Notificador sera instanciado primeiro

[Usando @Configuration e @Bean para definir beans]
@Configuration o objetivo e servidor como definicao de outros Beans
Vantagens a gente pode configurar o bean da forma que quizermos
Voce pode usar o @Configuration com @Bean ou o @Component para definir os seus Beans

[Conhecendo os pontos de injecao e a anotacao @Autowired]
1. Voce pode injetar atraves do construtor
2. Se voce tiver mais que um construtor e precisar decidir qual deve ser usado, vc pode user o @Autowired