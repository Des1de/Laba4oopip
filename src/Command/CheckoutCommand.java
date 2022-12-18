package Command;

import Models.OrderInformation;
import Models.ProductsModel;

public class CheckoutCommand implements Command{

    private ProductsModel productsModel;

    public CheckoutCommand(ProductsModel productsModel)
    {
        this.productsModel = productsModel;
    }

    @Override
    public void execute(OrderInformation orderInformation) {
        orderInformation.setTotalPrice(productsModel.getTotalPrice());
        productsModel.Checkout(orderInformation);
    }
}
