public class Kolekcija {
    private Object[] niz;

    public Kolekcija() {
        niz = new Object[0];
    }

    public int brojElemenata(){
        return niz.length;
    }

    public Object get(int index){

      return  niz[index];
    }

    public void dodaj(Object object) {
        Object[] objects = new Object[niz.length + 1];

        for (int i = 0; i < niz.length; i++) {
            objects[i] = niz[i];
        }
        objects[niz.length] = object;
        niz = objects;

    }

    public void obrisi(Object object) {

        int index = -1;
        for (int i = 0; i < niz.length; i++) {
            if (niz[i].equals(object)) {
                index = i;
            }
        }

        if (index == -1) {
            return;
        }

        Object[] objects = new Object[niz.length - 1];
        int counter = 0;
        for (int i = 0; i < niz.length; i++) {
            if (index != i) {
                objects[counter] = niz[i];
                counter++;
            }
        }
        niz = objects;
    }
}
