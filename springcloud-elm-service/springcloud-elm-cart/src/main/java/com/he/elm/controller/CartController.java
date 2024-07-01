package com.he.elm.controller;


import com.he.elm.common.dtos.CartResponseDto;
import com.he.elm.common.dtos.ListCartDto;
import com.he.elm.common.dtos.ResponseResult;
import com.he.elm.common.enums.AppHttpCodeEnum;
import com.he.elm.common.pojo.Cart;
import com.he.elm.service.CartService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cart")

public class CartController {
    @Resource
    private CartService cartService;

    @PostMapping("/listCart")
    public ResponseResult listCart(@RequestBody ListCartDto listCartDto) throws Exception{

        List<CartResponseDto> cartResponseDtos= cartService.listCart( listCartDto);
        Map<String, List<CartResponseDto>> map=new HashMap<>();
        map.put("cartResponseDtos",cartResponseDtos);

        return new ResponseResult(200,map);
    }

    @PutMapping("/saveCart")
    public ResponseResult saveCart(@RequestBody Cart cart) throws Exception{
        int res= cartService.saveCart(cart);
        if(res!=0){
            return ResponseResult.okResult(res);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.FAILED);
    }

    @PostMapping("/updateCart")
    public ResponseResult updateCart(@RequestBody Cart cart) throws Exception{
        int res= cartService.updateCart(cart);

        if (res!=0){
            return ResponseResult.okResult(res);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.FAILED);
    }

    @PostMapping("/removeCart")
    public ResponseResult removeCart(@Param("cartId") Integer cartId) throws Exception{
        int res= cartService.removeCart(cartId);
        if(res!=0){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.FAILED);
    }
}