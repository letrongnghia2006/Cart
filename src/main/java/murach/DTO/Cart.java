package murach.DTO;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<LineProduct> cart ;

    public Cart (){
        cart = new ArrayList<>();
    }
    public Cart(List<LineProduct> cart) {
        this.cart = new ArrayList<>(cart);
    }

    public List<LineProduct> getCart() {
        return cart;
    }

    public void setCart(List<LineProduct> cart) {
        this.cart = cart;
    }

    public void addProduct(LineProduct product){
        boolean isActive = false;
        for( int i = 0; i < cart.size(); i++ ){
            if(this.cart.get(i).getProduct().getProductCode() == product.getProduct().getProductCode()){
                this.cart.get(i).setQuantity(cart.get(i).getQuantity() + 1);
                isActive = true;
                break;
            }
        }
        if(!isActive){
            cart.add(product);
        }
    }

    public void updateQuantity (int quantity, int productCode){
        for(int i = 0; i < cart.size(); i++){
            if(cart.get(i).getProduct().getProductCode()==productCode){
                cart.get(i).setQuantity(quantity);
                return;
            }
        }
    }

    public void  removeProduct(int productCode){
        for(int i = 0; i < cart.size(); i++){
            if (cart.get(i).getProduct().getProductCode()==productCode){
                cart.remove(i);
            }
        }
    }

    public Double getTotal(){
        Double total = 0.0;
        for(int i = 0; i < cart.size(); i++){
            total += cart.get(i).getAmount();
        }
        return total;
    }
}
