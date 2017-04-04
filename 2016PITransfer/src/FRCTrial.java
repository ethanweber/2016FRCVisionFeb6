
// Note: Ethan took out everything related to network tables in this version.

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javafx.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;


import java.util.logging.Level;
import java.util.logging.Logger;





import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.MatOfPoint;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

//import com.sun.javafx.Utils;

public class FRCTrial extends JPanel{
	
//	private BufferedImage image;
	public static int h_min = 0;
	public static int h_max = 255;
	public static int s_min = 0;
	public static int s_max = 255;
	public static int v_min = 0;
	public static int v_max = 255;
	
	public static int frameNum = 261;
	
	public static void main (String args[]){
		
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		System.load("opencv_java300.dll".getPath());
//		System.load(dllFile.getPath());
//		System.out.println(System.getProperty("user.dir"));
//		System.load("C:/opencv/build/java/x64/opencv_java300.dll");
//		System.load("C:/Users/Ethan/Desktop/opencv_java300.so");
		System.load(System.getProperty("user.dir") + "/x64/opencv_java300.dll");
		JFrame frame1 = new JFrame("Camera");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(640,480);
		frame1.setBounds(0, 0, frame1.getWidth(), frame1.getHeight());
		Panel panel1 = new Panel();
		frame1.setContentPane(panel1);
	    frame1.setVisible(true);
	    
	    JFrame frame2 = new JFrame("HSV");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(640,480);
		frame2.setBounds(0, 0, frame2.getWidth(), frame2.getHeight());
		Panel panel2 = new Panel();
		frame2.setContentPane(panel2);
	    frame2.setVisible(true);
	    
	    JFrame frame3 = new JFrame("Filtered");
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.setSize(640,480);
		frame3.setBounds(0, 0, frame3.getWidth(), frame3.getHeight());
		Panel panel3 = new Panel();
		frame3.setContentPane(panel3);  
	    frame3.setVisible(true);
	    
	    JFrame frame4 = new JFrame("Eroded and Dilated");
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame4.setSize(640,480);
		frame4.setBounds(0, 0, frame4.getWidth(), frame4.getHeight());
		Panel panel4 = new Panel();
		frame4.setContentPane(panel4);  
	    frame4.setVisible(true);
	    
	    JFrame frame5 = new JFrame("Contour Image");
		frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame5.setSize(640,480);
		frame5.setBounds(0, 0, frame5.getWidth(), frame5.getHeight());
		Panel panel5 = new Panel();
		frame5.setContentPane(panel5);  
	    frame5.setVisible(true);
	    
		ControlClass textBox = new ControlClass();
	    
		VideoCapture capture = new VideoCapture(1);
		
		Mat webcam_image = new Mat();
		Mat hsv_image = new Mat();
		Mat hsv_filtered = new Mat();
		Mat final_image = new Mat();
		Mat contour_image = new Mat();
		
		
		Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
		Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3));
		
		capture.read(webcam_image);
		capture.read(hsv_image);
		capture.read(hsv_filtered);
		capture.read(final_image);
		capture.read(contour_image);
		
		frame1.setSize(webcam_image.width()+40,webcam_image.height()+60);
		frame2.setSize(webcam_image.width()+40,webcam_image.height()+60);
		frame3.setSize(webcam_image.width()+40,webcam_image.height()+60);
		frame4.setSize(webcam_image.width()+40,webcam_image.height()+60);
		frame5.setSize(webcam_image.width()+40,webcam_image.height()+60);
	    
		if(capture.isOpened()){
			while(true){
//				try{
//					Thread.sleep(1000);
//				} catch (InterruptedException ex){
//					Logger.getLogger(FRCTrial.class.getName()).log(Level.SEVERE, null, ex);
//				}
				capture.read(webcam_image);
//				webcam_image = Imgcodecs.imread("C:/Users/Ethan/Desktop/FRC2016/2016EthanVision/RealFullField/RealFullField/" + frameNum + ".jpg"); //Ethan Weber
				if (!webcam_image.empty()){
					textBox.updateStatus();
					
					Scalar hsv_min = new Scalar(h_min, s_min, v_min, 0);
				    Scalar hsv_max = new Scalar(h_max, s_max, v_max, 0);
					
					panel1.setimagewithMat(webcam_image);
					frame1.repaint();
					
					Imgproc.cvtColor(webcam_image, hsv_image, Imgproc.COLOR_BGR2HSV);
					panel2.setimagewithMat(hsv_image);
					frame2.repaint();
					
					Core.inRange(hsv_image, hsv_min, hsv_max, hsv_filtered);
					panel3.setimagewithMat(hsv_filtered);
					frame3.repaint();
					
					Imgproc.erode(hsv_filtered, final_image, erodeElement);
					Imgproc.erode(hsv_filtered, final_image, erodeElement);
					Imgproc.dilate(hsv_filtered, final_image, dilateElement);
								
					Mat final_image_copy = final_image.clone();
					panel4.setimagewithMat(final_image);
					frame4.repaint();
					
					capture.read(contour_image);
//					contour_image = Imgcodecs.imread("C:/Users/Ethan/Desktop/FRC2016/2016EthanVision/RealFullField/RealFullField/" + frameNum + ".jpg"); //Ethan Weber
					List<MatOfPoint> contours = new ArrayList<MatOfPoint>();    
				    Imgproc.findContours(final_image, contours, new Mat(), Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);

  		            boolean[] test1 = new boolean[contours.size()];
  		            boolean[] test2 = new boolean[contours.size()];
  		            boolean[] test3 = new boolean[contours.size()];
  		            boolean[] isGoal = new boolean[contours.size()];
		            
				    for(int i=0; i< contours.size();i++){
				        if (Imgproc.contourArea(contours.get(i)) > 250 ){
				        	
				            Rect rect = Imgproc.boundingRect(contours.get(i));
				            int yCoord = rect.y;
				            int xCoord = rect.x;
				            int height = rect.height;
				            int width = rect.width;
				            
				            double sum = 0;
				            for (int y = yCoord; y < yCoord + 5*height/6; y++){
				            	for (int x = xCoord + width/10; x < xCoord + 9.0*width/10.0; x++){
				            		double[] temp = final_image_copy.get(y, x);
									if (temp[0] == 255.0) sum++;
				            	}
				            }
				            double average = sum / ((4.0*width/5.0) * (5.0*height/6.0));
//				            System.out.print("Center Percentage: " + average + ", Side Percentage: ");
				            if (average < .20){
				            	test1[i] = true;
				            }else test1[i] = false;
				            
				            sum = 0;
				            for (int y = yCoord; y < yCoord + height; y++){
				            	for (int x = xCoord; x < xCoord + width/10; x++){
				            		double[] temp = final_image_copy.get(y, x);
									if (temp[0] == 255.0) sum++;
				            	}
				            }
				            average = sum / ((width/10.0) * (height));
				            if (average > .5){
				            	test2[i] = true;
				            }else test2[i] = false;
				            
				            sum = 0;
				            for (int y = yCoord; y < yCoord + height; y++){
				            	for (int x = xCoord + 9*width/10; x < xCoord + width; x++){
				            		double[] temp = final_image_copy.get(y, x);
									if (temp[0] == 255.0) sum++;
				            	}
				            }
				            average = sum / ((width/10.0) * (height));
//				            System.out.println(average);
				            if (average > .5){
				            	test3[i] = true;
				            }else test3[i] = false;
				            
				        }
				    }
				    for (int i = 0; i< contours.size();i++){
				    	if (test1[i] && test2[i] && test3[i]) isGoal[i] = true;
				    	else isGoal[i] = false;
				    }
				    double previousArea = 0;
				    int index = -1;
				    for (int i = 0; i< contours.size();i++){
				    	if (isGoal[i]){
				    		if (Imgproc.contourArea(contours.get(i)) > previousArea){
				    			index = i;
				    			previousArea = Imgproc.contourArea(contours.get(i));
				    		}
				    	}
				    }
				    Imgproc.line(contour_image, new Point(0, 240), new Point(640, 240), new Scalar(0, 255, 0), 2);
				    Imgproc.line(contour_image, new Point(320, 0), new Point(320, 480), new Scalar(0, 255, 0), 2);
				    
				    
				    if (index != -1){
					    Rect rect = Imgproc.boundingRect(contours.get(index));

					    double xPosition = (rect.x + rect.width/2 - 320);
					    double yPosition = (-(rect.y + rect.height/2 - 240));
					    
					    double distance = 0.0004686*yPosition*yPosition + -0.3527*yPosition + 126.6;

					    String outString = "X: " + xPosition + " Y: " + yPosition + " Top: " + (yPosition + rect.height/2.0) + " Dist: " + distance + "in";
					    
					    System.out.println(outString);
					    
						
			    		Imgproc.rectangle(contour_image, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(255,0,0), 10);
//			    		Imgproc.putText(contour_image, outString, new Point(rect.x + rect.width/2, rect.y + rect.height/2), 2, 1, new Scalar(0,0,255));
			    		Imgproc.putText(contour_image, outString, new Point(0, 460), 2, 1, new Scalar(0,0,255));
					    Imgproc.ellipse(contour_image, new Point(rect.x + rect.width/2, rect.y + rect.height/2), new Size(3,3), 0, 0, 360, new Scalar(0,0,255), 3, 1, 0);
			    		
					    Imgproc.line(contour_image, new Point(rect.x + rect.width/2, rect.y + rect.height/2), new Point(320, 240), new Scalar(0, 0, 255), 2);
//			    		Imgproc.line(contour_image, new Point(rect.x + rect.width/2, rect.y + rect.height/2), new Point(rect.x + rect.width/2, 240), new Scalar(0, 0, 255), 2);
//			  for   		Imgproc.line(contour_image, new Point(320, 240), new Point(rect.x + rect.width/2, 240), new Scalar(0, 0, 255), 2);
					    if (Math.abs((-(rect.y + rect.height/2 - 240))) < 5 && Math.abs(rect.x + rect.width/2 - 320) < 5){
					    	Imgproc.rectangle(contour_image, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(0,255,0), 10);
					    }
				    }
			        
					panel5.setimagewithMat(contour_image);
					frame5.repaint();
					
					
				}else{
					System.out.println(" --(!) No captured frame -- Break!");
					break;
				}
			}
		}
	}
	
	
}
