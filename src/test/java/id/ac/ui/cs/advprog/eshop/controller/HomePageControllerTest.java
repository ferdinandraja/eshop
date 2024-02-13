package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomePageControllerTest {

    @InjectMocks
    private HomePageController homePageController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHome() {
        String expectedViewName = "HomePage";

        // get the homepage name
        String actualViewName = homePageController.home();

        // Assert if both are equals or not
        assertEquals(expectedViewName, actualViewName, "View name should match");
        verify(model, times(0)).addAttribute(anyString(), any());
    }
}
