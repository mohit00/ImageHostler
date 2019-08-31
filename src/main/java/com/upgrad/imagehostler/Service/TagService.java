package com.upgrad.imagehostler.Service;
  import com.upgrad.imagehostler.Model.tags;
 import com.upgrad.imagehostler.repository.TagRepository;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public tags getTagByName(String title) {
        return tagRepository.findTag(title);
    }

    public tags createTag(tags tag) {
        return tagRepository.createTag(tag);
    }
}
