I've setup an organization called Bianova on https://cloud.mongodb.com/.

Inside the Oganization, there is a project called "App DB" - this is where i'll be test sending the data

The project has one MongoDB cluster running on AWS. The cluster is called Cluster0 and it uses the free tier.

I've created a user called `bianova` with the password `Nkcw1FNL9SUIJXh1` (this is user is not used to log into the mongodb website, that's what I need your emails for. This `bianova` user is a separate user that is used to connect to the db from code)

Mongo also by default restricts access to manually set IP addresses. I checked the "access from anywhere" option that sets the IP to 0.0.0.0/0 and enables network access from any IP.

I obtained some sample recipe JSON respones from [Rapid API](https://rapidapi.com/spoonacular/api/recipe-food-nutrition/), this is saved in the control/src/main/java/com/bianova/control/example_api_response.json file (a copy of this is also stored in the control directory, so that it can be read by the app)

I turned the json file into JAVA classes using https://www.jsonschema2pojo.org (after there were necessary adjustments), i saved the copy of the generated classes in control/src/main/java/com/bianova/control/generated_classes

I used [this tutorial](https://www.baeldung.com/jackson-object-mapper-tutorial) to load the sample JSON data into the class

I then tried just returning the recipe as a response to the browser. one of the fields in the original JSON (WineParing) was empty, and the serializer had an issue with it. As advised by the output, I added `SerializationFeature.FAIL_ON_EMPTY_BEANS` to the application.properties file, which fixed this

I then wanted to connect the app with the previously setup DB, [this tutorial](https://www.mongodb.com/compatibility/spring-boot) instructed me to add the following to the application.properties file:
    ```
    spring.data.mongodb.uri=mongodb+srv://<username>:<pwd>@<cluster>.mongodb.net/...
    spring.data.mongodb.database=<dbname>
    ```
Obviously I needed the connection strings that are specific to our setup, I found them in [our Mongo page](https://cloud.mongodb.com/v2/62a795f2f43ac9724490df3d#clusters) in the Connect section:
    ```
    spring.data.mongodb.uri=mongodb+srv://bianova:Nkcw1FNL9SUIJXh1@cluster0.gepytxe.mongodb.net/?retryWrites=true&w=majority
    spring.data.mongodb.database=Cluster0
    ```
So I used these in the application.properties file, and the output confirmed that the connection was successful.

I then followed [this tutorial](https://spring.io/guides/gs/accessing-data-mongodb/) to create basic db functionality
    - I created a repository interface (adds the "findRecipeByTitle" functinality)
    - Instead of using the application class, I added the rest of the functionality to the Controller class (so that it executes on an API call)
        - I added /add and /delete, it either adds the recipe from the json or deletes all data from mongo
    - i ran it and confirmed in the mongo web interface that it's worked

To run, run the "ControlApplication" class and go to localhost:8080/ in your browser (localhost:8080/add or localhost:8080/delete)


