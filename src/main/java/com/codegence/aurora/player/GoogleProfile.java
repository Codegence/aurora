package com.codegence.aurora.player;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleProfile {
    @Getter
    @Setter
    public static class Email {

        private String value;
        private String type;
    }

    @Getter
    @Setter
    public static class Name {
        private String familyName;
        private String givenName;
    }

    @Getter
    @Setter
    public static class Image {
        private String url;
        private boolean isDefault;
    }

    private String kind;
    private String etag;
    private String gender;
    private List<Email> emails;
    private String objectType;
    private String id;
    private String displayName;
    private Name name;
    private String url;
    private Image image;
    private boolean isPlusUser;
    private String language;
    private int circledByCount;
    private boolean verified;
    private String domain;


}

