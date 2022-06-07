package com.example.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.example.service.model.Image;
import com.example.service.model.PrctureMetadataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片或视频中提取EXIF,ICC等其他元数据信息
 */
@Slf4j
@Component
public class PictureInformationUtil {

    @Autowired
    private  StringInterceptUtil stringInterceptUtil;
//    @Autowired
//    private FileUtil fileUtil;

    /**
     * 获取图片中全部信息（未处理）
     * @param imager
     * @return
     * @throws Exception
     */
    public List<PrctureMetadataDTO> pictureInformationUtil(MultipartFile imager) throws Exception {
        List<PrctureMetadataDTO> prctureMetadataDTOList = new ArrayList();
        Metadata                 metadata               = JpegMetadataReader.readMetadata((InputStream) imager);
        //输出所有附加属性数据
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                log.info("名称："+tag.getTagName()+"========内容："+tag.getDescription());
                PrctureMetadataDTO prctureMetadataDto = new PrctureMetadataDTO();
                prctureMetadataDto.setName(tag.getTagName());
                prctureMetadataDto.setDescription(tag.getDescription());
                prctureMetadataDTOList.add(prctureMetadataDto);
            }
        }
        return prctureMetadataDTOList;
    }

    /**
     * 获取图片部分所需信息
     * @param imager
     * @return
     */
    public  Image pictureInformation(MultipartFile imager){
        try {
            Image watermarkDto = new Image();

            Metadata metadata = ImageMetadataReader.readMetadata(MultipartFileToFile(imager));
            for(Directory directory : metadata.getDirectories()){
                for (Tag tag : directory.getTags()){
                    //log.info("名称："+tag.getTagName()+"========内容："+tag.getDescription());
                    //获取纬度
                    if("GPS Latitude".equals(tag.getTagName())){
                        log.info("获取纬度=="+tag.getDescription());
                        watermarkDto.setLatitude(String.valueOf(dfm2LatLng(tag.getDescription())));
                    }
                    //获取经度
                    if("GPS Longitude".equals(tag.getTagName())){
                        log.info("获取经度=="+tag.getDescription());
                        watermarkDto.setLongitude(String.valueOf(dfm2LatLng(tag.getDescription())));
                    }
                    //获取拍摄时间
                    if("File Modified Date".equals(tag.getTagName())){
                        watermarkDto.setAbootTime("上传时间："+tag.getDescription());
                    }
                    //文件拍摄时间
                    if("Date/Time".equals(tag.getTagName())){
                        watermarkDto.setShotTime("拍摄时间："+tag.getDescription());
                    }
                    //拍摄设备名
                    if("Model".equals(tag.getTagName())){
                        watermarkDto.setEquipmentName("设备名："+tag.getDescription());
                    }
                    //获取文件名
                    if("File Name".equals(tag.getTagName())){
                        watermarkDto.setAiteName("文件名："+tag.getDescription());
                    }
                    //文件大小
                    if("File Size".equals(tag.getTagName())){
                        //String size = tag.getDescription().substring(0,tag.getDescription().length()-6);
                        String size = getNetFileSizeDescription(Long.parseLong(stringInterceptUtil.get_StringNum(tag.getDescription())));
                        watermarkDto.setFileSize("文件大小："+size);
                    }
                }
            }
            return watermarkDto;
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 经纬度格式  转换为  度分秒格式转换
     * @param point 坐标点
     * @return
     */
    public  String pointToLatlong(String point) {
        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double fen = Double.parseDouble(point.substring(point.indexOf("°")+1, point.indexOf("'")).trim());
        Double miao = Double.parseDouble(point.substring(point.indexOf("'")+1, point.indexOf("\"")).trim());
        Double duStr = du + fen / 60 + miao / 60 / 60 ;
        return duStr.toString();
    }

    /**
     * 经纬度转换 ，度分秒转度
     * @param jwd
     * @author LiuJie
     * @return
     */
    public  String Dms2D(String jwd){
        if(jwd != null && (jwd.contains("°"))){//如果不为空并且存在度单位
            //计算前进行数据处理
            jwd = jwd.replace("E", "").replace("N", "").replace(":", "").replace("：", "");
            double d=0,m=0,s=0;
            d = Double.parseDouble(jwd.split("°")[0]);
            //不同单位的分，可扩展
            if(jwd.contains("′")){//正常的′
                m = Double.parseDouble(jwd.split("°")[1].split("′")[0]);
            }else if(jwd.contains("'")){//特殊的'
                m = Double.parseDouble(jwd.split("°")[1].split("'")[0]);
            }
            //不同单位的秒，可扩展
            if(jwd.contains("″")){//正常的″
                //有时候没有分 如：112°10.25″
                s = jwd.contains("′")?Double.parseDouble(jwd.split("′")[1].split("″")[0]):Double.parseDouble(jwd.split("°")[1].split("″")[0]);
            }else if(jwd.contains("''")){//特殊的''
                //有时候没有分 如：112°10.25''
                s = jwd.contains("'")?Double.parseDouble(jwd.split("'")[1].split("''")[0]):Double.parseDouble(jwd.split("°")[1].split("''")[0]);
            }
            jwd = String.valueOf(d+m/60+s/60/60);//计算并转换为string
            //使用BigDecimal进行加减乘除
	/*BigDecimal bd = new BigDecimal("60");
	BigDecimal d = new BigDecimal(jwd.contains("°")?jwd.split("°")[0]:"0");
	BigDecimal m = new BigDecimal(jwd.contains("′")?jwd.split("°")[1].split("′")[0]:"0");
	BigDecimal s = new BigDecimal(jwd.contains("″")?jwd.split("′")[1].split("″")[0]:"0");
	//divide相除可能会报错（无限循环小数），要设置保留小数点
	jwd = String.valueOf(d.add(m.divide(bd,6,BigDecimal.ROUND_HALF_UP)
            .add(s.divide(bd.multiply(bd),6,BigDecimal.ROUND_HALF_UP))));*/
        }
        return jwd;
    }

    /***
     * 经纬度坐标格式转换（* °转十进制格式）
     * @param gps
     * @return gps_dou
     */
    public  double latLng2Decimal(String gps) {
        String a       = gps.split("°")[0].replace(" ", "");
        String b       = gps.split("°")[1].split("'")[0].replace(" ", "");
        String c       = gps.split("°")[1].split("'")[1].replace(" ", "").replace("\"", "");
        double gps_dou = Double.parseDouble(a) + Double.parseDouble(b) / 60 + Double.parseDouble(c) / 60 / 60;
        return gps_dou;
    }

    /**
     * 将经纬度转换为度分秒格式
     * @param du 116.418847
     * @return 116°25'7.85"
     */
    public  String changeToDFM(double du) {
        int du1 = (int) du;
        double tp = (du - du1) * 60;
        int fen = (int) tp;
        String miao = String.format("%.2f", Math.abs(((tp - fen) * 60)));
        return du1 + "°" + Math.abs(fen) + "'" + miao + "\"";
    }

    /**
     * 度分秒转经纬度
     *
     * @param dms 116°25'7.85"
     * @return 116.418847
     */
    public  double dfm2LatLng(String dms) {
        if (dms == null) return 0;
        try {
            dms = dms.replace(" ", "");
            String[] str2 = dms.split("°");
            if (str2.length < 2) return 0;
            int d = Integer.parseInt(str2[0]);
            String[] str3 = str2[1].split("\'");
            if (str3.length < 2) return 0;
            int f = Integer.parseInt(str3[0]);
            String str4 = str3[1].substring(0, str3[1].length() - 1);

            double m = Double.parseDouble(str4);

            double fen = f + (m / 60);
            double du = (fen / 60) + Math.abs(d);
            if (d < 0) du = -du;
            System.out.println(Double.parseDouble(String.format("%.7f", du)));
            return Double.parseDouble(String.format("%.7f", du));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 度分秒转经纬度
     * @param dms 116°25'7.85"
     * @return 116.418847
     */
    public  String changeToDu(String dms) {
        if (dms == null) return "";
        try {
            dms = dms.replace(" ", "");
            String[] str2 = dms.split("°");
            if (str2.length < 2) return "";
            int d = Integer.parseInt(str2[0]);
            String[] str3 = str2[1].split("\'");
            if (str3.length < 2) return "";
            int f = Integer.parseInt(str3[0]);
            String str4 = str3[1].substring(0, str3[1].length() - 1);
            double m = Double.parseDouble(str4);

            double fen = f + (m / 60);
            Double du = (fen / 60) + Math.abs(d);
            if (d < 0) du = -du;
            return du.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *  把byte转化为KB、MB、GB
     * @param size
     * @return
     */
    public  String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.00");
        if (size >= 1024L * 1024L * 1024L * 1024L) {
            double i = (size / (1024.0 * 1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("TB");
        } else if (size >= 1024L * 1024L * 1024L) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        } else if (size >= 1024L * 1024L) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        } else if (size >= 1024L) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        } else if (size < 1024L) {
            bytes.append("0B");
        }
        return bytes.toString();
    }

    /**
     * 将MultipartFile转换为File
     * @param multiFile
     * @return
     */
    public  File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若须要防止生成的临时文件重复,能够在文件名后添加随机码
        try {
//            File file = File.createTempFile(fileName, prefix);
            String url = "C:\\Users\\qing\\Desktop\\temp\\" + fileName;
//            File file = new File(url,fileName);
            File file = new File(url);
            multiFile.transferTo(file);
            //在JVM退出时删除文件
//            file.deleteOnExit();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}