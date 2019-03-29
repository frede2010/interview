package com.lajigaga.ThreadOptimization.ThreadPoolAndCountDownLatch;

/**
 * Created by lajigaga on 2019/3/18 0018.
 */
public class FileInfo {

    private String fileName;
    private String fileType;
    private Long fileSize;

    FileInfo(String fileName, String fileType, Long fileSize){
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
