package fdpoe.univalle;

import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {
    //Botones para la memoria
    private JButton bMemoriaLimpiar, bMemoriaGuardar, bMemoriaRecuperar;
    //Botones para los operadores
    private JButton bDivision, bMultiplicacion, bResta, bSuma;
    //Botones para las funciones
    private JButton bLimpiar, bBorrar, bPorcentaje, bIgual, bSigno, bPunto;
    //Botones para el ingreso de Digitos
    private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    //Panel que contiene toda la GUI
    private JPanel pPrincipal;
    //Panel que simula la pantalla de la calculadora
    private JPanel pDisplay;
    //Contenido de la "pantalla"
    //Campos de texto a modificar durante la ejecucion del programa
    private JTextField tOperando1, tOperando2, tResultado;
    //Indicador de la operacion que esta llevando a cabo
    private JLabel lOperador;
    //Separador de acuerdo a la guia dada
    private JSeparator separador;
    //Variables que permiten el funcionamiento del programa
    //Doubles para guardar los diferentes numeros permitiendo operaciones fraccionarias
    private double dOperando1, dOperando2, dResultado, dMemoria, dActual;
    //String que se envia a un campo de texto cuando es requerido
    private String sActual;
    //Booleanos que indican diferentes estados a tener en cuenta durante la ejecucion
    private boolean esSegundoOperando, tieneDecimal, mostrandoResultado;
    //Caracter que indica la operacion a realizar y es enviado a "lOperador"
    private char cOperador;

    public Calculadora() {
        //Asigno el panel que contiene la GUI
        setContentPane(pPrincipal);

        //Asigno la operacion de cerrado por defecto en "No hacer nada en cierre" con el objetivo de darle un manejo personalizado
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //Asigno el titulo de la ventana
        super.setTitle("Calculadora Simple - FDPOE (SamuelOrtizS)");

        //Inicio las variables que requieren un estado inicial controlado
        sActual = "";
        esSegundoOperando = false;
        cOperador = ' ';
        tieneDecimal = false;
        mostrandoResultado = false;

        //Elimino los bordes innecesarios en los campos de texto
        tOperando1.setBorder(BorderFactory.createEmptyBorder());
        tOperando2.setBorder(BorderFactory.createEmptyBorder());
        tResultado.setBorder(BorderFactory.createEmptyBorder());

        //Muestro las variables en sus respectivos componentes
        tOperando1.setText(String.valueOf(dOperando1));
        tOperando2.setText(String.valueOf(dOperando2));
        tResultado.setText(String.valueOf(dResultado));

        //Elimino la propiedad "focusable" de los campos de texto y el panel que los contiene
        tOperando1.setFocusable(false);
        tOperando2.setFocusable(false);
        tResultado.setFocusable(false);
        pDisplay.setFocusable(false);

        //Cambio el color del separador
        separador.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        //Asigno los botones a sus respectivos grupos
        //Listas con los grupos de botones que comparten caracteristicas
        JButton[] botonesDigitos = new JButton[12];
        botonesDigitos[0] = b0;
        botonesDigitos[1] = b1;
        botonesDigitos[2] = b2;
        botonesDigitos[3] = b3;
        botonesDigitos[4] = b4;
        botonesDigitos[5] = b5;
        botonesDigitos[6] = b6;
        botonesDigitos[7] = b7;
        botonesDigitos[8] = b8;
        botonesDigitos[9] = b9;
        botonesDigitos[10] = bPunto;
        botonesDigitos[11] = bSigno;

        JButton[] botonesMemoria = new JButton[3];
        botonesMemoria[0] = bMemoriaLimpiar;
        botonesMemoria[1] = bMemoriaGuardar;
        botonesMemoria[2] = bMemoriaRecuperar;

        JButton[] botonesSimbolos = new JButton[6];
        botonesSimbolos[0] = bDivision;
        botonesSimbolos[1] = bPorcentaje;
        botonesSimbolos[2] = bMultiplicacion;
        botonesSimbolos[3] = bResta;
        botonesSimbolos[4] = bSuma;
        botonesSimbolos[5] = bIgual;

        JButton[] botonesFunciones = new JButton[2];
        botonesFunciones[0] = bLimpiar;
        botonesFunciones[1] = bBorrar;

        //Asigno los colores a los diferentes grupos de botones
        //Para el mejor contraste, decidi mantener un tono cercano al negro, pero menos agresivo, siendo perfecto el gris oscuro
        for (int i = 0; i < 12; i++) {
            botonesDigitos[i].setForeground(Color.DARK_GRAY);
        }
        //Al tener otros 3 grupos de Botones Claramente diferenciados decidi crear una paleta de "Triada" en https://color.adobe.com/es/create/color-wheel
        //Partiendo de un tono rojizo (#FF2001) como base para llamar fuertemente la atencion en los botones de Borrar y Limpiar
        for (int i = 0; i < 2; i++) {
            botonesFunciones[i].setForeground(new Color(255,32,1,255));
        }
        //Este me definio 2 tonos azulados (#29518C y #558AD9) y 2 tonos verdosos (#B5D93F y #C4FF00)
        //Me decanto por los tonos azulados para los campos de memoria, al relacionar esta con la seguridad de tener un dato almacenado de forma segura.
        //Escogiendo asi el color #29518C dado su mejor contraste con los botones
        for (int i = 0; i < 3; i++) {
            botonesMemoria[i].setForeground(new Color(41,81,140,255));
        }
        //De los tonos verdosos que quedaron disponibles, ninguno se adecua a una visualizacion correcta de la GUI,
        //con lo que decido tomar un complementario del rojo original, el verde #00B35B y analizar varias tonalidades del mismo
        //Escogiendo finalmente el verde #00733B
        for (int i = 0; i < 6; i++) {
            botonesSimbolos[i].setForeground(new Color(0,115,59,255));
        }
        //Curiosamente esto me lleva accidentalmente a usar una triada RGB, la cual suele ser optima para una adecuada legibilidad y contraste.
        //Finalmente decido cambiar de gris oscuro a negro los botones de punto y cambio de signo, para diferenciarlos ligeramente de los numericos
        bPunto.setForeground(Color.BLACK);
        bSigno.setForeground(Color.BLACK);

        //Escuchadores para los botones
        b0.addActionListener(e -> agregarDigito(0));
        b1.addActionListener(e -> agregarDigito(1));
        b2.addActionListener(e -> agregarDigito(2));
        b3.addActionListener(e -> agregarDigito(3));
        b4.addActionListener(e -> agregarDigito(4));
        b5.addActionListener(e -> agregarDigito(5));
        b6.addActionListener(e -> agregarDigito(6));
        b7.addActionListener(e -> agregarDigito(7));
        b8.addActionListener(e -> agregarDigito(8));
        b9.addActionListener(e -> agregarDigito(9));
        bLimpiar.addActionListener(e -> limpiar());
        bSuma.addActionListener(e -> {
            //Actualizo el operador en la GUI
            cOperador = '+';
            actualizarOperador();
        });
        bResta.addActionListener(e -> {
            //Actualizo el operador en la GUI
            cOperador = '-';
            actualizarOperador();
        });
        bMultiplicacion.addActionListener(e -> {
            //Actualizo el operador en la GUI
            cOperador = '*';
            actualizarOperador();
        });
        bDivision.addActionListener(e -> {
            //Actualizo el operador en la GUI
            cOperador = '/';
            actualizarOperador();
        });
        bBorrar.addActionListener(e -> {
            try {
                // Si elimino el punto, actualizo la variable que me pertite saber si tiene decimales
                if (sActual.charAt(sActual.length() - 1) == '.') {
                    tieneDecimal = false;
                }
                //Cambio la cadena de texto por la subcadena que va desde el primer caracter hasta el penultimo
                sActual = sActual.substring(0, sActual.length() - 1);
                dActual = Double.parseDouble(sActual);
                actualizarOperandos();
            } catch (Exception exception) {
                //En caso de eliminar todos los caracteres, vuelvo cero la cadena
                dActual = 0.0;
                sActual = String.valueOf(dActual);
                actualizarOperandos();
            }
        });
        bMemoriaGuardar.addActionListener(e -> {
            if (mostrandoResultado) { //Si se esta mostrando un resultado, guardo el mismo en memoria y limpio el programa
                double memoriaTemporal = dResultado; //Guardando temporalmente el resultado de modo que no se borre al limpiar
                limpiar();
                dMemoria = memoriaTemporal;
            } else { //En cualquier otro caso
                //Convierto en cero el operando actual y lo guardo en memoria
                dMemoria = dActual;
                dActual = 0.0;
                sActual = "";
            }

            actualizarOperandos();
        });
        bMemoriaLimpiar.addActionListener(e -> {
            //Elimino la memoria convirtiendola en cero
            dMemoria = 0.0;
        });
        bMemoriaRecuperar.addActionListener(e -> {
            //Asigno al operando actual el valor guardado en memoria
            dActual = dMemoria;
            actualizarOperandos();
        });
        bPunto.addActionListener(e -> {
            //Si el numero no tiene decimales
            if (!tieneDecimal) {
                if (sActual.equals("")) { //Si la cadena esta vacia, agrego cero y el punto decimal
                    sActual = "0.";
                    tieneDecimal = true;
                } else if (Double.parseDouble(sActual) == 0.0) { //Si la cadena vale cero, la convierto en cero y un punto decimal
                    sActual = "0.";
                    tieneDecimal = true;
                } else {
                    if (sActual.charAt(sActual.length() - 1) != '.') { //Si la cadena tiene un valor diferente de cero, agrego el punto decimal al final
                        sActual = sActual + ".";
                        tieneDecimal = true;
                        //Siempre que se agregue el punto decimal actualizo la variable que lo indica
                    }
                }
            }
        });
        bIgual.addActionListener(e -> {
            //Dependiendo de la operacion seleccionada, opero los 2 operandos y guardo el resultado
            if (cOperador == '+') {
                dResultado = dOperando1 + dOperando2;
            } else if (cOperador == '-') {
                dResultado = dOperando1 - dOperando2;
            } else if (cOperador == '*') {
                dResultado = dOperando1 * dOperando2;
            } else if (cOperador == '/') {
                dResultado = dOperando1 / dOperando2;
            }
            //Señalo al programa que se esta mostrando un resultado mediante la variable correspondiente
            mostrandoResultado = true;
            //Muestro el resultado en el area de texto adecuada
            tResultado.setText(String.valueOf(dResultado));
        });
        bSigno.addActionListener(e -> {
            if (!tieneDecimal) {
                if (sActual.equals("")) { //Si la cadena esta vacia, la convierto en el signo negativo y continuo
                    dActual = -0.0;
                    sActual = "-";
                } else if (sActual.equals("-")) { //Si la cadena solo tiene el signo negativo, la convierto en vacia y continuo
                    dActual = 0.0;
                    sActual = "";
                } else if (Double.parseDouble(sActual) == 0.0) { //Si la cadena vale cero, la vuelvo cero negativo y continuo
                    dActual = -0.0;
                    sActual = "-0";
                } else if (Double.parseDouble(sActual) == -0.0) { //Si la cadena vale cero negativo, la vuelvo cero y continuo
                    dActual = 0.0;
                    sActual = "0";
                } else { //En cualquier otro caso, le coloco o quito el signo negativo al inicio
                    if (sActual.charAt(0) == '-') { //Si ya es negativo, tomo la cadena sin el primer digito
                        dActual = dActual * (-1);
                        sActual = sActual.substring(1);
                    } else {//Si no es negativo, coloco el negativo al inicio
                        dActual = dActual * (-1);
                        sActual = "-" + sActual;
                    }
                }
            } else {
                if (sActual.charAt(0) == '-') { //Si ya es negativo, tomo la cadena sin el primer digito
                    dActual = dActual * (-1);
                    sActual = sActual.substring(1);
                } else {//Si no es negativo, coloco el negativo al inicio
                    dActual = dActual * (-1);
                    sActual = "-" + sActual;
                }
            }
            actualizarOperandos();
        });
        bPorcentaje.addActionListener(e -> {
            //Entendiendo la funcion del porcentaje de la calculadora como
            // a +-*/ (b por ciento de a)
            if (cOperador == '+') {
                dResultado = dOperando1 + ( dOperando1 * ( dOperando2 / 100 ));
            } else if (cOperador == '-') {
                dResultado = dOperando1 - ( dOperando1 * ( dOperando2 / 100 ));
            } else if (cOperador == '*') {
                dResultado = dOperando1 * ( dOperando1 * ( dOperando2 / 100 ));
            } else if (cOperador == '/') {
                dResultado = dOperando1 / ( dOperando1 * ( dOperando2 / 100 ));
            }
            mostrandoResultado = true;
            tResultado.setText(String.valueOf(dResultado));
        });
    }

    private void agregarDigito(int _digito) { //Al ser una operacion repetitiva en multiples botones, uso un metodo en lugar de eescribir el mismo codigo en cada eschuchador
        if (mostrandoResultado) { // Si se esta mostrando un resultado, limpio el programa y luego empiezo a escribir
            limpiar();
        }
        try {
            //Agrega un digito a la cadena de texto y posteriormente lo asigna a una variable que permite realizar operaciones
            sActual = sActual + _digito;
            dActual = Double.parseDouble(sActual);
            actualizarOperandos();
        } catch (Exception exception) {
            // En caso de introducir mas de 2 puntos, se detecta y borra el punto adicional, como medida adicional a la variable "tieneDecimal"
            if (sActual.charAt(sActual.length() - 2) == '.') {
                sActual = sActual.substring(0, sActual.length() - 2);
            }
        }
    }

    private void limpiar() {
        //Asigno todas las variables a su valor inicial, al igual que los campos de texto necesarios en la GUI
        dMemoria = 0.0;
        dActual = 0.0;
        sActual = "";
        dOperando1 = 0.0;
        dOperando2 = 0.0;
        dResultado = 0.0;
        tOperando1.setText(String.valueOf(dOperando1));
        tOperando2.setText(String.valueOf(dOperando2));
        tResultado.setText(String.valueOf(dResultado));
        cOperador = ' ';
        lOperador.setText(String.valueOf(cOperador));
        tieneDecimal = false;
        esSegundoOperando = false;
        mostrandoResultado = false;
    }

    private void actualizarOperandos() {
        //Actualiza los campos de texto y variables dependiendo del operando a editar
        if (esSegundoOperando) {
            tOperando2.setText(String.valueOf(dActual));
            dOperando2 = dActual;
        } else {
            tOperando1.setText((String.valueOf(dActual)));
            dOperando1 = dActual;
        }
    }

    private void actualizarOperador() {
        //Actualiza el simbolo del operador que aparece a la izquierda
        lOperador.setText(String.valueOf(cOperador));
        //Indica al programa que debe editar el segundo operador
        esSegundoOperando = true;
        //Limpio las variables que representan el numero a modificar actualmente
        dActual = 0.0;
        sActual = "";
        if (mostrandoResultado) { //Si se esta mostrando un resultado
            //Tomo el resultado como primer operando
            dOperando1 = dResultado;
            //Escribo sobre el segundo operando
            dOperando2 = dActual;
            //Limpio el resultado
            dResultado = 0.0;
            //Indico al programa que se dejo de mostrar un resultado
            mostrandoResultado = false;
            //Actualizo los campos de textos
            tResultado.setText(String.valueOf(dResultado));
            tOperando1.setText(String.valueOf(dOperando1));
            //Actualizo los operandos de modo que el programa continue con normalidad
            actualizarOperandos();
        }
    }
}
