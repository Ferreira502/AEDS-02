/**
 * Celula Dupla (lista dupla dinamica)
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class CelulaDupla {
	public int elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	/**
	 * Construtor da classe.
	 */
	public CelulaDupla() {
		this(0);
	}

	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public CelulaDupla(int elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}

/**
 * Lista dupla dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class ListaDupla {
	private CelulaDupla primeiro;
	private CelulaDupla ultimo;

	/**
	 * Construtor da classe que cria uma lista dupla sem elementos (somente no cabeca).
	 */
	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

	/**
	 * Insere um elemento na primeira posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(int x) {
		CelulaDupla tmp = new CelulaDupla(x);

		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		if (primeiro.prox != null) {
			primeiro.prox.ant = tmp;
		} else {
			ultimo = tmp;
		}
		primeiro.prox = tmp;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirFim(int x) {
		CelulaDupla tmp = new CelulaDupla(x);
		tmp.ant = ultimo;
		ultimo.prox = tmp;
		ultimo = tmp;
	}

	/**
	 * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public int removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

		CelulaDupla tmp = primeiro.prox;
		int resp = tmp.elemento;

		primeiro.prox = tmp.prox;
		if (tmp.prox != null) {
			tmp.prox.ant = primeiro;
		} else {
			ultimo = primeiro;
		}

		tmp.prox = tmp.ant = null;
		tmp = null;

		return resp;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public int removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

		int resp = ultimo.elemento;
		ultimo = ultimo.ant;
		ultimo.prox = null;

		return resp;
	}

	/**
    * Insere um elemento em uma posicao especifica considerando que o 
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public void inserir(int x, int pos) throws Exception {
		int tamanho = tamanho();

		if (pos < 0 || pos > tamanho) {
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0) {
			inserirInicio(x);
		} else if (pos == tamanho) {
			inserirFim(x);
		} else {
			CelulaDupla i = primeiro.prox;
			for (int j = 0; j < pos - 1; j++, i = i.prox);

			CelulaDupla tmp = new CelulaDupla(x);
			tmp.ant = i;
			tmp.prox = i.prox;
			i.prox.ant = tmp;
			i.prox = tmp;
		}
	}

	/**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param pos Meio da remocao.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public int remover(int pos) throws Exception {
		int tamanho = tamanho();

		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} else if (pos < 0 || pos >= tamanho) {
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + ") invalida!");
		} else if (pos == 0) {
			return removerInicio();
		} else if (pos == tamanho - 1) {
			return removerFim();
		} else {
			CelulaDupla i = primeiro.prox;
			for (int j = 0; j < pos; j++, i = i.prox);

			i.ant.prox = i.prox;
			i.prox.ant = i.ant;
			int resp = i.elemento;
			i.prox = i.ant = null;
			i = null;

			return resp;
		}
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("]");
	}

	/**
	 * Mostra os elementos da lista de forma invertida 
    * e separados por espacos.
	 */
	public void mostrarInverso() {
		System.out.print("[ ");
		for (CelulaDupla i = ultimo; i != primeiro; i = i.ant) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("]");
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(int x) {
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			if (i.elemento == x) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
	public int tamanho() {
		int tamanho = 0;
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox, tamanho++);
		return tamanho;
	}
}

/**
 * Classe principal para testar a Lista Dupla.
 */
class PrincipalListaDupla {
	public static void main(String[] args) {
		try {
			System.out.println("=== LISTA DINAMICA DUPLAMENTE ENCADEADA ===");
			ListaDupla lista = new ListaDupla();

			lista.inserirInicio(1);
			lista.inserirInicio(0);
			lista.inserirFim(4);
			lista.inserirFim(5);
			lista.inserir(2, 2);
			lista.inserir(3, 3);
			lista.inserir(6, 6);
			lista.inserir(-1, 0);
			lista.inserirFim(7);
			lista.inserirFim(8);

			System.out.print("Apos insercoes: ");
			lista.mostrar();

			int x1 = lista.remover(3);
			int x2 = lista.remover(2);
			int x3 = lista.removerFim();
			int x4 = lista.removerInicio();
			int x5 = lista.remover(0);
			int x6 = lista.remover(4);

			System.out.print("Apos remocoes (" + x1 + ", " + x2 + ", " + x3 + ", " + x4 + ", " + x5 + ", " + x6 + "): ");
			lista.mostrar();
		}
		catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
	}
}