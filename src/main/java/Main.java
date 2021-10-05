import product.Cart;
import product.Product;

public class Main {
    public static void main(String[] args) {
        final int CART_LENGTH = 15;

        Cart cart = new Cart();
        for (int i = 0; i < CART_LENGTH; i++) {
            cart.add(Product.getRandomProduct());
        }
        System.out.println(cart);
        System.out.println(cart.size());
        cart.remove(cart.get(2));
        System.out.println(cart.size());
    }
}
