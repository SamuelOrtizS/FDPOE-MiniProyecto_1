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