package net.soulhacker.journalApp.repository;

import net.soulhacker.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    void deleteById(ObjectId id);
    void deleteByUserName(String userName);
}
