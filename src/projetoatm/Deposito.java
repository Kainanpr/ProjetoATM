//Representa uma transação de deposito no ATM
package projetoatm;

public class Deposito extends Transacao 
{
    private double valor; //Quantia a depositar
    private Teclado teclado; //Referencia ao teclado numerico
    private EntradaDeposito entradaDeposito; //Referencia a abertura para deposito
    private final static int CANCELAR = 0; //Constante para a opção de cancelamento
    
    //Construtor de Deposito
    public Deposito(int numeroConta, Tela atmTela, DataBase atmDataBase, 
            Teclado atmTeclado, EntradaDeposito atmEntradaDeposito)
    {
        //inicializa as variaveis da superclasse
        super(numeroConta, atmTela, atmDataBase);
        
        //inicializa as referencias a teclado e abertura para deposito
        this.teclado = atmTeclado;
        this.entradaDeposito = atmEntradaDeposito;
    }//Fim do construtor Deposito
    
    //Realiza a transação
    @Override
    public void execute()
    {
        DataBase dataBase = getDataBase(); //obtem a referencia
        Tela tela = getTela(); //obtem referencia
        
        valor = solicitaValorDeDeposito(); //obtem a quantia de deposito do usuario
        
        //Verifica se usuario inseriu uma quantia de deposito ou cancelou
        if(valor != CANCELAR)
        {
            //solicita o envelope de deposito contendo a quantia especificada
            tela.exibirMensagem("\nInsira um envelope de depósito ");
            tela.exibirValorDolar(valor);
            tela.exibirMensagemLinha("");
            
            //recebe o envelope de depósito
            boolean envelopeRecebido = entradaDeposito.isEnvelopeRecebido();
            
            //verifica se o envelope de deposito foi recebido
            if(envelopeRecebido)
            {
                tela.exibirMensagemLinha("\nSeu envelope foi recebido.\n"
                        + "NOTA: O dinheiro que acabou de depositar não estará disponível "
                        + "até que verifiquemos o valor de qualquer caixa fechada e "
                        + "seus cheques claros.");
                
                //Credita na conta para refletir o deposito
                dataBase.credito(getNumeroConta(), valor);
            }//Fim do if
            else
            {
                tela.exibirMensagemLinha("\nVocê não inseriu um envelope, "
                        + "então o caixa eletronico cancelou sua transação.");
            }//Fim do else
        }//fim do if
        
        else //O usuario cancelou em vez de inserir uma quantia
        {
            tela.exibirMensagemLinha("\nCancelando transação...");
        }//Fim do else
    }//Fim do metodo execute
    
    //Solicita que o usuario insira uma quantia de deposito em centavos
    private double solicitaValorDeDeposito()
    {
        Tela tela = getTela(); //obtem a referencia a tela
        
        //exibe a solicitação
        tela.exibirMensagem("\nInsira um valor de deposito em CENTAVOS (ou 0 para cancelar): ");
        int quantiaDeposito = teclado.getInput(); //recebe a entrada da quantioa do deposito
        
        //verifica se o usuario cancelou ou inseriu uma quantia valida
        if(quantiaDeposito == CANCELAR)
        {
            return CANCELAR; 
        }//Fim do if
        else
        {
            return(double) quantiaDeposito / 100; //retorna a quantia em dolares
        }//fim do else                  
    }//Fim do metodo solicitaValorDeDeposito  
}//Fim da classe Deposito
