package lhr.content;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

public class Frames {
	public List<Frame> frame = new ArrayList<Frame>();//用于存放获取的每一帧图像
	public List<Element> frames = new ArrayList<Element>();//存放每个图片的key信息，文件名
	public int frame_num;//存放的图形数量
	
	public List<Frame> getFrame() {
		return frame;
	}
	public void setFrame(List<Frame> frame) {
		this.frame = frame;
	}
	public List<Element> getFrames() {
		return frames;
	}
	public void setFrames(List<Element> frames) {
		this.frames = frames;
	}
	public int getFrame_num() {
		return frame_num;
	}
	public void setFrame_num(int frame_num) {
		this.frame_num = frame_num;
	}

	
}
