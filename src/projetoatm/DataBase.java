//Representa o banco de dados com as informações sobre as contas bancarias
package projetoatm;

public class DataBase 
{
    private Conta[] contas; //Array de Contas
    
    //Construtor DataBase sem argumentos inicializa as Contas
    public DataBase()
    {
        contas = new Conta[2]; //Apenas 2 contas para teste
        contas[0] = new Conta(12345, 54321, 1000.0, 1200.0);
        contas[1] = new Conta(98765, 56789, 200.0, 200.0);
    }//Fim do construtor DataBase sem argumentos
 
    //Recupera o objeto Conta que contem o numero de conta especificado
    private Conta getConta(int numeroConta)
    {
        //Faz um loop pelas contas procurando uma correspondencia com o numero de conta
        for(Conta auxConta: contas)
        {
            //Retorna a conta atual se uma correspondencia for localizada
            if(auxConta.getNumeroConta() == numeroConta)
                return auxConta;
        }// Fim do FOR
        
        return null; //Se nenhuma correspondencia com uma conta foi localizada, retorna null
    }//Fim do metodo getConta
    
    /* Determina se o numero de conta e PIN especificadors pelo usiario correspondem aqueles
       de uma conta no Banco de Dados */
    public boolean autenticaUser(int userNumeroConta, int userPIN)
    {
        //Tenta recuperar a conta com o numero da conta
        Conta userConta = getConta(userNumeroConta);
        
        //se a conta existir, retorna o resultado do metodo validaPIN de Conta
        if(userConta != null)
            return userConta.validePIN(userPIN);
        else
            return false; //Numero de conta nao foi localizado, portanto returna false
    }//Fim do metodo autenticaUser
    
    //retorna o saldo disponivel de Conta com o numero da conta especificado
    public double getSaldoDisponivel(int userNumeroConta)
    {
        return getConta(userNumeroConta).getSaldoDisponivel();
    }//Fim do metodo getSaldoDisponivel
    
    //retorna o saldo total de Conta com o numero da conta especificado
    public double getSaldoTotal(int userNumeroConta)
    {
        return getConta(userNumeroConta).getSaldoTotal();
    }//Fim do metodo getSaldoTotal
    
    //credita uma quantia a Conta com o numero da conta especificado
    public void credito(int userNumeroConta, double valor)
    {
        getConta(userNumeroConta).credito(valor);      
    }//Fim do metodo credito
    
    //Debita uma quantidade de Conta com o numero da conta especificado
    public void debito(int userNumeroConta, double valor)
    {
        getConta(userNumeroConta).debito(valor);
    }//fim do metodo debito  
}//Fim da classe DataBase
