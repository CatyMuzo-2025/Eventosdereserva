package com.espe.reservations.events.controller;

import com.espe.reservations.events.functional.ReservationFilters;
import com.espe.reservations.events.model.ReservationEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationEventController {

    @GetMapping(value = "/stream")
    public Flux<ReservationEvent> streamReservations() {
        return Flux.just(
                        // 3 reservas VALIDAS: precio > 0 y con al menos un email
                        new ReservationEvent(
                                "RES-001",
                                "Caty Muzo",
                                350.50,
                                List.of("catymuzo@yahoo.com")
                        ),
                        new ReservationEvent(
                                "RES-002",
                                "Luis Perez",
                                120.00,
                                List.of("luisperez@yahoo.com", "facturacion@yahoo.com")
                        ),
                        new ReservationEvent(
                                "RES-003",
                                "Maria Gomez",
                                980.75,
                                List.of("mariagomez@yahoo.com")
                        ),
                        // 2 reservas INVALIDAS
                        new ReservationEvent(
                                "RES-004",
                                "Carlos Ruiz",
                                0.0,                          // precio invalido (no es > 0)
                                List.of("carlosruiz@yahoo.com")
                        ),
                        new ReservationEvent(
                                "RES-005",
                                "Sofia Vega",
                                75.25,
                                Collections.emptyList()       // lista de emails vacia -> invalida
                        )
                )
                .filter(ReservationFilters.IS_VALID_RESERVATION)
                .doOnNext(ReservationFilters.PRINT_PROCESSED_EVENT)
                .defaultIfEmpty(genericReservationFallback());
    }


    private ReservationEvent genericReservationFallback() {
        return new ReservationEvent(
                "RES-000",
                "Reserva generica",
                0.0,
                Arrays.asList("sin-datos@yahoo.com")
        );
    }

}
