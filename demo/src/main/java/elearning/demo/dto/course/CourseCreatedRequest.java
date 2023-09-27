package elearning.demo.dto.course;

import java.sql.Date;

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

}
