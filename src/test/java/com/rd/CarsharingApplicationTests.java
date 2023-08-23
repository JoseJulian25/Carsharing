package com.rd;

import com.rd.controller.VehicleController;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class CarsharingApplicationTests {
    @Autowired
    private VehicleController vehicleController;

    @WithMockUser(roles = "ADMIN")
    @Test
    void findAll(){
        System.out.println(vehicleController.findAll());
    }
}
