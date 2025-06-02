package BV.thymian.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NftResponseDto {

    private Long idx;
    private String userAddress;
    private String imageLink;
    private String imageName;
    private String description;
    private Integer price;
    private String tags;
    private String category;
}