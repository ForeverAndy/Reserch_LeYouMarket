package com.leyou.upload.service.serviceimpl;

//使用fastdfs相关的
//import com.github.tobato.fastdfs.domain.StorePath;
//import com.github.tobato.fastdfs.service.FastFileStorageClient;


import com.leyou.myexception.LyException;
import com.leyou.myexception.MyException;
import leyou.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

//    @Autowired
//    private FastFileStorageClient storageClient;


    @Value("${qinniu.ACCESS_KEY}")
    private static  String ACCESS_KEY;

    @Value("${qinniu.SECRET_KEY}")
    private static  String SECRET_KEY;

    //要上传的空间
    @Value("${qinniu.bucketname}")
    private static  String bucketname;

    //密钥
//    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static final Logger logger= LoggerFactory.getLogger(UploadServiceImpl.class);

    /**
     *     支持上传的文件类型
     */
    private static final List<String> suffixes = Arrays.asList("image/png","image/jpeg","image/jpg");


    @Override
    public String upload(MultipartFile file) {
        try {

            //对比文件属性
            String type = file.getContentType();
            if (!suffixes.contains(type)) {
                logger.info("上传文件失败，文件类型不匹配：{}", type);
                throw new MyException(LyException.FILE_UPLOAD_ERROR);

            }

            //查看是否有文件
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                logger.info("上传失败，文件内容不符合要求");
                throw new MyException(LyException.FILE_UPLOAD_ERROR);

            }


            //准备目标路径
            File dest = new File("F:\\Work\\Java-Work\\Reserch_LeYouMarket\\leyou-manage-web\\dist\\static\\upload_file_sever",file.getOriginalFilename());

            //保存文件到本地
            file.transferTo(dest);

            //返回路径
            return "http://image.leyou.com/static/upload_file_sever/"+file.getOriginalFilename();
        } catch (IOException e) {
            //上传失败
            log.error("上传文件失败！",e);
            throw new MyException(LyException.FILE_UPLOAD_ERROR);
        }

        //返回路径

    }

    @Override
    public String upload_DFS(MultipartFile file) {
//        /**
//         * 1.图片信息校验
//         *      1)校验文件类型
//         *      2)校验图片内容
//         * 2.保存图片
//         *      1)生成保存目录
//         *      2)保存图片
//         *      3)拼接图片地址
//         */
//        try {
//            String type = file.getContentType();
//            if (!suffixes.contains(type)) {
//                logger.info("上传文件失败，文件类型不匹配：{}", type);
//                return null;
//            }
//            BufferedImage image = ImageIO.read(file.getInputStream());
//            if (image == null) {
//                logger.info("上传失败，文件内容不符合要求");
//                return null;
//            }
//
////            File dir = new File("G:\\LeYou\\upload");
////            if (!dir.exists()){
////                dir.mkdirs();
////            }
////            file.transferTo(new File(dir, Objects.requireNonNull(file.getOriginalFilename())));
//
//            StorePath storePath = this.storageClient.uploadFile(
//                    file.getInputStream(), file.getSize(), getExtension(file.getOriginalFilename()), null);
//
//            //String url = "http://image.leyou.com/upload/"+file.getOriginalFilename();
//            String url = "http://image.leyou.com/"+storePath.getFullPath();
////            System.out.println(url);
//            return url;
//        }catch (Exception e){
//            return null;
//        }
        return null;
    }

    @Override
    public String upload_qinu(MultipartFile file) {



        try {

            //对比文件属性
            String type = file.getContentType();
            if (!suffixes.contains(type)) {
                logger.info("上传文件失败，文件类型不匹配：{}", type);
                throw new MyException(LyException.FILE_UPLOAD_ERROR);

            }

            //查看是否有文件
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                logger.info("上传失败，文件内容不符合要求");
                throw new MyException(LyException.FILE_UPLOAD_ERROR);

            }
            //默认不指定key的情况下，以文件内容的hash值作为文件名
//
//            UploadManager uploadManager = new UploadManager();
//            String key = null;
//            String upToken = auth.uploadToken(bucketname);
//            FileInputStream inputStream=(FileInputStream)file.getInputStream();
//            byte[] uploadBytes = new byte[inputStream.available()];
//            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
//            Response res = uploadManager.put(byteInputStream, null,upToken);
//




            //返回路径
            return "http://image.leyou.com/static/upload_file_sever/"+file.getOriginalFilename();
        } catch (IOException e) {
            //上传失败
            log.error("上传文件失败！",e);
            throw new MyException(LyException.FILE_UPLOAD_ERROR);
        }

        //返回路径
    }

    public String getExtension(String fileName){
        return StringUtils.substringAfterLast(fileName,".");
    }
}
