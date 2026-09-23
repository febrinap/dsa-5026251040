package lw01.Unguided;

public class CarWash extends WashService {

    public CarWash(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int total;

        if (days <= 3) {
            total = days * 35000;
        } else {
            total = (3 * 35000) + ((days - 3) * 25000);
        }

        return total + 15000;
    }

    @Override
    public String label() {
        return "Car";
    }
}