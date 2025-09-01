package reversi;


public class ReversiMain
{
	reversi.IModel model;
	reversi.IView view;
	reversi.IController controller;

	ReversiMain()
	{
		model = new reversi.SimpleModel();
		
		view = new reversi.GUIView();
		
		controller = new reversi.ReversiController();
		
		// Initialise everything...
		model.initialise(8, 8, view, controller);
		controller.initialise(model, view);
		view.initialise(model, controller);
		
		// Now start the game - set up the board
		controller.startup();
	}
	
	public static void main(String[] args)
	{
		new ReversiMain();
	}
}
