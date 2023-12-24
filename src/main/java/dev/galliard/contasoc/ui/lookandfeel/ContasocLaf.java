package dev.galliard.contasoc.ui.lookandfeel;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;

import javax.swing.*;
import java.awt.*;

public class ContasocLaf extends FlatGitHubIJTheme {

    private static final String GREEN = "#549159";
    private static final String LIGHT_GREEN = "#6FAA6F";
    private static final String LIGHTER_GREEN = "#BADBBC";
    private static final String SELECTION = "#C8E8CA";
    private static final String GRAY_BORDER = "#7E7F87";

    public static boolean setup() {
        setProperties();
        return setup( new ContasocLaf() );
    }

    private static void setProperties() {
        UIManager.put("TitlePane.background", Color.decode(GREEN));
        UIManager.put("TitlePane.unifiedBackground", false);
        UIManager.put("TitlePane.foreground", Color.WHITE);
        UIManager.put("TitlePane.borderColor", Color.decode(GREEN));

        UIManager.put("TableHeader.background", Color.decode(GREEN));
        UIManager.put("TableHeader.hoverBackground", Color.decode(LIGHT_GREEN));
        UIManager.put("TableHeader.hoverForeground", Color.BLACK);
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.PLAIN, 18));
        UIManager.put("TableHeader.selectionBackground", Color.decode(SELECTION));
        UIManager.put("TableHeader.selectionForeground", Color.BLACK);
        UIManager.put("Table.selectionBackground", Color.decode(SELECTION));
        UIManager.put("Table.selectionForeground", Color.BLACK);

        UIManager.put("Component.focusedBorderColor", Color.decode(GREEN));
        UIManager.put("Component.focusWidth", 1);
        UIManager.put("Component.innerFocusWidth", 1);
        UIManager.put("Component.borderColor", Color.decode(GRAY_BORDER));
        UIManager.put("Component.selectionBackground", Color.decode(LIGHTER_GREEN));

        UIManager.put("Button.background", Color.decode(LIGHTER_GREEN));
        UIManager.put("Button.hoverBackground", Color.decode(SELECTION));

        UIManager.put("ToolTip.background", Color.decode(GREEN));
        UIManager.put("ToolTip.foreground", Color.WHITE);

        UIManager.put("TabbedPane.selectedBackground", Color.decode(LIGHT_GREEN));
        UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
        UIManager.put("TabbedPane.hoverColor", Color.decode(SELECTION));
        UIManager.put("TabbedPane.hoverForeground", Color.BLACK);

        System.setProperty("flatlaf.useWindowDecorations", "true");
    }

    @Override
    public String getName() {
        return "ContasocLaf";
    }
}
