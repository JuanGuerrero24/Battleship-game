package modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Barco {
    private List<Point> puntos;
    private int impactos;

    public Barco() {
        this.puntos = new ArrayList<>();
        this.impactos = 0;
    }

    public void agregarPunto(int f, int c) {
        puntos.add(new Point(f, c));
    }

    public List<Point> getPuntos() {
        return puntos;
    }

    public boolean contienePunto(int f, int c) {
        for (Point p : puntos) {
            if (p.x == f && p.y == c) return true;
        }
        return false;
    }

    public void registrarImpacto() {
        impactos++;
    }

    public boolean estaHundido() {
        return impactos >= puntos.size();
    }
}