package over.client;

import over.config.Configurator;
import over.controller.format.FontEditor;

import javax.swing.*;
import java.awt.*;

/**
 * <code>About</code> class implements a <code>JFrame</code> with the <code>Sentinel</code> application developers' information.
 *
 * @author Overload Inc.
 * @version 1.0, 08 Aug 2020
 */
public class About extends JFrame {
    private JPanel aboutPanel;
    private JScrollPane aboutScroll;
    private JPanel creditsPanel;
    private JScrollPane creditsScroll;
    private JLabel lblLogo;
    private JPanel softwarePanel;
    private JTabbedPane tabbedPane;
    private JTextPane txtAbout;
    private JTextPane txtCredits;

    /**
     * Class constructor.
     */
    public About() {
        initComponents();
        setAbout();
    }

    /**
     * Initializes the <code>JFrame</code> components.
     */
    private void initComponents() {
        softwarePanel = new JPanel();
        lblLogo = new JLabel();
        tabbedPane = new JTabbedPane();
        aboutPanel = new JPanel();
        aboutScroll = new JScrollPane();
        txtAbout = new JTextPane();
        creditsPanel = new JPanel();
        creditsScroll = new JScrollPane();
        txtCredits = new JTextPane();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Configurator.getConfigurator().getProperty("about"));
        setName("frmAbout");
        setResizable(false);

        softwarePanel.setName("softwarePanel");
        softwarePanel.setLayout(new BorderLayout());

        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setName("lblLogo");
        //lblLogo.setIcon(new ImageIcon(getClass().getResource("/over/res/img/sentinel_about.png")));
        softwarePanel.add(lblLogo, BorderLayout.CENTER);

        getContentPane().add(softwarePanel, BorderLayout.CENTER);

        tabbedPane.setName("tabbedPane");

        aboutPanel.setMaximumSize(new Dimension(400, 100));
        aboutPanel.setMinimumSize(new Dimension(400, 100));
        aboutPanel.setName("aboutPanel");
        aboutPanel.setPreferredSize(new Dimension(400, 100));
        aboutPanel.setLayout(new BorderLayout());

        aboutScroll.setName("aboutScroll");

        txtAbout.setName("txtAbout");
        txtAbout.setEditable(false);
        aboutScroll.setViewportView(txtAbout);

        aboutPanel.add(aboutScroll, BorderLayout.CENTER);

        tabbedPane.addTab(Configurator.getConfigurator().getProperty("about"), aboutPanel);

        creditsPanel.setMaximumSize(new Dimension(400, 100));
        creditsPanel.setMinimumSize(new Dimension(400, 100));
        creditsPanel.setName("creditsPanel");
        creditsPanel.setPreferredSize(new Dimension(400, 100));
        creditsPanel.setLayout(new BorderLayout());

        creditsScroll.setName("creditsScroll");

        txtCredits.setName("txtCredits");
        txtCredits.setEditable(false);
        creditsScroll.setViewportView(txtCredits);

        creditsPanel.add(creditsScroll, BorderLayout.CENTER);

        tabbedPane.addTab(Configurator.getConfigurator().getProperty("credits"), creditsPanel);

        getContentPane().add(tabbedPane, BorderLayout.SOUTH);

        pack();

        setLocationRelativeTo(null);
    }

    /**
     * Sets the information of the applications and developers involved in the project.
     */
    public void setAbout() {
        String application = Configurator.getConfigurator().getProperty("application");
        String description = " " + Configurator.getConfigurator().getProperty("description");
        String team = Configurator.getConfigurator().getProperty("team");
        String company = "\t" + Configurator.getConfigurator().getProperty("company");
        String developer01 = "\n\t" + Configurator.getConfigurator().getProperty("developer01");
        String contact = "\n" + Configurator.getConfigurator().getProperty("contact");
        String email01 = "\t" + Configurator.getConfigurator().getProperty("email01");
        String email02 = "\n\t" + Configurator.getConfigurator().getProperty("email02");

        try {
            FontEditor fontEditor = new FontEditor();
            fontEditor.setBold(txtAbout, application);
            fontEditor.setSimple(txtAbout, description);
            fontEditor.setBold(txtCredits, team);
            fontEditor.setSimple(txtCredits, company);
            fontEditor.setSimple(txtCredits, developer01);
            fontEditor.setSimple(txtCredits,"\n");
            fontEditor.setBold(txtCredits, contact);
            fontEditor.setSimple(txtCredits, email01);
            fontEditor.setSimple(txtCredits, email02);
        }
        catch(Exception e) {
        }
    }
}