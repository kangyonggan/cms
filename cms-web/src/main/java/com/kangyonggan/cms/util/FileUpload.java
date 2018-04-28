package com.kangyonggan.cms.util;

import com.kangyonggan.cms.constants.AppConstants;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author kangyonggan
 * @since 2016/12/6
 */
public class FileUpload {

    /**
     * 文件复制
     *
     * @param filename
     * @param newFilename
     * @throws FileUploadException
     */
    public static void copy(String filename, String newFilename) throws FileUploadException {
        try {
            FileInputStream fin = null;
            FileOutputStream fout = null;
            FileChannel in = null;
            FileChannel out = null;
            try {
                fin = new FileInputStream(PropertiesUtil.getProperties(AppConstants.FILE_PATH_ROOT) + filename);
                fout = new FileOutputStream(PropertiesUtil.getProperties(AppConstants.FILE_PATH_ROOT) + newFilename);
                in = fin.getChannel();
                out = fout.getChannel();
                in.transferTo(0, in.size(), out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (fout != null) {
                        fout.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    if (fin != null) {
                        fin.close();
                    }
                } catch (Exception e) {
                    throw new FileUploadException("文件复制异常", e);
                }
            }
        } catch (Exception e) {
            throw new FileUploadException("文件复制异常", e);
        }
    }

    /**
     * 文件复制
     *
     * @param filename
     * @param newFilename
     * @throws FileUploadException
     */
    public static void copyAbs(String filename, String newFilename) throws FileUploadException {
        try {
            FileInputStream fin = null;
            FileOutputStream fout = null;
            FileChannel in = null;
            FileChannel out = null;
            try {
                fin = new FileInputStream(filename);
                fout = new FileOutputStream(PropertiesUtil.getProperties(AppConstants.FILE_PATH_ROOT) + newFilename);
                in = fin.getChannel();
                out = fout.getChannel();
                in.transferTo(0, in.size(), out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (fout != null) {
                        fout.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    if (fin != null) {
                        fin.close();
                    }
                } catch (Exception e) {
                    throw new FileUploadException("文件复制异常", e);
                }
            }
        } catch (Exception e) {
            throw new FileUploadException("文件复制异常", e);
        }
    }

    /**
     * 上传文件
     *
     * @param file
     * @param prefix
     * @return
     * @throws Exception
     */
    public static String upload(MultipartFile file, String prefix) throws FileUploadException {
        String fileName = "";
        if (file.getSize() != 0) {
            try {
                fileName = extractFilePath(file, prefix);
                File desc = getAbsolutePath(fileName);
                file.transferTo(desc);
            } catch (Exception e) {
                throw new FileUploadException("文件上传异常", e);
            }
        }
        return fileName;
    }

    /**
     * 获取文件绝对路径
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static File getAbsolutePath(String filename) throws IOException {
        File desc = new File(PropertiesUtil.getProperties(AppConstants.FILE_PATH_ROOT) + filename);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 根据文件名和后缀提取上传后的文件路径，例如：upload/20150328/2015032821561197_suffix.jpg
     *
     * @param fileName
     * @param suffix
     * @return
     */
    public static String extractFilePath(String fileName, String suffix) {
        String fileExt = FilenameUtils.getExtension(fileName);
        return extractFilePathByExtension(fileExt, suffix);
    }

    /**
     * 根据 MultipartFile 提取上传后的文件路径，例如：upload/20150328/2015032821561197.jpg
     *
     * @param file
     * @param prefix
     * @return
     */
    public static String extractFilePath(MultipartFile file, String prefix) {
        String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
        return extractFilePathByExtension(fileExt, prefix);
    }

    /**
     * 根据根据扩展名和后缀得到新的文件路径
     *
     * @param extension
     * @param prefix
     * @return
     */
    private static String extractFilePathByExtension(String extension, String prefix) {
        StringBuilder tempPath = new StringBuilder();
        tempPath.append(AppConstants.FILE_UPLOAD).append(RandomUtil.getRandomString(prefix));
        tempPath.append(".").append(extension);

        return tempPath.toString();
    }
}
