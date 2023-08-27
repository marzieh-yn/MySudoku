import javax.swing.*;
import java.awt.*;

public class SButton extends JButton {
    private static final long serialVersionUID = 1L;

    Color FontColor = new Color(101, 26, 58);
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
        return new Dimension(180,40);
    }
}
