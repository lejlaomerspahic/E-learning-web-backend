package elearning.app.dto.course;

import java.util.Date;

import lombok.Data;

@Data
public class CourseCreatedRequest {

    private String name;
    private String category;
    private String info;
    private String description;
    private String duration;
    private String level;
    private String imageUrl;
    private String videoId;
    private Date lastUpdated;
    private String language;
    private String icon;
}
