I've setup an organization called Bianova on https://cloud.mongodb.com/.

Inside the Oganization, there is a project called "App DB" - this is where i'll be test sending the data

The project has one MongoDB cluster running on AWS. The cluster is called Cluster0 and it uses the free tier.

I've created a user called `bianova` with the password `Nkcw1FNL9SUIJXh1` (this is user is not used to log into the mongodb website, that's what I need your emails for. This `bianova` user is a separate user that is used to connect to the db from code)

Mongo also by default restricts access to manually set IP addresses. I checked the "access from anywhere" option that sets the IP to 0.0.0.0/0 and enables network access from any IP.