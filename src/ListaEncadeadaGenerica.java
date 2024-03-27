
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ListaEncadeadaGenerica<T extends Comparable<T>> {
    private Elemento<T> head;

    public ListaEncadeadaGenerica() {
        this.head = null;
    }
    //ITERADOR TESTE ###################################################################################################
    public Iterator<T> iterator() {
        return new Walker<>(this);
    }

    public class Walker<T extends Comparable<T>> implements Iterator<T> {
        private ListaEncadeadaGenerica<T> lista;
        private ListaEncadeadaGenerica.Elemento<T> current;

        public Walker(ListaEncadeadaGenerica<T> lista){
            this.lista = lista;
            this.current = lista.head;
        }
        @Override
        public boolean hasNext(){
            return current != null;
        }
        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException("Sem mais elementos ná lista.");
            }
            T elemento = current.data;
            current = current.next;
            return elemento;
        }
    }

    //METODO sum #######################################################################################################
    public double sum() {
        double sum = 0;
        Elemento<T> current = head;
        while (current != null) {
            sum += Double.parseDouble(current.data.toString());
            current = current.next;
        }
        return sum;
    }

    public T findMostRepeatedCrypto() {
        if (head == null) {
            throw new IllegalArgumentException("Lista vazia");
        }

        int[] frequencies = new int[256];
        int[] firstIndex = new int[256];
        Elemento<T> current = head;
        int index = 0;

        while (current != null) {
            String value = current.data.toString();
            for (int i = 0; i < value.length(); i++) {
                char character = value.charAt(i);
                frequencies[character]++;
                if (frequencies[character] == 1) {
                    firstIndex[character] = index;
                }
            }
            current = current.next;
            index++;
        }

        T mostRepeatedCrypto = null;
        int maxFrequency = 0;
        int firstOccurrence = Integer.MAX_VALUE;
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > maxFrequency || (frequencies[i] == maxFrequency && firstIndex[i] < firstOccurrence)) {
                maxFrequency = frequencies[i];
                mostRepeatedCrypto = (T) Character.toString((char)i);
                firstOccurrence = firstIndex[i];
            }
        }

        if (maxFrequency > 1) {
            return mostRepeatedCrypto;
        } else {
            T maxValue = (T) Integer.valueOf(0);
            current = head;
            while (current != null) {
                String value = current.data.toString();
                for (int i = 0; i < value.length(); i++) {
                    char character = value.charAt(i);
                    if (character > (Integer) maxValue) {
                        maxValue = (T) Character.toString(character);
                    }
                }
                current = current.next;
            }
            return maxValue;
        }
    }


    //METODO average ###################################################################################################
    public double average() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vazia");
        }
        return sum() / size();
    }

    //METODO minValue ##################################################################################################
    public T minValue() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vazia");
        }
        return minValueRecursive(head, head.data);
    }

    private T minValueRecursive(Elemento<T> current, T min) {
        if (current == null) {
            return min;
        }
        if (current.data.compareTo(min) < 0) {
            min = current.data;
        }
        return minValueRecursive(current.next, min);
    }

    //METODO maxValue ##################################################################################################
    public T maxValue() {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vazia");
        }
        return maxValueRecursive(head, head.data);
    }

    private T maxValueRecursive(Elemento<T> current, T max) {
        if (current == null) {
            return max;
        }
        if (current.data.compareTo(max) > 0) {
            max = current.data;
        }
        return maxValueRecursive(current.next, max);
    }



    //METODO add #######################################################################################################
    public void add(T data) {
        Elemento<T> newNode = new Elemento<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Elemento<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    //METODO get #######################################################################################################
    public T get(int index){
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException("o index não se encontra dentro do da Lista de  tamanho: " + size());
        }
        Elemento<T> current = head;
        for(int i = 0; i<index; i++){
            current = current.next;
        }
        return current.data;
    }

    //METODO remove ####################################################################################################
    public void remove(T data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Elemento<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }


    //METODO findMostRepeatedElement ###################################################################################
    public T findMostRepeatedElement() {
        if (head == null) {
            throw new IllegalStateException("Lista vazia");
        }
        T max = head.data;
        Elemento<T> current = head.next;
        while (current != null) {
            if (current.data.compareTo(max) > 0) {
                max = current.data;
            }
            current = current.next;
        }
        int[] frequencies = new int[(Integer) max + 1];
        current = head;
        while (current != null) {
            frequencies[(Integer) current.data]++;
            current = current.next;
        }
        T mostRepeatedElement = null;
        int maxFrequency = 0;
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > maxFrequency) {
                maxFrequency = frequencies[i];
                mostRepeatedElement = (T) Integer.valueOf(i);
            }
        }
        if (maxFrequency > 1) {
            return mostRepeatedElement;
        } else {
            return null;
        }
    }


    //METODO set #######################################################################################################
    public void set(int index, T data){
        Elemento<T> current = head;
        int currentIndex = 0;
        while(current != null && currentIndex < index){
            current = current.next;
            currentIndex++;
        }
        if(current != null){
            current.data = data;

        }

    }


    //METODO getMaxValue ###############################################################################################
    public T getMaxValue() {
        if (head == null) {
            throw new IllegalStateException("Lista vazia");
        }

        T max = head.data;
        Elemento<T> current = head.next;
        while (current != null) {
            if (current.data.compareTo(max) > 0) {
                max = current.data;
            }
            current = current.next;
        }
        return max;
    }

    //METODO intersection ##############################################################################################
    public ListaEncadeadaGenerica<T> intersection(ListaEncadeadaGenerica<T> otherList) {
        ListaEncadeadaGenerica<T> intersectionList = new ListaEncadeadaGenerica<>();
        Elemento<T> current = head;
        while (current != null) {
            if (otherList.contains(current.data) && !intersectionList.contains(current.data)) {
                intersectionList.add(current.data);
            }
            current = current.next;
        }
        return intersectionList;
    }
    private boolean contains(T data) {
        Elemento<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    //METODO swap ######################################################################################################
    public void swap(int index1, int index2) {
        if (index1 < 0 || index2 < 0 || index1 >= size() || index2 >= size()) {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
        }
        if (index1 == index2) {
            return;
        }
        Elemento<T> prev1 = null, curr1 = head;
        for (int i = 0; i < index1; i++) {
            prev1 = curr1;
            curr1 = curr1.next;
        }
        Elemento<T> prev2 = null, curr2 = head;
        for (int i = 0; i < index2; i++) {
            prev2 = curr2;
            curr2 = curr2.next;
        }
        if (prev1 != null) {
            prev1.next = curr2;
        } else {
            head = curr2;
        }
        if (prev2 != null) {
            prev2.next = curr1;
        } else {
            head = curr1;
        }
        Elemento<T> temp = curr1.next;
        curr1.next = curr2.next;
        curr2.next = temp;
    }


    //METODO indexOf ###################################################################################################
    public int indexOf(T value) {
        Elemento<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(value)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    //METODO printList #################################################################################################
    public void printList() {
        Elemento<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");//MODIFIQUEI AQUI tirei o "ln" do print
            current = current.next;
        }
        //System.out.println();
    }

    //METODO printReversed #############################################################################################
    public void printReversed() {
        printReversed(head);
    }
    private void printReversed(Elemento<T> node) {
        if (node == null) {
            return;
        }
        printReversed(node.next);
        System.out.println(node.data + " ");//MODIFIQUEI AQUI tirei o "ln" do print
    }

    //METODO quickSort #################################################################################################
    public void quickSort() {
        T[] array = toGenericArray();
        quickSort(array, 0, array.length - 1);
        updateList(array);
    }
    private void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    //METODO selectionSort #############################################################################################
    public void selectionSort() {
        T[] array = toGenericArray();
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        updateList(array);
    }

    //METODO randomListValues ##########################################################################################
    public void randomListValues(int n, int from, int to) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int value = rand.nextInt(to - from + 1) + from;
            add((T) Integer.valueOf(value));
        }
    }

    //METODO removeDuplicates  #########################################################################################
    public void removeDuplicates() {
        Elemento<T> current = head;
        while (current != null) {
            Elemento<T> runner = current;
            while (runner.next != null) {
                if (runner.next.data.equals(current.data)) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    //METODO clear #####################################################################################################
    public void clear(){
        head = null;
    }

    //METODO bubbleSort ################################################################################################
    public void bubbleSort() {
        T[] array = toGenericArray();
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        updateList(array);
    }
    private T[] toGenericArray() {
        T[] array = (T[]) new Comparable[size()];
        Elemento<T> current = head;
        int index = 0;
        while (current != null) {
            array[index] = current.data;
            current = current.next;
            index++;
        }
        return array;
    }
    private void updateList(T[] array) {
        head = null;
        for (T i : array) {
            add(i);
        }
    }

    //METODO size ######################################################################################################
    public int size() {
        int size = 0;
        Elemento<T> current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    //METODO printEvenNumbers ##########################################################################################
    public void printEvenNumbers(){
        Elemento<T> current = head;
        while(current != null){
            if(Integer.parseInt(current.data.toString()) % 2 == 0){
                System.out.print(current.data + " ");
            }
            current = current.next;
        }
    }

    //METODO printOddNumbers ###########################################################################################
    public void printOddNumbers(){
        Elemento<T> current = head;
        while(current!=null){
            if(Integer.parseInt(current.data.toString()) % 2 != 0){
                System.out.print(current.data + " ");
            }
            current = current.next;
        }
    }

    //METODO isEmpty ###################################################################################################
    public boolean isEmpty() {
        return head == null;
    }

    //METODO sort ######################################################################################################
    public void sort(){
        T[] array = toGenericArray();
        Arrays.sort(array);
        updateList(array);
    }

    //METODO split #####################################################################################################
    public ListaEncadeadaGenerica<T> split() {
        ListaEncadeadaGenerica<T> secondHalf = new ListaEncadeadaGenerica<>();
        if (head == null || head.next == null) {
            return secondHalf;
        }

        Elemento<T> slow = head;
        Elemento<T> fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        secondHalf.head = slow.next;
        slow.next = null;

        return secondHalf;
    }


    private static class Elemento<T> {
        public T data;
        public Elemento<T> next;

        public Elemento(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
