package net.soulhacker.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import java.time.LocalDateTime;

@Document(collection = "journalEntry")
@Data
public class JournalEntry {
    @Id
    private ObjectId id;

    private String title;
    private String content;
    private LocalDateTime date;


}
