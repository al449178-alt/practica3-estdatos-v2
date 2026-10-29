import java.util.*;

public class Mesa {
    //parte privada
    private Map<Palo, LinkedList<Carta>> filas;

    public Mesa() {
        filas = new EnumMap<>(Palo.class);
        for (Palo p : Palo.values()) {
            filas.put(p, new LinkedList<>());
        }
    }

    public boolean puedeColocar(Carta c) {
        if (c == null) {
            return false;
        }
        Palo p = c.getPalo();
        if (!paloAbierto(p)) {
            return c.getNumero() == 5;
        }
        Carta izq = getExtremoIzq(p);
        Carta der = getExtremoDer(p);
        return izq.esSiguienteDe(c) || c.esSiguienteDe(der);
    }

    public void colocar(Carta c) throws IllegalArgumentException {
        if (!puedeColocar(c)) {
            throw new IllegalArgumentException();
        }
        Palo p = c.getPalo();
        LinkedList<Carta> fila = filas.get(p);
        if (!paloAbierto(p)) {
            fila.add(c);
        } else if (getExtremoIzq(p).esSiguienteDe(c)) {
            fila.addFirst(c);
        } else {
            fila.addLast(c);
        }
    }

    public boolean paloAbierto(Palo p) {
        if (p == null) {
            return false;
        }
        return !filas.get(p).isEmpty();
    }

    public Carta getExtremoIzq(Palo p) {
        if (!paloAbierto(p)) {
            return null;
        }
        return filas.get(p).getFirst();
    }

    public Carta getExtremoDer(Palo p) {
        if (!paloAbierto(p)) {
            return null;
        }
        return filas.get(p).getLast();
    }

    public List<Carta> getFila(Palo p) {
        if (p == null || !paloAbierto(p)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(filas.get(p));
    }

    public int numCartas() {
        int total = 0;
        for (LinkedList<Carta> fila : filas.values()) {
            total += fila.size();
        }
        return total;
    }

    /** Una línea por palo, por ejemplo "copas:   [4, 5, 6]". */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Palo p : Palo.values()) {
            List<Integer> numeros = new ArrayList<>();
            for (Carta c : filas.get(p)) {
                numeros.add(c.getNumero());
            }
            sb.append(p.toString()).append(": ").append(numeros).append("\n");
        }
        return sb.toString();
    }
}
