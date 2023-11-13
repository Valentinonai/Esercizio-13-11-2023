package Esercizio10112023Progetto.Esercizio10112023Progetto.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserConfig {
    @Bean
    public Cloudinary cloudinaryUpload(@Value("${CLOUDINARY.NAME}")String name, @Value("${CLOUDINARY.API_KEY}")String api_key, @Value("${CLOUDINARY.SECRET}")String secret){

        Map<String,String> config=new HashMap<>();
        config.put("cloud_name",name);
        config.put("api_key",api_key);
        config.put("api_secret",secret);
        return new Cloudinary(config);
    }
}
