package lhr.plist;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import lhr.content.Frame;
import lhr.content.Frames;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ExtractMs {
	public static void main(String[] args) throws Exception {
		Element dict = null;//指向每一个dict元素
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new File("doc/2.plist"));
		Element root = doc.getRootElement();//获得根节点plist
		//System.out.println(root.getName());
		
		dict= root.getChild("dict");//根元素紧跟着的dict块
		
		List<Element> list_key = dict.getChildren("key");//获得frames与metadata元数据
		//Frames对象，用来存放获取的每一帧图片
		Frames frs = new Frames(); 
		/*获取frames中对应的图像存入Frames对象中，获取元数据信息*/
		Element e = null;
		for(int i=0;i<list_key.size();i++){
			e = list_key.get(i);
			if("frames".equals(e.getText())){
				dict = e.getParentElement().getChild("dict");
				frs.setFrames(dict.getChildren("key"));//存取一帧一帧的图片头信息，即key信息。
			}else if("metadata".equals(e.getText())){
				
			}
		}
		frs.setFrame_num(frs.frames.size());//图片数量
		Frame fr = new Frame();
		//提取大图中每幅小图像的数据，并存储起来
		setFrameMs(fr,frs);
		//以下语句用来测试存储是否成功
		System.out.println(frs.frame.get(2).picName);
		System.out.println(frs.frame.get(2).height);
		System.out.println(frs.frame.get(2).roated);
		//String srcImgFile ="f:/2.png";
		//cutPic(frs,srcImgFile);
		
	}
	/**
	 * 用来将每一张字图片分开存储到Frames类所定义的List<Frame>当中去,
	 * 每一张图片的信息存储到Frame这个类对象中
	 * 
	 */
	public static void setFrameMs(Frame fr,Frames frs){
		
		List<Element> list_dict = frs.frames.get(0).getParentElement().getChildren("dict");//分别获取妹夫图片所对应的dict块
	
		List<Element> list_dict_key = new ArrayList<Element>();
		List<Element> list_dict_value = new ArrayList<Element>();
		//List<Element> list_dict_value = new ArrayList<Element>();
		
		for(int i=0;i<frs.getFrame_num();i++){
			fr.setPicName(frs.frames.get(i).getText());//图片名称
			//System.out.println(fr.getPicName());
			list_dict_key = list_dict.get(i).getChildren("key");//妹夫图片对应的dict块中的key列表
			list_dict_value = list_dict.get(i).getChildren("string");//妹夫图片对应的dict块中的string列表
			//System.out.println(list_dict_value.size());
			String key = null;
			String value = null;
			int k = 0;//用于跳过rotated这个元素
			for(int j=0;j<list_dict_key.size();j++,k++){
				
				key = list_dict_key.get(j).getText();
				if("rotated".equals(key)){
					if(null == list_dict.get(i).getChild("false")){
						fr.setRoated(true);
					}else{
						fr.setRoated(false);
					}
					k--;//当key的值为rotated的时候，list_key_value中不该有值，所以保持位置不变。
				}else{
					value = list_dict_value.get(k).getText();
				}
				
				if("frame".equals(key)||"sourceColorRect".equals(key)){
					String value1 = value.substring(value.indexOf("{{")+2,value.indexOf("},"));//value1中记录了图像起始坐标
					String value2 = value.substring(value.indexOf(",{")+2, value.indexOf("}}"));//value2中记录了图像长和宽
					String sX = value1.substring(0,value1.indexOf(","));//起始横坐标
					String sY = value1.substring(value1.indexOf(",")+1);//起始纵坐标
					String wd = value2.substring(0, value2.indexOf(","));
					String hg = value2.substring(value2.indexOf(",")+1);
					if("frame".equals(key)){//设置整体图像的起始坐标和大小
						fr.setStartX(sX);
						fr.setStartY(sY);
						fr.setWidth(wd);
						fr.setHeight(hg);
					}else{//设置非透明图像的坐标和大小
						fr.setOpX(sX);
						fr.setOpY(sY);
						fr.setOpWidth(wd);
						fr.setOpHeight(hg);
					}
				}else if("offset".equals(key)){//图像区域（非透明区域）中心点与整体区域（透明区域与非透明区域）中心点的偏移量
					String oX = value.substring(value.indexOf("{")+1, value.indexOf(","));
					String oY = value.substring(value.indexOf(",")+1, value.indexOf("}"));
					fr.setOffsetX(oX);
					fr.setOffsetY(oY);
				}
				
			}
			frs.frame.add(fr);//将Frame类对象fr存储的图片信息放入Frames类定义的List<Frame>中,方便后续的分割提取
		}
	}
	
	public static void cutPic(Frames frs,String srcImgFile) throws Exception{
		BufferedImage img = ImageIO.read(new File(srcImgFile));
		//System.out.println(img.getWidth());
		//System.out.println(img.getHeight());
	//	System.out.println(frs.frame_num);
		//for(int i=0;i<frs.frame_num;)
	}
}
