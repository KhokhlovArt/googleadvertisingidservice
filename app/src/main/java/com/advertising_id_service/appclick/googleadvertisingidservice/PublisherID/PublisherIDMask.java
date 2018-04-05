package com.advertising_id_service.appclick.googleadvertisingidservice.PublisherID;

public class PublisherIDMask {
    private String prefix;
    private String seporator;
    private String extension;

    public PublisherIDMask(String prefix, String seporator, String extension) {
        this.prefix = prefix;
        this.seporator = seporator;
        this.extension = extension;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSeporator() {
        return seporator;
    }

    public String getExtension() {
        return extension;
    }
}
