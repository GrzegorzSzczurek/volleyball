package sample.model;

public class Match {

    private Integer id;
    private Cadre hostCadre;
    private Cadre guestCadre;
    private int frequency;
    private int fixture;

    public Match(Integer id, Cadre hostCadre, Cadre guestCadre, int frequency, int fixture) {
        this.id = id;
        this.hostCadre = hostCadre;
        this.guestCadre = guestCadre;
        this.frequency = frequency;
        this.fixture = fixture;
    }

    public Match(Cadre hostCadre, Cadre guestCadre, int frequency, int fixture) {
        this.hostCadre = hostCadre;
        this.guestCadre = guestCadre;
        this.frequency = frequency;
        this.fixture = fixture;
    }

    public Match() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cadre getHostCadre() {
        return hostCadre;
    }

    public void setHostCadre(Cadre hostCadre) {
        this.hostCadre = hostCadre;
    }

    public Cadre getGuestCadre() {
        return guestCadre;
    }

    public void setGuestCadre(Cadre guestCadre) {
        this.guestCadre = guestCadre;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFixture() {
        return fixture;
    }

    public void setFixture(int fixture) {
        this.fixture = fixture;
    }
}
