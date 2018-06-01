package com.khanh.serializationexample.resource;

import com.khanh.serializationexample.model.User;
import com.khanh.serializationexample.service.SerializedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/deserialize")
public class DeSerializationResource {

    private SerializedService serializedService;

    public DeSerializationResource(SerializedService serializedService) {
        this.serializedService = serializedService;
    }

    @GetMapping
    public List<User> getUsers() {
        return serializedService.getUsers();
    }

}
