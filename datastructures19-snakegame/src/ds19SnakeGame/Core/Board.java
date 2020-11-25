/*
* Γρηγόριος Παυλάκης - ΑΕΜ 9571 (τηλ. 697558826) <grigpavl@ece.auth.gr>
* Χάρης Φίλης - ΑΕΜ 9449 (τηλ. 6972434854) <charisfilis@ece.auth.gr>
*/

package ds19SnakeGame.Core;

public class Board 
{
    private int N, M;          //N: rows, M: columns
    private int[][] tiles;     //tile IDs are stored here
    private Snake[] snakes;    //all snakes are stored here
    private Ladder[] ladders;  //ladders
    private Apple[] apples;    //apples
	
    public Board()
    {
		setN(0);
		setM(0);
		tiles = new int[1][1];
		snakes = new Snake[1];
		ladders = new Ladder[1];
		apples = new Apple[1];
		createBoard();

	}

	public Board(int n, int m, int numberOfApples, int numberOfLadders, int numberOfSnakes) 
	{
		setN(n);
		setM(m);
		apples = new Apple[numberOfApples];
		snakes = new Snake[numberOfSnakes];
		ladders = new Ladder[numberOfLadders];
		tiles = new int[n][m];
		
		createBoard();
	}

	//Getters
	public int getN() 
	{
		return N;
	}

	public int getM() 
	{
		return M;
	}

	public int[][] getTiles() 
	{
		return tiles;
	}

	public Snake[] getSnakes() 
	{
		return snakes;
	}

	public Ladder[] getLadders() 
	{
		return ladders;
	}

	public Apple[] getApples() 
	{
		return apples;
	}

	//Setters
	public void setN(int N)
	{
		this.N = N;
	}

	public void setM(int M) 
	{
		this.M = M;
	}

	public void setTiles(int[][] tiles) 
	{
		this.tiles = tiles;
	}

	public void setSnakes(Snake[] snakes) 
	{
		this.snakes = snakes;
	}

	public void setLadders(Ladder[] ladders) 
	{
		this.ladders = ladders;
	}

	public void setApples(Apple[] apples) 
	{
		this.apples = apples;
	}
	
	
	//Actual methods
	public void createBoard()
	{
		//Sequence of calling matters here! Do not change!
		initialize(tiles);
		initialize(snakes);
		initialize(ladders);
		initialize(apples);
	}
	
