package com.exportBanane.controllers;

import com.exportBanane.Service.RecipientService;
import com.exportBanane.dto.RecipientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipients")
@RequiredArgsConstructor
public class RecipientController {

    @Autowired
    public final RecipientService service;

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody RecipientDto recipientDto){
        service.save(recipientDto);
        return  ResponseEntity.ok("recipient successfully saved!");
    }

    @GetMapping("/")
    public ResponseEntity<List<RecipientDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipientDto> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecipient(@PathVariable("id") Integer id,@RequestBody RecipientDto dto){
            dto.setId(id);
            service.update(dto);
        return  ResponseEntity.ok("recipient successfully updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
            service.delete(id);
         return  ResponseEntity.ok("recipient successfully deleted!");
    }
}
