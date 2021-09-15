package com.ytrue.yadmin.utils;

/**
 * @author ytrue
 * @date 2021/6/17 10:33
 * @description FileUtils 文件工具
 */
public class FileUtils {

    /**
     * 1024
     */
    private final static int ONE_THOUSAND_AND_TWENTY_FOUR = 1024;

    /**
     * 字节 转换为B MB GB
     *
     * @param size 字节大小
     * @return
     */
    public static String getPrintSize(long size) {
        long rest;
        if (size < ONE_THOUSAND_AND_TWENTY_FOUR) {
            return size + "B";
        } else {
            size /= ONE_THOUSAND_AND_TWENTY_FOUR;
        }

        if (size < ONE_THOUSAND_AND_TWENTY_FOUR) {
            return size + "KB";
        } else {
            rest = size % ONE_THOUSAND_AND_TWENTY_FOUR;
            size /= ONE_THOUSAND_AND_TWENTY_FOUR;
        }

        if (size < ONE_THOUSAND_AND_TWENTY_FOUR) {
            size = size * 100;
            return size / 100 + "." + rest * 100 / ONE_THOUSAND_AND_TWENTY_FOUR % 100 + "MB";
        } else {
            size = size * 100 / ONE_THOUSAND_AND_TWENTY_FOUR;
            return size / 100 + "." + size % 100 + "GB";
        }
    }
}
