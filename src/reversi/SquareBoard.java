package reversi;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareBoard extends JButton implements ActionListener{
	
	reversi.IModel model;
	reversi.IController controller;
	
	int x;
	int y;
	int width;
	int height;
	int player;
	
	public SquareBoard(int row, int column, int l_width, int l_height, int l_player, reversi.IModel model, reversi.IController controller) {
		x = row;
		y = column;
		width = l_width;
		height = l_height;
		player = l_player;
		setMinimumSize( new Dimension(width, height) );
		setPreferredSize( new Dimension(width, height) );
		this.model = model;
		this.controller = controller;
		addActionListener(this);
		
	}
	
	public int get_x() {
		
		return x;
	}
	
	public int get_y() {
			
			return y;
	}

	@Override
	protected void paintComponent (Graphics g)
	{	
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.green);
		g.fillRect(0, 0, width-2, height-2);
		
		if (model.getBoardContents(x, y) != 0) {
			
			if (model.getBoardContents(x,y) == 1){
				g.setColor(Color.white);
				g.fillOval(1, 1, width -4, height -4);
				g.setColor(Color.black);
				g.drawOval(1, 1, width -4, height -4);
			}
				
			
			if (model.getBoardContents(x,y) == 2){
				g.setColor(Color.black);
				g.fillOval(1, 1, width -4, width -4);
				g.setColor(Color.white);
				g.drawOval(1, 1, width -4, height -4);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		controller.squareSelected(player, x, y);
	
	}

}
