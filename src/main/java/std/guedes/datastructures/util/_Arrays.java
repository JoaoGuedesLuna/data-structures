package main.java.std.guedes.datastructures.util;

/**
 * Essa classe tem o objetivo de disponibilizar métodos auxiliares para operações com Arrays.
 *
 * @author João Guedes.
 */
public class _Arrays {

    /**
     * Método de busca sequencial. O array é percorrido sequencialmente e cada elemento é verificado,
     * caracterizando uma pesquisa linear.
     *
     * @param array Array onde será feita a busca.
     *
     * @param element Elemento procurado.
     *
     * @return Retorna o índice do elemento no array. Caso o elemento não se encontre no array o
     * valor retornado será negativo.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T> int sequentialSearch(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método de busca binária. Consiste em comparar o valor pesquisado com o valor do elemento no
     * meio do array e, caso sejam iguais, a posição do meio é retornada. Caso o valor pesquisado
     * seja antecedente ao do meio, o algoritmo descarta todos os valores posteriores. E caso o
     * valor pesquisado seja posterior ao valor do meio, o algoritmo descarta todos os valores
     * anteriores, até que sobre apenas o elemento desejado. Se o elemento restante não for o que
     * queremos, será retornado um valor negativo. Para que a busca seja efetiva o array deve estar
     * ordenado.
     *
     * @param array Array onde será feita a busca.
     *
     * @param element Elemento procurado.
     *
     * @return Retorna o índice do elemento no array. Caso o elemento não se encontre no array o
     * valor retornado será negativo.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T element) {
        int beginning = 0;
        int middle;
        int end = array.length-1;
        int compare;
        while (beginning <= end) {
            middle = (end+beginning)/2;
            compare = array[middle].compareTo(element);
            if(compare == 0) {
                return middle;
            }
            else if (compare < 0) {
                beginning = middle+1;
            }
            else {
                end = middle-1;
            }
        }
        return -1;
    }

    /**
     * Faz a ordenação de um array.
     * 
     * @param array Array que será ordenado.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        _Arrays.quickSort(array);
    }

    /**
     * O selection sort é um algoritmo de ordenação baseado em se passar sempre o menor valor do vetor
     * para a primeira posição (ou o maior dependendo da ordem requerida), depois o de segundo menor
     * valor para a segunda posição, e assim é feito sucessivamente com os n-1 elementos restantes,
     * até os últimos dois elementos.
     *
     * @param array Array que será ordenado.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        for (int i = 0; i < array.length-1; i++){
            for (int j = i+1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    _Arrays.swap(array, i, j);
                }
            }
        }
    }

    /**
     * O bubble sort é um algoritmo de ordenação dos mais simples. A ideia é percorrer o vetor diversas
     * vezes, e a cada passagem fazer flutuar para o topo o maior elemento da sequência. Essa
     * movimentação lembra como as bolhas em um tanque de água procuram seu próprio nível, e disso
     * vem o nome do algoritmo.
     *
     * @param array Array que será ordenado.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        boolean swappedSomething;
        do {
            swappedSomething = false;
            for (int i = 0; i < array.length-1; i++) {
                if (array[i].compareTo(array[i+1]) > 0) {
                    _Arrays.swap(array, i, i+1);
                    swappedSomething = true;
                }
            }
        } while(swappedSomething);
    }

    /**
     * O insertion sort percorre as posições do array, começando com o índice 1. Cada nova posição é
     * como a nova carta que você recebeu, e você precisa inseri-la no lugar correto no baralho
     * ordenado à esquerda daquela posição.
     *
     * @param array Array que será ordenado.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        int j;
        for (int i = 1; i < array.length; i++){
            T currentValue = array[i];
            j = i-1;
            while ((j >= 0) && (currentValue.compareTo(array[j]) < 0)) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = currentValue;
        }
    }

    /**
     * O merge sort é um algoritmo recursivo que divide uma lista continuamente pela metade. Se a lista
     * estiver vazia ou tiver um único ‘item’, ela está ordenada, por definição (o caso base). Se a lista
     * tiver mais de um ‘item’, dividimos a lista e invocamos recursivamente um merge sort em ambas as
     * metades. Assim que as metades estiverem ordenadas, a operação fundamental, chamada intercalação,
     * é realizada. Intercalar é o processo de pegar duas listas menores ordenadas e combiná-las de modo
     * a formar uma lista nova, única e ordenada.
     *
     * @param array Array que será ordenado.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array.length < 2) {
            return;
        }
        int middleIndex = array.length/2;
        T[] leftHalf = (T[]) new Comparable [middleIndex];
        T[] rightHalf = (T[]) new Comparable [array.length-middleIndex];
        for (int i = 0; i < leftHalf.length; i++) {
            leftHalf[i] = array[i];
        }
        for (int i = 0; i < rightHalf.length; i++) {
            rightHalf[i] = array[middleIndex+i];
        }
        _Arrays.mergeSort(leftHalf);
        _Arrays.mergeSort(rightHalf);
        _Arrays.merge(array, leftHalf, rightHalf);
    }

    /**
     * Esse método trabalha como auxílio ao método mergeSort(). Adiciona os elementos do array da esquerda
     * e da direita no array principal, de maneira ordenada.
     *
     * @param mainArray Array principal.
     *
     * @param leftHalf Array com os elementos da esquerda do array principal.
     *
     * @param rightHalf Array com os elementos da direita do array principal.
     *
     * @param <T> Tipo de dado contido no array.
     */
    private static <T extends Comparable<T>> void merge(T[] mainArray, T[] leftHalf, T[] rightHalf) {
        int topLeft = 0, topRight = 0;
        for (int i = 0; i < mainArray.length; i++) {
            if (topLeft >= leftHalf.length) {
                mainArray[i] = rightHalf[topRight++];
            }
            else if (topRight >= rightHalf.length) {
                mainArray[i] = leftHalf[topLeft++];
            }
            else if (leftHalf[topLeft].compareTo(rightHalf[topRight]) <= 0) {
                mainArray[i] = leftHalf[topLeft++];
            }
            else {
                mainArray[i] = rightHalf[topRight++];
            }
        }
    }

