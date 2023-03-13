package com.sda.study.springbootpractice.utils;

/**
 * Constant values used in this application
 * as a fixed values that can be used across all the classes
 */
public class Constants {
    public static class Audit {
        public static final String DEFAULT_AUDITOR = "SYSTEM";
    }
    public static class Security {
        public static final String AUTHORITY_ADMIN= "ROLE_ADMIN";
        public static final String AUTHORITY_TEACHER= "ROLE_TEACHER";
        public static final String AUTHORITY_STUDENT= "ROLE_STUDENT";
    }
}
