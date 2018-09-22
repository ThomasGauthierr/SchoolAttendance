# FirstGrails (due date : 30 sept)

## Before you run
Be sure to adapt your application.yml to your configuration and to add a file "default-user.jpg" in the web server folder where the images of this project will be stored.<br/>
You can find one in the folder "grails-app/assets/images".

## TP Link
[Here](http://cours.tokidev.fr/mbds/grails/tp_grails.pdf).

## Courses
[Click click](http://cours.tokidev.fr/mbds/grails/cours_grails.pdf).

## Trello
[Here](https://trello.com/b/ssAz0JX8/projet-grails).

## Images hosting
1. Keep the image name in the user's attributes
2. Save the file into the web server
3. Rebuild the complete path using basic path (to store in the config file) + file name

#### Example : 
*In application.yml*\
filePath: "/var/www/tp"\
fileUrl: "http://127.0.0.1:8081/tp"