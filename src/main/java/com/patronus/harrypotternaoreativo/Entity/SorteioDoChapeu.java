package com.patronus.harrypotternaoreativo.Entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Data
@ToString
public class SorteioDoChapeu {

    @SerializedName("sorting-hat-choice")
    private String id;

}
