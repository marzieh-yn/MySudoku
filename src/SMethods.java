import java.util.Random;

public class SMethods
{
	public static void start(byte[][] sudoku)
	{
		int count;
		for(count = 0; count < 729; count++)
			sudoku[count][0] = (byte) (1 + (count % 9));
	}
	
	public static void doSudoku(byte[][] sudoku, byte startStep)
	{
		Random generator = new Random(System.currentTimeMillis());
		byte step = startStep;
		int totalTries = 0;
		do
		{
			totalTries += 1;
			boolean noblanks = true;
			step = startStep;
		
	    	while((step < 81) && (noblanks))
			{
				byte number = (byte) generator.nextInt(9);
				byte position = (byte) generator.nextInt(81);
				step = SMethods.select(sudoku,number,position,step);

				boolean standalone = false;
				do
				{
					standalone = false;
					byte count;
					byte numberCount;
					for(count = 0; count < 81; count++)
					{
						byte zeroCount = 0;
						for(numberCount = 0; numberCount < 9; numberCount++)
						{
							if(sudoku[count * 9 + numberCount][step] == 0)
								zeroCount += 1;
							else
								number = (byte) (sudoku[count * 9 + numberCount][step] - 1);
							if(zeroCount == 9)
								noblanks = false;
						}
						if((zeroCount == 8) && (number < 10))
							{
								step = SMethods.select(sudoku,number,count,step);
								standalone = true;
							}
					}
				}
				while(standalone);
		
			}
	    MySudoku.step = step;
		}
		while((step != 81) && (totalTries < 500));

	}
	
	public static byte select(byte[][] sudoku, byte number, byte position, byte step)
	{
		if((sudoku[position*9 + number][step] == 0) || (sudoku[position*9 + number][step] > 9))
			return step;

		step += 1;
		int count = 0;
		for(count = 0; count < 729; count++)
			sudoku[count][step] = sudoku[count][step - 1];
		for(count = 0; count < 9; count++)
			sudoku[position*9 + count][step] = 0;

		byte row =   (byte) (position/9);
		for(count = 0; count < 9; count++)
			sudoku[row * 81 + count * 9 + number][step] = 0;

		byte column =   (byte) (position%9);
		for(count = 0; count < 9; count++)
			sudoku[column * 9 + count * 81 + number][step] = 0;

		int brow =  (position/27)*243;
		column = (byte) (((position%9)/3)*27);
		byte numberCount;
		for(numberCount = 0; numberCount < 3; numberCount++)
		{
			for(count = 0; count < 3; count++)
				sudoku[brow + column + count * 9 + numberCount * 81 + number ][step] = 0;
		}

		sudoku[position*9 + number][step] = (byte) (number + 11);
		return step;
	}
	
}

