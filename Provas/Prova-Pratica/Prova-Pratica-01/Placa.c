// Antiga placa
//___-____
//ABC-2025

// Nova Placa
//_______
//ABC1A22

#include <stdio.h>
#include <string.h>
#define MAX 100

int main() 
{
    char palavra[MAX];
    fgets(palavra, MAX, stdin);

    palavra[strcspn(palavra, "\n")] = '\0';

    int tamanho = strlen(palavra);

    if (tamanho == 8) 
    {
        if (palavra[0] >= 'A' && palavra[0] <= 'Z' &&
            palavra[1] >= 'A' && palavra[1] <= 'Z' &&
            palavra[2] >= 'A' && palavra[2] <= 'Z' &&
            palavra[3] == '-' &&
            palavra[4] >= '0' && palavra[4] <= '9' &&
            palavra[5] >= '0' && palavra[5] <= '9' &&
            palavra[6] >= '0' && palavra[6] <= '9' &&
            palavra[7] >= '0' && palavra[7] <= '9') 
            {
                printf("1\n");
                return 0;
        }
    }

    if (tamanho == 7) 
    {
        if (palavra[0] >= 'A' && palavra[0] <= 'Z' &&
            palavra[1] >= 'A' && palavra[1] <= 'Z' &&
            palavra[2] >= 'A' && palavra[2] <= 'Z' &&
            palavra[3] >= '0' && palavra[3] <= '9' &&
            palavra[4] >= 'A' && palavra[4] <= 'Z' &&
            palavra[5] >= '0' && palavra[5] <= '9' &&
            palavra[6] >= '0' && palavra[6] <= '9') 
            {
            
                printf("2\n");
                return 0;
        }
    }
    
    printf("0\n");
    return 0;
}
