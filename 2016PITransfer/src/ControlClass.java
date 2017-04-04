



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;


/**
 *
 * @author IMACS Curriculum Development Group
 * @version 2.0 January 14, 2015
 */
public class ControlClass extends JPanel implements ActionListener
{
    /** This TurtleController's program display area */
    private JTextArea myProgramDisplay;
    /** This TurtleController's program */
    private JFrame frame6;
    private Panel panel6;
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JSlider slider4;
    private JSlider slider5;
    private JSlider slider6;
    
    private JButton newButton;
    private JButton incButton;
    private JButton decButton;
    
    private ValueSetter accessor;
    /**
     * Class constructor
     */
    public ControlClass()
    {
    	
//        setLayout( new FlowLayout() );
//        setBorder( BorderFactory.createRaisedBevelBorder() );
//        setBackground( Color.gray );
//        setPreferredSize( new Dimension( 180, 350 ) );
        initialize();
    }
    
    /**
     * Overrides ActionListener's actionPerformed method
     * 
     * @param e  the event provoking an action to be performed
     */
    public void actionPerformed( ActionEvent e )
    {
      String actionName = e.getActionCommand();

      if ( "Set Values".equals( actionName ) )
      {
	    String input = getInput( "H MIN: " + FRCTrial.h_min + ", H MAX: " + FRCTrial.h_max + 
	    		", S MIN: " + FRCTrial.s_min + ", S MAX: " + FRCTrial.s_max 
	    		+ ", V MIN: " + FRCTrial.v_min + ", V MAX: " + FRCTrial.v_max
	    		+ "\nEnter 'yes' to save values.");
	    input = input.toLowerCase();
	    if (input.equals("yes") || input.equals("y")){
	    	System.out.println("Values Were Saved");
	    	accessor.writeVals();
//	    	accessor.readVals();
	    }
      }
      else if ( "Inc Frame".equals( actionName ) )
      {
    	  FRCTrial.frameNum++;
    	  System.out.println(FRCTrial.frameNum);
      }
      else if ( "Dec Frame".equals( actionName ) )
      {
    	  FRCTrial.frameNum--;
    	  System.out.println(FRCTrial.frameNum);
      }
      else
      {
        JOptionPane.showMessageDialog( this, actionName );
      }
      
   
//      executeProgram();
    }
    
    private String getInput( String prompt )
    {
      return JOptionPane.showInputDialog( this, prompt );
    }
    
    /**
     * The class initializer
     */
    private void initialize()
    {
    	accessor = new ValueSetter();
    	accessor.readVals();
    	frame6 = new JFrame("HSV Value Sliders");
		frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame6.setSize(640,480);
		frame6.setBounds(0, 0, 300, 350);
		panel6 = new Panel();
		
		slider1 = new JSlider();
		slider1.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 500));
        slider1.setMajorTickSpacing(5);
        slider1.setPaintTicks(true);
        slider1.setSize(0, 255);
        slider1.setValue(FRCTrial.h_min*100/255);
        slider1.setVisible(true);
        
        slider2 = new JSlider();
		slider2.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 500));
        slider2.setMajorTickSpacing(5);
        slider2.setPaintTicks(true);
        slider2.setSize(0, 255);
        slider2.setValue(FRCTrial.h_max*100/255);
        slider2.setVisible(true);
        
        slider3 = new JSlider();
		slider3.setLayout(new FlowLayout(FlowLayout.CENTER, 250, 500));
        slider3.setMajorTickSpacing(5);
        slider3.setPaintTicks(true);
        slider3.setSize(0, 255);
        slider3.setValue(FRCTrial.s_min*100/255);
        slider3.setVisible(true);
        
        slider4 = new JSlider();
		slider4.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 500));
        slider4.setMajorTickSpacing(5);
        slider4.setPaintTicks(true);
        slider4.setSize(0, 255);
        slider4.setValue(FRCTrial.s_max*100/255);
        slider4.setVisible(true);
        
        slider5 = new JSlider();
		slider5.setLayout(new FlowLayout(FlowLayout.CENTER, 400, 500));
        slider5.setMajorTickSpacing(5);
        slider5.setPaintTicks(true);
        slider5.setSize(0, 255);
        slider5.setValue(FRCTrial.v_min*100/255);
        slider5.setVisible(true);
        
        slider6 = new JSlider();
		slider6.setLayout(new FlowLayout(FlowLayout.CENTER, 450, 500));
        slider6.setMajorTickSpacing(5);
        slider6.setPaintTicks(true);
        slider6.setSize(0, 255);
        slider6.setValue(FRCTrial.v_max*100/255);
        slider6.setVisible(true);        
        
        newButton = new JButton( "Set Values" );
        newButton.setBounds(0, 600, 40, 20);
        newButton.setActionCommand( "Set Values" );
        newButton.addActionListener( this );
        
        incButton = new JButton( "Inc Frame" );
        incButton.setBounds(0, 600, 40, 20);
        incButton.setActionCommand( "Inc Frame" );
        incButton.addActionListener( this );
        
        decButton = new JButton( "Dec Frame" );
        decButton.setBounds(0, 600, 40, 20);
        decButton.setActionCommand( "Dec Frame" );
        decButton.addActionListener( this );
        
        panel6.add(slider1);
        panel6.add(slider2);
        panel6.add(slider3);
        panel6.add(slider4);
        panel6.add(slider5);
        panel6.add(slider6);
        
        panel6.add( newButton );
        panel6.add( incButton );
        panel6.add( decButton );
        
        
        frame6.setContentPane(panel6);
	    frame6.setVisible(true);
        
    }
    public void updateStatus(){
    	FRCTrial.h_min = slider1.getValue()*255/100;
    	FRCTrial.h_max = slider2.getValue()*255/100;
    	FRCTrial.s_min = slider3.getValue()*255/100;
    	FRCTrial.s_max = slider4.getValue()*255/100;
    	FRCTrial.v_min = slider5.getValue()*255/100;
    	FRCTrial.v_max = slider6.getValue()*255/100;
    }
}
