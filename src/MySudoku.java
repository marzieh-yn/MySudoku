public class MySudoku {

    public static byte[][] sudoku = new byte[729][82];
    public static byte step = 0;
    final static byte border = 14;

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(ShowGUI::showGUI);
        SMethods.start(sudoku);
        SetBorder.borderPaint();
    }
}