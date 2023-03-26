/*
  ____                             _  ___       _   _      ____
 / ___|  __ _ _ __ ___  _   _  ___| |/ _ \ _ __| |_(_)____/ ___|
 \___ \ / _` | '_ ` _ \| | | |/ _ \ | | | | '__| __| |_  /\___ \
  ___) | (_| | | | | | | |_| |  __/ | |_| | |  | |_| |/ /  ___) |
 |____/ \__,_|_| |_| |_|\__,_|\___|_|\___/|_|   \__|_/___||____/
 Creado por: Samuel Ortiz Sarasti (202127049)
 Para el curso de Fundamentos de Programacion Orientada a Eventos
 Mini Proyecto 1
 Creacion de una calculadora simple funcional con GUI
 Domingo 26 de Marzo de 2023
 */


package fdpoe.univalle;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Calculadora();
                frame.setSize(300, 400);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        //Ventana de confirmacion al momento de cerrar el programa
                        int opc = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Mensaje de Confirmación", JOptionPane.OK_CANCEL_OPTION);
                        if (opc == 0) {
                            System.exit(0);
                        }
                    }
                });
            }
        });
    }
}