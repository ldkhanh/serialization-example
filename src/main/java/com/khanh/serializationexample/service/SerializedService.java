package com.khanh.serializationexample.service;

import com.khanh.serializationexample.model.SerializedUser;
import com.khanh.serializationexample.model.User;
import com.khanh.serializationexample.repository.SerializedUserRepository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerializedService {

    private SerializedUserRepository repository;

    public SerializedService(SerializedUserRepository repository) {
        this.repository = repository;
    }

    public void persist(User user) {

        // convert user to byte[]
        byte[] serializedUser = convert(user);
        // persist
        SerializedUser serializedObject = new SerializedUser(serializedUser);
        repository.save(serializedObject);

    }

    private byte[] convert(User user) {
        try (
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream))  {
            objectOutputStream.writeObject(user);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUsers() {
        return repository.findAll()
                .stream()
                .map(serializedUser -> convert(serializedUser.getSerializedUser()))
                .collect(Collectors.toList());
    }

    private User convert(byte[] serializedUser) {
        try (
                ByteArrayInputStream inputStream = new ByteArrayInputStream(serializedUser);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream))  {
            User user = (User) objectInputStream.readObject();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