    /**
     * O algoritmo Quicksort utiliza o paradigma de programação Dividir para Conquistar. Dado a sequência
     * de entrada, deve-se primeiramente escolher um elemento que chamaremos pivô. Em seguida iterar
     * sobre toda a sequência de modo a posicionar todos os elementos menores do que esse pivô à sua
     * esquerda. A escolha do pivô pode ser feita aleatoriamente, ser o primeiro elemento ou o último.
     * Assim, temos que à esquerda da posição atual do nosso pivô, todos os elementos são menores que
     * ele. Consequentemente, por termos iterado toda a sequência, todos os elementos à direita são
     * maiores que o pivô. É importante ressaltar que, tanto à esquerda quanto à direita, os elementos
     * não estão completamente ordenados. Mas podemos afirmar com certeza que em relação ao pivô estão.
     * Nesse momento podemos então chamar recursivamente a subsequência à esquerda e à direita até elas
     * estejam completamente ordenadas.
     *
     * @param array Array que será ordenado.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T extends Comparable<T>> void quickSort(T[] array) {
        _Arrays.quickSort(array, 0, array.length-1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }
        T pivot = array[highIndex];
        int pivotIndex = _Arrays.partition(array, lowIndex, highIndex, pivot);
        _Arrays.quickSort(array, lowIndex, pivotIndex-1);
        _Arrays.quickSort(array, pivotIndex+1, highIndex);
    }

    /**
     * Esse método tem o objetivo de mover os elementos menores que o pivô para a esquerda do pivô e os
     * elementos maiores para a direita do pivô.
     *
     * @param array Array que está tentando ser ordenar.
     *
     * @param lowIndex Menor índice que se deseja trabalhar na estrutura.
     *
     * @param highIndex Maior índice que se deseja trabalhar na estrutura.
     *
     * @param pivot Elemento escolhido como pivô.
     *
     * @return Retorna o índice onde o pivô, após ocupar sua posição adequada, se encontra.
     *
     * @param <T> Tipo de dado contido no array.
     */
    private static <T extends Comparable<T>> int partition(T[] array, int lowIndex, int highIndex, T pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex;
        while (leftPointer < rightPointer) {
            while (array[leftPointer].compareTo(pivot) <= 0 && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (array[rightPointer].compareTo(pivot) >= 0 && leftPointer < rightPointer) {
                rightPointer--;
            }
            _Arrays.swap(array, leftPointer, rightPointer);
        }
        _Arrays.swap(array, highIndex, leftPointer);
        return leftPointer;
    }

    /**
     * Faz a troca de elementos de um array. O elemento do primeiro índice irá para a posição do
     * segundo índice e o elemento do segundo índice irá para a posição do primeiro índice.
     *
     * @param array Array que terá os elementos trocados.
     *
     * @param index1 Primeiro índice.
     *
     * @param index2 Segundo índice.
     *
     * @param <T> Tipo de dado contido no array.
     */
    private static <T> void swap(T[] array, int index1, int index2) {
        T aux = array[index1];
        array[index1] = array[index2];
        array[index2] = aux;
    }

    /**
     * Método que verifica se um array está vazio.
     *
     * @param array Array que será verificado.
     *
     * @return Retorna true caso o array esteja vazio ou false caso ele não esteja vazio.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T> boolean isEmpty(T[] array) {
        return array.length == 0;
    }

    /**
     * Retorna o array como uma String.
     *
     * @param array Array cujo dado será retornado como uma String.
     *
     * @return Retorna o array como uma String.
     *
     * @param <T> Tipo de dado contido no array.
     */
    public static <T> String toString(T[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length-1; i++) {
            sb.append(array[i]).append(", ");
        }
        if (!_Arrays.isEmpty(array)) {
            sb.append(array[array.length-1]);
        }
        sb.append("]");
        return sb.toString();
    }

}