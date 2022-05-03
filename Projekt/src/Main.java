import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a;
        boolean kontynuacja = true;
        Słownik s = new Słownik("zapisz.dat");
        while (kontynuacja)
        {
            System.out.println("MENU");
            System.out.println("Proszę wybrać cyfrę od 1 do 11");
            System.out.println("1. Dodanie słowa");
            System.out.println("2. Usunięcie słowa");
            System.out.println("3. Tłumaczenie z polskiego na angielski");
            System.out.println("4. Tłumaczenie z angielskiego na polski");
            System.out.println("5. Sortowanie alfabetycznie");
            System.out.println("6. Filtrowanie");
            System.out.println("7. Zapis do pliku binarnego");
            System.out.println("8. Wyświetlenie słownika");
            System.out.println("9. Import z pliku txt");
            System.out.println("10. Modyfikacja w pliku binarnym");
            System.out.println("11. Koniec programu");
            a=in.nextInt();
            switch (a)
            {
                case 1 -> s.dodajSlowo();
                case 2 -> s.usun();
                case 3 -> s.tlumaczeniePL();
                case 4 -> s.tlumaczenieANG();
                case 5 -> s.sortowanie();
                case 6 -> s.filtrowanie();
                case 7 -> s.ZapisDoBinar();
                case 8 -> s.wyswietlanie();
                case 9 -> s.importPlikTxt();
                case 10 -> s.modyfikacja();
                case 11 -> {
                    System.out.println("Koniec programu");
                    kontynuacja = false;
                }
                default -> System.out.println("Proszę podać jedną z powyższych cyfr");

            }
        }
    }
}
