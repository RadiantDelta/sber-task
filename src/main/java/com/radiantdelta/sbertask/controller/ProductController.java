package com.radiantdelta.sbertask.controller;

import com.radiantdelta.sbertask.domain.Product;
import com.radiantdelta.sbertask.dto.ProductDTO;
import com.radiantdelta.sbertask.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping()
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Retrieves all Products from the database
     * @return The list of Products retrieved from the database.
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)) }) })
    @Tag(name = "get", description = "GET methods of Products APIs")
    @Operation(summary = "Get products",
            description = "Get list of existing products. The response is the list of Product objects with id, name, and amount.")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Retrieves  Product with specific id from the database
     * @param id id of Product to be retrieved
     * @return The Product with specific id from the database
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "204", description = "Product not found",
                    content = @Content) })
    @Tag(name = "get", description = "GET methods of Products APIs")
    @Operation(summary = "Get product by id",
            description = "Get Product with specific id. The response is Product object with id, name, and amount.")
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@Parameter(
            description = "ID of product to be retrieved",
            required = true)
                                                  @PathVariable int id) {
        Product p = productService.findById(id);
        return (p == null) ? (ResponseEntity<Product>) ResponseEntity.noContent() : ResponseEntity.ok(p);


    }

    /**
     *  Handles creation of new Product
     * @param product the details of Product to be created
     * @return  Product or redirect
     */
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)) }) })
    @Operation(summary = "Create product",
            description = "Create product with specific name, and amount. The response is Product object with id, name, and amount.")
    @PostMapping
    @Transactional
    public ResponseEntity createProduct(@Parameter(
            description = "json \"productName\" and   \"amount\" ",
            required = true)
                                        @RequestBody Product product) {
        log.info("request body product id : " +product.getId());
        Product result = productService.createProduct(ProductDTO.from(product));

        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);



    }


    /**
     * Handles deleting of Product with specific id
     * @param id of Product to be deleted
     * @return a {@link ResponseEntity} with the  HTTP status code
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "204", description = "Product not found",
                    content = @Content) })
    @Operation(summary = "Delete Product",
            description = "Delete existing product. The response is status code.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@Parameter(
            description = "ID of product to be deleted",
            required = true) @PathVariable int id) {

        Product product = productService.findById(id);

        productService.deleteProduct(ProductDTO.from(product));

        return ResponseEntity.ok().build();

    }

    /**
     * Handles updating of Product with specific id
     * @param newProduct which takes place of previous Product
     * @param id of previous and new Product
     * @return updated Product with the  HTTP status code
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "204", description = "Product not found",
                    content = @Content) })
    @Operation(summary = "Update Product",
            description = "Update  existing product. The response is updated Product object with id, name, and amount.")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Product> replaceProduct(@Parameter(
            description = "json \"productName\" and   \"amount\"  of new product",
            required = true) @RequestBody Product newProduct, @Parameter(
            description = "ID of product to be replaced",
            required = true) @PathVariable int id) {

        Product result = productService.replaceProduct(ProductDTO.from(newProduct), id);

        return ResponseEntity.ok().body(result);


    }




}
