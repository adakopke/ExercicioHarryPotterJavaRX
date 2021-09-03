package com.patronus.harrypotternaoreativo.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Casa {

    private String id;
    private String name;
    private String animal;
    private String founder;
    private List<ValoresDaCasa> values;

}
