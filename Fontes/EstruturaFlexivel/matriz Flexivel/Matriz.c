#include <stdio.h>
#include <stdlib.h>

typedef struct Celula {
    int elemento;
    struct Celula *sup, *inf, *esq, *dir;
} Celula;

// Função para criar uma nova célula
Celula* criarCelula(int valor) {
    Celula* nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = valor;
    nova->sup = nova->inf = nova->esq = nova->dir = NULL;
    return nova;
}

// Criação da matriz flexível
Celula* matrizFlex(int linhas, int colunas) {
    Celula* inicio = criarCelula(0);

    // Criar primeira linha
    Celula* tmp = inicio;
    for (int j = 1; j < colunas; j++) {
        tmp->dir = criarCelula(0);
        tmp->dir->esq = tmp;
        tmp = tmp->dir;
    }

    // Criar demais linhas
    Celula* linhaCima = inicio;
    for (int i = 1; i < linhas; i++) {
        Celula* novaLinha = criarCelula(0);
        linhaCima->inf = novaLinha;
        novaLinha->sup = linhaCima;

        Celula* tmpCima = linhaCima->dir;
        Celula* atual = novaLinha;

        for (int j = 1; j < colunas; j++) {
            atual->dir = criarCelula(0);
            atual->dir->esq = atual;
            atual = atual->dir;

            atual->sup = tmpCima;
            tmpCima->inf = atual;

            tmpCima = tmpCima->dir;
        }
        linhaCima = linhaCima->inf;
    }

    return inicio;
}

// Preencher matriz
void preencherMatriz(Celula* inicio, int linhas, int colunas) {
    Celula* tmpLinha = inicio;
    Celula* tmpColuna;

    for (int i = 0; i < linhas; i++) {
        tmpColuna = tmpLinha;
        for (int j = 0; j < colunas; j++) {
            printf("Digite o elemento [%d][%d]: ", i, j);
            scanf("%d", &tmpColuna->elemento);
            tmpColuna = tmpColuna->dir;
        }
        tmpLinha = tmpLinha->inf;
    }
}

// Mostrar matriz
void mostrarMatriz(Celula* inicio, int linhas, int colunas) {
    Celula* tmpLinha = inicio;
    Celula* tmpColuna;

    for (int i = 0; i < linhas; i++) {
        tmpColuna = tmpLinha;
        for (int j = 0; j < colunas; j++) {
            printf("%d ", tmpColuna->elemento);
            tmpColuna = tmpColuna->dir;
        }
        printf("\n");
        tmpLinha = tmpLinha->inf;
    }
}

// Diagonal principal
void diagonalPrincipal(Celula* inicio, int linhas, int colunas) {
    Celula* tmp = inicio;
    printf("Diagonal Principal: ");
    while (tmp != NULL) {
        printf("%d ", tmp->elemento);
        if (tmp->dir != NULL && tmp->inf != NULL)
            tmp = tmp->dir->inf;
        else
            break;
    }
    printf("\n");
}

// Diagonal secundaria
void diagonalSecundaria(Celula* inicio, int linhas, int colunas) {
    // ir para o topo da ultima coluna
    Celula* tmpLinha = inicio;
    for (int i = 0; i < linhas; i++)
        tmpLinha = tmpLinha->inf;

    tmpLinha = inicio;
    for (int j = 1; j < colunas; j++)
        tmpLinha = tmpLinha->dir;

    Celula* tmp = tmpLinha;
    printf("Diagonal Secundaria: ");
    while (tmp != NULL) {
        printf("%d ", tmp->elemento);
        if (tmp->esq != NULL && tmp->inf != NULL)
            tmp = tmp->esq->inf;
        else
            break;
    }
    printf("\n");
}

int main() {
    int linhas = 3, colunas = 3;

    Celula* matriz = matrizFlex(linhas, colunas);

    preencherMatriz(matriz, linhas, colunas);
    printf("\nMatriz:\n");
    mostrarMatriz(matriz, linhas, colunas);

    diagonalPrincipal(matriz, linhas, colunas);
    diagonalSecundaria(matriz, linhas, colunas);

    return 0;
}