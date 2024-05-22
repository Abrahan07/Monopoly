/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

public class Carcel extends EspacioTablero {
    public Carcel(String nombre) {
        super(nombre);
    }

    public void encarcelarJugador(Jugador jugador) {
        jugador.setEnCarcel(true);
        jugador.setPosicion(10); // Suponiendo que la cárcel está en la posición 10
    }
}

