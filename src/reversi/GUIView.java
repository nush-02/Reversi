package reversi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



public class GUIView implements reversi.IView, ActionListener{
	
	 reversi.IModel model;
	 reversi.IController controller;

	JFrame frame1 = new JFrame();
	JFrame frame2 = new JFrame();
	JLabel message1 = new JLabel();
	JLabel message2 = new JLabel();
	JPanel Panel1_1 = new JPanel();
	JPanel Panel2_1 = new JPanel();
	JButton button1_1 = new JButton("Greedy AI (play white)");
	JButton button1_2 = new JButton("Restart");
	JButton button2_1 = new JButton("Greedy AI (play black)");
	JButton button2_2 = new JButton("Restart");
	JPanel[] arrayButtons1 = new JPanel[64];
	JButton[] arrayButtons2 = new JButton[64];
	
	

	public static void main(String[] args) {
		
		GUIView guiView = new GUIView();
		guiView.initialise(guiView.model, guiView.controller);

	}
	

	@Override
	public void initialise(reversi.IModel model, reversi.IController controller) {
		
		this.model = model;
		this.controller = controller;
		
		
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame1.setTitle("Reversi - white player");
		frame1.setLocationRelativeTo(null); // centre on screen
		
		frame1.getContentPane().setLayout(new BorderLayout(8,8));
		
		
		message1.setFont( new Font( "Arial", Font.BOLD, 12 ));
		message1.setText("Initial text goes here");
		frame1.add(message1,BorderLayout.NORTH);
		
		Panel1_1.setLayout(new GridLayout(8,8));
		Panel1_1.setPreferredSize(new Dimension(400,400));
		frame1.getContentPane().add(Panel1_1,BorderLayout.CENTER);
		
		int i = 0;
		int width = model.getBoardWidth();
		int height = model.getBoardHeight();
		
		for (int y = 0; y<height; y++) {
			for (int x = 0; x<width; x++) {
				arrayButtons1[i] = new JPanel();
				arrayButtons1[i].setLayout(new BorderLayout(1,1));
				arrayButtons1[i].setBorder(new EmptyBorder(0, 0, 0, 0));
				reversi.SquareBoard piece = new reversi.SquareBoard(x,y,50,50,1, model, controller);
				arrayButtons1[i].add(piece, BorderLayout.CENTER);
				Panel1_1.add(arrayButtons1[i]);
				frame1.repaint();
				i++;
			}
		}
				
		JPanel Panel1_2 = new JPanel();
		Panel1_2.setLayout(new GridLayout(2,1));
		frame1.add(Panel1_2,BorderLayout.SOUTH);
		
		
		Panel1_2.add(button1_1);
		button1_1.addActionListener(this);
		
		Panel1_2.add(button1_2);
		button1_2.addActionListener(this);
		
		
		
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame2.setTitle("Reversi - black player");
		frame2.setLocationRelativeTo(null); // centre on screen
		frame2.getContentPane().setLayout(new BorderLayout(8,8));
		
		message2.setFont( new Font( "Arial", Font.BOLD, 12 ));
		message2.setText("Initial text goes here");
		frame2.add(message2,BorderLayout.NORTH);
		
		Panel2_1.setLayout(new GridLayout(8,8));
		Panel2_1.setPreferredSize(new Dimension(400,400));
		frame2.add(Panel2_1,BorderLayout.CENTER);
	
		i = 63;	
		for (int y = height-1; y>= 0; y--) {
			for (int x = width -1; x>=0; x--) {
				arrayButtons2[i] = new JButton();
				arrayButtons2[i].setLayout(new BorderLayout(1,1));
				arrayButtons2[i].setBorder(new EmptyBorder(0, 0, 0, 0));
				reversi.SquareBoard piece = new reversi.SquareBoard(x,y,50,50,2, model, controller);
				arrayButtons2[i].add(piece, BorderLayout.CENTER);
				Panel2_1.add(arrayButtons2[i]);
				frame2.repaint();
				i--;
			}		
		}
		
		JPanel Panel2_2 = new JPanel();
		Panel2_2.setLayout(new GridLayout(2,1));
		frame2.add(Panel2_2,BorderLayout.SOUTH);
		
		
		Panel2_2.add(button2_1);
		button2_1.addActionListener(this);
		
		Panel2_2.add(button2_2);
		button2_2.addActionListener(this);
		
		
		frame1.setSize(400,500);
		frame1.pack();
		frame1.setVisible(true);
		
		frame2.setSize(520,600);
		frame2.pack();
		frame2.setVisible(true);
				
	}
	

	
	@Override
	public void refreshView() {
		
		frame1.repaint();
		frame2.repaint();
		
		
		
	}

	@Override
	public void feedbackToUser(int player, String message) {
		// TODO Auto-generated method stub
		if ( player == 1 )
			message1.setText(message);
		else if ( player == 2 )
			message2.setText(message);
		
		System.out.println("\nMessage to player " + player + ":\n" + message + "\n" );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button1_2 || e.getSource() == button2_2) {
			controller.startup();
		}
		
		else if (e.getSource() == button1_1 ){
			controller.doAutomatedMove(1);
		}
		else if ( e.getSource() == button2_1){
			controller.doAutomatedMove(2);
		}
	}

}
