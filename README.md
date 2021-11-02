#Links 
- https://www.youtube.com/watch?v=her_7pa0vrg&t=3963s&ab_channel=Amigoscode
- https://jwt.io/
- https://github.com/jwtk/jjwt

## Cross Site Request Forgery
The action of forging a copy of a document, signature, banknote, or work of art.

#### Step 1:
The perpetrator forges a request for a fund transfer to a website.

#### Step 2:
The perpetrator embeds the request into a hyperlink and sends it to visitors who may be logged into the site.

#### Step 3:
A visitor clicks on the link, inadvertently sending the request to the website.

#### Step 4:
The website validates the request and transfers funds from the visitor from the visitor's account to the perpetrator.

## Another way of configuring AntMatchers
- antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
- antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
- antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
- antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), TRAINEE.name())