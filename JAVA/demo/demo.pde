
private int inputMax = 256;
private int inputMin = 0;
private int inputMed;
/* All position define in % relative to frameSize */ 

private int x = 50;
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

void setup(){
	size(displayWidth, displayHeight);
	rectMode(CENTER);

	minX = int(map(minX, 0, 100, 0, width));
	maxX = int(map(maxX, 0, 100, 0, width));
	x = int( max( min( map( x, 0, 100, 0, width ), maxX), minX));

	minY = int(map(minY, 0, 100, 0, height));
	maxY = int(map(maxY, 0, 100, 0, height));
	y = int( max( min( map( y, 0, 100, 0, height ), maxY), minY));

	minW = int(map(minW, 0, 100, 0, width));
	maxW = int(map(maxW, 0, 100, 0, width));
	w = int( max( min( map( w, 0, 100, 0, width ), maxW), minW));

	minH = int(map(minH, 0, 100, 0, height));
	maxH = int(map(maxH, 0, 100, 0, height));
	h = int( max( min( map( h, 0, 100, 0, height ), maxH), minH));

	inputMed = abs(inputMax - inputMin) / 2;
}

int ratio(float value, String direction){
	boolean flag = (direction == "vertical" || direction == "v") || false;
	return 	int( 
				max( 
					min( 
						map( value, 0, 100, 0, ( flag ? height : width ) ),
						( flag ? maxH : maxW )
					),
					( flag ? minH : minW )
				)
			);
}

float parabolMap(int value){
	int diff= inputMed-value;
	return (diff<0 ? 1 : -1 ) * pow( abs(diff), 1.5);
}

void draw(){
	background(0);
	fill(255);
	noStroke();

	dX = 256 - (frameCount % 256);
	dY = frameCount % 256;
	
	int _dX=int(map(parabolMap(dX), -1450, 1450, minX, maxX));
	int _dY=int(map(parabolMap(dY), -1450, 1450, minY, maxY));
	
	dW = 256 - (frameCount % 256);
	dH = frameCount % 256;
	
	int _dW=int(map(parabolMap(dW), -1450, 1450, minW, maxW));
	int _dH=int(map(parabolMap(dH), -1450, 1450, minH, maxH));

	rect(x + _dX, y + _dY, w+ _dW, h + dH);
}