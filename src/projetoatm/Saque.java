//Representa uma transação de SAQUE no ATM
package projetoatm;

public class Saque extends Transacao 
{
    private int valor; //Quantia a Sacar
    private Teclado teclado; //Referencia ao Teclado do ATM
    private DiscipadorCedulas discipadorCedulas; //Referencia ao Discipador de cedulas do ATM
    
    //Constante que corresponde à opção cancelar no menu
    private final static int CANCELAR = 6;
    
    //Construtor sem argumentos
    public Saque(int numeroConta, Tela atmTela, DataBase atmDataBase, 
            Teclado atmTeclado, DiscipadorCedulas atmDiscipadorCedulas)
    {
        super(numeroConta, atmTela, atmDataBase);
        
        //Inicializa as referencias ao teclado numérico e ao dispensador de cedulas
        this.teclado = atmTeclado;
        this.discipadorCedulas = atmDiscipadorCedulas;
        
    } //Fim do construtor de Saque
    
    
    //Metodo sobreescrevendo execute
    @Override
    public void execute()
    {
        boolean dinheiroDispensado = false; //Cedulas ainda nao foram entregues
        double saldoDisponivel; //Quantia disponivel para saque
        
        //Obtem as referencias ao banco de dados e tela do banco
        DataBase dataBase = getDataBase();
        Tela tela = getTela();
        
        //faz um loop ate as cedulas serem entregues ou o usuario cancelar
        do
        {
            //Obtem a quantia de um saque escolhida pelo usuario
            valor = exibirMenuDeValores();
            
            //Verifica se o usuario escolheu uma quantia de saque ou cancelou
            if(valor != CANCELAR)
            {
                //obtem o saldo disponivel na conta envolvida
                saldoDisponivel = dataBase.getSaldoDisponivel(getNumeroConta());
                
                //verifica se o usuario tem dinheiro suficiente na conta
                if(valor <= saldoDisponivel)
                {
                    //verifica se o dispensador de cedulas tem cedulas suficientes
                    if(discipadorCedulas.isSuficienteDinheiroDisponivel(valor))
                    {
                        //atualiza a conta envolvida para refletir a retirada/saque
                        dataBase.debito(getNumeroConta(), valor);
                        
                        discipadorCedulas.dispensarDinheiro(valor); //Entrega cedulas
                        dinheiroDispensado = true; //Cedulas foram entregues
                        
                        //Instrui o usuario a pegar as cedulas
                        tela.exibirMensagemLinha("\nSeu dinheiro foi dispensado. Pegue seu dinheiro agora. ");   
                    }//Fim do if
                    
                    else //O dispensador de cedulas nao tem cedulas suficientes
                    
                        tela.exibirMensagemLinha("\nDinheiro disponivel insuficiente no ATM. "
                                                 + "\n\nEscolha uma quantia menor.");                    

                }//fim do if
                
                else //Não ha dinheiro suficiente disponivel na conta do usuario
                {
                    tela.exibirMensagemLinha("\nFundos insuficiente na sua conta. "
                                             + "\n\nEscolha uma quantia menor.");
                }//fim do else
            }//fim do if
            
            else // O usuario escolheu a opcao cancelar no menu
            {
                tela.exibirMensagemLinha("\nCancelando transação...");
                return; //retorna ao menu principal pq o usuario cancelou
            }//Fim do else      
        }while(!dinheiroDispensado);//Fim do while
        
    }//Fim do metodo execute
    
    //Exibe um menu de quantias de saques e a opção para canelar;
    //retorna a quantia escolhida ou 0 se o usuario escolher cancelar
    private int exibirMenuDeValores()
    {
        int quantiaEscolhida = 0; //variavel local para armazenar o valor de retorno
        
        Tela tela = getTela(); //Obtem a referencia da tela
        
        //Array de quantias que correspondem aos numeros no menu
        int[] valores = {0, 20, 40, 60, 100, 200};
        
        //faz um loop enquanto nenhuma escolha valida for feita
        while(quantiaEscolhida == 0)
        {
            //Exibe o menu
            tela.exibirMensagemLinha("\nMENU DE SAQUE");
            tela.exibirMensagemLinha("1 - $20");
            tela.exibirMensagemLinha("2 - $40");
            tela.exibirMensagemLinha("3 - $60");
            tela.exibirMensagemLinha("4 - $100");
            tela.exibirMensagemLinha("5 - $200");
            tela.exibirMensagemLinha("6 - Transação cancelada");
            tela.exibirMensagem("\nEscolha um valor para saque: ");
            
            int opcaoEscolhida = teclado.getInput(); //obtem a entrada de usuario pelo teclado
            
            //Determina como prosseguir com base no valor de entrada
            switch(opcaoEscolhida)
            {
                case 1:  //Se o usuario escolheu uma quantia de saque
                case 2:  //(isto é, escolheu a opção 1, 2, 3, 4 ou 5), retorna a
                case 3:  //quantia correspondente do array de quantias
                case 4:
                case 5:   
                    quantiaEscolhida = valores[opcaoEscolhida]; //salva a escolha do usuario
                    break;
                case CANCELAR: //O usuario escolheu cancelar
                    quantiaEscolhida = CANCELAR; //Salva a escolha do usuario
                    break;
                default: //O usuario nao inseriu um valor entre 1 e 6
                    tela.exibirMensagemLinha("Selecao invalida. Tente novamente.");
            }//Fim do switch 
        }//Fim do while
        
        return quantiaEscolhida; //Retorna a quantia de saque ou CANCELADA
    }// Fim da classe ExibirMenuDeValores   
} //Fim da classe SAQUE
