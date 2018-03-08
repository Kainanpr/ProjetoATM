//A superclasse abstrata Transacao representa uma transação no ATM
package projetoatm;

public abstract class Transacao {
    //Atrituros
    private int numeroConta; //Conta a sacar fundos
    private Tela tela; //Referencia a Tela do ATM
    private DataBase dataBase; //Referencia ao Banco de dados de informações sobre a conta
    
    //Metodo construtor sem argumentos invocado pelas subclasses utilizando super()
    public Transacao(int numeroConta, Tela atmTela, DataBase atmDataBase)         
    {
        this.numeroConta = numeroConta;
        this.tela = atmTela;
        this.dataBase = atmDataBase;
    } //Fim construtor de Transacao
    
    //Retorna o numero de conta
    public int getNumeroConta()
    {
        return numeroConta;
    }//Fim do metodo getNumeroConta
    
    //Retorna a referencia a tela
    public Tela getTela()
    {
        return tela;
    }//Fim do metodo getTela
    
    //Retorna a referencia ao Banco de dados da instituição financeira
    public DataBase getDataBase()
    {
        return dataBase;
    }//Fim do metodo getDatabase;
    
    //Metodos abstratos. Realiza a transação (sobrescrita po cada subclasse)
    public abstract void execute();
    
}// Fim classe Transacao
