package over.client;

import over.config.Configurator;
import over.controller.io.FileReceiver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * <code>ConfigurationPanel</code> class builds a <code>JPanel</code> to enter the configuration data necessary to
 * receive files from another computer and establish the target local directory.
 *
 * @author Overload Inc.
 * @version 1.0, 20 Aug 2020
 */
public class ConfigurationPanel extends JFrame {
    private JPanel mainPanel;
    private JButton btnAccept;
    private JButton btnLocalDirectory;
    private JLabel lblDirectory;
    private JLabel lblTargetIP;
    private JTextField txtDirectory;
    private JTextField txtTargetIP;
    private JFileChooser fileChooser;
    private static String path;
    private static String IP;
    private static File localDirectory;

    /**
     * Class constructor.
     */
    public ConfigurationPanel() {
        if(FileReceiver.getIP() == null)
            IP = "10.156.30.193";
        else
            IP = FileReceiver.getIP();

        if(FileReceiver.getLocalDirectory() != null) {
            localDirectory = new File(FileReceiver.getLocalDirectory());
            path = localDirectory.getAbsolutePath();
        }

        initComponents();
    }

    /**
     * Builds a <code>JPanel</code> to enter the origin server IP and the target local directory to
     * locate the received files.
     */
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        mainPanel = new JPanel();
        lblTargetIP = new JLabel();
        txtTargetIP = new JTextField();
        lblDirectory = new JLabel();
        txtDirectory = new JTextField();
        btnLocalDirectory = new JButton();
        btnAccept = new JButton();

        setTitle(Configurator.getConfigurator().getProperty("title02"));
        setName("frmConfiguration");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new Dimension(550, 230));
        setMinimumSize(new Dimension(550, 230));
        setPreferredSize(new Dimension(550, 230));

        mainPanel.setMaximumSize(new Dimension(550, 230));
        mainPanel.setMinimumSize(new Dimension(550, 230));
        mainPanel.setName("mainPanel");
        mainPanel.setPreferredSize(new Dimension(550, 230));
        mainPanel.setLayout(new GridBagLayout());

        lblTargetIP.setText(Configurator.getConfigurator().getProperty("lblTargetIP"));
        lblTargetIP.setName("lblTargetIP");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 10);
        mainPanel.add(lblTargetIP, gridBagConstraints);

        txtTargetIP.setMaximumSize(new Dimension(200, 30));
        txtTargetIP.setMinimumSize(new Dimension(200, 30));
        txtTargetIP.setName("txtTargetIP");
        txtTargetIP.setPreferredSize(new Dimension(200, 30));
        txtTargetIP.setText(IP);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(txtTargetIP, gridBagConstraints);

        lblDirectory.setText(Configurator.getConfigurator().getProperty("lblDirectory"));
        lblDirectory.setName("lblDirectory");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 10);
        mainPanel.add(lblDirectory, gridBagConstraints);

        txtDirectory.setEditable(false);
        txtDirectory.setBackground(new Color(255, 255, 255));
        txtDirectory.setMaximumSize(new Dimension(300, 30));
        txtDirectory.setMinimumSize(new Dimension(300, 30));
        txtDirectory.setName("txtDirectory");
        txtDirectory.setPreferredSize(new Dimension(300, 30));
        txtDirectory.setText(path);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(txtDirectory, gridBagConstraints);

        btnLocalDirectory.setText(Configurator.getConfigurator().getProperty("btnLocalDirectory"));
        btnLocalDirectory.setIcon(new ImageIcon(getClass().getResource("/over/res/img/opt_open_file.png")));
        btnLocalDirectory.setMaximumSize(new Dimension(100, 30));
        btnLocalDirectory.setMinimumSize(new Dimension(100, 30));
        btnLocalDirectory.setName("btnLocalDirectory");
        btnLocalDirectory.setPreferredSize(new Dimension(100, 30));
        btnLocalDirectory.addActionListener((ActionEvent evt) -> setLocalDirectory());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        mainPanel.add(btnLocalDirectory, gridBagConstraints);

        btnAccept.setText(Configurator.getConfigurator().getProperty("btnAccept"));
        btnAccept.setIcon(new ImageIcon(getClass().getResource("/over/res/img/opt_accept_squared.png")));
        btnAccept.setMaximumSize(new Dimension(120, 30));
        btnAccept.setMinimumSize(new Dimension(120, 30));
        btnAccept.setName("btnAccept");
        btnAccept.setPreferredSize(new Dimension(120, 30));
        btnAccept.addActionListener((ActionEvent evt) -> accept());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(btnAccept, gridBagConstraints);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Sets the local directory to locate the new incoming files.
     */
    private void setLocalDirectory() {
        fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(localDirectory);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setName("fileChooser");

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();

            txtDirectory.setText(path);
        }

        FileReceiver.setLocalDirectory(path);
    }

    /**
     * Establishes the IP and the local directory entered by the user.
     */
    private void accept() {
        FileReceiver.setIP(txtTargetIP.getText());
        this.dispose();
    }
}