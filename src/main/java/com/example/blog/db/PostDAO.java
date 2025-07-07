package com.example.blog.db;

import com.example.blog.core.Post;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Post.class)
public interface PostDAO {

    @SqlQuery("SELECT * FROM posts WHERE id = :id")
    Optional<Post> findById(@Bind("id") Long id);

    @SqlQuery("SELECT * FROM posts ORDER BY created_at DESC")
    List<Post> findAll();

    @SqlQuery("SELECT * FROM posts WHERE author = :author ORDER BY created_at DESC")
    List<Post> findByAuthor(@Bind("author") String author);

    @SqlQuery("SELECT * FROM posts WHERE published = true ORDER BY created_at DESC")
    List<Post> findPublished();

    @SqlUpdate("INSERT INTO posts (title, content, author, published, created_at, updated_at) " +
               "VALUES (:title, :content, :author, :published, :createdAt, :updatedAt)")
    @GetGeneratedKeys
    Long insert(@BindBean Post post);

    @SqlUpdate("UPDATE posts SET title = :title, content = :content, author = :author, " +
               "published = :published, updated_at = :updatedAt WHERE id = :id")
    int update(@BindBean Post post);

    @SqlUpdate("DELETE FROM posts WHERE id = :id")
    int delete(@Bind("id") Long id);
}