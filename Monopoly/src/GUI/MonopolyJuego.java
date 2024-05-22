/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MonopolyJuego extends JFrame {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private int indiceJugadorActual;
    private JTextArea registroJuego;
    private JButton botonLanzarDado;
    private JButton botonTerminarTurno;
    private JPanel panelTablero;
    private JPanel[][] panelesCasillas;

    public MonopolyJuego() {
        setTitle("Juego de Monopoly");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tablero = new Tablero();
        jugadores = new ArrayList<>();
        indiceJugadorActual = 0;

        panelTablero = new JPanel(new GridLayout(9, 9)); // Diseño de 9x9 para el tablero
        panelesCasillas = new JPanel[9][9];
        crearTableroVisual();

        JScrollPane scrollPane = new JScrollPane(panelTablero);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        add(scrollPane, BorderLayout.CENTER);

        inicializarJugadores(4); // Número configurable de jugadores

        registroJuego = new JTextArea();
        registroJuego.setEditable(false);
        add(new JScrollPane(registroJuego), BorderLayout.EAST);

        JPanel panelControl = new JPanel();
        botonLanzarDado = new JButton("Lanzar Dado");
        botonTerminarTurno = new JButton("Terminar Turno");

        panelControl.add(botonLanzarDado);
        panelControl.add(botonTerminarTurno);
        add(panelControl, BorderLayout.SOUTH);

        botonLanzarDado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lanzarDado();
            }
        });

        botonTerminarTurno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                terminarTurno();
            }
        });

        actualizarRegistroJuego("Juego iniciado con " + jugadores.size() + " jugadores.");
    }

    private void inicializarJugadores(int numeroDeJugadores) {
        Color[] colores = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
        for (int i = 1; i <= numeroDeJugadores; i++) {
            Jugador jugador = new Jugador("Jugador " + i, 1500, colores[i - 1]);
            jugadores.add(jugador);
            colocarFichaEnCasilla(jugador, 0); // Coloca la ficha en la posición de salida (0)
        }
    }

    private void lanzarDado() {
        int dado1 = (int) (Math.random() * 6) + 1;
        int dado2 = (int) (Math.random() * 6) + 1;
        int total = dado1 + dado2;
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);

        if (jugadorActual.isEnCarcel()) {
            if (dado1 == dado2) {
                jugadorActual.setEnCarcel(false);
                actualizarRegistroJuego(jugadorActual.getNombre() + " ha sacado dobles y sale de la cárcel.");
            } else {
                jugadorActual.incrementarTurnoEnCarcel();
                actualizarRegistroJuego(jugadorActual.getNombre() + " está en la cárcel y ha sacado " + total + ".");
                if (jugadorActual.getTurnosEnCarcel() >= 3) {
                    jugadorActual.pagarFianza();
                    actualizarRegistroJuego(jugadorActual.getNombre() + " ha pagado fianza y sale de la cárcel.");
                }
            }
        } else {
            moverFicha(jugadorActual, total);
            verificarPosicion(jugadorActual);
        }
    }

    private void moverFicha(Jugador jugador, int espacios) {
        int posicionAnterior = jugador.getPosicion();
        jugador.mover(espacios, tablero);
        int nuevaPosicion = jugador.getPosicion();
        actualizarRegistroJuego(jugador.getNombre() + " se movió de la posición " + posicionAnterior + " a la posición " + nuevaPosicion);
        colocarFichaEnCasilla(jugador, nuevaPosicion);
    }

    private void colocarFichaEnCasilla(Jugador jugador, int posicion) {
        Point coordenadas = obtenerCoordenadas(posicion);
        JPanel panelCasilla = panelesCasillas[coordenadas.x][coordenadas.y];
        panelCasilla.add(jugador.getFicha());
        panelCasilla.revalidate();
        panelCasilla.repaint();
    }

    private Point obtenerCoordenadas(int posicion) {
        if (posicion >= 0 && posicion < 40) {
            if (posicion < 9) {
                return new Point(0, posicion);
            } else if (posicion < 18) {
                return new Point(posicion - 8, 8);
            } else if (posicion < 27) {
                return new Point(8, 26 - posicion);
            } else {
                return new Point(35 - posicion, 0);
            }
        }
        return null;
    }

    private void verificarPosicion(Jugador jugador) {
        EspacioTablero espacio = tablero.getEspacio(jugador.getPosicion());
        if (espacio instanceof Propiedad) {
            Propiedad propiedad = (Propiedad) espacio;
            if (propiedad.getPropietario() == null) {
                int opcion = JOptionPane.showConfirmDialog(this, jugador.getNombre() + ", ¿quieres comprar " + propiedad.getNombre() + " por $" + propiedad.getCosto() + "?", "Comprar Propiedad", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    jugador.comprarPropiedad(propiedad);
                    actualizarRegistroJuego(jugador.getNombre() + " compró " + propiedad.getNombre());
                }
            } else if (propiedad.getPropietario() != jugador) {
                jugador.pagarAlquiler(propiedad);
                actualizarRegistroJuego(jugador.getNombre() + " pagó alquiler a " + propiedad.getPropietario().getNombre() + " por " + propiedad.getNombre());
            }
        } else if (espacio instanceof Carcel) {
            Carcel carcel = (Carcel) espacio;
            carcel.encarcelarJugador(jugador);
            actualizarRegistroJuego(jugador.getNombre() + " ha sido enviado a la cárcel.");
        } else if (espacio instanceof Impuesto) {
            Impuesto impuesto = (Impuesto) espacio;
            jugador.pagarAlquiler(new Propiedad("Impuesto", impuesto.getMonto(), 0)); // Simulación de pago de impuesto
            actualizarRegistroJuego(jugador.getNombre() + " ha pagado $" + impuesto.getMonto() + " de impuestos.");
        } else {
            // Manejar otros tipos de espacios (por ejemplo, Chance, Community Chest, etc.)
         //   manejarEspacioEspecial(jugador, espacio);
        }
    }

    /* private void manejarEspacioEspecial(Jugador jugador, EspacioTablero espacio) {
        // Implementar manejo de espacios especiales como "Chance" o "Community Chest"
        if (espacio instanceof Chance) {
            Chance chance = (Chance) espacio;
            chance.aplicarEfecto(jugador);
            actualizarRegistroJuego(jugador.getNombre() + " ha aterrizado en un espacio de " + espacio.getNombre() + " y ha recibido un efecto.");
        } else if (espacio instanceof CommunityChest) {
            CommunityChest communityChest = (CommunityChest) espacio;
            communityChest.aplicarEfecto(jugador);
            actualizarRegistroJuego(jugador.getNombre() + " ha aterrizado en un espacio de " + espacio.getNombre() + " y ha recibido un efecto.");
        }
    }  */

    private void terminarTurno() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
        actualizarRegistroJuego("Es el turno de " + jugadores.get(indiceJugadorActual).getNombre() + ".");
    }

    private void actualizarRegistroJuego(String mensaje) {
        registroJuego.append(mensaje + "\n");
    }

    private void crearTableroVisual() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int index = calcularIndex(i, j);
                JPanel panelCasilla = new JPanel();
                panelCasilla.setPreferredSize(new Dimension(60, 60));
                panelesCasillas[i][j] = panelCasilla;

                if (index >= 0 && index < 40) {
                    JButton botonCasilla = new JButton(tablero.getEspacio(index).getNombre());
                    botonCasilla.setPreferredSize(new Dimension(60, 60));
                    botonCasilla.setBackground(obtenerColorCasilla(index));
                    panelCasilla.add(botonCasilla);
                }

                panelCasilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panelTablero.add(panelCasilla);
            }
        }
    }

    private int calcularIndex(int i, int j) {
        if (i == 0) {
            return j;
        } else if (i == 8) {
            return 26 + (8 - j);
        } else if (j == 0) {
            return 26 - i;
        } else if (j == 8) {
            return 8 + i;
        }
        return -1;
    }

    private Color obtenerColorCasilla(int index) {
        // Cambiar colores dependiendo del tipo de casilla
        if (index % 10 == 0) {
            return Color.LIGHT_GRAY; // Esquinas
        } else if (index % 5 == 0) {
            return Color.CYAN; // Estaciones
        } else {
            return Color.WHITE; // Propiedades normales
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MonopolyJuego().setVisible(true);
            }
        });
    }
}


