package com.example.blog;

import com.example.blog.core.Post;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

public class PostResourceTest {

    private Post testPost;

    @Before
    public void setUp() {
        testPost = new Post("Test Post", "This is a test post content", "testauthor");
        testPost.setId(1L);
        testPost.setPublished(true);
        testPost.setCreatedAt(Instant.now());
        testPost.setUpdatedAt(Instant.now());
    }

    @Test
    public void testPostCreation() {
        assertNotNull(testPost);
        assertEquals("Test Post", testPost.getTitle());
        assertEquals("This is a test post content", testPost.getContent());
        assertEquals("testauthor", testPost.getAuthor());
        assertTrue(testPost.getPublished()); // Set to true in setUp
        assertNotNull(testPost.getCreatedAt());
        assertNotNull(testPost.getUpdatedAt());
    }

    @Test
    public void testPostGettersAndSetters() {
        testPost.setTitle("Updated Post");
        testPost.setContent("Updated content");
        testPost.setAuthor("newauthor");
        testPost.setPublished(true);
        
        assertEquals("Updated Post", testPost.getTitle());
        assertEquals("Updated content", testPost.getContent());
        assertEquals("newauthor", testPost.getAuthor());
        assertTrue(testPost.getPublished());
    }

    @Test
    public void testPostDefaultConstructor() {
        Post newPost = new Post();
        assertNull(newPost.getTitle());
        assertNull(newPost.getContent());
        assertNull(newPost.getAuthor());
        assertFalse(newPost.getPublished());
        assertNotNull(newPost.getCreatedAt());
        assertNotNull(newPost.getUpdatedAt());
    }

    @Test
    public void testPostConstructorWithParameters() {
        Post newPost = new Post("Title", "Content", "Author");
        assertEquals("Title", newPost.getTitle());
        assertEquals("Content", newPost.getContent());
        assertEquals("Author", newPost.getAuthor());
        assertFalse(newPost.getPublished());
        assertNotNull(newPost.getCreatedAt());
        assertNotNull(newPost.getUpdatedAt());
    }

    @Test
    public void testPostTimestamps() {
        Instant before = Instant.now();
        Post newPost = new Post("Title", "Content", "Author");
        Instant after = Instant.now();
        
        assertTrue(newPost.getCreatedAt().isAfter(before) || newPost.getCreatedAt().equals(before));
        assertTrue(newPost.getCreatedAt().isBefore(after) || newPost.getCreatedAt().equals(after));
        assertTrue(newPost.getUpdatedAt().isAfter(before) || newPost.getUpdatedAt().equals(before));
        assertTrue(newPost.getUpdatedAt().isBefore(after) || newPost.getUpdatedAt().equals(after));
    }
}