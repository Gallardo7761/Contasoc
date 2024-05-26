/*
 * Created by JFormDesigner on Sat Feb 17 09:02:01 CET 2024
 */

package dev.gallardo.contasoc.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import dev.gallardo.contasoc.common.TipoPago;
import dev.gallardo.contasoc.database.objects.Ingresos;
import dev.gallardo.contasoc.util.Parsers;

import java.awt.*;

/**
 * @author jomaa
 */
@SuppressWarnings("all")
public class IngresoPanel extends JPanel {
    private final Ingresos ingresos;
    public IngresoPanel(Ingresos ingresos) {
        this.ingresos = ingresos;
        initComponents();
        fillData();
    }

    public Ingresos getIngreso() {
        return ingresos;
    }

    private void fillData() {
        iconoLabel.setIcon(switch (TipoPago.valueOf(ingresos.getTipo())) {
            case BANCO -> new ImageIcon(getClass().getResource("/images/icons/bank.png"));
            case CAJA -> new ImageIcon(getClass().getResource("/images/icons/coins.png"));
        });
        cantidadLabel.setText(ingresos.getCantidad().toString() + "€");
        socioPanel.setText(ingresos.getNumeroSocio().toString());
        conceptoLabel.setText(ingresos.getConcepto());
        fechaLabel.setText(Parsers.dashDateParser(ingresos.getFecha().toString()));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        iconoCantidadPanel = new JPanel();
        iconoLabel = new JLabel();
        cantidadLabel = new JLabel();
        socioPanel = new JLabel();
        conceptoLabel = new JLabel();
        fechaLabel = new JLabel();

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

        //======== iconoCantidadPanel ========
        {
            iconoCantidadPanel.setOpaque(false);
            iconoCantidadPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3,gapy 0",
                // columns
                "[grow,fill]",
                // rows
                "[fill]" +
                "[grow,fill]"));

            //---- iconoLabel ----
            iconoLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/bank.png")));
            iconoCantidadPanel.add(iconoLabel, "cell 0 0,alignx center,growx 0,width 44:44:44,height 44:44:44");

            //---- cantidadLabel ----
            cantidadLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cantidadLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            cantidadLabel.setText("35,00\u20ac");
            iconoCantidadPanel.add(cantidadLabel, "cell 0 1,aligny center,grow 100 0,height 32:32:32");
        }
        add(iconoCantidadPanel, "cell 0 0,width 106:106:106,gapx null 12");

        //---- socioPanel ----
        socioPanel.setText("X");
        socioPanel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        socioPanel.setBorder(new TitledBorder(null, "N\u00ba Socio", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)));
        socioPanel.setHorizontalTextPosition(SwingConstants.CENTER);
        socioPanel.setHorizontalAlignment(SwingConstants.CENTER);
        add(socioPanel, "cell 1 0,width 96:96:96");

        //---- conceptoLabel ----
        conceptoLabel.setText("SEMESTRE 1 2024 + INVERNADERO");
        conceptoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        conceptoLabel.setBorder(new TitledBorder(null, "Concepto", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)));
        conceptoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        conceptoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(conceptoLabel, "cell 2 0");

        //---- fechaLabel ----
        fechaLabel.setText("31 dec 2024");
        fechaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        fechaLabel.setBorder(new TitledBorder(null, "Fecha", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)));
        fechaLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        fechaLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(fechaLabel, "cell 3 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JPanel iconoCantidadPanel;
    protected static JLabel iconoLabel;
    protected static JLabel cantidadLabel;
    private JLabel socioPanel;
    private JLabel conceptoLabel;
    private JLabel fechaLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
