package BV.thymian.service;

import BV.thymian.domian.Nft;
import BV.thymian.dto.NftRequestDto;
import BV.thymian.dto.NftResponseDto;
import BV.thymian.repository.NftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NftService {

    private final NftRepository nftRepository;


    @Transactional
    public NftResponseDto createNft(NftRequestDto requestDto) {

        Nft nft = Nft.builder()
                .userAddress(requestDto.getUserAddress())
                .imageLink(requestDto.getImageLink())
                .imageName(requestDto.getImageName())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .tags(requestDto.getTags())
                .category(requestDto.getCategory())
                .build();

        Nft saved = nftRepository.save(nft);


        return NftResponseDto.builder()
                .idx(saved.getIdx())
                .userAddress(saved.getUserAddress())
                .imageLink(saved.getImageLink())
                .imageName(saved.getImageName())
                .description(saved.getDescription())
                .price(saved.getPrice())
                .tags(saved.getTags())
                .category(saved.getCategory())
                .build();
    }


    @Transactional(readOnly = true)
    public List<NftResponseDto> getAllNfts() {
        return nftRepository.findAll().stream()
                .map(nft -> NftResponseDto.builder()
                        .idx(nft.getIdx())
                        .userAddress(nft.getUserAddress())
                        .imageLink(nft.getImageLink())
                        .imageName(nft.getImageName())
                        .description(nft.getDescription())
                        .price(nft.getPrice())
                        .tags(nft.getTags())
                        .category(nft.getCategory())
                        .build())
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public NftResponseDto getNftById(Long idx) {
        Nft nft = nftRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 idx의 NFT가 없습니다: " + idx));

        return NftResponseDto.builder()
                .idx(nft.getIdx())
                .userAddress(nft.getUserAddress())
                .imageLink(nft.getImageLink())
                .imageName(nft.getImageName())
                .description(nft.getDescription())
                .price(nft.getPrice())
                .tags(nft.getTags())
                .category(nft.getCategory())
                .build();
    }


    @Transactional
    public void deleteNft(Long idx) {
        if (!nftRepository.existsById(idx)) {
            throw new IllegalArgumentException("삭제하려는 NFT가 존재하지 않습니다: " + idx);
        }
        nftRepository.deleteById(idx);
    }

    /**
     * 특정 유저(idx)로 연관된 NFT 목록을 모두 조회하여 DTO 리스트로 반환
     */
    @Transactional(readOnly = true)
    public List<NftResponseDto> getNftsByUserId(Long userIdx) {
        List<Nft> nftList = nftRepository.findAllByUser_Idx(userIdx);

        return nftList.stream()
                .map(nft -> NftResponseDto.builder()
                        .idx(nft.getIdx())
                        .userAddress(nft.getUserAddress())
                        .imageLink(nft.getImageLink())
                        .imageName(nft.getImageName())
                        .description(nft.getDescription())
                        .price(nft.getPrice())
                        .tags(nft.getTags())
                        .category(nft.getCategory())
                        .build())
                .collect(Collectors.toList());
    }
}
