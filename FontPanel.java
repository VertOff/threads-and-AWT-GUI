import java.awt.*;

public class FontPanel {
    private static final Panel secondPanel = new Panel();
    private static List textFonts;
    private static List textTypes;
    private static List textSizes;
    private static Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);

    public static Panel getSecondPanel() {return secondPanel;}
    public static Font getFont(){return font;}
    public static List[] getFontLists(){return new List[] {textFonts,textTypes,textSizes,};}

    public void layoutSecondPanel(){
        secondPanel.setLayout(new GridLayout(1, 3));
        textFonts = new List(248, false);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        for (String fontName : fontNames) {
            textFonts.add(fontName);
        }
        textFonts.addActionListener(e -> {
            font = new Font(textFonts.getSelectedItem(), font.getStyle(), font.getSize());

            if(!ColorPanel.isFirstCheckbox)
                TextWindow.Text = FontPanel.getFont().getFamily();
        });
        secondPanel.add(textFonts);

        textTypes = new List(3, false);
        textTypes.add("Plain");
        textTypes.add("Bold");
        textTypes.add("Italic");
        textTypes.addActionListener(e -> font = new Font(font.getFontName(),
                textTypes.getSelectedIndex(), font.getSize()));
        secondPanel.add(textTypes);

        textSizes = new List(246, false);
        for(int i = 2; i < 81; i = i+2){
            textSizes.add(String.valueOf(i));
        }
        textSizes.addActionListener(e -> font = new Font(font.getFontName(),
                font.getStyle(), Integer.parseInt(textSizes.getSelectedItem())));
        secondPanel.add(textSizes);
    }
}
