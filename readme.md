# VIM PROJECTOR (CSE364_Group10)

<br>
<img src="https://github.com/hyejinkim99/markdown/blob/main/pic/wideLogo.png">

> ### ***Find Very Impressive Movies, VIM PROJECTOR***

<br>
<br>





## Introduction

Welcome to `VIM PROJECTOR`!  
`VIM PROJECTOR` is a **movie recommendation service** which recommends **Very Impressive Movies** according to given conditions.

### Function

1. **Recommendation by User Information😊**
   > Give the `genres` you wish to watch and your `gender`, `age` and `occupation`.  
   > Then you will receive a list of  **10 recommended movies** that belong to the given genres.

2. **Recommendation by Movie Information🎞️**
   > Give a `movie title` and the `number of movies` you wish to be recommended.  
   > Then you will get the given number of movies similar to the one you gave us.

3. **Feeling Lucky🍀**
   > Sometimes, recommendations of similar movies can be boring for you.  
   > Just click `feeling lucky`, then you will get a randomly recommended movie.  
   > If you are lucky, you may find an unpopular but unexpectedly impressive movie!

### Interface

1. **Web Service**
   > The website of `VIM PROJECTOR`.  
   > Simple and user-friendly. Easy to search.  
   > To see more details of web service, refer to [Web Service Guide](#web-service).

2. **REST API**
   > The command line interface of `VIM PROJECTOR`.  
   > Free to search. Make new applications.  
   > To see more details of REST API, refer to [REST API Guide](#rest-api).

`VIM PROJECTOR` recommends you a list of movies using infoamation such as `titles`, `genres`, and `links to imdb`.
There also is a feature that will give you a random movie.  
Find your next movie to watch with `VIM PROJECTOR`!!!

If you want more information about our **recommendation algorithm**, please see [Recommendation Algorithm](#recommendation-algorithm).  
Then, let's move on to quick start!

<br>
<br>
<br>





## Index

+ [Introduction](#introduction)
+ [Index](#index)
+ [Quick Start](#quick-start)
+ [Web Service](#web-service)
    + [Home](#home)
    + [Movie](#movie%EF%B8%8F)
    + [User](#user)
    + [Feeling Lucky](#feeling-lucky)
    + [Recommendation Page](#recommendation-page)
+ [REST API](#rest-api)
    + [Recommendation by User Information](#recommendation-by-user-information)
    + [Recommendation by Movie Information](#recommendation-by-movie-information)
    + [Feeling Lucky](#feeling-lucky-1)
    + [Get All Movie Information](#get-all-movie-information)
    + [Get Movie Title List](#get-movie-title-list)
+ [Recommendation Algorithm](#recommendation-algorithm)
    + [Abstract](#abstract)
    + [Details](#details)
+ [Contributors](#contributors)

<br>
<br>
<br>





## Quick Start
Just install `Docker` for running `VIM PROJECTOR` with our Dockerfile.
It will automatically install all the necessary program, and take codes.
All you need is to download the Dockerfile and the WAR file.

Put `Dockerfile` and `ROOT.war` file in a single folder, and run the commands below.
Access localhost:8080 with your web browser.
It will take 2~3 minutes to use the web application after running the program.
Please wait for a while if the web page is not properly acting.

```
$ docker build -t <image_name>
$ docker run -d -p 8080:8080 <image_name>
```

If you want details about Dockerfile, please refer below.

- `Java(version 11 or up)`, `Git`, `curl`,`vim`,`wget`,`mongodb`,`tomcat` and `Maven` installation must be preceded before installing and running this program.
- This program uses embedded MongoDB dependency.

If you don't want to use tomcat to run this program, use run.sh to build it.
This is the code for run.sh.
```
$ git clone $repository
$ cd $folder
$ git checkout main
$ mvn package
$ java -jar target/CSE364-project-0.0.1-SNAPSHOT.jar
```
This is the code to run docker container and run.sh.
```
$ docker build -t <image_name> .
$ docker run -it -d -p  8080:8080 <image_name>
$ docker exec -it <container_id> /bin/bash    
$ . run.sh    (in container)
```
Please refer to the following sections about details in [calling this API](#rest-api).

<br>
<br>
<br>








## Web Service

**For the Best User Experience**, we provide web service.  
Welcome to our user-friendly webpage of `VIM PROJECTOR`!

<br>

### Home👋

> http://localhost:8080/index.html  
> Top 10 movies welcome you.  
> For more services, click other tabs: `#Movie`, `#User`, `#Feeling Lucky`.
> Note that when you first load the home page after booting the application, it may take as long as two minutes for the home page to show.
>
> <table><tr><th><img src="https://github.com/hyejinkim99/markdown/blob/main/pic/welcomePage.png"></th></tr></table>

<br>

### Movie🎞️

> http://localhost:8080/movies/index.html  
> Choose a limit and then type in **movie title**.
> We provide an auto-completion feature when you type in the movie title. You will be able to see a list of candidate movies that contain what you are typing.
> Click a title from the list to choose a movie. This brings you to your result.
> `VIM PROJECTOR` recommends you highly rated movies based on genres of the given movie title.
>
> <table><tr><th width="60%"><img src="https://github.com/hyejinkim99/markdown/blob/main/pic/searchByMoviePage.png"></th><th><img src="https://github.com/hyejinkim99/markdown/blob/main/pic/searchByMovieTitle.gif"></th></tr></table>

<br>

### User😊

> http://localhost:8080/users/index.html  
> Select **hashtags** for each type of information. For gender, age and occupation, only one hashtag can be selected.
> Then, click on the search button.  
> `VIM PROJECTOR` recommends you top 10 movies based on the given user information.
>
> <table><tr><th width="60%"><img src="https://github.com/hyejinkim99/markdown/blob/main/pic/searchByUserPage.png"></th><th><img src="https://github.com/hyejinkim99/markdown/blob/main/pic/searchByUserInfo.gif"></th></tr></table>

<br>

### Feeling Lucky🍀

> http://localhost:8080/feelinglucky/index.html  
> Are you struggling with selecting movie?  
> **Feeling Lucky** is the answer!
> It shows you a random movie, so we wish you luck on finding a good one!
>
> <table><tr><th><img src="https://github.com/hyejinkim99/markdown/blob/main/pic/feelingLucky.png"></th></tr></table>

<br>

### Recommendation Page👍

> http://localhost:8080/users/recommendations?{inputs}  
> http://localhost:8080/movies/recommendations?{inputs}  
> Travel through our recommended movies.  
> For more information on a recommended movie, **just click the poster!**  
> It will take you to the `imdb` page of the movie.
>
> <table><tr><th width="50%"><img src="https://github.com/hyejinkim99/markdown/blob/main/pic/scroll.gif"></th><th><img src="https://github.com/hyejinkim99/markdown/blob/main/pic/interactive.gif"></th></tr></table>  

<br>
<br>
<br>





## REST API

`VIM PROJECTOR` provide **RESTful API** service.  
You can test it with `curl`.

<br>

### Recommendation by User Information

**API Basic Information**
|Method|Request URL|Content Type|Output Format|
|:---|:---|:---|:---|
|GET|http://localhost:8080/users/recommendations|application/x-www-form-urlencoded|JSON|

<br>

**Request Parameters**
|Name|Required|Default Value|Description|
|:---|:---|:---|:---|
|gender|mandatory|All genders|Available genders: `m`, `f`<br>- Wanted gender<br>- Leave this field blank for default option|
|age|mandatory|All ages|Available ages: `0 or higher integer`<br>- Wanted age<br>- Leave this field blank for default option<br>- Ages that are not integer or negative will be ignored|
|occupation|mandatory|All occupations|Available occupations: `academic`, `educator`, `artist`, `clerical`, `admin`, `collegestudent`, `gradstudent`, `customerservice`, `doctor`,`healthcare`, `executive`, `managerial`, `farmer`, `homemaker`, `k-12student`, `lawyer`, `programmer`, `retired`, `sales`, `marketing`, `scientist`, `self-employed`, `technician`, `engineer`, `tradesman`, `craftsman`, `unemployed`, `writer`, `other`<br>- Watned occupation<br>- Leave this field blank for default option<br>- Wrong occupations will be categorized as `other`|
|genres|mandatory|All genres|Available genres: `action`, `adventure`, `animation`, `children's`, `comedy`, `crime`, `documentary`, `drama`, `fantasy`, `film-noir`, `horror`, `musical`, `mystery`, `romance`, `scifi`, `thriller`, `war`, `western`<br>- Wanted genres<br>- Leave this field blank for default option<br>- In case you are entering several genres, please distinguish each genre by delimiter `\|` <br>- Wrong genres and repeated inputs will be ignored<br>- If there is no valid genre input, the program will search movie regardless of genres|

<br>

**Response Body**
|Key|Type|Description|
|:---|:---|:---|
|title|string|Titles of recommended movies|
|genres|string|Genres of recommended movies|
|imdb|string|Link of receommended movies|
|poster_url|string|Link of poster of receommended movies|

<br>

**Sample Request**
```
curl -X GET "http://localhost:8080/users/recommendations?gender=m&age=24&occupation=gradstudent&genres=action|adventure"
```

<br>

**Sample Response**
```
[{
    "title" : "Sanjuro (1962)",
    "genres" : "Action|Adventure",
    "imdb" : "(http://www.imdb.com/title/tt0056443)",
    "poster_url" : "https://m.media-amazon.com/images/M/MV5BZmY3MDlmODctYTY3Yi00NzYyLWIxNTUtYjVlZWZjMmMwZTBkXkEyXkFqcGdeQXVyMzAxNjg3MjQ@..jpg"
},
{
    "title" : "Man Who Would Be King, The (1975)",
    "genres" : "Adventure",
    "imdb" : "(http://www.imdb.com/title/tt0073341)",
    "poster_url" : "https://m.media-amazon.com/images/M/MV5BZWQzYjBjZmQtZDFiOS00ZDQ1LWI4MDAtMDk1NGE1NDBhYjNhL2ltYWdlXkEyXkFqcGdeQXVyNjc1NTYyMjg@..jpg"
},
...]
```

<br>

### Recommendation by Movie Information

**API Basic Information**
|Method|Request URL|Content Type|Output Format|
|:---|:---|:---|:---|
|GET|http://localhost:8080/movies/recommendations|application/x-www-form-urlencoded|JSON|

<br>

**Request Parameters**
|Name|Required|Default Value|Description|
|:---|:---|:---|:---|
|title|mandatory|-|- Capitalization does matter<br>- ISO-8859-15 encoding required<br>- If input title is invalid, the program will recommend highly rated movies|
|limit|mandatory|10|Available limit: `1 or higher integer`<br>- Number of recommended movies<br>- If limit is zero or negative, the program will recommend 10 movies|

<br>

**Response Body**
|Key|Type|Description|
|:---|:---|:---|
|title|string|Titles of recommended movies|
|genres|string|Genres of recommended movies|
|imdb|string|Link of receommended movies|
|poster_url|string|Link of poster of receommended movies|

<br>

**Sample Request**
```
curl -X GET "http://localhost:8080/movies/recommendations?title=Toy+Story+(1995)&limit=20"
```

<br>

**Sample Response**
```
[{
    "title" : "Toy Story 2 (1999)",
    "genres" : "Animation|Children's|Comedy",
    "imdb" : "(http://www.imdb.com/title/tt0120363)",
    "poster_url" : "https://m.media-amazon.com/images/M/MV5BMWM5ZDcxMTYtNTEyNS00MDRkLWI3YTItNThmMGExMWY4NDIwXkEyXkFqcGdeQXVyNjUwNzk3NDc@..jpg"
},
{
    "title" : "Chicken Run (2000)",
    "genres" : "Animation|Children's|Comedy",
    "imdb" : "(http://www.imdb.com/title/tt0120630)",
    "poster_url" : "https://m.media-amazon.com/images/M/MV5BY2UyYjFkNzAtYzIyMC00MGI1LTlkNDktNzUyOGQ5NTI2ZGFjXkEyXkFqcGdeQXVyNTUyMzE4Mzg@..jpg"
},
...]
```
<br>

### Feeling Lucky

**API Basic Information**
|Method|Request URL|Content Type|Output Format|
|:---|:---|:---|:---|
|GET|http://localhost:8080/feelinglucky/recommendations|application/x-www-form-urlencoded|JSON|

<br>


**Response Body**
|Key|Type|Description|
|:---|:---|:---|
|title|string|Titles of recommended movies|
|genres|string|Genres of recommended movies|
|imdb|string|Link of receommended movies|
|poster_url|string|Link of poster of receommended movies|

<br>


**Sample Request**
```
curl -X GET http://localhost:8080/feelinglucky/recommendations
```
<br>


**Sample Response**
```
[{
    "title" : "Crumb (1994)",
    "genres" : "Documentary",
    "imdb" : "(http://www.imdb.com/title/tt0109508)",
    "poster_url" : "https://m.media-amazon.com/images/M/MV5BYTViMDM5YjktMDhjNy00MjZjLTg2ODctOGI3MzkzYWNiODA0XkEyXkFqcGdeQXVyNTAyODkwOQ@@..jpg"
}]
```
<br>

### Get All Movie Information

**API Basic Information**
|Method|Request URL|Content Type|Output Format|
|:---|:---|:---|:---|
|GET|http://localhost:8080/movies|application/x-www-form-urlencoded|JSON|

<br>


**Response Body**
|Key|Type|Description|
|:---|:---|:---|
|movieId|int|Id of recommended movies|
|title|string|Titles of recommended movies|
|genres|string|Genres of recommended movies|

<br>


**Sample Request**
```
curl -X GET http://localhost:8080/movies
```
<br>


**Sample Response**
```
[{
    "movieId" : 1,
    "title" : "Toy Story (1995)",
    "genres" : "Animation|Children's|Comedy"
},
{
    "movieId" : 2,
    "title" : "Jumanji (1995)",
    "genres" : "Adventure|Children's|Fantasy"
},
...]
```

<br>

### Get Movie Title List

**API Basic Information**
|Method|Request URL|Content Type|Output Format|
|:---|:---|:---|:---|
|GET|http://localhost:8080/movies/title|application/x-www-form-urlencoded|JSON|

<br>

**Response**  
List of strings

<br>

**Sample Request**
```
curl -X GET http://localhost:8080/movies/title
```

<br>

**Sample Response**
```
[ "Toy Story (1995)", "Jumanji (1995)", ...]
```

<br>
<br>
<br>





## Recommendation Algorithm

### Abstract
The criteria for recommending movies in `VIM PROJECTOR` are `rating` and `count`.

> `rating` is the average score of a movie that belongs to `genres` you input (`target genres`) and is rated by users belonging to your categories of `gender`, `age`, and `occupation` (`target users`).

> `count` is the number of ratings of a movie that belongs to the target genres and rated by the target users.

In our algorithms, movies with **high rating averages** will be selected as recommendations. In each step in the algorithms, rating data of the movies that fit the conditions (`target movies`) will be processed and sorted. Top N movies in the sorted list will be selected to be recommended, with N being the number of movies required.

For a movie to be recommended, the `rating average` of a movie must be **equal or above 3.5**, and the `count` of the movie must be **equal or above 1%** of the number of total rating data.

<br>

### Details

`VIM PROJECTOR` uses two different movie recommendation algorithms depending on different inputs, **user information** and **movie information**.

1. **Recommendation by user information**

   > By **user information**, we mean your `gender`, `age`, `occupation` and `genres` you wish to watch.
   > When given **user information**, `VIM PROJECTOR` will recommend movies that are in the **target genres** and have high ratings by **target users**.  
   > The steps in this algorithm are shown below.
   >
   > 1.  Generate a list of 10 recommended movies that are in the `target genres` and are rated highly by `target users`. If `target genres` are not given, the algorithm will search for and recommend movies in all genres.
   > 2.  If the number of movies in the generated list is less than 10, then expand the range of `target users` by expanding the range of `occupation` to **all occupations** and search again. Then add the recommended movies to the list.
   > 3.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` again by expanding the range of `age` to **all age groups** and search again. Then add the recommended movies to the list.
   > 4.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` once more by expanding the range of `gender` to **all genders** and search again. Then add the recommended movies to the list.

    <details>
    <summary>Show an example</summary>
    <div markdown="1">
    <br>

   > Let’s see an example of {`gender`: "m", `age`: "56", `occupation`: "k-12student", `genres`: "" } given as an input of **user information**. Since the genres field is not given, movies in all genres will be the candidates for recommendation.
   >
   > According to this algorithm, the generation of a movie recommendation list would follow the process shown below.
   >
   > 1.  A list of recommended movies is generated using the given `target user` data and `target genres` data. In this example, there is only one `target user`. The number of movies contained in this list of recommended movies is less than 10.
   > 2.  Since there are less than 10 movies in the list, expand the range of `target users` by allowing the search for **all occupations**. Now the conditions for the `target users` would be {`gender`: "m", `age`: "56", `occupation`: "", `genres`: "" }. Add the recommended movies based on the new range of `target users`.
   > 3.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` by allowing the search for **all age groups**. Now the conditions for the `target users` would be {`gender`: "m", `age`: "", `occupation`: "", `genres`: "" }. Add the recommended movies based on the new range of `target users`.
   > 4.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` by allowing the search for **all genders**. Now the conditions for the `target users` would be {`gender`: "", `age`: "", `occupation`: "", `genres`: "" }. Add the recommended movies based on the new range of `target users`.
    </div>
    </details>

<br>


2. **Recommendation by movie information**

   > By **movie information**, we mean a `movie title` and a `limit number`. When given **movie information**, `VIM PROJECTOR` will recommend high-rated movies similar to the given movie and up to the given `limit number`. The similarity of the movies are assessed by the `genres` of the movies.
   > The steps in this algorithm are shown below.
   >
   > 1.  Generate a list of recommended movies that are in the **same genres** as the given movie.
   > 2.  If the number of movies in the generated list is less than the given `limit number`, add the recommended movies that belong to genres that **include all the genres** of the given movie to the list.
   > 3.  If the number of movies in the generated list is still less than the given `limit number`, add the recommended movies that belong to **at least one genre** of the given movie.
   > 4.  If the number of movies in the generated list is still less than the given `limit number`, add the recommended movies that belong to **all genres**.

    <details>
    <summary>Show an example</summary>
    <div markdown="1">
    <br>

   > Let’s see an example of {`title`: "Toy Story (1995)", `limit`: 20} given as an input of `movie information`. The `genres` of this movie are `Animation`, `Children's`, and `Comedy`. A list of 20 recommended movies should be generated.
   >
   > According to this algorithm, the generation of a movie recommendation list would follow the process shown below.
   >
   > 1.  A list of recommended movies is generated from the data of movies that only belong to all three `genres` of `Animation`, `Children's`, and `Comedy` at once. The generated list contains only one movie.
   > 2.  Then recommendations are searched within the data of movies that belong to all three `genres` and more. One such example would be a movie that belongs to `genres` of `Animation`, `Children’s`, `Comedy` and `Action`. However, in this case there are no movies added to the list.
   > 3.  Now the recommendations are searched from the data of movies that belong to at least one `genre` of the given three `genres`. This means that the recommended movies could belong to any one `genre` from `Animation`, `Children’s`, or `Comedy`, or to any two of these `genres`. In this example, there are 19 movies added to the generated list.
   > 4.  In this example, the list of 20 recommended movies is now generated. However, if the number of movies in the generated list is still less than the given `limit number`, add the recommended movies that belong to **all genres**.

    </div>
    </details>
    
<br>
<br>
<br>





## Contributors

<table>
    <tr>
        <th rowspan="2">NAME<br>(Student ID)</th>
        <th colspan="4">ROLE</th>
    </tr>
    <tr>
        <th width="25%">Milestone #1</th>
        <th width="25%">Milestone #2</th>
        <th width="25%">Milestone #3</th>
        <th width="25%">Milestone #4</th>
    </tr>
    <tr>
        <td>박소연<br>Soyeon Park<br>(20171113)</td>
        <td>- Suggest algorithm utilizing boolean ArrayList
        <br>- Implement code that receives boolean ArrayList as input and returns Rating as output
        </td>
        <td>- Implement 'recommender' package
        <br>- Implement 'recommender' package test codes
        </td>
        <td>- Implement 'Mileston2Test' and 'Milestone3Test'
        <br>- Edit 'Milestone2' and 'Milestone3' for include limit parameter
        </td>
        <td>- Make classes using mongodb
        <br>- Implement and design front-end
        <br>- Write 'Introduction' and link 'Index' of readme.md
        <br>- Make presentation PPT
        </td>
    </tr>
    <tr>
        <td>안종민<br>Jongmin Ahn<br>(20171155)</td>
        <td>- Implement code that processes parsed data as a boolean ArrayList
        <br>- Edit pom.xml
        </td>
        <td>- Implement 'parsinginputargs' package
        <br>- Implement 'loadingdata' package test codes
        </td>
        <td>- Edit Docker for Milestone3
        <br>- Implement 'loadingdata' package test codes
        <br>- Reimplement 'TopRating' class
        </td>
        <td>- Backend development
        <br>- Revised Dockerfile, run.sh, pom.xml
        <br>- Web application deployment
        <br>- Write 'quick start' part of readme.md
        <br>- Presentation
        </td>
    </tr>
    <tr>
        <td>류성화<br>Sungwha Ryu<br>(20171375)</td>
        <td>- Implement code that processes parsed data as a boolean ArrayList
        <br>- Translate README.md
        </td>
        <td>- Implement 'recommender' package
        <br>- Implement 'Milestone2.java' test codes
        </td>
        <td>- Apply Spring framework
        <br>- Implement 'Milestone3'
        <br>- Add 'stringStringloadHashFrom' method in 'FilePreprocessing.java' to get genres by title
        </td>
        <td>- Backend development
        <br>- Revised Dockerfile, run.sh, pom.xml
        <br>- Web application deployment
        <br>- Make presentation PPT
        </td>
    </tr>
    <tr>
        <td>김혜진<br>Hyejin Kim<br>(20181072)</td>
        <td>- Implement code that receives and parses arguments as inputs
        <br>- write first draft of README.md
        </td>
        <td>- Implement 'loadingdata' package
        <br>- Implement 'customdatastructure' and 'parsinginputargs' package test codes
        </td>
        <td>- Implement 'FilteringDataByCondition'
        <br>- Implement 'FilteringDataByCondition' test codes
        </td>
        <td>- Install and intergrate mongoDB with spring boot
        <br>- Implement and design front-end
        <br>- Write 'web service' and 'rest api' part of readme.md
        <br>- Make presentation PPT
        </td>
    </tr>
</table>
