//package com.example.myapp.controller;
//import com.example.myapp.service.PagosNetService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/pagos")
//public class PagoController {
//
//    private final PagosNetService pagosNetService;
//
//    public PagoController(PagosNetService pagosNetService) {
//        this.pagosNetService = pagosNetService;
//    }
//
//    @PostMapping("/realizar-pago")
//    public ResponseEntity<String> realizarPago(@RequestBody Map<String, Object> payload, Authentication authentication) {
//        Double amount = (Double) payload.get("amount");
//        String description = (String) payload.get("description");
//
//        // Llamar al servicio de PagosNet
//        String redirectUrl = pagosNetService.realizarPago(amount, description);
//
//        if (redirectUrl.startsWith("http")) {
//            return new ResponseEntity<>(redirectUrl, HttpStatus.OK);  // Devuelve la URL de redirección al frontend
//        }
//
//        return new ResponseEntity<>("Error al procesar el pago", HttpStatus.BAD_REQUEST);
//    }
//}
