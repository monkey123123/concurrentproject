package me.monkey.modules.db;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="item")
@Data
public class Item {
    private String _id;
    private String name;
    private String parentId;
    private String itemno;
    private String state;
    private String describe;

}
