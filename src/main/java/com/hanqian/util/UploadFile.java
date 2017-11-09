package com.hanqian.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 描 述: 
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-9-18
 * @see
 */
public class UploadFile {
	
	/**
	 * 上传文件
	 * @param file  文件
	 * @param savePath  提交的路径
	 * @param saveName  提交的名字
	 * @throws IOException
	 */
	public void saveFile(File file, String savePath, String saveName)
			throws IOException {	
		
		OutputStream os = null;		
		InputStream in =null;		
		try {
			
			int count = saveName.lastIndexOf("/");
			String fileNameGs = saveName.substring(count);
			
			
			File f1 = new File(savePath);
			if (!f1.exists()) {
				f1.mkdirs();
			}
			
			os = new FileOutputStream(new File(savePath, fileNameGs));			
			in = new FileInputStream(file);
			

			byte[] buffer = new byte[400];

			int length = 0;

			while ((length = in.read(buffer)) > 0) {
				os.write(buffer, 0, length);
				
			}			
		} catch (IOException e) {
			// TODO: handle exception
			throw e;
		}finally{			
			try {
				os.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				in.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	

}
