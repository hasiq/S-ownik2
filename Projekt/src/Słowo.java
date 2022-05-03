import java.io.Serializable;

public class Słowo implements Serializable, Comparable<Słowo> {
    private String slowo;
    private String tlumacz;

    public Słowo(String slowo, String tlumacz) {
        this.slowo = slowo;
        this.tlumacz = tlumacz;
    }

    public void wyswietl() {
        System.out.println(slowo + " po angielsku oznacza " + tlumacz);
    }

    public String getSlowo() {
        return slowo;
    }

    public String getTlumacz() {
        return tlumacz;
    }


    @Override
    public String toString() {
        return "Słowo: " +
                "slowo='" + slowo + '\'' +
                ", tlumaczenie='" + tlumacz + '\'';
    }

    @Override
    public int compareTo(Słowo o) {
        return this.slowo.compareTo(o.slowo);
    }
}

