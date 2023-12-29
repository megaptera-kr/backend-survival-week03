package kr.megaptera.assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String id;
    private String content;
    private String title;


    @Override
    public String toString() {
        return "PostDto{" +
               "id='" + id + '\'' +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}
