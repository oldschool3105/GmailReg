import java.nio.file.Path;
import java.nio.file.Paths;

public class MainClass {

    public static void main(String[] args) {
        System.out.println(getGeckoDriverPath());
    }

    // Driver path
    private static final String GECKO_DRIVER_PATH_UNIX = "src/main/resources/geckodriver";
    private static final String GECKO_DRIVER_PATH_WIN = "src\\main\\resources\\geckodriver";
    // Driver files
    private static final String GECKO_DRIVER_MAC = "/geckodriver-v0.19.1-macos";
    private static final String GECKO_DRIVER_LINUX_32 = "/geckodriver-v0.19.1-linux32";
    private static final String GECKO_DRIVER_LINUX_64 = "/geckodriver-v0.19.1-linux64";
    private static final String GECKO_DRIVER_WIN_32 = "\\geckodriver-v0.19.1-win32.exe";
    private static final String GECKO_DRIVER_WIN_64 = "\\geckodriver-v0.19.1-win64.exe";


    public static String getGeckoDriverPath() {

        String os = System.getProperty("os.name").toLowerCase();
        String bytes = System.getProperty("os.arch");

        boolean isWin = os.contains("win");
        boolean isMac = os.contains("mac");
        boolean isLinux = os.contains("nix") || os.contains("nux") || os.contains("aix");
        boolean is32 = bytes.equals("x86") || bytes.equals("i386") || bytes.equals("i486") || bytes.equals("i586") || bytes.equals("i686");
        boolean is64 = bytes.equals("x86_64") || bytes.equals("amd64");

        // Detect GECKO driver directory
        String fileName = "";
        Path GECKODriverDirectory = null;
        if (isMac || isLinux) {
            GECKODriverDirectory = Paths.get(GECKO_DRIVER_PATH_UNIX);
        }
        if (isWin) {
            GECKODriverDirectory = Paths.get(GECKO_DRIVER_PATH_WIN);
        }

        String GECKODriverPath = GECKODriverDirectory.toAbsolutePath().toString();

        // Detect which driver to use
        if (isMac) {
            GECKODriverPath = GECKODriverPath.concat(GECKO_DRIVER_MAC);
        }

        if (isLinux) {
            if (is32) {
                GECKODriverPath = GECKODriverPath.concat(GECKO_DRIVER_LINUX_32);
            }
            if (is64) {
                GECKODriverPath = GECKODriverPath.concat(GECKO_DRIVER_LINUX_64);
            }
        }

        if (isWin) {
            if (is32) {
                GECKODriverPath = GECKODriverPath.concat(GECKO_DRIVER_WIN_32);
            }
            if (is32) {
                GECKODriverPath = GECKODriverPath.concat(GECKO_DRIVER_WIN_64);
            }
        }

        return GECKODriverPath;
    }
}
