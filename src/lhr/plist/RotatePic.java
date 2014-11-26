package lhr.plist;

/**
 * 用来对图片进行逆时针旋转90操作
 * @author Administrator
 *
 */
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class RotatePic {
	public static BufferedImage rotate(int w,int h,BufferedImage srcImg){
		BufferedImage dstImg = new BufferedImage(h, w, srcImg.getType());
		AffineTransform af = new AffineTransform();
		af.translate(0, w);
		af.rotate(Math.toRadians(270));
		AffineTransformOp afop = new AffineTransformOp(af, null);
		return afop.filter(srcImg, dstImg);
	}
}
