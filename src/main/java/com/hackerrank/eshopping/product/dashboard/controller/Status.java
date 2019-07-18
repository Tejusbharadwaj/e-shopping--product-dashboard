package com.hackerrank.eshopping.product.dashboard.controller;

        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseStatus;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class Status {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> healthCheck() throws RuntimeException {
        try {
            return ResponseEntity.ok()
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body("OKAY!");
        } catch (Error error) {
            throw new RuntimeException("FAIL");
        }

    }
}
