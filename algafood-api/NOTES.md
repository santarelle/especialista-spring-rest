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
1. Usando o construtor para injetar beans. Se voce tiver mais que um construtor e precisar decidir qual deve ser usado, vc pode user o @Autowired
2. Usando o metodo de setter com @Autowired vc tambem pode injetar beans
3. Usando o @Autowired diretamente na declaracao do atributo da classe

[Dependencia opcional com @Autowired]
Quando anotamos com @Autowired(required = false) tornamos a injecao opcional. Caso exista um bean o Spring faz a injecao caso contrario, o valor fica null.
Sem o required = false para deixar opcional o Spring da erro Not Found e nao inicializa a aplicacao

[Ambiguidade de beans e injecao de lista de beans]
Quando temos dois beans repetidos o Spring por si so nao sabe qual deve ser injetado. Exemplo e usando uma interface com duas ou mais implementacoes.
Uma forma de resolver isso e usando uma lista List<Notificador> notificadores. O Spring vai pegar todas as implementacoes e injetar em uma lista

[Desambiguacao de beans com @Primary]
Quando anotamos um Bean com @Primary o Spring vai usar este bean na hora de uma injecao ambigua

[Desambiguacao de beans com @Qualifier]
Quando anotamos um Bean com @Qualifier estamos dando um apilido para este beans e podemos usar este @Qualifier("nome") para identificar a injecao de beans em outra classe.

[Desambiguacao de beans com anotacao customizada]
E possivel resolver criando uma anotacao que e anotada com @Qualifier e um @Retention do tipo RUNTIME.