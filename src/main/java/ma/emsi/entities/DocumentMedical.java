package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class DocumentMedical extends BaseEntity {
    private String type;
    private String path;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}
