package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final Map<Long, Post> posts;
    private final AtomicLong counterPost;

    public PostRepository() {
        posts = new ConcurrentHashMap<>();
        counterPost = new AtomicLong(0);
    }

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        if (posts.containsKey(post.getId())) {
            posts.put(post.getId(), post);
        } else {
            post.setId(counterPost.incrementAndGet());
            posts.put(post.getId(), post);
        }
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
