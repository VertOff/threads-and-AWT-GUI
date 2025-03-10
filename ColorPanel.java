import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ColorPanel {
    private static final Panel firstPanel = new Panel();
    private static final Panel first = new Panel();

    private static final Choice colorChoice = new Choice();
    private static final Button textColorBut = new Button("Цвет текста");
    private static final Button groundColorBut = new Button("Цвет фона");
    private static final String[] ruColor = new String[]{"Чёрный","Красный","Оранжевый","Жёлтый",
            "Зелёный","Бирюзовый","Синий","Фиолетовый","Белый"};
    private static final Color[] engColor = new Color[]{Color.BLACK, Color.RED, Color.ORANGE, Color.YELLOW,
            Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.WHITE};
    private static final CheckboxGroup checkBox = new CheckboxGroup();

    private static final Checkbox textPanel = new Checkbox("Из текстового поля", checkBox, true);
    private static final Checkbox fontName = new Checkbox("Имя шрифта", checkBox, false);

    public static Boolean isFirstCheckbox = true;
    private static Label labelColorChoice;
    private static Boolean textOrBG = true;

    public static Choice getColorChoice() {return colorChoice;}
    public static Panel getFirstPanel(){ return firstPanel;}
    public static Checkbox getTextPanel() {return textPanel;}
    public static Checkbox getFontName() {return fontName;}

    public void layoutFirstPanel() {
        firstPanel.setLayout(new GridLayout(1, 2));
        firstPanel.setBackground(Color.WHITE);
        firstPanel.add(first);

        textColorBut.addActionListener(e -> {
            labelColorChoice.setText("Цвет текста");
            ArrayList<Color> arr = new ArrayList<>(Arrays.asList(engColor));
            colorChoice.select(arr.indexOf(TextWindow.textColor));
            textOrBG = true;
        });
        groundColorBut.addActionListener(e -> {
            labelColorChoice.setText("Цвет фона");
            ArrayList<Color> arr = new ArrayList<>(Arrays.asList(engColor));
            colorChoice.select(arr.indexOf(TextWindow.backGColor));
            textOrBG = false;
        });

        first.setLayout(new GridLayout(2, 2));
        first.add(textColorBut);
        first.add(groundColorBut);

        first.add(textPanel);

        textPanel.addItemListener(e ->{
            isFirstCheckbox = true;
            TextWindow.Text = TextPanel.getText();
        });

        first.add(fontName);

        fontName.addItemListener(e ->{
            isFirstCheckbox = false;
            TextWindow.Text = FontPanel.getFont().getFamily();
        });

        Panel second = new Panel();
        firstPanel.add(second);
        second.setLayout(new GridLayout(1,3));

        Panel panelTemp1 = new Panel();
        second.add(panelTemp1);

        Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(3,1));

        labelColorChoice = new Label("Цвет текста", Label.CENTER);
        panel2.add(labelColorChoice);

        for (String temp : ruColor)
            colorChoice.add(temp);

        colorChoice.addItemListener(e -> {
            if(textOrBG)
                TextWindow.textColor = engColor[colorChoice.getSelectedIndex()];
            else
                TextWindow.backGColor = engColor[colorChoice.getSelectedIndex()];
        });

        panel2.add(colorChoice);

        Panel panelTemp2 = new Panel();
        panel2.add(panelTemp2);

        second.add(panel2);

        Panel panelTemp3 = new Panel();
        second.add(panelTemp3);
    }
}
