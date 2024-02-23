package dev.galliard.contasoc.ui.theme;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.formdev.flatlaf.util.SystemInfo;

import javax.swing.*;
import java.awt.*;

public class ContasocLaf extends FlatGitHubIJTheme {

    private static final String GREEN = "#549159";
    private static final String DARK_GREEN = "#3b6642";
    private static final String GASOIL_GREEN = "#548891";
    private  static final String ALT_GREEN = "#709154";
    private static final String ALT_DARK_GREEN = "#4f663b";
    private static final String LIGHT_GREEN = "#C8E8CA";
    private static final String GRAY_BORDER = "#7E7F87";
    private static final String LIGHT_GRAY_BORDER = "#A0A0A0";

    public static boolean setup() {
        setProperties();
        return setup(new ContasocLaf());
    }

    private static void setProperties() {
        UIManager.put("TitlePane.background", Color.decode(GREEN));
        UIManager.put("RootPane.background", Color.decode(GREEN));
        UIManager.put("TitlePane.inactiveBackground", Color.decode(GREEN));
        UIManager.put("TitlePane.inactiveForeground", Color.WHITE);
        UIManager.put("TitlePane.unifiedBackground", true);
        UIManager.put("TitlePane.foreground", Color.WHITE);
        UIManager.put("TitlePane.font", new Font("Segoe UI", Font.PLAIN, 14));

        UIManager.put("TableHeader.background", Color.decode(GREEN));
        UIManager.put("TableHeader.hoverBackground", Color.decode(LIGHT_GREEN));
        UIManager.put("TableHeader.hoverForeground", Color.BLACK);
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.PLAIN, 18));
        UIManager.put("TableHeader.selectionBackground", Color.decode(LIGHT_GREEN));
        UIManager.put("TableHeader.selectionForeground", Color.BLACK);
        UIManager.put("Table.selectionBackground", Color.decode(LIGHT_GREEN));
        UIManager.put("Table.selectionForeground", Color.BLACK);
        UIManager.put("Table.sortIconColor", Color.WHITE);

        UIManager.put("Component.focusedBorderColor", Color.decode(GREEN));
        UIManager.put("Component.focusWidth", 1);
        UIManager.put("Component.innerFocusWidth", 1);
        UIManager.put("Component.borderColor", Color.decode(GRAY_BORDER));
        UIManager.put("Component.selectionBackground", Color.decode(LIGHT_GREEN));

        UIManager.put("Button.background", Color.decode(LIGHT_GREEN));
        UIManager.put("Button.hoverBackground", Color.decode(LIGHT_GREEN));

        UIManager.put("ToolTip.background", Color.decode(GREEN));
        UIManager.put("ToolTip.foreground", Color.WHITE);

        UIManager.put("TabbedPane.selectedBackground", Color.decode(LIGHT_GREEN));
        UIManager.put("TabbedPane.selectedForeground", Color.BLACK);
        UIManager.put("TabbedPane.hoverColor", Color.decode(LIGHT_GREEN));
        UIManager.put("TabbedPane.hoverForeground", Color.BLACK);
        UIManager.put("TabbedPane.contentAreaColor", Color.decode(LIGHT_GRAY_BORDER));

        UIManager.put("ScrollBar.thumb", Color.decode(GREEN));
        UIManager.put("ScrollBar.thumbDarkShadow", Color.decode(LIGHT_GREEN));
        UIManager.put("ScrollBar.thumbHighlight", Color.decode(GREEN));
        UIManager.put("ScrollBar.thumbShadow", Color.decode(LIGHT_GREEN));
        UIManager.put("ScrollBar.track", Color.decode(LIGHT_GREEN));
        UIManager.put("ScrollBar.trackHighlight", Color.decode(LIGHT_GREEN));
        UIManager.put("ScrollBar.trackHighlightForeground", Color.decode(LIGHT_GREEN));
        UIManager.put("ScrollBar.trackForeground", Color.decode(LIGHT_GREEN));
        UIManager.put("ScrollBar.hoverTrackColor", Color.decode(LIGHT_GREEN));
        UIManager.put("ScrollBar.hoverThumbColor", Color.decode(GREEN));
        UIManager.put("ScrollBar.width", 10);

        UIManager.put("PasswordField.showRevealButton", true);

        UIManager.put("List.selectionInactiveBackground", LIGHT_GREEN);

        System.setProperty("flatlaf.menuBarEmbedded", "true");

        if (SystemInfo.isLinux) {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        }
    }

    @Override
    public String getName() {
        return "ContasocLaf";
    }
}
