import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.*;
import static java.lang.Thread.sleep;

class TextWindow extends Panel implements Runnable {

    public static Color backGColor = Color.CYAN;
    public static Color textColor = Color.black;
    public static String Text = "Java";
    public static int x = 250;
    public static int y = 75;
    private static int angle = 0;
    public static int rad = 10;


    public Panel getDrawPanel(){
        return this;
    }

    public void setActionListeners(){
        List[] arr = FontPanel.getFontLists();
        for (int i = 0; i < 3; i++)
            arr[i].addActionListener(e-> repaint());

        Choice choice = ColorPanel.getColorChoice();
        choice.addItemListener(e-> repaint());
        ColorPanel.getFontName().addItemListener(e -> repaint());
        ColorPanel.getTextPanel().addItemListener(e -> repaint());
        TextPanel.getTextField().addActionListener(e -> repaint());
        TextPanel.scrollHor.addAdjustmentListener(e -> {
            x = TextPanel.scrollHor.getValue();
            repaint();
        });
        TextPanel.scrollVert.addAdjustmentListener(e -> {
            y = TextPanel.scrollVert.getValue();
            repaint();
        });
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        setActionListeners();
        super.paint(g2d);
        g2d.setColor(backGColor);
        this.setBackground(backGColor);
        g2d.setColor(textColor);
        g2d.setFont(FontPanel.getFont());
        g2d.rotate(Math.toRadians(angle), 250-TextPanel.scrollHor.getValue(), 75-TextPanel.scrollVert.getValue());
        g2d.drawString(Text,300-x-TextPanel.scrollHor.getValue(),150-y-TextPanel.scrollVert.getValue());

    }
    @Override
    public void run(){
        ArrayList<Integer> yPos = new ArrayList<Integer>();
        ArrayList<Integer> yNeg = new ArrayList<Integer>();
        for(int x = -rad;x<=rad;x++){
            yPos.add((int)floor(sqrt(rad*rad-x*x)));
            yNeg.add((int)floor(sqrt(rad*rad-x*x))*-1);
        }
        Collections.reverse(yNeg);
        yPos.addAll(yNeg);

        while (true){
            int index = 0;
            for (int x = -rad; x<=rad; x++){
                TextWindow.x = x+rad;
                TextWindow.y = yPos.get(index)+40;
                index++;
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                angle = (angle + 10) % 360;
                repaint();
            }

            for (int x = rad; x>=-rad; x--){
                TextWindow.x = x+rad;
                TextWindow.y = yPos.get(index)+40;
                index++;
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                angle = (angle + 10) % 360;
                repaint();
            }
        }
    }
}

class TextPanel{
    public static Panel panel = new Panel();
    private static final TextField textField = new TextField();
    public static Scrollbar scrollVert = new Scrollbar(Scrollbar.VERTICAL,0,0,-250,250);
    public static Scrollbar scrollHor = new Scrollbar(Scrollbar.HORIZONTAL,0,0,-250,250);
    static {textField.setText("Java");}

    public static TextField getTextField() {
        return textField;
    }

    public static String getText(){return textField.getText();}

    public Panel getThird(){return panel;}

    public static void setTextPanel(){
        panel.setLayout(new BorderLayout());

        textField.addActionListener(e -> {
            if (ColorPanel.isFirstCheckbox)
                TextWindow.Text = textField.getText();
        });

        TextWindow aboba = new TextWindow();
        new Thread(aboba).start();

        panel.add(scrollVert, BorderLayout.WEST);
        panel.add(scrollHor, BorderLayout.SOUTH);
        panel.add(textField, BorderLayout.NORTH);
        panel.add(aboba.getDrawPanel(), BorderLayout.CENTER);
    }
}

