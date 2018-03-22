package com.youxin.ymall.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.w3c.dom.Element;

public class ImgCompressUtil {

	/**
	 * 图片压缩测试
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		tosmallerpic("C:\\Users\\Administrator\\Desktop\\杂乱图片\\images\\test\\1.png",
				"C:\\Users\\Administrator\\Desktop\\杂乱图片\\images\\test\\2.png", 200, 200, (float) 0.7);
	}

	/**
	 * Description：图片压缩
	 * 
	 * @param w
	 *            宽度
	 * @param h
	 *            高度
	 * @param sourcepath
	 *            原图片路径
	 * @param resultpath
	 *            目标图片保存路径
	 * @throws Exception
	 *
	 */
	public static void saveCompressImage(int w, int h, String sourcepath, String resultpath) throws Exception {
		File saveFilePath = new File(resultpath);
		if (!saveFilePath.getParentFile().exists()) {
			saveFilePath.getParentFile().mkdirs();
		}
		File file = new File(sourcepath);// 读入文件
		Image img = ImageIO.read(file); // 构造Image对象
		int width = img.getWidth(null);
		int height = img.getHeight(null);

		if (width / height > w / h) {
			h = (int) (height * w / width);

		} else {
			w = (int) (width * h / height);
		}
		tosmallerpic(sourcepath, resultpath, w, h, (float) 1.0);
	}

	/**
	 * 
	 * @param srcFilePath
	 *            图片所在的文件夹路径
	 * @param destFilePath
	 *            存放路径
	 * @param name
	 *            图片名
	 * @param w
	 *            目标宽
	 * @param h
	 *            目标高
	 * @param per
	 *            百分比
	 */
	private static void tosmallerpic(String srcFilePath, String destFilePath, int w, int h, float per) {
		Image src;
		try {
			src = javax.imageio.ImageIO.read(new File(srcFilePath)); // 构造Image对象
			String img_midname = destFilePath;
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			image.getGraphics().drawImage(src, 0, 0, w, h, null); // 绘制缩小后的图
			FileOutputStream fos = new FileOutputStream(img_midname); // 输出到文件流

			// 旧的使用 jpeg classes进行处理的方法
			// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
			// JPEGEncodeParam jep =
			// JPEGCodec.getDefaultJPEGEncodeParam(image_to_save);
			/* 压缩质量 */
			// jep.setQuality(per, true);
			// encoder.encode(image_to_save, jep);

			// 新的方法
			saveAsJPEG(100, image, per, fos);

			fos.close();
		} catch (IOException ex) {
			// Logger.getLogger(Img_Middle.class.getName()).log(Level.SEVERE,
			// null, ex);
			ex.printStackTrace();
		}
	}

	/**
	 * 以JPEG编码保存图片
	 * 
	 * @param dpi
	 *            分辨率
	 * @param image_to_save
	 *            要处理的图像图片
	 * @param JPEGcompression
	 *            压缩比
	 * @param fos
	 *            文件输出流
	 * @throws IOException
	 */
	public static void saveAsJPEG(Integer dpi, BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos)
			throws IOException {

		// useful documentation at
		// http://docs.oracle.com/javase/7/docs/api/javax/imageio/metadata/doc-files/jpeg_metadata.html
		// //useful example program at
		// http://johnbokma.com/java/obtaining-image-metadata.html to output
		// JPEG data

		// old jpeg class
		// com.sun.image.codec.jpeg.JPEGImageEncoder jpegEncoder =
		// com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(fos);
		// com.sun.image.codec.jpeg.JPEGEncodeParam jpegEncodeParam =
		// jpegEncoder.getDefaultJPEGEncodeParam(image_to_save);

		// Image writer
		// JPEGImageWriter imageWriter = (JPEGImageWriter)
		// ImageIO.getImageWritersBySuffix("jpeg").next();
		ImageWriter imageWriter = ImageIO.getImageWritersBySuffix("jpg").next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
		imageWriter.setOutput(ios);
		// and metadata
		IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);

		if (dpi != null && !dpi.equals("")) {

			// old metadata
			// jpegEncodeParam.setDensityUnit(com.sun.image.codec.jpeg.JPEGEncodeParam.DENSITY_UNIT_DOTS_INCH);
			// jpegEncodeParam.setXDensity(dpi);
			// jpegEncodeParam.setYDensity(dpi);

			// new metadata
			Element tree = (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");
			Element jfif = (Element) tree.getElementsByTagName("app0JFIF").item(0);
			jfif.setAttribute("Xdensity", Integer.toString(dpi));
			jfif.setAttribute("Ydensity", Integer.toString(dpi));

		}

		if (JPEGcompression >= 0 && JPEGcompression <= 1f) {

			// old compression
			// jpegEncodeParam.setQuality(JPEGcompression,false);

			// new Compression
			JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
			jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
			jpegParams.setCompressionQuality(JPEGcompression);

		}

		// old write and clean
		// jpegEncoder.encode(image_to_save, jpegEncodeParam);

		// new Write and clean up
		imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
		ios.close();
		imageWriter.dispose();

	}

}
