package com.he.elm.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.he.elm.apis.Business.IBusinessClient;
import com.he.elm.apis.Food.IFoodClient;
import com.he.elm.common.dtos.CartResponseDto;
import com.he.elm.common.dtos.ListCartDto;
import com.he.elm.common.pojo.Cart;
import com.he.elm.mapper.CartMapper;
import com.he.elm.service.CartService;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper,Cart> implements CartService {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private IFoodClient foodClient; //需调用食品服务
    @Resource
    private IBusinessClient businessClient; //需调用商家服务

    @Override
    public List<CartResponseDto> listCart(ListCartDto listCartDto) {
        List<Cart> cartList;
        if(listCartDto.getBusinessId()!=null){
            cartList= cartMapper.listCart(listCartDto.getUserId(),listCartDto.getBusinessId());
        }else {
            cartList= cartMapper.listCart(listCartDto.getUserId(),null);
        }

        log.info("cartList:=========>{}",cartList);
        List<CartResponseDto> cartResponseDtos=new ArrayList<>(cartList.size());

        for(Cart cart:cartList){
            log.info("cart:====================>{}",cart);
            CartResponseDto cartResponseDto=new CartResponseDto();

            cartResponseDto.setCartId(cart.getCartId());
            cartResponseDto.setQuantity(cart.getQuantity());
            cartResponseDto.setBusinessId(cart.getBusinessId());
            cartResponseDto.setCartId(cart.getCartId());
            cartResponseDto.setFoodId(cart.getFoodId());
            cartResponseDto.setFood(foodClient.getFoodById(cart.getFoodId()));
            cartResponseDto.setBusiness(businessClient.getBusinessById(cart.getBusinessId()));
            cartResponseDtos.add(cartResponseDto);

        }

        return cartResponseDtos;
    }

    @Override
    public int saveCart(Cart cart) {
        //直接保存，需要有所需要素
        log.info("saveCart:======================>{}",cart);
        cartMapper.insert(cart);
        return cart.getCartId();
    }

    @Override
    public int updateCart(Cart cart) {
        LambdaUpdateWrapper<Cart> updateWrapper = new LambdaUpdateWrapper<Cart>();
        //先筛选，再更新
        //根据cartId
        log.info("updateCart:======================{}",cart);
        updateWrapper.eq(Cart::getUserId,cart.getUserId());
        updateWrapper.eq(Cart::getFoodId,cart.getFoodId());
        updateWrapper.eq(Cart::getBusinessId,cart.getBusinessId());
        cartMapper.update(cart,updateWrapper);
        return cart.getCartId();
    }

    @Override
    public int removeCart(Integer cartId) {
        log.info("removeCart:cartId======================{}",cartId);
        LambdaUpdateWrapper<Cart> updateWrapper = new LambdaUpdateWrapper<Cart>();
        updateWrapper.eq(Cart::getCartId,cartId);
        int res =  cartMapper.delete(updateWrapper);
        log.info("removeCart:res======================{}",res);
        return res;
    }
}
