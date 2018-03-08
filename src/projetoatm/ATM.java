//Representa um caixa eletronico
package projetoatm;

public class ATM
{
    private boolean userAutenticado; //Se o usuario foi autenticado
    private int numeroConta; //Numero atual da conta do usuario
    private Tela tela; //Tela do ATM
    private Teclado teclado; //Teclado do ATM
    private DiscipadorCedulas discipadorCedulas; //Dispensador de cedulas do ATM
    private EntradaDeposito entradaDeposito; //Abertura para deposito do ATM
    private DataBase dataBase; //Banco de dados com as informações sobre as Contas
    
    //Constantes que correspondem as principais opções de menu
    private static final int PESQUISA_SALDO = 1;
    private static final int SAQUE = 2;
    private static final int DEPOSITO = 3;
    private static final int SAIR = 4;
    
    //Construtor sem argumentos de ATM inicializa as variaveis de instancia
    public ATM()
    {
        userAutenticado = false; //Usuario nao foi autenticado para iniciar
        numeroConta = 0; //Nenhum numero atual de conta para iniciar
        tela = new Tela(); //Cria a tela
        teclado = new Teclado(); //Cria o teclado
        discipadorCedulas = new DiscipadorCedulas();  //Cria o dispensador de cedulas
        entradaDeposito = new EntradaDeposito();  //Cria a abertura para deposito
        dataBase = new DataBase(); //Cria o banco de dados com infoemações sobre as contas
    } //Fim do construtor ATM sem argumentos
    
    //Inicia o ATM
    public void iniciar()
    {
        //Da boas-vindas e autentica o usuario; realiza transações
        while(true)
        {
            //faz um loop enquanto o usuario ainda nao esta autenticado
            while(!userAutenticado)
            {
                tela.exibirMensagemLinha("\nBem-Vindo!");
                autenticarUser(); //autentica o usuario
            }//Fim do while
            
            executarTransacao(); //O usuario agora esta autenticado
            userAutenticado = false; //Reinicializa antes da proxima sessao do ATM
            numeroConta = 0; //Reinicializa antes da proxima seçao do ATM
            tela.exibirMensagemLinha("\nObrigado! Até mais!");
        }// Fim do while
    }//Fim do iniciar
    
    //Tenta autenticar o usuario contra o banco de dados
    private void autenticarUser()
    {
        tela.exibirMensagem("\nPor favor, entre com seu numero da conta: ");
        int auxNumeroConta = teclado.getInput(); //Insere o numero de conta
        tela.exibirMensagem("\nEntre com seu PIN: "); //Solicita o PIN
        int pin = teclado.getInput(); //Insere o PIN
        
        //Configura userAtenticado como um valor booleano retornado pelo Banco de Dados
        userAutenticado = dataBase.autenticaUser(auxNumeroConta, pin);
        
        //Verifica se a autenticação foi bem-sucedida
        if(userAutenticado)
        {
            numeroConta = auxNumeroConta; //Salva a conta do usuario #
        }// Fim if
        else
        {
            tela.exibirMensagemLinha("Numero de conta ou PIN invalido. Por favor, Tente novamente.");
        }//Fim do else
    }//Fim do metodo autenticaUser
    
    //Exibe o menu principal e realiza as transações
    private void executarTransacao()
    {
        //Variavel local para armazanar a transação atualmente processada
        Transacao auxTransacao = null;
        
        boolean userSaiu = false; //Usuario optou por nao sair
        
        //Faz um loop enquanto o usuario nao escolher a opção para sair do sistema
        while(!userSaiu)
        {
            //Mostra o menu principal e obtém a seleção de usuario
            int opcaoEscolhida = exibirMenuPrincipal();
            
            //Decide como prosseguir com base na seleção de menu feita pelo usuario
            switch(opcaoEscolhida)
            {
                //O usuario optou por realizar um entre tres tipos de transações
                case PESQUISA_SALDO:
                case SAQUE:
                case DEPOSITO:
                    
                    //Inicializa como o novo objeto do tipo escolhido
                    auxTransacao = criarTransacao(opcaoEscolhida);
                                
                    auxTransacao.execute(); //Executar a transação
                    break;
                    
                case SAIR: //O usuario optou por terminar a sessao  
                    tela.exibirMensagemLinha("\nSaindo do sistema...");
                    userSaiu = true; //Essa sessão de ATM deve terminar
                    break;
                    
                default: //Usuario nao inseriu um numero inteiro de 1 a 4
                    tela.exibirMensagemLinha("\nVocê não entrou com um valor de seleção valido. Tente Novamente. ");
                    break;
            }// Fim do switch
        }//Fim do while
    }// Fim do metodo executarTransacao
    
    //Exibe o menu principal e retorna uma seleção de entrada
    private int exibirMenuPrincipal()
    {
        tela.exibirMensagemLinha("\nMENU PRINCIPAL: ");
        tela.exibirMensagemLinha("1 - Ver meu sado");
        tela.exibirMensagemLinha("2 - Sacar dinheiro");
        tela.exibirMensagemLinha("3 - Depositar dinheiro");
        tela.exibirMensagemLinha("4 - Sair\n");
        tela.exibirMensagemLinha("Escolha uma das opções acima: ");
        return teclado.getInput(); //Retorna a seleção do usuario
    }// Fim do metodo exibirMenuPrincipal
    
    //Retorna o objeto da subclasse de transação especificado
    private Transacao criarTransacao(int tipo)
    {
        Transacao temp = null; //Variavel Transacao temporaria
                
        //Determina qual tipo de Transacao criar
        switch(tipo)
        {
            case PESQUISA_SALDO: //Cria uma nova transação PesquisaSaldo
                temp = new PesquisaSaldo(numeroConta, tela, dataBase);
                break;
            case SAQUE: //Cria uma nova transação Saque
                temp = new Saque(numeroConta, tela, dataBase, teclado, discipadorCedulas);
                break;
            case DEPOSITO: //Cria uma nova transação Deposito
                temp = new Deposito(numeroConta, tela, dataBase, teclado, entradaDeposito);
                break;
        }//Fim do switch
        
        return temp; //Retorna o objeto recem-criado
    }// Fim do metodo criarTransacao  
}//Fim da classe ATM









