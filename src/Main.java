public class Main {

    public static void main(String[] args) {
        ListaEncadeadaGenerica<Integer> lista = new ListaEncadeadaGenerica<>();

        // EXEMPLOS DE USO:

        lista.add(3);
        lista.add(1);
        lista.add(4);
        lista.add(1);
        lista.add(5);
        lista.add(9);

        System.out.println("Lista original:");
        lista.printList();
        System.out.println();

        System.out.println("Soma dos elementos: " + lista.sum());
        System.out.println();

        System.out.println("Criptomoeda mais repetida: " + lista.findMostRepeatedCrypto());
        System.out.println();

        System.out.println("Média dos elementos: " + lista.average());
        System.out.println();

        System.out.println("Menor valor: " + lista.minValue());
        System.out.println();

        System.out.println("Maior valor: " + lista.maxValue());
        System.out.println();

        System.out.println("Elemento mais repetido: " + lista.findMostRepeatedElement());
        System.out.println();

        System.out.println("Números pares:");
        lista.printEvenNumbers();
        System.out.println();

        System.out.println("Números ímpares:");
        lista.printOddNumbers();
        System.out.println();

        lista.sort();
        System.out.println("Lista ordenada:");
        lista.printList();
        System.out.println();

        lista.removeDuplicates();
        System.out.println("Lista sem elementos duplicados:");
        lista.printList();
        System.out.println();

        lista.clear();
        System.out.println("Lista após limpeza:");
        lista.printList();
        System.out.println();
    }
}
