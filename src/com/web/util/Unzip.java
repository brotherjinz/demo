package com.web.util;

import java.io.*;
import java.util.zip.*;

/**
 * 程序实现了ZIP压缩
 */
public class Unzip {

	public void unzip(String zipFilepath, String zipDir, String indexId) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		try {
			ZipInputStream Zin = new ZipInputStream(new FileInputStream(zipFilepath));// 输入源zip路径
			BufferedInputStream bins = new BufferedInputStream(Zin);
			String Parent = zipDir; // 输出路径（文件夹目录）
			File fileout = null;
			ZipEntry entry;
			String modifyFileDir = zipDir + indexId;
			File modifyDir = new File(modifyFileDir);
			if (!modifyDir.exists())
				modifyDir.mkdirs();
			try {
				while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
					fileout = new File(Parent, entry.getName());
					if (!fileout.exists()) {
						(new File(fileout.getParent())).mkdirs();
					}
					FileOutputStream out = new FileOutputStream(fileout);
					BufferedOutputStream Bout = new BufferedOutputStream(out);
					int b;
					while ((b = bins.read()) != -1) {
						Bout.write(b);
					}
					Bout.close();
					out.close();
					String filePath = fileout.getAbsolutePath();
					System.out.println(filePath.substring(filePath.lastIndexOf("\\") + 1));
					String newFilename = modifyFileDir + "\\" + filePath.substring(filePath.lastIndexOf("\\") + 1);
					System.out.println("newFileName=" + newFilename);
					File newFile = new File(newFilename);
					fileout.renameTo(newFile);
					if (fileout.exists())
						fileout.delete();
				}
				bins.close();
				Zin.close();
				// 解压成功后删除
				// new File(zipFilepath).delete();
			} catch (IOException e) {
				// TODO Auto-generated catch blockfd
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗费时间： " + (endTime - startTime) + " ms");
	}

	public static void main(String[] args) {
		Unzip unzip = new Unzip();
		unzip.unzip("D:\\TEST.zip", "D:\\", "TEST");
	}
}
