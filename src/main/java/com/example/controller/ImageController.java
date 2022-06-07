package com.example.controller;
import com.example.service.ImageService;
import com.example.service.model.Image;
import com.example.utils.BaiDuMapUtil;
import com.example.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

/**
 * Author: qinbocheng
 * Date: 2022/5/1 16:19
 * Description:
 * History:
 * <author>    <time>          <version> <desc>
 * qinbocheng  2022/5/1 16:19 版本号    描述
 */
@Api(tags = "图片接口")
@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "获取图片详情")
    @ApiImplicitParam(name = "file",value = "图片",paramType = "File")
    @RequestMapping("/getImageDetail")
    public BaseResponse<Image> getImageDetail(MultipartFile file){
        Image image = imageService.getImageDetail(file);
        if (image.getLatitude() != null && image.getLongitude() != null)
        image.setAddress(BaiDuMapUtil.
                getAddress(Double.parseDouble(image.getLongitude()), Double.parseDouble(image.getLatitude())));
//        return new BaseResponse<Image>().returnOK(image);
        return BaseResponse.returnOK(image);
    }

    @ApiOperation(value = "上传图片")
    @RequestMapping("/uploadImage")
    public File uploadImage(MultipartFile file){
        System.out.println("2020020");
        return imageService.uploadImage(file);
    }

}
