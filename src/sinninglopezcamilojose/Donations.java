package sinninglopezcamilojose;

import java.math.BigDecimal;

public class Donations {
    public BigDecimal idDonations;
    public String msg;
    public float money;

    public Donations(BigDecimal idDonations, String msg, float money) {
        this.idDonations = idDonations;
        this.msg = msg;
        this.money = money;
    }
    
}
