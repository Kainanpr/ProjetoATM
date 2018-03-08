//Representa o teclado do ATM
package projetoatm;

import java.util.Scanner;

public class Teclado 
{
    private Scanner input; //Lê os dados na linha de comando
    
    //O construtor sem argumentos inicializa a classe Scanner
    public Teclado()
    {
        input = new Scanner(System.in);
    }//Fim do construtor Teclado sem argumentos
    
    //Retorna um valor inteiro inserido pelo usúario
    public int getInput()
    {
        return input.nextInt(); //Supomos que o usuário insira um inteiro
    }//Fim do metodo getInput
}//Fim da classe teclado
