/*
 * Created by JFormDesigner on Mon Mar 25 03:03:37 CET 2024
 */

package dev.galliard.contasoc.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.Trio;
import dev.galliard.contasoc.database.DBUtils;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;
import dev.galliard.contasoc.util.ErrorHandler;
import dev.galliard.contasoc.util.Parsers;
import net.miginfocom.swing.MigLayout;

/**
 * @author jomaa
 */
@SuppressWarnings("all")
public class BackupPanel extends JFrame {
	private String host;
	private final int port = 22;
	private String user;
	private String password;
	private String LOCAL_PATH = System.getProperty("user.home") + "/.contasoc/backups/";
	private String backupFile;
	private Session session;
	private ChannelSftp sftpChannel;
	
	public BackupPanel() {
		initComponents();
		this.host = Contasoc.cfgManager.getProperty("HOST");
		this.user = Contasoc.cfgManager.getProperty("SSHUser");
		this.password = Contasoc.cfgManager.getProperty("SSHPassword");
	}
	
	private void connect() {
        try {
            JSch jsch = new JSch();
            
            session = jsch.getSession(this.user, this.host, this.port);
            session.setPassword(this.password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            
            Channel channel = session.openChannel("sftp");
            channel.connect();
            
            sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd("/home/huertos/restpoints/");
        } catch (JSchException | SftpException e) {
        	Contasoc.logger.error(e.getMessage());
        }
	}
	
	private void close() {
		sftpChannel.exit();
		session.disconnect();
	}

	public void fillComboBox() {
		connect();
		Vector<ChannelSftp.LsEntry> fileList = null;
		try {
			fileList = sftpChannel.ls(".");
		} catch (SftpException e) {
			Contasoc.logger.error(e.getMessage());
		}
        List<String> valores = fileList.stream()
        		.map(e -> e.getFilename())
        		.filter(s -> !s.equals(".") && !s.equals(".."))
        		.toList();
		close();
		DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		valores = valores.stream()
				.map(s -> LocalDate.parse(Parsers.parseToDateString(s), inFormatter))
				.sorted(Comparator.reverseOrder())
				.map(d -> "Restaurar al " + d.format(outFormatter))
				.toList();
		valores.forEach(backupsComboBox::addItem);
	}
	
	private void download(String file) {
		connect();
		try {
			sftpChannel.get(file,LOCAL_PATH);
			ErrorHandler.backupDownloaded();
		} catch (SftpException e) {
			Contasoc.logger.error(e.getMessage());
		}
		close();
	}

	private void backups(ActionEvent e) {
		// TODO add your code here
		JComboBox<String> source = (JComboBox<String>) e.getSource();
        String selectedOption = (String) source.getSelectedItem();
        this.backupFile = Parsers.parseToBackupFileName(selectedOption);
	}

	private void downloadBtn(ActionEvent e) {
		String file = this.backupFile;
		download(file);
	}

	private void previewBtn(ActionEvent e) {
		File file = new File(System.getProperty("user.home") + "/.contasoc/backups/" + this.backupFile);
		String s = null;
		try {
			s = Files.readString(Path.of(file.getPath()));
		} catch (IOException e1) {
			Contasoc.logger.error(e1.getMessage());
		}
		editorPane.setText(s);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
		toolBar = new JPanel();
		downloadBtn = new JButton();
		previewBtn = new JButton();
		backupsComboBox = new JComboBox<>();
		mainPanel = new JPanel();
		scrollPane = new JScrollPane();
		editorPane = new JEditorPane();

		//======== this ========
		setResizable(false);
		setTitle("Gestor de puntos de restauraci\u00f3n");
		var contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3",
			// columns
			"[grow,fill]",
			// rows
			"[fill]" +
			"[grow,fill]"));

		//======== toolBar ========
		{
			toolBar.setLayout(new MigLayout(
				"insets 0,hidemode 3",
				// columns
				"[fill]" +
				"[fill]" +
				"[grow,fill]",
				// rows
				"[fill]"));

			//---- downloadBtn ----
			downloadBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			downloadBtn.setMargin(new Insets(0, 0, 0, 0));
			downloadBtn.setForeground(Color.black);
			downloadBtn.setAlignmentY(0.0F);
			downloadBtn.setMaximumSize(new Dimension(79, 32));
			downloadBtn.setMinimumSize(new Dimension(79, 32));
			downloadBtn.setPreferredSize(new Dimension(79, 32));
			downloadBtn.setIcon(new ImageIcon(getClass().getResource("/images/icons/download.png")));
			downloadBtn.setToolTipText("Descargar [Ctrl+F]");
			downloadBtn.setBorderPainted(false);
			downloadBtn.setBackground(new Color(0xf7f8fa));
			downloadBtn.addActionListener(e -> downloadBtn(e));
			downloadBtn.putClientProperty( "JButton.buttonType", "borderless");
			toolBar.add(downloadBtn, "cell 0 0,width 40:40:40,height 40:40:40");

			//---- previewBtn ----
			previewBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			previewBtn.setMargin(new Insets(0, 0, 0, 0));
			previewBtn.setForeground(Color.black);
			previewBtn.setAlignmentY(0.0F);
			previewBtn.setMaximumSize(new Dimension(79, 32));
			previewBtn.setMinimumSize(new Dimension(79, 32));
			previewBtn.setPreferredSize(new Dimension(79, 32));
			previewBtn.setIcon(new ImageIcon(getClass().getResource("/images/icons/preview.png")));
			previewBtn.setToolTipText("Previsualizazr [Ctrl+T]");
			previewBtn.setBorderPainted(false);
			previewBtn.setBackground(new Color(0xf7f8fa));
			previewBtn.addActionListener(e -> previewBtn(e));
			previewBtn.putClientProperty( "JButton.buttonType", "borderless");
			toolBar.add(previewBtn, "cell 1 0,width 40:40:40,height 40:40:40");

			//---- backupsComboBox ----
			backupsComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			backupsComboBox.addActionListener(e -> backups(e));
			toolBar.add(backupsComboBox, "cell 2 0");
		}
		contentPane.add(toolBar, "cell 0 0");

		//======== mainPanel ========
		{
			mainPanel.setLayout(new MigLayout(
				"insets 0,hidemode 3",
				// columns
				"[grow,fill]",
				// rows
				"[grow,fill]"));

			//======== scrollPane ========
			{

				//---- editorPane ----
				editorPane.setAutoscrolls(false);
				scrollPane.setViewportView(editorPane);
			}
			mainPanel.add(scrollPane, "cell 0 0");
		}
		contentPane.add(mainPanel, "cell 0 1");
		setSize(650, 450);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
	private JPanel toolBar;
	protected static JButton downloadBtn;
	protected static JButton previewBtn;
	private JComboBox<String> backupsComboBox;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private JEditorPane editorPane;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
