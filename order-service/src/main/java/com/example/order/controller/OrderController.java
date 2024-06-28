package com.example.order.controller;



import com.example.order.model.response.GeneralResponseDTO;
import com.example.order.model.response.RqOrderDTO;
import com.example.order.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    private IOrderService iOrderService;
    @GetMapping
    public ResponseEntity<GeneralResponseDTO> getAllOrder() {
        return ResponseEntity.ok( iOrderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(iOrderService.getOrderById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponseDTO>  createOrder(@RequestBody @Valid RqOrderDTO order) {
        return ResponseEntity.ok(iOrderService.saveOrder(order));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO> deleteOrderById(@PathVariable Long id) {
        return ResponseEntity.ok( iOrderService.deleteOrder(id));
    }
}
