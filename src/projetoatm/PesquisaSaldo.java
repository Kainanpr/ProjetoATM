//Representa uma transação de consulta de saldos no ATM
package projetoatm;

public class PesquisaSaldo extends Transacao
{
    //Construtor de PEsquisaSaldo
    public PesquisaSaldo(int numeroConta, Tela atmTela, DataBase atmDataBase)
    {
        super(numeroConta, atmTela, atmDataBase);
    }//Fim do construtor de PesquisaSaldo
    
    //Realiza a transação
    @Override
    public void execute()
    {
        DataBase dataBase = getDataBase();
        Tela tela = getTela();
        
        //Obtem o saldo disponivel da conta envolvida
        double saldoDisponivel = dataBase.getSaldoDisponivel(getNumeroConta());
        
        //Obtem o saldo total da conta envolvida
        double saldoTotal = dataBase.getSaldoTotal(getNumeroConta());
        
        //Exibe as informações sobre o saldo na tela
        tela.exibirMensagemLinha("\nInformações de saldo: ");
        tela.exibirMensagem("- Saldo Disponível: ");
        tela.exibirValorDolar(saldoDisponivel);
        tela.exibirMensagem("\n- Saldo Total: ");
        tela.exibirValorDolar(saldoTotal);
        tela.exibirMensagemLinha("");
    }//Fim do metodo execute   
}//Fim da classe PesquisaSaldo
