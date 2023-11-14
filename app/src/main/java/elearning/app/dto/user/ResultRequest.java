package elearning.app.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultRequest {

    private Long quizId;
    private Long score;
}
