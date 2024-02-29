package com.example.ex_psp_ej03;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/random")
public class RandomController {

    // Devuelve una lista de 100 números aleatorios
    @GetMapping("/numbers")
    public List<RandomNumber> getRandomNumbers() {
        List<RandomNumber> randomNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            randomNumbers.add(new RandomNumber(random.nextInt()));
        }

        return randomNumbers;
    }

    // Devuelve un número aleatorio con D dígitos
    @GetMapping("/number/{d}")
    public RandomNumber getRandomNumber(@PathVariable int d){
        Random random = new Random();

        // Averiguamos el valor máximo y mínimo para generar el número aleatorio
        // elevando D a 10
        int minValue = (int) Math.pow(10, d - 1);
        int maxValue = (int) (Math.pow(10, d - 1) * 9.9);

        int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;

        return new RandomNumber(randomNumber);
    }

    // Recibe un número y devuelve un número aleatorio con la misma cantidad de númenor
    // que el número recibido.
    @PutMapping("/number")
    public RandomNumber generateRandomNumber(@RequestBody RandomNumber inputNumber){
        // Llamamos al método anterior que devielve un número aleatorio con una cantidad D
        // de dígitos pero le enviamos la longitud del número que recibimos como parámetro
        // para determinar la cantidad de dígitos que tendrá el número generado.
        int numDigits = String.valueOf(inputNumber.getRandom()).length();
        return getRandomNumber(numDigits);
    }
}
