import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SetBorder{
    private static final int WindowWidth = 785;
    private static final int WindowHeight = 650;
    protected static void borderPaint() {
        JFrame f = new JFrame("MySudoku");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("sudoku.png"));
        } catch (IOException ignored) {
        }
        f.setResizable(false);
        f.setIconImage(image);
        f.setSize(WindowWidth, WindowHeight);
//        f.setLocation(0, 0);
        f.setLayout(new BorderLayout());

        f.add(new SPanel(new Dimension(WindowWidth, MySudoku.border)),  BorderLayout.NORTH);
        f.add(new SPanel(new Dimension(WindowWidth, MySudoku.border)),  BorderLayout.SOUTH);
        f.add(new SPanel(new Dimension(MySudoku.border,WindowHeight)),   BorderLayout.EAST);
        f.add(new SPanel(new Dimension(0,WindowHeight)),   BorderLayout.WEST);

        Display dp =new  Display();
        dp.setBackground(Color.WHITE);
        f.add(dp, BorderLayout.CENTER);

        f.setVisible(true);
    }
}
