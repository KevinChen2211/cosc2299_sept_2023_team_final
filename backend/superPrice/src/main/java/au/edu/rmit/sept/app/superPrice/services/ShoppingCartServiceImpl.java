package au.edu.rmit.sept.app.superPrice.services;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import au.edu.rmit.sept.app.superPrice.models.CartItem;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Long, CartItem> maps = new  HashMap<> (); // maps is shopping cart
    @Override
    public void add(CartItem item) {
        CartItem cartItem = maps.get(item.getProducID());
        if (cartItem == null) {
            maps.put(item.getProducID(), item);
        } else {
            cartItem.setQuantity((cartItem.getQuantity()) + 1);
        }
    }
    @Override
    public void remove( Long id) {
        maps.remove(id);
    }
    @Override
    public CartItem update(Long productID, int qty) {
        CartItem cartItem = maps.get(productID);
        cartItem.setQuantity(qty);
        return cartItem;
    }
    @Override
    public void clear() {
        maps.clear();
    }
    @Override
    public Collection<CartItem> getAllItems() {
        return maps.values();
    }
@Override
    public int getCount() {
        return maps.values().size();
    }
    @Override
    public double getAmount() {
        return maps.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }
}
