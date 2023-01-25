# NewsCycle Backend

Note: this is my 3rd time around in improving this backend service. It was first built with Node.js and then in Spring Boot. I have begun a new version with improved use of table relationships, security, and DTOs.

This is the Spring Boot backend to a fullstack website called NewsCycle. The fronted, made with React, is also available to view as one of my repos. The basic premise is that this website is a place to read and share comments about the news. This website pulls the day's news with NewsAPI. Users can create permanent accounts or timed burner accounts that cannot be logged back into after 24 hours. Authorization is handled with JSON Web Tokens and bcrypt. Features include threaded comments and the ability to like articles and comments. The database runs on MySQL.

I first created the backend for this website using Node.js as the backend. When learning Java, I decided to migrate the backend to Spring Boot and refactor the frontend as well. Both systems use JWT for authorization. I found the Spring Boot implementation much easier to manage once I got everything in place. 
