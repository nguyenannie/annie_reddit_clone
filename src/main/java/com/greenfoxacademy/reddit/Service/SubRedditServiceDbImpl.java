package com.greenfoxacademy.reddit.Service;

import com.greenfoxacademy.reddit.Repository.SubRedditRepository;
import com.greenfoxacademy.reddit.models.entities.Post;
import com.greenfoxacademy.reddit.models.entities.RedditUser;
import com.greenfoxacademy.reddit.models.entities.SubReddit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubRedditServiceDbImpl implements SubRedditService {

    @Autowired
    private SubRedditRepository subRedditRepository;

    @Override
    public void save(SubReddit subReddit) {
        subRedditRepository.save(subReddit);
    }

    @Override
    public void delete(SubReddit subReddit) {
        subRedditRepository.delete(subReddit.getId());
    }

    @Override
    public SubReddit findOne(long id) {
        return subRedditRepository.findOne(id);
    }

    @Override
    public List<SubReddit> findAll() {
        return subRedditRepository.findAll();
    }

    @Override
    public List<SubReddit> findByUser(RedditUser user) {
        return subRedditRepository.findByUser(user);
    }

    @Override
    public SubReddit findByName(String name) {
        return subRedditRepository.findByName(name);
    }

    @Override
    public List<SubReddit> findByNameLike(String name) {
        return subRedditRepository.findByNameLike(name);
    }


}
