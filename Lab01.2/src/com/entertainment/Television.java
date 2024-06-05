package com.entertainment;

import java.util.Objects;

public class Television {
    // fields or instance variables
    private String brand;
    private int volume;

    // Television HAS-A Tuner (for all things related to channel changing)
    private final Tuner tuner = new Tuner();  // instantiated internally, NOT exposed

    // constructors
    public Television() {
        super();
    }

    public Television(String brand, int volume) {
        super();
        setBrand(brand);
        setVolume(volume);
    }

    // business or "action" methods
    public int getCurrentChannel() {
        return tuner.getChannel();  // delegate to contained Tuner object
    }

    public void changeChannel(int channel) {
        tuner.setChannel(channel);  // delegate to contained Tuner object
    }

    // accessor methods
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Television other = (Television) obj;
        return this.getVolume() == other.getVolume() &&
               Objects.equals(this.getBrand(), other.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getVolume());
    }


    /*    @Override
    public int hashCode() {
        // this is a poor hash function, because it easily yields "hash collisions"
        // a "hash collision" is when "different" objects have the same hashcode (just dumb luck)
        // return getBrand().length() + getVolume();

        // this is a "scientifically correct" hash function, i.e.,
        // it minimizes the probability of hash collisions
        return Objects.hash(getBrand(), getVolume());
    }*/

/*    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        // check that 'obj' is really referring to a Television object, then we proceed
        // if (obj instanceof Television) {  // IS-A check
        if (this.getClass() == obj.getClass()) {  // are we the EXACT SAME TYPE?
            // downcast 'obj' to more specific reference type Television, for getName(), getAge()
            Television other = (Television) obj;

            // do the checks: business equality is defined as brand, volume
            result = Objects.equals(this.getBrand(), other.getBrand())  &&  // null-safe check
                     this.getVolume() == other.getVolume();                 // primitives can't be null
        }
        return result;
    }*/

    @Override
    public String toString() {
        return String.format("%s: brand=%s, volume=%s, currentChannel=%s",
                getClass().getSimpleName(), getBrand(), getVolume(), getCurrentChannel());
    }
}