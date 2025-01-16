package pojos;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class PlaylistRequest {
    private String name;
    private String description;
    @SerializedName("public")
    private boolean isPublic;

    // Constructors
    public PlaylistRequest() {}

    public PlaylistRequest(String name, String description, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    // toString Method
    @Override
    public String toString() {
        return "PlaylistRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isPublic=" + isPublic +
                '}';
    }

    public static void main(String[] args) {

        PlaylistRequest playlistRequest = new PlaylistRequest(
                "New Playlist",
                "New playlist description",
                false
        );

        // Convert the object to JSON using Gson
        Gson gson = new Gson();
        String requestBody = gson.toJson(playlistRequest);

        // Print the JSON request body
        System.out.println(requestBody);
    }

}
