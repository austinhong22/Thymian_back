package BV.thymian.controller;

import BV.thymian.dto.NftRequestDto;
import BV.thymian.dto.NftResponseDto;
import BV.thymian.service.NftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nfts")
@RequiredArgsConstructor
public class NftController {

    private final NftService nftService;

    @PostMapping
    public ResponseEntity<NftResponseDto> createNft(@RequestBody @Valid NftRequestDto nftRequest) {
        NftResponseDto created = nftService.createNft(nftRequest);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<NftResponseDto>> getAllNfts() {
        List<NftResponseDto> nftList = nftService.getAllNfts();
        return ResponseEntity.ok(nftList);
    }


    @GetMapping("/{idx}")
    public ResponseEntity<NftResponseDto> getNft(@PathVariable("idx") Long idx) {
        NftResponseDto dto = nftService.getNftById(idx);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{idx}")
    public ResponseEntity<Void> deleteNft(@PathVariable("idx") Long idx) {
        nftService.deleteNft(idx);
        return ResponseEntity.noContent().build();
    }
}
