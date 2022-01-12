package rfi.monpaniervert.managers;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import rfi.monpaniervert.enums.FileType;

public interface S3Manager {


    /**
     * Put a string content in a S3 bucket.
     *
     * @param content  content file
     * @param fileName: the name of the file to save
     * @param type: the type, used to select sub-directory in the bucket
     * @return the Url of the file uploaded.
     */
    String putFile(String content, String fileName, FileType type);

    /**
     * Put a file in a S3 bucket.
     *
     * @param file: the file to save in the bucket.
     * @param fileName: the name of the file to save
     * @param type: the type, used to select sub-directory in the bucket
     * @return the Url of the file uploaded.
     */
    String putFile(File file, String fileName, FileType type);

    /**
     * Put file on the bucket
     *
     * @param inputStream     : the file to save on the bucket.
     * @param fileName : the name of the file to save on the bucket.
     * @param type     : the type of the file to save on the bucket.
     * @return the Url of the file uploaded.
     */
    String putFile(InputStream inputStream, String fileName, FileType type);

    /**
     * Delete a file from the bucket
     *
     * @param fileKey: the key of the file (s3 path) to delete
     */
    void deleteFile(String fileKey);

    /**
     * Downloads a file from the bucket
     *
     * @param fileName file name
     * @return file content
     */
    String getFile(String fileName);

    /**
     * get file from the bucket
     *  @param fileName : the name of the file to get from the bucket.
     * @param type : the type of thefile to get from the bucket.
     * @return {@link S3ObjectInputStream]
     */
    Optional<InputStream> getFile(String fileName, FileType type);

    /**
     * checks if file exists
     *  @param fileName : the name of the file to get from the bucket.
     * @param type : the type of thefile to get from the bucket.
     * @return true if the file exists in the S3
     */
    boolean isFileExists(String fileName, FileType type);

    /**
	 * get list urls files from the bucket
	 * @param fileKey : the key of the file (s3 path) to get from the bucket.
	 */
	List<String> getUrls(String fileName , FileType type);

	/**
	 * get url file from the bucket
	 * @param fileKey : the key of the file (s3 path) to get from the bucket.
	 */
	Optional<String> getUrl(String fileKey, FileType type);

    /**
     * Put a file in a S3 bucket.
     *
     * @param file: the file to save in the bucket.
     * @param fileName: the name of the file to save
     * @param type: the type, used to select sub-directory in the bucket
     * @return the Url of the file uploaded.
     */
    String putFileWithNoTimestamp(File file, String fileName, FileType type);
}