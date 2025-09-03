#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAX 100

char paraMinusculo(char c) 
{
    if (c >= 'A' && c <= 'Z') return c + ('a' - 'A');
    return c;
}

bool ehDigito(char c) 
{
    return (c >= '0' && c <= '9');
}

bool ehVogal(char c) 
{
    c = paraMinusculo(c);
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
}

bool ehSoVogal(char *palavra) 
{
    int tamanho = strlen(palavra);
    if (tamanho == 0) return false;
    
    for (int i = 0; i < tamanho; i++) 
    {
        if (ehVogal(palavra[i])) 
        {
            return true;
        }
    }
    return false;
}

bool ehLetra(char c) 
{
    return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
}

bool ehSoCons(char *palavra) 
{
    int tamanho = strlen(palavra);
    if (tamanho == 0) return false;
    
    for (int i = 0; i < tamanho; i++) 
    {
        if (ehLetra(palavra[i]) || ehVogal(palavra[i])) 
        {
            return false;
        }
    }
    return true;
}

bool ehInteiro(char *palavra) 
{
    int tamanho = strlen(palavra);
    if (tamanho == 0) return false;
    
    for (int i = 0; i < tamanho; i++) 
    {
        if (!ehDigito(palavra[i]))
        {
            return false;
        }
    }
    return true;
}


bool ehReal(char *palavra) 
{
    int tamanho = strlen(palavra);
    if (tamanho == 0) 
    {
        return false;
    }
        
    int i = 0;
    bool pontoEncontrado = false;

    if (palavra[0] == '+' || palavra[0] == '-') 
    {
        if (tamanho == 1) 
        {
            return false;
        }
         i = 1;
    }

    for (i = 0; i < tamanho; i++) 
    {
        char c = palavra[i];
        if (ehDigito(c) && (c == '.' || c == ',') && !pontoEncontrado) 
        {
            pontoEncontrado = true;
        } 
        else 
        {
            return false;
        }
    }

    if (palavra[tamanho - 1] == '.' || palavra[tamanho - 1] == ',') 
    {
        return false;
    }

    return pontoEncontrado;
}


int comparaString(char *palavra) 
{
    return strcmp(palavra, "FIM");
}

int main () 
{
    char p1 [MAX];
    char p2 [MAX];
    int resultado = 1;

    while (resultado != 0) 
    {
        scanf("%s %s", p1, p2);

        if (comparaString(p1) == 0)
        {
            break;
        }

        if (ehSoVogal(p1) == true)
        {
            printf("SIM ");
        }
        else 
        {
            printf("NAO ");
        }

        if (ehSoCons(p1) == true)
        {
            printf("SIM ");
        }
        else 
        {
            printf("NAO ");
        }

        if (ehInteiro(p1) == true)
        {
            printf("SIM ");
        }
        else 
        {
            printf("NAO ");
        }

        if (ehReal(p1) == true)
        {
            printf("SIM");
        }
        else 
        {
            printf("NAO");
        }

        printf("\n");
        resultado = comparaString(p1);
    }   

    return 0;
}