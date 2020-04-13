package advance.datastructureandalgorithm.lintcode;

public class RectangleArea {
	int width;
	int height;
	
	public RectangleArea(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public int getArea(){
		return this.width * this.height;
	}
}
