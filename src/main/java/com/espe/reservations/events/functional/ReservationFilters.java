package com.espe.reservations.events.functional;
import com.espe.reservations.events.model.ReservationEvent;
import java.util.function.Consumer;
import java.util.function.Predicate;


public final class ReservationFilters {

    private ReservationFilters() {

    }
    public static final Predicate<ReservationEvent> IS_VALID_RESERVATION = event ->
            event.getPrice() != null
                    && event.getPrice() > 0
                    && event.getEmails() != null
                    && !event.getEmails().isEmpty();

    public static final Consumer<ReservationEvent> PRINT_PROCESSED_EVENT = event ->
            System.out.println("[Evento procesado] -> " + event);

}
