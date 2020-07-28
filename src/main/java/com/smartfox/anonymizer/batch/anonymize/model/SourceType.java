package com.smartfox.anonymizer.batch.anonymize.model;

/**
 * Source Type - to map the type of the column - then will select strategy for
 * the replacement of values
 * 
 * @author hdargaye
 *
 */
public enum SourceType {
    ADDRESS, EMAIL, NAME, PHONE, POB, COMMENT, SURNAME, POCO, DOB,CITY , DEPTDOB, ERASE}
