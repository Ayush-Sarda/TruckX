#This project was build upon Spring Boot framework and PostgreSQL database.
# The architecture is comprised of two modules
  1. dashcam
  2. admin
  
Dashcam
# The dashcam module is responsible for the calls coming from the dashcam.
# The upload video endpoint is using one assumption i.e. the video will be uploaded and sent as a request parameter on the endpoint along with imei and filename as well.
# The video upload endpoint uses S3 for its cloud upload. The credentials for the same can be put in the application.properties file.

Admin
# The admin module is reponsible for exposing the endpoints to the admin user.
# The send command endpoint assumes a base url for any dashcam followed by imei number as a path variable, since there was no information provided as to where to send the request.
