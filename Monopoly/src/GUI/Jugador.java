/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;

public class Jugador {
    private String nombre;
    private int dinero;
    private int posicion;
    private boolean enCarcel;
    private int turnosEnCarcel;
    private JPanel ficha;

    public Jugador(String nombre, int dinero, Color colorFicha) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.posicion = 0;
        this.enCarcel = false;
        this.turnosEnCarcel = 0;
        this.ficha = new JPanel();
        this.ficha.setBackground(colorFicha);
        this.ficha.setPreferredSize(new Dimension(10, 10)); // Tamaño de la ficha
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setTurnosEnCarcel(int turnosEnCarcel) {
        this.turnosEnCarcel = turnosEnCarcel;
    }

    public void setFicha(JPanel ficha) {
        this.ficha = ficha;
    }

    
    public String getNombre() {
        return nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public int getPosicion() {
        return posicion;
    }

    public boolean isEnCarcel() {
        return enCarcel;
    }

    public int getTurnosEnCarcel() {
        return turnosEnCarcel;
    }

    public JPanel getFicha() {
        return ficha;
    }

    public void setEnCarcel(boolean enCarcel) {
        this.enCarcel = enCarcel;
        this.turnosEnCarcel = enCarcel ? 0 : this.turnosEnCarcel;
    }

    public void incrementarTurnoEnCarcel() {
        if (enCarcel) {
            turnosEnCarcel++;
        }
    }

    public void mover(int espacios, Tablero tablero) {
        if (!enCarcel) {
            posicion = (posicion + espacios) % 40;
        }
    }

    public void comprarPropiedad(Propiedad propiedad) {
        if (dinero >= propiedad.getCosto()) {
            dinero -= propiedad.getCosto();
            propiedad.setPropietario(this);
        }
    }

    public void pagarAlquiler(Propiedad propiedad) {
        if (dinero >= propiedad.getAlquiler()) {
            dinero -= propiedad.getAlquiler();
            propiedad.getPropietario().recibirAlquiler(propiedad.getAlquiler());
        } else {
            // Manejar la bancarrota
        }
    }

    public void recibirAlquiler(int alquiler) {
        dinero += alquiler;
    }

    public void pagarFianza() {
        if (dinero >= 50) {
            dinero -= 50;
            setEnCarcel(false);
        }
    }
}



