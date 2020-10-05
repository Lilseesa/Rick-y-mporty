package cr.ac.ucr.rickmorty.models;

public class Location {

    private String name;
    private String url;
    private String type;
    private String dimension;

    public Location() {
    }

    public Location(String name, String url, String type, String dimension) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.dimension = dimension;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", dimension='" + dimension + '\'' +
                '}';
    }
}
