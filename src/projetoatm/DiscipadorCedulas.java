//Representa o dispensador de cedulas do ATM
package projetoatm;


public class DiscipadorCedulas
{
    //O numero inicial padrao de cedulas no dispensador de cédulas
    private final static int COUNT_INICIAL = 500;
    private int count; //numero de cedulas de US$ 20 remanescente
    
    //Construtor sem argumentos DiscipadorCedulas inicializa a count para o padrao
    public DiscipadorCedulas()
    {
        count = COUNT_INICIAL; //Configura atributo count como o padrao
    }//Fim do construtor DispensadorCedulas
    
    //Simila a entrega da quantia especificado de cedular
    public void dispensarDinheiro(int valor)
    {
        int valorRequerido = valor / 20; //numero de cedulas de US$ 20 requerido
        count -= valorRequerido; // atualiza a contagem das cedulas
    }// Fim do metodo dispensarDinheiro
    
    //Indica se o dispensador de cedulas pode entrar a quantia desejada
    public boolean isSuficienteDinheiroDisponivel(int valor)
    {
        int valorRequerido = valor / 20; //Numero de cedulas de US$ 20 requerido
        
        if(count >= valorRequerido)
            return true; //Há cedulas suficientes disponiveis
        else
            return false; // NÃO há cedulas suficientes disponiveis
    }//Fim do metodo isSuficienteDinheiroDisponivel      
}//Fim da classe DispensadorCedulas