package me.monkey.modules.db;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="device")
@Data
public class Device {
    private String _id;
    private String name;
    private String parentId;
    private String state;
    private String describe;

}
