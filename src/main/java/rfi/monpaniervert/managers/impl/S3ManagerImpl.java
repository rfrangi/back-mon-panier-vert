package rfi.monpaniervert.managers.impl;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import rfi.monpaniervert.enums.FileType;
import rfi.monpaniervert.exceptions.S3ClientException;
import rfi.monpaniervert.managers.S3Manager;

/**
 * Implementation of {@link S3Client}.
 */
@Component
@PropertySource("classpath:application.properties")
public class S3ManagerImpl implements S3Manager {
    private static final Logger LOGGER = LoggerFactory.getLogger(S3ManagerImpl.class);
    private static final String S3_KEY_WITH_SUBDIR = "{0}/{1}/{2}";
    private static final String S3_KEY = "{0}/{1}";
    private static final String FILENAME_SEPARATOR = "_";
 
    private AmazonS3 s3Bean;
    
	@Value("${s3.bucket}")
    private String bucket;
	
	@Value("${s3.directory}")
	private String subDirectory;
    
    public S3ManagerImpl( AmazonS3 s3Bean) {
        this.s3Bean = s3Bean;
    }
    
    @Override
    public String putFileWithNoTimestamp(File file, String fileName, FileType type) {
        return put(file, fileName, type, false);
    }

    @Override
    public String putFile(String content, String fileName, FileType type) {
        return put(content, fileName, type, true);
    }

    @Override
    public String putFile(File file, String fileName, FileType type) {
        return put(file, fileName, type, true);
    }

    @Override
    public String putFile(InputStream inputStream, String fileName, FileType type) {
        return put(inputStream, fileName, type, true);
    }

    private boolean isSVG(String fileName) {
    	int i = fileName.lastIndexOf('.');
    	if (i > 0) {
    	    return "svg".equals(fileName.substring(i+1));
    	}
    	return false;
    }
    
    private ObjectMetadata getObjectMetadataForSVG() {
		final ObjectMetadata objectMetadata =new ObjectMetadata();
		objectMetadata.setContentType("image/svg+xml");
		return objectMetadata;
    }

    private String put(Object file, String fileName, FileType type, boolean hasTimestamp) {
        try {
            String fileKey = getKey(type.getName(), hasTimestamp ? new Date().getTime() + FILENAME_SEPARATOR + fileName : "" + fileName);
            if (file instanceof File) {
            	
            	if(this.isSVG(fileName)) {
            		s3Bean.putObject(new PutObjectRequest(this.bucket, fileKey, (File) file)
            	            .withMetadata(this.getObjectMetadataForSVG()));
            	} else {
            		s3Bean.putObject(this.bucket, fileKey, (File) file);
            	}
            } else if (file instanceof String) {
                final String fileString = (String) file;
            	if(this.isSVG(fileName)) {
                    final byte[] contentBytes = fileString.getBytes(com.amazonaws.util.StringUtils.UTF8);
                    final ObjectMetadata metadata = this.getObjectMetadataForSVG();
                    metadata.setContentLength(contentBytes.length);
                    s3Bean.putObject(new PutObjectRequest(this.bucket, fileKey, new ByteArrayInputStream(contentBytes), metadata));
            	} else {
            		s3Bean.putObject(this.bucket, fileKey, fileString);
            	}
            } else if (file instanceof InputStream) {
                fileKey = getKey(type.getName(), fileName);
                if(this.isSVG(fileName)) {
                    s3Bean.putObject(this.bucket, fileKey, (InputStream) file,
                    		this.getObjectMetadataForSVG());
                }else {
                    s3Bean.putObject(this.bucket, fileKey, (InputStream) file,
                            new ObjectMetadata());
                }

            } else {
                throw new IllegalArgumentException("Incorrect file type when uploading a file");
            }
            return fileKey;
        } catch (AmazonS3Exception e) {
            LOGGER.error("Error while uploading file {} on bucket {}", fileName, this.bucket);
            throw new S3ClientException("file:" + fileName + " could not be uploaded on bucket", e);
        }
    }
    
    

    @Override
    public void deleteFile(String fileKey) {
        try {
            s3Bean.deleteObject(this.bucket, fileKey);
        } catch (AmazonS3Exception e) {
            LOGGER.error("Error while deleting file {} from bucket {}", fileKey, this.bucket);
            throw new S3ClientException("file: " + fileKey + " could not be deleted from bucket", e);
        }
    }

    @Override
    public String getFile(String fileKey) {
        try {
            return s3Bean.getObjectAsString(this.bucket, fileKey);
        } catch (AmazonS3Exception e) {
            LOGGER.error("Error while downloading file {} from bucket {}", fileKey, this.bucket);
            throw new S3ClientException("file: " + fileKey + " could not be found", e);
        }
    }

    @Override
    public Optional<InputStream> getFile(String fileName, FileType type) {
        try {
            if (isFileExists(fileName, type)) {
                return Optional.ofNullable(s3Bean.getObject(this.bucket, getKey(type.getName(),
                        fileName)).getObjectContent());
            } else {
                return Optional.empty();
            }
        } catch (AmazonServiceException e) {
            LOGGER.error("Error while searching file {} from bucket {}", fileName, this.bucket);
            throw new S3ClientException("file: " + fileName + " could not be found", e);
        }
    }

    /**
     * gets key of  a document in S3
     *  @param fileName : the name of the file to get from the bucket.
     * @param type : the type of thefile to get from the bucket.
     * @return key of document
     */

    private String getKey(String type, String fileName) {
        if(this.subDirectory != null && !this.subDirectory.equals("")) {
            return MessageFormat.format(S3_KEY_WITH_SUBDIR, this.subDirectory, type, fileName);
        } else {
            return MessageFormat.format(S3_KEY, type, fileName);
        }
    }

    @Override
    public boolean isFileExists(String fileName, FileType type) {
        return s3Bean.doesObjectExist(this.bucket, getKey(type.getName(), fileName));
    }

    @Override
    public List<String> getUrls(String fileKey, FileType type) {
        try {
            return s3Bean.listObjects(this.bucket, getKey(type.getName(), fileKey))
                    .getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
        } catch (AmazonS3Exception e) {
            LOGGER.error("Error while searching file {} from bucket {}", fileKey, this.bucket);
            throw new S3ClientException("file: " + fileKey + " could not be found", e);
        }
    }

	@Override
	public Optional<String> getUrl(String fileKey, FileType type) {
		try {
			List<S3ObjectSummary> objectSummaries = s3Bean.listObjects(this.bucket, getKey(type.getName(),
					fileKey)).getObjectSummaries();
			if (!objectSummaries.isEmpty()) {
				return Optional.ofNullable(objectSummaries.get(0).getKey());
			}else {
				return Optional.empty();
			}
		} catch (AmazonS3Exception e) {
			LOGGER.error("Error while searching file {} from bucket {}", fileKey, this.bucket);
			throw new S3ClientException("file: " + fileKey + " could not be found", e);
		}
	}
}
