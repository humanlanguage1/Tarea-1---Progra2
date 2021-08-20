package demo.api.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.api.demo.model.Producto;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value="api/acme", produces ="application/json")
public class HomeController {
    private Map<String, Producto> productos;

    public HomeController() {
        productos = new HashMap<String,Producto>();
        Producto p = new Producto();
        String id = UUID.randomUUID().toString();
        p.setId(id);
        p.setDescription("description");
        p.setName("name");
        p.setPrice(Double.parseDouble("20.10"));
        productos.put(id, p);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Producto>> listall() {
        return new ResponseEntity<Map<String, Producto>>(productos, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Producto p) {
        String id = UUID.randomUUID().toString();
        p.setId(id);
        productos.put(id, p);
        return new ResponseEntity<String>(id, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Producto> get(@PathVariable String id) {
        return new ResponseEntity<Producto>(productos.get(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody Producto p) {
        p.setId(id);
        productos.put(id, p);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable String id) {
        productos.remove(id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
