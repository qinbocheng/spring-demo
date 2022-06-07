package com.example.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author: qinbocheng
 * Date: 2022/5/1 16:37
 * Description:
 * History:
 * <author>    <time>          <version> <desc>
 * qinbocheng  2022/5/1 16:37 版本号    描述
 */
@Data
@ApiModel("图片详情类")
public class Image {
    @ApiModelProperty(value = "设备名")
    private String equipmentName;//设备名
    @ApiModelProperty(value = "文件名")
    private String aiteName; //文件名
    @ApiModelProperty(value = "电表编号")
    private String ammaterNumber;//电表编号
    @ApiModelProperty(value = "文件大小")
    private String fileSize;//文件大小
    @ApiModelProperty(value = "纬度")
    private String latitude;//纬度
    @ApiModelProperty(value = "经度")
    private String longitude;//经度
    @ApiModelProperty(value = "拍摄时间")
    private String shotTime;//拍摄时间
    @ApiModelProperty(value = "上传时间")
    private String abootTime;//上传时间
    @ApiModelProperty(value = "地址",notes = "做注释性声明")
    private String address;//地址

}
