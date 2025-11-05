class No {
	public char elemento; // Conteudo do no.
	public No esq; // No da esquerda.
	public No dir; // No da direita.
   public No2 outro;
	
   /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	No(char elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
      this.outro = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No da esquerda.
	 * @param dir No da direita.
	 */
	No(char elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
      this.outro = null;
	}
}


class No2 {
	public String elemento; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.
	
   /**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 */
	No2(String elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
	}

	/**
	 * Construtor da classe.
	 * @param elemento Conteudo do no.
	 * @param esq No2 da esquerda.
	 * @param dir No2 da direita.
	 */
	No2(String elemento, No2 esq, No2 dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}

class Pilha {
    private int[] elementos;
    private int topo;

    public Pilha(int tamanho) {
        elementos = new int[tamanho];
        topo = 0;
    }

    public void push(int valor) {
        if (topo < elementos.length) {
            elementos[topo++] = valor;
        } else {
            System.out.println("Pilha cheia!");
        }
    }

    public int pop() {
        if (topo > 0) {
            return elementos[--topo];
        } else {
            System.out.println("Pilha vazia!");
            return -1;
        }
    }

    public void mostrar() {
        System.out.print("Pilha: ");
        for (int i = topo - 1; i >= 0; i--) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println();
    }
}

class Lista {
    private int[] elementos;
    private int n;

    public Lista(int tamanho) {
        elementos = new int[tamanho];
        n = 0;
    }

    public void inserirFim(int valor) {
        if (n < elementos.length) {
            elementos[n++] = valor;
        } else {
            System.out.println("Lista cheia!");
        }
    }

    public void removerFim() {
        if (n > 0) {
            n--;
        } else {
            System.out.println("Lista vazia!");
        }
    }

    public void mostrar() {
        System.out.print("Lista: ");
        for (int i = 0; i < n; i++) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println();
    }
}


class Fila {
    private int[] elementos;
    private int primeiro, ultimo;

    public Fila(int tamanho) {
        elementos = new int[tamanho + 1];
        primeiro = ultimo = 0;
    }

    public void enfileirar(int valor) {
        if ((ultimo + 1) % elementos.length != primeiro) {
            elementos[ultimo] = valor;
            ultimo = (ultimo + 1) % elementos.length;
        } else {
            System.out.println("Fila cheia!");
        }
    }

    public int desenfileirar() {
        if (primeiro != ultimo) {
            int resp = elementos[primeiro];
            primeiro = (primeiro + 1) % elementos.length;
            return resp;
        } else {
            System.out.println("Fila vazia!");
            return -1;
        }
    }

    public void mostrar() {
        System.out.print("Fila: ");
        for (int i = primeiro; i != ultimo; i = (i + 1) % elementos.length) {
            System.out.print(elementos[i] + " ");
        }
        System.out.println();
    }
}


/**
 * Arvore de arvore
 * @author Max do Val Machado
 */
public class ArvoreArvore {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public ArvoreArvore() {
		raiz = null;
      inserir('M');
      inserir('T');
      inserir('F');
      //os outros 23 caracteres.
	}

   public void inserir(char letra){
      //igualzinho (mesmo, de verdade) ao da árvore binária padrão!!!
   }


   public void inserir(String s){
      inserir(s, raiz);
   }

   public void inserir(String s, No i) throws Exception {
		if (i == null) {
         throw new Exception("Erro ao inserir: caractere invalido!");

      } else if (s.charAt(0) < i.elemento) {
         inserir(x, i.esq);

      } else if (s.charAt(0) > i.elemento) {
         inserir(x, i.dir);

      } else {
         i.outro = inserir(s, i.outro);
      }
   }


	private No2 inserir(String s, No2 i) throws Exception {
		if (i == null) {
         i = new No2(x);

      } else if (s.compareTo(i.elemento) < 0) {
         i.esq = inserir(x, i.esq);

      } else if (s.compareTo(i.elemento) > 0) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir: elemento existente!");
      }

		return i;
	}


   public void mostrar(){
      mostrar(raiz);
   }

   public void mostrar(No i){
      if (i != null){
         mostrar(i.esq);
         //System.out.println("Letra: " + i.elemento);
         mostrar(i.outra);
         mostrar(i.dir);
      }
   }

   public void mostrar(No2 i){
      if (i != null){
         mostrar(i.esq);
         System.out.println(i.elemento);
         mostrar(i.dir);
      }
   }



   public boolean hasStringTam10(){
      return hasStringTam10(raiz);
   }

   public boolean hasStringTam10(No i){
      boolean resp = false;
      if(i != null){
         resp = hasStringTam10(i.outro) || hasStringTam10(i.esq) || hasStringTam10(i.dir);
      }
      return resp;
   }

   public boolean hasStringTam10(No2 i){
      boolean resp = false;
      if(i != null){
         resp = i.elemento.length() == 10 || hasStringTam10(i.esq) || hasStringTam10(i.dir);
      }
      return resp;
   }


   public boolean hasStringTam10(char c){
      return hasStringTam10(raiz, c);
   }

   public boolean hasStringTam10(No i, char c){
      boolean resp;
		if (i == null) { 
         resp = false;

      } else if (c < i.elemento) { 
         resp = hasStringTam10(i.esq, c); 

      } else if (c > i.elemento) { 
         resp = hasStringTam10(i.dir, c); 
      
      } else { 
         resp = hasStringTam10(i.outro); 
      }
      return resp;
   } 





	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param elemento Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String elemento) {
		return pesquisar(raiz, elemento);
	}

	private boolean pesquisar(No no, String x) {
      boolean resp;
		if (no == null) { 
         resp = false;

      } else if (x.charAt(0) < no.elemento) { 
         resp = pesquisar(no.esq, x); 

      } else if (x.charAt(0) > no.elemento) { 
         resp = pesquisar(no.dir, x); 
      
      } else { 
         resp = pesquisarSegundaArvore(no.outro, x); 
      }
      return resp;
	}

	private boolean pesquisarSegundaArvore(No2 no, String x) {
      boolean resp;
		if (no == null) { 
         resp = false;

      } else if (x.compareTo(no.elemento) < 0) { 
         resp = pesquisarSegundaArvore(no.esq, x); 

      } else if (x.compareTo(no.elemento) > 0) { 
         resp = pesquisarSegundaArvore(no.dir, x); 

      } else { 
         resp = true; 
      }
      return resp;
	}


   public int contPalavra(char letra){
      return contPalavra(letra, raiz);
   }

   public int contPalavra(char letra, No i) throws Exception {
      int resp = 0;

		if (i == null) {
         throw new Exception("Erro ao pesquisar: caractere invalido!");

      } else if (letra < i.elemento) {
         resp = contPalavra(letra, i.esq);

      } else if (letra > i.elemento) {
         resp = contPalavra(letra, i.dir);

      } else {
         resp = contPalavra(i.outro);
      }

      return resp;
   }

   public int contPalavra(No2 i){
      int resp = 0;
      if(i != null){
         resp = 1 + contPalavra(i.esq) + contPalavra(i.dir);
      }
      return resp;
   }

   // exemplo: inserir valor na pilha do nó com a letra informada
    public void inserirNaPilha(char letra, int valor) {
        No no = procurarNo(raiz, letra);
        if (no != null) {
            no.pilha.push(valor);
            System.out.println("Inserido " + valor + " na pilha do nó " + letra);
        } else {
            System.out.println("Nó " + letra + " não encontrado!");
        }
    }

    // exemplo: remover valor da pilha
    public void removerDaPilha(char letra) {
        No no = procurarNo(raiz, letra);
        if (no != null) {
            int removido = no.pilha.pop();
            System.out.println("Removido " + removido + " da pilha do nó " + letra);
        } else {
            System.out.println("Nó " + letra + " não encontrado!");
        }
    }

    // função auxiliar para encontrar o nó pela letra
    private No procurarNo(No i, char letra) {
        if (i == null) return null;
        if (letra == i.elemento) return i;
        else if (letra < i.elemento) return procurarNo(i.esq, letra);
        else return procurarNo(i.dir, letra);
    }

    // mostrar as pilhas de todos os nós
    public void mostrarPilhas() {
        mostrarPilhas(raiz);
    }

    private void mostrarPilhas(No i) {
        if (i != null) {
            mostrarPilhas(i.esq);
            System.out.println("Nó " + i.elemento + ":");
            i.pilha.mostrar();
            mostrarPilhas(i.dir);
        }
    }

    public void inserirNaLista(char letra, int valor) {
        No no = procurarNo(raiz, letra);
        if (no != null) {
            no.lista.inserirFim(valor);
            System.out.println("Inserido " + valor + " na lista do nó " + letra);
        }
    }

    public void inserirNaFila(char letra, int valor) 
    {
        No no = procurarNo(raiz, letra);
        if (no != null) {
            no.fila.enfileirar(valor);
            System.out.println("Inserido " + valor + " na fila do nó " + letra);
        }
    }

    public void removerDaFila(char letra) {
        No no = procurarNo(raiz, letra);
        if (no != null) {
            int removido = no.fila.desenfileirar();
            System.out.println("Removido " + removido + " da fila do nó " + letra);
        }
    }

}