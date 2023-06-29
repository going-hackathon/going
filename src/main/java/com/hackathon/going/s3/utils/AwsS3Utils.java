package com.hackathon.going.s3.utils;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hackathon.going.domain.pinImage.entity.PinImage;
import com.hackathon.going.domain.postImage.entity.PostImage;
import com.hackathon.going.global.error.dto.ErrorCode;
import com.hackathon.going.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3Utils {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    private static final String S3_POST_DIRECTORY_NAME = "post";
    private static final String S3_PIN_DIRECTORY_NAME = "pin";

    public List<PostImage> uploadPostImage(String userAccountId, List<MultipartFile> files) {
        List<PostImage> postImages = new ArrayList<>();

        for(MultipartFile file : files) {
            // 메타데이터 설정
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            // 저장할 S3 버킷 디렉토리명 설정
            String originalFileName = file.getOriginalFilename();
            int idx = originalFileName.lastIndexOf(".");
            String extension =  originalFileName.substring(idx + 1);

            String fileName = S3_POST_DIRECTORY_NAME + "/" + UUID.randomUUID() + "." + extension;

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                log.error("S3 파일 업로드에 실패하였습니다. {}", ErrorCode.S3_FILE_UPLOAD_FAILED.getMessage());
                throw new BusinessException(ErrorCode.FILE_UPLOAD_ERROR);
            }
            String imageUrl = amazonS3Client.getUrl(bucket, fileName).toString();
            postImages.add(PostImage.builder().url(imageUrl).build());
        }
        return postImages;
    }

    public List<PinImage> uploadPinImage(Long pinId, List<MultipartFile> files) {
        List<PinImage> pinImages = new ArrayList<>();

        for(MultipartFile file : files) {
            // 메타데이터 설정
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            // 저장할 S3 버킷 디렉토리명 설정
            String originalFileName = file.getOriginalFilename();
            int idx = originalFileName.lastIndexOf(".");
            String extension =  originalFileName.substring(idx + 1);

            String fileName = S3_PIN_DIRECTORY_NAME + "/" + UUID.randomUUID() + "." + extension;

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                log.error("S3 파일 업로드에 실패하였습니다. {}", ErrorCode.S3_FILE_UPLOAD_FAILED.getMessage());
                throw new BusinessException(ErrorCode.FILE_UPLOAD_ERROR);
            }
            String imageUrl = amazonS3Client.getUrl(bucket, fileName).toString();
            pinImages.add(PinImage.builder().url(imageUrl).build());
        }
        return pinImages;
    }

    public void deleteImage(String imageUrl) {
        String fileKey = S3_POST_DIRECTORY_NAME + "/" + imageUrl.split("/")[4];
        if (amazonS3Client.doesObjectExist(bucket, fileKey)) {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileKey));
        }
    }
}
