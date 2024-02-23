/*
 * Created by JFormDesigner on Mon Oct 30 02:46:37 CET 2023
 */

package dev.galliard.contasoc.ui;

import javax.swing.event.*;
import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.Action;
import dev.galliard.contasoc.common.PrintAction;
import dev.galliard.contasoc.database.DBUtils;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;
import dev.galliard.contasoc.ui.models.GastoPanelRenderer;
import dev.galliard.contasoc.ui.models.IngresoPanelRenderer;
import dev.galliard.contasoc.ui.models.ListaEsperaPanelRenderer;
import dev.galliard.contasoc.ui.models.SocioPanelRenderer;
import dev.galliard.contasoc.util.Parsers;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @author jomaa
 */
@SuppressWarnings("ALL")
public class UIContasoc extends JFrame {
    private String mail = "";
    public UIContasoc() {
        initComponents();
        setActions();
        GUIManager.populateGUITables();
        GUIManager.addListenerToSearchBar(sociosLista, (DefaultListModel<SocioPanel>) sociosLista.getModel());
    }

    private void printBtnActionPerformed(ActionEvent e) {
        Component selected = UIContasoc.tabbedPane1.getSelectedComponent();
        if (selected.equals(sociosPanel)) {
            if (cardSociosPanel.isVisible()) {
                GUIManager.valor = PrintAction.SOCIOS;
            } else if (cardListaEsperaPanel.isVisible()) {
                GUIManager.valor = PrintAction.LISTA_ESPERA;
            }
        } else if (selected.equals(ingresosPanel)) {
            GUIManager.valor = PrintAction.INGRESOS;
        } else if (selected.equals(gastosPanel)) {
            GUIManager.valor = PrintAction.GASTOS;
        }
        GUIManager.printContent();
    }

    private void toListaEsperaBtnActionPerformed(ActionEvent e) {
        ((CardLayout) sociosPanel.getLayout()).show(sociosPanel, "cardListaEspera");
    }

    private void toSociosBtnActionPerformed(ActionEvent e) {
        ((CardLayout) sociosPanel.getLayout()).show(sociosPanel, "cardSocios");
    }

    private void balancePanelComponentShown(ComponentEvent e) {
        if (DBUtils.isEmpty("Balance")) {
            SaldoInicial saldoInicial = SaldoInicial.getInstance();
            saldoInicial.setVisible(true);
            saldoInicial.requestFocus();
        }
        BalanceCalculatorThread balanceCalc = new BalanceCalculatorThread();
        Thread thread = new Thread(balanceCalc);
        thread.start();
    }

    private void nuevoBtnActionPerformed(ActionEvent e) {
        if (sociosPanel.isVisible() && !cardListaEsperaPanel.isVisible()) {
            AddSocios addSocios = AddSocios.getInstance();
            addSocios.clear();
            addSocios.setTitle("Añadir nuevo socio");
            addSocios.setVisible(true);
        } else if (ingresosPanel.isVisible()) {
            AddModifyIngresos.accion = Action.ADD;
            AddModifyIngresos addModifyIngresos = AddModifyIngresos.getInstance();
            addModifyIngresos.clear();
            addModifyIngresos.setTitle("Añadir nuevo ingreso");
            addModifyIngresos.setVisible(true);
        } else if (gastosPanel.isVisible()) {
            AddModifyGastos.accion = Action.ADD;
            AddModifyGastos addModifyGastos = AddModifyGastos.getInstance();
            addModifyGastos.clear();
            addModifyGastos.setTitle("Añadir nuevo gasto");
            addModifyGastos.setVisible(true);
        }
    }

    private void eliminarBtnActionPerformed(ActionEvent e) {
        int sel = 1;
        if (!cardListaEsperaPanel.isVisible()) {
            String[] options = {"Sí", "No"};
            sel = JOptionPane.showOptionDialog(null, "¿Seguro que quieres eliminar el dato?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (sociosPanel.isVisible()) {
                int selected = UIContasoc.sociosLista.getSelectedIndex();
                Socios socios = selected >= 0 ? GUIManager.getSocios().get(selected) : null;
                DefaultListModel<SocioPanel> model = (DefaultListModel<SocioPanel>) UIContasoc.sociosLista.getModel();
                switch (sel) {
                    case JOptionPane.YES_OPTION:
                        model.removeElementAt(selected);
                        Contasoc.sqlMemory.socioEliminado(socios);
                        Contasoc.sqlMemory.socioAgregado(socios);
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                }
            } else if (ingresosPanel.isVisible()) {
                int selected = UIContasoc.ingresosLista.getSelectedIndex();
                Ingresos ingresos = selected >= 0 ? GUIManager.getIngresos().get(selected) : null;
                DefaultListModel<IngresoPanel> model = (DefaultListModel<IngresoPanel>) UIContasoc.ingresosLista.getModel();
                switch (sel) {
                    case JOptionPane.YES_OPTION:
                        model.removeElementAt(selected);
                        Contasoc.sqlMemory.ingresoEliminado(ingresos);
                        Contasoc.sqlMemory.ingresoAgregado(ingresos);
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                }
            } else if (gastosPanel.isVisible()) {
                int selected = UIContasoc.gastosLista.getSelectedIndex();
                Gastos gastos = selected >= 0 ? GUIManager.getGastos().get(selected) : null;
                DefaultListModel<GastoPanel> model = (DefaultListModel<GastoPanel>) UIContasoc.gastosLista.getModel();
                switch (sel) {
                    case JOptionPane.YES_OPTION:
                        model.removeElementAt(selected);
                        Contasoc.sqlMemory.gastoEliminado(gastos);
                        Contasoc.sqlMemory.gastoAgregado(gastos);
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                }
            }
        }
    }

