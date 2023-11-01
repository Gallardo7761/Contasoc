package es.exmaster.contasoc.ui;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;

import javax.swing.*;
import java.awt.*;

public class ContasocLaf extends FlatGitHubIJTheme {

    private static final String GREEN = "#549159";
    private static final String LIGHT_GREEN = "#79CB60";
    public static final String NAME = "contasoc";

    public static boolean setup() {
        setProperties();
        installLafInfo();
        return setup(new ContasocLaf());
    }

    private static void setProperties() {
        System.setProperty("flatlaf.menuBarEmbedded", "true");
        UIManager.put("TitlePane.background", Color.decode(GREEN));
        UIManager.put("TitlePane.unifiedBackground", false);
        UIManager.put("TitlePane.foreground", Color.WHITE);
        UIManager.put("TitlePane.menuBarEmbedded", true);
        UIManager.put("TitlePane.centerTitleIfMenuBarEmbedded", true);
        UIManager.put("TitlePane.borderColor", Color.decode(LIGHT_GREEN));

        UIManager.put("TableHeader.background", Color.decode(GREEN));
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TableHeader.selectionBackground", Color.decode(LIGHT_GREEN));
        UIManager.put("TableHeader.selectionForeground", Color.WHITE);
        UIManager.put("Table.selectionBackground", Color.decode(LIGHT_GREEN));
        UIManager.put("Table.selectionForeground", Color.WHITE);

        UIManager.put("Component.focusedBorderColor", Color.decode(LIGHT_GREEN));
        UIManager.put("Component.focusWidth", 0);
        UIManager.put("Component.innerFocusWidth", 0);
    }

    public static void installLafInfo() {
        installLafInfo(NAME, ContasocLaf.class);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
