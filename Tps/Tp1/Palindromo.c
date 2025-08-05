#include <stdio.h>
#include <string.h>

#define MAX 100

int main ()
{
    char palavra [MAX];
    int palindromo = 1;

    scanf("%s", &palavra);
    int tamanho = strlen(palavra);

    for (int i = 0; i < tamanho / 2; i++) 
    {
        if (palavra[i] != palavra[tamanho - 1 - i]) 
        {
            palindromo = 0;
        }
    }
    
    if (palindromo)
    {
        printf("SIM\n");
    }
    else
    {
        printf("NAO\n");
    }
    
}