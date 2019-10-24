package com.ts.internal.redis;

public class RedisConstants {

    public static final String STATUS_PROJECT_CREATED = "PROJECT_CREATED";
    public static final String STATUS_XTM_FAILURE = "XTMFailure";
    public static final String STATUS_REQUEST_RECEIVED = "Request Recieved";
    public static final String SOURCE_CALLBACK_SUCCESSFULLL = "SourceCallback Success";
    public static final String TRANSLATION_COMPLETED = "Translation Completed";
    public static final String PROJECT_FINISHED = "PROJECT_FINISHED";

    public static final String READY_TO_DOWNLOAD= "READY_TO_DOWNLOAD";
    public static final String DOWNLOAD_XTM = "DOWNLOADED_FROM_XTM";
    public static final String DOWNLOAD_COMPLETED = "DOWNLOAD_COMPLETED";

    public static final String DATE_FORMAT = "MMM d, yyyy HH:mm:ss";
    
    public static final int TIME_TO_EXPIRE = 30;

    private RedisConstants() {
        throw new IllegalStateException("RedisConstants Utility class");
    }

}
