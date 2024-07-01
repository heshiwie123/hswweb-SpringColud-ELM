package com.he.elm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.he.elm.common.dtos.CartResponseDto;
import com.he.elm.common.dtos.ListCartDto;
import com.he.elm.common.pojo.Cart;


import java.util.List;

public interface CartService extends IService<Cart> {
    public List<CartResponseDto> listCart(ListCartDto listCartDto);
    public int saveCart(Cart cart);
    public int updateCart(Cart cart);
    public int removeCart(Integer cartId);

}
