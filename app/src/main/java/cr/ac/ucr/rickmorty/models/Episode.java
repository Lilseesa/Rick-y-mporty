package cr.ac.ucr.rickmorty.models;

public class Episode {

    private int id;
    private String name;
    private String url;
    private String episode;
    private String airDate;

    public Episode() {
    }

    public Episode(int id, String name, String url, String episode, String airDate) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.episode = episode;
        this.airDate = airDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    //SETS
    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    //GETS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getEpisode() {
        return episode;
    }

    public String getAirDate() {
        return airDate;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", episode='" + episode + '\'' +
                ", airDate='" + airDate + '\'' +
                '}';
    }
}
