package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProductGet() {
        // Arrange
        // Use eq() matcher for the first argument and any() matcher for the second argument
        when(model.addAttribute(eq("product"), any(Product.class))).thenReturn(model);

        // Act
        String viewName = productController.createProductGet(model);

        // Assert
        assertEquals("CreateProduct", viewName, "View name should match");
        // Verify that addAttribute method is invoked with specified arguments
        verify(model).addAttribute(eq("product"), any(Product.class));
        // Verify that no other interactions occur with the model object
        verifyNoMoreInteractions(model);
    }

    @Test
    void testCreateProductPost() {
        // Arrange
        Product product = new Product();
        when(productService.create(product)).thenReturn(product);

        // Act
        String redirectUrl = productController.createProductPost(product);

        // Assert
        assertEquals("redirect:list", redirectUrl, "Redirect URL should match");
        verify(productService).create(product);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void testProductList() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);

        // Act
        String viewName = productController.productList(model);

        // Assert
        assertEquals("ProductList", viewName, "View name should match");
        verify(model).addAttribute("products", productList);
        verifyNoMoreInteractions(model);
    }

    @Test
    void testGetEdit() {
        // Arrange
        String id = "1";
        Product product = new Product();
        when(productService.get(id)).thenReturn(product);

        // Act
        String viewName = productController.GetEdit(model, id);

        // Assert
        assertEquals("Edit", viewName, "View name should match");
        verify(model).addAttribute("product", product);
        verifyNoMoreInteractions(model);
    }

    @Test
    void testPostEdit() {
        // Arrange
        String id = "1";
        Product product = new Product();
        when(productService.edit(product, id)).thenReturn(product);

        // Act
        String redirectUrl = productController.PostEdit(product, id);

        // Assert
        assertEquals("redirect:/product/list", redirectUrl, "Redirect URL should match");
        verify(productService).edit(product, id);
        verifyNoMoreInteractions(productService);
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        String id = "1";
        Product product = new Product();
        when(productService.get(id)).thenReturn(product);

        // Act
        String redirectUrl = productController.deleteProduct(id);

        // Assert
        assertEquals("redirect:/product/list", redirectUrl, "Redirect URL should match");
        verify(productService).get(id);
        verify(productService).delete(product);
        verifyNoMoreInteractions(productService);
    }
}
