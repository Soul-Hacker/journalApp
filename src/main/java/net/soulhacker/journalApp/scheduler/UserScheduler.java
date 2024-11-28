package net.soulhacker.journalApp.scheduler;

import net.soulhacker.journalApp.entity.JournalEntry;
import net.soulhacker.journalApp.entity.User;
import net.soulhacker.journalApp.repository.UserRepository;
import net.soulhacker.journalApp.repository.UserRepositoryImpl;
import net.soulhacker.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class UserScheduler {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepository;
    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserSendEmail()
    {
        List<User>users=userRepository.getUserForSA();
        for(User user:users)
        {
            List<JournalEntry>journalEntries=user.getJournalEntries();
            List<String> filtered = journalEntries.stream()
                    .filter(dateStr -> LocalDateTime.parse(dateStr).isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .collect(Collectors.toList());
            String entry=String.join(" ",filtered);
            emailService.sendEmail(user.getEmail(), "Mail for last 7 days","checking");
        }
    }
}
