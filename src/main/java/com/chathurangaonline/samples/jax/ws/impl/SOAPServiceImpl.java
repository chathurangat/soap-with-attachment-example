package com.chathurangaonline.samples.jax.ws.impl;

import com.chathurangaonline.samples.jax.ws.SOAPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@WebService
@SOAPBinding
@MTOM
public class SOAPServiceImpl implements SOAPService{

    private static final Logger logger = LoggerFactory.getLogger(SOAPServiceImpl.class);
    private static final String FILE_PATH = "/home/chathuranga/Pictures/webService/";

    @Override
    @WebMethod
    public String uploadImage(Image image,String imageName){
        if (image != null) {
            try {
                File imageFile = new File(FILE_PATH + imageName);
                ImageIO.write((BufferedImage) image, "gif", imageFile);
            } catch (IOException e) {
                throw new WebServiceException("Upload Failed");
            }
            return FILE_PATH + imageName;
        }
        throw new WebServiceException("No image data to upload.");
    }

    @Override
    @WebMethod
    public Image downloadImage(String imagePath) {
        logger.info(" == request for downloading the image on path [{}]== ",imagePath);
        if (imagePath != null) {
            try {
                File imageFile = new File(imagePath);
                return ImageIO.read(imageFile);
            } catch (IOException e) {
                throw new WebServiceException("No Image found in the path "+imagePath);
            }
        }
        throw  new WebServiceException("Image Path is required");
    }
}
