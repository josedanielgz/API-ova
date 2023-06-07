package com.OvaEducativaAPI.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;


import com.OvaEducativaAPI.Repository.UserRepository;
import com.OvaEducativaAPI.modelo.User;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class UserController {
    
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/search")
    public User searchByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

    // @GetMapping("/notas")
    // public Double[] getUserNotas(@RequestParam String email) {
    //     User user = userRepository.findByEmail(email);
        
    //     if (user != null) {
    //         return user.getNotas();
    //     } else {
    //         return null;
    //     }
    // }

//     @PutMapping("/users/setNotes")
// public ResponseEntity<String> updateUserNotasAtIndex(@RequestParam String email, @RequestParam int index, @RequestParam Double newValue) {
//     User user = userRepository.findByEmail(email);

//     if (user != null) {
//         Double[] notas = user.getNotas();
        
//         // Check if the provided index is within the bounds of the notas array
//         if (index >= 0 && index < notas.length) {
//             notas[index] = newValue;
//             user.setNotas(notas);
            
//             userRepository.save(user);
            
//             return ResponseEntity.ok("Value at index " + index + " in notas array updated successfully.");
//         } else {
//             return ResponseEntity.badRequest().body("Invalid index provided.");
//         }
//     } else {
//         return ResponseEntity.notFound().build();
//     }
// }


@PutMapping("/users/setNotes")
public ResponseEntity<String> updateUserNotasAtIndex(@RequestBody Map<String, String> request) {
    String email = request.get("email");
    User user = userRepository.findByEmail(email);

    if (user != null) {
        int index = Integer.parseInt(request.get("index"));
        double nuevaNota = Double.parseDouble(request.get("nuevaNota"));
        Double[] notas = user.getNotas();
        
        // Check if the provided index is within the bounds of the notas array
        if (index >= 0 && index < notas.length) {
            notas[index] = nuevaNota;
            user.setNotas(notas);
            
            userRepository.save(user);
            
            return ResponseEntity.ok("Value at index " + index + " in notas array updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid index provided.");
        }
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/perfil")
public Double[] getUserNotas(@RequestBody Map<String, String> request) {
    String email = request.get("email");
    User user = userRepository.findByEmail(email);
        
        if (user != null) {
            return user.getNotas();
        } else {
            return null;
        }
}

//Devuelve un arreglo de notas de tipo doble mediante la url

@GetMapping("/perfil2")
public Double[] getUserNotas(@RequestParam String email) {
    User user = userRepository.findByEmail(email);

    if (user != null) {
        return user.getNotas();
    } else {
        return null;
    }
}
   
}