package com.youxin.ymall.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * Description：保存文件到
 * 
 * @author lijianwei
 */
public class SaveImagesTools {
	public static String rootPath = "";// 图片的根目录

	static {
		try {
			// rootPath = StringTools.getValueFromProperties("application-dev",
			// "allImagesRootPath");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存图片的虚拟机目录
	 * 
	 * @param file
	 *            要保存的文件
	 * @param childPathWithName
	 *            文件的目录和名字的结合
	 * @return
	 */
	public static String saveImage(MultipartFile file, String childPath, String fileName) {
		try {
			// 先创建根目录文件夹
			File saveFilePath = new File(rootPath + childPath);
			if (!saveFilePath.exists()) {
				saveFilePath.mkdirs();
			}
			File ttFile = new File(saveFilePath + "/" + fileName);
			if (!ttFile.exists()) {
				ttFile.createNewFile();
			}
			file.transferTo(ttFile);
			return ttFile.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 创建并保存缩略图
	 * 
	 * @param w
	 * @param h
	 * @param sourcePath
	 * @param destPath
	 * @return
	 */
	/*
	 * public static Boolean saveThumbImage(int w, int h,String
	 * sourcePath,String destPath) { try { File compressFile = new
	 * File(rootPath+destPath); if(!compressFile.exists()) {
	 * compressFile.createNewFile(); } ImgCompress imgCom = new
	 * ImgCompress(rootPath+sourcePath); imgCom.resizeFix(w, h,compressFile);
	 * return true; } catch (Exception e) { e.printStackTrace(); return false; }
	 * }
	 */

	/**
	 * 根据文件路径。获取到文件的大小
	 * 
	 * @param filePath
	 * @return
	 */
	public static long getFileSize(String filePath) {
		try {
			File file = new File(rootPath + filePath);
			return file.length();
		} catch (Exception e) {
			e.printStackTrace();
			return 0l;
		}

	}

	/**
	 * 保存图片字符串的虚拟机目录
	 * 
	 * @param file
	 *            要保存的文件
	 * @param childPathWithName
	 *            文件的目录和名字的结合
	 * @return
	 */
	public static void saveImage_stringToImages(String imageStr, String path) {
		try {
			ImageToStringTools.generateImage(imageStr, rootPath + path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存图片的虚拟机目录
	 * 
	 * @param file
	 *            要保存的文件
	 * @param childPathWithName
	 *            文件的目录和名字的结合
	 * @return
	 */
	public static boolean saveImage(String formFilePath, String childPath, String fileName) {
		try {
			// 先创建根目录文件夹
			File saveFilePath = new File(rootPath + childPath);
			if (!saveFilePath.exists()) {
				saveFilePath.mkdirs();
			}
			File ttFile = new File(saveFilePath + "/" + fileName);
			if (!ttFile.exists()) {
				ttFile.createNewFile();
			}

			FileInputStream fi = null;
			FileOutputStream fo = null;
			FileChannel in = null;
			FileChannel out = null;
			try {
				fi = new FileInputStream(new File(rootPath + formFilePath));
				fo = new FileOutputStream(ttFile);
				in = fi.getChannel();// 得到对应的文件通道
				out = fo.getChannel();// 得到对应的文件通道
				in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fi.close();
					in.close();
					fo.close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 保存图片的虚拟机目录
	 * 
	 * @param file
	 *            要保存的文件
	 * @param childPathWithName
	 *            文件的目录和名字的结合
	 * @return
	 */
	public static boolean saveImage(InputStream is, String childPath, String fileName) {
		try {
			// 先创建根目录文件夹
			File saveFilePath = new File(rootPath + childPath);
			if (!saveFilePath.exists()) {
				saveFilePath.mkdirs();
			}
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			OutputStream os = new FileOutputStream(saveFilePath + "/" + fileName);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Description：删除文件夹中的所有子文件夹和文件
	 * 
	 * @param path
	 */
	public static void deleteAllFilesByPath(String path) {
		try {
			File topFile = new File(path);
			if (topFile.exists()) {
				File[] files = topFile.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deleteAllFilesByPath(files[i].getAbsolutePath());
					}
					files[i].delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
