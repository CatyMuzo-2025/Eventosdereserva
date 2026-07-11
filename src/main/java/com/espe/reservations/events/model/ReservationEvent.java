package com.espe.reservations.events.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class ReservationEvent {

    private final String id;
    private final String passengerName;
    private final Double price;
    private final List<String> emails;

    public ReservationEvent(String id, String passengerName, Double price, List<String> emails) {
        this.id = id;
        this.passengerName = passengerName;
        this.price = price;
        // Copia defensiva #1: se copia la lista recibida para que cambios
        // posteriores en la lista original del llamador NO afecten a este objeto.
        this.emails = (emails == null) ? new ArrayList<>() : new ArrayList<>(emails);
    }

    public String getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Double getPrice() {
        return price;
    }

    public List<String> getEmails() {
        // Copia defensiva #2: se retorna una copia nueva en cada llamada,
        // para que quien consuma este getter no pueda mutar la lista interna.
        return new ArrayList<>(this.emails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationEvent)) return false;
        ReservationEvent that = (ReservationEvent) o;
        return Objects.equals(id, that.id)
                && Objects.equals(passengerName, that.passengerName)
                && Objects.equals(price, that.price)
                && Objects.equals(emails, that.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passengerName, price, emails);
    }

    @Override
    public String toString() {
        return "ReservationEvent{" +
                "id='" + id + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", price=" + price +
                ", emails=" + emails +
                '}';
    }
}