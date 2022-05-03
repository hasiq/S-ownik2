import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Słownik implements Serializable {
    private List<Słowo> Slownik = new ArrayList<>();
    private Map<String, Słowo> mapaPL = new HashMap<>();
    private Map<String, Słowo> mapaANG = new HashMap<>();
    private String source = null;

    public Słownik(String source) {
        this.source = source;
        ObjectInputStream odczyt = null;
        Słowo s = null;
        try {
            try {
                odczyt = new ObjectInputStream(new FileInputStream(source));
                while (true) {
                    s = (Słowo) odczyt.readObject();
                    Slownik.add(s);
                    mapaPL.put(s.getSlowo(), s);
                    mapaANG.put(s.getTlumacz(), s);
                }
            } catch (ClassNotFoundException | IOException ex) {
                System.err.println(ex.getMessage());

            } finally {
                if (odczyt != null) {
                    odczyt.close();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void dodajSlowo() {
        Scanner in = new Scanner(System.in);
        System.out.println("Proszę podać słowa do dodania: ");
        String slowo = in.nextLine();
        System.out.println("Proszę podać tłumaczenie słowa do dodania: ");
        String tlumacz = in.nextLine();
        tlumacz = tlumacz.toLowerCase(Locale.ROOT);
        slowo = slowo.toLowerCase(Locale.ROOT);
        Słowo s = new Słowo(slowo, tlumacz);
        mapaPL.put(slowo, s);
        mapaANG.put(tlumacz, s);
        Slownik.add(s);
    }

    public void dodajSlowo(String slowo, String tlumacz) {
        tlumacz = tlumacz.toLowerCase(Locale.ROOT);
        slowo = slowo.toLowerCase(Locale.ROOT);
        Słowo s = new Słowo(slowo, tlumacz);
        mapaPL.put(slowo, s);
        mapaANG.put(tlumacz, s);
        Slownik.add(s);
    }

    public void usun() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Proszę podać słowo do usunięcia: ");
            String slowo = in.nextLine();
            mapaANG.remove(mapaPL.get(slowo).getTlumacz());
            Slownik.remove(mapaPL.get(slowo));
            mapaPL.remove(slowo);
            ZapisDoBinar();
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    public void usun(String nazwa) {
        try {
            mapaANG.remove(mapaPL.get(nazwa).getTlumacz());
            Slownik.remove(mapaPL.get(nazwa));
            mapaPL.remove(nazwa);
            ZapisDoBinar();
        }catch (NullPointerException e)
        {
            System.err.println("Nie ma takiego słowa");
        }
    }

    public void tlumaczeniePL() {

        Scanner in1 = new Scanner(System.in);
        System.out.println("Podaj słowo polskie do tłumaczenia: ");
        String slowo = in1.nextLine();
        if (mapaPL.containsKey(slowo)) {
            Słowo s = mapaPL.get(slowo);
            s.wyswietl();
        } else {
            Scanner in5 = new Scanner(System.in);
            System.out.println("Nie ma takiego słowa czy chcesz je dodać(T/N)?");
            String wybor = in5.nextLine();
            wybor = wybor.toUpperCase(Locale.ROOT);
            if (wybor.equals("T") || wybor.equals("Y")) {
                dodajSlowo();
            } else if (wybor.equals("N")) {
                System.out.println("Nie dodano słowa");
            } else {
                System.out.println("Podano nieprawidłowe dane");
            }
        }
    }

    public void tlumaczenieANG() {
        Scanner in2 = new Scanner(System.in);
        System.out.println("Podaj słowo angielskie do tłumaczenia: ");
        String slowo = in2.nextLine();
        if (mapaANG.containsKey(slowo)) {
            Słowo s = mapaANG.get(slowo);
            s.wyswietl();
        } else {
            Scanner in5 = new Scanner(System.in);
            System.out.println("Nie ma takiego słowa czy chcesz je dodać(T/N)?");
            String wybor = in5.nextLine();
            wybor = wybor.toUpperCase(Locale.ROOT);
            if (wybor.equals("T") || wybor.equals("Y")) {
                dodajSlowo();
            } else if (wybor.equals("N")) {
                System.out.println("Nie dodano słowa");
            } else {
                System.out.println("Podano nieprawidłowe dane");
            }
        }
    }

    public void sortowanie() {
        List<String> sorted = Slownik.parallelStream().sorted().map(Słowo::getSlowo).collect(Collectors.toList());
        System.out.println(sorted);
    }

    public void filtrowanie() {
        Scanner in6 = new Scanner(System.in);
        System.out.println("Po czym mam filtrować?");
        String filtr = in6.nextLine();
        for (int i = 0; i < Slownik.size(); i++) {
            if (Slownik.get(i).getSlowo().contains(filtr)) {
                System.out.println(Slownik.get(i));
            }
        }
    }

    public void ZapisDoBinar() {
        File plik = null;
        ObjectOutputStream zapis = null;
        try {
            plik = new File(source);
            if (plik.exists()) {
                plik.delete();
            }
            plik.createNewFile();
            zapis = new ObjectOutputStream(new FileOutputStream(source));
            for (int i = 0; i < Slownik.size(); i++) {
                zapis.writeObject(Slownik.get(i));
                zapis.flush();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (zapis != null) {
                try {
                    zapis.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public void wyswietlanie() {
        for (Słowo slowo : Slownik) {
            slowo.wyswietl();
            System.out.println();
        }
    }

    public void importPlikTxt() {
        try {
            Scanner in7 = new Scanner(System.in);
            System.out.println("Proszę podać ścieżkę do pliku");
            String sciezka = in7.nextLine();
            BufferedReader read = new BufferedReader(new FileReader(sciezka));
            String line = null;
            while ((line = read.readLine()) != null) {
                String[] split = line.split("-");
                Słowo s = new Słowo(split[0], split[1]);
                dodajSlowo(split[0], split[1]);

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void modyfikacja() {
        Scanner in8 = new Scanner(System.in);
        System.out.println("Proszę podać słowo do zmodyfikowania: ");
        String nazwa = in8.nextLine();
            if (mapaPL.containsKey(nazwa)|| mapaANG.containsKey(nazwa)) {
                usun(nazwa);
                Scanner in4 = new Scanner(System.in);
                System.out.println("Podaj nową nazwę słowa: ");
                String slowo = in4.nextLine();
                System.out.println("Podaj tłumaczenie tego słowa: ");
                String tlumaczenie = in4.nextLine();
                dodajSlowo(slowo, tlumaczenie);
                ZapisDoBinar();
            }
            else {
                System.out.println("Słowo nie istnieje ale możesz je dodać");
                dodajSlowo();
        }
    }
}