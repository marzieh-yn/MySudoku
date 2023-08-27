import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;

public class SPanel extends Panel {
    private static final long serialVersionUID = 1L;
    Color LightPink = new Color(243, 240, 240);
    public SPanel(Dimension set)
    {
        super();
        this.setBackground(LightPink);
        this.setPreferredSize(set);
    }
}
