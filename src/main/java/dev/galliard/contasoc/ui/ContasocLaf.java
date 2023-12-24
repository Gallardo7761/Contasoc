package dev.galliard.contasoc.ui;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;

import javax.swing.*;
import java.awt.*;

public class ContasocLaf extends FlatGitHubIJTheme {

    private static final String GREEN = "#549159";
    private static final String LIGHT_GREEN = "#6FAA6F";
    private static final String SELECTION = "#99cd85";

    public static boolean setup() {
        setProperties();
        return setup( new ContasocLaf() );
    }

    private static void setProperties() {
        System.setProperty("flatlaf.menuBarEmbedded", "true");
        UIManager.put("TitlePane.background", Color.decode(GREEN));
        UIManager.put("TitlePane.unifiedBackground", false);
        UIManager.put("TitlePane.foreground", Color.WHITE);
        UIManager.put("TitlePane.menuBarEmbedded", true);
        UIManager.put("TitlePane.centerTitleIfMenuBarEmbedded", true);
        UIManager.put("TitlePane.borderColor", Color.decode(GREEN));

        UIManager.put("TableHeader.background", Color.decode(GREEN));
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.PLAIN, 18));
        UIManager.put("TableHeader.selectionBackground", Color.decode(LIGHT_GREEN));
        UIManager.put("TableHeader.selectionForeground", Color.WHITE);
        UIManager.put("Table.selectionBackground", Color.decode(SELECTION));
        UIManager.put("Table.selectionForeground", Color.BLACK);

        UIManager.put("Component.focusedBorderColor", Color.decode(GREEN));
        UIManager.put("Component.focusWidth", 0);
        UIManager.put("Component.innerFocusWidth", 0);



    }

    @Override
    public String getName() {
        return "ContasocLaf";
    }
}
