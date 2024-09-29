//package com.example.myapp.service;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class PagosNetService {
//
//    @Value("${pagosnet.api.url}")
//    private String pagosnetUrl;
//
//    @Value("${pagosnet.api.key}")
//    private String apiKey;
//
//    @Value("${pagosnet.merchant.id}")
//    private String merchantId;
//
//    @Value("${pagosnet.return.url}")
//    private String returnUrl;
//
//    private final RestTemplate restTemplate;
//
//    public PagosNetService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public String realizarPago(Double monto, String descripcion) {
//        try {
//            // Crear el cuerpo de la solicitud
//            Map<String, Object> body = new HashMap<>();
//            body.put("merchant_id", merchantId);
//            body.put("amount", monto);
//            body.put("description", descripcion);
//            body.put("return_url", returnUrl);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer " + apiKey);  // Autorización usando la clave API
//
//            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
//
//            // Enviar la solicitud POST a la API de PagosNet
//            ResponseEntity<String> response = restTemplate.exchange(pagosnetUrl, HttpMethod.POST, request, String.class);
//
//            // Obtener la URL de redirección de respuesta de PagosNet
//            if (response.getStatusCode() == HttpStatus.OK) {
//                return response.getBody();  // Aquí obtienes la URL de redirección para completar el pago
//            }
//
//            return "Error: no se pudo procesar el pago.";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error: " + e.getMessage();
//        }
//    }
//}
