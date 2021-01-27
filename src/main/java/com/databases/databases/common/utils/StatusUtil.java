package com.databases.databases.common.utils;

public final class StatusUtil {

    public class Code {

        // Success code
        public static final int CREATE_SUCCESS = 0;
        public static final int DELETE_SUCCESS = 0;
        public static final int UPDATE_SUCCESS = 0;
        public static final int QUERY_SUCCESS = 0;

        // Fail code
        public static final int CREATE_FAIL = -1001;
        public static final int DELETE_FAIL = -1002;
        public static final int UPDATE_FAIL = -1003;
        public static final int QUERY_FAIL = -1004;

        // Error code
        public static final int TOKEN_ERROR = -2001;
        public static final int EXISTS_ERROR = -2002;
        public static final int NOT_EXISTS_ERROR = -2003;
        public static final int TYPE_ERROR = -2004;

        // Business Logic
        public static final int LOGIN_SUCCESS = 0;
        public static final int LOGIN_FAIL = -3001;
        public static final int CHANGE_PASSWORD_SUCCESS = 0;
        public static final int CHANGE_PASSWORD_FAIL = -3002;
        public static final int MEMBER_NOT_EMPTY = -3003;
        public static final int SUB_NOT_EMPTY = -3004;
        public static final int SUB_CYCLE = -3005;

        public static final int DETAIL_SUCCESS = 0;
        public static final int DETAIL_FAIL = -4001;
        public static final int AUTH_SUCCESS = 0;
        public static final int AUTH_FAIL = -4002;

        public static final int UPLOAD_SUCCESS = 0;
        public static final int UPLOAD_FAIL = -5001;
        public static final int DOWNLOAD_SUCCESS = 0;
        public static final int DOWNLOAD_FAIL = -5002;
        public static final int PRE_UPLOAD_SUCCESS = 0;
        public static final int PRE_UPLOAD_FAIL = -5003;
    }

    public class Description {

        // Success Description
        public static final String CREATE_SUCCESS = "data.create.success";
        public static final String DELETE_SUCCESS = "data.delete.success";
        public static final String UPDATE_SUCCESS = "data.update.success";
        public static final String QUERY_SUCCESS = "data.query.success";

        // Fail Description
        public static final String CREATE_FAIL = "data.create.fail";
        public static final String DELETE_FAIL = "data.delete.fail";
        public static final String UPDATE_FAIL = "data.update.fail";
        public static final String QUERY_FAIL = "data.query.fail";

        // Error Description
        public static final String TOKEN_ERROR = "status.token.error";
        public static final String EXISTS_ERROR = "status.exists.error";
        public static final String NOT_EXISTS_ERROR = "status.not_exists.error";
        public static final String TYPE_ERROR = "status.type.error";

        // Business Logic
        public static final String LOGIN_SUCCESS = "status.login.success";
        public static final String LOGIN_FAIL = "status.login.fail";
        public static final String CHANGE_PASSWORD_SUCCESS = "status.change_pass.success";
        public static final String CHANGE_PASSWORD_FAIL = "status.change_pass.fail";
        public static final String MEMBER_NOT_EMPTY = "status.member_not_empty.fail";
        public static final String SUB_NOT_EMPTY = "status.sub_not_empty.fail";
        public static final String SUB_CYCLE = "status.sub_cycle.fail";

        public static final String DETAIL_SUCCESS = "status.detail.success";
        public static final String DETAIL_FAIL = "status.detail.fail";
        public static final String AUTH_SUCCESS = "status.auth.success";
        public static final String AUTH_FAIL = "status.auth.fail";

        public static final String UPLOAD_SUCCESS = "status.upload.success";
        public static final String UPLOAD_FAIL = "status.upload.fail";
        public static final String DOWNLOAD_SUCCESS = "status.download.success";
        public static final String DOWNLOAD_FAIL = "status.download.fail";
        public static final String PRE_UPLOAD_SUCCESS = "status.pre-upload.success";
        public static final String PRE_UPLOAD_FAIL = "status.pre-upload.fail";
    }
}
