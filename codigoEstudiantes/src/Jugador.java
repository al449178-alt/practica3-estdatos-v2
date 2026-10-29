import java.util.*;

public class Jugador {
    //parte privada completar
    private String nombre;
    private List<Carta> mano;

    public Jugador(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }


    public void recibir(Carta c) {
        if (c == null) throw new IllegalArgumentException();
        mano.add(c);
        mano.sort(null);
    }


    public boolean eliminar(Carta c) {
        if (c == null) throw new IllegalArgumentException();
        return mano.remove(c);
    }


    public int numCartas() {
        return mano.size();
    }

    public List<Carta> getMano() {
        return new ArrayList<>(mano);
    }

    public boolean puedeJugar (Mesa mesa) {
        for (Carta c : mano) {
            if (mesa != null && mesa.puedeColocar(c)) return true;
        }
        return false;
    }

    public List<Carta> cartasJugables (Mesa mesa) {
        List<Carta> jugables = new ArrayList<>();
        if (mesa != null) {
            for (Carta c : mano) {
                if (mesa.puedeColocar(c)) {
                    jugables.add(c);
                }
            }
        }
        return jugables;
    }


    public String getNombre() {
        return nombre;
    }

    /** @return false; JugadorAutomatico lo redefine */
    public boolean esAutomatico() {
        return false;
    }

    /** Dos jugadores son iguales si tienen el mismo nombre. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
                    return false;
        }
        return nombre.equals(((Jugador) o).nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    @Override
    public String toString() {
        return nombre;
    }

}
