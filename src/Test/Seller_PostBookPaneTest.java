import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Seller_PostBookPaneTest {


    User user = new User("jane", "doe", "01/09/1969", "janedoe@email.com", "janedoe", "password", "admin");

    @Test
    void isProfitValid() {

        var postbook = new Seller_PostBookPane(user,0,0);

        postbook.computeProfit();

    }
}