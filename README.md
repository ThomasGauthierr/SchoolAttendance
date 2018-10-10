# FirstGrails (due date : 30 sept)

## Before you run
Be sure to adapt your application.yml to your configuration and to add a file "default-user.jpg" in the web server folder where the images of this project will be stored. It's the default image we use for users created in the boostrap.<br/>
You can find one in the folder "grails-app/assets/images".

# Part 1 : Grails

## Access control

### Users
Users with no specific roles can access to their matchs, their messages, and check their profile.

### Admins
Admins have the same rights than the users but have more complete panels. Only them have the right to create message, users, or matchs.

### Banned users
When an admin delete a user, he's kept in the database but flagged as banned. Those users have no rights at all. They are redirected to a page informing them they are banned when trying to connect.

### Not logged-in
Users who didn't sign in have access to nothing but the login form.

## Functionalities

### Users
As said above, admins have the right to create new users and to attribute them a profile picture. They can modify it later or ban the user.

### Matchs
All matches are stored in the database with the winner, the looser, and their scores. 

### Messages
Admins can create news messages. Not-read messages are displayed differently for the users if they have never opened them.

## Bonuses

### Drag'n Drop
To add or update a user image, we implemented a drag'n drop system.\
You can find the function in charge of this in */grails-app/assets/javascripts/dragAndDropImageUpload.js*.

### Cron 
We set up a cron which deletes read messages every day at 4:00AM, thanks to the [Quartz plugin](http://plugins.grails.org/plugin/grails/quartz). 
The object fulfilling this role is **MessageJob**, located in the */job/mbds/firstgrails* folder

# Part 2 : REST
//ToDo

## Links
[TP](http://cours.tokidev.fr/mbds/grails/tp_grails.pdf)  
[Courses](http://cours.tokidev.fr/mbds/grails/cours_grails.pdf)  
[Trello](https://trello.com/b/ssAz0JX8/projet-grails)

## Images hosting
1. Keep the image name in the user's attributes
2. Save the file into the web server
3. Rebuild the complete path using basic path (to store in the config file) + file name

#### Example : 
*In application.yml*\
filePath: "/var/www/tp"\
fileUrl: "http://127.0.0.1:8081/tp"
