/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectohsv;

/**
 *
 * @author BICHIYAL
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class VentanaHSV extends JFrame {
    private BufferedImage imagenOriginal;
    private BufferedImage imagenCMYK;
    private BufferedImage imagenHSV;

    private File archivoOriginal;

    private final JLabel lblImagen;
    private final JLabel lblPorcentaje;
    private final JLabel lblEstado;
    private final JSlider sliderSaturacion;

    public VentanaHSV() {

        setTitle("Proyecto de Manipulación RGB - CMYK - HSV");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Área donde se muestra la imagen
        lblImagen = new JLabel(
                "Selecciona una imagen",
                SwingConstants.CENTER
        );

        lblImagen.setPreferredSize(
                new Dimension(750, 480)
        );

        lblImagen.setBorder(
                BorderFactory.createTitledBorder(
                        "Vista de imagen"
                )
        );

        // Control de saturación HSV
        sliderSaturacion = new JSlider(
                100,
                200,
                150
        );

        sliderSaturacion.setMajorTickSpacing(25);
        sliderSaturacion.setPaintTicks(true);
        sliderSaturacion.setPaintLabels(true);

        lblPorcentaje = new JLabel("150%");

        sliderSaturacion.addChangeListener(e -> {

            lblPorcentaje.setText(
                    sliderSaturacion.getValue() + "%"
            );
        });

        // Botones
        JButton btnCargar =
                new JButton("Cargar imagen");

        JButton btnProcesar =
                new JButton("Procesar CMYK + HSV");

        JButton btnMostrarCMYK =
                new JButton("Mostrar CMYK");

        JButton btnMostrarHSV =
                new JButton("Mostrar HSV");

        JButton btnRestablecer =
                new JButton("Restablecer");

        // Acciones
        btnCargar.addActionListener(
                e -> cargarImagen()
        );

        btnProcesar.addActionListener(
                e -> procesarResultados()
        );

        btnMostrarCMYK.addActionListener(
                e -> mostrarCMYK()
        );

        btnMostrarHSV.addActionListener(
                e -> mostrarHSV()
        );

        btnRestablecer.addActionListener(
                e -> restablecerImagen()
        );

        // Panel de botones
        JPanel panelBotones =
                new JPanel(new FlowLayout());

        panelBotones.add(btnCargar);
        panelBotones.add(btnProcesar);
        panelBotones.add(btnMostrarCMYK);
        panelBotones.add(btnMostrarHSV);
        panelBotones.add(btnRestablecer);

        // Panel de saturación
        JPanel panelSaturacion =
                new JPanel(new FlowLayout());

        panelSaturacion.add(
                new JLabel("Saturación HSV:")
        );

        panelSaturacion.add(
                sliderSaturacion
        );

        panelSaturacion.add(
                lblPorcentaje
        );

        // Estado
        lblEstado = new JLabel(
                "Listo. Carga una imagen para comenzar.",
                SwingConstants.CENTER
        );

        // Panel inferior
        JPanel panelInferior =
                new JPanel(new BorderLayout());

        panelInferior.add(
                panelSaturacion,
                BorderLayout.NORTH
        );

        panelInferior.add(
                panelBotones,
                BorderLayout.CENTER
        );

        panelInferior.add(
                lblEstado,
                BorderLayout.SOUTH
        );

        // Agregar componentes
        add(
                lblImagen,
                BorderLayout.CENTER
        );

        add(
                panelInferior,
                BorderLayout.SOUTH
        );
    }

    // ==========================================================
    // CARGAR IMAGEN
    // ==========================================================

    private void cargarImagen() {

        JFileChooser selector =
                new JFileChooser();

        int resultado =
                selector.showOpenDialog(this);

        if (resultado ==
                JFileChooser.APPROVE_OPTION) {

            try {

                archivoOriginal =
                        selector.getSelectedFile();

                imagenOriginal =
                        ImageIO.read(
                                archivoOriginal
                        );

                imagenCMYK = null;
                imagenHSV = null;

                mostrarImagen(
                        imagenOriginal
                );

                lblEstado.setText(
                        "Imagen original cargada."
                );

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo cargar la imagen."
                );
            }
        }
    }

    // ==========================================================
    // PROCESAR CMYK + HSV
    // ==========================================================

    private void procesarResultados() {

        if (imagenOriginal == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero debes cargar una imagen."
            );

            return;
        }

        // Crear resultado CMYK
        imagenCMYK =
                convertirASimulacionCMYK(
                        imagenOriginal
                );

        // Crear resultado HSV
        float factor =
                sliderSaturacion.getValue()
                / 100.0f;

        imagenHSV =
                modificarSaturacionHSV(
                        imagenOriginal,
                        factor
                );

        try {

            // Carpeta donde está la imagen original
            File carpeta =
                    archivoOriginal.getParentFile();

            // Guardar resultado CMYK
            File archivoCMYK =
                    new File(
                            carpeta,
                            "resultado_cmyk.jpg"
                    );

            ImageIO.write(
                    imagenCMYK,
                    "jpg",
                    archivoCMYK
            );

            // Guardar resultado HSV
            File archivoHSV =
                    new File(
                            carpeta,
                            "resultado_hsv.jpg"
                    );

            ImageIO.write(
                    imagenHSV,
                    "jpg",
                    archivoHSV
            );

            // Mostrar resultado HSV
            mostrarImagen(
                    imagenHSV
            );

            lblEstado.setText(
                    "Resultados generados: resultado_cmyk.jpg y resultado_hsv.jpg"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Procesamiento terminado.\n\n"
                    + "Se generaron:\n"
                    + "resultado_cmyk.jpg\n"
                    + "resultado_hsv.jpg\n\n"
                    + "Los archivos se guardaron en la misma carpeta "
                    + "de la imagen original."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudieron guardar los resultados."
            );
        }
    }

    // ==========================================================
    // FILTRO HSV
    // ==========================================================

    private BufferedImage modificarSaturacionHSV(
            BufferedImage original,
            float factor) {

        int ancho =
                original.getWidth();

        int alto =
                original.getHeight();

        BufferedImage resultado =
                new BufferedImage(
                        ancho,
                        alto,
                        BufferedImage.TYPE_INT_RGB
                );

        for (int y = 0; y < alto; y++) {

            for (int x = 0; x < ancho; x++) {

                int rgb =
                        original.getRGB(x, y);

                int r =
                        (rgb >> 16) & 0xFF;

                int g =
                        (rgb >> 8) & 0xFF;

                int b =
                        rgb & 0xFF;

                // RGB -> HSB/HSV
                float[] hsv =
                        java.awt.Color.RGBtoHSB(
                                r,
                                g,
                                b,
                                null
                        );

                // hsv[0] = Matiz
                // hsv[1] = Saturación
                // hsv[2] = Brillo

                hsv[1] =
                        hsv[1] * factor;

                // Limitar saturación
                if (hsv[1] > 1.0f) {

                    hsv[1] = 1.0f;
                }

                // HSB/HSV -> RGB
                int nuevoRGB =
                        java.awt.Color.HSBtoRGB(
                                hsv[0],
                                hsv[1],
                                hsv[2]
                        );

                resultado.setRGB(
                        x,
                        y,
                        nuevoRGB
                );
            }
        }

        return resultado;
    }

    // ==========================================================
    // SIMULACIÓN CMYK
    // ==========================================================

    private BufferedImage convertirASimulacionCMYK(
            BufferedImage original) {

        int ancho =
                original.getWidth();

        int alto =
                original.getHeight();

        BufferedImage resultado =
                new BufferedImage(
                        ancho,
                        alto,
                        BufferedImage.TYPE_INT_RGB
                );

        for (int y = 0; y < alto; y++) {

            for (int x = 0; x < ancho; x++) {

                int rgb =
                        original.getRGB(x, y);

                int r =
                        (rgb >> 16) & 0xFF;

                int g =
                        (rgb >> 8) & 0xFF;

                int b =
                        rgb & 0xFF;

                // Normalizar RGB
                double R =
                        r / 255.0;

                double G =
                        g / 255.0;

                double B =
                        b / 255.0;

                // Convertir RGB -> CMYK
                double K =
                        1.0 - Math.max(
                                R,
                                Math.max(G, B)
                        );

                double C;
                double M;
                double Y;

                if (K >= 0.999999) {

                    C = 0;
                    M = 0;
                    Y = 0;

                } else {

                    C =
                            (1.0 - R - K)
                            / (1.0 - K);

                    M =
                            (1.0 - G - K)
                            / (1.0 - K);

                    Y =
                            (1.0 - B - K)
                            / (1.0 - K);
                }

                // Cuantización para simular
                // una reproducción CMYK simplificada
                C = cuantizar(C);
                M = cuantizar(M);
                Y = cuantizar(Y);
                K = cuantizar(K);

                // CMYK -> RGB
                double nuevoR =
                        255.0
                        * (1.0 - C)
                        * (1.0 - K);

                double nuevoG =
                        255.0
                        * (1.0 - M)
                        * (1.0 - K);

                double nuevoB =
                        255.0
                        * (1.0 - Y)
                        * (1.0 - K);

                int Rfinal =
                        limitar(
                                (int) Math.round(nuevoR)
                        );

                int Gfinal =
                        limitar(
                                (int) Math.round(nuevoG)
                        );

                int Bfinal =
                        limitar(
                                (int) Math.round(nuevoB)
                        );

                int nuevoRGB =
                        (Rfinal << 16)
                        | (Gfinal << 8)
                        | Bfinal;

                resultado.setRGB(
                        x,
                        y,
                        nuevoRGB
                );
            }
        }

        return resultado;
    }

    // ==========================================================
    // CUANTIZACIÓN CMYK
    // ==========================================================

    private double cuantizar(
            double valor) {

        int niveles = 16;

        return Math.round(
                valor * (niveles - 1)
        ) / (double) (niveles - 1);
    }

    // ==========================================================
    // LIMITAR VALOR RGB
    // ==========================================================

    private int limitar(
            int valor) {

        if (valor < 0) {
            return 0;
        }

        if (valor > 255) {
            return 255;
        }

        return valor;
    }

    // ==========================================================
    // MOSTRAR CMYK
    // ==========================================================

    private void mostrarCMYK() {

        if (imagenCMYK == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero debes procesar la imagen."
            );

            return;
        }

        mostrarImagen(
                imagenCMYK
        );

        lblEstado.setText(
                "Mostrando resultado CMYK."
        );
    }

    // ==========================================================
    // MOSTRAR HSV
    // ==========================================================

    private void mostrarHSV() {

        if (imagenHSV == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero debes procesar la imagen."
            );

            return;
        }

        mostrarImagen(
                imagenHSV
        );

        lblEstado.setText(
                "Mostrando resultado HSV."
        );
    }

    // ==========================================================
    // MOSTRAR IMAGEN
    // ==========================================================

    private void mostrarImagen(
            BufferedImage imagen) {

        if (imagen == null) {
            return;
        }

        int anchoDisponible =
                lblImagen.getWidth();

        int altoDisponible =
                lblImagen.getHeight();

        if (anchoDisponible <= 0) {
            anchoDisponible = 750;
        }

        if (altoDisponible <= 0) {
            altoDisponible = 480;
        }

        Image imagenEscalada =
                imagen.getScaledInstance(
                        anchoDisponible - 20,
                        altoDisponible - 40,
                        Image.SCALE_SMOOTH
                );

        lblImagen.setText("");

        lblImagen.setIcon(
                new ImageIcon(
                        imagenEscalada
                )
        );
    }

    // ==========================================================
    // RESTABLECER
    // ==========================================================

    private void restablecerImagen() {

        if (imagenOriginal != null) {

            mostrarImagen(
                    imagenOriginal
            );

            lblEstado.setText(
                    "Mostrando imagen original."
            );
        }
    }
         
}
