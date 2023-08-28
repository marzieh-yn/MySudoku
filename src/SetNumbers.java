public class SetNumbers extends Display{
    private static byte count;
    private static byte XPosition = 0;
    private static byte YPosition = 0;
    private static byte XNumber = 0; //the number in x position 123 or 456 or 789
    private static byte YNumber = 0;

    public static void setNumber(int x, int y){
        int[] NumberPosition = {3,63,124,187,248,309,372,433,494};
        final byte pSNumberY = 19;
        if( x < buttonWidth + NumberPosition[0])
            return;

        x -= buttonWidth - NumberPosition[0];

        var newXposition = XYPosition(XPosition,x,NumberPosition);
        var newYposition = XYPosition(YPosition,y,NumberPosition);

        byte position = (byte) (newXposition + newYposition*9);

        x -=  NumberPosition[newXposition];
        var newXnumber = XYNumber(XNumber,x,pSNumberY);

        y -=  NumberPosition[newYposition];
        var newYnumber = XYNumber(YNumber,y,pSNumberY);


        byte number = (byte) (newXnumber + newYnumber*3);

        MySudoku.step = SMethods.select(MySudoku.sudoku, number, position, MySudoku.step);


    }
    public static byte XYPosition(byte position, int xY, int[] NumberPosition){

        for(count = 0; count < 9; count++)
            if(xY > NumberPosition[count])
                position = count;

        return position;
    }
    public static byte XYNumber(byte number, int xY, int pSNumberY){

        for(count = 0; count < 3; count++)
            if(xY >  pSNumberY*count)
                number = count;

        return number;
    }
}
