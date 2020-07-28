package com.smartfox.anonymizer.batch.anonymize.model;

/**
 * Source class - to model the CSV source file
 *
 * @author hdargaye
 *
 */
public class SourceItem {

    private String column;
    private String idName;
    private String schema;
    private SourceType sourceType;
    private String table;

    public String getColumn() {
        return this.column;
    }

    public String getIdName() {
        return this.idName;
    }

    public String getSchema() {
        return this.schema;
    }

    public SourceType getSourceType() {
        return this.sourceType;
    }

    public String getTable() {
        return this.table;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Source [column=" + this.column + ", schema=" + this.schema + ", sourceType=" + this.getSourceType() + ", table=" + this.table + "]";
    }

}
