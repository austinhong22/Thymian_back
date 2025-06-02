package BV.thymian.repository;

import BV.thymian.domian.Nft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NftRepository extends JpaRepository<Nft, Long> {
    List<Nft> findAllByUser_Idx(Long userIdx);
}
