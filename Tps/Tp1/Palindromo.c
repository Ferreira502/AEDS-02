#include <stdio.h>
#include <string.h>

#define MAX 1000

int comparaString(char *palavra) 
{
    return strcmp(palavra, "FIM");
}

int ehPalindromo(char palavra[], int inicio, int fim) 
{
    if (inicio >= fim) 
    {
        return 1;
    }
    if (palavra[inicio] != palavra[fim]) 
    {
        return 0;
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
        palavra [strcspn(palavra, "\n")] = '\0';
        
        resultado = comparaString(palavra);

        if (comparaString(palavra) == 0) 
        {
            break;
        }

        int tamanho = strlen(palavra);

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
