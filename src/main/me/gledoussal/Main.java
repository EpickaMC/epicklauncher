package main.me.gledoussal;

import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameTweak;
import fr.theshark34.openlauncherlib.minecraft.GameType;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.me.gledoussal.nologin.account.Account;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends Application {

    public static final String APPLICATION_TITLE = "Epicka Dagloth's Launcher";
    public static final String WEBSITE_URL = "https://epicka-dagloth.fr/";
    public static final String SHOP_URL = "https://epicka-dagloth.fr/shop";
    public static final String SERVER_IP = "135.125.65.202";
    public static final int SERVER_PORT = 25603;
    public static final String SERVER_STAFF_IP = "gmv4.inovaperf.fr";
    public static final int SERVER_STAFF_PORT = 25664;
    public static final String UPDATE_URL = "http://launcher.dagloth.inovaperf.me/";
    public static final String DISCORD_URL = "https://discord.gg/6ZpQmXZ2C5";
    public static final String TWITTER_URL = "https://twitter.com/EpickaDagloth";
    public static final String YOUTUBE_URL = "https://www.youtube.com/channel/UCgVSp5ErLJtZa1wO5u6XtYA";
    public static final String INSTAGRAM_URL = "https://www.instagram.com/epickadaglothsorigins/";
    public static final String TIKTOK_URL = "https://www.tiktok.com/@epickadagloth";
    public static final String BETA_UPDATE_URL = "http://launcher.dagloth.inovaperf.me/";
    public static final String LAUNCHER_VERSION = "BETA - 1.0.0";
    public static final String LAUNCHER_CHECK_URL = "http://launcher.dagloth.inovaperf.me/resources/version.txt";
    public static final String LAUNCHER_DOWNLOAD_EXE_URL = "https://launcher.dagloth.inovaperf.me/EpickaDaglothLauncher.jar";
    public static final String LAUNCHER_DOWNLOAD_JAR_URL = "https://launcher.dagloth.inovaperf.me/EpickaDaglothLauncher.jar";
    private static final GameVersion VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
    public static final GameInfos INFOS = new GameInfos("Epicka_Dagloth", VERSION, new GameTweak[]{GameTweak.FORGE});
    public static final File DIR = INFOS.getGameDir();
    private static final GameVersion BETA_VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
    public static final GameInfos BETA_INFOS = new GameInfos("Epicka_Dagloth", BETA_VERSION, new GameTweak[]{GameTweak.FORGE});
    public static final File BETA_DIR = BETA_INFOS.getGameDir();
    public static Account account;
    public static List<Account> accountList = new ArrayList<Account>();
    public static Stage primaryStage = null;
    private double xOffset = 0.0;
    private double yOffset = 0.0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Main.primaryStage = stage;

        if (System.getProperty("os.name").contains("Windows")) {
            if (System.getenv("ProgramFiles(x86)") != null) {
                if (System.getProperty("sun.arch.data.model").equals("32")) {
                    JOptionPane.showMessageDialog(null, "Java 32bit est installé sur un windows" +
                            "64bit. Le jeu ne pourra pas se lancer ! Le navigateur va s'ouvrir sur le site " +
                            "officiel de Epicka Dagloth (allez en bas de la page et vous trouverez la solution)");
                    Desktop.getDesktop().browse(new URI("https://epicka-dagloth.fr/installation"));
                    System.exit(1);
                }
            }
        }

        DIR.mkdir();

        Font.loadFont(getClass().getResourceAsStream("/fonts/BrownRegular.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Lulo.ttf"), 14);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/main.fxml")));
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {

            if (yOffset < 36) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }

        });

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle(Main.APPLICATION_TITLE);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/images/icon.png"))));
        primaryStage.setResizable(false);

        Scene scene = new Scene(root, 1200, 737);
        primaryStage.setScene(scene);
        primaryStage.show();
        Platform.runLater(root::requestFocus);
    }
}
