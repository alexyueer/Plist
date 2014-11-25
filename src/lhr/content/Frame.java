package lhr.content;

public class Frame {
	public String picName;
	public String startX,startY;
	public String width,height;
	public String offsetX,offsetY;
	public Boolean roated;
	public String opX,opY;//非透明区域相对于透明区域的位置坐标opaque（不透明）
	public String opWidth,opHeight;//非透明区域（既可以看得见的区域）的长和宽
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getStartX() {
		return startX;
	}
	public void setStartX(String startX) {
		this.startX = startX;
	}
	public String getStartY() {
		return startY;
	}
	public void setStartY(String startY) {
		this.startY = startY;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getOffsetX() {
		return offsetX;
	}
	public void setOffsetX(String offsetX) {
		this.offsetX = offsetX;
	}
	public String getOffsetY() {
		return offsetY;
	}
	public void setOffsetY(String offsetY) {
		this.offsetY = offsetY;
	}
	public Boolean getRoated() {
		return roated;
	}
	public void setRoated(Boolean roated) {
		this.roated = roated;
	}
	public String getOpX() {
		return opX;
	}
	public void setOpX(String opX) {
		this.opX = opX;
	}
	public String getOpY() {
		return opY;
	}
	public void setOpY(String opY) {
		this.opY = opY;
	}
	public String getOpWidth() {
		return opWidth;
	}
	public void setOpWidth(String opWidth) {
		this.opWidth = opWidth;
	}
	public String getOpHeight() {
		return opHeight;
	}
	public void setOpHeight(String opHeight) {
		this.opHeight = opHeight;
	}
}
