/**
 * Carta inmutable de la baraja española de 40 cartas.
 * Números válidos: 1 a 7, 10 (sota), 11 (caballo) y 12 (rey).
 */
public final class Carta implements Comparable<Carta> {

    private final Palo palo;
    private final int numero;

    /**
     * @throws IllegalArgumentException si el palo es null o el número no está en {1..7, 10, 11, 12}
     */
    public Carta(Palo palo, int numero) {
        if (palo==null || (numero <1 || numero >12 || (numero > 7 && numero <10))) throw new IllegalArgumentException();
        this.palo=palo;
        this.numero=numero;

    }

    public Palo getPalo() {
        return this.palo;
    }

    public int getNumero() {

        return this.numero;
    }


    public int getOrden() {
        if (this.numero>7) return this.numero-2;
        return this.numero;
    }


    public boolean esSiguienteDe(Carta otra) {
        if(otra == null) return false;
        return this.palo == otra.getPalo() && this.getOrden() == otra.getOrden() + 1;
    }


    @Override
    public int compareTo(Carta otra) {
        int compPalo = this.palo.compareTo(otra.getPalo());
        if (compPalo != 0) {
            return compPalo;
        }
        return Integer.compare(this.getOrden(), otra.getOrden());
    }

    @Override
    public String toString() {
        String carta="";
        switch (this.numero){
            case 1:
                carta="As de "+this.palo.toString();
                break;
            case 2:
                carta="Dos de "+this.palo.toString();
                break;
            case 3:
                carta="Tres de "+this.palo.toString();
                break;
            case 4:
                carta="Cuatro de "+this.palo.toString();
                break;
            case 5:
                carta="Cinco de "+this.palo.toString();
                break;
            case 6:
                carta="Seis de "+this.palo.toString();
                break;
            case 7:
                carta="Siete de "+this.palo.toString();
                break;
            case 10:
                carta="Sota de "+this.palo.toString();
                break;
            case 11:
                carta="Caballo de "+this.palo.toString();
                break;
            case 12:
                carta="Rey de "+this.palo.toString();
                break;
        }
        return carta;
    }

    /** Dos cartas son iguales si tienen el mismo palo y el mismo número. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Carta)) {
            return false;
        }
        Carta otra = (Carta) o;
        return palo == otra.palo && numero == otra.numero;
    }

    /** Coherente con equals. */
    @Override
    public int hashCode() {
        return palo.ordinal() * 13 + numero;
    }


}
