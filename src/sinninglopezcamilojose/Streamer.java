package sinninglopezcamilojose;

import java.math.BigDecimal;

public class Streamer {
    public BigDecimal idStreamer;
    public String userName;
    public float followers;

    public Streamer(BigDecimal idStreamer, String userName, float followers) {
        this.idStreamer = idStreamer;
        this.userName = userName;
        this.followers = followers;
    }
    
}
