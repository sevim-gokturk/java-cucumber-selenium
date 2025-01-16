package pojos;

import com.google.gson.annotations.SerializedName;

public class CreatePlaylistRequestBody {
    private String name;
    private String description;
    @SerializedName("public")
    private Boolean isPublic;

    public CreatePlaylistRequestBody() {
    }

    public CreatePlaylistRequestBody(String name, String description, Boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }

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

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "CreatePlaylistRequestBody{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isPublic=" + isPublic +
                '}';
    }
}
