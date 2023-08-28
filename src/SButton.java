import javax.swing.*;
import java.awt.*;

public class SButton extends JButton {
    Color BackgroundButton =new Color(211, 169, 169);
    public SButton(String action,String command){
        super(action);
        this.setFont(new Font("Serif",Font.BOLD,18));
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setBackground(BackgroundButton);
        this.setActionCommand(command);
    }
    public Dimension getPreferredSize(){
        return new Dimension(150,40);
    }
}
