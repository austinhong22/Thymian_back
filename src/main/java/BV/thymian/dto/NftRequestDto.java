package BV.thymian.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NftRequestDto {

    @NotBlank(message = "userAddress(지갑 주소)는 필수입니다.")
    private String userAddress;

    @NotBlank(message = "imageLink는 필수입니다.")
    private String imageLink;

    @NotBlank(message = "imageName은 필수입니다.")
    private String imageName;

    private String description;

    @NotNull(message = "price(가격)은 필수입니다.")
    @Min(value = 0, message = "price는 0 이상이어야 합니다.")
    private Integer price;

    private String tags;

    private String category;
}
