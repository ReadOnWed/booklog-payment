package com.booklog.payment.controller;

import com.booklog.payment.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH})
public class WishlistController {
    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

//    @PostMapping("/product")
//    public ResponseEntity<Message> wishProduct(@RequestBody Map<String, String> map) {
//        try {
//            List ret = wishlistService.wishProduct(map);
//
//            if (ret != null) {
//                Message message = new Message();
//                HttpHeaders headers = new HttpHeaders();
//
//                headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//
//                message.setStatus(StatusEnum.OK);
//                message.setCode(StatusEnum.OK);
//                message.setMessage("요청에 성공하였습니다.");
//                message.setData(ret);
//                return new ResponseEntity<>(message, headers, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return exceptionHandling(e);
//        }
//    }
}

