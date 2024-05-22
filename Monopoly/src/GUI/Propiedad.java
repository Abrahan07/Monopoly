/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

public class Propiedad extends EspacioTablero {
    private int costo;
    private int alquiler;
    private Jugador propietario;

    public Propiedad(String nombre, int costo, int alquiler) {
        super(nombre);
        this.costo = costo;
        this.alquiler = alquiler;
        this.propietario = null;
    }

    public int getCosto() {
        return costo;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }
}

