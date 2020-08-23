package over.client;

import over.config.Configurator;
import over.controller.format.FontEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * <code>SentinelClient</code> class implements a <code>JFrame</code> with the <code>Sentinel</code> application
 * graphical user interface.
 *
 * @author Overload Inc.
 * @version 1.0, 20 Aug 2020
 */
public class SentinelClient extends JFrame {
    private JMenuItem aboutOption;
    private JMenuItem configOption;
    private JMenuItem exitOption;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JScrollPane scrollConsole;
    private JMenu settingsMenu;
    private static JTextPane txtConsole;
    private static FontEditor fontEditor;

    /**
     * Class constructor.
     */
    public SentinelClient() {
        initComponents();
        fontEditor = new FontEditor();
    }

    /**
     * Initializes the GUI components.
     */
    private void initComponents() {
        mainPanel = new JPanel();
        scrollConsole = new JScrollPane();
        txtConsole = new JTextPane();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        exitOption = new JMenuItem();
        settingsMenu = new JMenu();
        configOption = new JMenuItem();
        helpMenu = new JMenu();
        aboutOption = new JMenuItem();

        GridBagConstraints gridBagConstraints;

        setTitle(Configurator.getConfigurator().getProperty("clientName"));
        setName("frmSentinel");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(400, 400));
        setMinimumSize(new Dimension(400, 400));
        setPreferredSize(new Dimension(400, 400));

        mainPanel.setMaximumSize(new Dimension(400, 400));
        mainPanel.setMinimumSize(new Dimension(400, 400));
        mainPanel.setName("mainPanel");
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.setLayout(new GridBagLayout());

        scrollConsole.setName("scrollConsole");
        scrollConsole.setPreferredSize(new Dimension(300, 300));

        txtConsole.setMaximumSize(new Dimension(300, 300));
        txtConsole.setMinimumSize(new Dimension(300, 300));
        txtConsole.setName("txtConsole");
        txtConsole.setPreferredSize(new Dimension(300, 300));
        scrollConsole.setViewportView(txtConsole);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(scrollConsole, gridBagConstraints);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        menuBar.setName("menuBar");

        fileMenu.setMnemonic('F');
        fileMenu.setText(Configurator.getConfigurator().getProperty("fileMenu"));
        fileMenu.setName("fileMenu");

        exitOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        exitOption.setMnemonic('E');
        exitOption.setText(Configurator.getConfigurator().getProperty("exitOption"));
        exitOption.setName("exitOption");
        exitOption.setIcon(new ImageIcon(getClass().getResource("/over/res/img/opt_exit.png")));
        exitOption.addActionListener(e -> System.exit(0));
        fileMenu.add(exitOption);

        menuBar.add(fileMenu);

        settingsMenu.setMnemonic('S');
        settingsMenu.setText(Configurator.getConfigurator().getProperty("settingsMenu"));
        settingsMenu.setName("settingsMenu");

        configOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        configOption.setMnemonic('O');
        configOption.setText(Configurator.getConfigurator().getProperty("configOption"));
        configOption.setName("configOption");
        configOption.setIcon(new ImageIcon(getClass().getResource("/over/res/img/opt_settings_gray.png")));
        configOption.addActionListener((ActionEvent evt) -> initLocalDirectory());
        settingsMenu.add(configOption);

        menuBar.add(settingsMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText(Configurator.getConfigurator().getProperty("helpMenu"));
        helpMenu.setName("helpMenu");

        aboutOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        aboutOption.setMnemonic('A');
        aboutOption.setText(Configurator.getConfigurator().getProperty("aboutOption"));
        aboutOption.setName("aboutOption");
        aboutOption.setIcon(new ImageIcon(getClass().getResource("/over/res/img/opt_about.png")));
        aboutOption.addActionListener(e -> new About().setVisible(true));
        helpMenu.add(aboutOption);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Opens a new windows to establish the <code>Sentinel</code>'s configuration parameters.
     */
    private void initLocalDirectory() {
        new ConfigurationPanel().setVisible(true);
    }

    /**
     * Adds a new message to the <code>Sentinel</code>'s console.
     * @param path the file path detected.
     */
    public static void addMessage(String path) {
        String message = Configurator.getConfigurator().getProperty("message04");

        fontEditor.setSimple(txtConsole, message + " " + path + "\n");
    }

    /**
     * The <code>Sentinel</code>'s starting point.
     * @param args the initial parameters.
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            Configurator.getConfigurator().initConfigurator();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SentinelClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new SentinelClient().setVisible(true));
    }
}