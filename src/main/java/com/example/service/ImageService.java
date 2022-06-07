package com.example.service;

import com.example.service.model.Image;
import com.example.utils.PictureInformationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Author: qinbocheng
 * Date: 2022/5/1 16:20
 * Description:
 * History:
 * <author>    <time>          <version> <desc>
 * qinbocheng  2022/5/1 16:20 版本号    描述
 */
@Service
public class ImageService {

    @Autowired
    private PictureInformationUtil pictureInformationUtil;

    public Image getImageDetail(MultipartFile file) {
        return pictureInformationUtil.pictureInformation(file);
    }

    public File uploadImage(MultipartFile file) {
        return pictureInformationUtil.MultipartFileToFile(file);
    }
}
