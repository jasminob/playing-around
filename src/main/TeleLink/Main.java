package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Imenik imenik = new Imenik();
        imenik.dodaj("Adnan", new MobilniBroj(61, "555-555"));
        imenik.dodaj("Selki", new MobilniBroj(61, "783-555"));
        imenik.dodaj("Mends", new MobilniBroj(62, "783-555"));
        imenik.dodaj("Lelo", new FiksniBroj(FiksniBroj.Grad.SARAJEVO, "666-555"));
        imenik.dodaj("Neko", new FiksniBroj(FiksniBroj.Grad.SARAJEVO, "777-555"));
        imenik.dodaj("Mata", new FiksniBroj(FiksniBroj.Grad.SARAJEVO, "951-555"));

        imenik.dodaj("Mendenz", new MedunarodniBroj("+387", "783-555"));
        imenik.dodaj("Mona", new MedunarodniBroj("+386", "783-555"));
        imenik.dodaj("Merso", new MedunarodniBroj("+380", "103-555"));


        System.out.println(imenik.naSlovo('S'));


        Set<String> s = imenik.izGrada(FiksniBroj.Grad.SARAJEVO);
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        Set<TelefonskiBroj​​> tb = imenik.izGradaBrojevi(FiksniBroj.Grad.SARAJEVO);
        Iterator<TelefonskiBroj​​> ter = tb.iterator();
        while (ter.hasNext()) {
            System.out.println(ter.next().ispisi());
        }

    }
}

abstract class TelefonskiBroj​​ implements Comparable<TelefonskiBroj​​> {

    public abstract String ispisi();

    public abstract int hashCode();
}

class FiksniBroj extends TelefonskiBroj​​ {

    private String broj;

    public String getBroj() {
        return broj;
    }


    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Grad getG() {
        return g;
    }

    public void setG(Grad g) {
        this.g = g;
    }

    private Grad g;

    public FiksniBroj(Grad grad, String broj) {
        this.broj = broj;
        this.g = grad;
    }

    @Override
    public String ispisi() {
        return String.format("%s/%s", g, broj);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(TelefonskiBroj​​ o) {
        return ispisi().compareTo(o.ispisi());


    }

    enum Grad {
        SARAJEVO(33),
        TUZLA(35),
        ZENICA(32),
        TRAVNIK(30),
        ORASJE(31), LIVNO(34),
        MOSTAR(36),
        BanjaLuka(51),
        BRCKO(49);
        private int pozivni;

        Grad(int pozivni) {
            this.pozivni = pozivni;
        }

        public int getPozivni() {
            return pozivni;
        }

        @Override
        public String toString() {
            String s;
            s = pozivni + "";
            while (s.length() < 3) {
                s = "0" + s;
            }
            return s;
        }
    }
}

class MobilniBroj extends TelefonskiBroj​​ {

    int mobilnaMreza;
    String broj;

    public MobilniBroj(int mobilnaMreza, String broj) {
        this.mobilnaMreza = mobilnaMreza;
        this.broj = broj;
    }


    @Override
    public String ispisi() {
        return String.format("0%d/%s", mobilnaMreza, broj);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(TelefonskiBroj​​ o) {
        return 0;
    }
}

class MedunarodniBroj extends TelefonskiBroj​​ {
    private String drzava;
    private String broj;

    public MedunarodniBroj(String drzava, String broj) {
        this.drzava = drzava;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        return String.format("%s/%s", drzava, broj);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(TelefonskiBroj​​ o) {
        return 0;
    }
}

class Imenik {

    private HashMap<String, TelefonskiBroj​​> imenik = new HashMap<>();


    public void dodaj(String ime, TelefonskiBroj​​ broj​​) {
        imenik.put(ime, broj​​);
    }

    public String dajBroj(String ime) {
        return imenik.get(ime).ispisi();
    }

    public String naSlovo(char s) {
        int i = 1;
        String bucket = "";

        for (Map.Entry<String, TelefonskiBroj​​> pair : imenik.entrySet()) {

            if (pair.getKey().startsWith(s + "")) {
                bucket = String.format("%d. %s - %s ", i, pair.getKey(), pair.getValue().ispisi());
                i++;
            }
        }
        return bucket;
    }

    public Set<String> izGrada(FiksniBroj.Grad g) {

        TreeSet<String> bucket = new TreeSet<>();
        for (Map.Entry<String, TelefonskiBroj​​> pair : imenik.entrySet()) {
            TelefonskiBroj​​ broj = pair.getValue();
            if (broj instanceof FiksniBroj) {
                if (((FiksniBroj) broj).getG().equals(g)) {
                    bucket.add(String.format("%s", pair.getKey()));
                }
            }
        }
        return bucket;
    }

    public Set<TelefonskiBroj​​> izGradaBrojevi(FiksniBroj.Grad g) {

        TreeSet<TelefonskiBroj​​> bucket = new TreeSet<>();
        for (Map.Entry<String, TelefonskiBroj​​> pair : imenik.entrySet()) {
            if (pair.getValue() instanceof FiksniBroj)
                if (((FiksniBroj) pair.getValue()).getG().equals(g)) {
                    bucket.add(pair.getValue());
                }
        }
        return bucket;
    }

}
