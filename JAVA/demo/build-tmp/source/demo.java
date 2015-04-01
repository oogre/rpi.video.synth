import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class demo extends PApplet {


private int inputMax = 256;
private int inputMin = 0;
private int inputMed;
/* All position define in % relative to frameSize */ 

private int x = 0;
private int minX = -100;
private int maxX = 100;
private int dX;

private int y = 50;
private int minY = -100;
private int maxY = 100;
private int dY;

private int w = 33;
private int minW = 0;
private int maxW = 100;
private int dW;

private int h = 33;
private int minH = 0;
private int maxH = 100;
private int dH;

public void setup(){
	size(displayWidth, displayHeight);
	rectMode(CENTER);

	minX = PApplet.parseInt(map(minX, 0, 100, 0, width));
	maxX = PApplet.parseInt(map(maxX, 0, 100, 0, width));
	x = PApplet.parseInt( max( min( map( x, 0, 100, 0, width ), maxX), minX));

	minY = PApplet.parseInt(map(minY, 0, 100, 0, height));
	maxY = PApplet.parseInt(map(maxY, 0, 100, 0, height));
	y = PApplet.parseInt( max( min( map( y, 0, 100, 0, height ), maxY), minY));

	minW = PApplet.parseInt(map(minW, 0, 100, 0, width));
	maxW = PApplet.parseInt(map(maxW, 0, 100, 0, width));
	w = PApplet.parseInt( max( min( map( w, 0, 100, 0, width ), maxW), minW));

	minH = PApplet.parseInt(map(minH, 0, 100, 0, height));
	maxH = PApplet.parseInt(map(maxH, 0, 100, 0, height));
	h = PApplet.parseInt( max( min( map( h, 0, 100, 0, height ), maxH), minH));

	inputMed = abs(inputMax - inputMin) / 2;
}

public int ratio(float value, String direction){
	boolean flag = (direction == "vertical" || direction == "v") || false;
	return 	PApplet.parseInt( 
				max( 
					min( 
						map( value, 0, 100, 0, ( flag ? height : width ) ),
						( flag ? maxH : maxW )
					),
					( flag ? minH : minW )
				)
			);
}

public float parabolMap(int value){
	int diff= inputMed-value;
	return (diff<0 ? 1 : -1 ) * pow( abs(diff), 1.5f);
}

public void draw(){
	background(0);
	fill(255);
	noStroke();

	dX = 256 - (frameCount % 256);
	dY = frameCount % 256;
	
	int _dX=PApplet.parseInt(map(parabolMap(dX), -1450, 1450, minX, maxX));
	int _dY=PApplet.parseInt(map(parabolMap(dY), -1450, 1450, minY, maxY));
	
	dW = 256 - (frameCount % 256);
	dH = frameCount % 256;
	
	int _dW=PApplet.parseInt(map(parabolMap(dW), -1450, 1450, minW, maxW));
	int _dH=PApplet.parseInt(map(parabolMap(dH), -1450, 1450, minH, maxH));

	rect(x + _dX, y + _dY, w+ _dW, h + dH);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "demo" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
