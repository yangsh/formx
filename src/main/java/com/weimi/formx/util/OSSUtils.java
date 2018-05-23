package com.weimi.formx.util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.weimi.formx.common.config.oss.OSSConfig;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * Created by yangsh on 2018-05-22
 */
public class OSSUtils {

    private OSSClient ossClient;

    public OSSUtils() {
        CredentialsProvider cp = new DefaultCredentialProvider(OSSConfig.accessKeyId, OSSConfig.accessKeySecret);
        ossClient = new OSSClient(OSSConfig.endpoint, cp, new ClientBuilderConfiguration());
    }

    public void destory() {
        ossClient.shutdown();
    }

    public String uploadImage(InputStream is) {
        String key = System.currentTimeMillis() + StringUtils.getRandomString(6) + ".png";
        ossClient.putObject(OSSConfig.bucketName, key, is);
        return key;
    }

    /**
     * 获取 URL 连接
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为 1 小时
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(OSSConfig.bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
