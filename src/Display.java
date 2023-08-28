import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Display extends JPanel implements ActionListener {
    static int displayWidth = 557;
    static int displayHeight = 580;
    static int buttonWidth = 200;
    private final Color blackColorNumber = new Color(5, 5, 9);
    private final Color wrongEmptyNumber = new Color(143, 53, 143);
    private static final Color whiteBackGround = new Color(0xf5, 0xf5, 0xf5);

    public Display(){

        addMouseListener(new MouseAdapter() //we listen for mouse clicks on this panel
        {
            public void mousePressed(MouseEvent e)
            {
                SetNumbers.setNumber(e.getX(),e.getY());   //the called method on mouse click
                repaint(buttonWidth,0, displayWidth, displayHeight);
            }//end of mouse select
        });//end of mouse listener
        this.setLayout(new BorderLayout());

        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(buttonWidth,displayHeight));
        panelButton.setBackground(whiteBackGround);

        FlowLayout FLayout = new FlowLayout();
        FLayout.setHgap(50);
        FLayout.setVgap(50);
        panelButton.setLayout(FLayout);

        SButton SolvedSudoku = new SButton("Solve Sudoku",
                "SolvedSudoku");
        panelButton.add(SolvedSudoku);

        SButton UndoButton = new SButton("Undo",
                "Undo");
        UndoButton.addActionListener(this);
        panelButton.add(UndoButton);

        SButton EasyButton = new SButton("Easy",
                "Easy");
        EasyButton.addActionListener(this);
        panelButton.add(EasyButton);

        SButton MediumButton = new SButton("Medium",
                "Medium");
        MediumButton.addActionListener(this);
        panelButton.add(MediumButton);

        SButton HardButton = new SButton("Hard","Hard");
        HardButton.addActionListener(this);
        panelButton.add(HardButton);

        SButton CS = new SButton(" Custom Sudoku", "CS");
        CS.addActionListener(this);
        //        panelButton.add(CS);

        this.add(panelButton,BorderLayout.WEST);
    }



    public Dimension getPreferredSize()
    {
        return new Dimension(displayWidth + displayHeight, displayHeight);
    }

    protected void paintComponent(Graphics g)
    {
        final byte Foot = 24;
        final byte NumberX = 11;
        final byte NumberY = 54;
        final byte blankSize = 59;
        final byte pNumberX = 4;
        final byte pNumberY = 18;
        final byte pSNumberX = 20;
        final byte pSNumberY = 19;
        final int FootMessageX = 96;
        final int FootMessageY = 574;
        final int FootNumberX = 211;
        final int FootNumberY = 574;
        //Grid lines for the display
        int[] BigLines = {0, 184, 369, 554, 577};
        int[] SmallLines = {62, 123, 247, 308, 432, 493};
        int[] NumberPosition = {3,63,124,187,248,309,372,433,494};
        Font fontSelected = new Font("Serif", Font.BOLD, 50);
        Font fontFoot = new Font("Serif", Font.PLAIN, 20);
        Font fontPencil = new Font("Serif", Font.PLAIN, 20);

        super.paintComponent(g);
        g.setColor(blackColorNumber);
        g.setFont(fontPencil);


        //horizontal lines
        byte count;
        for(count = 0; count < 5; count++)
            g.fillRect(0, BigLines[count], displayWidth + buttonWidth, 3);
        for(count = 0; count < 6; count++)
            g.drawLine(0, SmallLines[count], displayWidth + buttonWidth, SmallLines[count]);
        //vertical lines
        g.fillRect(BigLines[0] + buttonWidth, 0, 3, displayHeight);
        g.fillRect(BigLines[1] + buttonWidth, 0, 3, displayHeight - Foot);
        g.fillRect(BigLines[2] + buttonWidth, 0, 3, displayHeight - Foot);
        g.fillRect(BigLines[3] + buttonWidth, 0, 3, displayHeight);
        for(count = 0; count < 6; count++)
            g.drawLine(SmallLines[count] + buttonWidth, 0, SmallLines[count] + buttonWidth, displayHeight -Foot);
        //foot text
        g.setFont(fontFoot);
        g.drawString(" Step No: ", FootMessageX + buttonWidth, FootMessageY);
        g.drawString(String.valueOf(MySudoku.step), FootNumberX + buttonWidth, FootNumberY);
        byte numbercount;
        for(numbercount = 0; numbercount < 81; numbercount++)
        {
            g.setColor(blackColorNumber);
            byte zeros = 0;
            byte outercount;
            for(outercount = 0; outercount < 3; outercount++)
            {
                for(count = 0; count < 3; count++)
                {
                    byte pencilnumber = MySudoku.sudoku[count + outercount*3 + numbercount*9][ MySudoku.step];
                    if(pencilnumber > 0)
                    {
                        if(pencilnumber < 10)
                        {
                            g.setFont(fontPencil);
                            g.drawString(String.valueOf(pencilnumber ), NumberPosition[numbercount%9] + (count*pSNumberX) + pNumberX + buttonWidth, NumberPosition[numbercount/9] + outercount*pSNumberY + pNumberY);
                        }
                        else
                        {
                            g.setFont(fontSelected);
                            g.drawString(String.valueOf(pencilnumber - 10), NumberPosition[numbercount%9] + buttonWidth + NumberX, NumberPosition[numbercount/9] + NumberY);
                        }
                    }
                    else
                        zeros += 1;
                }
            }
            if(zeros == 9)
            {
                g.setColor(wrongEmptyNumber);
                g.fillRect(NumberPosition[numbercount%9] + buttonWidth, NumberPosition[numbercount/9], blankSize, blankSize);
            }
        }
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("CS"))
            MySudoku.step = 0;
        else if(e.getActionCommand().equals("Hard"))
        {
            SMethods.doSudoku(MySudoku.sudoku, (byte) 0);
            MySudoku.step = 25;
        }
        else if(e.getActionCommand().equals("Medium"))
        {
            SMethods.doSudoku(MySudoku.sudoku, (byte) 0);
            MySudoku.step = 35;
        }
        else if(e.getActionCommand().equals("Easy"))
        {
            SMethods.doSudoku(MySudoku.sudoku, (byte) 0);
            MySudoku.step = 45;
        }
        else if(e.getActionCommand().equals("SolvedSudoku"))
        {
            SMethods.doSudoku(MySudoku.sudoku, MySudoku.step);
        }
        else if(e.getActionCommand().equals("Undo"))
        {
            if(MySudoku.step > 0)
                MySudoku.step -= 1;
        }

        repaint(buttonWidth,0, displayWidth, displayHeight);
    }
}
