package Models;

public interface ProductObservable{

        void addSubscriber(ProductObserver subscriber);
        void removeSubscriber(ProductObserver subscriber);

}