	public void createElementBoard()
	{
		String[][] elementBoardSnakes = new String[N][M];
		String[][] elementBoardLadders = new String[N][M];
		String[][] elementBoardApples = new String[N][M];
		
		for (int p = 0; p < N; p++)
		{
			for (int q = 0; q < M; q++)
			{
				elementBoardSnakes[p][q] = "___";
				elementBoardLadders[p][q] = "___";
				elementBoardApples[p][q] = "___";
			}
		}
			
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < M; j++)
			{
				for (int k = 0; k < snakes.length; k++)
				{
					if (tiles[i][j] == snakes[k].getHeadId())
		      		{
						elementBoardSnakes[i][j] = ("SH" + snakes[k].getSnakeId());            // printing SH in a snakeHead tile
					}
					
					if (tiles[i][j] == snakes[k].getTailId())
					{
						elementBoardSnakes[i][j] = ("ST" + snakes[k].getSnakeId());            //printing ST in a snakeTail tile
					}
				}
				
				for (int k = 0; k < ladders.length; k++)
				{
					if (tiles[i][j] == ladders[k].getUpStepId())
					{
						elementBoardLadders[i][j] = ("LU" + ladders[k].getLadderId());         //printing LU in a ladder's upStep part tile
					}
					
					if (tiles[i][j] == ladders[k].getDownStepId())
					{
						elementBoardLadders[i][j] = ("LD" + ladders[k].getLadderId());         //printing LD in a ladder's downStep part tile
 					}
				}
				
				for (int k = 0; k < apples.length; k++)
				{
					if (tiles[i][j] == apples[k].getAppleTileId() && apples[k].getColor() == "Red")
					{
						elementBoardApples[i][j] = ("AR" + apples[k].getAppleId());            //printing AR for a red apple
					}
					
					if (tiles[i][j] == apples[k].getAppleTileId() && apples[k].getColor() == "Black")
					{
						elementBoardApples[i][j] = ("AB" + apples[k].getAppleId());            // printing AB for a black apple
					}
				}
			}
		}
		//Printing the matrices
		System.out.println("\n\t\tSnakes: ");

		for (int i = 0; i < N; i++)
    	{
			System.out.printf("Row %d: ", N - 1 -i);
			for (int j = 0; j < M; j++)
			{
				System.out.printf("%s ", elementBoardSnakes[N - 1 - i][j]);
			}
			System.out.println("");
    	}
		
		System.out.println("\n\t\tLadders: ");
			
		for (int i = 0; i < N; i++)
    	{
			System.out.printf("Row %d: ", N - 1 -i);
			for (int j = 0; j < M; j++)
			{
				System.out.printf("%s ", elementBoardLadders[N - 1 - i][j]);
			}
			System.out.println("");
    	}
		
		System.out.println("\n\t\tApples: ");
		
		for (int i = 0; i < N; i++)
    	{
			System.out.printf("Row %d: ", N - 1 -i);
			for (int j = 0; j < M; j++)
			{
				System.out.printf("%s ", elementBoardApples[N - 1 - i][j]);
			}
			System.out.println("");
    	}
		
		System.out.println("");
		
	}
	
	
	//Private functions for better code organization - This is a helper function
	private void initialize(int[][] tiles)
	{
		/*
		 * This function initializes the tile IDs according to the 
		 * instruction given in the assignment (ascending-descending order alternatively at each row).
		 * Tile ID 1 is given to tiles[0][0].
		 */
		int rowCounter = 0;
		int lastTileId = 1;    //the last tile ID before a row change
		
		for (int i = 0; i < N; i++)
		{
			if (rowCounter % 2 == 0)
			{
				for (int j = 0; j < M; j++)
				{
					tiles[i][j] = lastTileId++;  //playing with the ++ operator priority here
				}
			}
			else
			{
				for (int j = M - 1; j >= 0; j--)
				{
					tiles[i][j] = lastTileId++;
				}
			}
			rowCounter++;
		}
	}
		
	private void initialize(Snake[] snakes)
	{
		int headId = 0, tailId = 0;
		
		for (int i = 0; i < snakes.length; i++)
		{
			do
			{
				headId = (int) (Math.random() * (N * M));  //possible values are from 1 to NxM
				tailId = (int) (Math.random() * (N * M));
			}
			while ((Math.abs(headId - tailId) <= M) || (headId < tailId));
			//this ensures that the chosen IDs will be at least M places apart (not in the same row)
			
		    snakes[i] = new Snake(i, headId, tailId);
		}
	}

	private void initialize(Ladder[] ladders)
	{
		int topId = 0, bottomId = 0;

		int[] snakeHeads = new int[snakes.length];  //snake head IDs here
		int[] snakeTails = new int[snakes.length];  //tail IDs

		boolean snakePartExists;

		for (int i = 0; i < snakes.length; i++)
		{
			snakeHeads[i] = snakes[i].getHeadId();
			snakeTails[i] = snakes[i].getTailId();
		}

		for (int i = 0; i < ladders.length; i++)
		{
			do
			{
				topId = (int) (Math.random() * (N * M));  //possible values are from 1 to NxM
				bottomId = (int) (Math.random() * (N * M));
				snakePartExists = false;    //whether a snake part has been detected or not

				//this ensures that the ladder ends are never placed on snake ends
				for (int j = 0; j < snakes.length; j++)
				{
					if (topId == snakeHeads[j] || topId == snakeTails[j] || bottomId == snakeHeads[j] || bottomId == snakeTails[j])
					{
						snakePartExists = true;      //boolean value that indicates if we have a conflict between a snake part and a ladder part
						break;
					}
				}

			}
			while ((Math.abs(topId - bottomId) <= M) || (topId < bottomId) || snakePartExists == true);//this ensures that the chosen IDs will be at least M places apart (not in the same row),the top part of a ladder will always be higher and the placement of a ladder part,will never be on the same tile as a snake head or tail

			ladders[i] = new Ladder(i, topId, bottomId, false);
		}
	}

	/*
	 *This function initializes apple array
	 * It puts red or black apple randomly using a double random generator from Math library
	 * Then with a check we create either a black either a red apple
	 */
	private void initialize(Apple[] apples)
	{		
		for (int i = 0; i < apples.length; i++)
		{
			int appleTileId = (int) (Math.random() * (N * M));

			double redOrBlack = Math.random();
			if (redOrBlack >= 0.5)
			{
				apples[i] = new Apple(i, appleTileId, 5, "Red");
			}
			else
			{
				apples[i] = new Apple(i, appleTileId, -5, "Black");
			}
			
		}
	}
}
