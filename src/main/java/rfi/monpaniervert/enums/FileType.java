package rfi.monpaniervert.enums;


/**
 * Defines a S3 file type, each type has its specific folder.
 */
public enum FileType {
    IMG_COMPAGNIE("IMG_COMPAGNIE"),
    IMG_SITE("IMG_SITE"),
    IMG_USER("IMG_USER");
	
    private final String name;

    FileType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
