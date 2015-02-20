package com.chathurangaonline.samples.jax.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.awt.*;

@WebService
public interface SOAPService {

    @WebMethod
    String uploadImage(Image image,String ImageName);

    @WebMethod
    Image downloadImage(String imagePath);
}

