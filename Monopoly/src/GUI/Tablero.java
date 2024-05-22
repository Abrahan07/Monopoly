/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<EspacioTablero> espacios;

    public Tablero() {
        espacios = new ArrayList<>(40);
        inicializarEspacios();
    }

    private void inicializarEspacios() {
    espacios.add(new EspacioTablero("Salida"));
    espacios.add(new Propiedad("Mediterranean Avenue", 60, 2));
    espacios.add(new EspacioTablero("Community Chest"));
    espacios.add(new Propiedad("Baltic Avenue", 60, 4));
    espacios.add(new Impuesto("Income Tax", 200));
    espacios.add(new EstacionTren("Reading Railroad"));
    espacios.add(new Propiedad("Oriental Avenue", 100, 6));
    espacios.add(new EspacioTablero("Chance"));
    espacios.add(new Propiedad("Vermont Avenue", 100, 6));
    espacios.add(new Propiedad("Connecticut Avenue", 120, 8));
    espacios.add(new Carcel("Jail"));
    espacios.add(new Propiedad("St. Charles Place", 140, 10));
    espacios.add(new ServicioPublico("Electric Company"));
    espacios.add(new Propiedad("States Avenue", 140, 10));
    espacios.add(new Propiedad("Virginia Avenue", 160, 12));
    espacios.add(new EstacionTren("Pennsylvania Railroad"));
    espacios.add(new Propiedad("St. James Place", 180, 14));
    espacios.add(new EspacioTablero("Community Chest"));
    espacios.add(new Propiedad("Tennessee Avenue", 180, 14));
    espacios.add(new Propiedad("New York Avenue", 200, 16));
    espacios.add(new EspacioTablero("Chance"));
    espacios.add(new Propiedad("Kentucky Avenue", 220, 18));
    espacios.add(new Propiedad("Indiana Avenue", 220, 18));
    espacios.add(new ServicioPublico("Water Works"));
    espacios.add(new Propiedad("Illinois Avenue", 240, 20));
    espacios.add(new Propiedad("Atlantic Avenue", 260, 22));
    espacios.add(new Propiedad("Ventnor Avenue", 260, 22));
    espacios.add(new EstacionTren("B. & O. Railroad"));
    espacios.add(new Propiedad("Marvin Gardens", 280, 24));
    espacios.add(new EspacioTablero("Go To Jail"));
    espacios.add(new Propiedad("Pacific Avenue", 300, 26));
    espacios.add(new Propiedad("North Carolina Avenue", 300, 26));
    espacios.add(new EspacioTablero("Community Chest"));
    espacios.add(new Propiedad("Pennsylvania Avenue", 320, 28));
    espacios.add(new ServicioPublico("Short Line"));
    espacios.add(new EspacioTablero("Chance"));
    espacios.add(new Propiedad("Park Place", 350, 35));
    espacios.add(new Impuesto("Luxury Tax", 100));
    espacios.add(new Propiedad("Boardwalk", 400, 50));
}


    public EspacioTablero getEspacio(int posicion) {
        return espacios.get(posicion);
    }
}
