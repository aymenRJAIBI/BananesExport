package com.exportBanane.controllers;

import com.exportBanane.Service.OrderService;
import com.exportBanane.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    public final OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody OrderDto orderDto){
        orderService.save(orderDto);
        return  ResponseEntity.ok("Order successfully saved!");
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDto>> findByRecipient(@PathVariable("id") Integer id){
         return ResponseEntity.ok(orderService.findByRecipientId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable("id") Integer id,@RequestBody OrderDto odto){
            odto.setId(id);
            orderService.update(odto);
        return  ResponseEntity.ok("Order successfully updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Integer id){
            orderService.delete(id);
        return  ResponseEntity.ok("Order successfully deleted!");
    }


}
