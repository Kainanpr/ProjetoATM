//Representa a TELA do ATM
package projetoatm;

public class Tela //Essa classe tem construtor PADRAO, pois nao forneci nenhum
{
    //Exibe uma mensagem SEEEEM retorno de carro(SEM PULAR LINHA)
    public void exibirMensagem(String mensagem)
    {
        System.out.print(mensagem);
    }//fim do metodo exibirMensagem;
    
    //Exibe um mensagem COOOOM um retorno de carro(PULA LINHA)
    public void exibirMensagemLinha(String mensagem)
    {
        System.out.println(mensagem);
    }//Fim do metodo exibirMensagemLinha
    
    //Exibir um valor em dólares
    public void exibirValorDolar(double valor)
    {
        System.out.printf("$%,.2f", valor);
    }//Fim do metodo exibirValorDolar  
}//Fim da classe Tela
