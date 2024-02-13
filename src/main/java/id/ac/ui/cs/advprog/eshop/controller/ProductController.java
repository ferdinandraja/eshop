package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductGet(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product){
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productList(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/edit/{id}")
    public String GetEdit(Model model, @PathVariable String id) {
        Product product = service.get(id);
        model.addAttribute("product", product);
        return "Edit";
    }

    @PostMapping("/edit/{id}")
    public String PostEdit(@ModelAttribute Product product, @PathVariable String id) {
        service.edit(product, id);
        return "redirect:/product/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        Product product = service.get(id);
        service.delete(product);
        return "redirect:/product/list";
    }

}