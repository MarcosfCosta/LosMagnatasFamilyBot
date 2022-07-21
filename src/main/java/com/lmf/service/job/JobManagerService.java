package com.lmf.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class JobManagerService {

    @Autowired
    private JobClanLeaderboardImpl jobClanLeaderboard;


    @PostConstruct
    public void initJobs() {
        final List<BaseJob> jobList = Arrays.asList( jobClanLeaderboard);
        jobList.forEach(BaseJob::execute);
    }
}
