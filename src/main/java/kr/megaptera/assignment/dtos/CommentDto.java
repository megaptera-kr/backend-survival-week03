package kr.megaptera.assignment.dtos;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private String id;

    private String postId;

    private String content;

    @Override
    public String toString() {
        return "CommentDto{" +
               "id='" + id + '\'' +
               ", postId='" + postId + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}
