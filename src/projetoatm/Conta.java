//Representa uma conta bancaria
package projetoatm;

public class Conta 
{
    private int numeroConta; //numero da conta
    private int pin; //PIN para autenticação
    private double saldoDisponivel; //Fundos disponiveis para saque
    private double saldoTotal; //Fundos disponiveis + depositos pendentes
    
    //Construtor Conta inicializa os atributos
    public Conta (int numeroConta, int pin, double saldoDisponivel, double saldoTotal)
    {
        this.numeroConta = numeroConta;
        this.pin = pin;
        this.saldoDisponivel = saldoDisponivel;
        this.saldoTotal = saldoTotal;
    }//Fim do construtor
    
    //determina se um PIn especificado pelo usuario corresponde ao PIN em Conta
    public boolean validePIN(int userPIN)
    {
        if(userPIN == pin)
            return true;
        else
            return false;
    }//Fim do metodo validePIN
    
    //Retorna o saldo disponivel
    public double getSaldoDisponivel()
    {
        return saldoDisponivel;
    }// Fim do metodo getSaaldoDisponivel
    
    //Retorna o saldo total
    public double getSaldoTotal()
    {
        return saldoTotal;
    }//Fim do metodo getSaldoTotal
    
    //Credita uma quantia à conta
    public void credito(double valor)
    {
        saldoTotal += valor; //Adiciona ao saldo total
    }//Fim do metodo credito
    
    //Debita uma quantia da conta
    public void debito(double valor)
    {
        saldoDisponivel -= valor; //subtrai do saldo disponivel
        saldoTotal -= valor; //Subtrai do saldo total
    }//Fim do metodo debito
    
    //retorna o numero da conta
    public int getNumeroConta()
    {
        return numeroConta;
    }//Fim do metodo getNumeroConta 
}//Fim da classe Conta















