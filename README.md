NewsCycle Backend

This is the Springboot backend to a fullstack website called NewsCycle. The fronted, made with React, is also available to view as one of my repos. The basic premise is that this website is a place to read and share comments about the news. This website pulls the day's news with NewsAPI. Users can create permanent accounts or timed burner accounts that cannot be logged back into after 24 hours. Authorization is handled with JSON Web Tokens and bcrypt. Features include threaded comments and the ability to like articles and comments. The databse runs on MySQL.

I first created the backend for this website using Node.js as the backend. When learning Java, I decided to migrate the backend to Springboot and refactor the frontend as well.

Both systems use JWT for authorization. I found the Springboot implementation much easier to manage once I got everything in place. As this was my first foray into a Java-based web service, the learning curve was at times steep, but eventually manageable.
