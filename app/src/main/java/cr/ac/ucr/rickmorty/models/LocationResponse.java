package cr.ac.ucr.rickmorty.models;

import java.util.ArrayList;

public class LocationResponse {

    ArrayList<Location> results;

    public LocationResponse() {
    }

    public LocationResponse(ArrayList<Location> results) {
        this.results = results;
    }

    public ArrayList<Location> getResults() {
        return results;
    }

    public void setResults(ArrayList<Location> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "LocationResponse{" +
                "results=" + results +
                '}';
    }

}
