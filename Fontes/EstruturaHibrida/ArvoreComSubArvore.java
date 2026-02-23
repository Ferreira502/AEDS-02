// ------------------- NÓ DA ÁRVORE PRINCIPAL -------------------
class No {
    public int elemento;
    public No esq, dir;
    public ArvoreBinaria subArvore; // cada nó tem uma árvore própria

    public No(int elemento) {
        this(elemento, null, null);
    }

    public No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.subArvore = new ArvoreBinaria(); // cria uma subárvore vazia
    }
}

// ------------------- ÁRVORE BINÁRIA -------------------
class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    // Insere um novo nó
    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) throws Exception {
        if (i == null) return new No(x);
        if (x < i.elemento) i.esq = inserir(x, i.esq);
        else if (x > i.elemento) i.dir = inserir(x, i.dir);
        else throw new Exception("Erro: elemento já existe!");
        return i;
    }

    // Pesquisa um elemento
    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(int x, No i) {
        if (i == null) return false;
        if (x == i.elemento) return true;
        if (x < i.elemento) return pesquisar(x, i.esq);
        return pesquisar(x, i.dir);
    }

    // Busca e retorna o nó pelo valor
    private No buscarNo(No i, int x) {
        if (i == null) return null;
        if (x == i.elemento) return i;
        if (x < i.elemento) return buscarNo(i.esq, x);
        return buscarNo(i.dir, x);
    }

    // Insere um valor na subárvore de um nó específico
    public void inserirNaSubArvore(int chaveNo, int valorSubArvore) throws Exception {
        No no = buscarNo(raiz, chaveNo);
        if (no != null) {
            no.subArvore.inserir(valorSubArvore);
        } else {
            System.out.println("Nó " + chaveNo + " não encontrado!");
        }
    }

    // Percorre a árvore principal e mostra cada subárvore
    public void caminharCentral() {
        System.out.println("Árvore principal com subárvores:");
        caminharCentral(raiz);
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print("Nó " + i.elemento + " -> Subárvore: ");
            i.subArvore.mostrarCentral(); // mostra a subárvore desse nó
            caminharCentral(i.dir);
        }
    }

    // Percorre e mostra os elementos dessa árvore
    public void mostrarCentral() {
        System.out.print("[ ");
        mostrarCentral(raiz);
        System.out.println("]");
    }

    private void mostrarCentral(No i) {
        if (i != null) {
            mostrarCentral(i.esq);
            System.out.print(i.elemento + " ");
            mostrarCentral(i.dir);
        }
    }
}

// ------------------- MAIN -------------------
public class ArvoreComSubArvore {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria arvore = new ArvoreBinaria();

        // Cria a árvore principal
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(30);

        // Adiciona valores nas subárvores de alguns nós
        arvore.inserirNaSubArvore(10, 5);
        arvore.inserirNaSubArvore(10, 8);
        arvore.inserirNaSubArvore(10, 12);

        arvore.inserirNaSubArvore(20, 3);
        arvore.inserirNaSubArvore(20, 9);

        arvore.inserirNaSubArvore(30, 100);
        arvore.inserirNaSubArvore(30, 50);

        // Exibe toda a estrutura
        arvore.caminharCentral();
    }
}