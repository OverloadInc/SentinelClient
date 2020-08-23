package over.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <code>Configurator</code> class allows establishing the default application's language.
 *
 * @author Overload Inc.
 * @version 1.0, 08 Aug 2020
 */
public class Configurator {

    /**
     * The <code>properties</code> file name.
     */
    private String file;

    /**
     * The <code>properties</code> file path.
     */
    private final String PATH = "over/config/";

    /**
     * The <code>Configurator</code> class instance.
     */
    private static Configurator configurator;

    /**
     * Class constructor.
     */
    private Configurator() {
    }

    /**
     * Gets a <code>Configurator</code> class instance for accessing to the class' methods.
     * @return the <code>Configurator</code> class instance.
     */
    public static Configurator getConfigurator() {
        if(configurator == null)
            configurator = new Configurator();

        return configurator;
    }

    /**
     * Initializes the configurator according to the user's system default language.
     */
    public void initConfigurator() {
        String locale = Locale.getDefault().getLanguage().toLowerCase();

        switch (locale) {
            case "español":
            case "es": setFile("es_config"); break;
            default: setFile("en_config");
        }
    }

    /**
     * Sets the <code>properties</code> file name for the application language.
     * @param fileName the <code>properties</code> file name.
     */
    public void setFile(String fileName) {
        file = fileName;
    }

    /**
     * Gets the <code>properties</code> file name.
     * @return the <code>properties</code> file name.
     */
    public String getFile() {
        return file;
    }

    /**
     * Gets the path of the <code>properties</code> file.
     * @param property the <code>properties</code> file name.
     * @return the path of the <code>properties</code> file.
     */
    public String getProperty(String property) {
        return ResourceBundle.getBundle(PATH + file).getString(property);
    }
}