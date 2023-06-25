package com.springboot.project.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.nio.file.Path;

@Component
public class FileUtil {
    public Resource load(String fileName, Path root){
        try{
                Path file=root.resolve(fileName);
                Resource resource=new UrlResource(file.toUri());
                if(resource.exists()||resource.isReadable()){
                    return resource;
                }else{
                    throw new RuntimeException("Couldnt read the file");
                }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error"+e.getMessage());
        }
    }

}
