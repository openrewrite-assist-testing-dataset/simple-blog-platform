package com.example.blog.auth;

import java.security.Principal;

public class BlogPrincipal implements Principal {
    
    private final String name;

    public BlogPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlogPrincipal that = (BlogPrincipal) obj;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BlogPrincipal{name='" + name + "'}";
    }
}