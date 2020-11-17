package cwiczenia;

public class petle {
    public static void main(String[] args) {
        int[] tablica = new int[10];

        for (int i = 0; i < 10; i++) {
            tablica[i] = i+ 1;
        }


        for (int x : tablica) {
            System.out.println(x);
        }
    }
}


//        if (zmienna <= 2) {
//            System.out.println("mniejsza od 2");
//        } else if (zmienna <= 4 || zmienna <= 1) {
//            System.out.println("mniejsza od 4");
//        } else {
//            System.out.println("wieksza niz 4");
//        }



