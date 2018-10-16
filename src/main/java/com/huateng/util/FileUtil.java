package com.huateng.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
*  <p><strong>Description:</strong>文件操作工具类  </p>
* @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
* @Company 上海华腾软件系统有限公司
* @version 1.0
* Copyright 2012, Shanghai Huateng Software Systems Co., Ltd.
* All right reserved.
*/
public class FileUtil {
	
	/**
	 * 
	 *<p><strong>Description:</strong> 移动文件夹  </p>
	 * @param oriLocate  原文件夹
	 * @param newLocate  新路径
	 * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
	 * @update 日期: 2012-9-25
	 */
	public static void moveFoleder(String oriLocate, String newLocate) {
		File oriFolder = new File(oriLocate);
		File newFolder = new File(newLocate);
		if (!newFolder.isDirectory()) {
			newFolder.mkdirs();
		}
		File newFile = new File(newFolder.getAbsoluteFile() + File.separator
				+ oriFolder.getName());
		oriFolder.renameTo(newFile);
	}

	
	/**
	 * 
	 *<p><strong>Description:</strong> 删除文件（夹）  </p>
	 * @param file  文件（夹）
	 * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
	 * @update 日期: 2012-9-25
	 */
	public static void delete(File file) {
		if (!file.exists())
			return;
		if (file.isFile()) {
			file.delete();
		} else {
			for (File f : file.listFiles()) {
				delete(f);
			}
			file.delete();
		}
	}

	
	/**
	 * 
	 *<p><strong>Description:</strong> 批量删除文件  </p>
	 * @param fileList  可以是文件的集合，也可以是文件路径的集合
	 * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
	 * @update 日期: 2012-9-25
	 */
	public static void deleteFileList(List fileList) {
		if (fileList == null || fileList.isEmpty()) {
			return;
		}
		for (int i = 0, j = fileList.size(); i < j; i++) {
			Object file = fileList.get(i);
			if (file instanceof File) {
				File f = (File) file;
				if (f.exists()) {
					f.delete();
				}
			} else {
				File f = new File(file.toString());
				if (f.exists()) {
					f.delete();
				}
			}
		}
	}

	
	/**
	 * 
	 *<p><strong>Description:</strong> 复制文件（夹）到一个目标文件夹  </p>
	 * @param oriFile 源文件（夹）
	 * @param objectFolderFile目标文件夹
	 * @author <a href="mailto: zhan_yaokang@huateng.com">zhanyaokang</a>
	 * @update 日期: 2012-9-25
	 */
	public static void copy(File oriFile, File objectFolderFile) {
		InputStream ins = null;
		FileOutputStream outs = null;
		try {
			if (!oriFile.exists())
				return;
			if (!objectFolderFile.exists())
				objectFolderFile.mkdirs();
			if (oriFile.isFile()) {
				File objFile = new File(objectFolderFile.getPath()
						+ File.separator + oriFile.getName());
				// 复制文件到目标地

				ins = new FileInputStream(oriFile);

				outs = new FileOutputStream(objFile);
				byte[] buffer = new byte[1024 * 512];
				int length;
				while ((length = ins.read(buffer)) != -1) {
					outs.write(buffer, 0, length);
				}
				outs.flush();
			} else {
				String objFolder = objectFolderFile.getPath() + File.separator
						+ oriFile.getName();
				File _objFolderFile = new File(objFolder);
				_objFolderFile.mkdirs();
				for (File sf : oriFile.listFiles()) {
					copy(sf, new File(objFolder));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ins != null)
					ins.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (outs != null)
					outs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 将文件（夹）移动到令一个文件夹
	 * 
	 * @param resFile
	 *            源文件（夹）
	 * @param objFolderFile
	 *            目标文件夹
	 * @throws IOException
	 *             异常时抛出
	 */
	public static void move(File resFile, File objFolderFile)
			throws IOException {
		copy(resFile, objFolderFile);
		delete(resFile);
	}

	
	/**
	 * 使用Java NIO写大文件
	 * 
	 * @param file
	 * @param data
	 * @throws IOException
	 */
	public static void writeBigFile(File file, String data) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		FileChannel fcout = raf.getChannel();
		ByteBuffer wBuffer = ByteBuffer.allocateDirect(1024 * 1024);
		try {
			raf.seek(raf.length());
			fcout.write(wBuffer.wrap(data.getBytes()), fcout.size());
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			wBuffer.flip();
			wBuffer.compact();
			fcout.close();
			raf.close();
		}
	}


	
	

	

	/**
	 * 构建目录
	 * 
	 * @param outputDir
	 * @param subDir
	 */
	public static void createDirectory(String outputDir, String subDir) {
		File file = new File(outputDir);
		if (!(subDir == null || subDir.trim().equals(""))) {// 子目录不为空
			file = new File(outputDir + "/" + subDir);
		}
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 清理文件(目录或文件)
	 * 
	 * @param file
	 */
	public static void deleteDirectory(File file) {
		if (file.isFile()) {
			file.delete();// 清理文件
		} else {
			File list[] = file.listFiles();
			if (list != null) {
				for (File f : list) {
					deleteDirectory(f);
				}
				file.delete();// 清理目录
			}
		}
	}

	/**
	 * 检查文件是否存在
	 * 
	 * @param fileFullName
	 *            全路径文件名
	 * @return 存在：true，不存在：false
	 */
	public static boolean isExistsFile(String fileFullName) {
		File flagFile = new File(fileFullName);
		if (flagFile.isFile()) {
			return true;
		} else {
			return false;
		}
	}
	
	

	/**
	 * 
	 * 读取某个文件夹下的所有文件夹和文件, 返回所有文件名
	 * 
	 * @param filepath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return List<String> pathList
	 * 
	 */
	public static List<String> readfile(String filepath,
			 List<String> pathList) throws Exception {
		if (pathList == null) {
			pathList = new ArrayList<String>();
		}

		File file = new File(filepath);
		// 文件
		if (!file.isDirectory()) {
			pathList.add(file.getPath());

		} else if (file.isDirectory()) { // 如果是目录， 遍历所有子目录取出所有文件名
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "/" + filelist[i]);
				if (!readfile.isDirectory()) {
					pathList.add(readfile.getPath());
				} else if (readfile.isDirectory()) { // 子目录的目录
					readfile(filepath + "/" + filelist[i], pathList);
				}
			}
		}
		return pathList;
	}
	
	/**
	 * 将文件打成zip压缩包
	 * @param sourceFiles  	待压缩文件
	 * @param zipFile		zip文件
	 * @throws Exception
	 */
	public static File fileToZip(List<File> sourceFiles, File zipFile) throws Exception{
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			if(zipFile.exists()) {
//				throw new Exception(">>>>>> " + zipFile.getPath() + " 目录下存在名字为：" + zipFile.getName() + ".zip" + " 打包文件. <<<<<<");
				zipFile.delete();
			} 
			if(null == sourceFiles || sourceFiles.size() < 1) {
				zipFile = null;
				return zipFile;
//				throw new Exception("没有需要压缩的文件");
			}
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(new BufferedOutputStream(fos));
			byte[] bufs = new byte[1024*10];
			for (File file : sourceFiles) {

				// 创建ZIP实体,并添加进压缩包
				ZipEntry zipEntry = new ZipEntry(file.getName());
				zos.putNextEntry(zipEntry);
				// 读取待压缩的文件并写进压缩包里
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis,1024*10);
				int read = 0;
				while((read=bis.read(bufs, 0, 1024*10)) != -1) {
					zos.write(bufs, 0, read);
				}
				bis.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if(null != bis) bis.close();
				if(null != zos) zos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return zipFile;
	}
	
	/**
	 * 将存放在sourceFilePath目录下的源文件,打包成fileName名称的ZIP文件,并存放到zipFilePath。
	 * @param sourceFilePath 待压缩的文件路径
	 * @param zipFilePath	 压缩后存放路径
	 * @param fileName		 压缩后文件的名称
	 * @return flag
	 */
	public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName) {
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		
		if(sourceFile.exists() == false) {
			System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 不存在. <<<<<<");
		} else {
			try {
				File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
				if(zipFile.exists()) {
					System.out.println(">>>>>> " + zipFilePath + " 目录下存在名字为：" + fileName + ".zip" + " 打包文件. <<<<<<");
				} else {
					File[] sourceFiles = sourceFile.listFiles();
					if(null == sourceFiles || sourceFiles.length < 1) {
						System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 里面不存在文件,无需压缩. <<<<<<");
					} else {
						fos = new FileOutputStream(zipFile);
						zos = new ZipOutputStream(new BufferedOutputStream(fos));
						byte[] bufs = new byte[1024*10];
						for(int i=0;i<sourceFiles.length;i++) {
							// 创建ZIP实体,并添加进压缩包
							ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
							zos.putNextEntry(zipEntry);
							// 读取待压缩的文件并写进压缩包里
							fis = new FileInputStream(sourceFiles[i]);
							bis = new BufferedInputStream(fis,1024*10);
							int read = 0;
							while((read=bis.read(bufs, 0, 1024*10)) != -1) {
								zos.write(bufs, 0, read);
							}
						}
						flag = true;
					}
				}
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				// 关闭流
				try {
					if(null != bis) bis.close();
					if(null != zos) zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		
		return flag;
	}
	
	/**
	 * <p><strong>Description: </strong>创建此文件，表示数据文件已经生成</p>
	 * @author zgb
	 * @param filePath
	 * @param date
	 * @throws Exception
	 * @update 2013-11-20
	 */
	public static void createOkFile(String filePath,String date) throws Exception{
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			File f = new File(filePath);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			osw = new OutputStreamWriter(new FileOutputStream(
					filePath),
					ConfigProperties.getProperties("charset.database"));
			bw = new BufferedWriter(osw);
			
			bw.write("date="+date);
			bw.newLine();
			bw.flush();
		} catch (Exception e) {
			throw e;
		}finally{
			if (bw != null) {
				bw.close();
			}
			if (osw != null) {
				osw.close();
			}
		}
	}
	
	/**
	 * 根据文件路径获取文件名
	 * @param filePathName
	 * @return
	 * @throws Exception
	 */
	public static String getFileName(String filePathName){
		File f = new File(filePathName);
		return f.getName();
	}
	
	/**
	 * 写入TXT文件内容,文件不存在则创建
	 * @param content 内容
	 * @param fileName 文件路径及名称
	 * @throws IOException 异常
	 */
	public static void writeTxtFile(String content,String filePath,String fileName,String code) throws IOException{
		File path = new File(filePath.replace("\\", File.separator));
		if(!path.exists()){
			path.mkdirs();
		}
		File f = new File(filePath.replace("\\", File.separator)+"/"+fileName);
		if(!f.exists()){
			f.createNewFile();
		}
		OutputStream out = new FileOutputStream(f);
		BufferedWriter rd = new BufferedWriter(new OutputStreamWriter(out,code));
		rd.write(content);
		rd.flush();
		out.close();
		rd.close();
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		writeTxtFile("aaa", "d:/home/was/data/sendData/20170820","0408-POINTADJ-700022-20170820","UTF-8");
//		  List<String> map = readfile("D:\batchUpload",new ArrayList<String>());
//		   for(int i=0 ; i < map.size(); i++) {
//		    System.out.println(map.get(i));
//		   }
		
		
	}
	
	public static void mkdirs(String dir){
		File dirfile = new File(dir);
		if(!dirfile.isDirectory()){
			dirfile.mkdirs();
		}
	}
}
