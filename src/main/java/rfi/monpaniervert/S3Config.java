package rfi.monpaniervert;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.core.io.s3.SimpleStorageProtocolResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@PropertySource("classpath:application.properties")
public class S3Config {

    @Bean
    @Primary
    public AmazonS3 amazonS3(
    		@Value("${s3.accesskey}") String accesskey,
    		@Value("${s3.secretkey}") String secretkey) {
        AWSCredentials credentials = new BasicAWSCredentials("AKIAYU3U43E2RJIUJDQE", "5qngtihlh7v3IgRM7WsY1E095r3xjJqIFi937g8y");
        return AmazonS3ClientBuilder
      		  .standard()
      		  .withCredentials(new AWSStaticCredentialsProvider(credentials))
      		  .withRegion(Regions.EU_WEST_3)
      		  .build();
    }

    @Primary
    @Bean
    public ResourceLoader resourceLoader(AmazonS3 amazonS3) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        resourceLoader.addProtocolResolver(new SimpleStorageProtocolResolver(amazonS3));
        return resourceLoader;
    }
}
