package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;
    @BeforeEach
    void setUp() {
    }


    @Test
    void testEditService() {
        // create a dummy product
        String tempId = "1e6d6c25-bff3-4fd5-9f7d-5a8e79e1018e";
        String tempName = "Budiman Sudjatmiko";
        int tempQuantity = 50;
        Product testProduct = new Product();
        testProduct.setProductId(tempId);
        testProduct.setProductName(tempName);
        testProduct.setProductQuantity(tempQuantity);

        //edit the product
        Product newEditProduct = productService.edit(testProduct, tempId);

        //check both products
        assertEquals(testProduct, newEditProduct);
    }
    /*@Test
    void testEditNotBiggerThanZero() {
        List<Product> productlist = new ArrayList<>();
        productlist.add(product);
        Product productEdit a new Product:
        productEdit.setProductNane("WuLing SPW*); productedit.setProcuctQuantity(3); producttdit.setProduct]d(product.getProductidO);
                when(productRepository.findALL).thenReturn(productList.iteratorO));
        when(productRepository.edit(productEdit, product.getProductId()))
•thennswer(invocation - +productist.set(0, productEdit));
        when(productRepository.delete(productEdit)).thenAnswer(invocation - +procuctList.renove(productEdit));
        service.ecit(productEcit, productEdit.getProductidO);
        List«Product»products a service.findAlLo;
        assertfalse(products.iterator().hastlext());
    }*/

    @Test
    void testCreateService() {
        // create a dummy product
        String tempId = "1e6d6c25-bff3-4fd5-9f7d-5a8e79e1018e";
        String tempName = "Budiman Sudjatmiko";
        int tempQuantity = 50;
        Product testProduct = new Product();
        testProduct.setProductId(tempId);
        testProduct.setProductName(tempName);
        testProduct.setProductQuantity(tempQuantity);

        when(productRepository.create(testProduct)).thenReturn(testProduct);
        when(productRepository.findAll()).thenReturn(Collections.singletonList(testProduct).iterator());

        productService.create(testProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        // verify the product iterated and the product created is the same
        assertEquals(testProduct.getProductId(), savedProduct.getProductId());
        assertEquals(testProduct.getProductName(), savedProduct.getProductName());
        assertEquals(testProduct.getProductQuantity(), savedProduct.getProductQuantity());

    }
    @Test
    void testFindAllService() {
        // Do some dummy method like findAll
        List<Product> dummyList = new ArrayList<>();
        dummyList.add(new Product());
        dummyList.add(new Product());
        when(productRepository.findAll()).thenReturn(dummyList.iterator());

        List<Product> getAll = productService.findAll();

        // Verify if the size of the dummy and the product created are the same or not
        assertEquals(dummyList.size(), getAll.size());
    }

    @Test
    void testDeleteProduct() {
        // create a dummy product
        String tempId = "1e6d6c25-bff3-4fd5-9f7d-5a8e79e1018e";
        String tempName = "Budiman Sudjatmiko";
        int tempQuantity = 50;
        Product testProduct = new Product();
        testProduct.setProductId(tempId);
        testProduct.setProductName(tempName);
        testProduct.setProductQuantity(tempQuantity);
         // create a dummy product without id
        String fakeName = "Fadli Zon";
        int fakeQuantity = 60;
        Product fakeProduct = new Product();
        fakeProduct.setProductName(fakeName);
        fakeProduct.setProductQuantity(fakeQuantity);


        //perform the designated method manually
        when(productRepository.create(testProduct)).thenReturn(testProduct);
        when(productRepository.delete(testProduct)).thenReturn(true);

        productService.create(testProduct);


        // test both the product with and without id
        boolean successDeleted = productService.delete(testProduct);
        boolean failedDeleted = productService.delete(fakeProduct);
        assertTrue(successDeleted);
        assertFalse(failedDeleted);
    }

    @Test
    void testGetService() {
        // create dummy product
        String tempId = "1e6d6c25-bff3-4fd5-9f7d-5a8e79e1018e";
        String tempName = "Budiman Sudjatmiko";
        int tempQuantity = 50;
        Product testProduct = new Product();
        testProduct.setProductId(tempId);
        testProduct.setProductName(tempName);
        testProduct.setProductQuantity(tempQuantity);

        // perform the designated method manually
        when(productRepository.findAll()).thenReturn(Collections.singletonList(testProduct).iterator());
        Product retrievedProduct = productService.get(tempId);

        // test the product from get and the first created product
        assertEquals(retrievedProduct, testProduct);
        assertNotNull(retrievedProduct);
    }

    @Test
    void testNotGetService() {
        // create dummy product
        String tempId = "1e6d6c25-bff3-4fd5-9f7d-5a8e79e1018e";
        String tempName = "Budiman Sudjatmiko";
        int tempQuantity = 50;
        Product testProduct = new Product();
        testProduct.setProductId(tempId);
        testProduct.setProductName(tempName);
        testProduct.setProductQuantity(50);

        // Create an arraylist to be a manual testing
        List<Product> productList = new ArrayList<>();
        productList.add(testProduct);
        when(productRepository.findAll()).thenReturn(productList.iterator());
        String fakeId = "iniboongan";
        // get the id that is not created yet
        Product retrievedProduct = productService.get(fakeId);

        // assertNull because there is no product with the id
        assertNull(retrievedProduct);
    }

}
