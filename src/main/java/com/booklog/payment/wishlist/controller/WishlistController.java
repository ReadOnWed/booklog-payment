package com.booklog.payment.wishlist.controller;

import com.booklog.payment.wishlist.service.WishlistService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/product")
    public ResponseEntity<Map<String, String>> wishProduct(@RequestBody Map<String, String> map) {
        Map<String, String> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT;
        try {
            List ret = wishlistService.wishProduct(map);

            if (ret != null) {
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                if ((Integer) ret.get(0) == 1)
                    resultMap.put("user", "success");
                if ((Integer) ret.get(1) == 1)
                    resultMap.put("product", "success");

                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @PostMapping("/product/delete")
    public ResponseEntity<Map<String, String>> unwishProduct(@RequestBody Map<String, String> map) {
        Map<String, String> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT;
        try {
            List ret = wishlistService.unwishProduct(map);

            if (ret != null) {
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                if ((Integer) ret.get(0) == 1)
                    resultMap.put("user", "success");
                if ((Integer) ret.get(1) == 1)
                    resultMap.put("product", "success");

                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @PostMapping("/product/{productNo}")
    public ResponseEntity<Long> getProductWishNum(@PathVariable String productNo) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        Long ret = null;
        try {
            ret = wishlistService.getProductWishNum(productNo);

            if (ret != null) {
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(ret, status);
    }

    @PostMapping("/product/user/{userNo}")
    public ResponseEntity<Set> getWishList(@PathVariable String userNo) {
        Set ret = null;
        HttpStatus status = HttpStatus.NO_CONTENT;
        try {
            ret = wishlistService.getWishList(userNo);

            if (ret != null) {
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(ret, status);
    }
}