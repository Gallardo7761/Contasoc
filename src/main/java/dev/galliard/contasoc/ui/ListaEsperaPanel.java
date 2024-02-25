/*
 * Created by JFormDesigner on Sun Feb 18 02:31:29 CET 2024
 */

package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.objects.Socios;
import dev.galliard.contasoc.util.Parsers;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author jomaa
 */
public class ListaEsperaPanel extends JPanel {
    private final Socios socios;
    private final int posicion;
    public ListaEsperaPanel(Socios socios, int posicion) {
        this.socios = socios;
        this.posicion = posicion;
        initComponents();
        fillData();
    }

    public Socios getSocio() {
        return socios;
    }

    private void fillData() {
        posicionLabel.setText(String.valueOf(posicion));
        socioLabel.setText(String.valueOf(socios.getNumeroSocio()));
        nombreLabel.setText(socios.getNombre());
        dniLabel.setText(socios.getDni());
        telefonoLabel.setText(String.valueOf(socios.getTelefono()));
        emailLabel.setText(socios.getEmail());
        fechaDeAltaLabel.setText(Parsers.dashDateParser(socios.getFechaDeAlta().toString()));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        posicionLabel = new JLabel();
        socioLabel = new JLabel();
        datosImportantesPanel = new JPanel();
        nombreLabel = new JLabel();
        contactoPanel = new JPanel();
        dniLabel = new JLabel();
        telefonoLabel = new JLabel();
        emailLabel = new JLabel();
        fechaDeAltaLabel = new JLabel();

        //======== this ========
        setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.controlShadow));
        setLayout(new MigLayout(
            "hidemode 3,gapx 0",
            // columns
            "[fill]" +
            "[fill]" +
            "[grow,fill]" +
            "[fill]",
            // rows
            "[grow,fill]"));

        //---- posicionLabel ----
        posicionLabel.setText("1");
        posicionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        posicionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        posicionLabel.setBorder(new TitledBorder(null, "Posici\u00f3n", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)));
        add(posicionLabel, "cell 0 0,width 96:96:96");

        //---- socioLabel ----
        socioLabel.setText("1");
        socioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        socioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        socioLabel.setBorder(new TitledBorder(null, "N\u00ba Socio", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)));
        add(socioLabel, "cell 1 0,width 96:96:96");

        //======== datosImportantesPanel ========
        {
            datosImportantesPanel.setOpaque(false);
            datosImportantesPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3,gapy 0",
                // columns
                "[grow,fill]",
                // rows
                "[fill]" +
                "[grow,fill]"));

            //---- nombreLabel ----
            nombreLabel.setText("NOMBRE APELLIDO APELLIDO");
            nombreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
            datosImportantesPanel.add(nombreLabel, "cell 0 0");

            //======== contactoPanel ========
            {
                contactoPanel.setOpaque(false);
                contactoPanel.setLayout(new MigLayout(
                    "insets 0,hidemode 3,gapx 0",
                    // columns
                    "[grow,fill]" +
                    "[grow,fill]" +
                    "[grow,fill]",
                    // rows
                    "[grow,fill]"));

                //---- dniLabel ----
                dniLabel.setText("00000000T");
                dniLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                dniLabel.setBorder(new TitledBorder(null, "NIF/NIE", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                    new Font("Segoe UI", Font.PLAIN, 14)));
                contactoPanel.add(dniLabel, "cell 0 0");

                //---- telefonoLabel ----
                telefonoLabel.setText("600500400");
                telefonoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                telefonoLabel.setBorder(new TitledBorder(null, "Tel\u00e9fono", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                    new Font("Segoe UI", Font.PLAIN, 14)));
                contactoPanel.add(telefonoLabel, "cell 1 0");

                //---- emailLabel ----
                emailLabel.setText("email@email.com");
                emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                emailLabel.setBorder(new TitledBorder(null, "Email", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                    new Font("Segoe UI", Font.PLAIN, 14)));
                contactoPanel.add(emailLabel, "cell 2 0");
            }
            datosImportantesPanel.add(contactoPanel, "cell 0 1");
        }
        add(datosImportantesPanel, "cell 2 0");

        //---- fechaDeAltaLabel ----
        fechaDeAltaLabel.setText("31 dec 2024");
        fechaDeAltaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        fechaDeAltaLabel.setBorder(new TitledBorder(null, "Fecha", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)));
        fechaDeAltaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(fechaDeAltaLabel, "cell 3 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JLabel posicionLabel;
    protected static JLabel socioLabel;
    protected static JPanel datosImportantesPanel;
    protected static JLabel nombreLabel;
    protected static JPanel contactoPanel;
    protected static JLabel dniLabel;
    protected static JLabel telefonoLabel;
    protected static JLabel emailLabel;
    protected static JLabel fechaDeAltaLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
