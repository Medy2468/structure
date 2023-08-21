package com.cgl.web;

import com.cgl.entities.Product;
import com.cgl.services.Implement.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;

    @GetMapping("/")
    public String homePage(Model model)
    {
        return "index";
    }

    @GetMapping("/list")
    public String listPage(Model model)
    {
        List<Product> list = productService.getAllProduct();
        model.addAttribute("products", list);

        return "list";
    }

    @GetMapping("/add")
    public String addPage(Model model)
    {
        Product product =  new Product();
        model.addAttribute("product", product);

        return "add";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product, BindingResult bindingResult, @RequestParam("photo")MultipartFile photo)
    {
        if(bindingResult.hasErrors())
        {
            return "add";
        }

        try {
            if (!photo.isEmpty()) {
                //Recuperer le nom du fichier
                String fileName = UUID.randomUUID().toString() + "_" + photo.getOriginalFilename();
                String uploadPath = "/images";
                String filePath = Paths.get(uploadPath, fileName).toString();

                Files.copy(photo.getInputStream(), Paths.get(filePath));

                //Definir l'URL d'accès à la photo
                product.setPhotoFile(fileName);
            }
            productService.addProduct(product);
            return "redirect:/list";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "add";
        }
    }

    /*@PostMapping("/add")
    public ResponseEntity<Product> add(@RequestBody Product product)
    {
        Product prod = productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }

    @PutMapping ("/update")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id)
    {
        Product prod = productService.updateProduct(id, product);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prod);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id)
    {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list()
    {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/infos/{id}")
    public ResponseEntity<Product> getOne(@PathVariable Long id)
    {
        return ResponseEntity.ok(productService.getOneProduct(id));
    }*/
}
