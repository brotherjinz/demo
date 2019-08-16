package com.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 对字符串加压解压
 * @author Administrator
 *
 */
public class ZipStringUtil {
/*	*//**
	 * 使用gzip进行压缩
	 *//*
	public static String gzip(String primStr) {
		if (primStr == null || primStr.length() == 0) {
			return primStr;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return new sun.misc.BASE64Encoder().encode(out.toByteArray());
	}

	*//**
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 * @param compressedStr
	 * @return
	 *//*
	public static String gunzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = new sun.misc.BASE64Decoder()
					.decodeBuffer(compressedStr);
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}

	*//**
	 * 使用zip进行压缩 
	 * @param str
	 *            压缩前的文本
	 * @return 返回压缩后的文本
	 *//*
	public static final String zip(String str) {
		if (str == null)
			return null;
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes());
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr = new sun.misc.BASE64Encoder()
					.encodeBuffer(compressed);
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return compressedStr;
	}
	
	*//**
	 * 使用zip进行压缩 
	 * @param str
	 *            压缩前的文本
	 * @return 返回压缩后的文本
	 *//*
	public static final String zip(byte[] str) {
		if (str.length==0)
			return null;
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str);
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr = new sun.misc.BASE64Encoder()
					.encodeBuffer(compressed);
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return compressedStr;
	}
	
	*//**
	 * 使用zip进行解压缩
	 * @param compressed
	 *            压缩后的文本
	 * @return 解压后的字符串
	 *//*
	public static final String unzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			byte[] compressed = new sun.misc.BASE64Decoder()
					.decodeBuffer(compressedStr);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}
	
	*//**
	 * 使用zip进行解压缩 
	 * @param compressed
	 *            压缩后的文本
	 * @return 解压后的字节数组
	 *//*
	public static final byte[] unbytezip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			byte[] compressed = new sun.misc.BASE64Decoder()
					.decodeBuffer(compressedStr);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed.getBytes();
	}
	*/
	/**  
     * 压缩  
     * @param data  
     *            待压缩数据  
     * @return byte[] 压缩后的数据  
     */  
    public static byte[] compress(byte[] data) {   
        byte[] output = new byte[0];   
  
        Deflater compresser = new Deflater();   
  
        compresser.reset();   
        compresser.setInput(data);   
        compresser.finish();   
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);   
        try {   
            byte[] buf = new byte[1024];   
            while (!compresser.finished()) {   
                int i = compresser.deflate(buf);   
                bos.write(buf, 0, i);   
            }   
            output = bos.toByteArray();   
        } catch (Exception e) {   
            output = data;   
            e.printStackTrace();   
        } finally {   
            try {   
                bos.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
        compresser.end();   
        return output;   
    } 
    
    
    /**  
     * 解压缩    
     * @param data  
     *            待压缩的数据  
     * @return byte[] 解压缩后的数据   
     */  
    public static byte[] decompress(byte[] data) {   
        byte[] output = new byte[0];   
  
        Inflater decompresser = new Inflater();   
        decompresser.reset();   
        decompresser.setInput(data);   
  
        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);   
        try {   
            byte[] buf = new byte[1024];   
            while (!decompresser.finished()) {   
                int i = decompresser.inflate(buf);   
                o.write(buf, 0, i);   
            }   
            output = o.toByteArray();   
        } catch (Exception e) {   
            output = data;   
            e.printStackTrace();   
        } finally {   
            try {   
                o.close();   
            } catch (IOException e) {   
                e.printStackTrace();   
            }   
        }   
  
        decompresser.end();   
        return output;   
    }   
    
    /**
	 * 将字节数组转换为16进制字符串 例:%78%9C%4B%04%00%00%62%00%62
	 * zhangl 2013-11-4
	 * @param bts
	 * @return
	 */
	public static String byteTosca16(byte[] bts) {
		StringBuilder encoded = new StringBuilder();
			try {
				
				for (int j = 0; j < bts.length; j++) {
					String hex=Integer.toHexString(bts[j] & 0xff).toUpperCase();
					encoded.append("%");
					if(hex.length()==1)
					{
						encoded.append("0");
					}
					encoded.append(hex);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		try {
			byte[] aa=URLDecoder.decode(encoded.toString(), "UTF-8").getBytes();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return encoded.toString();
	}
	
	/**
	 * 字符串转有符号字节数组 例:789C4B040000620062
	 * zhangl 2013-11-5
	 * @param encoded
	 * @return byte[]
	 */
	public static byte[] strTobyte(String encoded) {
		byte[] decodeBts=null;
		try {
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			//去掉百分号后每次取两个字母i+2
			for(int i=0;i<encoded.toString().length();i+=2)
			{   
				//去掉百分号后每次取两个字母i+2
				byte b=Integer.valueOf(encoded.toString().substring(i, i+2), 16).byteValue();
				bos.write(b);
			}
			decodeBts=bos.toByteArray();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return decodeBts;
	}
	
    public static void main(String[] args){  
         	
	}
}
