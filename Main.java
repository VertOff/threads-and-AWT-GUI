import java.awt.*;
import java.awt.event.*;

public class Main {
    static Frame main = new Frame();

    public static void settingsMain(){
        main.setLayout(new GridLayout(3, 1));
        main.setSize(800, 800);
    }

    public static void main(String[] args) {

        main.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });

        ColorPanel firstPanel = new ColorPanel();
        FontPanel secondPanel = new FontPanel();
        firstPanel.layoutFirstPanel();
        secondPanel.layoutSecondPanel();

        TextPanel a = new TextPanel();
        TextPanel.setTextPanel();

        main.add(ColorPanel.getFirstPanel());
        main.add(FontPanel.getSecondPanel());
        main.add(a.getThird());

        settingsMain();

        main.setVisible(true);
    }
}