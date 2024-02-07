package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp(){
    }
    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEdit() {
        String tempId = "1e6d6c25-bff3-4fd5-9f7d-5a8e79e1018e";
        String tempName = "Budiman Sudjatmiko";
        int tempQuantity = 50;
        Product testProduct = new Product();
        testProduct.setProductId(tempId);
        testProduct.setProductName(tempName);
        testProduct.setProductQuantity(tempQuantity);
        productRepository.create(testProduct);
        String changesName = "Fadli Zon";
        int changesQuantity = 60;
        testProduct.setProductName(changesName);
        testProduct.setProductQuantity(changesQuantity);
        productRepository.edit(testProduct, tempId);

        Iterator<Product> productIterator = productRepository.findAll();
        Product ChangesItem = null;
        while (productIterator.hasNext()) {
            Product iterationProduct = productIterator.next();
            if (testProduct.getProductId().equals(iterationProduct.getProductId())) {
                ChangesItem = iterationProduct;
                break;
            }
        }
        assertNotNull(ChangesItem);
        assertEquals(ChangesItem.getProductId(), tempId);
        assertEquals(ChangesItem.getProductName(), changesName);
        assertEquals(ChangesItem.getProductQuantity(), changesQuantity);
    }

    @Test
    void editNoProduct() {
        String tempId = "1e6d6c25-bff3-4fd5-9f7d-5a8e79e1018e";
        String tempName = "Budiman Sudjatmiko";
        int tempQuantity = 50;
        Product testProduct = new Product();
        testProduct.setProductId(tempId);
        testProduct.setProductName(tempName);
        testProduct.setProductQuantity(tempQuantity);
        productRepository.create(testProduct);

        String fakeId = "iniBoongan";
        String fakeName = "Fadli Zon";
        int fakeQuantity = 60;
        Product fakeProduct = new Product();
        fakeProduct.setProductId(fakeId);
        fakeProduct.setProductName(fakeName);
        fakeProduct.setProductQuantity(fakeQuantity);
        productRepository.edit(fakeProduct, fakeId);

        Iterator<Product> productIterator = productRepository.findAll();
        Product curProduct = productIterator.next();
        assertNotEquals(curProduct.getProductId(), fakeId);
        assertNotEquals(curProduct.getProductName(), fakeName);
        assertNotEquals(curProduct.getProductQuantity(), fakeQuantity);
    }

    @Test
    void DeleteTesting() {
        String tempId = "1e6d6c25-bff3-4fd5-9f7d-5a8e79e1018e";
        String tempName = "Budiman Sudjatmiko";
        int tempQuantity = 50;
        Product testProduct = new Product();
        testProduct.setProductId(tempId);
        testProduct.setProductName(tempName);
        testProduct.setProductQuantity(tempQuantity);
        productRepository.create(testProduct);

        productRepository.delete(testProduct);
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void DeleteNoProduct() {
        String tempId = "1e6d6c25-bff3-4fd5-9f7d-5a8e79e1018e";
        String tempName = "Budiman Sudjatmiko";
        int tempQuantity = 50;
        Product testProduct = new Product();
        testProduct.setProductId(tempId);
        testProduct.setProductName(tempName);
        testProduct.setProductQuantity(tempQuantity);
        productRepository.create(testProduct);

        String fakeId = "iniBoongan";
        String fakeName = "Fadli Zon";
        int fakeQuantity = 60;
        Product fakeProduct = new Product();
        fakeProduct.setProductId(fakeId);
        fakeProduct.setProductName(fakeName);
        fakeProduct.setProductQuantity(fakeQuantity);
        productRepository.delete(fakeProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product existProduct = productIterator.next();
        assertEquals(testProduct.getProductId(), existProduct.getProductId());
        assertEquals(testProduct.getProductName(), existProduct.getProductName());
        assertEquals(testProduct.getProductQuantity(), existProduct.getProductQuantity());
    }
}
