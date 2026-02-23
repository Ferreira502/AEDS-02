#include <stdio.h>  
#include <string.h>  

#define MAX 1000   

// Função que compara uma string com "FIM"
// Retorna 0 se forem iguais, valor diferente de 0 caso contrário
int comparaString(char *palavra) 
{
    return strcmp(palavra, "FIM");
}

// Função recursiva que verifica se uma string é palíndromo
// Recebe a palavra, o índice inicial e o final
int ehPalindromo(char palavra[], int inicio, int fim) 
{
    if (inicio >= fim) 
    {
        return 1; 
    }

    if (palavra[inicio] != palavra[fim]) 
    {
        return 0; // falso
    }

    return ehPalindromo(palavra, inicio + 1, fim - 1);
}

int main() 
{
    char palavra[MAX];  
    int resultado = 1;  

    while (resultado != 0) 
    {
        fgets(palavra, MAX, stdin);
        palavra[strcspn(palavra, "\n")] = '\0';
        
        // Verifica se a palavra é "FIM"
        resultado = comparaString(palavra);

        if (resultado == 0) // Se for "FIM", encerra o loop
        {
            break;
        }

        int tamanho = strlen(palavra); 

        // Verifica se a palavra é palíndromo
        if (ehPalindromo(palavra, 0, tamanho - 1)) 
        {
            printf("SIM\n");
        } 
        else 
        {
            printf("NAO\n");
        }
    }

    return 0;
}
