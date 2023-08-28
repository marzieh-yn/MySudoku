import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;

public class SPanel extends Panel {
    Color LightPink = new Color(243, 240, 240);
    public SPanel(Dimension set)
    {
        super();
        this.setBackground(LightPink);
        this.setPreferredSize(set);
    }
}
