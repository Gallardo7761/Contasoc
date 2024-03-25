/*
 * Created by JFormDesigner on Sun Feb 04 02:38:41 CET 2024
 */

package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.database.objects.Socios;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author jomaa
 */
@SuppressWarnings("all")
public class SocioPanel extends JPanel {
    private final Socios socios;
    public SocioPanel(Socios socios) {
        this.socios = socios;
        initComponents();
        fillData();
    }

    public Socios getSocio() {
        return socios;
    }

    private void fillData() {
        iconoLabel.setIcon(
		switch (socios.getTipo()) {
            case HORTELANO -> new ImageIcon(getClass().getResource("/images/icons/farmer.png"));
            case HORTELANO_INVERNADERO -> new ImageIcon(getClass().getResource("/images/icons/green_house.png"));
            case COLABORADOR -> new ImageIcon(getClass().getResource("/images/icons/join.png"));
            case LISTA_ESPERA -> new ImageIcon(getClass().getResource("/images/icons/list.png"));
            case SUBVENCION -> new ImageIcon(getClass().getResource("/images/icons/subvencion4.png"));
        });
        nombreLabel.setText(socios.getNombre());
        dniLabel.setText(socios.getDni());
        telefonoLabel.setText(String.valueOf(socios.getTelefono()));
        emailLabel.setText(socios.getEmail());
        socioLabel.setText(String.valueOf(socios.getNumeroSocio()));
        huertoLabel.setText(String.valueOf(socios.getNumeroHuerto()));
        estadoLabel.setText(socios.getEstado().name());
        estadoLabel.setBackground(socios.getEstado() == Estado.ACTIVO ? new Color(0x09f78c) : new Color(0xff8d8d));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        iconoEstadoPanel = new JPanel();
        iconoLabel = new JLabel();
        estadoLabel = new JLabel();
        datosImportantesPanel = new JPanel();
        nombreLabel = new JLabel();
        contactoPanel = new JPanel();
        dniLabel = new JLabel();
        telefonoLabel = new JLabel();
        emailLabel = new JLabel();
        socioLabel = new JLabel();
        huertoLabel = new JLabel();

        //======== this ========
        setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.controlShadow));
        setLayout(new MigLayout(
            "hidemode 3,gapx 0",
            // columns
            "[fill]" +
            "[grow,fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[grow,fill]"));

        //======== iconoEstadoPanel ========
        {
            iconoEstadoPanel.setOpaque(false);
            iconoEstadoPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3,gapy 0",
                // columns
                "[grow,fill]",
                // rows
                "[fill]" +
                "[grow,fill]"));

            //---- iconoLabel ----
            iconoLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/farmer.png")));
            iconoEstadoPanel.add(iconoLabel, "cell 0 0,alignx center,growx 0,width 44:44:44,height 44:44:44");

            //---- estadoLabel ----
            estadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            estadoLabel.setBackground(new Color(0x09f78c));
            estadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            estadoLabel.setBorder(new TitledBorder(""));
            estadoLabel.setText("ACTIVO");
            estadoLabel.setOpaque(true);
            iconoEstadoPanel.add(estadoLabel, "cell 0 1,align center center,grow 0 0,width 96:96:96,height 32:32:32");
        }
        add(iconoEstadoPanel, "cell 0 0,gapx null 12");

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
        add(datosImportantesPanel, "cell 1 0");

        //---- socioLabel ----
        socioLabel.setText("1");
        socioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        socioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        socioLabel.setBorder(new TitledBorder(null, "N\u00ba Socio", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)));
        add(socioLabel, "cell 2 0,width 96:96:96");

        //---- huertoLabel ----
        huertoLabel.setText("1");
        huertoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        huertoLabel.setBorder(new TitledBorder(null, "N\u00ba Huerto", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)));
        huertoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(huertoLabel, "cell 3 0,width 96:96:96");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JPanel iconoEstadoPanel;
    protected static JLabel iconoLabel;
    protected static JLabel estadoLabel;
    protected static JPanel datosImportantesPanel;
    protected static JLabel nombreLabel;
    protected static JPanel contactoPanel;
    protected static JLabel dniLabel;
    protected static JLabel telefonoLabel;
    protected static JLabel emailLabel;
    protected static JLabel socioLabel;
    protected static JLabel huertoLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
