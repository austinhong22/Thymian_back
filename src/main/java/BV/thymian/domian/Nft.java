package BV.thymian.domian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nft")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Long idx;

    @Column(name = "user_address", nullable = false, length = 100)
    private String userAddress;

    @Column(name = "image_link", nullable = false, length = 500)
    private String imageLink;

    @Column(name = "image_name", nullable = false, length = 100)
    private String imageName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "tags", length = 200)
    private String tags;

    @Column(name = "category", length = 50)
    private String category;
}
