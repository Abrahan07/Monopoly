/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

public class Impuesto extends EspacioTablero {
    private int monto;

    public Impuesto(String nombre, int monto) {
        super(nombre);
        this.monto = monto;
    }

    public int getMonto() {
        return monto;
    }
}

