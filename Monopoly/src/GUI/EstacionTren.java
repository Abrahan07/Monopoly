/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

public class EstacionTren extends EspacioTablero {
    private int costo;
    private int alquiler;
    private Jugador propietario;

    public EstacionTren(String nombre) {
        super(nombre);
        this.costo = 175;
        this.alquiler = 20;
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

