package reversi;

public class ReversiController implements reversi.IController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	reversi.IModel model;
	reversi.IView view;
	int pressed = 1;
	

	@Override
	public void initialise(reversi.IModel model, reversi.IView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void startup() {

		int width = model.getBoardWidth();
		int height = model.getBoardHeight();
		for ( int x = 0 ; x < width ; x++ )
			for ( int y = 0 ; y < height ; y++ )
				model.setBoardContents(x, y, 0);
		model.setBoardContents(3,3,1);
		model.setBoardContents(4,3,2);
		model.setBoardContents(3,4,2);
		model.setBoardContents(4,4,1);
		
		model.setFinished(false);
		
		model.setPlayer(1);
		view.feedbackToUser(1, "White player - choose where to put your piece");
		view.feedbackToUser(2, "Black player - not your turn");
		view.refreshView();
		
		pressed = 1;

	}
	
	@Override
	public void update() {
		
		boolean player1;
		boolean player2;
		
		
		view.refreshView();
		
		player1 = movesLeft(1);
		player2 = movesLeft(2);
		
		
		if (player1 == false && player2 == false) {
			model.setFinished(true);
			int white = 0;
			int black = 0;
			for (int temp_y = 0; temp_y< model.getBoardWidth(); temp_y++) {
				for (int temp_x = 0; temp_x<model.getBoardHeight(); temp_x++) {
					if(model.getBoardContents(temp_x, temp_y) == 1) {
						white++;
					}
					else if(model.getBoardContents(temp_x, temp_y) == 2) {
						black++;
					}	
				}
			}
			if (white > black) {
				view.feedbackToUser(1, "White won. White "+white+" to Black "+black+". Reset the game to replay." );
				view.feedbackToUser(2, "White won. White "+white+" to Black "+black+". Reset the game to replay." );
				view.refreshView();
			}
			else if (black > white) {
				view.feedbackToUser(1, "Black won. Black "+black+" to White "+white+". Reset the game to replay." );
				view.feedbackToUser(2, "Black won. Black "+black+" to White "+white+". Reset the game to replay." );
				view.refreshView();
			}
			else {
				view.feedbackToUser(1, "Draw. Both players ended with "+white+" pieces. Reset the game to replay.");
				view.feedbackToUser(2, "Draw. Both players ended with "+black+" pieces. Reset the game to replay." );
				view.refreshView();
			}
			
		}
		else if(player1 == false && player2 == true) {
			model.setFinished(false);
			model.setPlayer(2);
			view.feedbackToUser(2, "Black player - choose where to put your piece");
			view.feedbackToUser(1, "White player - not your turn");
			view.refreshView();
		}
		else if(player2 == false && player1 == true) {
			model.setFinished(false);
			model.setPlayer(1);
			view.feedbackToUser(1, "White player - choose where to put your piece");
			view.feedbackToUser(2, "Black player - not your turn");
			view.refreshView();
		}
		else if(player2 == true && player1 == true){
			model.setFinished(false);
			if (pressed == 0) {
				if (model.getPlayer() == 1) {
					model.setPlayer(2);
					view.feedbackToUser(2, "Black player - choose where to put your piece");
					view.feedbackToUser(1, "White player - not your turn");
					view.refreshView();
				
				}
				else {
					model.setPlayer(1);
					view.feedbackToUser(1, "White player - choose where to put your piece");
					view.feedbackToUser(2, "Black player - not your turn");
					view.refreshView();
				}
			}
			else if (pressed == 1) {
				if (model.getPlayer() == 1) {
					view.feedbackToUser(1, "White player - choose where to put your piece");
					view.feedbackToUser(2, "Black player - not your turn");
					view.refreshView();
				
				}
				else {
					view.feedbackToUser(2, "Black player - choose where to put your piece");
					view.feedbackToUser(1, "White player - not your turn");
					view.refreshView();
				}
			}
		}
		pressed = 1;
		
		
	}

	@Override
	public void squareSelected(int player, int x, int y) {
		
		
		if ( model.hasFinished() )
			{
				return; 
			}
		if (model.getPlayer() != player) {
			view.feedbackToUser(player, "It is not your turn!");
			return;
		}
		
		if (isValidMove(x,y, player) == 0) {
			return;
		}
		moveValidMove(x,y, player);
		model.setBoardContents(x, y, player);
		
		pressed = 0;
		update();
		
		
	}

	@Override
	public void doAutomatedMove(int player) {
		
		int temp_x = 0;
		int temp_y = 0;
		int total = 0;
		int result = 0;
		
		if (model.getPlayer() != player) {
			view.feedbackToUser(player, "It is not your turn!");
			return;
		}
		
		for(int y=0; y<model.getBoardHeight(); y++) {
			for(int x=0; x<model.getBoardWidth();x++) {
				result = isValidMove(x,y,player);
				if(result >total) {
					total = result;
					temp_x = x;
					temp_y = y;
				}
			}
		}
		squareSelected(player, temp_x, temp_y);
	}
	
	public boolean movesLeft(int player) {
		
		int result = 0;
		int total = 0;
		for (int y = 0; y< model.getBoardHeight(); y++) {
			for (int x = 0; x<model.getBoardWidth(); x++) {
				result = isValidMove(x,y,player);
				if (result == 0) {
					total = total + 1;
					
				}
			}		
		}
		if (total == 64) {
			return false;
		}
		return true;
	}
	
	public boolean isOnBoard(int x, int y) {
		
		if (x>=0 && x<=7 ) {
			if (y>=0 && y<=7) {
				return true;
			}
		}
		return false;
	}
	
	public int isValidMove(int x, int y, int player) {
		
		int button = 0;
		int opposite = 0;
		
		if ( model.getBoardContents(x,y) != 0) {
			return 0; 
		}
		
		model.setBoardContents(x, y, player);
		button = model.getBoardContents(x,y);
		
		if (button == 1) {
			opposite = 2;
		}
		else {
			opposite = 1;
		}
		
		int buttonsToChange = 0;
		
		for (int offsetx = -1; offsetx <=1; offsetx++) {
			for (int offsety = -1; offsety <=1; offsety++) {
				if (offsetx == 0 && offsety == 0 ) {
					continue;
				}
				int temp_x = x;
				int temp_y = y;
				
				
				temp_x = temp_x + offsetx;
				temp_y = temp_y + offsety;
				
				
				
				if (isOnBoard(temp_x,temp_y) && (model.getBoardContents(temp_x,  temp_y)== opposite)){
					temp_x = temp_x + offsetx;
					temp_y = temp_y + offsety;
					
					if (!(isOnBoard(temp_x, temp_y))) {
						continue;
					}
					
					while (model.getBoardContents(temp_x, temp_y) == opposite) {
						temp_x = temp_x + offsetx;
						temp_y = temp_y + offsety;
						if (!(isOnBoard(temp_x, temp_y))) {
							break;
						}
					}
					
					if (!(isOnBoard(temp_x, temp_y))) {
						continue;
					}
					
					if (model.getBoardContents(temp_x, temp_y) == button) {
						while (true) {
							buttonsToChange++;
							temp_x = temp_x - offsetx;
							temp_y = temp_y - offsety;
							if (temp_x == x && temp_y ==y) {
								break;
							}
							buttonsToChange++;
						}
					}
				}
				
			}
		}
		model.setBoardContents(x, y, 0);
		return buttonsToChange;
	}
			
	public void moveValidMove(int x, int y, int player) {
				
		int button = 0;
		int opposite = 0;
		
		if ( model.getBoardContents(x,y) != 0) {
			return; 
		}
		
		model.setBoardContents(x, y, player);
		button = model.getBoardContents(x,y);
				
		if (button == 1) {
			opposite = 2;
		}
		else {
			opposite = 1;
		}
				
			
		for (int offsetx = -1; offsetx <=1; offsetx++) {
			for (int offsety = -1; offsety <=1; offsety++) {
				int temp_x = x;
				int temp_y = y;
						
				temp_x = temp_x + offsetx;
				temp_y = temp_y + offsety;
						
				if (isOnBoard(temp_x,temp_y) && (model.getBoardContents(temp_x,  temp_y)== opposite)){
					temp_x = temp_x + offsetx;
					temp_y = temp_y + offsety;
							
					if (!(isOnBoard(temp_x, temp_y))) {
						continue;
					}
							
					while (model.getBoardContents(temp_x, temp_y) == opposite) {
						temp_x = temp_x + offsetx;
						temp_y = temp_y + offsety;
						if (!(isOnBoard(temp_x, temp_y))) {
							break;
						}
					}
							
					if (!(isOnBoard(temp_x, temp_y))) {
						continue;
					}
							
					if (model.getBoardContents(temp_x, temp_y) == button) {
						while (true) {
							model.setBoardContents(temp_x, temp_y, player);
							temp_x = temp_x - offsetx;
							temp_y = temp_y - offsety;
							if (temp_x == x && temp_y ==y) {
								break;
							}
						}
					}
				}
						
			}			
		}
	}
}
