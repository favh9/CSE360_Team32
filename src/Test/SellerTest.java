import org.junit.Test;
import static org.junit.Assert.*;

public class SellerTest {

    User user = new User("Jane", "Doe", "01/09/1869", "janedoe@email.com", "janedoe", "pwd", "super_admin");

    @Test
    public void testTitleValidation() {
        var seller = new Seller_PostBookControl(user, 0, 0);

        // Valid title
        seller.getBooknameField().setText("Valid Book Title");
        assertEquals("Valid Book Title", seller.getBooknameField().getText());

        // Title exceeding limit
        seller.getBooknameField().setText("Title Exceeding Length Limit With More Than 32 Characters");
        assertEquals("Title Exceeding Length Limit Wit", seller.getBooknameField().getText());

        // Invalid characters
        seller.getBooknameField().setText("Invalid!Title@");
        assertEquals("InvalidTitle", seller.getBooknameField().getText());
    }

    @Test
    public void testAuthorValidation() {
        var seller = new Seller_PostBookControl(user, 0, 0);

        // Valid author
        seller.getAuthorField().setText("John Doe");
        assertEquals("John Doe", seller.getAuthorField().getText());

        // Author exceeding limit
        seller.getAuthorField().setText("Author Name Exceeding Length Limit With More Than 32 Characters");
        assertEquals("Author Name Exceeding Length L", seller.getAuthorField().getText());

        // Invalid characters
        seller.getAuthorField().setText("Author@123");
        assertEquals("Author123", seller.getAuthorField().getText());
    }

    @Test
    public void testYearValidation() {
        var seller = new Seller_PostBookControl(user, 0, 0);

        // Valid year
        seller.getYearComboBox().getEditor().setText("2023");
        seller.getYearComboBox().setValue("2023");
        assertEquals(2023, seller.getYear());

        // Invalid year
        try {
            seller.getYearComboBox().getEditor().setText("abcd");
            seller.getYearComboBox().setValue("abcd");
            fail("Should throw NumberFormatException for invalid year.");
        } catch (NumberFormatException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testPriceValidation() {
        var seller = new Seller_PostBookControl(user, 0, 0);

        // Valid price
        seller.getPriceField().setText("123.45");
        assertEquals(123.45, seller.getOriginalPrice(), 0.01);

        // Price exceeding max
        seller.getPriceField().setText("99999.99");
        assertEquals(99999.99, seller.getOriginalPrice(), 0.01);

        seller.getPriceField().setText("100000.00");
        assertEquals("99999.99", seller.getPriceField().getText());

        // Invalid input
        seller.getPriceField().setText("abc123");
        assertEquals("123", seller.getPriceField().getText());
    }

    @Test
    public void testCategoryValidation() {
        var seller = new Seller_PostBookControl(user, 0, 0);

        // Valid category
        seller.getCategoryComboBox().setValue("Fiction");
        assertEquals("Fiction", seller.getCategory());

        // Empty category
        seller.getCategoryComboBox().setValue("");
        assertEquals("", seller.getCategory());
    }

    @Test
    public void testConditionValidation() {
        var seller = new Seller_PostBookControl(user, 0, 0);

        // Valid condition
        seller.getConditionComboBox().setValue("New");
        assertEquals("New", seller.getCategory());

        // Empty condition
        seller.getConditionComboBox().setValue("");
        assertEquals("", seller.getCondition());
    }

    @Test
    public void testButtonHandler() {
        var seller = new Seller_PostBookControl(user, 0, 0);

        // Mock empty fields scenario
        seller.getBooknameField().setText("");
        seller.getAuthorField().setText("");
        assertTrue(seller.getPane().emptyFields());

        // Mock valid fields
        seller.getBooknameField().setText("Valid Title");
        seller.getAuthorField().setText("Valid Author");
        seller.getYearComboBox().setValue("2023");
        seller.getCategoryComboBox().setValue("Fiction");
        seller.getConditionComboBox().setValue("New");
        seller.getPriceField().setText("123.45");
        assertFalse(seller.getPane().emptyFields());
    }
}
