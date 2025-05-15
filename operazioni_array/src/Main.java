import java.util.Scanner;
public class Main {

    // Somma tra array di double
    public static double[] somma(double[] arr1, double[] arr2) {
        double[] result = new double[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i] + arr2[i];
        }
        return result;
    }

    // Differenza tra array di double
    public static double[] differenza(double[] arr1, double[] arr2) {
        double[] result = new double[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i] - arr2[i];
        }
        return result;
    }

    // Prodotto tra array di double
    public static double[] prodotto(double[] arr1, double[] arr2) {
        double[] result = new double[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i] * arr2[i];
        }
        return result;
    }

    // Somma di tutte le componenti di un array
    public static double sommaComponenti(double[] arr) {
        double somma = 0;
        for (int i = 0; i < arr.length; i++) {
            somma += arr[i];
        }
        return somma;
    }

    // Prodotto scalare
    public static double prodottoScalare(double[] arr1, double[] arr2) {
        double scalare = 0;
        for (int i = 0; i < arr1.length; i++) {
            scalare += arr1[i] * arr2[i];
        }
        return scalare;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Chiedere la dimensione degli array
        System.out.print("Inserisci la dimensione degli array: ");
        int n = scanner.nextInt();

        // Creazione degli array
        double[] array1 = new double[n];
        double[] array2 = new double[n];

        // Inserimento dei dati nel primo array
        System.out.println("Inserisci gli elementi del primo array:");
        for (int i = 0; i < n; i++) {
            array1[i] = scanner.nextDouble();
        }

        // Inserimento dei dati nel secondo array
        System.out.println("Inserisci gli elementi del secondo array:");
        for (int i = 0; i < n; i++) {
            array2[i] = scanner.nextDouble();
        }

        // Somma degli array
        System.out.print("Somma: ");
        double[] sommaArray = somma(array1, array2);
        for (int i = 0; i < n; i++) {
            System.out.print(sommaArray[i] + " ");
        }
        System.out.println();

        // Differenza degli array
        System.out.print("Differenza: ");
        double[] differenzaArray = differenza(array1, array2);
        for (int i = 0; i < n; i++) {
            System.out.print(differenzaArray[i] + " ");
        }
        System.out.println();

        // Prodotto degli array
        System.out.print("Prodotto: ");
        double[] prodottoArray = prodotto(array1, array2);
        for (int i = 0; i < n; i++) {
            System.out.print(prodottoArray[i] + " ");
        }
        System.out.println();

        // Somma delle componenti di array1
        System.out.println("Somma delle componenti del primo array: " + sommaComponenti(array1));

        // Somma delle componenti di array2
        System.out.println("Somma delle componenti del secondo array: " + sommaComponenti(array2));

        // Prodotto scalare
        System.out.println("Prodotto scalare: " + prodottoScalare(array1, array2));
    }
}