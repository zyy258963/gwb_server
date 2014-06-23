package com.gwb.lee.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {

	public static List<String> listFile = null;

	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		// if (dir.exists()) {
		// System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
		// return false;
		// }
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			System.out.println("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	public static boolean readFile(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		if (!file.isDirectory()) {
			System.out.println("文件");
			System.out.println("path=" + file.getPath());
			System.out.println("absolutepath=" + file.getAbsolutePath());
			System.out.println("name=" + file.getName());
			listFile.add(file.getPath());
		} else if (file.isDirectory()) {
//			System.out.println("文件夹");
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filePath + "\\" + filelist[i]);
				if (!readfile.isDirectory()) {
//					System.out.println("path=" + readfile.getPath());
//					System.out.println("absolutepath="
//							+ readfile.getAbsolutePath());
//					System.out.println("name=" + readfile.getName());
					listFile.add(readfile.getPath());
				} else if (readfile.isDirectory()) {
					readFile(filePath + "\\" + filelist[i]);
				}
			}
		}
		return true;
	}
	
	
	public static List<String> getPathFiles(String filePath){
		listFile = new LinkedList<String>();
		try {
			readFile(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return listFile;
	}
	
	public static void main(String[] args) {
		List<String> files = FileUtils.getPathFiles("C:\\File\\movie");
		for (int i = 0; i < files.size(); i++) {
			System.out.println(files.get(i));
		}
	}

}
