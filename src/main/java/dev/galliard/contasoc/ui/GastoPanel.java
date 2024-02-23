/*
 * Created by JFormDesigner on Sun Feb 18 00:47:02 CET 2024
 */

package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.util.Parsers;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author jomaa
 */
public class GastoPanel extends JPanel {
    private Gastos gastos;
    public GastoPanel(Gastos gastos) {
        this.gastos = gastos;
        initComponents();
        fillData();
    }

    public Gastos getGasto() {
        return gastos;
    }

    private void fillData() {
        iconoLabel.setIcon(switch (TipoPago.valueOf(gastos.getTipo())) {
            case BANCO -> new ImageIcon(getClass().getResource("/images/icons/bank.png"));
            case CAJA -> new ImageIcon(getClass().getResource("/images/icons/coins.png"));
        });
        cantidadLabel.setText(gastos.getCantidad().toString() + "€");
        conceptoLabel.setText(gastos.getConcepto());
        proveedorLabel.setText(gastos.getProveedor());
        facturaLabel.setText(gastos.getFactura());
        fechaLabel.setText(Parsers.dashDateParser(gastos.getFecha().toString()));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        iconoEstadoPanel = new JPanel();
        iconoLabel = new JLabel();
        cantidadLabel = new JLabel();
        provFacConPanel = new JPanel();
        conceptoLabel = new JLabel();
        proveedorLabel = new JLabel();
        facturaLabel = new JLabel();
        fechaLabel = new JLabel();

        //======== this ========
        setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.controlShadow));
        setLayout(new MigLayout(
            "hidemode 3,gapx 0",
            // columns
            "[fill]" +
            "[grow,fill]" +
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
            iconoLabel.setIcon(new ImageIcon(getClass().getResource("/images/icons/bank.png")));
            iconoEstadoPanel.add(iconoLabel, "cell 0 0,alignx center,growx 0,width 44:44:44,height 44:44:44");

            //---- cantidadLabel ----
            cantidadLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cantidadLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            cantidadLabel.setText("35,00\u20ac");
            iconoEstadoPanel.add(cantidadLabel, "cell 0 1,aligny center,grow 100 0,height 32:32:32");
        }
        add(iconoEstadoPanel, "cell 0 0,width 106:106:106,gapx null 12");

        //======== provFacConPanel ========
        {
            provFacConPanel.setOpaque(false);
            provFacConPanel.setLayout(new MigLayout(
                "insets 0,hidemode 3,gap 0 0",
                // columns
                "[grow,fill]",
                // rows
                "[grow,fill]"));

            //---- conceptoLabel ----
            conceptoLabel.setText("SEMESTRE 1 2024 + INVERNADERO");
            conceptoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
            conceptoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            conceptoLabel.setHorizontalAlignment(SwingConstants.LEFT);
            provFacConPanel.add(conceptoLabel, "cell 0 0");

            //---- proveedorLabel ----
            proveedorLabel.setText("EMASESA");
            proveedorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            proveedorLabel.setBorder(new TitledBorder(null, "Proveedor", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.PLAIN, 14)));
            provFacConPanel.add(proveedorLabel, "cell 0 1");

            //---- facturaLabel ----
            facturaLabel.setText("text");
            facturaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            facturaLabel.setBorder(new TitledBorder(null, "Factura", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.PLAIN, 14)));
            provFacConPanel.add(facturaLabel, "cell 0 1");
        }
        add(provFacConPanel, "cell 1 0");

        //---- fechaLabel ----
        fechaLabel.setText("31 dec 2024");
        fechaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        fechaLabel.setBorder(new TitledBorder(null, "Fecha", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)));
        fechaLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        fechaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(fechaLabel, "cell 2 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JPanel iconoEstadoPanel;
    protected static JLabel iconoLabel;
    protected static JLabel cantidadLabel;
    private JPanel provFacConPanel;
    private JLabel conceptoLabel;
    private JLabel proveedorLabel;
    private JLabel facturaLabel;
    private JLabel fechaLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