    private void enviarBtnActionPerformed(ActionEvent e) {
        /*
        * switch (UIContasoc.tipoEmailComboBox.getSelectedItem().toString()) {
            case "NORMAL":
                String destinatario = UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim();
                String asunto = UIContasoc.asuntoField.getText();
                String cuerpo = "";
                cuerpo = EmailSender2.NORMAL_EMAIL
                        .replace("{nombre}", GUIManager.getSocios().stream().filter(socio -> socio.getEmail().equals(destinatario)).findFirst().get().getNombre())
                        .replace("{mensaje}", htmlEditor.getText());
                if (adjuntoCheckBox.isSelected()) {
                    new EmailSender2().sendEmail(destinatario, asunto, cuerpo, EmailSender2.adjunto);
                } else {
                    new EmailSender2().sendEmail(destinatario, asunto, cuerpo);
                }
                asuntoField.setText("");
                htmlEditor.setText("");
                break;
            case "AVISO IMPAGO":
                cuerpo = "";
                destinatario = UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim();
                cuerpo = EmailSender2.NORMAL_EMAIL
                        .replace("{nombre}", GUIManager.getSocios().stream().filter(socio -> socio.getEmail().equals(destinatario)).findFirst().get().getNombre());
                asunto = "AVISO IMPAGO";
                UIContasoc.asuntoField.setText(asunto);
                if (adjuntoCheckBox.isSelected()) {
                    new EmailSender2().sendEmail(destinatario, asunto, cuerpo, EmailSender2.adjunto);
                    asuntoField.setText("");
                } else {
                    ErrorHandler.error("Este tipo de email requiere adjunto");
                }
                break;
            case "AVISO ABANDONO":
                cuerpo = "";
                destinatario = UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim();
                asunto = "AVISO ABANDONO";
                UIContasoc.asuntoField.setText(asunto);
                cuerpo = EmailSender2.NORMAL_EMAIL
                        .replace("{nombre}", GUIManager.getSocios().stream().filter(socio -> socio.getEmail().equals(destinatario)).findFirst().get().getNombre());
                if (adjuntoCheckBox.isSelected()) {
                    new EmailSender2().sendEmail(destinatario, asunto, cuerpo, EmailSender2.adjunto);
                    asuntoField.setText("");
                } else {
                    ErrorHandler.error("Este tipo de email requiere adjunto");
                }
                break;
            case "MAL COMPORTAMIENTO":
                cuerpo = "";
                destinatario = UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim();
                asunto = "AVISO MAL COMPORTAMIENTO";
                UIContasoc.asuntoField.setText(asunto);
                cuerpo = EmailSender2.NORMAL_EMAIL
                        .replace("{nombre}", GUIManager.getSocios().stream().filter(socio -> socio.getEmail().equals(destinatario)).findFirst().get().getNombre());
                if (adjuntoCheckBox.isSelected()) {
                    new EmailSender2().sendEmail(destinatario, asunto, cuerpo, EmailSender2.adjunto);
                    asuntoField.setText("");
                } else {
                    ErrorHandler.error("Este tipo de email requiere adjunto");
                }

                break;
        }
        * */
    }

    protected void setActions() {
        // Crear acciones para cada función
        javax.swing.Action nuevoAction = new AbstractAction("Nuevo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoBtnActionPerformed(e);
            }
        };

