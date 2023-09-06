package au.edu.rmit.sept.app.superPrice.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import au.edu.rmit.sept.app.superPrice.models.CartItem;

@Service
public interface ShoppingCartService {
    public void add(CartItem item);
    public void remove( Long id);
    public CartItem update(Long productID, int qty);
    public void clear();
    public Collection<CartItem> getAllItems();
    public int getCount();
    public double getAmount();
}
