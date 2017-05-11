
package example.jbot.spoonacular;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "usedIngredientCount",
    "missedIngredientCount",
    "likes",
    "title",
    "image",
    "imageType",
    "calories",
    "protein",
    "fat",
    "carbs"
})
public class Result {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("usedIngredientCount")
    private Integer usedIngredientCount;
    @JsonProperty("missedIngredientCount")
    private Integer missedIngredientCount;
    @JsonProperty("likes")
    private Integer likes;
    @JsonProperty("title")
    private String title;
    @JsonProperty("image")
    private String image;
    @JsonProperty("imageType")
    private String imageType;
    @JsonProperty("calories")
    private Integer calories;
    @JsonProperty("protein")
    private String protein;
    @JsonProperty("fat")
    private String fat;
    @JsonProperty("carbs")
    private String carbs;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("usedIngredientCount")
    public Integer getUsedIngredientCount() {
        return usedIngredientCount;
    }

    @JsonProperty("usedIngredientCount")
    public void setUsedIngredientCount(Integer usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    @JsonProperty("missedIngredientCount")
    public Integer getMissedIngredientCount() {
        return missedIngredientCount;
    }

    @JsonProperty("missedIngredientCount")
    public void setMissedIngredientCount(Integer missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    @JsonProperty("likes")
    public Integer getLikes() {
        return likes;
    }

    @JsonProperty("likes")
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("imageType")
    public String getImageType() {
        return imageType;
    }

    @JsonProperty("imageType")
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    @JsonProperty("calories")
    public Integer getCalories() {
        return calories;
    }

    @JsonProperty("calories")
    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @JsonProperty("protein")
    public String getProtein() {
        return protein;
    }

    @JsonProperty("protein")
    public void setProtein(String protein) {
        this.protein = protein;
    }

    @JsonProperty("fat")
    public String getFat() {
        return fat;
    }

    @JsonProperty("fat")
    public void setFat(String fat) {
        this.fat = fat;
    }

    @JsonProperty("carbs")
    public String getCarbs() {
        return carbs;
    }

    @JsonProperty("carbs")
    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