        javax.swing.Action editarAction = new AbstractAction("Editar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                verBtn(e);
            }
        };

        javax.swing.Action eliminarAction = new AbstractAction("Eliminar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarBtnActionPerformed(e);
            }
        };

        javax.swing.Action imprimirAction = new AbstractAction("Imprimir") {
            @Override
            public void actionPerformed(ActionEvent e) {
                printBtnActionPerformed(e);
            }
        };

        javax.swing.Action ayudaAction = new AbstractAction("Ayuda") {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpBtnActionPerformed(e);
            }
        };

        javax.swing.Action derechaAction = new AbstractAction("Derecha") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sociosPanel.isVisible() && !cardListaEsperaPanel.isVisible()) {
                    toListaEsperaBtnActionPerformed(e);
                }
            }
        };

        javax.swing.Action izquierdaAction = new AbstractAction("Izquierda") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sociosPanel.isVisible() && cardListaEsperaPanel.isVisible()) {
                    toSociosBtnActionPerformed(e);
                }
            }
        };

        javax.swing.Action sociosAltAction = new AbstractAction("Socios") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        };

        javax.swing.Action ingresosAltAction = new AbstractAction("Ingresos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(1);
            }
        };

        javax.swing.Action gastosAltAction = new AbstractAction("Gastos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(2);
            }
        };

        javax.swing.Action balanceAltAction = new AbstractAction("Balance") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(3);
            }
        };

        javax.swing.Action emailAltAction = new AbstractAction("Email") {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(4);
            }
        };

        // Obtener el panel de contenido
        JPanel contentPane = (JPanel) this.getContentPane();
        // Configurar atajos de teclado
        KeyStroke nuevoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
        KeyStroke editarKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
        KeyStroke eliminarKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK);
        KeyStroke imprimirKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK);
        KeyStroke ayudaKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        KeyStroke derechaKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
        KeyStroke izquierdaKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        KeyStroke sociosAltKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK);
        KeyStroke ingresosAltKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK);
        KeyStroke gastosAltKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_DOWN_MASK);
        KeyStroke balanceAltKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_DOWN_MASK);
        KeyStroke emailAltKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK);

        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(nuevoKeyStroke, "nuevo");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(editarKeyStroke, "editar");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(eliminarKeyStroke, "eliminar");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(imprimirKeyStroke, "imprimir");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ayudaKeyStroke, "ayuda");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(derechaKeyStroke, "derecha");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(izquierdaKeyStroke, "izquierda");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(sociosAltKeyStroke, "socios");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ingresosAltKeyStroke, "ingresos");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(gastosAltKeyStroke, "gastos");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(balanceAltKeyStroke, "balance");
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(emailAltKeyStroke, "email");

        contentPane.getActionMap().put("nuevo", nuevoAction);
        contentPane.getActionMap().put("editar", editarAction);
        contentPane.getActionMap().put("eliminar", eliminarAction);
        contentPane.getActionMap().put("imprimir", imprimirAction);
        contentPane.getActionMap().put("ayuda", ayudaAction);
        contentPane.getActionMap().put("derecha", derechaAction);
        contentPane.getActionMap().put("izquierda", izquierdaAction);
        contentPane.getActionMap().put("socios", sociosAltAction);
        contentPane.getActionMap().put("ingresos", ingresosAltAction);
        contentPane.getActionMap().put("gastos", gastosAltAction);
        contentPane.getActionMap().put("balance", balanceAltAction);
        contentPane.getActionMap().put("email", emailAltAction);
    }

    private void helpBtnActionPerformed(ActionEvent e) {
        HelpMenu helpMenu = HelpMenu.getInstance();
        helpMenu.setVisible(true);
        helpMenu.setLocationRelativeTo(null);
    }

    private void thisWindowClosing(WindowEvent e) {
        save();
        System.exit(0);
    }

    private void save() {
        if (Contasoc.sqlMemory.dataModified()) {
            List<Socios> sociosEditados = Contasoc.sqlMemory.getSociosEditados();
            List<Ingresos> ingresosEditados = Contasoc.sqlMemory.getIngresosEditados();
            List<Gastos> gastosEditados = Contasoc.sqlMemory.getGastosEditados();
            List<Socios> sociosAgregados = Contasoc.sqlMemory.getSociosAgregados();
            List<Ingresos> ingresosAgregados = Contasoc.sqlMemory.getIngresosAgregados();
            List<Gastos> gastosAgregados = Contasoc.sqlMemory.getGastosAgregados();
            List<Socios> sociosEliminados = Contasoc.sqlMemory.getSociosEliminados();
            List<Ingresos> ingresosEliminados = Contasoc.sqlMemory.getIngresosEliminados();
            List<Gastos> gastosEliminados = Contasoc.sqlMemory.getGastosEliminados();

            JOptionPane optionPane = new JOptionPane("Hay cambios sin guardar, ¿quieres guardarlos?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
            optionPane.setOptions(new Object[]{"Sí", "No"});
            int result2 = optionPane.showConfirmDialog(this, "Hay cambios sin guardar, ¿quieres guardarlos?", "Guardar cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result2 == JOptionPane.YES_OPTION) {
                if(sociosEditados.size() > 0) {
                    for (Socios socio : sociosEditados) {
                        DBUtils.updateSocioOnClose(
                                SocioView.tempNumeroSocio,
                                new String[]{
                                        socio.getNumeroSocio().toString(),
                                        socio.getNumeroHuerto().toString(),
                                        socio.getNombre(),
                                        socio.getDni(),
                                        socio.getTelefono() == null ? "NULL" : socio.getTelefono().toString(),
                                        socio.getEmail() == "" ? "NULL" : socio.getEmail(),
                                        socio.getFechaDeAlta() == null ? "NULL" : socio.getFechaDeAlta().toString(),
                                        socio.getFechaDeEntrega() == null ? "NULL" : socio.getFechaDeEntrega().toString(),
                                        socio.getFechaDeBaja() == null ? "NULL" : socio.getFechaDeBaja().toString(),
                                        socio.getNotas(),
                                        socio.getTipo().name() == null ? "HORTELANO" : socio.getTipo().name()
                                });
                    }
                }
                if(ingresosEditados.size() > 0) {
                    for (Ingresos ingreso : ingresosEditados) {
                        DBUtils.updateIngresoOnClose(
                                Integer.parseInt(AddModifyIngresos.tempSocio),
                                AddModifyIngresos.tempConcepto,
                                new String[]{
                                        ingreso.getNumeroSocio().toString(),
                                        ingreso.getFecha().toString(),
                                        ingreso.getConcepto(),
                                        ingreso.getCantidad().toString(),
                                        ingreso.getTipo()
                                });
                    }
                }
                if(gastosEditados.size() > 0) {
                    for (Gastos gasto : gastosEditados) {
                        DBUtils.updateGastoOnClose(
                                AddModifyGastos.tempFecha,
                                AddModifyGastos.tempProveedor,
                                AddModifyGastos.tempFactura,
                                new String[]{
                                        gasto.getFecha().toString(),
                                        gasto.getProveedor(),
                                        gasto.getConcepto(),
                                        gasto.getCantidad().toString(),
                                        gasto.getFactura(),
                                        gasto.getTipo()
                                });
                    }
                }
                if(gastosAgregados.size() > 0) {
                    for (Gastos gasto : gastosAgregados) {
                        Contasoc.jpaGastoDao.save(gasto);
                    }
                }
                if(gastosEliminados.size() > 0) {
                    for (Gastos gasto : gastosEliminados) {
                        DBUtils.removeGastoOnClose(gasto.getId());
                    }
                }
                if(ingresosAgregados.size() > 0) {
                    for (Ingresos ingreso : ingresosAgregados) {
                        Contasoc.jpaIngresoDao.save(ingreso);
                    }
                }
                if(ingresosEliminados.size() > 0) {
                    for (Ingresos ingreso : ingresosEliminados) {
                        DBUtils.removeIngresoOnClose(ingreso.getId());
                    }
                }
                if(sociosAgregados.size() > 0) {
                    for (Socios socio : sociosAgregados) {
                        Contasoc.jpaSocioDao.save(socio);
                    }
                }
                if(sociosEliminados.size() > 0) {
                    for (Socios socio : sociosEliminados) {
                        DBUtils.removeSocioOnClose(socio.getId());
                    }
                }
            }
        }
    }

    private void cerrarAnyoBtn(ActionEvent e) {
        DBUtils.cerrarAnyo();
        SaldoInicial saldoInicial = SaldoInicial.getInstance();
        saldoInicial.setVisible(true);
        saldoInicial.requestFocus();
    }

    private void verBtn(ActionEvent e) {

    }

    private void ayudaBtn(ActionEvent e) {
        HelpMenu helpMenu = HelpMenu.getInstance();
        helpMenu.setVisible(true);
        helpMenu.setLocationRelativeTo(null);
    }

    private void editarBtn(ActionEvent e) {
        if (sociosPanel.isVisible() && !cardListaEsperaPanel.isVisible()) {
            SocioView view = SocioView.getInstance();
            Socios socio = UIContasoc.sociosLista.getSelectedValue().getSocio();

            SocioView.tempNumeroSocio = socio.getNumeroSocio();

            view.socioField.setText(socio.getNumeroSocio().toString());
            view.nombreField.setText(socio.getNombre());
            view.dniField.setText(socio.getDni());
            view.telefonoField.setText(socio.getTelefono().toString());
            view.emailField.setText(socio.getEmail());
            view.huertoField.setText(socio.getNumeroHuerto().toString());
            view.altaField.setText(
                    socio.getFechaDeAlta() != null ? Parsers.dashDateParser(socio.getFechaDeAlta().toString()) : ""
            );
            view.entregaField.setText(
                    socio.getFechaDeEntrega() != null ? Parsers.dashDateParser(socio.getFechaDeEntrega().toString()) : ""
            );
            view.bajaField.setText(
                    socio.getFechaDeBaja() != null ? Parsers.dashDateParser(socio.getFechaDeBaja().toString()) : ""
            );
            view.notasField.setText(socio.getNotas());
            view.tipoSocioComboBox.setSelectedItem(socio.getTipo().name());

            List<Ingresos> ingresos = GUIManager.getIngresos().stream()
                    .filter(ingreso -> ingreso.getNumeroSocio().equals(socio.getNumeroSocio()))
                    .toList();
            DefaultListModel<IngresoPanel> model = new DefaultListModel<>();
            view.ingresosLista.setModel(model);
            ingresos.forEach(ingreso -> model.addElement(new IngresoPanel(ingreso)));

            view.setVisible(true);
            view.requestFocus();
        } else if (ingresosPanel.isVisible()) {
            AddModifyIngresos.accion = Action.MODIFY;
            AddModifyIngresos addModifyIngresos = AddModifyIngresos.getInstance();
            Ingresos ingreso = UIContasoc.ingresosLista.getSelectedValue().getIngreso();

            AddModifyIngresos.tempSocio = ingreso.getNumeroSocio().toString();
            AddModifyIngresos.tempConcepto = ingreso.getConcepto();

            addModifyIngresos.socioField.setText(ingreso.getNumeroSocio().toString());
            addModifyIngresos.fechaField.setText(Parsers.dashDateParser(ingreso.getFecha().toString()));
            addModifyIngresos.conceptoField.setText(ingreso.getConcepto());
            addModifyIngresos.cantidadField.setText(ingreso.getCantidad().toString());
            addModifyIngresos.tipoPagoComboBox.setSelectedItem(ingreso.getTipo());
            addModifyIngresos.setVisible(true);
        } else if (gastosPanel.isVisible()) {
            AddModifyGastos.accion = Action.MODIFY;
            AddModifyGastos addModifyGastos = AddModifyGastos.getInstance();
            Gastos gasto = UIContasoc.gastosLista.getSelectedValue().getGasto();

            AddModifyGastos.tempFecha = gasto.getFecha().toString();
            AddModifyGastos.tempProveedor = gasto.getProveedor();
            AddModifyGastos.tempFactura = gasto.getFactura();

            addModifyGastos.fechaField.setText(Parsers.dashDateParser(gasto.getFecha().toString()));
            addModifyGastos.proveedorField.setText(gasto.getProveedor());
            addModifyGastos.conceptoField.setText(gasto.getConcepto());
            addModifyGastos.cantidadField.setText(gasto.getCantidad().toString());
            addModifyGastos.facturaField.setText(gasto.getFactura());
            addModifyGastos.tipoPagoComboBox.setSelectedItem(gasto.getTipo());
            addModifyGastos.setVisible(true);
        }
    }

    private void tabbedPane1StateChanged(ChangeEvent e) {
        if(tabbedPane1.getSelectedComponent().equals(sociosPanel)) {
            aux.setVisible(true);
            buscarField.setVisible(true);
        } else {
            aux.setVisible(false);
            buscarField.setVisible(false);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        btnSearchPanel = new JPanel();
        nuevoBtn = new JButton();
        editarBtn = new JButton();
        eliminarBtn = new JButton();
        printBtn = new JButton();
        aux = new JPanel();
        buscarField = new JTextField();
        ayudaBtn = new JButton();
        tabbedPane1 = new JTabbedPane();
        sociosPanel = new JPanel();
        cardSociosPanel = new JPanel();
        sociosListaPanel = new JScrollPane();
        sociosLista = new JList<>();
        toListaEsperaBtn = new JButton();
        cardListaEsperaPanel = new JPanel();
        toSociosBtn = new JButton();
        listaEsperaListaPanel = new JScrollPane();
        listaEsperaLista = new JList<>();
        ingresosPanel = new JPanel();
        ingresosListaPanel = new JScrollPane();
        ingresosLista = new JList<>();
        gastosPanel = new JPanel();
        gastosListaPanel = new JScrollPane();
        gastosLista = new JList<>();
        balancePanel = new JPanel();
        balanceCantidadesPanel = new JPanel();
        tBancoIngresos = new JPanel();
        tBancoIngresosLabel = new JLabel();
        tBancoIngresosValue = new JLabel();
        tCajaIngresos = new JPanel();
        tCajaIngresosLabel = new JLabel();
        tCajaIngresosValue = new JLabel();
        tBancoGastos = new JPanel();
        tBancoGastosLabel = new JLabel();
        tBancoGastosValue = new JLabel();
        tCajaGastos = new JPanel();
        tCajaGastosLabel = new JLabel();
        tCajaGastosValue = new JLabel();
        saldoBanco = new JPanel();
        saldoBancoLabel = new JLabel();
        saldoBancoValue = new JLabel();
        saldoCaja = new JPanel();
        saldoCajaLabel = new JLabel();
        saldoCajaValue = new JLabel();
        cerrarAnyoPanel = new JPanel();
        cerrarAnyoBtn = new JButton();

        //======== this ========
        setFont(new Font("Segoe UI", Font.PLAIN, 12));
        setIconImage(new ImageIcon(getClass().getResource("/images/contasoc_small.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 617));
        setMinimumSize(new Dimension(1000, 617));
        setTitle("Huertos la Salud Bellavista");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(6, 0));

        //======== btnSearchPanel ========
        {
            btnSearchPanel.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.controlShadow));
            btnSearchPanel.setLayout(new MigLayout(
                "insets panel,hidemode 1,gap 12 0",
                // columns
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[grow,fill]" +
                "[fill]",
                // rows
                "[fill]"));

            //---- nuevoBtn ----
            nuevoBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            nuevoBtn.setMargin(new Insets(0, 0, 0, 0));
            nuevoBtn.setForeground(Color.black);
            nuevoBtn.setAlignmentY(0.0F);
            nuevoBtn.setMaximumSize(new Dimension(79, 32));
            nuevoBtn.setMinimumSize(new Dimension(79, 32));
            nuevoBtn.setPreferredSize(new Dimension(79, 32));
            nuevoBtn.setIcon(new ImageIcon(getClass().getResource("/images/icons/add2_32.png")));
            nuevoBtn.setToolTipText("Nuevo [Ctrl+N]");
            nuevoBtn.setBackground(new Color(0xf7f8fa));
            nuevoBtn.setBorderPainted(false);
            nuevoBtn.addActionListener(e -> nuevoBtnActionPerformed(e));
            nuevoBtn.putClientProperty( "JButton.buttonType", "borderless");
            btnSearchPanel.add(nuevoBtn, "cell 0 0,width 40:40:40,height 40:40:40");

            //---- editarBtn ----
            editarBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            editarBtn.setForeground(Color.black);
            editarBtn.setIcon(new ImageIcon(getClass().getResource("/images/icons/corel2_32.png")));
            editarBtn.setMargin(new Insets(0, 0, 0, 0));
            editarBtn.setToolTipText("Editar [Ctrl+E]");
            editarBtn.setBackground(new Color(0xf7f8fa));
            editarBtn.setBorderPainted(false);
            editarBtn.addActionListener(e -> editarBtn(e));
            editarBtn.putClientProperty( "JButton.buttonType", "borderless");
            btnSearchPanel.add(editarBtn, "cell 1 0,width 40:40:40,height 40:40:40");

            //---- eliminarBtn ----
            eliminarBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            eliminarBtn.setMargin(new Insets(0, 0, 0, 0));
            eliminarBtn.setForeground(Color.black);
            eliminarBtn.setAlignmentY(0.0F);
            eliminarBtn.setMaximumSize(new Dimension(79, 32));
            eliminarBtn.setMinimumSize(new Dimension(79, 32));
            eliminarBtn.setPreferredSize(new Dimension(79, 32));
            eliminarBtn.setIcon(new ImageIcon(getClass().getResource("/images/icons/delete2_32.png")));
            eliminarBtn.setToolTipText("Eliminar [Ctrl+D]");
            eliminarBtn.setBackground(new Color(0xf7f8fa));
            eliminarBtn.setBorderPainted(false);
            eliminarBtn.addActionListener(e -> eliminarBtnActionPerformed(e));
            eliminarBtn.putClientProperty( "JButton.buttonType", "borderless");
            btnSearchPanel.add(eliminarBtn, "cell 2 0,width 40:40:40,height 40:40:40");

            //---- printBtn ----
            printBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            printBtn.setMargin(new Insets(0, 0, 0, 0));
            printBtn.setForeground(Color.black);
            printBtn.setAlignmentY(0.0F);
            printBtn.setMaximumSize(new Dimension(79, 32));
            printBtn.setMinimumSize(new Dimension(79, 32));
            printBtn.setPreferredSize(new Dimension(79, 32));
            printBtn.setIcon(new ImageIcon(getClass().getResource("/images/icons/printer2_32.png")));
            printBtn.setToolTipText("Imprimir [Ctrl+P]");
            printBtn.setBackground(new Color(0xf7f8fa));
            printBtn.setBorderPainted(false);
            printBtn.addActionListener(e -> printBtnActionPerformed(e));
            printBtn.putClientProperty( "JButton.buttonType", "borderless");
            btnSearchPanel.add(printBtn, "cell 3 0,width 40:40:40,height 40:40:40");

            //======== aux ========
            {
                aux.setOpaque(false);
                aux.setLayout(new MigLayout(
                    "insets 0,hidemode 3",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[grow,fill]"));

                //---- buscarField ----
                buscarField.setFont(buscarField.getFont().deriveFont(buscarField.getFont().getSize() + 6f));
                buscarField.setCaretColor(new Color(0x549159));
                buscarField.setPreferredSize(new Dimension(68, 28));
                buscarField.setMinimumSize(new Dimension(68, 28));
                buscarField.setMaximumSize(new Dimension(2147483647, 28));
                buscarField.putClientProperty("JTextField.placeholderText", "Buscar socios...");
                buscarField.putClientProperty("JTextField.leadingIcon", new ImageIcon(Objects.requireNonNull(UIContasoc.class.getResource("/images/icons/search_medium_black.png"))));
                aux.add(buscarField, "cell 0 0,height 40:40:40");
            }
            btnSearchPanel.add(aux, "cell 4 0,width 500:500:500");

            //---- ayudaBtn ----
            ayudaBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            ayudaBtn.setMargin(new Insets(0, 0, 0, 0));
            ayudaBtn.setForeground(Color.black);
            ayudaBtn.setAlignmentY(0.0F);
            ayudaBtn.setMaximumSize(new Dimension(79, 32));
            ayudaBtn.setMinimumSize(new Dimension(79, 32));
            ayudaBtn.setPreferredSize(new Dimension(79, 32));
            ayudaBtn.setIcon(new ImageIcon(getClass().getResource("/images/icons/help2_32.png")));
            ayudaBtn.setToolTipText("Ayuda [F1]");
            ayudaBtn.setBackground(new Color(0xf7f8fa));
            ayudaBtn.setBorderPainted(false);
            ayudaBtn.addActionListener(e -> ayudaBtn(e));
            ayudaBtn.putClientProperty( "JButton.buttonType", "borderless");
            btnSearchPanel.add(ayudaBtn, "cell 5 0,width 40:40:40,height 40:40:40");
        }
        contentPane.add(btnSearchPanel, BorderLayout.NORTH);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 6f));
            tabbedPane1.addChangeListener(e -> tabbedPane1StateChanged(e));
            tabbedPane1.putClientProperty("JTabbedPane.minimumTabWidth", 160);
            tabbedPane1.putClientProperty("JTabbedPane.maximumTabWidth", 160);

            //======== sociosPanel ========
            {
                sociosPanel.setLayout(new CardLayout());

                //======== cardSociosPanel ========
                {
                    cardSociosPanel.setLayout(new MigLayout(
                        "insets 12 12 12 0,hidemode 3,gap 0 0",
                        // columns
                        "[grow,fill]" +
                        "[fill]",
                        // rows
                        "[grow,fill]"));

                    //======== sociosListaPanel ========
                    {

                        //---- sociosLista ----
                        sociosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        sociosLista.setCellRenderer(new SocioPanelRenderer());
                        sociosListaPanel.setViewportView(sociosLista);
                    }
                    cardSociosPanel.add(sociosListaPanel, "cell 0 0");

                    //---- toListaEsperaBtn ----
                    toListaEsperaBtn.setBackground(new Color(0xf7f8fa));
                    toListaEsperaBtn.setFont(toListaEsperaBtn.getFont().deriveFont(toListaEsperaBtn.getFont().getStyle() & ~Font.BOLD, toListaEsperaBtn.getFont().getSize() + 4f));
                    toListaEsperaBtn.setForeground(new Color(0x5b6168));
                    toListaEsperaBtn.setToolTipText("Ver lista de espera [->]");
                    toListaEsperaBtn.setBorderPainted(false);
                    toListaEsperaBtn.setBorder(null);
                    toListaEsperaBtn.addActionListener(e -> toListaEsperaBtnActionPerformed(e));
                    toListaEsperaBtn.setText("\u25B6");
                    cardSociosPanel.add(toListaEsperaBtn, "cell 1 0,width 24:24:24");
                }
                sociosPanel.add(cardSociosPanel, "cardSocios");

                //======== cardListaEsperaPanel ========
                {
                    cardListaEsperaPanel.setLayout(new MigLayout(
                        "insets 12 0 12 12,hidemode 3,gap 0 0",
                        // columns
                        "[fill]" +
                        "[grow,fill]",
                        // rows
                        "[grow,fill]"));

                    //---- toSociosBtn ----
                    toSociosBtn.setBackground(new Color(0xf7f8fa));
                    toSociosBtn.setFont(toSociosBtn.getFont().deriveFont(toSociosBtn.getFont().getStyle() & ~Font.BOLD, toSociosBtn.getFont().getSize() + 4f));
                    toSociosBtn.setForeground(new Color(0x5b6168));
                    toSociosBtn.setToolTipText("Ver socios [<-]");
                    toSociosBtn.setBorderPainted(false);
                    toSociosBtn.setBorder(null);
                    toSociosBtn.addActionListener(e -> toSociosBtnActionPerformed(e));
                    toSociosBtn.setText("\u25C0");
                    cardListaEsperaPanel.add(toSociosBtn, "cell 0 0,width 24:24:24");

                    //======== listaEsperaListaPanel ========
                    {

                        //---- listaEsperaLista ----
                        listaEsperaLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        listaEsperaLista.setCellRenderer(new ListaEsperaPanelRenderer());
                        listaEsperaListaPanel.setViewportView(listaEsperaLista);
                    }
                    cardListaEsperaPanel.add(listaEsperaListaPanel, "cell 1 0");
                }
                sociosPanel.add(cardListaEsperaPanel, "cardListaEspera");
            }
            tabbedPane1.addTab("SOCIOS", sociosPanel);

            //======== ingresosPanel ========
            {
                ingresosPanel.setLayout(new MigLayout(
                    "insets 12,hidemode 3",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[grow,fill]"));

                //======== ingresosListaPanel ========
                {

                    //---- ingresosLista ----
                    ingresosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    ingresosLista.setCellRenderer(new IngresoPanelRenderer());
                    ingresosListaPanel.setViewportView(ingresosLista);
                }
                ingresosPanel.add(ingresosListaPanel, "cell 0 0");
            }
            tabbedPane1.addTab("INGRESOS", ingresosPanel);

            //======== gastosPanel ========
            {
                gastosPanel.setLayout(new MigLayout(
                    "insets 12,hidemode 3",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[grow,fill]"));

                //======== gastosListaPanel ========
                {

                    //---- gastosLista ----
                    gastosLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    gastosLista.setCellRenderer(new GastoPanelRenderer());
                    gastosListaPanel.setViewportView(gastosLista);
                }
                gastosPanel.add(gastosListaPanel, "cell 0 0");
            }
            tabbedPane1.addTab("GASTOS", gastosPanel);

            //======== balancePanel ========
            {
                balancePanel.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentShown(ComponentEvent e) {
                        balancePanelComponentShown(e);
                    }
                });
                balancePanel.setLayout(new MigLayout(
                    "insets 33 0 0 0,hidemode 3,aligny center",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[grow,fill]"));

                //======== balanceCantidadesPanel ========
                {
                    balanceCantidadesPanel.setLayout(new GridLayout(4, 2));

                    //======== tBancoIngresos ========
                    {
                        tBancoIngresos.setLayout(new BorderLayout());

                        //---- tBancoIngresosLabel ----
                        tBancoIngresosLabel.setText("TOTAL INGRESOS BANCO");
                        tBancoIngresosLabel.setFont(tBancoIngresosLabel.getFont().deriveFont(tBancoIngresosLabel.getFont().getSize() + 10f));
                        tBancoIngresosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        tBancoIngresos.add(tBancoIngresosLabel, BorderLayout.NORTH);

                        //---- tBancoIngresosValue ----
                        tBancoIngresosValue.setText("0,00 \u20ac");
                        tBancoIngresosValue.setHorizontalTextPosition(SwingConstants.CENTER);
                        tBancoIngresosValue.setHorizontalAlignment(SwingConstants.CENTER);
                        tBancoIngresosValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        tBancoIngresos.add(tBancoIngresosValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(tBancoIngresos);

                    //======== tCajaIngresos ========
                    {
                        tCajaIngresos.setLayout(new BorderLayout());

                        //---- tCajaIngresosLabel ----
                        tCajaIngresosLabel.setText("TOTAL INGRESOS CAJA");
                        tCajaIngresosLabel.setFont(tCajaIngresosLabel.getFont().deriveFont(tCajaIngresosLabel.getFont().getSize() + 10f));
                        tCajaIngresosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        tCajaIngresos.add(tCajaIngresosLabel, BorderLayout.NORTH);

                        //---- tCajaIngresosValue ----
                        tCajaIngresosValue.setText("0,00  \u20ac");
                        tCajaIngresosValue.setHorizontalTextPosition(SwingConstants.CENTER);
                        tCajaIngresosValue.setHorizontalAlignment(SwingConstants.CENTER);
                        tCajaIngresosValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        tCajaIngresos.add(tCajaIngresosValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(tCajaIngresos);

                    //======== tBancoGastos ========
                    {
                        tBancoGastos.setLayout(new BorderLayout());

                        //---- tBancoGastosLabel ----
                        tBancoGastosLabel.setText("TOTAL GASTOS BANCO");
                        tBancoGastosLabel.setFont(tBancoGastosLabel.getFont().deriveFont(tBancoGastosLabel.getFont().getSize() + 10f));
                        tBancoGastosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        tBancoGastos.add(tBancoGastosLabel, BorderLayout.NORTH);

                        //---- tBancoGastosValue ----
                        tBancoGastosValue.setText("0,00 \u20ac");
                        tBancoGastosValue.setHorizontalTextPosition(SwingConstants.CENTER);
                        tBancoGastosValue.setHorizontalAlignment(SwingConstants.CENTER);
                        tBancoGastosValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        tBancoGastos.add(tBancoGastosValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(tBancoGastos);

                    //======== tCajaGastos ========
                    {
                        tCajaGastos.setLayout(new BorderLayout());

                        //---- tCajaGastosLabel ----
                        tCajaGastosLabel.setText("TOTAL GASTOS CAJA");
                        tCajaGastosLabel.setFont(tCajaGastosLabel.getFont().deriveFont(tCajaGastosLabel.getFont().getSize() + 10f));
                        tCajaGastosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        tCajaGastos.add(tCajaGastosLabel, BorderLayout.NORTH);

                        //---- tCajaGastosValue ----
                        tCajaGastosValue.setText("0,00 \u20ac");
                        tCajaGastosValue.setHorizontalTextPosition(SwingConstants.CENTER);
                        tCajaGastosValue.setHorizontalAlignment(SwingConstants.CENTER);
                        tCajaGastosValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        tCajaGastos.add(tCajaGastosValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(tCajaGastos);

                    //======== saldoBanco ========
                    {
                        saldoBanco.setLayout(new BorderLayout());

                        //---- saldoBancoLabel ----
                        saldoBancoLabel.setText("SALDO ACTUAL BANCO");
                        saldoBancoLabel.setFont(saldoBancoLabel.getFont().deriveFont(saldoBancoLabel.getFont().getSize() + 10f));
                        saldoBancoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        saldoBanco.add(saldoBancoLabel, BorderLayout.NORTH);

                        //---- saldoBancoValue ----
                        saldoBancoValue.setText("0,00 \u20ac");
                        saldoBancoValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        saldoBancoValue.setHorizontalAlignment(SwingConstants.CENTER);
                        saldoBanco.add(saldoBancoValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(saldoBanco);

                    //======== saldoCaja ========
                    {
                        saldoCaja.setLayout(new BorderLayout());

                        //---- saldoCajaLabel ----
                        saldoCajaLabel.setText("SALDO ACTUAL CAJA");
                        saldoCajaLabel.setFont(saldoCajaLabel.getFont().deriveFont(saldoCajaLabel.getFont().getSize() + 10f));
                        saldoCajaLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        saldoCaja.add(saldoCajaLabel, BorderLayout.NORTH);

                        //---- saldoCajaValue ----
                        saldoCajaValue.setText("0,00 \u20ac");
                        saldoCajaValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        saldoCajaValue.setHorizontalAlignment(SwingConstants.CENTER);
                        saldoCaja.add(saldoCajaValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(saldoCaja);

                    //======== cerrarAnyoPanel ========
                    {
                        cerrarAnyoPanel.setLayout(new MigLayout(
                            "hidemode 3",
                            // columns
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- cerrarAnyoBtn ----
                        cerrarAnyoBtn.setText("Cerrar A\u00f1o");
                        cerrarAnyoBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                        cerrarAnyoBtn.addActionListener(e -> cerrarAnyoBtn(e));
                        cerrarAnyoPanel.add(cerrarAnyoBtn, "cell 0 0,aligny bottom,growy 0,width 128:128:128,height 32:32:32");
                    }
                    balanceCantidadesPanel.add(cerrarAnyoPanel);
                }
                balancePanel.add(balanceCantidadesPanel, "cell 0 0");
            }
            tabbedPane1.addTab("BALANCE", balancePanel);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JPanel btnSearchPanel;
    protected static JButton nuevoBtn;
    protected static JButton editarBtn;
    protected static JButton eliminarBtn;
    protected static JButton printBtn;
    protected static JPanel aux;
    protected static JTextField buscarField;
    protected static JButton ayudaBtn;
    protected static JTabbedPane tabbedPane1;
    protected static JPanel sociosPanel;
    protected static JPanel cardSociosPanel;
    protected static JScrollPane sociosListaPanel;
    public static JList<SocioPanel> sociosLista;
    protected static JButton toListaEsperaBtn;
    protected static JPanel cardListaEsperaPanel;
    protected static JButton toSociosBtn;
    protected static JScrollPane listaEsperaListaPanel;
    public static JList<ListaEsperaPanel> listaEsperaLista;
    protected static JPanel ingresosPanel;
    protected static JScrollPane ingresosListaPanel;
    public static JList<IngresoPanel> ingresosLista;
    protected static JPanel gastosPanel;
    protected static JScrollPane gastosListaPanel;
    public static JList<GastoPanel> gastosLista;
    protected static JPanel balancePanel;
    protected static JPanel balanceCantidadesPanel;
    protected static JPanel tBancoIngresos;
    protected static JLabel tBancoIngresosLabel;
    protected static JLabel tBancoIngresosValue;
    protected static JPanel tCajaIngresos;
    protected static JLabel tCajaIngresosLabel;
    protected static JLabel tCajaIngresosValue;
    protected static JPanel tBancoGastos;
    protected static JLabel tBancoGastosLabel;
    protected static JLabel tBancoGastosValue;
    protected static JPanel tCajaGastos;
    protected static JLabel tCajaGastosLabel;
    protected static JLabel tCajaGastosValue;
    protected static JPanel saldoBanco;
    protected static JLabel saldoBancoLabel;
    protected static JLabel saldoBancoValue;
    protected static JPanel saldoCaja;
    protected static JLabel saldoCajaLabel;
    protected static JLabel saldoCajaValue;
    protected static JPanel cerrarAnyoPanel;
    protected static JButton cerrarAnyoBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
