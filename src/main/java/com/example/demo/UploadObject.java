package com.example.demo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class UploadObject {

    public static String upload(String fileName, MultipartFile multipartFile)
            throws IOException {

        Regions clientRegion = Regions.EU_WEST_2;



        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                                                 .withRegion(clientRegion)
                                                 .build();


        String bucket = "auction-photos-aw";
        String contentType = "image/*";
        InputStream inputStream = multipartFile.getInputStream();
        boolean isPublic = true;


        if (inputStream != null) {
            try {
                ObjectMetadata meta = new ObjectMetadata();
                meta.setContentLength(inputStream.available());
                meta.setContentType(contentType);

                PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, inputStream, meta);

                putObjectRequest = putObjectRequest.withCannedAcl(
                            CannedAccessControlList.PublicRead);

                s3Client.putObject(putObjectRequest);

            } catch (Exception e) {
                System.out.println("Error uploading file on S3: " + e);
            }
        } else {
            System.out.println("Content InputStream is null. Not Uploading on S3");
        }
        return null;
    }
}
