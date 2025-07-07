CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    author VARCHAR(255) NOT NULL,
    published BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_posts_author ON posts(author);
CREATE INDEX idx_posts_published ON posts(published);
CREATE INDEX idx_posts_created_at ON posts(created_at);

-- Insert a sample post
INSERT INTO posts (title, content, author, published, created_at, updated_at) 
VALUES ('Welcome to the Blog', 'This is the first post in our simple blog platform!', 'admin', true, NOW(), NOW());